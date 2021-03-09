package com.hongseng.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import model.SysQuestion;
import model.dto.SelectQuestionDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-03-09 13:42
 **/
@Mapper
public interface QuestionMapper extends BaseMapper<SysQuestion> {

    List<SelectQuestionDto> selectQuestionList(@Param("type") Integer type, @Param("subjectId") Integer subjectId, @Param("difficultyGrade") Integer difficultyGrade);
}
