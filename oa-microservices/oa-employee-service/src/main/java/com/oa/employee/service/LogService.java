package com.oa.employee.service;

import com.oa.common.entity.Log;

import java.util.List;

public interface LogService {

    void save(Log log);

    void removeById(Integer id);

    List<Log> listByEmployeeId(String employeeId);
}
