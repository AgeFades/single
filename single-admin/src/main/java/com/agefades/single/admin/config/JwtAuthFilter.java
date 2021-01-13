package com.agefades.single.admin.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.agefades.single.admin.biz.sys.dto.CacheMenuDTO;
import com.agefades.single.admin.biz.sys.dto.SysUserDTO;
import com.agefades.single.admin.constants.IgnoreAuthConstant;
import com.agefades.single.admin.constants.RedisConstant;
import com.agefades.single.admin.enums.AdminResultCodeEnum;
import com.agefades.single.admin.util.SysThreadLocalUtil;
import com.agefades.single.common.constants.CommonConstant;
import com.agefades.single.common.enums.BoolEnum;
import com.agefades.single.common.enums.CommonResultCodeEnum;
import com.agefades.single.common.exception.BizException;
import com.agefades.single.common.util.JwtUtil;
import com.agefades.single.common.util.RedisUtil;
import com.agefades.single.common.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Jwt 认证、鉴权过滤器
 *
 * @author DuChao
 * @date 2020/9/1 3:59 下午
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final RedisUtil redisUtil;

    /**
     * Spring REST ful 风格路径匹配
     */
    private final static AntPathMatcher MATCHER = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type,XFILENAME,XFILECATEGORY,XFILESIZE,x-requested-with,Authorization");
            response.setHeader("Access-Control-Allow-Credentials", "true");

            String uri = request.getServletPath();

            // 判断请求是否不需要登录、鉴权
            if (checkIgnores(uri)) {
                filterChain.doFilter(request, response);
                return;
            }

            // 校验请求 token
            String jwt = JwtUtil.getJwtFromRequest(request);
            if (StrUtil.isNotBlank(jwt)) {
                String userId;
                try {
                    userId = JwtUtil.parseJwt(jwt);
                } catch (BizException e) {
                    ResponseUtil.renderJson(response, e.getCode(), e.getMessage());
                    return;
                }
                String hashKey = RedisConstant.SYS_USER_INFO_PREFIX + userId;
                if (!redisUtil.hasKey(hashKey) || ObjectUtil.isNull(userId)) {
                    log.warn("token过期,token: {}, userId: {}", jwt, userId);
                    ResponseUtil.renderJson(response, CommonResultCodeEnum.TOKEN_PARSE_ERROR);
                    return;
                }
                Object sysUserInfoDtoStr = redisUtil.hGet(hashKey, RedisConstant.SYS_USER_DTO);

                if (ObjectUtil.notEqual(jwt, redisUtil.hGet(hashKey, RedisConstant.SYS_USER_TOKEN))) {
                    log.warn("账户在其他地方登录，token: {}, userId: {}", jwt, userId);
                    ResponseUtil.renderJson(response, CommonResultCodeEnum.TOKEN_NOT_EQUAL_ERROR);
                    return;
                }

                if (ObjectUtil.isNull(sysUserInfoDtoStr)) {
                    log.warn("非法token，token: {}, userId: {}", jwt, userId);
                    ResponseUtil.renderJson(response, CommonResultCodeEnum.TOKEN_PARSE_ERROR);
                    return;
                }

                // 刷新 token
                Long expire = redisUtil.getExpire(hashKey);
                if (expire <= JwtUtil.REFRESH_SCORE_TTL) {
                    redisUtil.expire(hashKey, expire + JwtUtil.REFRESH_TTL, TimeUnit.SECONDS);
                }

                SysUserDTO sysUserDTO = JSONUtil.toBean(sysUserInfoDtoStr.toString(), SysUserDTO.class);

                boolean isOnlyLogin = false;
                if (CollUtil.isNotEmpty(IgnoreAuthConstant.ONLY_LOGIN)) {
                    isOnlyLogin = IgnoreAuthConstant.ONLY_LOGIN.stream().anyMatch(e -> MATCHER.match(e, uri));
                }

                // 判断 是否仅需登录即可访问，否的话进入下层判断
                if (!isOnlyLogin) {
                    // 判断 是否管理员, 否则需要鉴权
                    if (ObjectUtil.notEqual(BoolEnum.Y.getCode(), sysUserDTO.getIsAdmin())) {
                        // 进行鉴权
                        Object permissions = redisUtil.hGet(hashKey, RedisConstant.SYS_USER_PERMISSION);
                        List<CacheMenuDTO> list = JSONUtil.toList(JSONUtil.parseArray(permissions), CacheMenuDTO.class);
                        if (!hasPermission(uri, list)) {
                            log.warn("权限拦截: userId: {}, uri: {}", userId, uri);
                            ResponseUtil.renderJson(response, AdminResultCodeEnum.USER_NO_PERMISSION);
                            return;
                        }
                    }
                }

                try {
                    SysThreadLocalUtil.setCurUser(sysUserDTO);
                    filterChain.doFilter(request, response);
                } finally {
                    SysThreadLocalUtil.reset();
                }
            } else {
                log.info("登录拦截: {}", uri);
                ResponseUtil.renderJson(response, CommonResultCodeEnum.TOKEN_NOT_FOUND_ERROR);
            }
        } catch (Exception e) {
            log.error("后台JWT过滤异常", e);
        }
    }

    /**
     * 判断当前用户是否有访问该路径的权限
     *
     * @param uri         请求 URI，如 localhost:8001/auth，这里取值 /auth
     * @param permissions 当前用户的权限集合
     * @return 是否有权限
     */
    private boolean hasPermission(String uri, List<CacheMenuDTO> permissions) {
        return CollUtil.isNotEmpty(permissions) && permissions.stream().anyMatch(p -> MATCHER.match(p.getUri(), uri));
    }

    /**
     * 请求是否不需要进行认证拦截
     *
     * @param uri 请求 URI，如 localhost:8001/auth，这里取值 /auth
     * @return true : 忽略 ，false : 不忽略
     */
    private boolean checkIgnores(String uri) {
        // 判断是否在 不需要登录、鉴权 基本配置中，true 时直接返回
        if (CommonConstant.IGNORE_PATH.stream().anyMatch(uri::contains)) {
            return true;
        }

        // 判断是否 匹配 配置忽略路径
        return CollUtil.isNotEmpty(IgnoreAuthConstant.IGNORE_PATTERN) && IgnoreAuthConstant.IGNORE_PATTERN.stream().anyMatch(v -> MATCHER.match(v, uri));
    }

    public static void main(String[] args) {
        AntPathMatcher matcher = new AntPathMatcher();
        // false
        System.out.println(matcher.match("/user/detail", "/user/detail/"));
        System.out.println(matcher.match("/user/**/do", "/user/do/xx"));

        // true
        System.out.println(matcher.match("/user/**/detail", "/user/1/2/detail"));
        System.out.println(matcher.match("/user/detail", "/user/detail"));
        System.out.println(matcher.match("/user/detail/**", "/user/detail/1"));
        System.out.println(matcher.match("/user/**/do", "/user/xx/do"));
        System.out.println(matcher.match("/**/xx/do", "/user/xx/do"));
        System.out.println(matcher.match("", "/test/fail"));
    }

}
