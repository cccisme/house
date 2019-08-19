package com.team.house.mapper;

import com.team.house.entity.Street;
import com.team.house.entity.StreetExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StreetMapper {
    int countByExample(StreetExample example);

    int deleteByExample(StreetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Street record);

    int insertSelective(Street record);

    List<Street> selectByExample(StreetExample example);

    Street selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Street record, @Param("example") StreetExample example);

    int updateByExample(@Param("record") Street record, @Param("example") StreetExample example);

    int updateByPrimaryKeySelective(Street record);

    int updateByPrimaryKey(Street record);

    int deleteStreetByDid(List<Integer> list);
}