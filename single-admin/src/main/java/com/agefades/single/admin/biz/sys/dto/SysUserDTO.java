package com.agefades.single.admin.biz.sys.dto;

import lombok.Data;

/**
 * 登录用户信息DTO
 *      用于请求认证后, 后台直接通过 请求线程ThreadLocal 获取当前用户信息
 *      例如: 可能需要扩展用户的部门信息
 *            private String deptId;
 *
 * @author DuChao
 * @date 2021/1/13 4:54 下午
 */
@Data
public class SysUserDTO {

    private String id;

    private String username;

    private Integer isAdmin;

}
