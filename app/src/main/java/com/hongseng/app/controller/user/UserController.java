package com.hongseng.app.controller.user;

import com.hongseng.app.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.dto.InsertUserDto;
import model.dto.UpdateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import result.Result;
import utils.SearchFilter;


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

    @PostMapping("/list-user")
    @PreAuthorize("hasAnyAuthority('0038')")
    @ApiOperation(value = "展示所有用户信息")
    public Result listUser(@RequestBody @Validated SearchFilter searchFilter) {
        return userService.listUser(searchFilter);
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAnyAuthority('0138')")
    @ApiOperation(value = "展示用户详情")
    public Result userDetail(@PathVariable Integer id) {
        return userService.userDetail(id);
    }

    @PostMapping("/user")
    @PreAuthorize("hasAnyAuthority('0038')")
    @ApiOperation(value = "注册新用户")
    public Result insertUser(@RequestBody @Validated InsertUserDto insertUserDto) {
        return userService.insertUser(insertUserDto);
    }

    @PutMapping("/user")
    @PreAuthorize("hasAnyAuthority('0038')")
    @ApiOperation(value = "修改用户信息")
    public Result updateUser(@RequestBody @Validated UpdateUserDto updateUserDto) {
        return userService.updateUser(updateUserDto);
    }

}
