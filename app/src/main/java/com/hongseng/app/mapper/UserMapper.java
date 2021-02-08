package com.hongseng.app.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import model.SysUser;
import model.dto.SearchUserDto;
import model.vo.DetailUserVo;
import model.vo.SelectUserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author fanbaolin
 * @Description
 * @Date 8:33 2021/1/12
 */
@Mapper
public interface UserMapper extends BaseMapper<SysUser> {


    List<SelectUserVo> listUser(SearchUserDto searchUserDto);

    DetailUserVo selectUserVoById(Integer id);

}
