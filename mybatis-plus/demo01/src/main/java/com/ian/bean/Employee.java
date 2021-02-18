package com.ian.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "tbl_employee")
public class Employee {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    @TableField(value = "last_name")
    private String lastName;

    @TableField(value = "email")
    private String email;

    @TableField(value = "gender")
    private String gender;

    @TableField(value = "age")
    private Integer age;

    public static final String COL_ID = "id";

    public static final String COL_LAST_NAME = "last_name";

    public static final String COL_EMAIL = "email";

    public static final String COL_GENDER = "gender";

    public static final String COL_AGE = "age";
}