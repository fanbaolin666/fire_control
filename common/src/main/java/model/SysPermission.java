package model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-01-15 09:42
 **/
@Data
@TableName("sys_permission")
public class SysPermission {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("father_id")
    private Integer fatherId;

    @TableField("menu_name")
    private String menuName;

    private String code;

    @TableField("menu_grade")
    private Integer menuGrade;
}
