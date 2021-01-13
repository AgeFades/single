package com.agefades.single.admin.biz.sys.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import com.agefades.single.admin.biz.sys.dto.CacheMenuDTO;
import com.agefades.single.admin.biz.sys.dto.ClientMenuDTO;
import com.agefades.single.admin.biz.sys.dto.SysUserDTO;
import com.agefades.single.admin.biz.sys.entity.SysUser;
import com.agefades.single.admin.biz.sys.req.LoginReq;
import com.agefades.single.admin.biz.sys.resp.LoginResp;
import com.agefades.single.admin.biz.sys.service.SysUserService;
import com.agefades.single.admin.constants.RedisConstant;
import com.agefades.single.admin.enums.AdminResultCodeEnum;
import com.agefades.single.common.enums.BoolEnum;
import com.agefades.single.common.util.Assert;
import com.agefades.single.common.util.JwtUtil;
import com.agefades.single.common.util.RedisUtil;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 登录相关服务
 *
 * @author DuChao
 * @date 2021/1/13 4:05 下午
 */
@Slf4j
@Service
@AllArgsConstructor
public class LoginDomain {

    private final SysUserService sysUserService;

    private final RedisUtil redisUtil;

    /**
     * 用户登录
     * 每次登录生成新 token、实时查找用户数据、用户权限
     *
     * @param req {@link LoginReq}
     * @return {@link LoginResp}
     */
    public LoginResp login(LoginReq req) {
        // 1. 基本校验: 用户是否存在、密码是否匹配、状态是否启用
        SysUser user = sysUserService.lambdaQuery()
                .eq(SysUser::getUsername, req.getUsername())
                .one();
        Assert.notNull(user, AdminResultCodeEnum.USER_USERNAME_NOT_FOUND);
        Assert.isTrue(DigestUtil.bcryptCheck(req.getPassword(), user.getPassword()), AdminResultCodeEnum.USER_PWD_INVALID);
        Assert.isTrue(user.getIsEnable() == BoolEnum.Y.getCode(), AdminResultCodeEnum.USER_IS_DISABLE);

        // 2. 基本校验通过、处理缓存信息
        String userId = user.getId();
        String hashKey = RedisConstant.SYS_USER_INFO_PREFIX + userId;
        Map<String, String> cacheMap = Maps.newHashMap();

        // 缓存 token
        String token = JwtUtil.createJwt(userId);
        cacheMap.put(RedisConstant.SYS_USER_TOKEN, token);

        // 构建返回对象
        LoginResp.LoginRespBuilder respBuilder = LoginResp.builder()
                .isAdmin(user.getIsAdmin())
                .username(user.getUsername())
                .token(token);

        // 缓存 用户信息
        SysUserDTO dto = BeanUtil.copyProperties(user, SysUserDTO.class);
        cacheMap.put(RedisConstant.SYS_USER_DTO, JSONUtil.toJsonStr(dto));

        // 处理 用户权限
        List<ClientMenuDTO> permission = sysUserService.getPermission(userId);
        if (CollUtil.isNotEmpty(permission)) {
            // 获取缓存权限列表
            List<CacheMenuDTO> cachePermissionList = permission.stream()
                    .filter(a -> StrUtil.isNotBlank(a.getUri()))
                    .map(a -> BeanUtil.copyProperties(a, CacheMenuDTO.class))
                    .collect(Collectors.toList());
            cacheMap.put(RedisConstant.SYS_USER_PERMISSION, JSONUtil.toJsonStr(cachePermissionList));

            // 获取客户端权限列表
            List<ClientMenuDTO> clientPermissionList = buildTree(permission);
            respBuilder.permissionTree(clientPermissionList);
        }

        // Redis Hash 缓存用户数据
        redisUtil.hPutAll(hashKey, cacheMap);

        // 设置过期时间
        Long ttl = Boolean.TRUE.equals(req.getRememberMe()) ? RedisConstant.SYS_USER_TOKEN_REMEMBER_EXPIRE : RedisConstant.SYS_USER_TOKEN_EXPIRE;
        redisUtil.expire(hashKey, ttl, TimeUnit.HOURS);

        return respBuilder.build();
    }

    /**
     * 递归构建树结构
     */
    private List<ClientMenuDTO> buildTree(List<ClientMenuDTO> list) {
        List<ClientMenuDTO> tree = new ArrayList<>();
        list.forEach(p -> {
                    if ("0".equals(p.getPid())) {
                        tree.add(findChild(p, list));
                    }
                });
        tree.sort(Comparator.comparing(ClientMenuDTO::getWeight));
        return tree;
    }

    /**
     * 查找子节点
     *
     * @param root 当前根节点
     * @param list 节点集合
     * @return 树节点
     */
    private ClientMenuDTO findChild(ClientMenuDTO root, List<ClientMenuDTO> list) {
        if (CollUtil.isEmpty(root.getChild())) {
            root.setChild(new ArrayList<>());
        }
        list.forEach(p -> {
            if (p.getPid().equals(root.getId())) {
                root.getChild().add(findChild(p, list));
            }
        });
        root.getChild().sort(Comparator.comparing(ClientMenuDTO::getWeight));
        return root;
    }

    /**
     * 退出登录
     */
    public void logout(HttpServletRequest req) {
        redisUtil.delete(RedisConstant.SYS_USER_INFO_PREFIX + JwtUtil.parseJwt(JwtUtil.getJwtFromRequest(req)));
    }

}
