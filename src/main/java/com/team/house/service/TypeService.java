package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.util.Page;

import java.util.List;

public interface TypeService {
    //分页查询
    PageInfo<Type> getAllType(Page page);

    int addType(Type type);

    //通过id查单条
    Type getSingleType(Integer id);

    //修改
    int upType(Type type);

    //批量删除
    int delMoreType(List<Integer> ids);

    int delType(Integer id);


    //前台方法   添加出租房
    List<Type> qtgetType();

    //查所有类型不分页的
    List<Type> getsyType();
}
