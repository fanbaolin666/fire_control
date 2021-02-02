package com.hongseng.app.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongseng.app.mapper.DepartmentMapper;
import com.hongseng.app.mapper.UserMapper;
import com.hongseng.app.mapper.UserRoleMapper;
import enums.ErrorCodeEnum;
import model.Department;
import model.SysUser;
import model.UserRole;
import model.dto.InsertUserDto;
import model.dto.InsertUserRoleDto;
import model.dto.UAndDAndIUserRoleDto;
import model.dto.UpdateUserDto;
import model.vo.SelectUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import result.CommonConstants;
import result.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-01-29 14:55
 **/
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    public Result listUser(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        // 查询所有用户信息
        List<SelectUserVo> selectUserVos = userMapper.listUser(null);
        for (SelectUserVo selectUserVo : selectUserVos) {
            List<String> roleNames = userRoleMapper.selectRoleName(selectUserVo.getId());
            selectUserVo.setRoleName(roleNames);
        }
        PageInfo<SelectUserVo> selectUserVoPageInfo = new PageInfo<>(selectUserVos);
        return Result.success(selectUserVoPageInfo);
    }

    public Result userDetail(Integer id) {
        SelectUserVo userVo = new SelectUserVo();
        List<SelectUserVo> selectUserVos = userMapper.listUser(id);
        BeanUtils.copyProperties(selectUserVos.get(0), userVo);
        List<String> roleNames = userRoleMapper.selectRoleName(userVo.getId());
        userVo.setRoleName(roleNames);
        return Result.success(userVo);
    }

    @Transactional(rollbackFor = Exception.class)
    public Result insertUser(InsertUserDto insertUserDto) {
        if (isUniqueUserName(insertUserDto.getUserName())) {
            return Result.failure(ErrorCodeEnum.SYS_ERR_ACCOUNT);
        }
        SysUser sysUser = new SysUser();
        // 密码加密
        String encodePassword = passwordEncoder.encode(insertUserDto.getPassword());
        BeanUtils.copyProperties(insertUserDto, sysUser);
        sysUser.setPassword(encodePassword);
        // 插入用户
        int res = userMapper.insert(sysUser);

        if (CollectionUtil.isNotEmpty(insertUserDto.getRoleId())) {
            // 插入用户角色
            UAndDAndIUserRoleDto uAndDAndIUserRoleDto = new UAndDAndIUserRoleDto();
            uAndDAndIUserRoleDto.setUserId(sysUser.getId());
            uAndDAndIUserRoleDto.setRoleId(insertUserDto.getRoleId());
            insertRoles(uAndDAndIUserRoleDto);
        }
        if (res == CommonConstants.DeleteCodeStatus.IS_NOT_DELETE) {
            return Result.failure(ErrorCodeEnum.SYS_ERR_CREATE_FAILED);
        }
        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public Result updateUser(UpdateUserDto updateUserDto) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(updateUserDto, sysUser);
        if (Objects.nonNull(updateUserDto.getPassword())) {
            sysUser.setPassword(passwordEncoder.encode(updateUserDto.getPassword()));
        }
        // 修改用户基本信息
        int res = userMapper.updateById(sysUser);
        // 修改用户角色
        if (CollectionUtil.isNotEmpty(updateUserDto.getRoleId())) {
            // 删除用户下所有的角色
            QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
            userRoleQueryWrapper.eq("user_id", updateUserDto.getId());
            userRoleMapper.delete(userRoleQueryWrapper);
            // 插入角色
            UAndDAndIUserRoleDto uAndDAndIUserRoleDto = new UAndDAndIUserRoleDto();
            uAndDAndIUserRoleDto.setUserId(sysUser.getId());
            uAndDAndIUserRoleDto.setRoleId(updateUserDto.getRoleId());
            insertRoles(uAndDAndIUserRoleDto);
        }
        if (res == CommonConstants.DeleteCodeStatus.IS_NOT_DELETE) {
            return Result.failure(ErrorCodeEnum.SYS_ERR_UPDATE_FAILED);
        }
        return Result.success();
    }

    private boolean isUniqueUserName(String userName) {
        QueryWrapper<SysUser> userNameQuery = new QueryWrapper<SysUser>().eq("user_name", userName);
        List<SysUser> sysUsers = userMapper.selectList(userNameQuery);
        return CollectionUtil.isNotEmpty(sysUsers);
    }

    private Integer insertRoles(UAndDAndIUserRoleDto userRoleDto) {
        ArrayList<InsertUserRoleDto> insertUserRoleDtos = new ArrayList<>(userRoleDto.getRoleId().size());

        InsertUserRoleDto insertUserRoleDto = null;
        for (Integer roleId : userRoleDto.getRoleId()) {
            insertUserRoleDto = new InsertUserRoleDto();
            insertUserRoleDto.setRoleId(roleId);
            insertUserRoleDto.setUserId(userRoleDto.getUserId());
            insertUserRoleDtos.add(insertUserRoleDto);
        }
        return userRoleMapper.insertUserRole(insertUserRoleDtos);
    }
}
