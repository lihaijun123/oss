<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/tags/focus.tld" prefix="f"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link rel="stylesheet" type="text/css" href="/css/oss.css" />
<link rel="stylesheet" type="text/css" href="/css/pop.css" />
</head>
<body>
<form:form modelAttribute="paintCatgory" action="/paintCatgory.do" method="post">
	<input type="hidden" name="method" value="edit" />
	<input type="hidden" name="sn" value="${paintCatgory.sn }" />
	<table width="100%">
		<caption class="x-panel-header"></caption>
				<tr>
			<th><font color="red">*</font>类别名称:</th>
			<td ><form:input path="name"/></td>
		</tr>
				
			</table>
	<div class="buttons">
		<input type="submit"  class="submitc" value="保存"/>
	</div>
</form:form>
<div class="buttons">
	<a href="/paintCatgory.do?method=new">新建</a>|
	<a href="/uitoolList.ui?funcID=1080331">返回列表</a>
</div>
</body>
<!-- Ext Js Lib Start -->
<script type="text/javascript" src="/extjs/ext-base.js"></script>
<script type="text/javascript" src="/extjs/ext-all.js"></script>
<!-- Ext Js Lib End -->
<!-- User Defined Js Start -->
<script src="extjs/validate_form.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/util.js"></script>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/calendar.js"></script>
<%@include file="/WEB-INF/jsp/common/uploadfile.jsp" %>
<script src="/js/ImageEditor/imageEditor.js"></script>
</html>