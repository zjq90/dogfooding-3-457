package com.oa.auth.service.impl;

import com.oa.auth.mapper.EmployeeMapper;
import com.oa.auth.service.AuthService;
import com.oa.common.entity.Employee;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public Employee login(String id, String password) {
        Employee employee = employeeMapper.selectWithDepartment(id);
        if (employee != null && employee.getPassword().equals(password)) {
            return employee;
        }
        return null;
    }

    @Override
    public void changePassword(String employeeId, String newPassword) {
        Employee employee = employeeMapper.selectById(employeeId);
        employee.setPassword(newPassword);
        employeeMapper.updateById(employee);
    }
}
