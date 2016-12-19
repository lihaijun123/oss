<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>uitool列表</title>
		<!-- Ext Css -->
		<link rel="stylesheet" type="text/css" href="/css/focus3d.css" />
		<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" />
		<!-- Ext Js Lib -->
		<script type="text/javascript" src="/extjs/ext-base.js"></script>
		<script type="text/javascript" src="/extjs/ext-all.js"></script>
		<!-- User Defined Js -->
		<script src="extjs/style.js" type="text/javascript"></script>
		<script src="extjs/validate_form.js" type="text/javascript"></script>
		<script type="text/javascript">

			function deleteByfuncId(funcId){
				if(window.confirm("是否删除?")){
					if(funcId > 0){
						window.location.href = "/uitool.do?method=delete&funcId=" + funcId;
					}
				}
			}
		</script>

	</head>
	<body>

	<div class="table-responsive">
		<table class="table table-hover">
			<caption>
				功能列表
			</caption>
			<thead>
				<tr>
				<th>funcID</th>
				<th>功能名称</th>
				<th>样式文件</th>
				<th>脚本文件</th>
				<th>自定义jsp文件</th>
				<!--
				 <td><a href="/uitool.do?method=add">添加</a></td>

				 -->
				 <th>操作</th>
				 </tr>
			</thead>
			<tbody>
			<c:forEach var="func" items="${funcDisplayInfoList}">
				<tr>
					<td >
						${func.funcId }
					</td>
					<td >
						${func.funcTitle }
					</td>
					<td>
						${func.displayCss }
					</td>
					<td>
						${func.displayJs }
					</td>
					<td>
						${func.displayPage }
					</td>
					<td title="${func.funcTitle }">
						<a title="${func.funcTitle }" href="/uitool.do?method=edit&funcId=${func.funcId }">
						修改
						</a>
						<a title="${func.funcTitle }" href="/uitool.do?method=table&funcId=${func.funcId }">
						配置
						</a>
						<a title="${func.funcTitle }"  href="/uitool.do?method=exportSql&funcId=${func.funcId }">
						导出SQL
						</a>
						<a title="${func.funcTitle }" href="javascript:deleteByfuncId(${func.funcId })">
						删除
						</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	</body>
</html>