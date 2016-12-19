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
		<title>修改工作台资源</title>
		<link rel="stylesheet" type="text/css" href="/css/oss.css" />
		<!-- Ext Js Lib -->
		<script type="text/javascript" src="/extjs/ext-base.js"></script>
		<script type="text/javascript" src="/extjs/ext-all.js"></script>
		<!-- User Defined Js -->
		<script src="extjs/validate_form.js" type="text/javascript"></script>

	</head>
	<body>
		<form:form modelAttribute="ossAdminResource">
			<table width="100%">
				<caption class="x-panel-header">
					修改工作台资源︰
				</caption>
				<tr>
					<td colspan="2" align="center">
						<font color="red">${message}</font>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredredstar">*</span> 资源名称︰
					</th>
					<td align="left">
						<form:input path="resourceName" onblur="validateField(this)"
							size="40" maxlength="100"/>
						<form:errors path="resourceName" cssClass="errors" />
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredredstar">*</span>资源类型︰
					</th>
					<td align="left">
						<select name="resourceType">
							<option value="6" selected="${ossAdminResource.resourceType eq 0 ? 'true' : 'false'}" >工作台首页</option>
						</select>
					</td>
				</tr>
				<tr style="display: none">
					<th>
						资源角色
					</th>
					<td align="left">
						<form:select path="roles" multiple="false">
							<form:options items="${OssAdminResource.roles}"
								itemLabel="roleName" itemValue="roleId" />
						</form:select>
					</td>
				</tr>
				<tr>
					<th>
						资源操作︰
					</th>
					<td align="left">
						<form:input path="resourceInterface" onblur="validateField(this)" size="40" />
						<form:errors path="resourceInterface" cssClass="errors" />
					</td>
				</tr>
				<tr>
					<th>
						排序︰
					</th>
					<td align="left">
						<form:input path="resourceOrder" size="40" maxlength="10"
							onblur="validateField(this)"/>
						<form:errors path="resourceOrder" cssClass="errors"/>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredredstar">*</span>资源显示名称︰
					</th>
					<td align="left">
						<form:input path="resourceDisplay" onblur="validateField(this)"
							size="40" maxlength="100"/>
						<form:errors path="resourceDisplay" cssClass="errors" />
					</td>
				</tr>
				<tr>
					<th>
						备注︰
					</th>
					<td>
						<form:textarea path="description" cols="80" rows="10" />
					</td>
				</tr>
				<tr>
					<th>
						状态︰
					</th>
					<td>
						<label><input type="radio" value="1" name="active" id="active_1" ${ossAdminResource.active eq 1 ? "checked='checked'" : ""} />启用</label><label for="active_0"><input type="radio" value="0" name="active" id="active_0" ${ossAdminResource.active eq 0 ? "checked='checked'" : ""} />停用</label>

					</td>
				</tr>
			</table>
			<!--form內容結束-->
			<div class="buttons">
				<input type="submit" value=" 修 改 " class="submitc">
				<input type="button" value=" 返 回 "
					onclick="window.location.href='/resource.do?method=list'"
					class="submitc">
			</div>
		</form:form>
		<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
	</body>
</html>