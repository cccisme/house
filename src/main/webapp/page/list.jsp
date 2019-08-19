<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
<script language="JavaScript" src="../admin/js/jquery-1.8.3.js"></script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=navbar class=wrap>
  <FORM id=sform method=post action=getBorswerHouse>
    <input type="hidden" name="page" id="savepage" value="1">
    <div>
      标题：<INPUT class=text type=text name=title value="${condition.title}">
      类型：<SELECT name=typeid id="typeId">
              <OPTION selected value="">不限</OPTION>
           </SELECT>
      区域：<SELECT name=districtid id="district_id">
              <OPTION selected value="">不限</OPTION>
           </SELECT>
      街道：<SELECT name=streetid id="street_id">
              <OPTION selected value="">不限</OPTION>
           </SELECT>
      价格：<INPUT class=text type=text name=startprice value="${condition.startprice}">-<INPUT class=text type=text name=endprice value="${condition.endprice}">元
      <LABEL class=ui-blue><INPUT value=搜索房屋 type=submit></LABEL>
    </div>
  </FORM>
</DIV>
<DIV class="main wrap">
  <c:if test="${!empty pageInfo.list}">
    <TABLE class=house-list>
      <TBODY>
      <c:forEach items="${pageInfo.list}" var="h">
      <TR>
        <TD class=house-thumb><span><A href="details.jsp" target="_blank"><img src="http://localhost:81/${h.path}" width="100" height="75" alt=""></a></span></TD>
        <TD>
          <DL>
            <DT><A href="details.jsp" target="_blank">${h.title}</A></DT>
            <DD>${h.dname}==${h.sname},${h.floorage}平米<BR>联系方式：${h.contact}</DD></DL></TD>
        <TD class=house-type>${h.tname}</TD>
        <TD class=house-price><SPAN>${h.price}</SPAN>元/月</TD></TR>
      </c:forEach>
      </TBODY>
    </TABLE>
    <DIV class=pager>
    <UL>
      <LI class=current><A href="javascript:gopage(1)">首页</A></LI>
      <LI><A href="javascript:gopage(${pageInfo.prePage==0?1:pageInfo.prePage})">上一页</A></LI>
      <LI><A href="javascript:gopage(${pageInfo.nextPage==0?pageInfo.pageNum:pageInfo.nextPage})">下一页</A></LI>
      <LI><A href="javascript:gopage(${pageInfo.pages})">末页</A></LI></UL>
      <SPAN class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV></DIV>
    <DIV id=footer class=wrap>
  </c:if>
      <c:if test="${empty pageInfo.list}">
        <center style="color: red;  font-size: 24px;">暂无出租房信息</center>
      </c:if>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY>
<script language="JavaScript">
  function gopage(page){
    $("#savepage").val(page);
    $("#sform").submit();
  }
  $(function () {//加载事件
    //户型
    $.post("qtgetType",null,function (data) {
      for (var i = 0; i<data.length;i++){
        //创建option
        var node=$("<option value="+data[i].id+">"+data[i].name+"</option>");
        $("#typeId").append(node);
      }
      $("#typeId").val(${condition.typeid})
    },"json");
    //区域
    $.post("getqtdistrict",null,function (data) {
      for (var i = 0; i<data.length;i++){
        //创建option
        var node=$("<option value="+data[i].id+">"+data[i].name+"</option>");
        $("#district_id").append(node);
      }
      $("#district_id").val(${condition.districtid});
      loadStreet(${condition.districtid});
    },"json");

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
      $("#street_id").val(${condition.streetid})
    },"json")
  }
</script>
</HTML>
