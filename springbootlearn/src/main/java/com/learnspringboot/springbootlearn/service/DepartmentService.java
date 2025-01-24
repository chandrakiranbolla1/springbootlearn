package com.learnspringboot.springbootlearn.service;

import com.learnspringboot.springbootlearn.entity.Department;
import com.learnspringboot.springbootlearn.error.DepartmentNameNotFoundException;
import com.learnspringboot.springbootlearn.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {

    public Department saveDepartment(Department department);

    public List<Department> fetchDepartmentList();

    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    public void deleteDepartmentById(Long departmnetId);

    public Department updateDepartment(Long departmentId, Department department) throws IllegalAccessException;

    public Department fetchDepartmentByName(String departmentName) throws DepartmentNameNotFoundException;
}
