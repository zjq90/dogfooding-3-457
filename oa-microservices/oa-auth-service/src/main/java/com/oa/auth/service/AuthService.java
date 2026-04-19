package com.oa.auth.service;

import com.oa.common.entity.Employee;

public interface AuthService {

    Employee login(String id, String password);

    void changePassword(String employeeId, String newPassword);
}
