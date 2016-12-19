<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>角色列表</title>
		<!-- Ext Css -->
		<link rel="stylesheet" type="text/css" href="/resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css" href="/css/oss.css" />
		<!-- Ext Js Lib -->
		<script type="text/javascript" src="/extjs/ext-base.js"></script>
		<script type="text/javascript" src="/extjs/ext-all.js"></script>
		<!-- User Defined Js -->
		<script src="extjs/style.js" type="text/javascript"></script>
		<script src="extjs/validate_form.js" type="text/javascript"></script>

	</head>
	<body>
		<span style="color: red;">${message }</span>
		<table width="100%">
			<caption class="x-panel-header">
				角色列表
			</caption>
			<tr>
				<td align="left">
					<b>角色名称</b>
				</td>
				<td align="left">
					<b>描述</b>
				</td>
				<td align="center" nowrap>
					<b><a href="role.do?method=add">添加</a></b>
				</td>
			</tr>
			<c:forEach var="roles" items="${OssAdminRoleList}">
				<tr>

					<td align="left">
						<a href="role.do?method=details&roleId=${roles.roleId }">${roles.roleName }</a>
					</td>
					<td align="left">
						${roles.description }
					</td>
					<td align="center" nowrap>
						<a href="role.do?method=modify&roleId=${roles.roleId }">修改</a>&nbsp;&nbsp;
						<a id="delRole_${roles.roleId }" roleId=${roles.roleId } href="javascript:void()">删除</a>&nbsp;&nbsp;
						<a href="role.do?method=grantMenu&roleId=${roles.roleId }">菜单授权</a>&nbsp;&nbsp;
						<a href="role.do?method=grantWorkspace&roleId=${roles.roleId }">工作台授权</a>&nbsp;&nbsp;
						<!--
						<a href="role.do?method=delete&roleId=${roles.roleId }">刪除</a>&nbsp;&nbsp;
						<a href="role.do?method=grantMethod&roleId=${roles.roleId }">方法授权</a>
						 -->
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
	<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
		$("a[id^='delRole_']").click(function(){
			if(confirm("确认删除？")){
				$(this).attr("href", "role.do?method=delete&roleId=" + $(this).attr("roleId"));
			}
		});
	</script>
</html>