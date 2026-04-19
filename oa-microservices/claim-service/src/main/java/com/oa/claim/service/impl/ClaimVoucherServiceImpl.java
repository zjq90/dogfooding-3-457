package com.oa.claim.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.oa.claim.dto.ClaimVoucherDTO;
import com.oa.claim.entity.ClaimVoucher;
import com.oa.claim.entity.ClaimVoucherItem;
import com.oa.claim.entity.DealRecord;
import com.oa.claim.mapper.ClaimVoucherItemMapper;
import com.oa.claim.mapper.ClaimVoucherMapper;
import com.oa.claim.mapper.DealRecordMapper;
import com.oa.claim.service.ClaimVoucherService;
import com.oa.common.Constants;
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
    @Transactional
    public void save(ClaimVoucherDTO dto) {
        ClaimVoucher claimVoucher = dto.getClaimVoucher();
        claimVoucher.setCreateTime(new Date());
        claimVoucher.setStatus(Constants.CLAIMVOUCHER_CREATED);
        claimVoucher.setNextDealId(claimVoucher.getCreateId());

        double total = 0;
        for (ClaimVoucherItem item : dto.getItems()) {
            total += item.getAmount();
        }
        claimVoucher.setTotalAmount(total);

        claimVoucherMapper.insert(claimVoucher);

        for (ClaimVoucherItem item : dto.getItems()) {
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
    @Transactional
    public void update(ClaimVoucherDTO dto) {
        ClaimVoucher claimVoucher = dto.getClaimVoucher();
        claimVoucher.setStatus(Constants.CLAIMVOUCHER_CREATED);
        claimVoucher.setNextDealId(claimVoucher.getCreateId());

        double total = 0;
        for (ClaimVoucherItem item : dto.getItems()) {
            total += item.getAmount();
        }
        claimVoucher.setTotalAmount(total);

        claimVoucherMapper.updateById(claimVoucher);

        claimVoucherItemMapper.delete(new LambdaQueryWrapper<ClaimVoucherItem>()
                .eq(ClaimVoucherItem::getClaimVoucherId, claimVoucher.getId()));

        for (ClaimVoucherItem item : dto.getItems()) {
            item.setClaimVoucherId(claimVoucher.getId());
            claimVoucherItemMapper.insert(item);
        }

        DealRecord dealRecord = new DealRecord();
        dealRecord.setClaimVoucherId(claimVoucher.getId());
        dealRecord.setDealId(claimVoucher.getCreateId());
        dealRecord.setDealTime(new Date());
        dealRecord.setDealType(Constants.DEAL_UPDATE);
        dealRecord.setDealResult(Constants.CLAIMVOUCHER_CREATED);
        dealRecord.setComment("无");
        dealRecordMapper.insert(dealRecord);
    }

    @Override
    public ClaimVoucher getById(Integer id) {
        return claimVoucherMapper.selectByIdWithNames(id);
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
    public List<ClaimVoucherItem> listItemsByClaimVoucherId(Integer claimVoucherId) {
        return claimVoucherItemMapper.selectByClaimVoucherId(claimVoucherId);
    }

    @Override
    public List<DealRecord> listRecordsByClaimVoucherId(Integer claimVoucherId) {
        return dealRecordMapper.selectByClaimVoucherId(claimVoucherId);
    }

    @Override
    @Transactional
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
    @Transactional
    public void deal(DealRecord dealRecord) {
        dealRecord.setDealTime(new Date());
        dealRecordMapper.insert(dealRecord);

        ClaimVoucher claimVoucher = claimVoucherMapper.selectById(dealRecord.getClaimVoucherId());

        if (Constants.DEAL_PASS.equals(dealRecord.getDealType())) {
            if (claimVoucher.getTotalAmount() <= Constants.LIMIT_CHECK || Constants.POST_GM.equals(getEmployeePost(claimVoucher.getCreateId()))) {
                claimVoucher.setStatus(Constants.CLAIMVOUCHER_APPROVED);
                claimVoucher.setNextDealId(getCashierId());
            } else {
                claimVoucher.setStatus(Constants.CLAIMVOUCHER_RECHECK);
                claimVoucher.setNextDealId(getGeneralManagerId());
            }
        } else if (Constants.DEAL_BACK.equals(dealRecord.getDealType())) {
            claimVoucher.setStatus(Constants.CLAIMVOUCHER_BACK);
            claimVoucher.setNextDealId(claimVoucher.getCreateId());
        } else if (Constants.DEAL_REJECT.equals(dealRecord.getDealType())) {
            claimVoucher.setStatus(Constants.CLAIMVOUCHER_TERMINATED);
            claimVoucher.setNextDealId(null);
        } else if (Constants.DEAL_PAID.equals(dealRecord.getDealType())) {
            claimVoucher.setStatus(Constants.CLAIMVOUCHER_PAID);
            claimVoucher.setNextDealId(null);
        }

        claimVoucherMapper.updateById(claimVoucher);
    }

    private String getEmployeePost(String employeeId) {
        return Constants.POST_STAFF;
    }

    private String getCashierId() {
        return "c1002";
    }

    private String getGeneralManagerId() {
        return "z1001";
    }
}
