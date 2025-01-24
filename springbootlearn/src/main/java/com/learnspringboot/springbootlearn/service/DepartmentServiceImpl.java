package com.learnspringboot.springbootlearn.service;

import com.learnspringboot.springbootlearn.entity.Department;
import com.learnspringboot.springbootlearn.error.DepartmentNameNotFoundException;
import com.learnspringboot.springbootlearn.error.DepartmentNotFoundException;
import com.learnspringboot.springbootlearn.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (!department.isPresent()) {
            throw new DepartmentNotFoundException("Department Not Available");
        }
        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmnetId) {
        departmentRepository.deleteById(departmnetId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department depDB = departmentRepository.findById(departmentId).get();

        if (Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())) {
            depDB.setDepartmentName(department.getDepartmentName());
        }

        if (Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())) {
            depDB.setDepartmentCode(department.getDepartmentCode());
        }

        if (Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())) {
            depDB.setDepartmentAddress(department.getDepartmentAddress());
        }
        return departmentRepository.save(depDB);
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) throws DepartmentNameNotFoundException {
        Department department = departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
        if (departmentName == null || departmentName.trim().isEmpty()) {
            throw new DepartmentNameNotFoundException("Department Name should Not Be Empty");
        }
        if (department ==null) {
            throw new DepartmentNameNotFoundException("DepartmentName Not Available");
        }
        return department;
    }
}