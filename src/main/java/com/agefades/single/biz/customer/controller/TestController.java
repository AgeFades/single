package com.agefades.single.biz.customer.controller;

import com.agefades.single.base.Result;
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

    @GetMapping
    @ApiOperation(value = "测试 - 正常")
    public Result<Void> testOk() {
        return Result.success();
    }

    @PostMapping
    @ApiOperation(value = "测试 - 异常")
    public Result<Void> testError() {
        int a = 1 / 0;
        return Result.success();
    }

}
