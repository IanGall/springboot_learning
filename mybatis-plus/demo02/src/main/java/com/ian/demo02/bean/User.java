package com.ian.demo02.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName(value = "mp_user")
@EqualsAndHashCode
public class User extends Model<User> {
    public static final String COL_ID = "id";
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "user_id", type = IdType.NONE)
    private Long userId;

    /**
     * 姓名
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 年龄
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 直属上级id
     */
    @TableField(value = "manager_id")
    private Long managerId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(exist = false)
    private String remark;

    public static final String COL_USER_ID = "user_id";

    public static final String COL_NAME = "name";

    public static final String COL_AGE = "age";

    public static final String COL_EMAIL = "email";

    public static final String COL_MANAGER_ID = "manager_id";

    public static final String COL_CREATE_TIME = "create_time";
}