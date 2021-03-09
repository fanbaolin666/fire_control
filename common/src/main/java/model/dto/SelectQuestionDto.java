package model.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-03-09 14:08
 **/
@Data
public class SelectQuestionDto {
    /**
     * id
     */
    private Integer id;

    /**
     * 题目内容
     */
    private String content;

    /**
     * 题目类型 1 单选 2 多选 3 判断
     */
    private Integer type;

    /**
     * 答案 abcd对应1234 判断 1 正确 2 错误
     */
    private Integer answer;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 难度等级 1低级 2中级 3高级
     */
    private Integer difficultyGrade;

    /**
     * 问题解析
     */
    private String analysis;

    /**
     * 题目图片路径
     */
    private String pictureUrl;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 修改时间
     */
    private Timestamp updateTime;
}
