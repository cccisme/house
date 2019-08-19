package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.util.Page;

import java.util.List;

public interface DistrictService {
    /*
 * 查询所有区域
 * @return  区域实体集合
 */

    //分页查询
    PageInfo<District> getAllDistrict(Page page);

    //添加
    int addDistrict(District district);

    //通过id查单条
    District getDistrict(Integer id);

    //修改
    int upDistrict(District district);

    //删除
    int delDistrict(Integer id);

    //批量删除
    int delMoreDistrict(List<Integer> ids);



    //前台
    List<District> qtgetDistrict();

    List<District> getqtdistrict();
}
