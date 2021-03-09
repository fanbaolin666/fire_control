package com.hongseng.app.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongseng.app.mapper.QuestionMapper;
import model.SysQuestion;
import model.dto.SelectQuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.Result;
import utils.SearchFilter;

import java.util.List;
import java.util.Objects;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-03-09 13:41
 **/
@Service
public class QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    public Result questionList(SearchFilter searchFilter) {
        PageHelper.startPage(searchFilter.getPageNum(), searchFilter.getPageSize());
        JSONObject filters = searchFilter.getFilters();
        Integer type = null;
        Integer subjectId = null;
        Integer difficultyGrade = null;
        if (Objects.nonNull(filters)) {
            type = filters.getInteger("type");
            subjectId = filters.getInteger("subjectId");
            difficultyGrade = filters.getInteger("difficultyGrade");
        }
        List<SelectQuestionDto> selectQuestionDtos = questionMapper.selectQuestionList(type, subjectId, difficultyGrade);
        PageInfo<SelectQuestionDto> sysQuestionPageInfo = new PageInfo<>(selectQuestionDtos);
        return Result.success(sysQuestionPageInfo);
    }
}
