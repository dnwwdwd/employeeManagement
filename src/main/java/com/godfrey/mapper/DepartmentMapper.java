package com.godfrey.mapper;

import com.godfrey.dto.DepartmentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description : 部门Mapper
 *
 * @author godfrey
 * @since 2020-05-26
 */
@Mapper
@Repository
public interface DepartmentMapper {

    List<DepartmentDTO> selectAllDepartment();
    DepartmentDTO selectDepartmentById(@Param("depId") Integer depId);
    int addDepartment(DepartmentDTO departmentDTO);
    //修改一个部门信息
    int updateDepartment(@Param("depId") Integer depId, @Param("newDepId") Integer newDepId, @Param("department_name") String department_name);
    //根据id删除部门信息
    int deleteDepartment(@Param("depId") Integer depId);
}
