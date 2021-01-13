-- 创建数据库
CREATE DATABASE `single` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT ENCRYPTION='N';

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统操作日志';