package model.vo;

import lombok.Data;

import java.util.Collections;
import java.util.List;


/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-01-22 15:49
 **/
@Data
public class PermissionVo {
    private Integer id;

    private Integer fatherId;

    private String menuName;

    private String code;

    private Integer menuGrade;

    private List<PermissionVo> permissionVo = Collections.emptyList();
}
