<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>角色首页资源授权</title>
		<link rel="stylesheet" type="text/css" href="/css/oss.css" />
		<!-- Ext Css End -->
		<!-- Ext Js Lib Start -->
		<script type="text/javascript" src="/extjs/ext-base.js"></script>
		<script type="text/javascript" src="/extjs/ext-all.js"></script>
		<!-- Ext Js Lib End -->
		<!-- User Defined Js Start -->
		<script src="extjs/validate_form.js" type="text/javascript"></script>
		<!-- User Defined Js End -->
	</head>
	<body>
		<form:form modelAttribute="ossAdminRole">
			<table width="100%">
				<!-- 標題 -->
				<caption class="x-panel-header">
					角色首页资源授权
				</caption>
				<tr>
					<th>
						<span class="requiredredstar">*</span>角色名称︰

					</th>
					<td align="left">
						<form:input path="roleName" onblur="validateField(this)"
							readonly="true" />
						<form:errors path="roleName" cssClass="errors" />
					</td>
				</tr>

				<tr>
					<th>
						角色资源︰
					</th>
					<td>

						<c:forEach var="resource" items="${ossAdminResourseList}" varStatus="status">
							<input type="checkbox" id="resource_${resource.resourceId }" name="resources" value="${resource.resourceId }"/>${resource.resourceName }
						</c:forEach>
						<c:forEach var="resource" items="${resourceSelected}">
							<input type="hidden" id="selectResource_${resource.resourceId }" value="${resource.resourceId }"/>
						</c:forEach>
						<form:errors path="resources" cssClass="errors" />
					</td>
				</tr>
			</table>
			<div class="buttons">
				<input type="submit" value=" 确认 " class="submitc">
				<input type="button" value=" 返 回 "
					onclick="window.location.href='/uitoolList.ui?funcID=226'" class="submitc">
			</div>
		</form:form>
		<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript">
		jQuery(function($){
			$("input[id^='selectResource_']").each(function(){
				$("#resource_" + $(this).val()).attr("checked", true);
			});
		});
		</script>
	</body>
</html>