package com.hongseng.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import model.RolePermission;
import model.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-01-15 09:52
 **/
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {


    @Select("SELECT id,menu_name menuName ,code ,menu_grade menuGrade,father_id fatherId  from sys_permission ")
    List<SysPermission> permissionList();

    @Select("select permission_id from role_permission where role_id = #{roleId}")
    List<Integer> getPermissionIds(Integer roleId);

}
