package com.agefades.single.admin.biz.sys.controller;

import com.agefades.single.admin.annotation.SysLog;
import com.agefades.single.admin.biz.sys.req.SysUserAddReq;
import com.agefades.single.admin.biz.sys.req.SysUserQueryReq;
import com.agefades.single.admin.biz.sys.resp.SysUserQueryResp;
import com.agefades.single.admin.biz.sys.service.SysUserService;
import com.agefades.single.common.annotation.RateLimiter;
import com.agefades.single.common.base.Result;
import com.agefades.single.common.util.PageUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/sys/user")
@Api(tags = "账户管理API")
@AllArgsConstructor
public class  SysUserController {

    private final SysUserService sysUserService;

    @PostMapping("list")
    @ApiOperation(value = "条件分页查询")
    public Result<PageUtil<SysUserQueryResp>> list(@RequestBody SysUserQueryReq req) {
        Page<SysUserQueryResp> page = sysUserService.conditionPage(req);
        return Result.success(new PageUtil<>(page));
    }

    @RateLimiter
    @PostMapping("add")
    @SysLog(desc = "新增系统用户")
    @ApiOperation(value = "新增系统用户")
    public Result<Void> add(@RequestBody @Valid SysUserAddReq req) {
        sysUserService.add(req);
        return Result.success();
    }

}
