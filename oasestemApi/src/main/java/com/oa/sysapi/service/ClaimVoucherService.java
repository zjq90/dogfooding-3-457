package com.oa.sysapi.service;

import com.oa.sysapi.dto.ClaimVoucherDTO;
import com.oa.sysapi.entity.ClaimVoucher;
import com.oa.sysapi.entity.ClaimVoucherItem;
import com.oa.sysapi.entity.DealRecord;

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
