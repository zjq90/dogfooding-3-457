package com.oa.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("log")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;

    @TableField("employee_id")
    private String employeeId;

    @TableField("operation_time")
    private Date operationTime;

    private String operation;

    @TableField(exist = false)
    private Employee employee;
}
