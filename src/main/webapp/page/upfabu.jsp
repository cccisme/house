<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script language="JavaScript" src="../admin/js/jquery-1.8.3.js"></script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新房屋信息发布</DT>
  <DD class=past>修改房屋信息</DD></DL>
<DIV class=box>
<FORM id=add_action method=post name=add.action 
action=/page/updateHouse enctype="multipart/form-data">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD class=field>标　　题：<input type="hidden" name="id" value="${house.id}">
    </TD>
    <TD><INPUT id=add_action_title class=text type=text name=title value="${house.title}"> </TD></TR>
  <TR>
    <TD class=field>户　　型：</TD>
    <TD>
      <SELECT class=text name=typeId id="typeId">
        <option value="">请选择</option>
      </SELECT>
    </TD>
  </TR>
  <TR>
    <TD class=field>面　　积：</TD>
    <TD><INPUT id=add_action_floorage class=text type=text value="${house.floorage}" name=floorage></TD></TR>
  <TR>
    <TD class=field>价　　格：</TD>
    <TD><INPUT id=add_action_price class=text type=text name=price value="${house.price}"> </TD></TR>
  <TR>
    <TD class=field>发布日期：</TD>
    <TD><INPUT class=text type=date name=pubdate value="<fmt:formatDate value="${house.pubdate}" pattern="yyyy-MM-dd"/>"></TD></TR>
  <TR>
    <TD class=field>位　　置：</TD>
    <TD>
      区：<SELECT class=text id="district_id" name=district_id" value="${house.did}">
             <OPTION  value="">请选择</OPTION>
         </SELECT>
        街：<SELECT class=text name=streetId id="street_id" value="${house.streetId}">
            <OPTION  value="">请选择</OPTION>
      </SELECT>
    </TD>
  </TR>
  <TR>
    <TD class=field>联系方式：</TD>
    <TD><INPUT id=add_action_contact class=text type=text name=contact value="${house.contact}"> </TD>
  </TR>
  <TR>
    <TD class=field>图片：<img src="http://localhost:81/${house.path}" width="60" height="60"></TD>
    <TD><INPUT id=addtp class=text type=file name=pfile>
      <input type="hidden" value="${house.path}" name="oldPath">
    </TD>
  </TR>
  <TR>
    <TD class=field>详细信息：</TD>
    <TD><TEXTAREA name=description>${house.description}</TEXTAREA></TD></TR></TBODY></TABLE>
<DIV class=buttons><INPUT value=立即修改 type=submit>
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV>
</BODY>
<script language="JavaScript">
  $(function () {//加载事件
    //户型
     $.post("qtgetType",null,function (data) {
        for (var i = 0; i<data.length;i++){
          //创建option
          var node=$("<option value="+data[i].id+">"+data[i].name+"</option>");
          $("#typeId").append(node);
          $("#typeId").val(${house.typeId})
        }
     },"json");
     //区域
    $.post("getqtdistrict",null,function (data) {
      for (var i = 0; i<data.length;i++){
        //创建option
        var node=$("<option value="+data[i].id+">"+data[i].name+"</option>");
        $("#district_id").append(node);
        $("#district_id").val(${house.did})
      }
    },"json");

    //加载街道
    loadStreet(${house.did});
    $("#street_id").val(${house.streetId});    //设置街道的选中项
      //给区域列表添加改变事件
    $("#district_id").change(function () {
      loadStreet($(this).val());    //重新加载街道
  })});
    function loadStreet(did) {
      $.post("getjd",{"did":did},function (data) {
        //清空原有数据
        $("#street_id>option:gt(0)").remove();
        for (var i = 0; i < data.length; i++){
          //使用$()工厂函数创建标签
          var node=$("<option value="+data[i].id+">"+data[i].name+"</option>");
          //将创建的标签添加到下拉列表
          $("#street_id").append(node);
        }
        $("#street_id").val(${house.streetId});
      },"json")
    }
</script>
</HTML>
