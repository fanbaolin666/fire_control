package model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-02-01 16:55
 **/
@Data
public class DeleteDepartmentDto {
    @NotNull
    private List<Integer> ids;
}
