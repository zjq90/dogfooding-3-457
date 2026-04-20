package com.oa.employee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oa.employee.entity.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> selectAll();
}
