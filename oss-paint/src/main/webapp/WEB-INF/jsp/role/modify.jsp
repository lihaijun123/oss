<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="f" uri="/WEB-INF/tags/focus.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>修改角色</title>
		<link rel="stylesheet" type="text/css" href="/css/oss.css" />
		<!-- Ext Js Lib -->
		<script type="text/javascript" src="/extjs/ext-base.js"></script>
		<script type="text/javascript" src="/extjs/ext-all.js"></script>
		<script src="extjs/validate_form.js" type="text/javascript"></script>
		<script>
		 var selectChecks = new Array();
		</script>
	</head>
	<body>
		<form:form modelAttribute="ossAdminRole">
			<table width="100%">
				<caption class="x-panel-header">
					修改角色
				</caption>
				<tr>
					<th>
						<span class="requiredredstar">*</span>角色名称︰

					</th>
					<td align="left">
						<form:input path="roleName" onblur="validateField(this)" size="40"/>
						<form:errors path="roleName" cssClass="errors" />
					</td>

				</tr>

				<tr>
					<th>
						描述︰
					</th>
					<td align="left">
						<form:textarea path="description" cols="80" rows="10" />
					</td>

				</tr>

				<tr>
					<th> 业务状态︰ </th>
					<td align="left">
						<label><input type="radio" value="1" name="active" id="active_1" ${ossAdminRole.active eq 1 ? "checked='checked'" : ""} />启用</label><label for="active_0"><input type="radio" value="0" name="active" id="active_0" ${ossAdminRole.active eq 0 ? "checked='checked'" : ""} />停用</label>
					</td>

				</tr>
			</table>
			<!--form內容結束-->
			<div class="buttons">
				<input type="submit" value=" 修 改 " class="submitc">
				<input type="button" value=" 返 回 "
					onclick="window.location.href='/role.do?method=list'" class="submitc">
			</div>
		</form:form>
	</body>
</html>