package com.godfrey.controller;

import com.godfrey.dto.DepartmentDTO;
import com.godfrey.dto.EmployeeDTO;
import com.godfrey.pojo.Employee;
import com.godfrey.pojo.Log;
import com.godfrey.pojo.User;
import com.godfrey.service.DepartmentService;
import com.godfrey.service.EmployeeService;
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

/**
 * description : EmployeeController
 *
 * @author godfrey
 * @since 2020-05-26
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LogService logService;

    //查询所有员工，返回列表页面
    @GetMapping("/emp")
    public String list(Model model, HttpSession session){
        Collection<EmployeeDTO> employees = employeeService.selectAllEmployeeDTO();
        //        将结果放在请求中
        model.addAttribute("emps",employees);
        if(!CollectionUtils.isEmpty(employees)){
            Object object = session.getAttribute("user");
            User user = (User) object;
            Log log = new Log(user.getRoleId(),user.getUserName(),"执行了查询员工信息操作");
            logService.insertLogOperation(log);
        }
        return "emp/list.html";
    }

    //to员工添加页面
    @GetMapping("/add")
    public String toAdd(Model model){
        //查出所有的部门，提供选择
        Collection<DepartmentDTO> departments = departmentService.selectAllDepartment();
        model.addAttribute("departments",departments);
        return "emp/add.html";
    }
    //员工添加功能，使用post接收
    @PostMapping("/add")
    public String add(Employee employee, HttpSession session){
        //保存员工信息
        int i = employeeService.addEmployee(employee);
        if(i>0){
            Object object = session.getAttribute("user");
            User user = (User) object;
            Log log = new Log(user.getRoleId(),user.getUserName(),"执行了添加员工操作");
            logService.insertLogOperation(log);
        }
        //回到员工列表页面，可以使用redirect或者forward
        return "redirect:/emp";
    }

    //to员工修改页面
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id, Model model){
        //根据id查出来员工
        Employee employee = employeeService.selectEmployeeById(id);
        //将员工信息返回页面
        model.addAttribute("emp",employee);
        //查出所有的部门，提供修改选择
        Collection<DepartmentDTO> departments = departmentService.selectAllDepartment();
        model.addAttribute("departments",departments);
        return "emp/update.html";
    }

    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee, HttpSession session){
        int i = employeeService.updateEmployee(employee);
        //回到员工列表页面
        if(i>0){
            Object object = session.getAttribute("user");
            User user = (User) object;
            Log log = new Log(user.getRoleId(),user.getUserName(),"执行了更新员工信息操作");
            logService.insertLogOperation(log);
        }
        return "redirect:/emp";
    }

    @GetMapping("/delEmp/{id}")
    public String deleteEmp(@PathVariable("id")Integer id, HttpSession session){
        //根据id删除员工
        int i = employeeService.deleteEmployee(id);
        if(i>0){
            Object object = session.getAttribute("user");
            User user = (User) object;
            Log log = new Log(user.getRoleId(),user.getUserName(),"执行了删除员工操作");
            logService.insertLogOperation(log);
        }
        return "redirect:/emp";
    }
}
