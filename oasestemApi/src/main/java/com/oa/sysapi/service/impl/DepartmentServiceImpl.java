package com.oa.sysapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.oa.sysapi.entity.Department;
import com.oa.sysapi.mapper.DepartmentMapper;
import com.oa.sysapi.service.DepartmentService;
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
