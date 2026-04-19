package com.oa.sysapi.controller;

import com.oa.sysapi.common.Result;
import com.oa.sysapi.entity.Log;
import com.oa.sysapi.service.LogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    @Resource
    private LogService logService;

    @GetMapping
    public Result<List<Log>> list(HttpServletRequest request) {
        String employeeId = (String) request.getAttribute("currentEmployeeId");
        return Result.success(logService.listByEmployeeId(employeeId));
    }

    @DeleteMapping("/{id}")
    public Result<Void> remove(@PathVariable Integer id) {
        logService.removeById(id);
        return Result.success();
    }
}
