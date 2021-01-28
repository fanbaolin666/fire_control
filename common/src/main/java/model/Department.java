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
 * @create: 2021-01-28 13:44
 **/
@Data
@TableName("department")
public class Department {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 部门名称
     */
    @TableField("department_name")
    private String departmentName;

    /**
     * 部门描述
     */
    @TableField("department_desc")
    private String departmentDesc;

    /**
     * 父部门id
     */
    @TableField("father_id")
    private Integer fatherId;

    /**
     * 部门级别
     */
    @TableField("department_grade")
    private Short departmentGrade;

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
