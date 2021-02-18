package com.ian.bean;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import lombok.Data;

@Data
@TableName(value = "`user`")
public class User {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

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
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 版本
     */
    @TableField(value = "version")
    @Version
    private Integer version;

    /**
     * 逻辑删除标识（0，未删除；1，已删除）
     */
    @TableField(value = "deleted", select = false)
    @TableLogic
    private Integer deleted;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_AGE = "age";

    public static final String COL_EMAIL = "email";

    public static final String COL_MANAGER_ID = "manager_id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_VERSION = "version";

    public static final String COL_DELETED = "deleted";
}