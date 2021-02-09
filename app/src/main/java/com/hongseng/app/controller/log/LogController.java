package com.hongseng.app.controller.log;

import com.hongseng.app.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import result.Result;
import utils.SearchFilter;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-02-08 09:20
 **/
@RestController
@Validated
@Api(value = "LogController", tags = "日志管理")
public class LogController {

    @Autowired
    LogService logService;

    @PostMapping("/log")
    @PreAuthorize("hasAnyAuthority('0018')")
    @ApiOperation(value = "展示日志记录")
    public Result logList(@RequestBody @Validated SearchFilter searchFilter) {
        return logService.logList(searchFilter);
    }


}
