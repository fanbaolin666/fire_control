package com.hongseng.app.controller.user;

import com.hongseng.app.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import result.Result;

import javax.validation.constraints.NotNull;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-01-29 14:52
 **/
@RestController
@Validated
@Api(value = "UserRoleController", tags = "用户管理")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('0038')")
    @ApiOperation(value = "展示所有用户信息")
    public Result listUser(@NotNull Integer pageSize, @NotNull  Integer pageNum) {
        return userService.listUser(pageSize,pageNum);
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAnyAuthority('0038')")
    @ApiOperation(value = "展示用户详情")
    public Result userDetail(@PathVariable Integer id) {
        return userService.userDetail(id);
    }

}
