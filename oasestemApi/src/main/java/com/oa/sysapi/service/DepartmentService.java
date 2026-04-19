package com.oa.sysapi.service;

import com.oa.sysapi.entity.Department;

import java.util.List;

public interface DepartmentService {

    void save(Department department);

    void update(Department department);

    void removeById(String id);

    Department getById(String id);

    List<Department> listAll();
}
