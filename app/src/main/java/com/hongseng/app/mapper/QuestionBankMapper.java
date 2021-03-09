package com.hongseng.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import model.SysQuestionBank;
import model.dto.CountTypeNumDto;
import model.dto.SelectQuestionBankDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-03-09 14:40
 **/
@Mapper
public interface QuestionBankMapper extends BaseMapper<SysQuestionBank> {

    List<SelectQuestionBankDto> selectQuestionBank(@Param("name") String name);

    List<CountTypeNumDto> getCountTypeNum(@Param("id")List<Integer> id );
}
