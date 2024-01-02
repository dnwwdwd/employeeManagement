package com.godfrey.mapper;

import com.godfrey.dto.LogDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface LogMapper {
    int insertLogOperation(@Param("roleId") int roleId, @Param("user_name") String user_name, @Param("operation") String operation);
    List<LogDTO> selectAllLogOperations();
}
