package com.learnspringboot.springbootlearn.repository;

import com.learnspringboot.springbootlearn.entity.Department;
import com.learnspringboot.springbootlearn.error.DepartmentNotFoundException;
import com.learnspringboot.springbootlearn.service.DepartmentService;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department department =
                Department.builder()
                        .departmentName("ECE")
                        .departmentCode("ECE-01")
                        .departmentAddress("Hyderabad")
                        .build();
        entityManager.persist(department);
        departmentService.saveDepartment(department);
    }

//    @Test
//    public void whenFindById_ThenReturnDepartment(){
//        Department department = departmentRepository.findById(1L).get();
//        assertEquals(department.getDepartmentName(),"Mech");
//    }

    @Test
    public void whenFindByName_ThenReturnDepartment(){
        String departmentName = "ECE";
        Department department = departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
        assertEquals(department.getDepartmentName(),"ECE");
    }

    //Implement all the test and research all the other ways
//    @Test
//    public void whenIdISCorrect_ThenDeleteDepartment() throws DepartmentNotFoundException {
//        Long departmentId = 1L;
//        departmentRepository.deleteById(departmentId);
//        //Department result = departmentService.fetchDepartmentById(1l);
//        //System.out.println(result);
//        //assertNull(result,"Department Not Available");
//        verify(departmentRepository,times(1)).deleteById(1L);
//    }

}