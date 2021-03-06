$(function(){
    //使用datagrid显示区域
    $('#dg').datagrid({
        toolbar: "#ToolBar",  //显示工具栏
        title:"管理员信息",
        url:'getuser',  //服务器地址
        pagination:true,  //启用分页
        pageList:[3,6,9,15,20], //设置每页大小
        pageSize:3, //每页三条
        columns:[[
            {field:'ck',checkbox:true,width:100,align:'left'},
            {field:'id',title:'编号',width:100,align:'left'},
            {field:'name',title:'姓名',width:100,align:'left'},
            {field:'telephone',title:'电话',width:100,align:'left'},
            {field:'age',title:'年龄',width:100,align:'left'},
            {field:'opt',title:'操作',width:100,align:'left',
                formatter:function(value,row,index){
                    //发送同步请求
                    // return "<a href=\"delDistrict?id="+row.id+"\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a>";
                    //发送异步请求Ajax
                    return "<a href=\"javascript:Deluser("+row.id+")\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a>";

                }}
        ]]
    });
});
/!*打开对话框*!/
function Add(id,title){
    $("#"+id).dialog("open").dialog("setTitle",title);
}

/*关闭对话框*/
function CloseDialog(id){
    $("#"+id).dialog("close");
}
/*实现区域添加*/
function SaveDialog(){
    //传统使用ajax技术实现添加
    //1.获取表单对象的数据  2.使用ajax方法|post方法发送异步请求 3.处理回调用函数

    //使用easyui提交表单
    $('#addDialogForm').form('submit', {
        url:"addUsers",
        success:function(data){
            data=$.parseJSON(data);
            if(data.result==1){
                //关闭对话框
                $("#AddDialog").dialog("close");
                $('#dg').datagrid('reload');
                $.messager.alert('提示框','添加成功！^_^','info');
            }else{
                $.messager.alert('提示框','添加失败！^_^','info');
            }
        }
    });

}
/*打开修改的对话框*/
function ModifyBySelect(){
    //获取datagrid选中行  返回的数组
    var SelectRows = $("#dg").datagrid('getSelections');
    if(SelectRows.length!=1){
        $.messager.alert('提示框','你还没有选中行，或者选择了多行.','info');
        return;
    }
    //打开窗口
    $("#upDialog").dialog("open").dialog("setTitle",">>>>修改区域");

    //将数据回显示到表单中  键需和表单对象名称相同
    //$('#addDialogForm').form('load',json对象:{键:值,键:值..});
    //1.获取选中行对象进行回显示 {"id":xxx,"name":sss}
    //var row=SelectRows[0];
    //$('#upDialogForm').form('load',row);

    //2.通过主键从数据库查询单条对象进行回显(当datagrid的行数据无法满足表单显示时)
    //发送异步请求获取对象进行回显
    var row=SelectRows[0];
    $.post("getSingleUser",{"id":row.id},function(data){
        //回显
        $('#upDialogForm').form('load',data);
    },"json");
}


/*实现修改代码*/
function upDaveDialog(){
    //使用easyui提交表单
    $('#upDialogForm').form('submit', {
        url:"upUser",
        success:function(data){  //注意:返回的是json字符串
            //将json字符串转化为json对象
            data=$.parseJSON(data);
            if(data.result==1){
                //关闭对话框
                $("#upDialog").dialog("close");
                //实现datagrid的刷新
                $('#dg').datagrid('reload');
                $.messager.alert('提示框','修改成功！^_^','info');
            }else{
                $.messager.alert('提示框','修改失败！^_^','info');
            }
        }
    });
}
/*实现删除功能*/
function  Deluser(obj) {  //传的是编号
    //发送异步请求实现删除

    $.messager.confirm('提示框', '你真的想把我删除掉吗？我不能离开你', function(r){
        if (r){
            $.post("deluser",{"id":obj},function(data){
                if(data.result==1){
                    //实现datagrid的刷新
                    $('#dg').datagrid('reload');
                }else{
                    $.messager.alert('提示框','删除失败！^_^','info');
                }

            },"json");
        }else{
            $.messager.alert('提示框','想好再点，可以吗！^_^','info');
        }

    });
}
/*批量删除*/
function DeleteMoreById(){
    //获取选中行
    var SelectRows = $("#dg").datagrid('getSelections');
    if(SelectRows.length==0){
        $.messager.alert('提示框','你还没有选中行^_^','info');
        return;
    }

    //确认删除
    $.messager.confirm('提示框', '你真的想把我删除掉吗？我不能离开你',function(flag){
        if(flag){  //为true实现删除
            // 调用服务器接口进行删除
            //获取选中项的值
            var value="";
            for(var i=0;i<SelectRows.length;i++){
                value=value+SelectRows[i].id+",";
            }
            value=value.substring(0,value.length-1);  //去除最后的逗号

            //发送异步请求到服务器
            $.post("delMoreUsers",{"id":value},function(data){
                if(data.result>0){
                    //实现datagrid的刷新
                    $.messager.alert('提示框','删除成功！^_^','info');
                    $('#dg').datagrid('reload');
                }else{
                    $.messager.alert('提示框','删除失败！^_^','info');
                }
            },"json");
        }
    });

}
/*实现Datagrid的搜索功能*/
function search() {
    //实现搜索查询
    //datagrid的load方法是重新加载，它会将查询条件，随着页码、页大小
    //一起发送到当前控件所指定的服务器地址进行处理
    //$("#dg").datagrid("load","传查询条件：{键：值，键：值}")
    var ssname=$("#ssname").val();
    var sstel=$("#sstel").val();
    var ssstartage=$("#ssstartage").val();
    var ssendage=$("#ssendage").val();
    $("#dg").datagrid("load",{"name":ssname,"telephone":sstel,"startAge":ssstartage,"endAge":ssendage});
}
