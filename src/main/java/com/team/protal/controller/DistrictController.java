package com.team.protal.controller;

import com.team.house.entity.District;
import com.team.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("districtController2")
@RequestMapping("/page")   //表示后所有的控制器请求都在/admin目录下
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @RequestMapping("/getqtdistrict")
    @ResponseBody
   public List<District> getqtdistrict(){
        return districtService.getqtdistrict();
    }
}

