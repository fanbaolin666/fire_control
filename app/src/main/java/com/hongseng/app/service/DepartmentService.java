package com.hongseng.app.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hongseng.app.mapper.DepartmentMapper;
import com.hongseng.app.mapper.UserMapper;
import enums.ErrorCodeEnum;
import model.Department;
import model.SysUser;
import model.dto.InsertDepartmentDto;
import model.dto.UpdateDepartmentDto;
import model.vo.DepartmentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.CommonConstants;
import result.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-01-28 13:53
 **/
@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    UserMapper userMapper;


    public Result departmentList() {
        List<Department> departments = departmentMapper.selectList(new QueryWrapper<>());
        List<DepartmentVo> departmentVo = getDepartmentVo(departments);
        return Result.success(departmentVo);
    }

    public Result departmentDetail(Integer id) {
        return Result.success(departmentMapper.selectById(id));

    }

    public Result insertDepartment(InsertDepartmentDto insertDepartment) {
        Department department = new Department();
        BeanUtils.copyProperties(insertDepartment, department);
        int res = departmentMapper.insert(department);
        if (res == CommonConstants.DataBaseOperationStatus.IS_FAILURE) {
            return Result.failure(ErrorCodeEnum.SYS_ERR_CREATE_FAILED);
        }
        return Result.success();
    }

    public Result updateDepartment(UpdateDepartmentDto updateDepartmentDto) {
        Department department = new Department();
        BeanUtils.copyProperties(updateDepartmentDto, department);
        int res = departmentMapper.updateById(department);
        if (res == CommonConstants.DataBaseOperationStatus.IS_FAILURE) {
            return Result.failure(ErrorCodeEnum.SYS_ERR_UPDATE_FAILED);
        }
        return Result.success();
    }

    public Result deleteDepartment(Integer id) {
        // 如果部门中有人员，不能删除
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        sysUserQueryWrapper.eq("department_id", id);
        List<SysUser> sysUsers = userMapper.selectList(sysUserQueryWrapper);
        if (CollectionUtil.isNotEmpty(sysUsers)) {
            return Result.failure(ErrorCodeEnum.SYS_ERR_DELETE_FAILED);
        }
        int res = departmentMapper.deleteById(id);
        if (res == CommonConstants.DataBaseOperationStatus.IS_FAILURE) {
            return Result.failure(ErrorCodeEnum.SYS_ERR_DELETE_FAILED);
        }
        return Result.success();
    }


    private List<DepartmentVo> getDepartmentVo(List<Department> departments) {

        ArrayList<DepartmentVo> oneDepartmentDto = new ArrayList<>();
        // 一级部门
        List<Department> oneDepartment = departments.stream().filter(d -> d.getDepartmentGrade() == 1).collect(Collectors.toList());
        DepartmentVo departmentVo = null;
        for (Department one : oneDepartment) {
            departmentVo = new DepartmentVo();
            BeanUtils.copyProperties(one, departmentVo);
            oneDepartmentDto.add(departmentVo);
        }

        // 二级部门
        List<Department> twoDepartment = departments.stream().filter(d -> d.getDepartmentGrade() == 2).collect(Collectors.toList());
        ArrayList<DepartmentVo> twoDepartments = new ArrayList<>();
        for (DepartmentVo one : oneDepartmentDto) {
            for (Department two : twoDepartment) {
                if (one.getId().equals(two.getFatherId())) {
                    departmentVo = new DepartmentVo();
                    BeanUtils.copyProperties(two, departmentVo);
                    twoDepartments.add(departmentVo);
                    one.setDepartmentVos(twoDepartments);
                }
            }
        }
        return oneDepartmentDto;
    }
}
