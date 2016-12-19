<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
		<title>部门列表</title>
		<!-- Ext Css -->
		<link rel="stylesheet" type="text/css" href="/resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css" href="/css/oss.css" />
		<!-- Ext Js Lib -->
		<script type="text/javascript" src="/extjs/ext-base.js"></script>
		<!-- User Defined Js -->
		<script type="text/javascript" src="/extjs/tree_linknode_ui.js"></script>
		<script type="text/javascript" src="/extjs/link_tree.js"></script>
		<script src="extjs/style.js" type="text/javascript"></script>
		<script src="extjs/validate_form.js" type="text/javascript"></script>

	</head>
	<body>
		<span style="color: red;">${message }</span>
		<table width="50%">
		<caption class="x-panel-header">
			部门列表
		</caption>
		<tr>
			<td >
				<b>名称</b>
			</td>
			<td>
				<b>
					<a href="department.do?method=add">添加</a>
				</b>
			</td>
		</tr>
		<c:forEach var="currentDepart" items="${allDepartment}">
			<tr>
				<td>
					<a href="department.do?method=details&departmentId=${currentDepart.departmentId }">${currentDepart.departmentName }</a>&nbsp;&nbsp;
				</td>
				<td>
					<a href="department.do?method=modify&departmentId=${currentDepart.departmentId }">修改</a>
					<a id="delDepartment_${currentDepart.departmentId }" departmentId=${currentDepart.departmentId } href="javascript:void()">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>

</script>
	</body>
	<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">

		$("a[id^='delDepartment_']").click(function(){
			if(confirm("确认删除？")){
				$(this).attr("href", "department.do?method=delete&departmentId=" + $(this).attr("departmentId"));
			}
		});

	</script>
</html>