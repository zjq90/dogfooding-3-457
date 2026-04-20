package com.oa.claim.service.impl;

import com.oa.common.Constants;
import com.oa.claim.dto.ClaimVoucherDTO;
import com.oa.claim.entity.ClaimVoucher;
import com.oa.claim.entity.ClaimVoucherItem;
import com.oa.claim.entity.DealRecord;
import com.oa.claim.mapper.ClaimVoucherItemMapper;
import com.oa.claim.mapper.ClaimVoucherMapper;
import com.oa.claim.mapper.DealRecordMapper;
import com.oa.claim.service.ClaimVoucherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ClaimVoucherServiceImpl implements ClaimVoucherService {

    @Resource
    private ClaimVoucherMapper claimVoucherMapper;

    @Resource
    private ClaimVoucherItemMapper claimVoucherItemMapper;

    @Resource
    private DealRecordMapper dealRecordMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ClaimVoucherDTO dto) {
        ClaimVoucher claimVoucher = dto.getClaimVoucher();
        List<ClaimVoucherItem> items = dto.getItems();

        claimVoucher.setCreateTime(new Date());
        claimVoucher.setNextDealId(claimVoucher.getCreateId());
        claimVoucher.setStatus(Constants.CLAIMVOUCHER_CREATED);
        claimVoucherMapper.insert(claimVoucher);

        for (ClaimVoucherItem item : items) {
            item.setClaimVoucherId(claimVoucher.getId());
            claimVoucherItemMapper.insert(item);
        }

        DealRecord dealRecord = new DealRecord();
        dealRecord.setClaimVoucherId(claimVoucher.getId());
        dealRecord.setDealId(claimVoucher.getCreateId());
        dealRecord.setDealTime(new Date());
        dealRecord.setDealType(Constants.DEAL_CREATE);
        dealRecord.setDealResult(Constants.CLAIMVOUCHER_CREATED);
        dealRecord.setComment("无");
        dealRecordMapper.insert(dealRecord);
    }

    @Override
    public ClaimVoucher getById(Integer id) {
        return claimVoucherMapper.selectById(id);
    }

    @Override
    public List<ClaimVoucherItem> listItemsByClaimVoucherId(Integer claimVoucherId) {
        return claimVoucherItemMapper.selectByClaimVoucherId(claimVoucherId);
    }

    @Override
    public List<DealRecord> listRecordsByClaimVoucherId(Integer claimVoucherId) {
        return dealRecordMapper.selectByClaimVoucherId(claimVoucherId);
    }

    @Override
    public List<ClaimVoucher> listByCreateId(String createId) {
        return claimVoucherMapper.selectByCreateId(createId);
    }

    @Override
    public List<ClaimVoucher> listByNextDealId(String nextDealId) {
        return claimVoucherMapper.selectByNextDealId(nextDealId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ClaimVoucherDTO dto) {
        ClaimVoucher claimVoucher = dto.getClaimVoucher();
        List<ClaimVoucherItem> items = dto.getItems();

        claimVoucher.setNextDealId(claimVoucher.getCreateId());
        claimVoucher.setStatus(Constants.CLAIMVOUCHER_CREATED);
        claimVoucherMapper.updateById(claimVoucher);

        claimVoucherItemMapper.deleteByClaimVoucherId(claimVoucher.getId());
        for (ClaimVoucherItem item : items) {
            item.setClaimVoucherId(claimVoucher.getId());
            claimVoucherItemMapper.insert(item);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submit(Integer id) {
        ClaimVoucher claimVoucher = claimVoucherMapper.selectById(id);

        claimVoucher.setStatus(Constants.CLAIMVOUCHER_SUBMIT);
        claimVoucherMapper.updateById(claimVoucher);

        DealRecord dealRecord = new DealRecord();
        dealRecord.setClaimVoucherId(id);
        dealRecord.setDealId(claimVoucher.getCreateId());
        dealRecord.setDealTime(new Date());
        dealRecord.setDealType(Constants.DEAL_SUBMIT);
        dealRecord.setDealResult(Constants.CLAIMVOUCHER_SUBMIT);
        dealRecord.setComment("无");
        dealRecordMapper.insert(dealRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deal(DealRecord dealRecord) {
        ClaimVoucher claimVoucher = claimVoucherMapper.selectById(dealRecord.getClaimVoucherId());
        dealRecord.setDealTime(new Date());

        if (Constants.DEAL_PASS.equals(dealRecord.getDealType())) {
            claimVoucher.setStatus(Constants.CLAIMVOUCHER_APPROVED);
            claimVoucher.setNextDealId(null);
            dealRecord.setDealResult(Constants.CLAIMVOUCHER_APPROVED);
        } else if (Constants.DEAL_BACK.equals(dealRecord.getDealType())) {
            claimVoucher.setStatus(Constants.CLAIMVOUCHER_BACK);
            claimVoucher.setNextDealId(claimVoucher.getCreateId());
            dealRecord.setDealResult(Constants.CLAIMVOUCHER_BACK);
        } else if (Constants.DEAL_REJECT.equals(dealRecord.getDealType())) {
            claimVoucher.setStatus(Constants.CLAIMVOUCHER_TERMINATED);
            claimVoucher.setNextDealId(null);
            dealRecord.setDealResult(Constants.CLAIMVOUCHER_TERMINATED);
        } else if (Constants.DEAL_PAID.equals(dealRecord.getDealType())) {
            claimVoucher.setStatus(Constants.CLAIMVOUCHER_PAID);
            claimVoucher.setNextDealId(null);
            dealRecord.setDealResult(Constants.CLAIMVOUCHER_PAID);
        }

        claimVoucherMapper.updateById(claimVoucher);
        dealRecordMapper.insert(dealRecord);
    }
}
