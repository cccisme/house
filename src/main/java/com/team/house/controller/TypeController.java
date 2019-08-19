package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.service.TypeService;
import com.team.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin")   //表示后所有的控制器请求都在/admin目录下
public class TypeController {
    @Autowired
    private TypeService typeService;
    //分页查询
    @RequestMapping("/getType")
    @ResponseBody
    public HashMap<String, Object> getType(Page page){
        PageInfo<Type> pageInfo = typeService.getAllType(page);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    //添加
    @RequestMapping("/addType")
    @ResponseBody
    public String addType(Type type){
       //调用业务
       int temp=typeService.addType(type);
       return "{\"result\":"+temp+"}";
    }


    //修改查单条
    @RequestMapping("/getSingleType")
    @ResponseBody
    public Type addDistrict(Integer id){
         return typeService.getSingleType(id);
    }

    //修改
    @RequestMapping("/upType")
    @ResponseBody
      public String upType(Type type){
        int i = typeService.upType(type);
        return "{\"result\":"+i+"}";
    }

    //删除
    @RequestMapping("/delType")
    @ResponseBody
    public String delType(Integer id){
        int temp=typeService.delType(id);
        return "{\"result\":" + temp + "}";
    }

    //批量删除
    @RequestMapping("/delMoreType")
    @ResponseBody
    public String delMoreType(String id){
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
        int temp = typeService.delMoreType(list);
        return "{\"result\":" + temp + "}";

    }
}
