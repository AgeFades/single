package com.agefades.single.common.enums;

/**
 * 枚举 code msg 类型接口
 * 
 * @author DuChao
 * @date 2020/7/7 2:00 下午
 */
public interface CodeEnum {

    /**
     * 获取code的方法，用于后续循环对比状态。
     * @return 枚举类型
     */
    String getCode();

    /**
     * 获取msg的方法，用于后续循环对比状态。
     * @return 枚举类型
     */
    String getMsg();

}
