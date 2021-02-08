package com.hongseng.app.controller;

import annotation.AnonymousAccess;
import com.hongseng.app.config.log.MyLog;
import com.hongseng.app.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import result.Result;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-01-12 07:52
 **/
@RestController
@Validated
@Api(value = "LoginController",tags = "登录管理")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    @AnonymousAccess
    @ApiOperation(value = "用户登录")
    @MyLog("用户登录")
    public Result login(@RequestBody @Validated UserDto user){
        return loginService.login(user);
    }
}
