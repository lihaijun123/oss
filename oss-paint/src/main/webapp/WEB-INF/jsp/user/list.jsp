<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户列表</title>
		<!-- Ext Css -->
		<link rel="stylesheet" type="text/css" href="/resources/css/ext-all.css" />
		<style type="text/css">
			body { font-size: 12px; color: #000000; font-family: "verdana", "宋体"; margin:0px; SCROLLBAR-FACE-COLOR: #CFE2FC; SCROLLBAR-HIGHLIGHT-COLOR: #fcfcfc; SCROLLBAR-3DLIGHT-COLOR: #7eabf7; SCROLLBAR-ARROW-COLOR: #2F5FAE; SCROLLBAR-TRACK-COLOR: #e3f0ff; SCROLLBAR-DARKSHADOW-COLOR: #CEE1FD; border:1px solid #C4D7FF; background-color:#f4f8ff; }
		</style>
		<!-- Ext Js Lib -->
		<script type="text/javascript" src="/extjs/ext-base.js"></script>
		<script type="text/javascript" src="/extjs/ext-all.js"></script>
		<!-- User Defined Js -->
		<script src="extjs/style.js" type="text/javascript"></script>
		<script src="extjs/validate_form.js" type="text/javascript"></script>

	</head>
	<body>
		<form:form modelAttribute="ossAdminDepartment" action="/user.do">
			<input type="hidden" name="method" value="query"/>
			<b>用户列表</b>
			<div style="float: left;width:100%; margin-top: 20px;margin-left: 50px;">
			<table width="100%" >
				<tr>
					<td align="right" width="50">
						部门︰
					</td>
					<td width="100">
						<form:select path="departmentId">
							<form:option value="null">请选择...</form:option>
							<form:options items="${ossAdminDepartmentList}"
								itemLabel="departmentName" itemValue="departmentId" />
						</form:select>
					</td>
					<td width="50">
						<input type="submit" value="查 詢">
					</td>
					<td align="left">
						&nbsp;
					</td>
				</tr>
			</table>
			<table width="100%">
				<caption class="x-panel-header">&nbsp;
				</caption>
				<tr>
					<td nowrap="nowrap" width="30%" align="center">
						<b>部门</b>
					</td>
					<td>
						<b>用户
						<span style="margin-right: 120px;"></span>
						<a href="/user.do?method=add">添加</a></b>
					</td>
				</tr>

				<c:forEach var="currentDepart" items="${allDepartment}">
					<tr>
						<td align="center">
							${currentDepart.departmentName }
						</td>
						<td>
							<table width="200px;" cellspacing="3">
								<c:forEach var="user" items="${currentDepart.ossAdminUsers}">
									<tr>
										<td>
											<a href="user.do?method=details&userId=${user.userId }">${user.fullname}</a>&nbsp;&nbsp;
										</td>
										<td align="right">
											<a href="user.do?method=modify&userId=${user.userId}">修改</a>
											<c:if test='${user.fullname ne "admin"}'>
												<a id="delUser_${user.userId}" userId=${user.userId} href="javascript:void()">删除</a>
											</c:if>
										</td>
									</tr>

								</c:forEach>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="2"><hr></td>
					</tr>
				</c:forEach>
			</table>
			</div>
		</form:form>
	</body>
	<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
		$("a[id^='delUser_']").click(function(){
			if(confirm("确认删除？")){
				$(this).attr("href", "user.do?method=delete&userId=" + $(this).attr("userId"));
			}
		});

	</script>
</html>