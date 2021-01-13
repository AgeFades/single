package com.agefades.single.admin.enums;

import com.agefades.single.common.enums.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 管理端业务响应Code、Msg枚举
 *
 * --- 管理端 ---
 * M01 用户
 * M02 订单
 * ...
 * M99 其他
 *
 * @author DuChao
 * @date 2021/1/12 2:44 下午
 */
@Getter
@AllArgsConstructor
public enum AdminResultCodeEnum implements CodeEnum {

    // ------ M01 用户 ------//
    USER_REPEAT_USERNAME("M0101", "用户名重复"),
    USER_REPEAT_PHONE("M0102", "手机号重复"),
    USER_NO_PERMISSION("M0103", "无访问权限"),
    USER_USERNAME_NOT_FOUND("M0104", "此账号不存在，请检查账号"),
    USER_IS_DISABLE("M0105", "此账号已被停用，请联系管理员"),
    USER_PWD_INVALID("M0106", "密码错误"),


    ;

    /**
     * 响应 Code
     */
    private final String code;

    /**
     * 响应 Msg
     */
    private final String msg;
}
