package model.vo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-01-28 14:02
 **/
@Data
public class DepartmentVo {
    /**
     * id
     */
    private Integer id;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 部门描述
     */
    private String departmentDesc;

    /**
     * 父部门id
     */
    private Integer fatherId;

    /**
     * 部门级别
     */
    private Short departmentGrade;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 修改时间
     */
    private Timestamp updateTime;

    private List<DepartmentVo> departmentVos = Collections.emptyList();
}
