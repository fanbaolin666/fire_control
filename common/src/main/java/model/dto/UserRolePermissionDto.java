package model.dto;

import lombok.Data;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-01-15 09:36
 **/
@Data
public class UserRolePermissionDto {

    private Integer id;

    private String userName;

    private String password;

    private String code;
}
