package model.vo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-02-02 13:37
 **/
@Data
public class SelectUserVo {
    private Integer id;

    private String userName;

    private Boolean available;

    private Integer departmentId;

    private String departmentName;

    private List<String> roleName;

    private Integer sex;

    private String telephone;

    private Long mobile;

    private String email;

    private Timestamp lastLoginTime;

    private Timestamp createTime;
}
