package com.oa.claim.service;

import com.oa.claim.dto.ClaimVoucherDTO;
import com.oa.claim.entity.ClaimVoucher;
import com.oa.claim.entity.ClaimVoucherItem;
import com.oa.claim.entity.DealRecord;

import java.util.List;

public interface ClaimVoucherService {

    void save(ClaimVoucherDTO dto);

    ClaimVoucher getById(Integer id);

    List<ClaimVoucherItem> listItemsByClaimVoucherId(Integer claimVoucherId);

    List<DealRecord> listRecordsByClaimVoucherId(Integer claimVoucherId);

    List<ClaimVoucher> listByCreateId(String createId);

    List<ClaimVoucher> listByNextDealId(String nextDealId);

    void update(ClaimVoucherDTO dto);

    void submit(Integer id);

    void deal(DealRecord dealRecord);
}
