<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ctx}/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctx}/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/lib/jquery/1.9.1/jquery.min.js"></script>


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
	}

	//3.初始化ztree
	$(function() {

		$.fn.zTree.init($("#permissionTree"), setting);

	});
</script>

</head>


<body>
	Ztree的demo
	<br>


	<!-- ztree是基于列表的，所以必须有一个基本的ul，并且有id和class -->
	<ul id="permissionTree" class="ztree"></ul>
</body>
</html>