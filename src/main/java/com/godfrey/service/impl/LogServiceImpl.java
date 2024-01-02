package com.godfrey.service.impl;

import com.godfrey.dto.LogDTO;
import com.godfrey.mapper.LogMapper;
import com.godfrey.pojo.Log;
import com.godfrey.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;
    @Override
    public int insertLogOperation(Log log) {
        return logMapper.insertLogOperation(log.getRoleId(), log.getUser_name(), log.getOperation());
    }

    @Override
    public List<LogDTO> selectAllLogOperations() {
        return logMapper.selectAllLogOperations();
    }
}
