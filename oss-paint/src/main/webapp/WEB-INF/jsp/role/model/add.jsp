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
		<title>添加角色模块</title>
		<link rel="stylesheet" type="text/css" href="/css/oss.css" />
		<!-- User Defined Js -->
		<script type="text/javascript" src="/extjs/validate_form.js"></script>
	</head>
	<body>
		<form:form modelAttribute="roleModel" action="roleModel.do?method=add" method="post">
			<table width="100%">
				<caption class="x-panel-header">
					添加角色模块
				</caption>
				<tr>
					<th>
						<span class="requiredredstar">*</span>角色名称︰
					</th>
					<td align="left">
						<f:complex name="roleId" items="${roles}" type="select" itemLabel="roleName" itemValue="roleId" defaultValue="${param.roleID}" otherAttrs="><option value=''>請選擇</option"/>
						<form:errors path="roleId" cssClass="errors"/>
					</td>
				</tr>

				<tr>
					<th> 
						<span class="requiredredstar">*</span>模块︰
					</th>
					<td align="left">
						<f:complex name="model"
							paramType="PARAMETER_TYPE_MODEL_TYPE" siteType="1"
							type="select" itemLabel="parameterValue" itemValue="parameterKey"
							/>
					</td>
				</tr>
				
			</table>
			<!--form內容結束-->
			<div class="buttons">
				<input type="submit" value=" 添 加 " class="submitc">
				<input type="button" value=" 返 回 "
					onclick="window.location.href='/uitoolList.ui?funcID=255'" class="submitc">
			</div>
		</form:form>
	</body>
</html>