package com.hongseng.app.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hongseng.app.mapper.PermissionMapper;
import com.hongseng.app.mapper.RolePermissionMapper;
import com.hongseng.app.mapper.UserMapper;
import com.hongseng.app.mapper.UserRoleMapper;
import model.*;
import model.dto.UserDto;
import model.dto.UserRoleDto;
import model.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import enums.ErrorCodeEnum;
import result.Result;
import utils.JwtTokenUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-01-12 08:31
 **/
@Service
public class LoginService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    RolePermissionMapper rolePermissionMapper;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public Result login(UserDto user) {
        String userName = user.getUserName();
        String password = user.getPassword();
        // 用户名是否存在
        QueryWrapper<SysUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name", userName);
        SysUser userInfo = userMapper.selectOne(userQueryWrapper);
        if (null == userInfo) {
            return Result.failure(ErrorCodeEnum.SYS_ERR_LOGIN_FAIL);
        }

        // 用户存在，查找角色
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.eq("user_id", userInfo.getId());
        List<UserRole> userRoles = userRoleMapper.selectList(userRoleQueryWrapper);

        // 角色查找权限
        QueryWrapper<RolePermission> rolePermissionQueryWrapper = new QueryWrapper<>();
        rolePermissionQueryWrapper.in("role_id", userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList()));
        List<RolePermission> rolePermissions = rolePermissionMapper.selectList(rolePermissionQueryWrapper);

        QueryWrapper<SysPermission> permissionQueryWrapper = new QueryWrapper<>();
        permissionQueryWrapper.in("id",rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toList()));
        List<SysPermission> sysPermissions = permissionMapper.selectList(permissionQueryWrapper);
        UserRoleDto userRoleDto = new UserRoleDto();
        BeanUtils.copyProperties(userInfo,userRoleDto);
        userRoleDto.setCode(sysPermissions.stream().map(SysPermission::getCode).collect(Collectors.joining(",")));

        JwtUser jwtUser = new JwtUser(userRoleDto);


        boolean flag = passwordEncoder.matches(password, userInfo.getPassword());
        if (!flag) {
            return Result.failure(ErrorCodeEnum.SYS_ERR_LOGIN_FAIL);
        }
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();

        String strAuthorities = authorities.stream().map(a -> ((GrantedAuthority) a).getAuthority()).collect(Collectors.joining(","));
        String token = JwtTokenUtils.createToken(userName, strAuthorities, false);

        // 模型转换
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        userVo.setPassword(userInfo.getPassword());
        userVo.setToken(token);
        return Result.success(userVo);
    }
}
