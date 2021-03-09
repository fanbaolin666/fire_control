package com.hongseng.app.controller.examination;

import com.hongseng.app.service.QuestionService;
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
 * @create: 2021-03-09 13:41
 **/
@RestController
@Validated
@Api(value = "QuestionController", tags = "试题管理")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping("/question")
    @PreAuthorize("hasAnyAuthority('0210')")
    @ApiOperation(value = "展示所有试题")
    public Result questionList(@RequestBody @Validated SearchFilter searchFilter) {
        return questionService.questionList(searchFilter);
    }

}
