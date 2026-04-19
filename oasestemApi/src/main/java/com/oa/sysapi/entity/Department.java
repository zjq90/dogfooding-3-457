package com.oa.sysapi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    private String name;

    private String address;
}
