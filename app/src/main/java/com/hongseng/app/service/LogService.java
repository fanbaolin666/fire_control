package com.hongseng.app.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.hongseng.app.mapper.LogMapper;
import model.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.Result;
import utils.SearchFilter;

import java.util.List;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-02-08 09:21
 **/
@Service
public class LogService {

    @Autowired
    LogMapper logMapper;

    public Result logList(SearchFilter searchFilter) {
        PageHelper.startPage(searchFilter.getPageNum(),searchFilter.getPageSize());
        QueryWrapper<SysLog> logQueryWrapper = new QueryWrapper<>();

        List<SysLog> sysLogs = logMapper.selectList(logQueryWrapper);
        return Result.success(sysLogs);
    }
}
