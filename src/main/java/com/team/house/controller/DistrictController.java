package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.service.DistrictService;
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
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @RequestMapping("/getDistrict")
    @ResponseBody
    public HashMap<String, Object> getDistrict(Page page){
        PageInfo<District> pageInfo = districtService.getAllDistrict(page);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    @RequestMapping("/addDistrict")
    @ResponseBody
    public String  addDistrict(District district){
        //调用业务
        int temp=districtService.addDistrict(district);
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping("/getSingleDistrict")
    @ResponseBody
    public District  getSingleDistrict(Integer id){
        return districtService.getDistrict(id);
    }
    @RequestMapping("/upDistrict")
    @ResponseBody
    public String upDistrict(District district){
        int i = districtService.upDistrict(district);
        return "{\"result\":"+i+"}";
    }

    //删除
    @RequestMapping("/delDistrict")
    @ResponseBody
    public String delDistrict(Integer id){
        int temp=districtService.delDistrict(id);
        return "{\"result\":" + temp + "}";
    }


    @RequestMapping("/delMoreDistrict")
    @ResponseBody
    public String delMoreDistrict(String id){
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
        int temp = districtService.delMoreDistrict(list);
        return "{\"result\":" + temp + "}";
    }
}

