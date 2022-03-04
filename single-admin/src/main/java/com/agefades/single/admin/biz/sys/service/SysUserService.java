package com.agefades.single.admin.biz.sys.service;

import com.agefades.single.admin.biz.sys.dto.ClientMenuDTO;
import com.agefades.single.admin.biz.sys.resp.SysUserQueryResp;
import com.agefades.single.admin.biz.sys.entity.SysUser;
import com.agefades.single.admin.biz.sys.req.SysUserAddReq;
import com.agefades.single.admin.biz.sys.req.SysUserQueryReq;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SysUserService extends IService<SysUser> {

    /**
     * 分页条件查询
     */
    Page<SysUserQueryResp> conditionPage(SysUserQueryReq req);

    /**
     * 新增系统用户
     */
    void add(SysUserAddReq req);

    /**
     * 获取用户权限列表
     */
    List<ClientMenuDTO> getPermission(String userId);
}

