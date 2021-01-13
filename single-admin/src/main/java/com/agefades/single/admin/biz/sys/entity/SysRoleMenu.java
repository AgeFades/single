package com.agefades.single.admin.biz.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色 - 权限 关联表
 *
 * @author DuChao
 * @date 2021/1/13 11:34 上午
 */
@Data
@TableName("sys_role_menu")
public class SysRoleMenu implements Serializable {
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
     * 菜单id
     */
    private String menuId;

}
