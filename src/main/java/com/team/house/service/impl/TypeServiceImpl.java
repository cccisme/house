package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.entity.TypeExample;
import com.team.house.mapper.TypeMapper;
import com.team.house.service.TypeService;
import com.team.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public PageInfo<Type> getAllType(Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        TypeExample typeExample = new TypeExample();
        List<Type> list = typeMapper.selectByExample(typeExample);
        PageInfo pageInfo=new PageInfo(list);
        return pageInfo;
    }

   @Override
    public int addType(Type type) {
        return typeMapper.insertSelective(type);
    }

     @Override
    public Type getSingleType(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

     @Override
    public int upType(Type type) {
         TypeExample e = new TypeExample();
         TypeExample.Criteria c = e.createCriteria();
         c.andIdEqualTo(type.getId());
        return typeMapper.updateByExampleSelective(type,e);
    }


    @Override
    public int delType(Integer id) {
        return typeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delMoreType(List<Integer> ids) {
        return typeMapper.delMoreType(ids);
    }


    //前台方法      添加出租房
    @Override
    public List<Type> qtgetType() {
        return typeMapper.selectByExample(null);
    }

    @Override
    public List<Type> getsyType() {
        return typeMapper.selectByExample(null);
    }

}
