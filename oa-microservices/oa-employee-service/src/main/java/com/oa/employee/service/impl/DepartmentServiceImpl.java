package com.oa.employee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.oa.common.entity.Department;
import com.oa.employee.mapper.DepartmentMapper;
import com.oa.employee.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public void save(Department department) {
        departmentMapper.insert(department);
    }

    @Override
    public void update(Department department) {
        departmentMapper.updateById(department);
    }

    @Override
    public void removeById(String id) {
        departmentMapper.deleteById(id);
    }

    @Override
    public Department getById(String id) {
        return departmentMapper.selectById(id);
    }

    @Override
    public List<Department> listAll() {
        return departmentMapper.selectList(new LambdaQueryWrapper<>());
    }
}
