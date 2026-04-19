package com.oa.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("deal_record")
public class DealRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;

    @TableField("claim_voucher_id")
    private Integer claimVoucherId;

    @TableField("deal_id")
    private String dealId;

    @TableField("deal_time")
    private Date dealTime;

    @TableField("deal_type")
    private String dealType;

    @TableField("deal_result")
    private String dealResult;

    private String comment;

    @TableField(exist = false)
    private Employee dealer;
}
