package com.hongseng.app.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongseng.app.mapper.UserMapper;
import model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.Result;

import java.util.List;

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

    public Result listUser(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> sysUsers = userMapper.selectList(new QueryWrapper<SysUser>());
        PageInfo<SysUser> sysUserPageInfo = new PageInfo<>(sysUsers);
        return Result.success(sysUserPageInfo);
    }

    public Result userDetail(Integer id){
        QueryWrapper<SysUser> userId = new QueryWrapper<SysUser>().eq("id", id);
        List<SysUser> sysUsers = userMapper.selectList(userId);
        return Result.success(sysUsers);
    }
}
