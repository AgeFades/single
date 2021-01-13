package com.agefades.single.common.constants;

import java.util.Arrays;
import java.util.List;

/**
 * 公共常量类
 *
 * @author DuChao
 * @date 2021/1/12 4:52 下午
 */
public interface CommonConstant {

    /**
     * 默认排序字段
     */
    String DEFAULT_SORT_FIELD = "create_time";

    /**
     * 手机号校验正则
     */
    String PHONE_PATTEN = "1\\d{10}";

    /**
     * 成功描述
     */
    String SUCCESS = "SUCCESS";

    /**
     * 异常描述
     */
    String ERROR = "ERROR";

    /**
     * 不需要登录、鉴权即可访问的路径
     * 此处基本配置 各个整合框架 路径
     */
    List<String> IGNORE_PATH = Arrays.asList(
            ".html",
            ".css",
            ".js",
            "csrf",
            "api-docs",
            "swagger",
            "webjars",
            "error",
            "actuator",
            "null",
            "druid"
    );

}
