package com.hongseng.app.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import model.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author fanbaolin
 * @Description
 * @Date 8:33 2021/1/12
 */
@Mapper
public interface UserMapper extends BaseMapper<SysUser> {

}
