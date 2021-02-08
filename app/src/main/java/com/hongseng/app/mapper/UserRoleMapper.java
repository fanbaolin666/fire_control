package com.hongseng.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import model.UserRole;
import model.dto.InsertUserRoleDto;
import model.dto.UpdateRoleDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-01-15 09:37
 **/
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

   Integer insertUserRole(List<InsertUserRoleDto> userRoleDtos);

   List<UpdateRoleDto> selectRoleName(Integer userId);
}
