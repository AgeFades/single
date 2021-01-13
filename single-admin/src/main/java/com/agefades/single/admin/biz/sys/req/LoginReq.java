package com.agefades.single.admin.biz.sys.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录请求Vo
 *
 * @author DuChao
 * @date 2020/9/2 1:52 下午
 */
@Data
@ApiModel(value = "用户登录请求对象")
public class LoginReq {

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty("记住登录, true: token3天过期，false: token1天过期（默认）")
    private Boolean rememberMe;

}
