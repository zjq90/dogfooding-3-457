package com.oa.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oa.auth.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface EmployeeMapper extends BaseMapper<Employee> {

    @Select("SELECT e.*, d.name as department_name FROM employee e " +
            "LEFT JOIN department d ON e.department_id = d.id WHERE e.id = #{id}")
    Employee selectWithDepartment(@Param("id") String id);

    @Update("UPDATE employee SET password = #{password} WHERE id = #{id}")
    void updatePassword(@Param("id") String id, @Param("password") String password);
}
