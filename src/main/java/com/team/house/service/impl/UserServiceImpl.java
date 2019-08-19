package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.entity.UsersExample;
import com.team.house.mapper.UsersMapper;
import com.team.house.service.UserService;
import com.team.house.util.MD5Utils;
import com.team.house.util.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public PageInfo<Users> getAllUser(UserCondition condition) {

        PageHelper.startPage(condition.getPage(),condition.getRows());
        //添加条件
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andIsadminEqualTo(1);
        //添加搜索条件
        if (condition.getName()!=null&&condition.getName()!=""){
            criteria.andNameLike("%"+condition.getName()+"%");
        }
        if (condition.getTelephone()!=null&&condition.getName()!=""){
            criteria.andTelephoneLike("%"+condition.getTelephone()+"%");
        }
        if (condition.getStartAge()!=null&&condition.getName()!=""){
            criteria.andAgeGreaterThanOrEqualTo(condition.getStartAge());
        }
        if (condition.getEndAge()!=null&&condition.getName()!=""){
            criteria.andAgeLessThanOrEqualTo(condition.getEndAge());
        }
        List<Users> list = usersMapper.selectByExample(usersExample);
        PageInfo pageInfo=new PageInfo(list);
        return pageInfo;
    }

    @Override
    public int addUsers(Users users) {
        return usersMapper.insertSelective(users);
    }

    @Override
    public Users getSingleUser(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }

    @Override
    public int upUser(Users users) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andIdEqualTo(users.getId());
        return usersMapper.updateByExampleSelective(users,usersExample);
    }

    @Override
    public int deluser(Integer id) {
        return usersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delMoreUsers(List<Integer> list) {
        return usersMapper.delMoreUsers(list);
    }





    //前台方法
    @Override
    public int jcUsername(String name) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(name);
        List<Users> users = usersMapper.selectByExample(usersExample);
        return users.size();
    }

    @Override
    public int addptUser(Users users) {
        //设置为注册用户    可以选择在数据库中给此字段设置默认值：0
        users.setIsadmin(0);

        //在数据库保存密码是，不要用明文
        //给用户注册的密码进行md5加密：确保数据的安全性
        String passwordEncrypt = MD5Utils.md5Encrypt(users.getPassword());
        users.setPassword(passwordEncrypt);

        return usersMapper.insertSelective(users);
    }

    @Override
    public Users login(String name, String password) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        List<Users> users = usersMapper.selectByExample(usersExample);
        if (users.size()==0){
            return null;
        }else {
            return users.get(0);
        }
    }
}
