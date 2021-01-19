package com.hongseng.app.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongseng.app.mapper.RoleMapper;
import com.hongseng.app.mapper.UserRoleMapper;
import enums.ErrorCodeEnum;
import model.SysRole;
import model.UserRole;
import model.vo.UAndDAndIUserRoleVo;
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
public class UserRoleService {

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
    public Result listUserRole(Integer userId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>();
        sysRoleQueryWrapper.eq("user_id",userId);
        List<SysRole> sysRoles = roleMapper.selectList(sysRoleQueryWrapper);
        PageInfo<SysRole> sysRolePageInfo = new PageInfo<>(sysRoles);
        return Result.success(sysRolePageInfo);
    }

    /**
     * @return Result
     * @Author fbl
     * @Description 修改用户角色信息
     * @Date 11:55 2021/1/18
     * @Param UpdateAndDeleteUserRoleDto
     */
    public Result updateUserRole(UAndDAndIUserRoleVo userRoleVo) {
        // 删除用户下所有的角色
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.eq("user_id", userRoleVo.getUserId());
        userRoleMapper.delete(userRoleQueryWrapper);
        insertRoles(userRoleVo);
        return Result.success();
    }

    /**
     * @return Result
     * @Author fbl
     * @Description 删除用户角色信息
     * @Date 11:55 2021/1/18
     * @Param UpdateAndDeleteUserRoleDto
     */
    public Result deleteUserRole(UAndDAndIUserRoleVo userRoleVo) {
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.eq("user_id", userRoleVo.getUserId());
        userRoleQueryWrapper.in("role_id", userRoleVo.getRoleId());
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
    public Result insertUserRole(UAndDAndIUserRoleVo userRoleVo) {
        insertRoles(userRoleVo);
        return Result.success();
    }

    private void insertRoles(UAndDAndIUserRoleVo userRoleVo) {
        List<Integer> roleId = userRoleVo.getRoleId();
        UserRole userRole = new UserRole();
        for (Integer id : roleId) {
            userRole.setUserId(userRoleVo.getUserId());
            userRole.setRoleId(id);
            userRoleMapper.insert(userRole);
        }
    }
}
