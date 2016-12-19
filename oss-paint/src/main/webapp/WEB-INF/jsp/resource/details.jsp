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
		<title>查看${url }资源</title>
		<link rel="stylesheet" type="text/css" href="/css/oss.css" />
		<!-- Ext Js Lib -->
		<script type="text/javascript" src="/extjs/ext-base.js"></script>
		<script type="text/javascript" src="/extjs/ext-all.js"></script>
		<script src="extjs/validate_form.js" type="text/javascript"></script>

	</head>
	<body>
		<form:form modelAttribute="ossAdminResource">
			<table width="100%">
				<caption class="x-panel-header">
					查看${url }资源
				</caption>
				<tr>
					<th>
						<span class="requiredredstar">*</span>菜單资源名称
					</th>
					<td align="left">
						<form:input path="resourceName" onblur="validateField(this)"
							size="40" disabled="true" />
					</td>

				</tr>

				<tr>
					<th>
						<span class="requiredredstar">*</span>上级资源
					</th>
					<td align="left">
						<form:select path="resourceParentId" disabled="true"
							cssClass="x-form-select-one">
							<form:options items="${menuResourcesList}"
								itemLabel="resourceName" itemValue="resourceId" />
						</form:select>
					</td>

				</tr>

				<tr>
					<th>
						<span class="requiredredstar">*</span>资源类型
					</th>
					<td align="left">
						<form:radiobuttons path="resourceType"
							items="${crmBaseParameterRESOURCEType}"
							itemLabel="parameterValue" itemValue="parameterKey"
							onblur="validateField(this)" disabled="true" />

					</td>

				</tr>

				<tr>
					<th>
						<span class="requiredredstar">*</span>资源操作
					</th>
					<td align="left">
						<form:input path="resourceInterface" onblur="validateField(this)"
							size="40" disabled="true" />
					</td>

				</tr>

				<tr>
					<th>
						<span class="requiredredstar">*</span>排序︰
					</th>
					<td align="left">
						<form:input path="resourceOrder" size="40" maxlength="10"
							onblur="validateField(this)" disabled="true" />
						<form:errors path="resourceOrder" cssClass="errors" />
					</td>
				</tr>

				<tr>
					<th>
						<span class="requiredredstar">*</span>资源显示名称︰
					</th>
					<td align="left">
						<form:input path="resourceDisplay" onblur="validateField(this)"
							size="40" disabled="true" />
						<form:errors path="resourceDisplay" cssClass="errors" />
					</td>

				</tr>

				<tr>
					<th>
						备注
					</th>
					<td align="left">
						<form:textarea path="description" cols="80" rows="10"
							disabled="true" />
					</td>
				</tr>

				<tr>
					<th>
						状态︰
					</th>
					<td>
						<f:complex name="active" paramType="PARAM_TYPE_ACTIVE"
							siteType="1" type="radio" itemLabel="parameterValue"
							itemValue="parameterKey" defaultValue="${ossAdminResource.active}" otherAttrs="disabled='disabled'"/>
					</td>
				</tr>

			</table>
			<!--form內容結束-->
			<div class="buttons">
				<c:if test="${url=='menu'}">
					<input type="button" value=" 編 輯 "
						onclick="window.location.href='/resource.do?method=menuModify&resourceId=${ossAdminResource.resourceId }'"
						class="submitc">
					<input type="button" value=" 返 回 "
						onclick="window.location.href='/resource.do?method=list'"
						class="submitc">
				</c:if>
				<c:if test="${url=='url'}">
					<input type="button" value=" 編 輯 "
						onclick="window.location.href='/resource.do?method=urlModify&resourceId=${ossAdminResource.resourceId }'"
						class="submitc">
					<input type="button" value=" 返 回 "
						onclick="window.location.href='/uitoolList.ui?funcID=221'"
						class="submitc">
				</c:if>
				<c:if test="${url=='method'}">
				<input type="button" value=" 編 輯 "
						onclick="window.location.href='/resource.do?method=methodModify&resourceId=${ossAdminResource.resourceId }'"
						class="submitc">
					<input type="button" value=" 返 回 "
						onclick="window.location.href='/uitoolList.ui?funcID=225'"
						class="submitc">
				</c:if>
			</div>
		</form:form>

	</body>
</html>