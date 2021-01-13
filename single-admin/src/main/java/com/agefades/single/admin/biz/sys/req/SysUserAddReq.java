package com.agefades.single.admin.biz.sys.req;

import com.agefades.single.common.annotation.Phone;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ApiModel("新增系统用户Vo")
public class SysUserAddReq {

    @Pattern(regexp = "([A-Za-z0-9]{3,15}||[A-Za-z]{3,15})",message = "用户名格式不正确")
    @ApiModelProperty("用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 6,max = 16,message = "密码长度限制在6-16个字符之间")
    @ApiModelProperty("密码")
    private String password;

    @NotBlank(message = "姓名不能为空")
    @Length(max = 15,message = "姓名不能超过15个字符")
    @ApiModelProperty("姓名")
    private String name;

    @Phone
    @NotBlank(message = "手机号不能为空")
    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("备注")
    private String comment;

    @ApiModelProperty("是否超级管理员 1是 0否, 默认否")
    private Integer isAdmin;

}
