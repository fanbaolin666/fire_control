package com.hongseng.app.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hongseng.app.config.jwtfilter.JWTAuthorizationFilter;
import com.hongseng.app.mapper.PermissionMapper;
import com.hongseng.app.mapper.RolePermissionMapper;
import com.hongseng.app.mapper.UserMapper;
import com.hongseng.app.mapper.UserRoleMapper;
import enums.ErrorCodeEnum;
import model.*;
import model.dto.UserDto;
import model.dto.UserRolePermissionDto;
import model.vo.JwtUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import result.Result;
import utils.JwtTokenUtils;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
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

    /**
     * @Author fbl
     * @Description 登录
     * @Date 9:39 2021/1/18
     * @Param userInfo
     * @return UserRoleDto
     */
    public Result login(UserDto user) {
        String userName = user.getUserName();
        String password = user.getPassword();

        // 重新登录清除相关用户token
        if(Objects.nonNull(JWTAuthorizationFilter.TOKEN.get(userName))){
            JWTAuthorizationFilter.TOKEN.put(userName,null);
        }

        // 用户名是否存在
        QueryWrapper<SysUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name", userName).eq("is_available",true);
        SysUser userInfo = userMapper.selectOne(userQueryWrapper);
        if (Objects.isNull(userInfo)) {
            return Result.failure(ErrorCodeEnum.SYS_ERR_LOGIN_FAIL);
        }
        // 刷新用户最后登录时间
        userInfo.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
        userMapper.updateById(userInfo);

        // 得到用户权限
        UserRolePermissionDto userRolePermissionDto = getUserRole(userInfo);
        JwtUser jwtUser = new JwtUser(userRolePermissionDto);


        boolean flag = passwordEncoder.matches(password, userInfo.getPassword());
        if (!flag || !userInfo.getAvailable()) {
            return Result.failure(ErrorCodeEnum.SYS_ERR_LOGIN_FAIL);
        }
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();

        String strAuthorities = authorities.stream().map(a -> ((GrantedAuthority) a).getAuthority()).collect(Collectors.joining(","));
        String token = JwtTokenUtils.createToken(userName, strAuthorities, false);

        // 模型转换
        JwtUserVo userVo = new JwtUserVo();
        BeanUtils.copyProperties(user, userVo);
        userVo.setPassword(userInfo.getPassword());
        userVo.setToken(token);
        return Result.success(userVo);
    }


    /**
     * @Author fbl
     * @Description 得到用户权限
     * @Date 9:39 2021/1/18
     * @Param userInfo
     * @return UserRoleDto
    */
    private UserRolePermissionDto getUserRole(SysUser userInfo) {
        // 用户存在，查找角色
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.eq("user_id", userInfo.getId());
        List<UserRole> userRoles = userRoleMapper.selectList(userRoleQueryWrapper);

        // 角色查找权限
        QueryWrapper<RolePermission> rolePermissionQueryWrapper = new QueryWrapper<>();
        rolePermissionQueryWrapper.in("role_id", userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList()));
        List<RolePermission> rolePermissions = rolePermissionMapper.selectList(rolePermissionQueryWrapper);

        QueryWrapper<SysPermission> permissionQueryWrapper = new QueryWrapper<>();
        permissionQueryWrapper.in("id", rolePermissions.stream().map(RolePermission::getPermissionId).distinct().collect(Collectors.toList()));
        List<SysPermission> sysPermissions = permissionMapper.selectList(permissionQueryWrapper);
        UserRolePermissionDto userRolePermissionDto = new UserRolePermissionDto();
        BeanUtils.copyProperties(userInfo, userRolePermissionDto);
        userRolePermissionDto.setCode(sysPermissions.stream().map(SysPermission::getCode).distinct().collect(Collectors.joining(",")));
        return userRolePermissionDto;
    }
}
