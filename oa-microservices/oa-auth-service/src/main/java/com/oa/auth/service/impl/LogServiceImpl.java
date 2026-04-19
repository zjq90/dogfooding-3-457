package com.oa.auth.service.impl;

import com.oa.auth.mapper.LogMapper;
import com.oa.auth.service.LogService;
import com.oa.common.entity.Log;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    @Override
    public void save(Log log) {
        logMapper.insert(log);
    }

    @Override
    public void removeById(Integer id) {
        logMapper.deleteById(id);
    }

    @Override
    public List<Log> listByEmployeeId(String employeeId) {
        return logMapper.selectByEmployeeId(employeeId);
    }
}
