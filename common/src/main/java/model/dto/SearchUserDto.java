package model.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-02-04 09:16
 **/
@Data
public class SearchUserDto {
    private String userName;
    private Long mobile;
    private Boolean available;
    private Timestamp beginTime;
    private Timestamp endTime;
}
