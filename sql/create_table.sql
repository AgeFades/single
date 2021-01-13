-- 创建数据库
CREATE DATABASE `single` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT ENCRYPTION='N';

DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '操作用户',
  `result_type` varchar(10) NOT NULL DEFAULT '' COMMENT '操作结果类型',
  `class_name` varchar(64) NOT NULL DEFAULT '' COMMENT '操作资源类名',
  `method_name` varchar(64) NOT NULL DEFAULT '' COMMENT '操作资源方法名',
  `method_type` varchar(10) NOT NULL DEFAULT '' COMMENT '操作资源请求类型',
  `log_desc` varchar(32) NOT NULL DEFAULT '' COMMENT '操作日志描述',
  `elapsed_time` varchar(10) NOT NULL DEFAULT '' COMMENT '消耗时间，单位毫秒',
  `browser` varchar(128) NOT NULL DEFAULT '' COMMENT '操作请求浏览器 + 版本',
  `os` varchar(32) NOT NULL DEFAULT '' COMMENT '操作请求的系统名',
  `platform` varchar(32) NOT NULL DEFAULT '' COMMENT '操作请求的平台名',
  `ip` varchar(32) NOT NULL DEFAULT '' COMMENT '操作请求的ip',
  `params` text NULL COMMENT '操作请求的参数',
  `error_msg` varchar(255) NOT NULL DEFAULT '' COMMENT '异常Msg',
  `trace_id` varchar(32) NOT NULL DEFAULT '' COMMENT '异常日志traceId',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='系统操作日志';

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `username` varchar(30) NOT NULL DEFAULT '' COMMENT '用户名，唯一',
  `password` varchar(128) NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '姓名',
  `phone` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号，唯一',
  `is_enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态  0未启用 1 启用',
  `comment` varchar(300) NOT NULL DEFAULT '' COMMENT '备注',
  `is_admin` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否超级管理员 1是 0否',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 0未删除 1删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk-username` (`username`) USING BTREE,
  KEY `idx-phone` (`phone`) USING BTREE
) ENGINE=InnoDB COMMENT='系统用户表';

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='角色表';

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `role_id` varchar(20) NOT NULL COMMENT '角色id',
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk-user_role` (`user_id`,`role_id`) COMMENT '联合唯一索引: 用户id + 角色id'
) ENGINE=InnoDB COMMENT='用户角色关系表';

DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `pid` varchar(20) NOT NULL DEFAULT '0' COMMENT '父菜单ID',
  `weight` tinyint(4) DEFAULT '0' COMMENT '显示权重',
  `uri` varchar(200) DEFAULT '' COMMENT '请求路径',
  `type` varchar(1) DEFAULT '' COMMENT '菜单类型（D目录 M菜单 B按钮）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='菜单权限表';

DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `role_id` varchar(20) NOT NULL COMMENT '角色id',
  `menu_id` varchar(20) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk-role_menu` (`role_id`,`menu_id`) COMMENT '联合唯一索引: 角色id + 权限id'
) ENGINE=InnoDB COMMENT='角色菜单关系表';