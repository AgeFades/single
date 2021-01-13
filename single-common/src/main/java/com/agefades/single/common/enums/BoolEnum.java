package com.agefades.single.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 布尔类型枚举
 *
 * @author DuChao
 * @date 2021/1/12 4:37 下午
 */
@Getter
@AllArgsConstructor
public enum BoolEnum {

    /**
     * Boolean.TRUE
     */
    Y(1),

    /**
     * Boolean.FALSE
     */
    N(0)

    ;
    private final int code;

}
