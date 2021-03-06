package model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-02-02 08:38
 **/
@Data
public class UpdateUserDto {

    @NotNull
    private Integer id;

    private String password;

    private String userName;

    private Integer departmentId;

    private Boolean available;

    private List<Integer> roleId;

    private Integer sex;

    private String telephone;

    private Long mobile;

    private String email;
}
