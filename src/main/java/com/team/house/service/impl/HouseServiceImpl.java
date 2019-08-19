package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.mapper.HouseMapper;
import com.team.house.service.HouseService;
import com.team.house.util.HouseCondition;
import com.team.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;

    //发布出租房
    @Override
    public int addHouse(House house) {
        return houseMapper.insertSelective(house);
    }

    //显示出租房信息
    @Override
    public PageInfo<House> getHouseByUserId(Integer id, Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        List<House> house = houseMapper.getHouseByUserId(id);
        PageInfo pageInfo = new PageInfo(house);
        return pageInfo;
    }

    @Override
    public House Upcdthx(String id) {
        return houseMapper.getHouse(id);
    }

    @Override
    public int updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int goscdate(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getHouseByIsPass(Integer ispass, Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        List<House> house = houseMapper.getHouseByIsPass(ispass);
        return new PageInfo(house);
    }

    @Override
    public int upHouseispass(String id,Integer ispass) {
        House house = new House();
        house.setId(id);
        house.setIspass(ispass);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getBorswerHouse(HouseCondition condition) {
        PageHelper.startPage(condition.getPage(),condition.getRows());
        List<House> list = houseMapper.getBorswerHouse(condition);
        PageInfo<House> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
