package com.hongseng.app.controller.role;

import com.hongseng.app.service.RoleService;
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
@Api(value = "RoleController", tags = "角色管理")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/role")
    @PreAuthorize("hasAnyAuthority('0001')")
    @ApiOperation(value = "展示角色信息")
    public Result listRole() {
        return roleService.listRole();
    }


    @PostMapping("/role")
    @PreAuthorize("hasAnyAuthority('0001')")
    @ApiOperation(value = "修改角色信息")
    public Result updateRole(@NotNull UAndDAndIUserRoleDto userRoleDto) {
        return roleService.updateRole(userRoleDto);
    }

    @DeleteMapping("/role")
    @PreAuthorize("hasAnyAuthority('0001')")
    @ApiOperation(value = "删除角色信息")
    public Result deleteRole(@NotNull UAndDAndIUserRoleDto userRoleDto) {
        return roleService.deleteRole(userRoleDto);
    }

    @PutMapping("/role")
    @PreAuthorize("hasAnyAuthority('0001')")
    @ApiOperation(value = "新增角色信息")
    public Result insertRole(@NotNull UAndDAndIUserRoleDto userRoleDto) {
        return roleService.insertRole(userRoleDto);
    }
}
