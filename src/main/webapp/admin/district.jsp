<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>区域管理</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
    <script src="js/jquery-1.8.3.js"></script>
    <!--jquery.easyui.min.js包含了easyUI中的所有插件-->
    <script src="js/jquery.easyui.min.js"></script>
    <script language="JavaScript" src="js/district.js"></script>
</head>
<body>
<!--显示区域-->
<table id="dg"></table>

<!--工具栏-->
<div id="ToolBar">
    <div style="height: 40px;">
        <a href="javascript:Add('AddDialog','>>>添加区域')" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a> <a
            href="javascript:ModifyBySelect()" class="easyui-linkbutton"
            iconCls="icon-edit" plain="true">修改</a> <a
            href="javascript:DeleteMoreById()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">批量删除</a>
    </div>
</div>
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="addDialogForm" method="post">
        <table>
            <tr>
                <td>区域名称:</td>
                <td><input type="text" class="easyui-validatebox" required
                           name="name" id="bname" /></td>
            </tr>

        </table>
    </form>
</div>
<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a>
    <a href="javascript:CloseDialog('AddDialog')"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
<!--修改的对话框-->
<div id="upDialog" class="easyui-dialog" buttons="#upDialogButtons2"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="upDialogForm"  method="post">
        <table>
            <tr>
                <td>区域编号:</td>
                <td><input type="text"  readonly style="border: none" class="easyui-validatebox" required
                           name="id" id="xgid" />
                </td>
            </tr>
            <tr>
                <td>区域名称:</td>
                <td><input type="text" class="easyui-validatebox" required
                           name="name" id="xgname" />
                </td>
            </tr>

        </table>
    </form>
</div>
<div id="upDialogButtons2">
    <a href="javascript:upDaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">更新</a>
    <a href="javascript:CloseDialog('upDialog')"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
<!--定义保存对话框中的按钮-->
<div id="upDialogButtons">
    <a href="javascript:closeck()" class="easyui-linkbutton"
       iconCls="icon-ok">确定</a>
    <a href="javascript:closeck()"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
<!--显示街道的窗口-->
<div id="showStreetDialog" class="easyui-dialog" buttons="#upDialogButtons"
     style="width: 500px; height: 350px; padding: 10px 20px;" closed="true">
    <table id="streetdg"></table>
    <hr/>
    <form id="addStreetForm"  method="post">
        <table>
            <tr>
                <td>街道名称:</td>
                <td><input type="text" class="easyui-validatebox" required:false name="name" id="jdname" /></td>
                <td><a href="javascript:addstreet()" class="easyui-linkbutton" iconCls="icon-ok">添加</a></td>
            </tr>
            <tr>
                <td><input type="hidden" class="easyui-validatebox" required name="districtId" id="jdDid"/></td>
            </tr>
        </table>
    </form>
</div>
<%--<script type="text/javascript" src="./js/MyEasyUICRUD.js"></script>--%>
</body>
</html>
