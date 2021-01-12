package com.hongseng.app.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hongseng.app.mapper.UserMapper;
import model.JwtUser;
import model.SysUser;
import model.dto.UserDto;
import model.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import result.ErrorCodeEnum;
import result.Result;
import utils.JwtTokenUtils;

import java.util.Collection;
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
    private PasswordEncoder passwordEncoder;

    public Result login(UserDto user) {
        String userName = user.getUserName();
        String password = user.getPassword();
        // 用户名是否存在
        QueryWrapper<SysUser> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("user_name",userName);
        SysUser userInfo = userMapper.selectOne(objectQueryWrapper);

        JwtUser jwtUser = new JwtUser(userInfo);

        if (null == userInfo) {
            return Result.failure(ErrorCodeEnum.SYS_ERR_LOGIN_FAIL);

        }
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
