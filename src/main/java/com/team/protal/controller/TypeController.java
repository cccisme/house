package com.team.protal.controller;

import com.team.house.entity.Type;
import com.team.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("typeController2")
@RequestMapping("/page")
//@RestController  =   @Controller  +  @ResponseBody
public class TypeController {
    @Autowired
    private TypeService typeService;

    @RequestMapping("/qtgetType")
    @ResponseBody
    public List<Type> qtgetType(){
        return typeService.getsyType();
    }
}
