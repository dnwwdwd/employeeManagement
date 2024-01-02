package com.godfrey.controller;

import com.godfrey.dto.DepartmentDTO;
import com.godfrey.dto.DepartmentDTO1;
import com.godfrey.pojo.Department;
import com.godfrey.pojo.Log;
import com.godfrey.pojo.User;
import com.godfrey.service.DepartmentService;
import com.godfrey.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private LogService logService;
    @GetMapping("/dep")
    public String listDepartment(Model model, HttpSession session){
        List<DepartmentDTO> departments = departmentService.selectAllDepartment();
        model.addAttribute("deps",departments);
        if(!CollectionUtils.isEmpty(departments)){
            Object object = session.getAttribute("user");
            User user = (User) object;
            Log log=new Log(user.getRoleId(),user.getUserName(),"执行了查询部门信息操作");
            logService.insertLogOperation(log);
        }
        return "department/listDepartment.html";
    }

    @GetMapping("/addDepartment")
    public String toAdd(Model model){
        //查出所有的部门，提供选择
        Collection<DepartmentDTO> departments = departmentService.selectAllDepartment();
        model.addAttribute("departments",departments);
        return "department/addDepartment.html";
    }

    @PostMapping("/addDepartment")
    public String add(DepartmentDTO departmentDTo, HttpSession session){
        //保存员工信息
        int i = departmentService.addDepartment(departmentDTo);
        if(i>0){
            Object object = session.getAttribute("user");
            User user = (User) object;
            Log log=new Log(user.getRoleId(),user.getUserName(),"执行了添加部门操作");
            logService.insertLogOperation(log);
        }
        //回到员工列表页面，可以使用redirect或者forward
        return "redirect:/dep";
    }

    @GetMapping("/dep/{id}")
    public String toUpdateEmp(@PathVariable("id") int depId, Model model){
        //根据id查出来部门
        DepartmentDTO departmentDTO = departmentService.selectDepartmentById(depId);
        model.addAttribute("dep", departmentDTO);
        //查出所有的部门，提供修改选择
        Collection<DepartmentDTO> departments = departmentService.selectAllDepartment();
        model.addAttribute("departments", departments);
        return "department/updateDepartment.html";
    }

    @PostMapping("/updateDepartment")
    public String updateEmp(DepartmentDTO departmentDTO, HttpSession session){
        int i = departmentService.updateDepartment(departmentDTO);
        if(i>0){
            Object object = session.getAttribute("user");
            User user = (User) object;
            Log log=new Log(user.getRoleId(),user.getUserName(),"执行了修改部门信息操作");
            logService.insertLogOperation(log);
        }
        //回到部门列表页面
        return "redirect:/dep";
    }

    @GetMapping("/deldep/{id}")
    public String deleteDep(@PathVariable("id") int depId, HttpSession session){
        int i = departmentService.deleteDepartment(depId);
        if(i>0){
            Object object = session.getAttribute("user");
            User user = (User) object;
            Log log=new Log(user.getRoleId(),user.getUserName(),"执行了删除部门操作");
            logService.insertLogOperation(log);
        }
        return "redirect:/dep";
    }

}
