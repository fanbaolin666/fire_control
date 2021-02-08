package model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-02-05 14:48
 **/
@Data
public class BatchDeleteRoleDto {
    @NotNull
    private List<Integer> ids;
}
