package com.hongseng.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import model.SysLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-02-05 10:50
 **/
@Mapper
public interface LogMapper extends BaseMapper<SysLog> {
}
