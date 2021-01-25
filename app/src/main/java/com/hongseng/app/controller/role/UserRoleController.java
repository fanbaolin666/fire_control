package com.hongseng.app.controller.role;

import com.hongseng.app.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.dto.UAndDAndIUserRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import result.Result;

import javax.validation.constraints.NotNull;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-01-18 10:37
 **/
@RestController
@Validated
@Api(value = "UserRoleController", tags = "用户角色管理")
public class UserRoleController {

    @Autowired
    UserRoleService userRoleService;

    @GetMapping("/user-role")
    @PreAuthorize("hasAnyAuthority('0001')")
    @ApiOperation(value = "展示用户角色信息")
    public Result listUserRole(@NotNull Integer userId,@NotNull Integer pageSize,@NotNull  Integer pageNum) {
        return userRoleService.listUserRole(userId,pageSize,pageNum);
    }


    @PostMapping("/user-role")
    @PreAuthorize("hasAnyAuthority('0001')")
    @ApiOperation(value = "修改用户角色信息")
    public Result updateUserRole(@Validated @RequestBody UAndDAndIUserRoleDto userRoleDto) {
        return userRoleService.updateUserRole(userRoleDto);
    }

    @DeleteMapping("/user-role")
    @PreAuthorize("hasAnyAuthority('0001')")
    @ApiOperation(value = "删除用户角色信息")
    public Result deleteUserRole(@Validated @RequestBody UAndDAndIUserRoleDto userRoleDto) {
        return userRoleService.deleteUserRole(userRoleDto);
    }

    @PutMapping("/user-role")
    @PreAuthorize("hasAnyAuthority('0001')")
    @ApiOperation(value = "新增用户角色信息")
    public Result insertUserRole(@Validated @RequestBody UAndDAndIUserRoleDto userRoleDto) {
        return userRoleService.insertUserRole(userRoleDto);
    }
}
