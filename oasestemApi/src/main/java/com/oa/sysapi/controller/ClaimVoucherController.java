package com.oa.sysapi.controller;

import com.oa.sysapi.common.Constants;
import com.oa.sysapi.common.Result;
import com.oa.sysapi.dto.ClaimVoucherDTO;
import com.oa.sysapi.entity.ClaimVoucher;
import com.oa.sysapi.entity.ClaimVoucherItem;
import com.oa.sysapi.entity.DealRecord;
import com.oa.sysapi.service.ClaimVoucherService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/claim-vouchers")
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
    public Result<Void> save(@RequestBody ClaimVoucherDTO dto, HttpServletRequest request) {
        String employeeId = (String) request.getAttribute("currentEmployeeId");
        dto.getClaimVoucher().setCreateId(employeeId);
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

    @GetMapping("/self")
    public Result<List<ClaimVoucher>> listSelf(HttpServletRequest request) {
        String employeeId = (String) request.getAttribute("currentEmployeeId");
        return Result.success(claimVoucherService.listByCreateId(employeeId));
    }

    @GetMapping("/deal")
    public Result<List<ClaimVoucher>> listDeal(HttpServletRequest request) {
        String employeeId = (String) request.getAttribute("currentEmployeeId");
        return Result.success(claimVoucherService.listByNextDealId(employeeId));
    }

    @PutMapping
    public Result<Void> update(@RequestBody ClaimVoucherDTO dto, HttpServletRequest request) {
        String employeeId = (String) request.getAttribute("currentEmployeeId");
        dto.getClaimVoucher().setCreateId(employeeId);
        claimVoucherService.update(dto);
        return Result.success();
    }

    @PostMapping("/{id}/submit")
    public Result<Void> submit(@PathVariable Integer id) {
        claimVoucherService.submit(id);
        return Result.success();
    }

    @PostMapping("/deal")
    public Result<Void> deal(@RequestBody DealRecord dealRecord, HttpServletRequest request) {
        String employeeId = (String) request.getAttribute("currentEmployeeId");
        dealRecord.setDealId(employeeId);
        claimVoucherService.deal(dealRecord);
        return Result.success();
    }
}
