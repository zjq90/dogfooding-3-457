package com.oa.auth.service.impl;

import com.oa.auth.entity.Employee;
import com.oa.auth.mapper.EmployeeMapper;
import com.oa.auth.service.AuthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public Employee login(String id, String password) {
        Employee employee = employeeMapper.selectWithDepartment(id);
        if (employee == null) {
            return null;
        }
        if (!employee.getPassword().equals(password)) {
            return null;
        }
        return employee;
    }

    @Override
    public void changePassword(String id, String newPassword) {
        employeeMapper.updatePassword(id, newPassword);
    }
}
