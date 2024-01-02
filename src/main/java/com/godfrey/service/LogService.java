package com.godfrey.service;

import com.godfrey.dto.LogDTO;
import com.godfrey.pojo.Log;
import java.util.List;

public interface LogService {
    int insertLogOperation(Log log);
    List<LogDTO> selectAllLogOperations();
}
