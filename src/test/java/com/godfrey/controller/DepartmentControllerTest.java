package com.godfrey.controller;

import com.godfrey.dto.DepartmentDTO;
import com.godfrey.dto.EmployeeDTO;
import com.godfrey.pojo.Employee;
import com.godfrey.service.DepartmentService;
import com.godfrey.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class DepartmentControllerTest {
    @Resource
    private EmployeeService employeeService;
    @Resource
    private DepartmentService departmentService;
    @Test
    public void deleteDepTest(){
        List<EmployeeDTO> employeeDTOS = employeeService.selectAllEmployeeDTO();
        System.out.println(employeeDTOS);
        Assertions.assertNotNull(employeeDTOS);
    }
    @Test
    public void selectDepartmentByIdTest(){
        DepartmentDTO departmentDTO = departmentService.selectDepartmentById(10);
        System.out.println(departmentDTO.toString());

    }
}
