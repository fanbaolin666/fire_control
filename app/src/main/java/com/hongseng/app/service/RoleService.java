package com.hongseng.app.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongseng.app.mapper.RoleMapper;
import com.hongseng.app.mapper.UserRoleMapper;
import enums.ErrorCodeEnum;
import model.SysRole;
import model.UserRole;
import model.dto.BatchDeleteRoleDto;
import model.dto.InsertRoleDto;
import model.dto.UpdateRoleDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.CommonConstants;
import result.Result;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-01-19 10:24
 **/
@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    public Result roleList(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>();
        List<SysRole> sysRoles = roleMapper.selectList(sysRoleQueryWrapper);
        PageInfo<SysRole> sysRolePageInfo = new PageInfo<>(sysRoles);
        return Result.success(sysRolePageInfo);

    }

    public Result insertRole(InsertRoleDto roleVo) {
        // 角色名重复
        if(isUniqueRoleName(roleVo.getRoleName())){
            return Result.failure(ErrorCodeEnum.SYS_ERR_ROLE_REPETITION);
        }
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleVo, sysRole);
        roleMapper.insert(sysRole);
        return Result.success();
    }

    public Result updateRole(UpdateRoleDto roleDto) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleDto, sysRole);
        roleMapper.updateById(sysRole);
        return Result.success();
    }

    public Result deleteRole(Integer id) {
        int res = roleMapper.deleteById(id);
        deleteUserRole(id);
        if (res == CommonConstants.DeleteCodeStatus.IS_NOT_DELETE) {
            return Result.failure(ErrorCodeEnum.SYS_ERR_DELETE_FAILED);
        }
        return Result.success();
    }

    public Result batchDeleteRole(BatchDeleteRoleDto batchDeleteRoleDto){
        @NotNull List<Integer> ids = batchDeleteRoleDto.getIds();
        int res = roleMapper.deleteBatchIds(ids);
        ids.forEach(this::deleteUserRole);
        if (res == CommonConstants.DeleteCodeStatus.IS_NOT_DELETE) {
            return Result.failure(ErrorCodeEnum.SYS_ERR_DELETE_FAILED);
        }
        return Result.success();
    }

    /**
     * @Author fbl
     * @Description 删除用户的角色信息
     * @Date 14:28 2021/2/8
     * @Param id
    */
    private void deleteUserRole(Integer id){
        QueryWrapper<UserRole> roleId = new QueryWrapper<UserRole>().eq("role_id", id);
        userRoleMapper.delete(roleId);
    }

    public Result getRole(Integer id) {
        return Result.success(roleMapper.selectById(id));
    }

    /**
     * 角色名唯一
     *
     * @param roleName
     * @return
     */
    private boolean isUniqueRoleName(String roleName){
        QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>();
        sysRoleQueryWrapper.eq("role_name", roleName);
        return CollectionUtil.isNotEmpty(roleMapper.selectList(sysRoleQueryWrapper));
    }
}
