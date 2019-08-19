package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.DistrictExample;
import com.team.house.entity.StreetExample;
import com.team.house.mapper.DistrictMapper;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.DistrictService;
import com.team.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;

    @Autowired
    private StreetMapper streetMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)//挂起事务，就是不用事务！！！！
    public PageInfo<District> getAllDistrict(Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        DistrictExample DistrictExample = new DistrictExample();
        List<District> list = districtMapper.selectByExample(DistrictExample);
        PageInfo pageInfo=new PageInfo(list);
        return pageInfo;
    }

    @Override
    public int addDistrict(District District) {
        int i = districtMapper.insertSelective(District);
        return i;
    }

    @Override
    public District getDistrict(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    @Override
    public int upDistrict(District District) {
        return districtMapper.updateByPrimaryKeySelective(District);
    }

    @Override
    public int delDistrict(Integer id) {
        StreetExample e = new StreetExample();
        StreetExample.Criteria c = e.createCriteria();
        c.andDistrictIdEqualTo(id);
        streetMapper.deleteByExample(e);
        return districtMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delMoreDistrict(List<Integer> ids) {
        int i = districtMapper.delMoreDistrict(ids);
        streetMapper.deleteStreetByDid(ids);
        return i;
    }



    //前台方法    发布出租房查询区域
    @Override
    public List<District> qtgetDistrict() {
        return districtMapper.selectByExample(null);
    }

    @Override
    public List<District> getqtdistrict() {
        return districtMapper.selectByExample(null);
    }
}

