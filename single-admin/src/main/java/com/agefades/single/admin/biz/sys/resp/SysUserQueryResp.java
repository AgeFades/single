package com.agefades.single.admin.biz.sys.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("系统用户分页条件查询响应Vo")
public class SysUserQueryResp {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("状态  0未启用 1启用")
    private Integer isEnable;

    @ApiModelProperty("备注")
    private String comment;

    @ApiModelProperty("是否超级管理员 1是 0否")
    private Integer isAdmin;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

}
