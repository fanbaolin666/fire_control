package model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.Instant;


/**
 * @program: fire_control
 * @description: 用户实体类
 * @author: fbl
 * @create: 2021-01-12 08:28
 **/
@Data
@TableName("sys_user")
public class SysUser {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("user_name")
    private String userName;

    private String password;

    @TableField("is_disable")
    private Boolean disable;

    @TableField("create_time")
    private Instant createTime;

    @TableField("update_time")
    private Instant updateTime;

}
