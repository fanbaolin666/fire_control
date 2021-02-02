package com.hongseng.app.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongseng.app.mapper.PermissionMapper;
import com.hongseng.app.mapper.RolePermissionMapper;
import enums.ErrorCodeEnum;
import model.RolePermission;
import model.SysPermission;
import model.dto.UpdatePermissionDto;
import model.vo.PermissionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import result.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-01-22 15:21
 **/
@Service
public class RolePermissionService extends ServiceImpl<RolePermissionMapper, RolePermission> {

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Autowired
    PermissionMapper permissionMapper;


    public Result permissionList() {
        List<SysPermission> permissionList = rolePermissionMapper.permissionList();
        List<PermissionVo> permissionVo = getPermissionVo(permissionList);
        return Result.success(permissionVo);
    }

    @Transactional(rollbackFor = Exception.class)
    public Result updatePermission(UpdatePermissionDto updatePermissionDto) {
        QueryWrapper<RolePermission> rolePermissionQueryWrapper = new QueryWrapper<>();
        rolePermissionQueryWrapper.eq("role_id", updatePermissionDto.getRoleId());
        // 删除角色所有权限
        rolePermissionMapper.delete(rolePermissionQueryWrapper);
        // 新增新的权限
        List<Integer> permissionIds = updatePermissionDto.getPermissionIds();

        ArrayList<RolePermission> rolePermissions = new ArrayList<>();
        RolePermission rolePermission = null;
        for (Integer permissionId : permissionIds) {
            rolePermission = new RolePermission();
            rolePermission.setPermissionId(permissionId);
            rolePermission.setRoleId(updatePermissionDto.getRoleId());
            rolePermissions.add(rolePermission);
        }

        boolean res = saveBatch(rolePermissions);
        if (!res) {
            return Result.failure(ErrorCodeEnum.SYS_ERR_UPDATE_FAILED);
        }
        return Result.success();
    }

    public Result userPermission(Integer roleId) {
        List<Integer> permissionIds = rolePermissionMapper.getPermissionIds(roleId);
        QueryWrapper<SysPermission> sysPermissionQueryWrapper = new QueryWrapper<>();
        sysPermissionQueryWrapper.in("id", permissionIds);
        List<SysPermission> permissionList = permissionMapper.selectList(sysPermissionQueryWrapper);
        List<String> codes = permissionList.stream().map(SysPermission::getCode).collect(Collectors.toList());
        return Result.success(codes);
    }


    /**
     * @return List<PermissionVo>
     * @Author fbl
     * @Description 分层展示权限信息
     * @Date 16:03 2021/1/25
     * @Param permissionList
     */
    private List<PermissionVo> getPermissionVo(List<SysPermission> permissionList) {
        ArrayList<PermissionVo> onePermissionDto = new ArrayList<>();
        // 一级权限
        List<SysPermission> onePermission = permissionList.stream().filter(p -> p.getMenuGrade() == 1).collect(Collectors.toList());
        // 二级权限
        List<SysPermission> twoPermission = permissionList.stream().filter(p -> p.getMenuGrade() == 2).collect(Collectors.toList());
        // 三级权限
        List<SysPermission> threePermission = permissionList.stream().filter(p -> p.getMenuGrade() == 3).collect(Collectors.toList());

        PermissionVo permissionVo = null;
        for (SysPermission one : onePermission) {
            permissionVo = new PermissionVo();
            BeanUtils.copyProperties(one, permissionVo);
            onePermissionDto.add(permissionVo);
        }
        ArrayList<PermissionVo> twoPermissionDto;
        for (PermissionVo one : onePermissionDto) {
            twoPermissionDto = new ArrayList<>();
            for (SysPermission two : twoPermission) {
                if (one.getId().equals(two.getFatherId())) {
                    permissionVo = new PermissionVo();
                    BeanUtils.copyProperties(two, permissionVo);
                    twoPermissionDto.add(permissionVo);
                    one.setPermissionVo(twoPermissionDto);
                }
            }
            ArrayList<PermissionVo> threePermissionDto;
            for (PermissionVo two : twoPermissionDto) {
                threePermissionDto  = new ArrayList<>();
                for (SysPermission three : threePermission) {
                    if (two.getId().equals(three.getFatherId())) {
                        permissionVo = new PermissionVo();
                        BeanUtils.copyProperties(three, permissionVo);
                        threePermissionDto.add(permissionVo);
                        two.setPermissionVo(threePermissionDto);
                    }
                }
            }
        }
        return onePermissionDto;
    }
}
