package com.hongseng.app.controller.department;

import com.hongseng.app.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.dto.DeleteDepartmentDto;
import model.dto.InsertDepartmentDto;
import model.dto.UpdateDepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import result.Result;


/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-01-28 13:52
 **/
@RestController
@Validated
@Api(value = "DepartmentController", tags = "部门管理")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/department")
    @PreAuthorize("hasAnyAuthority('0028')")
    @ApiOperation(value = "分级展示所有部门信息")
    public Result departmentList() {
        return departmentService.departmentList();
    }

    @GetMapping("/department/{id}")
    @PreAuthorize("hasAnyAuthority('0028')")
    @ApiOperation(value = "展示部门详情")
    public Result departmentDetail(@PathVariable Integer id) {
        return departmentService.departmentDetail(id);
    }

    @PostMapping("/department")
    @PreAuthorize("hasAnyAuthority('0028')")
    @ApiOperation(value = "增加部门信息")
    public Result insertDepartment(@RequestBody @Validated InsertDepartmentDto insertDepartmentDto) {
        return departmentService.insertDepartment(insertDepartmentDto);
    }

    @PutMapping("/department")
    @PreAuthorize("hasAnyAuthority('0028')")
    @ApiOperation(value = "修改部门信息")
    public Result updateDepartment(@RequestBody @Validated UpdateDepartmentDto updateDepartmentDto) {
        return departmentService.updateDepartment(updateDepartmentDto);
    }

    @PostMapping("/del-department")
    @PreAuthorize("hasAnyAuthority('0028')")
    @ApiOperation(value = "删除部门信息")
    public Result deleteDepartment(@RequestBody @Validated DeleteDepartmentDto deleteDepartmentDto) {
        return departmentService.deleteDepartment(deleteDepartmentDto);
    }

}
