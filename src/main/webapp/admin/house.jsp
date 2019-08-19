<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/25
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" District="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" District="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" District="text/css" href="easyUI/css/demo.css">
    <script src="js/jquery-1.8.3.js"></script>
    <!--jquery.easyui.min.js包含了easyUI中的所有插件-->
    <script src="js/jquery.easyui.min.js"></script>
    <script language="JavaScript" src="js/house.js"></script>
</head>
<body>
   <!--显示区域-->
   <table id="dg"></table>

   <!--工具栏-->
   <div id="ToolBar">
       <div style="height: 40px;">
           <a href="javascript:DeleteMoreById()" class="easyui-linkbutton"
               iconCls="icon-remove" plain="true">批量审核</a>
       </div>
       <div>
           标题<input type="text" id="ssname" name="name">
           区域名称:<input type="text" id="sstel" name="telephone">
           街道名称:<input type="text" id="ssstartage" name="startAge">
           类型名称:<input type="text" id="ssendage" name="endAge">
           <a href="javascript:search()" class="easyui-linkbutton"
              iconCls="icon-search" plain="true">搜索</a>
       </div>
   </div>

    <!--添加的对话框-->
   <div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
         style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
       <form id="addDialogForm"  method="post">
           <table>
               <tr>
                   <td>姓名:</td>
                   <td><input type="text" class="easyui-validatebox" required name="name" id="bname" /></td>
               </tr>
               <tr>
                   <td>电话:</td>
                   <td><input type="text" class="easyui-validatebox" required name="telephone" id="btel" /></td>
               </tr>
               <tr>
                   <td>年龄:</td>
                   <td><input type="text" class="easyui-validatebox" required name="age" id="bage" /></td>
               </tr>
               <tr>
                   <td><input type="hidden" class="easyui-validatebox" required name="isadmin" id="bvip" value="1" /></td>
               </tr>
           </table>
       </form>
   </div>

   <!--定义保存对话框中的按钮-->
   <div id="AddDialogButtons">
       <a href="javascript:SaveDialog()" class="easyui-linkbutton"
          iconCls="icon-ok">保存</a>
       <a href="javascript:CloseDialog('AddDialog')"
          class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
   </div>


   <!--修改的对话框-->
   <div id="upDialog" class="easyui-dialog" buttons="#upDialogButtons"
        style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
       <form id="upDialogForm"  method="post">
           <table>
               <tr>
                   <td>编号:</td>
                   <td><input type="text"  readonly style="border: none" class="easyui-validatebox" required
                              name="id" id="xgid" />
                   </td>
               </tr>
               <tr>
                   <td>姓名:</td>
                   <td><input type="text" class="easyui-validatebox" required name="name" id="xgname" />
                   </td>
               </tr>
               <tr>
                   <td>电话:</td>
                   <td><input type="text" class="easyui-validatebox" required name="telephone" id="xgtel" />
                   </td>
               </tr>
               <tr>
                   <td>年龄:</td>
                   <td><input type="text" class="easyui-validatebox" required name="age" id="xgage" />
                   </td>
               </tr>
           </table>
       </form>
   </div>

   <!--定义保存对话框中的按钮-->
   <div id="upDialogButtons">
       <a href="javascript:upDaveDialog()" class="easyui-linkbutton"
          iconCls="icon-ok">更新</a>
       <a href="javascript:CloseDialog('upDialog')"
          class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
   </div>
</body>
</html>
