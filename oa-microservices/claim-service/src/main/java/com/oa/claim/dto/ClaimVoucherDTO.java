package com.oa.claim.dto;

import com.oa.claim.entity.ClaimVoucher;
import com.oa.claim.entity.ClaimVoucherItem;
import lombok.Data;

import java.util.List;

@Data
public class ClaimVoucherDTO {

    private ClaimVoucher claimVoucher;

    private List<ClaimVoucherItem> items;
}
