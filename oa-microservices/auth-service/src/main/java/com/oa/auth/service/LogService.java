package com.oa.auth.service;

import com.oa.auth.entity.Log;

import java.util.List;

public interface LogService {

    void save(Log log);

    void removeById(Integer id);

    List<Log> listByEmployeeId(String employeeId);
}
