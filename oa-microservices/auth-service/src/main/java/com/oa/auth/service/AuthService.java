package com.oa.auth.service;

import com.oa.auth.entity.Employee;

public interface AuthService {

    Employee login(String id, String password);

    void changePassword(String id, String newPassword);
}
