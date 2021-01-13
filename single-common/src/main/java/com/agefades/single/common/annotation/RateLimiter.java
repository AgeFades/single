package com.agefades.single.common.annotation;

import com.agefades.single.common.enums.RateLimiterTypeEnum;
import org.springframework.core.annotation.AliasFor;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 限流注解, {@link AliasFor} 必须通过 {@link AnnotationUtils} 才会生效
 * 默认: 通过请求参数限流，3秒钟内 最大请求1次
 *
 * @author DuChao
 * @date 2020/10/16 2:45 下午
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {

    long DEFAULT_MAX_REQUEST = 1;

    /**
     * 限流类型 {@link RateLimiterTypeEnum}，默认通过请求参数限流
     */
    RateLimiterTypeEnum type() default RateLimiterTypeEnum.PARAM;

    /**
     * max 最大请求数, 默认1
     */
    @AliasFor("value") long max() default DEFAULT_MAX_REQUEST;

    /**
     * max 最大请求数, 默认1
     */
    @AliasFor("max") long value() default DEFAULT_MAX_REQUEST;

    /**
     * 超时时长，默认 3 秒
     */
    long timeout() default 3;

    /**
     * 超时时间单位，默认 秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
