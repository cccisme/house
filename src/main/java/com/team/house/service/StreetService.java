package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.util.Page;

import java.util.List;

public interface StreetService {
    //查询某区域下的街道
    PageInfo<Street> getStreetByDid(Integer id, Page page);

    int addStreet(Street street);

    int delStreet(Integer id);

    int UpStreet(Street street);

    List<Street> getjd(Integer did);
}
