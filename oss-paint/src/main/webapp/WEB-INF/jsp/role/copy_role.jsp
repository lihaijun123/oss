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
		<title>负责角色 ︰${option}</title>
		<link rel="stylesheet" type="text/css" href="/css/oss.css" />
		<!-- Ext Js Lib -->
		<script type="text/javascript" src="/extjs/ext-base.js"></script>
		<script type="text/javascript" src="/extjs/ext-all.js"></script>
		<script type="text/javascript" src="/extjs/TreeCheckNodeUI.js"></script>

		<!-- User Defined Js -->
		<script src="extjs/validate_form.js" type="text/javascript"></script>
		<script>
		function check() {
			document.forms[0].submit();
		}
		</script>
	</head>
	<body>
		<form:form modelAttribute="ossAdminRole" action="role.do">
			<input type="hidden" name="method" value="copy">
			<input type="hidden" name="roleId" value="${roleId}">
			<input type="hidden" name="option" value="${option}">
			<table width="100%">
				<caption class="x-panel-header">
					负责角色 : ${option}
				</caption>
				<tr>
					<th>
						<span class="requiredredstar">*</span>角色名称︰

					</th>
					<td align="left">
						<form:input path="roleName" onblur="validateField(this)" />
						<form:errors path="roleName" cssClass="errors" />
					</td>

				</tr>

				<tr>
					<th>
						业务状态︰
					</th>
					<td align="left">
						<f:complex name="active" paramType="PARAM_TYPE_ACTIVE"
							siteType="1" type="radio" itemLabel="parameterValue"
							itemValue="parameterKey"/>
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
					<th>
						角色资源:
					</th>
					<td align="left">
						<c:choose>
							<c:when test="${!empty resource}">
								<input type="checkbox" name="resource" value="1"
									checked="checked">
							</c:when>
							<c:otherwise>
								<input type="checkbox" name="resource" value="1">
							</c:otherwise>
						</c:choose>
					</td>

				</tr>

				<tr>
					<th>
						角色队列:
					</th>
					<td align="left">
						<c:choose>
							<c:when test="${!empty queue}">
								<input type="checkbox" name="queue" value="1"
									checked="checked">
							</c:when>
							<c:otherwise>
								<input type="checkbox" name="queue" value="1">
							</c:otherwise>
						</c:choose>
					</td>

				</tr>
			</table>
			<!--form內容結束-->
			<div class="buttons">
				<input type="submit" value=" 复制 " class="submitc">
				<input type="button" value=" 返 回 "
					onclick="window.location.href='/uitoolList.ui?funcID=226'"
					class="submitc">
			</div>
		</form:form>
	</body>
</html>