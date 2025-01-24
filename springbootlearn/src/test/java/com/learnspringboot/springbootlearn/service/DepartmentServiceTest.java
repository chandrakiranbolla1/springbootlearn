package com.learnspringboot.springbootlearn.service;

import com.learnspringboot.springbootlearn.entity.Department;
import com.learnspringboot.springbootlearn.error.DepartmentNameNotFoundException;
import com.learnspringboot.springbootlearn.error.DepartmentNotFoundException;
import com.learnspringboot.springbootlearn.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;



    @BeforeEach
    void setUp() throws DepartmentNameNotFoundException {
        Department department =
                Department.builder()
                        .departmentName("IT")
                        .departmentAddress("Hyderabad")
                        .departmentCode("IT-06")
                        .departmentId(1L)
                        .build();

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT"))
                .thenReturn(department);
    }

    @Test
    void WhenValidDepartmentName_thenDepartmentShouldFound() throws DepartmentNameNotFoundException {
        String departmentName  = "IT";
        Department found = departmentService.fetchDepartmentByName(departmentName);

        assertEquals(departmentName,found.getDepartmentName());
    }

    @Test
    void WhenInValidDepartmentName_thenDepartmentNotFound()  {
        String departmentName = "";
        DepartmentNameNotFoundException thrown = assertThrows(DepartmentNameNotFoundException.class, ()-> departmentService.fetchDepartmentByName(departmentName));
        assertEquals("Department Name should Not Be Empty",thrown.getMessage());
    }

    @Test
    void WhenDepartmentIsNullthen_ThrowException(){
        String departmentName = null;
        DepartmentNameNotFoundException e = assertThrows(DepartmentNameNotFoundException.class,() -> departmentService.fetchDepartmentByName(departmentName));
        assertEquals("Department Name should Not Be Empty",e.getMessage());
    }

    @Test
    void WhenDepartmentPresent_ThenDelete() throws DepartmentNotFoundException {
        Long departmentId = 1L;
        departmentService.deleteDepartmentById(departmentId);
        verify(departmentRepository,times(1)).deleteById(departmentId);
    }

//    @Test
//    void UpdateDepeartment_WhenWithInTheseConditions(){
//        Department department = new Department();
//        department.setDepartmentAddress("IT-06");
//        department.setDepartmentAddress("Hyderabad");
//        department.setDepartmentName("EEE");
//        departmentRepository.save(department);
//
//        Long departmentId = 1L;
//        Department depDB = departmentRepository.findById(departmentId).get();
//
////        if (department != null ){
////        }
//
//        if (Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())) {
//            depDB.setDepartmentName(department.getDepartmentName());
//        }
//
//        if (Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())) {
//            depDB.setDepartmentCode(department.getDepartmentCode());
//        }
//
//        if (Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())) {
//            depDB.setDepartmentAddress(department.getDepartmentAddress());
//        }
//        Department updated = departmentRepository.save(depDB);
//
//        assertEquals(updated, depDB);
//    }


}