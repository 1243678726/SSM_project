<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>角色管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 角色管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="cl pd-5 bg-1 bk-gray"> <span class="l"> <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" href="javascript:;" onclick="admin_role_add()"><i class="Hui-iconfont">&#xe600;</i> 添加角色</a> </span> <span class="r">共有数据：<strong id="total"></strong> 条</span> </div>
	<table class="table table-border table-bordered table-hover table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="6">角色管理</th>
			</tr>
			<tr class="text-c">
				<th width="25"><input type="checkbox" id="all" value="" name=""></th>
				<th width="40">ID</th>
				<th width="200">角色名</th>
				<th>用户列表</th>
				<th width="300">描述</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody id="userTbody">
			<tr class="text-c">
				<td><input type="checkbox" value="" name=""></td>
				<td>1</td>
				<td>超级管理员</td>
				<td><a href="#">admin</a></td>
				<td>拥有至高无上的权利</td>
				<td class="f-14"><a title="编辑" href="javascript:;" onclick="admin_role_edit('角色编辑','admin-role-add.html','1')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a title="删除" href="javascript:;" onclick="admin_role_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
			</tr>
		
		</tbody>
		
	</table>
	
	<div id="userPage"></div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/laypage/1.2/laypage.js"></script>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript">



function showData(users){
	var html = "";
	
	for(var i = 0;i<users.length;i++){
		//分别取出没一行数据
		var u = users[i];
		html +="<tr class='text-c'>";
		html +="<td><input type='checkbox' id='delete' value='"+u.id+"' name='delete'></td>";
		html +="<td>"+u.id+"</td>";
		html +="<td>"+u.usercode+"</td>";
		html +="<td>"+u.username+"</td>";
		html +="<td>"+u.roleId+"</td>";
		html +="<td class='td-status'><span class='label label-success radius'>已启用</span></td>";
		html +="<td class='td-manage'><a title='编辑' href='javascript:;'onclick='admin_role_edit("+u.id+")' class='ml-5'";
		html +="style='text-decoration: none'><i class='Hui-iconfont'>&#xe6df;</i></a> ";
		html +="<a title='删除' href='javascript:;' onclick='admin_role_del("+u.id+")' class='ml-5'style='text-decoration: none'><i class='Hui-iconfont'>&#xe6e2;</i></a></td>";
		html +="</tr>";
	}
	//添加表格中
	$("#userTbody").html(html);
}


//异步分页
//url :数据请求地址
//curr :当前页
function menulists(url,curr){
  var pageNum = curr || 1; //向服务端传的参数
  $.ajax({
		method:"get",
		url:url,
		data:{pageNum:pageNum},
		success:function(data){
			//将数据整理添加的表格中
			showData(data.list);
			//设置总数
			$("#total").text(data.total);
			$("#page").text(data.pageNum);
			console.log(data);
			console.log(data.list);
			//显示分页
	        laypage({
	          cont: 'userPage', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
	          pages: data.pages, //通过后台拿到的总页数
	          skip: true, //是否开启跳页
	          skin: '#6665fe',
	          curr: data.pageNum || 1, //当前页
	          jump: function(obj, first){ //触发分页后的回调
	        	 //console.log(obj,first);
	            if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
	            	menulists("${pageContext.request.contextPath}/user/list.do",obj.curr);
	            }
	          }
			})
		}
	})
}

$(function(){
	menulists("${pageContext.request.contextPath}/user/list.do",1);
	
});

   
/*管理员-角色-添加*/
function admin_role_add(){
	layer_show("添加用户","${pageContext.request.contextPath}/user/insertPage.do","800","600");
}
/*管理员-角色-编辑*/
function admin_role_edit(uid){
	layer_show("修改用户","${pageContext.request.contextPath}/user/edit.do?id="+uid,"800","600");
}
/* function admin_role_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
} */
/*管理员-角色-删除*/
function admin_role_del(id){
	
	layer.confirm('角色删除须谨慎，确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '${pageContext.request.contextPath}/user/delete.do?id='+id,
			dataType: 'json',
			success: function(data){
				/* $(obj).parents("tr").remove(); */
				layer.msg(data.message,{icon:1,time:1000});
				
			},
			
			error:function(data) {
				console.log(data.msg);
			},
			
		});
		var curr = $(".laypage_curr").text();
 		window.menulists("${pageContext.request.contextPath}/user/list.do",curr);
 	});
}


function datadel(){
		//批量获取要删除的id
		layer.confirm("角色删除须谨慎，确认要删除吗？",function(index){
			var chk_value = [];//定义一个数组
	        //利用将name等于delete的多选按钮得到
	        $("input[name='delete']:checked").each(function() {
	        //将选中额数据存到数组里
	        chk_value.push($(this).val());
	        console.log(chk_value)
	        });
	        if (chk_value.length == 0) {
	            alert("你还没有选择任何内容！");
	        }
	        if (chk_value.length > 0) {
	        	//传统方法
	           // location.href = "${pageContext.request.contextPath}/user/allDelete.do?chk_value=" + chk_value;
	        	$.ajax({  
	        	    url: '${pageContext.request.contextPath}/user/allDelete.do',  
	        	    data: {"ids":chk_value},
	        	    dataType:"json",  
	        	    type: "POST",  
	        	    success: function (data) {  
	        	    	layer.msg(data.message,{icon:1,time:1000});
	        	    },  
	        	});
	        	var curr = $(".laypage_curr").text();
	     		window.menulists("${pageContext.request.contextPath}/user/list.do",curr);
	        }
		})
		
        

}



</script>
</body>
</html>