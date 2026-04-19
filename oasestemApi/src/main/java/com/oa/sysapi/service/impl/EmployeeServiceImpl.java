package com.oa.sysapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.oa.sysapi.entity.Employee;
import com.oa.sysapi.mapper.EmployeeMapper;
import com.oa.sysapi.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public void save(Employee employee) {
        employee.setPassword("123456");
        employeeMapper.insert(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeMapper.updateById(employee);
    }

    @Override
    public void removeById(String id) {
        employeeMapper.deleteById(id);
    }

    @Override
    public Employee getById(String id) {
        return employeeMapper.selectById(id);
    }

    @Override
    public Employee getByIdWithDepartment(String id) {
        return employeeMapper.selectWithDepartment(id);
    }

    @Override
    public List<Employee> listAll() {
        return employeeMapper.selectAllWithDepartment();
    }

    @Override
    public List<Employee> listByDepartmentAndPost(String departmentId, String post) {
        return employeeMapper.selectByDepartmentAndPost(departmentId, post);
    }
}
