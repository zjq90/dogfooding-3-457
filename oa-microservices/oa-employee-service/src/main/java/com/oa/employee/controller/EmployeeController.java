package com.oa.employee.controller;

import com.oa.common.common.Constants;
import com.oa.common.common.Result;
import com.oa.common.entity.Employee;
import com.oa.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @GetMapping
    public Result<List<Employee>> list() {
        return Result.success(employeeService.listAll());
    }

    @GetMapping("/{id}")
    public Result<Employee> getById(@PathVariable String id) {
        return Result.success(employeeService.getByIdWithDepartment(id));
    }

    @PostMapping
    public Result<Void> save(@RequestBody Employee employee) {
        employeeService.save(employee);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody Employee employee) {
        employeeService.update(employee);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> remove(@PathVariable String id) {
        employeeService.removeById(id);
        return Result.success();
    }

    @GetMapping("/posts")
    public Result<Map<String, Object>> getPosts() {
        Map<String, Object> data = new HashMap<>();
        data.put("posts", Constants.getPosts());
        return Result.success(data);
    }

    @GetMapping("/dealers")
    public Result<List<Employee>> getDealers(@RequestParam String departmentId, @RequestParam String post) {
        return Result.success(employeeService.listByDepartmentAndPost(departmentId, post));
    }
}
