package com.oa.employee.controller;

import com.oa.common.common.Result;
import com.oa.common.entity.Log;
import com.oa.employee.service.LogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    @Resource
    private LogService logService;

    @GetMapping
    public Result<List<Log>> list(@RequestParam String employeeId) {
        return Result.success(logService.listByEmployeeId(employeeId));
    }

    @PostMapping
    public Result<Void> save(@RequestBody Log log) {
        logService.save(log);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> remove(@PathVariable Integer id) {
        logService.removeById(id);
        return Result.success();
    }
}
