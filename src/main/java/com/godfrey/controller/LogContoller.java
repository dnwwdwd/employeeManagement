package com.godfrey.controller;

import com.godfrey.dto.LogDTO;
import com.godfrey.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LogContoller {
    @Autowired
    private LogService logService;
    @GetMapping("/log")
    public String logList(Model model){
        List<LogDTO> logDTOS = logService.selectAllLogOperations();
        model.addAttribute("logs", logDTOS);
        return "log/logList.html";
    }
}
