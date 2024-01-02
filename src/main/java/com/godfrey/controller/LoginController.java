package com.godfrey.controller;

import com.godfrey.pojo.User;
import com.godfrey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * description : 登录Controller
 *
 * @author godfrey
 * @since 2020-05-26
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public String login(@RequestParam("username")String username,
                        @RequestParam("password")String password,
                        HttpSession session,
                        Model model){
        User user = userService.selectPasswordByName(username, password);
        if ( user != null){
            //登录成功！
            session.setAttribute("user",user);
            int roleId = user.getRoleId();
            model.addAttribute("roleId", roleId);
            if(roleId == 0){
                return "redirect:/empMain.html";
            }
            return "redirect:/main.html";
        }else {
            //登录失败！存放错误信息
            model.addAttribute("msg","用户名或密码错误");
            return "index";
        }
    }

    @GetMapping("/user/loginOut")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "redirect:/index.html";
    }
}
