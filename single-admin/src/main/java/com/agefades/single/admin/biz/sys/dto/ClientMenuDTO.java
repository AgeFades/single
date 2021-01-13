package com.agefades.single.admin.biz.sys.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "用户权限Vo")
public class ClientMenuDTO {

    @JsonIgnore
    private String id;

    @ApiModelProperty("权限名称")
    private String name;

    @JsonIgnore
    private String pid;

    @JsonIgnore
    private Integer weight;

    @JsonIgnore
    private String uri;

    @ApiModelProperty("菜单类型（D目录 M菜单 B按钮）")
    private String type;

    @ApiModelProperty("权限标识")
    private String perms;

    @ApiModelProperty("子节点")
    private List<ClientMenuDTO> child;

}
