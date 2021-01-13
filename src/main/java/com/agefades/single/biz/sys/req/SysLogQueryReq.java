package com.agefades.single.biz.sys.req;

import com.agefades.single.base.PageReq;
import com.agefades.single.biz.sys.entity.SysLog;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 操作日志查询请求Vo
 *
 * @author DuChao
 * @date 2020/9/29 2:12 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "操作日志查询请求Vo")
public class SysLogQueryReq extends PageReq<SysLog> {

    @ApiModelProperty("操作用户")
    private String username;

    @ApiModelProperty("操作结果类型，成功SUCCESS, 异常ERROR")
    private String resultType;

    @ApiModelProperty("操作日志描述")
    private String logDesc;

    @ApiModelProperty("异常日志traceId")
    private String traceId;

    @ApiModelProperty("开始时间,yyyy-MM-dd hh:mm:ss")
    private LocalDateTime startTime;

    @ApiModelProperty("结束时间,yyyy-MM-dd hh:mm:ss")
    private LocalDateTime endTime;

}
