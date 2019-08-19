package com.team.house.mapper;

import com.team.house.entity.House;
import com.team.house.entity.HouseExample;
import com.team.house.util.HouseCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseMapper {
    int countByExample(HouseExample example);

    int deleteByExample(HouseExample example);

    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") House record, @Param("example") HouseExample example);

    int updateByExample(@Param("record") House record, @Param("example") HouseExample example);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);


    //查询用户所有的出租房信息
    //实体类的作用：传递数据用的
    public List<House> getHouseByUserId(Integer uid);

    //查询出租房信息
    public House getHouse(String id);

    //查询用户未审核的出租房信息
    public List<House> getHouseByIsPass(Integer ispass);

    public  List<House> getBorswerHouse(HouseCondition condition);
}