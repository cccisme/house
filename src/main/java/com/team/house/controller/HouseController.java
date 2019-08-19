package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.service.HouseService;
import com.team.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController  //一下所有方法全部返回异步
@RequestMapping("/admin")
public class HouseController {
    @Autowired
    private HouseService houseService;


    //查询未审核的功能
    @RequestMapping("getHouseNoPass")
    public Map<String, Object> getHouseNoPass(Page page){
        PageInfo<House> pageInfo = houseService.getHouseByIsPass(0, page);
        HashMap<String, Object> map = new HashMap();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }


    //查询已审核的功能
    @RequestMapping("getHouseYesPass")
    public Map<String, Object> getHouseYesPass(Page page){
        PageInfo<House> pageInfo = houseService.getHouseByIsPass(1, page);
        HashMap<String, Object> map = new HashMap();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    //修改     通过审核
    @RequestMapping("upHousetgispass")
    public String upHousetgispass(String id){
        int i=houseService.upHouseispass(id,1);
        return "{\"result\":"+i+"}";
    }

    //修改     取消审核
    @RequestMapping("upHouseqxispass")
    public String upHouseqxispass(String id){
        int i=houseService.upHouseispass(id,0);
        return "{\"result\":"+i+"}";
    }
}
