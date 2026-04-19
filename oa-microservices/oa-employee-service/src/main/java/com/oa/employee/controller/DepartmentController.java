package com.oa.employee.controller;

import com.oa.common.common.Result;
import com.oa.common.entity.Department;
import com.oa.employee.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    @GetMapping("/list")
    public Result<List<Department>> list() {
        return Result.success(departmentService.listAll());
    }

    @GetMapping("/{id}")
    public Result<Department> getById(@PathVariable String id) {
        return Result.success(departmentService.getById(id));
    }

    @PostMapping
    public Result<Void> save(@RequestBody Department department) {
        departmentService.save(department);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody Department department) {
        departmentService.update(department);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> remove(@PathVariable String id) {
        departmentService.removeById(id);
        return Result.success();
    }
}
