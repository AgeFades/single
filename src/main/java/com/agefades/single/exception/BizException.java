package com.agefades.single.exception;

import lombok.Getter;

/**
 * 业务异常
 *
 * @author DuChao
 * @date 2021/1/12 2:51 下午
 */
@Getter
public class BizException extends RuntimeException {

    /**
     * 异常Code
     */
    private final String code;

    /**
     * 异常Msg
     */
    private final String message;

    public BizException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
