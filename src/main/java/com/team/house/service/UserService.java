package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.util.UserCondition;

import java.util.List;

public interface UserService {
    //后台方法


    PageInfo<Users> getAllUser(UserCondition condition);

    int addUsers(Users users);

    int upUser(Users users);

    Users getSingleUser(Integer id);

    int deluser(Integer id);

    int delMoreUsers(List<Integer> list);






    //前台方法
    int jcUsername(String name);

    int addptUser(Users users);

    Users login(String name, String password);
}
