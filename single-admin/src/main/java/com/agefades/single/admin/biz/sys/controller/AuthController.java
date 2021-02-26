package com.agefades.single.admin.biz.sys.controller;

import com.agefades.single.admin.biz.sys.domain.LoginDomain;
import com.agefades.single.admin.biz.sys.req.LoginReq;
import com.agefades.single.admin.biz.sys.resp.LoginResp;
import com.agefades.single.common.annotation.RateLimiter;
import com.agefades.single.common.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Api(tags = "认证API")
@AllArgsConstructor
public class AuthController {

    private final LoginDomain loginDomain;

    @RateLimiter
    @ApiOperation(value = "登录(账号只能在一个地方登录)")
    @PostMapping("/login")
    public Result<LoginResp> login(@Valid @RequestBody LoginReq req) {
        LoginResp resp = loginDomain.login(req);
        return Result.success(resp);
    }

    @ApiOperation(value = "退出登录")
    @GetMapping("/logout")
    public Result<Void> logout(HttpServletRequest req) {
        loginDomain.logout(req);
        return Result.success();
    }

}
