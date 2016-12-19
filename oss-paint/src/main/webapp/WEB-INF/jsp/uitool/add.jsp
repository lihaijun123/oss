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
		<title>添加列表</title>
		<link rel="stylesheet" type="text/css" href="/css/oss.css" />
		<!-- Ext Js Lib -->
		<script type="text/javascript" src="/extjs/ext-base.js"></script>
		<script type="text/javascript" src="/extjs/ext-all.js"></script>
		<script type="text/javascript" src="/js/util.js"></script>
		<script src="extjs/validate_form.js" type="text/javascript"></script>
	</head>
	<body>
		<form:form modelAttribute="uitoolFuncDisplayInfo" action="/uitool.do">
			<input type="hidden" name="method" value="add"/>
			<table width="100%">
				<caption class="x-panel-header">
					添加列表资源
				</caption>
				<tr>
					<td colspan="2" align="center">
						<font color="red">${message }</font>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredredstar">*</span>funcID︰
					</th>
					<td align="left">
						<form:input path="funcId"/>
						<form:errors path="funcId" cssClass="errors" />
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredredstar">*</span>功能名称︰
					</th>
					<td align="left">
						<form:input path="funcTitle"/>
						<form:errors path="funcTitle" cssClass="errors" />
					</td>
				</tr>
				<tr>
					<th>
						样式文件︰
					</th>
					<td align="left">
						<form:input path="displayCss"/>
						<form:errors path="displayCss" cssClass="errors" />
					</td>
				</tr>
				<tr>
					<th>
						脚本文件︰
					</th>
					<td align="left">
						<form:input path="displayJs"/>
						<form:errors path="displayJs" cssClass="errors" />
					</td>
				</tr>

				<tr>
					<th>
						自定义jsp文件︰
					</th>
					<td align="left">
						<form:input path="displayPage"/>
						<form:errors path="displayPage" cssClass="errors" />
					</td>
				</tr>
			</table>
			<div class="buttons">
				<input type="submit" value=" 添 加 " class="submitc" id="addSub">
				<input type="button" value=" 返 回 "
					onclick="window.location.href='/uitool.do?method=list'"
					class="submitc">
			</div>
		</form:form>

	</body>
</html>