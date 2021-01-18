package com.hongseng.app.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hongseng.app.mapper.RoleMapper;
import com.hongseng.app.mapper.UserRoleMapper;
import enums.ErrorCodeEnum;
import model.SysRole;
import model.UserRole;
import model.dto.UAndDAndIUserRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.CommonConstants;
import result.Result;

import java.util.List;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-01-18 10:43
 **/
@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    /**
     * @return Result
     * @Author fbl
     * @Description 查询用户角色信息
     * @Date 11:55 2021/1/18
     * @Param
     */
    public Result listRole() {
        Page<SysRole> page = new Page<>();
        IPage<SysRole> sysRolePage = roleMapper.selectPage(page, new QueryWrapper<SysRole>());
        return Result.success(sysRolePage);
    }

    /**
     * @return Result
     * @Author fbl
     * @Description 修改用户角色信息
     * @Date 11:55 2021/1/18
     * @Param UpdateAndDeleteUserRoleDto
     */
    public Result updateRole(UAndDAndIUserRoleDto userRoleDto) {
        // 删除用户下所有的角色
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.eq("user_id", userRoleDto.getUserId());
        userRoleMapper.delete(userRoleQueryWrapper);
        insertRoles(userRoleDto);
        return Result.success();
    }

    /**
     * @return Result
     * @Author fbl
     * @Description 删除用户角色信息
     * @Date 11:55 2021/1/18
     * @Param UpdateAndDeleteUserRoleDto
     */
    public Result deleteRole(UAndDAndIUserRoleDto userRoleDto) {
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.eq("user_id", userRoleDto.getUserId());
        userRoleQueryWrapper.in("role_id", userRoleDto.getRoleId());
        int delete = userRoleMapper.delete(userRoleQueryWrapper);
        if (delete == CommonConstants.DeleteCodeStatus.IS_NOT_DELETE) {
            return Result.failure(ErrorCodeEnum.SYS_ERR_DELETE_FAILED);
        }
        return Result.success();
    }

    /**
     * @return Result
     * @Author fbl
     * @Description 新增用户角色信息
     * @Date 11:55 2021/1/18
     * @Param UpdateAndDeleteUserRoleDto
     */
    public Result insertRole(UAndDAndIUserRoleDto userRoleDto) {
        insertRoles(userRoleDto);
        return Result.success();
    }

    private void insertRoles(UAndDAndIUserRoleDto userRoleDto) {
        List<Integer> roleId = userRoleDto.getRoleId();
        UserRole userRole = new UserRole();
        for (Integer id : roleId) {
            userRole.setUserId(userRoleDto.getUserId());
            userRole.setRoleId(id);
            userRoleMapper.insert(userRole);
        }
    }
}
