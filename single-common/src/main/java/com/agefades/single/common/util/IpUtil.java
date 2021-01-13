package com.agefades.single.common.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取请求Ip地址工具类
 *
 * @author DuChao
 * @date 2021/1/12 3:41 下午
 */
@Slf4j
public class IpUtil {

    private final static String UNKNOWN = "unknown";
    private final static int MAX_LENGTH = 15;

    /**
     * 获取 IP 地址
     * 使用 Nginx 等反向代理软件时，则不能通过 request.getRemoteAddr() 获取 Ip 地址
     * 如果使用了多级反向代理的话，X-Forwarded-For 的值并不止一个，而是一串Ip 地址
     * X-Forwarder-For 中第一个非 unknown 的有效 Ip 字符串，则为真实的 Ip 地址
     */
    public static String getIpAddr(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null){
            return "127.0.0.1";
        }

        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String ip = "";

        try {
            ip = request.getHeader("x-forwarded-for");
            if (StrUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)){
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StrUtil.isEmpty(ip) || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StrUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StrUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StrUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e){
            log.error("IpUtil getIpAddr Error: ",e);
        }

        // 使用代理，则获取第一个 Ip 地址
        if (StrUtil.isNotBlank(ip) && ip.length() > MAX_LENGTH){
            if (ip.indexOf(StrUtil.COMMA) > 0){
                ip = ip.substring(0, ip.indexOf(StrUtil.COMMA));
            }
        }
        return ip;
    }

}
