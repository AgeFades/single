package com.agefades.single.biz.sys.controller;

import com.agefades.single.base.Result;
import com.agefades.single.biz.sys.entity.SysLog;
import com.agefades.single.biz.sys.req.SysLogQueryReq;
import com.agefades.single.biz.sys.service.SysLogService;
import com.agefades.single.util.PageUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志API
 *
 * @author DuChao
 * @date 2020/9/29 2:05 下午
 */
@Api(tags = "操作日志API")
@AllArgsConstructor
@RestController
@RequestMapping("/sys/log")
public class SysLogController {

    private final SysLogService sysLogService;

    @ApiOperation(value = "操作日志多条件分页查询")
    @PostMapping("condition/page")
    public Result<PageUtil<SysLog>> conditionPage(@RequestBody SysLogQueryReq req){
        Page<SysLog> page = sysLogService.conditionPage(req);
        return Result.success(new PageUtil<>(page));
    }

}

