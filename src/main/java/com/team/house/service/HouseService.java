package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.util.HouseCondition;
import com.team.house.util.Page;

public interface HouseService {
    int addHouse(House house);

    PageInfo<House> getHouseByUserId(Integer id, Page page);

    House Upcdthx(String id);

    int updateHouse(House house);

    int goscdate(House house);

    //查询未审核的出租房
    PageInfo<House> getHouseByIsPass(Integer ispass, Page page);

    int upHouseispass(String id,Integer ispass);

    //查询所有的出租房
     PageInfo<House> getBorswerHouse(HouseCondition condition);

}
