package com.oa.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oa.auth.entity.Log;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LogMapper extends BaseMapper<Log> {

    @Select("SELECT l.*, e.name as employee_name FROM log l " +
            "LEFT JOIN employee e ON l.employee_id = e.id " +
            "WHERE l.employee_id = #{employeeId} ORDER BY l.operation_time DESC")
    List<Log> selectByEmployeeId(@Param("employeeId") String employeeId);
}
