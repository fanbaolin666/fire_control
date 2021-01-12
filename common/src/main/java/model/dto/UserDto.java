package model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: spring-security
 * @description:
 * @author: fbl
 * @create: 2020-12-08 07:55
 **/
@Data
public class UserDto {
    @NotBlank
    private String userName;

    @NotBlank
    private String password;
}
