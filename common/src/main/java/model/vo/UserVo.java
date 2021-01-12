package model.vo;

import lombok.Data;

/**
 * @program: spring-security
 * @description:
 * @author: fbl
 * @create: 2020-12-08 08:15
 **/
@Data
public class UserVo {
    private String userName;
    private String password;
    private String token;
}
