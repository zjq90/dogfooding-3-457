package com.oa.employee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oa.employee.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmployeeMapper extends BaseMapper<Employee> {

    @Select("SELECT e.*, d.name as department_name, d.address as department_address " +
            "FROM employee e LEFT JOIN department d ON e.department_id = d.id WHERE e.id = #{id}")
    Employee selectWithDepartment(@Param("id") String id);

    @Select("SELECT e.*, d.name as department_name, d.address as department_address " +
            "FROM employee e LEFT JOIN department d ON e.department_id = d.id")
    List<Employee> selectAllWithDepartment();

    @Select("SELECT e.*, d.name as department_name, d.address as department_address " +
            "FROM employee e LEFT JOIN department d ON e.department_id = d.id " +
            "WHERE e.department_id = #{departmentId} AND e.post = #{post}")
    List<Employee> selectByDepartmentAndPost(@Param("departmentId") String departmentId, @Param("post") String post);
}
