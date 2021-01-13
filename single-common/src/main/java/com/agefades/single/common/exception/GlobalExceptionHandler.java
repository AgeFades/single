package com.agefades.single.common.exception;

import com.agefades.single.common.base.Result;
import com.agefades.single.common.enums.CommonResultCodeEnum;
import com.agefades.single.common.util.LogUtil;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.Map;

/**
 * 全局异常统一处理
 *
 * @author DuChao
 * @date 2021/1/12 2:53 下午
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Throwable.class)
    public Result<Void> handleThrowable(Throwable ex) {
        if (ex instanceof BizException) {
            BizException e = (BizException) ex;
            return Result.error(e.getCode(), e.getMessage());
        } else if (ex instanceof BindException) {
            return handleValidException(((BindException) ex).getBindingResult());
        } else if (ex instanceof MethodArgumentNotValidException) {
            return handleValidException(((MethodArgumentNotValidException) ex).getBindingResult());
        } else if (ex instanceof MismatchedInputException) {
            log.warn("MismatchedInputException:", ex);
            return Result.error(CommonResultCodeEnum.INVALID_JSON_ERROR);
        } else if (ex instanceof HttpMessageNotReadableException) {
            log.warn("请求参数JSON异常: ", ex);
            return Result.error(CommonResultCodeEnum.INVALID_JSON_ERROR);
        } else if (ex instanceof HttpMediaTypeNotSupportedException) {
            log.warn("不支持的请求格式: ", ex);
            return Result.error(CommonResultCodeEnum.NOT_SUPPORTED_HTTP_MEDIA_TYPE);
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            log.warn("请求类型不匹配异常: ", ex);
            return Result.error(CommonResultCodeEnum.HTTP_REQUEST_METHOD_NOT_SUPPORTED);
        } else if (ex instanceof MaxUploadSizeExceededException) {
            log.warn("文件上传异常: ", ex);
            return Result.error(CommonResultCodeEnum.MAX_UPLOAD_SIZE_ERROR);
        } else if (ex instanceof MissingServletRequestParameterException) {
            log.warn("缺少必要的参数异常: ", ex);
            MissingServletRequestParameterException e = (MissingServletRequestParameterException) ex;
            return Result.error(CommonResultCodeEnum.MISSING_REQUIRE_PARAMETER, "缺少参数: " + e.getParameterName() + ",类型: " + e.getParameterType());
        } else if (ex instanceof MissingServletRequestPartException) {
            log.warn("缺少必要MultipartFile的参数异常: ", ex);
            MissingServletRequestPartException e = (MissingServletRequestPartException) ex;
            return Result.error(CommonResultCodeEnum.MISSING_REQUIRE_PARAMETER, "缺少文件类型参数: " + e.getRequestPartName());
        } else if (ex instanceof MethodArgumentTypeMismatchException) {
            log.warn("参数类型不匹配异常: ", ex);
            MethodArgumentTypeMismatchException e = (MethodArgumentTypeMismatchException) ex;
            return Result.error(CommonResultCodeEnum.METHOD_ARGUMENT_TYPE_MISMATCH, "参数: " + e.getName() + "类型不匹配,需要 " + e.getRequiredType() + " 类型");
        } else if (ex instanceof IllegalArgumentException) {
            log.warn("非法参数异常: ", ex);
            return Result.error(CommonResultCodeEnum.ILLEGAL_ARGUMENT, "非法的参数: " + ex.getMessage());
        }

        String traceId = LogUtil.getTraceId();
        log.error("系统异常！traceId:{}", traceId, ex);
        return Result.errorWithTraceId(CommonResultCodeEnum.SYSTEM_ERROR, traceId);
    }

    /**
     * JSR-303 数据参数校验异常处理
     */
    private Result<Void> handleValidException(BindingResult bindingResult) {
        Map<String, String> errorMap = Maps.newHashMap();
        bindingResult.getFieldErrors()
                .forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        return Result.error(CommonResultCodeEnum.VALID_ERROR, errorMap.toString());
    }

}
