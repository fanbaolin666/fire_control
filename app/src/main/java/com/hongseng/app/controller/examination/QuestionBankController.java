package com.hongseng.app.controller.examination;

import com.hongseng.app.service.QuestionBankService;
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
@Api(value = "QuestionBankController", tags = "题库管理")
public class QuestionBankController {

    @Autowired
    QuestionBankService questionBankService;

    @PostMapping("/question-bank")
    @PreAuthorize("hasAnyAuthority('0110')")
    @ApiOperation(value = "展示题库")
    public Result questionBankList(@RequestBody @Validated SearchFilter searchFilter) {
        return questionBankService.questionBankList(searchFilter);
    }
}
