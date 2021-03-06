package com.agefades.single.common.base;

import com.agefades.single.common.enums.CodeEnum;
import com.agefades.single.common.enums.CommonResultCodeEnum;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Web统一返回对象
 *
 * @author DuChao
 * @date 2021/1/12 2:42 下午
 */
@Data
@NoArgsConstructor
@ApiModel(description = "响应信息主体")
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;
    private String message;
    private String traceId;
    private T data;

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(String code, String message, String traceId) {
        this.code = code;
        this.message = message;
        this.traceId = traceId;
    }

    public Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return new Result<>(CommonResultCodeEnum.SUCCESS.getCode(), CommonResultCodeEnum.SUCCESS.getMsg());
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(CommonResultCodeEnum.SUCCESS.getCode(), CommonResultCodeEnum.SUCCESS.getMsg(), data);
    }

    public static <T> Result<T> error(String code, String msg) {
        return new Result<>(code, msg);
    }

    public static <T> Result<T> error(CodeEnum codeEnum) {
        return new Result<>(codeEnum.getCode(), codeEnum.getMsg());
    }

    public static <T> Result<T> error(CodeEnum codeEnum, String msg) {
        return new Result<>(codeEnum.getCode(), msg);
    }

    public static <T> Result<T> errorWithTraceId(CodeEnum bizCodeEnum, String traceId) {
        return new Result<>(bizCodeEnum.getCode(), bizCodeEnum.getMsg(), traceId);
    }
}
