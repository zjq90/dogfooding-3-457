package com.oa.starter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oa.common.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

    Employee selectWithDepartment(@Param("id") String id);

    List<Employee> selectAllWithDepartment();

    List<Employee> selectByDepartmentAndPost(@Param("departmentId") String departmentId, @Param("post") String post);
}
