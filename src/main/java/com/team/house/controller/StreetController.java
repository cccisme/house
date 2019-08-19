package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.service.StreetService;
import com.team.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class StreetController {
    @Autowired
    private StreetService streetService;

    @RequestMapping("/getStreet")
    @ResponseBody
    public HashMap<String, Object> getStreet(Integer id,Page page){
        PageInfo<Street> pageInfo = streetService.getStreetByDid(id,page);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("/addStreet")
    @ResponseBody
    public String addStreet(Street street){
        int i=streetService.addStreet(street);
        return "{\"result\":" + i + "}";
    }

    @RequestMapping("/delStreet")
    @ResponseBody
    public String delStreet(Integer id){
        int i=streetService.delStreet(id);
        return "{\"result\":" + i + "}";
    }

    @RequestMapping("/UpStreet")
    @ResponseBody
    public String UpStreet(Street street){
        System.out.println(street.getName()+"******************************");
        System.out.println(street.getId()+"******************************");
        System.out.println(street.getDistrictId()+"******************************");
        int i=streetService.UpStreet(street);
        return "{\"result\":" + i + "}";
    }
}
