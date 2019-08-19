package com.team.protal.controller;

import com.team.house.entity.Users;
import com.team.house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller("usersController2")
@RequestMapping("/page")   //表示后所有的控制器请求都在/admin目录下
public class UsersController {
    @Autowired
    private UserService userService;

    //查询用户名是否存在
    @RequestMapping("/jcUsername")
    @ResponseBody
    private String jcUsername(String name){
        int i = userService.jcUsername(name);
        return "{\"result\":" + i + "}";
    }


    @RequestMapping("reg")
    public String reg(Users users){
        //调用业务实现注册
        int temp=userService.addptUser(users);
        if (temp>0){
            return "login";   //进入登入页面
        }else {
            return "regs";   //进入注册页面
        }
    }

    @RequestMapping("loginAction")
    public String loginAction(String name, String password, Model model, HttpSession session) throws Exception{
        if (name==null){
            return "login";
        }
        //调用业务实现注册
        Users user=userService.login(name,password);
        if (user==null){
            model.addAttribute("info","用户名或密码错误！");
            return "login";
        }else {
            //只要登录，使用session作用域保存登录的人
            session.setAttribute("loginInfo",user);
            //设置保存的有效时间
            session.setMaxInactiveInterval(6000);   //以秒为单位
            return "redirect:getHouse";//用户登录后的管理页
        }
    }
}
