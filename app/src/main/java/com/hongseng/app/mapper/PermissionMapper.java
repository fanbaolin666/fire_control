package com.hongseng.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import model.SysPermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-01-15 09:45
 **/
@Mapper
public interface PermissionMapper extends BaseMapper<SysPermission> {
}
