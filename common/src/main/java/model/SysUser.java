package model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



/**
 * @program: fire_control
 * @description:
 * @author: fbl
 * @create: 2021-01-12 08:28
 **/
@Data
@TableName("sys_user")
public class SysUser {
    @TableId
    private Integer id;

    @TableField("user_name")
    private String userName;

    private String password;

    private String permission;

}
