package com.team.protal.controller;

import com.team.house.entity.Street;
import com.team.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("streetController2")
@RequestMapping("/page")   //表示后所有的控制器请求都在/admin目录下
public class StreetController {
    @Autowired
    private StreetService streetService;

    @RequestMapping("/getjd")
    @ResponseBody
    public List<Street> getjd(Integer did){
        System.out.println(did+"--------------------------------------");
        List<Street> list=streetService.getjd(did);
        return list;
    }
}
