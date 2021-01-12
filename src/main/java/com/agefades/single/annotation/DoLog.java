package com.agefades.single.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记: 需要日志记录方法入参、出参、耗时
 *
 * @author DuChao
 * @date 2020/7/27 5:45 下午
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DoLog {
}
