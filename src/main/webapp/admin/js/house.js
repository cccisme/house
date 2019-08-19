$(function(){
    //使用datagrid显示区域
    $('#dg').datagrid({
        toolbar: "#ToolBar",  //显示工具栏
        title:"未审核房屋信息",
        url:'getHouseNoPass',  //服务器地址
        pagination:true,  //启用分页
        pageList:[3,6,9,15,20], //设置每页大小
        pageSize:3, //每页三条
        columns:[[
            {field:'ck',checkbox:true,width:100,align:'left'},
            {field:'id',title:'编号',width:100,align:'left'},
            {field:'title',title:'标题',width:100,align:'left'},
            {field:'price',title:'价格',width:100,align:'left'},
            {field:'pubdate',title:'发布日期',width:100,align:'left'},
            {field:'contact',title:'联系方式',width:100,align:'left'},
            {field:'floorage',title:'面积',width:100,align:'left'},
            {field:'dname',title:'区域名称',width:100,align:'left'},
            {field:'sname',title:'街道名称',width:100,align:'left'},
            {field:'tname',title:'类型',width:100,align:'left'},
            {field:'opt',title:'操作',width:100,align:'left',
                formatter:function(value,row,index){
                    //发送同步请求
                    // return "<a href=\"delDistrict?id="+row.id+"\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a>";
                    //发送异步请求Ajax
                    return "<a href=\"javascript:pass("+row.id+")\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">审核</a>";

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


/*审核出租房*/
    function pass(id){
        $.messager.confirm('提示框', '确定审核通过么?', function(r){
            if (r){
                $.post("upHousetgispass",{"id":id},function(data){
                    if(data.result==1){
                        //实现datagrid的刷新
                        $('#dg').datagrid('reload');
                        $.messager.alert('提示框','审核成功！^_^','info');
                    }else{
                        $.messager.alert('提示框','审核失败！^_^','info');
                    }

                },"json");
            }else{
                $.messager.alert('提示框','想好再点，可以吗！^_^','info');
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
