package com.agefades.single.admin.biz.sys.resp;

import com.agefades.single.admin.biz.sys.dto.ClientMenuDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 登录响应Vo
 *
 * @author DuChao
 * @date 2020/9/2 3:27 下午
 */
@Data
@Builder
@ApiModel(description = "登录响应Vo")
public class LoginResp {

    @ApiModelProperty("授权凭证")
    private String token;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("是否超级管理员 1是 0否")
    private Integer isAdmin;

    @ApiModelProperty("权限树")
    private List<ClientMenuDTO> permissionTree;

}
