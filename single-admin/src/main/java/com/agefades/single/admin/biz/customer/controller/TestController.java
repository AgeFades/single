package com.agefades.single.admin.biz.customer.controller;

import com.agefades.single.admin.annotation.SysLog;
import com.agefades.single.common.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试API")
@RestController
@RequestMapping
public class TestController {

    @SysLog(desc = "操作日志测试 - 正常")
    @GetMapping
    @ApiOperation(value = "测试 - 正常")
    public Result<Void> testOk() {
        return Result.success();
    }

    @SysLog(desc = "操作日志测试 - 异常")
    @PostMapping
    @ApiOperation(value = "测试 - 异常")
    public Result<Void> testError() {
        int a = 1 / 0;
        return Result.success();
    }

}
