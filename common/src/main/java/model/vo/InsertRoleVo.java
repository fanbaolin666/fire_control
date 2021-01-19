package model.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-01-19 10:30
 **/
@Data
public class InsertRoleVo {

    @NotNull
    private String roleName;

    private String roleDesc;
}
