package com.agefades.single.admin.biz.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜单权限表
 *
 * @author DuChao
 * @date 2021/1/13 11:32 上午
 */
@Data
@TableName("sys_menu")
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private String id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 父菜单ID
     */
    private Long pid;
    /**
     * 显示权重
     */
    private Integer weight;
    /**
     * 请求路径
     */
    private String uri;
    /**
     * 菜单类型（D目录 M菜单 B按钮）
     */
    private String type;
    /**
     * 权限标识
     */
    private String perms;
    /**
     * 资源请求类型 GET POST PUT DELETE
     */
    private String method;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
