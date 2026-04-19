package com.oa.sysapi.service;

import com.oa.sysapi.entity.Employee;

public interface AuthService {

    Employee login(String id, String password);

    void changePassword(String employeeId, String newPassword);
}
