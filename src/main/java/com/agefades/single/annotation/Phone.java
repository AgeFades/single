package com.agefades.single.annotation;

import com.agefades.single.constants.CommonConstant;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 手机号正则校验注解
 *
 * @author DuChao
 * @date 2021/1/12 5:01 下午
 */
@Retention(RUNTIME)
@Target({ FIELD, PARAMETER })
@Pattern(regexp = CommonConstant.PHONE_PATTEN, message = "手机号格式校验失败")
public @interface Phone {

    String message() default "手机号格式校验失败";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
