package com.oa.common.dto;

import com.oa.common.entity.ClaimVoucher;
import com.oa.common.entity.ClaimVoucherItem;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ClaimVoucherDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private ClaimVoucher claimVoucher;
    private List<ClaimVoucherItem> items;
}
