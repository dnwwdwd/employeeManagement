package com.godfrey.service.impl;

/**
 * description : DepartmentServiceImpl
 *
 * @author godfrey
 * @since 2020-05-27
 */

import com.godfrey.dto.DepartmentDTO;
import com.godfrey.dto.DepartmentDTO1;
import com.godfrey.mapper.DepartmentMapper;
import com.godfrey.pojo.Department;
import com.godfrey.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentDTO> selectAllDepartment() {
        return departmentMapper.selectAllDepartment();
    }

    @Override
    public DepartmentDTO selectDepartmentById(Integer depId){
        return departmentMapper.selectDepartmentById(depId);
    }

    @Override
    public int addDepartment(DepartmentDTO departmentDTO) {
        return departmentMapper.addDepartment(departmentDTO);
    }

    @Override
    public int updateDepartment(DepartmentDTO departmentDTO) {
        Integer depId = departmentDTO.getDepId();
        Integer newDepId = departmentDTO.getNewDepId();
        String department_name = departmentDTO.getDepartment_name();
        return departmentMapper.updateDepartment(depId, newDepId, department_name);
    }

    @Override
    public int deleteDepartment(Integer id) {
        return departmentMapper.deleteDepartment(id);
    }
}