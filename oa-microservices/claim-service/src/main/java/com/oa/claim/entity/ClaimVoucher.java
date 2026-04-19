package com.oa.claim.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("claim_voucher")
public class ClaimVoucher implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;

    private String cause;

    @TableField("create_id")
    private String createId;

    @TableField("create_time")
    private Date createTime;

    @TableField("next_deal_id")
    private String nextDealId;

    @TableField("total_amount")
    private Double totalAmount;

    private String status;

    @TableField(exist = false)
    private String creatorName;

    @TableField(exist = false)
    private String dealerName;
}
