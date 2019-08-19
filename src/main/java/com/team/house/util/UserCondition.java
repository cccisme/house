package com.team.house.util;


//查询条件   //2.继承page 类只为添加页码和页大小
public class UserCondition extends Page {
    //类型全用包装类、封装类
    private String name;
    private String telephone;
    private Integer startAge;
    private Integer endAge;

    /*1.添加分页的相关属性
    * private Integer page;
    * private Integer rows;
     setter和getter方法
    * */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getStartAge() {
        return startAge;
    }

    public void setStartAge(Integer startAge) {
        this.startAge = startAge;
    }

    public Integer getEndAge() {
        return endAge;
    }

    public void setEndAge(Integer endAge) {
        this.endAge = endAge;
    }
}
