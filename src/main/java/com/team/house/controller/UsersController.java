package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.service.UserService;
import com.team.house.util.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin")   //表示后所有的控制器请求都在/admin目录下
public class UsersController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getuser")
    @ResponseBody
    public HashMap<String, Object> getDistrict(UserCondition condition){
        PageInfo<Users> pageInfo = userService.getAllUser(condition);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    @RequestMapping("/addUsers")
    @ResponseBody
    public String addUsers(Users users){
        int temp=userService.addUsers(users);
        return "{\"result\":" + temp + "}";
    }

    //修改查单条
    @RequestMapping("/getSingleUser")
    @ResponseBody
    private Users getSingleUser(Integer id){
        return userService.getSingleUser(id);
    }

    //修改
    @RequestMapping("/upUser")
    @ResponseBody
    private String upUser(Users users){
        int i=userService.upUser(users);
        return "{\"result\":" + i + "}";
    }

    @RequestMapping("/deluser")
    @ResponseBody
    private String deluser(Integer id){
        int i=userService.deluser(id);
        return "{\"result\":" + i + "}";
    }

    @RequestMapping("/delMoreUsers")
    @ResponseBody
    private String delMoreUsers(String id){
        //接收编号，名称
        //id=1,2,3,4
        //分割字符串
        String[] arys = id.split(",");
        //转化为List
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arys.length; i++) {
            list.add(Integer.parseInt(arys[i]));
        }
        //调用业务
        int i=userService.delMoreUsers(list);
        return "{\"result\":" + i + "}";
    }
}
