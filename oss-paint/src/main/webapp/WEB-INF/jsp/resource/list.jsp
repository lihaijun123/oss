<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>所有资源列表</title>
		<!-- Ext Css -->
		<link rel="stylesheet" type="text/css" href="/css/focus3d.css" />
		<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" />
		<!-- Ext Js Lib -->
		<script type="text/javascript" src="/extjs/ext-base.js"></script>
		<script type="text/javascript" src="/extjs/ext-all.js"></script>
		<!-- User Defined Js -->
		<script src="extjs/style.js" type="text/javascript"></script>
		<script src="extjs/validate_form.js" type="text/javascript"></script>
	</head>
	<body>
		<div class="table-responsive">
			<table class="table table-hover">
				<caption class="x-panel-header">
					所有资源列表
				</caption>
				<thead>
					<tr >
						<th>资源编号</th>
						<th>资源名称</th>
						<th>描述</th>
						<th align="center">
							<a href="/resource.do?method=menuAdd">添加</a>
						</th>
					</tr>
				</thead>
				<c:forEach var="resources" items="${menuResourcesList}">
					<tr>
						<td>
							${resources.resourceId}
						</td>
						<td title="${resources.resourceName }">
							${resources.resourceName }
						</td>
						<td>
							${resources.description }
						</td>
						<c:if test="${resources.resourceId ne 0 }">
						<td title="${resources.resourceName }">
							<a href="resource.do?method=details&resourceId=${resources.resourceId }&url=menu">查看</a>&nbsp;&nbsp;
							<a href="resource.do?method=menuModify&resourceId=${resources.resourceId }">修改</a>&nbsp;&nbsp;
							<a href="resource.do?method=delete&resourceId=${resources.resourceId }">刪除</a>
							<a title="${resources.resourceName }" href="resource.do?method=exportSql&resourceId=${resources.resourceId }">导出sql</a>
						</td>
						</c:if>
						<c:if test="${resources.resourceId eq 0 }">
							<td>&nbsp;</td>
						</c:if>
					</tr>
				</c:forEach>

				<!-- **************************** 工作台 type-6 ****************************************************-->
				<tr class="success">
					<td colspan="3">首页资源</td>
					<td>
						<font size="2"><a href="/resource.do?method=workspaceAdd">添加</a></font>
					</td>
				</tr>

				<c:forEach var="workspace" items="${workspaceResourcesList}">
					<tr>
						<td>
							${workspace.resourceId}
						</td>
						<td>
							${workspace.resourceName }
						</td>
						<td>
							${workspace.resourceDescription }
						</td>
						<td >
							<a href="resource.do?method=details&resourceId=${workspace.resourceId }">查看</a>&nbsp;&nbsp;
							<a href="resource.do?method=workspaceModify&resourceId=${workspace.resourceId }">修改</a>&nbsp;&nbsp;
							<a href="resource.do?method=delete&resourceId=${workspace.resourceId }">刪除</a>
						</td>
					</tr>
				</c:forEach>
		<!--form內容結束-->
	</body>
</html>