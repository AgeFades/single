package com.agefades.single.biz.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统用户
 *
 * @author DuChao
 * @date 2021/1/13 11:20 上午
 */
@Data
@TableName(value = "sys_user")
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private String id;
    /**
     * 用户名，唯一
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号，唯一
     */
    private String phone;
    /**
     * 状态  0未启用 1 启用
     */
    private Integer isEnable;
    /**
     * 备注
     */
    private String comment;
    /**
     * 删除标记 0未删除 1删除
     */
    private Integer deleted;
    /**
     * 是否超级管理员 1是 0否
     */
    private Integer isAdmin;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

}
