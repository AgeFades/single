package com.agefades.single.common.config;

import com.agefades.single.common.util.LogUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 日志TraceId过滤器
 *
 * @author DuChao
 * @date 2020/9/10 3:13 下午
 */
@Order(1)
@Configuration
public class LogTraceIdFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        LogUtil.setTraceId();
        try {
            filterChain.doFilter(request, response);
        } finally {
            LogUtil.clearAll();
        }
    }

}
