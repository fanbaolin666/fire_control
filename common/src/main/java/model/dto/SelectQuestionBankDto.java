package model.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-03-09 15:20
 **/
@Data
public class SelectQuestionBankDto {
    private Integer id;
    private String name;
    private String remark;
    private Integer radio = 0;
    private Integer multi = 0;
    private Integer judge = 0;
    private Timestamp createTime;
}
