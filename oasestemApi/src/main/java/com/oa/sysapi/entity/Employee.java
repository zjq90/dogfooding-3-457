package com.oa.sysapi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    private String password;

    private String name;

    @TableField("department_id")
    private String departmentId;

    private String post;

    @TableField(exist = false)
    private Department department;
}
