package com.agefades.single.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务响应Code、Msg枚举
 *
 * --- 公共异常 ---
 * C01 客户
 * C02 订单
 * ...
 * C99 其他
 *
 * --- C端PC ---
 * P01 客户
 * P02 订单
 * ...
 * P99 其他
 *
 * --- 管理端 ---
 * M01 客户
 * M02 订单
 * ...
 * M99 其他
 *
 * @author DuChao
 * @date 2021/1/12 2:44 下午
 */
@Getter
@AllArgsConstructor
public enum BizCodeEnum {

    // ------ 全局公共响应开始 ------
    SUCCESS("00000", "成功"),

    SYSTEM_ERROR("C0001", "系统异常,请联系管理员"),

    VALID_ERROR("C0002", "数据校验失败"),

    INVALID_JSON_ERROR("C0003", "请求JSON格式异常"),

    NOT_SUPPORTED_HTTP_MEDIA_TYPE("C0004", "不支持的HttpMediaType"),

    HTTP_REQUEST_METHOD_NOT_SUPPORTED("C0005", "不支持的请求类型"),

    MAX_UPLOAD_SIZE_ERROR("C0006", "文件上传大小超过限制，请压缩文件或者降低图片质量再上传"),

    MISSING_REQUIRE_PARAMETER("C0007", "缺少必要参数"),

    METHOD_ARGUMENT_TYPE_MISMATCH("C0008", "参数值与参数类型不匹配"),

    ILLEGAL_ARGUMENT("C0009", "非法参数异常"),

    REDIS_LIMIT_ERROR("C0010", "操作过于频繁，请稍后再试"),

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
