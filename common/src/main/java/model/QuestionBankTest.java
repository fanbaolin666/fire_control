package model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-03-09 15:51
 **/
@Data
@TableName("question_bank_test")
public class QuestionBankTest {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 题目id
     */
    @TableField("question_id")
    private Integer questionId;

    /**
     * 试卷id
     */
    @TableField("question_bank_id")
    private Integer questionBankId;

    /**
     * 该题分值
     */
    private Double score;

    /**
     * 如果多选，遗漏正确选项应得几分（没有错选）
     */
    private Double omitScore;
}
