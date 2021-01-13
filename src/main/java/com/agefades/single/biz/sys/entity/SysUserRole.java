package com.agefades.single.biz.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户 - 角色 关联表
 *
 * @author DuChao
 * @date 2021/1/13 11:31 上午
 */
@Data
@TableName("sys_user_role")
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private String id;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 用户id
     */
    private String userId;

}
