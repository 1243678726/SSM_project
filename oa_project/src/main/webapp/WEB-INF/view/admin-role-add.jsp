<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico">
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>新建网站角色 - 管理员管理 - H-ui.admin v3.1</title>
<meta name="keywords" content="H-ui.admin ">
<meta name="description" content=" ">
</head>
<body>
	<article class="page-container">
		<form action="" method="post" class="form form-horizontal"
			id="form-admin-role-add">
			<input type="hidden" value="${role.roeId}" name="roeId">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>角色名称：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${role.name}"
						placeholder="" id="roleName" name="name">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">备注：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${role.remark}"
						placeholder="" id="" name="remark">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">网站角色：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<!-- ztree是基于列表的，所以必须有一个基本的ul，并且有id和class -->
					<ul id="permissionTree" class="ztree"></ul>

				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<button type="submit" class="btn btn-success radius"
						id="admin-role-save" name="admin-role-save">
						<i class="icon-ok"></i> 确定
					</button>
				</div>
			</div>
		</form>
	</article>

	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="${ctx}/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/lib/layer/2.4/layer.js"></script>
	<link rel="stylesheet" href="${ctx}/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx}/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="${ctx}/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="${ctx}/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
	<script type="text/javascript"
		src="${ctx}/lib/jquery.validation/1.14.0/validate-methods.js"></script>
	<script type="text/javascript"
		src="${ctx}/lib/jquery.validation/1.14.0/messages_zh.js"></script>
	<script type="text/javascript">
	
		
	var setting = {
			/* 是否支持复选框 */
			check : {
				enable : true
			},
			/* data ：ztree的数据的相关配置
				simpleData:是否支持简单格式的数据
			 */
			data : {
				simpleData : {
					enable : true
				}
			},
			
			async : {
				enable : true,
				url : "${ctx}/role/getAllPermissions.do"
			},
			/* 异步加载成功以后的设置 */
			callback : {
				onAsyncSuccess : zTreeOnAsyncSuccess
			}
		};

		function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
			
			//获取zTree对象
			var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
			treeObj.expandAll(true);
			//提交id 请求权限数据，
			var roleId = ${role.roeId};
			$.get("${ctx}/role/getPermissionIdsByRoleId.do",{roleId:roleId},function(result){
				console.log(result);
				var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
				for(var i=0;i<result.length;i++){
					var permissionId=result[i];
					//获取指定id的节点，此节点其实就是当前角色用户的权限对应的那个节点
					var node = treeObj.getNodeByParam("id",permissionId);
					if(node.children==null){
						treeObj.checkNode(node, true, true);
					}
				}
				
			})
		}

		//3.初始化ztree
		$(function() {

			$.fn.zTree.init($("#permissionTree"), setting);
		
		});
	
	//提交保存的方法
	$('#admin-role-save').click(function(){
		var formdata =	$('#form-admin-role-add').serialize();
		//1.获取zTree对象（树对象包含当前所有节点的所有数据）
			var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
			
			//2.获取选中的所有节点
			var nodes = treeObj.getCheckedNodes(true);
			
			//声明数组，封装权限id
			var permissionIds = [];
			for (var i = 0; i < nodes.length; i++) {
				var node = nodes[i];
				var permissionId = node.id;
				//将数据添加到permissionIds数组中
				permissionIds.push(permissionId);
			}
			formdata=formdata = formdata+"&permissionIds="+permissionIds;
			$.post("${ctx}/role/update.do",formdata,function(result){
				if(result.id==1){
					layer.msg("删除成功",{icon:1,time:1000});
					
				}
				
			})
			//获取当前弹出层索引
			var index = parent.layer.getFrameIndex(window.name);
			//让父层页面重新刷新一下（重新加载一下）
			window.parent.location.reload();
			//关闭当前弹出层
			parent.layer.close(index);
			
	})
		

		$(function() {
			$(".permission-list dt input:checkbox").click(
					function() {
						$(this).closest("dl").find("dd input:checkbox").prop(
								"checked", $(this).prop("checked"));
					});
			$(".permission-list2 dd input:checkbox")
					.click(
							function() {
								var l = $(this).parent().parent().find(
										"input:checked").length;
								var l2 = $(this).parents(".permission-list")
										.find(".permission-list2 dd").find(
												"input:checked").length;
								if ($(this).prop("checked")) {
									$(this).closest("dl").find(
											"dt input:checkbox").prop(
											"checked", true);
									$(this).parents(".permission-list").find(
											"dt").first()
											.find("input:checkbox").prop(
													"checked", true);
								} else {
									if (l == 0) {
										$(this).closest("dl").find(
												"dt input:checkbox").prop(
												"checked", false);
									}
									if (l2 == 0) {
										$(this).parents(".permission-list")
												.find("dt").first().find(
														"input:checkbox").prop(
														"checked", false);
									}
								}
							});

			$("#form-admin-role-add").validate({
				rules : {
					roleName : {
						required : true,
					},
				},
				onkeyup : false,
				focusCleanup : true,
				success : "valid",
				submitHandler : function(form) {
					$(form).ajaxSubmit();
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
	</script>
	<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>