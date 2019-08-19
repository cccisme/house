package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.entity.StreetExample;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.StreetService;
import com.team.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StreetServiceImpl implements StreetService {
    @Autowired
    private StreetMapper streetMapper;

    @Override
    public PageInfo<Street> getStreetByDid(Integer id,Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        StreetExample streetExample = new StreetExample();
        StreetExample.Criteria criteria = streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(id);
        List<Street> list = streetMapper.selectByExample(streetExample);
        PageInfo pageInfo=new PageInfo(list);
        return pageInfo;
    }

    @Override
    public int addStreet(Street street) {
        return streetMapper.insertSelective(street);
    }

    @Override
    public int delStreet(Integer id) {
        return streetMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int UpStreet(Street street) {
        StreetExample streetExample = new StreetExample();
        StreetExample.Criteria criteria = streetExample.createCriteria();
        criteria.andIdEqualTo(street.getId());
        return streetMapper.updateByExampleSelective(street,streetExample);
    }



    //前台方法    发布出租房  查询区域下的街道
    @Override
    public List<Street> getjd(Integer did) {
        StreetExample streetExample = new StreetExample();
        StreetExample.Criteria criteria = streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(did);
        return streetMapper.selectByExample(streetExample);
    }
}
