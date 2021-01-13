package com.agefades.single.biz.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统操作日志
 *
 * @author DuChao
 * @date 2020-07-24 13:54:08
 */
@Data
@TableName("sys_log")
@ApiModel("操作日志")
public class SysLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private String id;
    /**
     * 操作用户
     */
    @ApiModelProperty("操作用户")
    private String username;
    /**
     * 操作结果类型
     */
    @ApiModelProperty("操作结果")
    private String resultType;
    /**
     * 操作资源类名
     */
    @ApiModelProperty("资源类")
    private String className;
    /**
     * 操作资源方法名
     */
    @ApiModelProperty("资源方法")
    private String methodName;
    /**
     * 操作资源请求类型
     */
    @ApiModelProperty("请求类型")
    private String methodType;
    /**
     * 操作日志描述
     */
    @ApiModelProperty("日志描述")
    private String logDesc;
    /**
     * 消耗时间，单位毫秒
     */
    @ApiModelProperty("耗时(毫秒)")
    private String elapsedTime;
    /**
     * 操作请求浏览器 + 版本
     */
    @ApiModelProperty("操作浏览器")
    private String browser;
    /**
     * 操作请求的系统名
     */
    @ApiModelProperty("操作系统")
    private String os;
    /**
     * 操作请求的平台名
     */
    @ApiModelProperty("操作平台")
    private String platform;
    /**
     * 操作请求的ip
     */
    @ApiModelProperty("请求IP")
    private String ip;
    /**
     * 操作请求的参数
     */
    @ApiModelProperty("请求参数")
    private String params;
    /**
     * 异常Msg
     */
    @ApiModelProperty("异常信息")
    private String errorMsg;
    /**
     * 异常日志traceId
     */
    @ApiModelProperty("traceId")
    private String traceId;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

}
