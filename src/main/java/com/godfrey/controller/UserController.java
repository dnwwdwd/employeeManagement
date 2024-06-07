package com.godfrey.controller;

import com.godfrey.dto.EmployeeDTO;
import com.godfrey.pojo.Log;
import com.godfrey.pojo.User;
import com.godfrey.service.EmployeeService;
import com.godfrey.service.LogService;
import com.godfrey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;
    @Autowired
    private EmployeeService employeeService;

    // 查询个人信息
    @GetMapping("/userInfo")
    public String UserInfo(Model model, HttpSession session){
        Object object = session.getAttribute("user");
        User user = (User) object;
        int roleId = user.getRoleId();
        model.addAttribute("userInfo", user);
        if(user != null){
            Log log = new Log(user.getRoleId(),user.getUserName(),"执行了查询个人信息操作");
            logService.insertLogOperation(log);
        }
        if(roleId == 0){
            return "user/empInfo.html";
        }
        return "user/adminInfo.html";
    }
    //同部门员工信息
    @GetMapping("/empInfoSameDep")
    public String empInfFromSameDep(Model model, HttpSession session){
        Object object= session.getAttribute("user");
        User user = (User) object;
        List<User> empInfoFromSameDep=userService.empInfoFromSameDep(user.getEmpId());
        model.addAttribute("empInfoFromSameDep", empInfoFromSameDep);
        if(!CollectionUtils.isEmpty(empInfoFromSameDep)){
            Log log = new Log(user.getRoleId(),user.getUserName(),"执行了查询同部门员工信息操作");
            logService.insertLogOperation(log);
        }
        return "user/empInfoSameDep.html";
    }
    //通过id修改员工登录信息
    @GetMapping("/updateUserInfo/{id}")
    public String toUpdateEmpInfo(@PathVariable("id") Integer id, Model model, HttpSession session){
        // 通过id查询user信息
        User userInformation = userService.userInfo(id);
        model.addAttribute("userInformation", userInformation);
        Object object = session.getAttribute("user");
        User user = (User) object;
        if(user.getRoleId() == 0){
            return "user/updateEmpInfo.html";
        }
        return "user/updateAdminInfo.html";
    }
    //通过id修改user登录信息
    @PostMapping("/updateUserInfo")
    public String updateUserInfo(User user, HttpSession session){
        int i = userService.updateUserInfo(user);
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        int roleId = loginUser.getRoleId();
        if(i>0){
            Log log=new Log(loginUser.getRoleId(),loginUser.getUserName(),"执行了修改员工登录信息操作");
            logService.insertLogOperation(log);
        }
        if(roleId == 1){
            return "redirect:/empInfoList";
        }
        return "redirect:/userInfo";
    }
    // 查询全部员工个人信息
    @GetMapping("/empInfoList")
    public String empInfoList(Model model, HttpSession session){
        List<User> empInfos = userService.selectEmpInfoList();
        model.addAttribute("empInfos", empInfos);
        if(!CollectionUtils.isEmpty(empInfos)){
            Object object = session.getAttribute("user");
            User emp = (User) object;
            Log log=new Log(emp.getRoleId(),emp.getUserName(),"执行了查询所有员工登录信息操作");
            logService.insertLogOperation(log);
        }
        return "user/empInfoList.html";
    }
    @GetMapping("/delUser/{id}")
    public String deleteUser(@PathVariable("id") int id, HttpSession session){
        int i = userService.deleteEmp(id);
        if(i>0){
            Object object = session.getAttribute("user");
            User user = (User) object;
            Log log=new Log(user.getRoleId(),user.getUserName(),"执行了删除员工登录信息操作");
            logService.insertLogOperation(log);
        }
        return "redirect:/empInfoList";
    }
    // 添加员工登录信息
    @GetMapping("/addUser")
    public String toAddUser(Model model){
        List<EmployeeDTO> employeeDTOS = employeeService.selectAllEmployeeDTO();
        model.addAttribute("employees",employeeDTOS);
        return "user/addUser.html";
    }

    @PostMapping("/addUser")
    public String addUser(User user, HttpSession session){
        int i = userService.addUser(user);
        if(i > 0){
            Object object = session.getAttribute("user");
            User loginUser = (User) object;
            Log log=new Log(loginUser.getRoleId(),loginUser.getUserName(),"执行了添加员工登录信息操作");
            logService.insertLogOperation(log);
        }
        return "redirect:/empInfoList";
    }
}
