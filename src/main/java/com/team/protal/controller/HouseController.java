package com.team.protal.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.House;
import com.team.house.entity.Type;
import com.team.house.entity.Users;
import com.team.house.service.DistrictService;
import com.team.house.service.HouseService;
import com.team.house.service.TypeService;
import com.team.house.util.HouseCondition;
import com.team.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller("houseController2")
@RequestMapping("/page")   //表示后所有的控制器请求都在/admin目录下
public class HouseController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private HouseService houseService;

    //前台方法   添加出租房
    @RequestMapping("/FaBuczf")
    public String qtgetType(Model model){
        List<Type> list=typeService.qtgetType();
        List<District> list2=districtService.qtgetDistrict();
        model.addAttribute("list",list);
        model.addAttribute("list2",list2);
        return "fabu";
    }

    //添加出租房、
    @RequestMapping("addHouse")
    public String addHouse(HttpSession session,House house, @RequestParam(name="pfile",required = false) CommonsMultipartFile pfile) throws Exception{
        //1.实现图片上传，图片在图片服务器   d:/images
            String filename=pfile.getOriginalFilename();   //上传文件名称
            String expname=filename.substring(filename.lastIndexOf("."));    //上传文件的扩展名
            String saveFileName=System.currentTimeMillis()+expname; //保存文件名称
            String path="d:/images/"+saveFileName;   //保存路径
            File saveFile = new File(path);
            pfile.transferTo(saveFile);
        //2.将输入的数据保存
            house.setId(System.currentTimeMillis()+"");    //设置编号
            //设置用户id
            Users users=(Users) session.getAttribute("loginInfo");
            house.setUserId(users.getId());
            //设置图片
            house.setPath(saveFileName);

            //house.setIsdel(0);   //如果数据有默认值可不设
            //house.setIspass(0);     //如果数据有默认值可不设
            int temp=houseService.addHouse(house);
            if (temp>0){
                return "redirect:getHouse";
            }else {
                saveFile.delete();  //如果文件上传  数据没传就删除文件
            }
        return "redirect:FaBuczf";
    }


    //查询所有用户出租房信息        只需要传page类中的page属性(页码)
    @RequestMapping("/getHouse")
    public String getHouseByUserId(HttpSession session,Page page,Model model) throws Exception{
        //获取用户编号
        Users users=(Users) session.getAttribute("loginInfo");
        PageInfo<House> pageInfo=houseService.getHouseByUserId(users.getId(),page);
        model.addAttribute("pageInfo",pageInfo);
        return "guanli";
    }

    //修改出租房
    @RequestMapping("/goUpdate")
    public String goUpdate(String id,Model model) throws Exception{
        //修改查单条回显
        House house=houseService.Upcdthx(id);
        model.addAttribute("house",house);
        return "upfabu";
    }

    //修改出租房
    @RequestMapping("/updateHouse")   //不要忘记传递主键   出租房id
    public String updateHouse(String oldPath,House house,@RequestParam(name="pfile",required = false) CommonsMultipartFile pfile) throws Exception{
        String filename = pfile.getOriginalFilename();
        if (filename.equals("")){
            //没有选择图片，不用上传新图片，数据不用更新
        }else {
            //上传图片，数据库更新   //删除原图
            //1.实现图片上传，图片在图片服务器   d:/images
            String expname=filename.substring(filename.lastIndexOf("."));    //上传文件的扩展名
            String saveFileName=System.currentTimeMillis()+expname; //保存文件名称
            String path="d:/images/"+saveFileName;   //保存路径
            File saveFile = new File(path);
            pfile.transferTo(saveFile);    //上传文件  新图

            //2.删除原有图片      还可用新图覆盖旧图达到删除的效果
            new File("d://images//"+oldPath).delete();
            house.setPath(saveFileName);
        }
        int i=houseService.updateHouse(house);
        return "redirect:getHouse";
    }


    //逻辑删除出租房goscdate
    @RequestMapping("/goscdate")
    public String goscdate(House house){
        house.setIsdel(1);
        int i = houseService.goscdate(house);
        return "redirect:getHouse";
    }


    //显示浏览所有的出租房信息
    @RequestMapping("getBorswerHouse")
    public String getBorswerHouse(HouseCondition condition, Model model) throws Exception{
        //调用业务层
        PageInfo<House> pageInfo = houseService.getBorswerHouse(condition);
        model.addAttribute("pageInfo",pageInfo);
        //回显查询条件 
        model.addAttribute("condition",condition);
        return "list";
    }
}
