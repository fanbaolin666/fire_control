package com.hongseng.app.controller.permission;

import com.hongseng.app.service.RolePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.dto.UpdatePermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import result.Result;


/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-01-22 15:19
 **/
@RestController
@Validated
@Api(value = "RolePermissionController", tags = "角色权限管理")
public class RolePermissionController {

    @Autowired
    RolePermissionService rolePermissionService;

    @GetMapping("/permission")
    @PreAuthorize("hasAnyAuthority('0001')")
    @ApiOperation(value = "分级展示所有权限信息")
    public Result permissionList() {
        return rolePermissionService.permissionList();
    }

    @PutMapping("/permission")
    @PreAuthorize("hasAnyAuthority('0001')")
    @ApiOperation(value = "设置角色权限信息")
    public Result updatePermission(@RequestBody @Validated UpdatePermissionDto updatePermissionDto) {
        return rolePermissionService.updatePermission(updatePermissionDto);
    }
}
