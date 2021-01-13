package com.agefades.single.admin.biz.sys.req;

import com.agefades.single.admin.biz.sys.entity.SysUser;
import com.agefades.single.common.base.PageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("系统用户分页条件查询Vo")
public class SysUserQueryReq extends PageReq<SysUser> {

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("状态: 0停用 1启用")
    private Integer isEnable;

}
