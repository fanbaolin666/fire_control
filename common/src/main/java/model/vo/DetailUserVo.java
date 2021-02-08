package model.vo;

import lombok.Data;

import java.util.List;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-02-03 10:36
 **/
@Data
public class DetailUserVo {
    private Integer id;

    private String userName;

    private Boolean available;

    private Integer departmentId;

    private Integer firstDepartmentGradeId;

    private Integer secondDepartmentGradeId;

    private Integer thirdDepartmentGradeId;

    private List<Integer> roleId;

    private Integer sex;

    private String telephone;

    private Long mobile;

    private String email;

}
