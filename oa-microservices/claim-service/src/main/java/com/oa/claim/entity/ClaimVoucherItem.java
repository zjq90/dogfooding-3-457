package com.oa.claim.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("claim_voucher_item")
public class ClaimVoucherItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;

    @TableField("claim_voucher_id")
    private Integer claimVoucherId;

    private String item;

    private Double amount;

    private String comment;
}
