package com.hongseng.app.controller;

import annotation.AnonymousAccess;
import com.hongseng.app.service.LoginService;
import io.swagger.annotations.Api;
import model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
    public Result login(@RequestBody @Validated UserDto user){
        return loginService.login(user);
    }

    @GetMapping("/preAuthorize")
    @PreAuthorize("hasAnyAuthority('0001')")
    public String test(){
        return "PreAuthorize is ok";
    }
}
