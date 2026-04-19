package com.oa.employee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oa.common.entity.Department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {
}
