package com.oa.auth.service.impl;

import com.oa.common.entity.Log;
import com.oa.auth.mapper.LogMapper;
import com.oa.auth.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    @Override
    public void save(Log log) {
        logMapper.insert(log);
    }
}
