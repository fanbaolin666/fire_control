package model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-03-09 13:43
 **/
@Data
@TableName("sys_question")
public class SysQuestion {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
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
     * 科目外键
     */
    private Integer subjectId;

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
    @TableField("picture_url")
    private String pictureUrl;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Timestamp createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Timestamp updateTime;
}
