<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tags/focus.tld" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户详细信息</title>
		<!-- Ext Css -->
		<link rel="stylesheet" type="text/css" href="/css/oss.css" />
		<!-- Ext Js Lib -->
		<script type="text/javascript" src="/extjs/ext-base.js"></script>
		<script type="text/javascript" src="/extjs/ext-all.js"></script>
		<!-- User Defined Js -->
		<script src="extjs/style.js" type="text/javascript"></script>
		<script src="extjs/validate_form.js" type="text/javascript"></script>

	</head>
	<body>
		<form:form modelAttribute="ossAdminUser">
			<table width="100%">
				<caption  class="x-panel-header">
					用户登录信息
				</caption>
				<tr align="left"  >
					<td colspan="2" style="background-color: #99CCCC">
						用户登录信息
					</td>
				</tr>
				<tr>
					<th class="required">
						用户登录名：
					</th>
					<td>
						<form:input path="loginName" onblur="validateField(this)"
							size="40" disabled="true" />

					</td>
				</tr>
				<c:if test="${!ossAdminUser.isLdapUser()}">
					<tr>
						<th class="required">
							用户登录密码︰
						</th>
						<td>
							<form:input path="password" size="40" disabled="true" />
						</td>
					</tr>
				</c:if>
				<tr align="left">
					<td colspan="2" style="background-color: #99CCCC">
						用户基本信息
					</td>
				</tr>
				<tr>
					<th>
						工号︰

					</th>
					<td align="left">
						<form:input path="workerId" size="40" disabled="true" />
					</td>

				</tr>
				<tr>
					<th class="required">
						姓名︰
					</th>
					<td>
						<form:input path="fullname" size="40" disabled="true" />
					</td>
				</tr>

				<tr>
					<th>
						別名（英文名）︰

					</th>
					<td align="left">
						<form:input path="agname" size="40" disabled="true" />
					</td>
				</tr>
				<tr>
					<th class="required">
						部门名称︰
					</th>
					<td>
						<form:select path="ossAdminDepartment" items="${departmentList }" itemLabel="departmentName" itemValue="departmentId" disabled="true"/>
					</td>
				</tr>
				<tr>
					<th class="required">
						性別︰
					</th>
					<td>
						<f:complex name="gender" paramType="USER_SEX"
							siteType="1" type="radio" itemLabel="parameterValue"
							itemValue="parameterKey" defaultValue="${ossAdminUser.gender}"
							otherAttrs="disabled" />
					</td>
				</tr>
				<tr>
					<th>
						分机号︰

					</th>
					<td align="left">
						<form:input path="extension" size="40" disabled="true" />
					</td>
				</tr>
				<tr>
					<th>
						手机号码︰
					</th>
					<td align="left">
						<form:input path="mobileTelephone" size="40" disabled="true" />
					</td>
				</tr>
				<!--
				<tr>
					<th>
						登录方式︰
					</th>
					<td>
						<c:if test="${ossAdminUser.isLdapUser()}">
							<b>域账号登录${ossAdminUser.isLdapUser}</b>
						</c:if>
						<c:if test="${!ossAdminUser.isLdapUser()}">
							<b>销售系统用户名、密码登录</b>
						</c:if>

						<!--<form:input path="loginName" size="40" readonly="true" />
					</td>
				</tr>
				 -->
				<tr>
					<th>
						EMAIL︰
					</th>
					<td>
						<form:input path="email" size="40" disabled="true" />

					</td>
				</tr>
				<tr>
					<th class="required">
						用户角色︰
					</th>
					<td>
						<form:select path="roles" disabled="true" multiple="false">
							<form:option label="请选择" value="" />
							<form:options items="${ossAdminRolesList}" itemLabel="roleName"
								itemValue="roleId" />
						</form:select>
					</td>
				</tr>
				<tr>
					<th class="required">
						业务状态︰
					</th>
					<td>
						<label><input type="radio" value="1" name="active" id="active_1" ${ossAdminUser.active eq 1 ? "checked='checked'" : ""} disabled="true"/>启用</label><label for="active_0"><input type="radio" value="0" name="active" id="active_0" ${ossAdminUser.active eq 0 ? "checked='checked'" : ""} disabled="true"/>停用</label>
						<!--<f:complex name="active" paramType="HElP_INFO_STATUS"
							siteType="1" type="radio" itemLabel="parameterValue"
							itemValue="parameterKey" defaultValue="${ossAdminUser.active}"
							otherAttrs="disabled" />
						-->
					</td>
				</tr>
				<tr>
					<th>
						备注︰
					</th>
					<td>
						<form:textarea path="description" cols="40" rows="10"
							disabled="true" />
					</td>
				</tr>
			</table>
			<div align="center">
				<input type="button" value=" 编辑 "
					onclick='window.location="user.do?method=modify&userId=${ossAdminUser.userId }"'
					class="submitc">
				<input type="button" value=" 返 回 "
					onclick="window.location='/user.do?method=list'"
					class="submitc">
			</div>
		</form:form>
	</body>
</html>