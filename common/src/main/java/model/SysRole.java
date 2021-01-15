package model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-01-15 09:33
 **/
@Data
@TableName("sys_role")
public class SysRole {

    @TableId
    private Integer id;

    @TableField("role_name")
    private String roleName;
}
