package com.oa.sysapi.service;

import com.oa.sysapi.entity.Log;

import java.util.List;

public interface LogService {

    void save(Log log);

    void removeById(Integer id);

    List<Log> listByEmployeeId(String employeeId);
}
