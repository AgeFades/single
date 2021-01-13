package com.agefades.single.admin.constants;

import java.util.Arrays;
import java.util.List;

/**
 * 忽略鉴权常量、这里只是最简单的一种实现思路
 *      扩展方向: 动态配置、字典码表、HttpMethod针对配置 ...
 *
 * @author DuChao
 * @date 2021/1/13 4:28 下午
 */
public interface IgnoreAuthConstant {

    /**
     * 忽略 { 认证 & 鉴权 } 请求路径集合
     */
    List<String> IGNORE_PATTERN = Arrays.asList("/auth/login", "/test/ok");

    /**
     * 忽略 { 鉴权 } 请求路径集合
     */
    List<String> ONLY_LOGIN = Arrays.asList("/sys/log/condition/page");

}
