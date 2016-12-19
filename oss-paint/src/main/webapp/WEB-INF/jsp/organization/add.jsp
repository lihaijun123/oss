<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tags/focus.tld" prefix="f"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
		<title>添加部门</title>
		<!-- Ext Css -->
		<link rel="stylesheet" type="text/css" href="/css/oss.css" />
		<!-- Ext Js Lib -->
		<script type="text/javascript" src="/extjs/ext-base.js"></script>
		<script type="text/javascript" src="/extjs/ext-all.js"></script>
		<!-- User Defined Js -->
		<script src="/js/util.js" type="text/javascript"></script>
		<script src="extjs/validate_form.js" type="text/javascript"></script>
		<script type="text/javascript" src="/extjs/department_user.js"></script>

	</head>
	<body>
		<form:form modelAttribute="ossAdminDepartment" onsubmit="setBtn2Disabled('addSub')">
			<table width="100%">
				<caption class="x-panel-header">
					添加部门
				</caption>
				<tr>
					<th>
						<span class="requiredredstar">*</span>名称︰

					</th>
					<td align="left">
						<form:input path="departmentName" onblur="validateField(this)"
							size="40" />
						<form:errors path="departmentName" cssClass="errors" />
					</td>
				</tr>
				<input type="hidden" name="departmentParentId" value="0"/>
				<input type="hidden" name="departmentType" value="1"/>
				<tr>
					<th class="required">
						上级部门︰
					</th>
					<td align="left">
						<input type="hidden" name="departmentParentId" value="0"/>
						<c:choose>
							<c:when test="${empty parentDepartment}">
								南京焦点总部
							</c:when>
							<c:otherwise>
								${parentDepartment.departmentName}
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<!--

				<tr>
					<th class="required">
						部门类型︰
					</th>
					<td align="left">
						<f:complex name="departmentType" paramType="PARAM_TYPE_DEPART"
							siteType="1" type="select" itemLabel="parameterValue"
							itemValue="parameterKey"
							defaultValue="${ossAdminDepartment.departmentType}" />
						<form:errors path="departmentType" cssClass="errors" />
					</td>
				</tr>
				 -->


				<tr>
					<th>
						地址︰
					</th>
					<td align="left">
						<form:input path="departmentAddress" size="40" />
					</td>
				</tr>

				<tr>
					<th>
						联系人︰
					</th>
					<td align="left">
						<form:input path="departmentContact" size="40" />
					</td>

				</tr>

				<tr>
					<th>
						联系电话︰
					</th>
					<td align="left">
						<form:input path="departmentPhone" size="40" maxlength="100" />
					</td>
				</tr>

				<tr>
					<th>
						传真号︰
					</th>
					<td align="left">
						<form:input path="departmentFax" size="40" maxlength="100" />
					</td>
				</tr>
				<input type="hidden" name="active" value="1"/>
				<!--
				<tr>
					<th class="required">
						业务状态︰

					</th>
					<td align="left">
						<f:complex name="active" paramType="PARAM_TYPE_ACTIVE"
							siteType="1" type="radio" itemLabel="parameterValue"
							itemValue="parameterKey"
							defaultValue="${ossAdminDepartment.active}" />

					</td>
				</tr>

				 -->

				<tr>
					<th>
						描述︰
					</th>
					<td align="left">
						<form:textarea path="description" cols="50" rows="10" />
					</td>
				</tr>
			</table>

			<div align="center">
				<input type="submit" value=" 添 加 " id="addSub" class="submitc">
				<input type="button" value=" 返 回 "
					onclick='window.location="/department.do?method=list"'
					class="submitc" />
			</div>
		</form:form>
	</body>
</html>