package model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-02-01 09:21
 **/
@Data
public class InsertUserDto {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    private Integer departmentId;

    private List<Integer> roleId;

    private Integer sex;

    private String telephone;

    private Integer mobile;

    private String email;

}
