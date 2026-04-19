package com.oa.claim.controller;

import com.oa.common.common.Constants;
import com.oa.common.common.Result;
import com.oa.common.dto.ClaimVoucherDTO;
import com.oa.common.entity.ClaimVoucher;
import com.oa.common.entity.ClaimVoucherItem;
import com.oa.common.entity.DealRecord;
import com.oa.claim.service.ClaimVoucherService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/claim")
public class ClaimVoucherController {

    @Resource
    private ClaimVoucherService claimVoucherService;

    @GetMapping("/items")
    public Result<Map<String, Object>> getItems() {
        Map<String, Object> data = new HashMap<>();
        data.put("items", Constants.getItems());
        return Result.success(data);
    }

    @PostMapping
    public Result<Void> save(@RequestBody ClaimVoucherDTO dto) {
        claimVoucherService.save(dto);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Map<String, Object>> getById(@PathVariable Integer id) {
        Map<String, Object> data = new HashMap<>();
        data.put("claimVoucher", claimVoucherService.getById(id));
        data.put("items", claimVoucherService.listItemsByClaimVoucherId(id));
        data.put("records", claimVoucherService.listRecordsByClaimVoucherId(id));
        return Result.success(data);
    }

    @GetMapping("/self/{createId}")
    public Result<List<ClaimVoucher>> listByCreateId(@PathVariable String createId) {
        return Result.success(claimVoucherService.listByCreateId(createId));
    }

    @GetMapping("/deal/{nextDealId}")
    public Result<List<ClaimVoucher>> listByNextDealId(@PathVariable String nextDealId) {
        return Result.success(claimVoucherService.listByNextDealId(nextDealId));
    }

    @PutMapping
    public Result<Void> update(@RequestBody ClaimVoucherDTO dto) {
        claimVoucherService.update(dto);
        return Result.success();
    }

    @PostMapping("/submit/{id}")
    public Result<Void> submit(@PathVariable Integer id) {
        claimVoucherService.submit(id);
        return Result.success();
    }

    @PostMapping("/deal")
    public Result<Void> deal(@RequestBody DealRecord dealRecord) {
        claimVoucherService.deal(dealRecord);
        return Result.success();
    }
}
