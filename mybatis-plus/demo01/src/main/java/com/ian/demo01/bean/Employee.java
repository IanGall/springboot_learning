package com.ian.demo01.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Mybatis-plus会默认使用实体类的类名到数据库中找对应的表
 * 解决办法：使用注解
 *
 * @TableName("tbl_employee")
 */
@Data
//@TableName("tbl_employee")
public class Employee {

    /**
     * @TableId: value: 指定表中的主键列的列明，如果实力属性名与列名一致，可以省略不指定
     * type: 指定主键策略
     */
    //@TableId(type = IdType.AUTO)
    private Integer id;

    private String lastName;

    private String email;

    private String gender;

    private Integer age;

    @TableField(exist = false)
    private Double salary;

}
