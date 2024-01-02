package com.godfrey.service;

import com.godfrey.dto.DepartmentDTO;
import com.godfrey.dto.DepartmentDTO1;
import com.godfrey.pojo.Department;

import java.util.List;

/**
 * description : DepartmentService
 *
 * @author godfrey
 * @since 2020-05-27
 */
public interface DepartmentService {
    List<DepartmentDTO> selectAllDepartment();
    DepartmentDTO selectDepartmentById(Integer depId);
    int addDepartment(DepartmentDTO departmentDTO);
    int updateDepartment(DepartmentDTO departmentDTO);
    int deleteDepartment(Integer depId);
}
