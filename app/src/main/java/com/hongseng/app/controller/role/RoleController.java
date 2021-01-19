package com.hongseng.app.controller.role;

import com.hongseng.app.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.vo.InsertRoleVo;
import model.vo.UpdateRoleVo;
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
 * @create: 2021-01-19 10:23
 **/
@RestController
@Validated
@Api(value = "UserRoleController", tags = "角色管理")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/role")
    @PreAuthorize("hasAnyAuthority('0001')")
    @ApiOperation(value = "展示角色信息")
    public Result roleList(@NotNull Integer pageSize, @NotNull Integer pageNum) {
        return roleService.roleList(pageSize, pageNum);
    }


    @PostMapping("/role")
    @PreAuthorize("hasAnyAuthority('0001')")
    @ApiOperation(value = "新增角色信息")
    public Result insertRole(@RequestBody @Validated InsertRoleVo roleVo) {
        return roleService.insertRole(roleVo);
    }

    @PutMapping("/role")
    @PreAuthorize("hasAnyAuthority('0001')")
    @ApiOperation(value = "修改角色信息")
    public Result updateRole(@RequestBody UpdateRoleVo roleVo) {
        return roleService.updateRole(roleVo);
    }


    @DeleteMapping("/role/{id}")
    @PreAuthorize("hasAnyAuthority('0001')")
    @ApiOperation(value = "删除角色信息")
    public Result deleteRole(@PathVariable Integer id) {
        return roleService.deleteRole(id);
    }
}
