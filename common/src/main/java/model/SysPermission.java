package model;

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
    @TableId
    private Integer id;

    @TableField("menu_name")
    private String menuName;

    private String code;
}
