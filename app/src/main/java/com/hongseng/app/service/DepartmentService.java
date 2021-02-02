package com.hongseng.app.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hongseng.app.mapper.DepartmentMapper;
import com.hongseng.app.mapper.UserMapper;
import enums.ErrorCodeEnum;
import model.Department;
import model.SysUser;
import model.dto.DeleteDepartmentDto;
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
        if (isUniqueDepartmentName(insertDepartment.getDepartmentName())) {
            return Result.failure(ErrorCodeEnum.SYS_ERR_DEPARTMENT_REPETITION);
        }
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

    public Result deleteDepartment(DeleteDepartmentDto deleteDepartmentDto) {
        List<Integer> ids = deleteDepartmentDto.getIds();
        // 如果部门中有人员，不能删除
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        sysUserQueryWrapper.in("department_id", ids);
        List<SysUser> sysUsers = userMapper.selectList(sysUserQueryWrapper);
        if (CollectionUtil.isNotEmpty(sysUsers)) {
            return Result.failure(ErrorCodeEnum.SYS_ERR_DELETE_DEPARTMENT);
        }
        int res = departmentMapper.deleteBatchIds(ids);
        if (res == CommonConstants.DataBaseOperationStatus.IS_FAILURE) {
            return Result.failure(ErrorCodeEnum.SYS_ERR_DELETE_FAILED);
        }
        return Result.success();
    }


    private List<DepartmentVo> getDepartmentVo(List<Department> departments) {
        ArrayList<DepartmentVo> oneDepartmentVo = new ArrayList<>();
        // 一级部门
        List<Department> oneDepartment = departments.stream().filter(d -> d.getDepartmentGrade() == 1).collect(Collectors.toList());
        // 二级部门
        List<Department> twoDepartment = departments.stream().filter(d -> d.getDepartmentGrade() == 2).collect(Collectors.toList());
        // 三级部门
        List<Department> threeDepartment = departments.stream().filter(d -> d.getDepartmentGrade() == 3).collect(Collectors.toList());

        DepartmentVo departmentVo = null;
        for (Department one : oneDepartment) {
            departmentVo = new DepartmentVo();
            BeanUtils.copyProperties(one, departmentVo);
            oneDepartmentVo.add(departmentVo);
        }
        ArrayList<DepartmentVo> twoDepartments;
        for (DepartmentVo one : oneDepartmentVo) {
            twoDepartments = new ArrayList<>();
            for (Department two : twoDepartment) {
                if (one.getId().equals(two.getFatherId())) {
                    departmentVo = new DepartmentVo();
                    BeanUtils.copyProperties(two, departmentVo);
                    twoDepartments.add(departmentVo);
                    one.setDepartmentVos(twoDepartments);
                }
            }
            ArrayList<DepartmentVo> threeDepartments;
            for (DepartmentVo two : twoDepartments) {
                threeDepartments = new ArrayList<>();
                for (Department three : threeDepartment) {
                    if (two.getId().equals(three.getFatherId())) {
                        departmentVo = new DepartmentVo();
                        BeanUtils.copyProperties(three, departmentVo);
                        threeDepartments.add(departmentVo);
                        two.setDepartmentVos(threeDepartments);
                    }
                }
            }
        }
        return oneDepartmentVo;
    }

    private boolean isUniqueDepartmentName(String departmentName) {
        QueryWrapper<Department> departmentNameQuery = new QueryWrapper<Department>().eq("department_name", departmentName);
        List<Department> departments = departmentMapper.selectList(departmentNameQuery);
        return CollectionUtil.isNotEmpty(departments);
    }
}
