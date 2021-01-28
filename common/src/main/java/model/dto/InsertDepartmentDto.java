package model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-01-28 14:32
 **/
@Data
public class InsertDepartmentDto {
    /**
     * 部门名称
     */
    @NotBlank
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
    @NotNull
    private Short departmentGrade;
}
