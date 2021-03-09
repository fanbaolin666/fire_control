package com.hongseng.app.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongseng.app.mapper.QuestionBankMapper;
import com.hongseng.app.mapper.QuestionBankTestMapper;
import model.QuestionBankTest;
import model.dto.CountTypeNumDto;
import model.dto.SelectQuestionBankDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.Result;
import utils.SearchFilter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-03-09 14:40
 **/
@Service
public class QuestionBankService {

    @Autowired
    QuestionBankMapper questionBankMapper;

    @Autowired
    QuestionBankTestMapper questionBankTestMapper;

    public Result questionBankList(SearchFilter searchFilter) {
        PageHelper.startPage(searchFilter.getPageNum(), searchFilter.getPageSize());
        JSONObject filters = searchFilter.getFilters();
        String name = null;
        if (Objects.nonNull(filters)) {
            name = filters.getString("name");
        }
        // 查询题库
        List<SelectQuestionBankDto> selectQuestionBankDtos = questionBankMapper.selectQuestionBank(name);
        // 统计各种类型选择题数量
        for (SelectQuestionBankDto selectQuestionBankDto : selectQuestionBankDtos) {
            Integer id = selectQuestionBankDto.getId();
            // 题库都有那些题
            QueryWrapper<QuestionBankTest> questionBankId = new QueryWrapper<QuestionBankTest>().eq("question_bank_id", id);
            List<QuestionBankTest> questionBankTests = questionBankTestMapper.selectList(questionBankId);
            // 这些题的类型统计
            List<CountTypeNumDto> countTypeNum = questionBankMapper.getCountTypeNum(questionBankTests.stream().map(QuestionBankTest::getId).collect(Collectors.toList()));
            for (CountTypeNumDto countTypeNumDto : countTypeNum) {
                if(countTypeNumDto.getType().equals(1)){
                    selectQuestionBankDto.setRadio(countTypeNumDto.getCount());
                }
                if(countTypeNumDto.getType().equals(2)){
                    selectQuestionBankDto.setMulti(countTypeNumDto.getCount());
                }
                if(countTypeNumDto.getType().equals(3)){
                    selectQuestionBankDto.setJudge(countTypeNumDto.getCount());
                }
            }
        }
        PageInfo<SelectQuestionBankDto> sysQuestionBankPageInfo = new PageInfo<>(selectQuestionBankDtos);
        return Result.success(sysQuestionBankPageInfo);
    }
}
