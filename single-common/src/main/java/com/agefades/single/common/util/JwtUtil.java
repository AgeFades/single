package com.agefades.single.common.util;

import cn.hutool.core.util.StrUtil;
import com.agefades.single.common.enums.CommonResultCodeEnum;
import com.agefades.single.common.exception.BizException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * Jwt 工具类
 *
 * @author DuChao
 * @date 2021/1/13 4:07 下午
 */
@Slf4j
public class JwtUtil {

    /**
     * 加密Key
     */
    private static final String KEY = "AgeFades";

    /**
     * 认证请求头名称
     */
    private static final String AUTH_HEADER = "Authorization";

    /**
     * Jwt 前缀
     */
    private static final String JWT_PREFIX = "Bearer ";

    /**
     * jwt 刷新token增加时间，默认值：7200L {@code 2小时}.
     */
    public static final Long REFRESH_TTL = 7200L;

    /**
     * jwt 需要刷新token时间，默认值：1800L {@code 0.5小时}.
     */
    public static final Long REFRESH_SCORE_TTL = 1800L;

    /**
     * 创建 Jwt Token
     *
     * @param id 用户id
     * @return token
     */
    public static String createJwt(String id) {
        return Jwts.builder()
                .setId(id)
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }

    /**
     * 解析 Jwt
     *
     * @param jwt token
     * @return 客户id
     */
    public static String parseJwt(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(KEY)
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getId();

        } catch (Exception e) {
            log.warn("Jwt[{}]解析失败: ", jwt, e);
            throw new BizException(CommonResultCodeEnum.TOKEN_PARSE_ERROR);
        }
    }

    /**
     * 从 request 的 header 中获取 Jwt
     */
    public static String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTH_HEADER);
        if (StrUtil.isNotBlank(bearerToken) && bearerToken.startsWith(JWT_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
