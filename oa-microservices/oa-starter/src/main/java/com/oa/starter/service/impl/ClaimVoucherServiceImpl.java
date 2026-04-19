package com.oa.starter.service.impl;

import com.oa.common.common.Constants;
import com.oa.common.dto.ClaimVoucherDTO;
import com.oa.common.entity.ClaimVoucher;
import com.oa.common.entity.ClaimVoucherItem;
import com.oa.common.entity.DealRecord;
import com.oa.common.entity.Employee;
import com.oa.starter.mapper.ClaimVoucherItemMapper;
import com.oa.starter.mapper.ClaimVoucherMapper;
import com.oa.starter.mapper.DealRecordMapper;
import com.oa.starter.mapper.EmployeeMapper;
import com.oa.starter.service.ClaimVoucherService;
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

    @Resource
    private EmployeeMapper employeeMapper;

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
        return claimVoucherMapper.selectWithDetails(id);
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
        Employee employee = employeeMapper.selectById(claimVoucher.getCreateId());

        claimVoucher.setStatus(Constants.CLAIMVOUCHER_SUBMIT);
        List<Employee> managers = employeeMapper.selectByDepartmentAndPost(employee.getDepartmentId(), Constants.POST_FM);
        claimVoucher.setNextDealId(managers.get(0).getId());
        claimVoucherMapper.updateById(claimVoucher);

        DealRecord dealRecord = new DealRecord();
        dealRecord.setClaimVoucherId(id);
        dealRecord.setDealId(employee.getId());
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
        Employee employee = employeeMapper.selectById(dealRecord.getDealId());
        dealRecord.setDealTime(new Date());

        if (Constants.DEAL_PASS.equals(dealRecord.getDealType())) {
            if (claimVoucher.getTotalAmount() <= Constants.LIMIT_CHECK || Constants.POST_GM.equals(employee.getPost())) {
                claimVoucher.setStatus(Constants.CLAIMVOUCHER_APPROVED);
                List<Employee> cashiers = employeeMapper.selectByDepartmentAndPost(null, Constants.POST_CASHIER);
                claimVoucher.setNextDealId(cashiers.get(0).getId());
                dealRecord.setDealResult(Constants.CLAIMVOUCHER_APPROVED);
            } else {
                claimVoucher.setStatus(Constants.CLAIMVOUCHER_RECHECK);
                List<Employee> gms = employeeMapper.selectByDepartmentAndPost(null, Constants.POST_GM);
                claimVoucher.setNextDealId(gms.get(0).getId());
                dealRecord.setDealResult(Constants.CLAIMVOUCHER_RECHECK);
            }
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
