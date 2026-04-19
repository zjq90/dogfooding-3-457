package com.oa.sysapi.controller;

import com.oa.sysapi.common.Result;
import com.oa.sysapi.config.JwtUtil;
import com.oa.sysapi.dto.ChangePasswordDTO;
import com.oa.sysapi.dto.LoginDTO;
import com.oa.sysapi.entity.Employee;
import com.oa.sysapi.service.AuthService;
import com.oa.sysapi.service.LogService;
import com.oa.sysapi.entity.Log;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Resource
    private AuthService authService;

    @Resource
    private LogService logService;

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody LoginDTO loginDTO) {
        Employee employee = authService.login(loginDTO.getId(), loginDTO.getPassword());
        if (employee == null) {
            return Result.error("工号或密码错误");
        }

        Log log = new Log();
        log.setEmployeeId(employee.getId());
        log.setOperationTime(new Date());
        log.setOperation("login");
        logService.save(log);

        String token = JwtUtil.generateToken(employee.getId());

        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("employee", employee);
        return Result.success(data);
    }

    @PostMapping("/change-password")
    public Result<Void> changePassword(@RequestBody ChangePasswordDTO dto, HttpServletRequest request) {
        String employeeId = (String) request.getAttribute("currentEmployeeId");
        Employee employee = authService.login(employeeId, dto.getOldPassword());
        if (employee == null) {
            return Result.error("原密码错误");
        }
        if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
            return Result.error("两次输入的密码不一致");
        }
        authService.changePassword(employeeId, dto.getNewPassword());

        Log log = new Log();
        log.setEmployeeId(employeeId);
        log.setOperationTime(new Date());
        log.setOperation("changePassword");
        logService.save(log);

        return Result.success();
    }

    @GetMapping("/info")
    public Result<Employee> getInfo(HttpServletRequest request) {
        String employeeId = (String) request.getAttribute("currentEmployeeId");
        Employee employee = authService.login(employeeId, "");
        return Result.success(employee);
    }
}
