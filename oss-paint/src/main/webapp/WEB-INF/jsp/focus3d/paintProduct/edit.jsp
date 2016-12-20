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
<form:form modelAttribute="paintProduct" action="/paintProduct.do" method="post">
	<input type="hidden" name="method" value="edit" />
	<input type="hidden" name="sn" value="${paintProduct.sn }" />
	<table width="100%">
		<caption class="x-panel-header"></caption>
		
					<tr>
			<th><font color="red">*</font>颜色名称:</th>
			<td ><form:input path="colorName"/></td>
		</tr>
		<tr>
			<th><font color="red">*</font>色系:</th>
			<td >
				<form:select path="colorSn">
					<form:options items="${colorList }" itemLabel="name" itemValue="sn"/>
				</form:select>
			</td>
		</tr>
				<tr>
			<th><font color="red">*</font>模型图片:</th>
			<td >
				<f:img id="picFileSn_img" hid="picFileSn" hname="picFileSn" width="120" height="120" src="${paintProduct.picFileSn}" /><br>
				<input id="file_upload1" name="file_upload1" type="file" /><br>
				<form:errors path="picFileSn" cssClass="errors" />
			</td>
		</tr>
				<tr>
			<th><font color="red">*</font>模型文件:</th>
			<td >
				<input id="file_upload2" name="file_upload2" type="file" /><br>
				<f:fileInfo hid="modelFileSn" hname="modelFileSn" value="${paintProduct.modelFileSn }"/>
			</td>
		</tr>
	
		<tr>
			<th><font color="red">*</font>涂料名称:</th>
			<td ><form:input path="name"/></td>
		</tr>
				<tr>
			<th><font color="red">*</font>产品ID:</th>
			<td ><form:input path="productId"/></td>
		</tr>
				<tr>
			<th><font color="red">*</font>产品系列:</th>
			<td >
				<form:select path="cateSn">
					<form:options items="${cateList }" itemLabel="name" itemValue="sn"/>
				</form:select>
			</td>
		</tr>
				<tr>
			<th><font color="red">*</font>规格:</th>
			<td ><form:input path="size"/></td>
		</tr>
				<tr>
			<th><font color="red">*</font>用量:</th>
			<td ><form:input path="dosage"/></td>
		</tr>
				
				
			</table>
	<div class="buttons">
		<input type="submit"  class="submitc" value="保存"/>
	</div>
</form:form>
<div class="buttons">
	<a href="/paintProduct.do?method=new">新增</a>|
	<a href="/uitoolList.ui?funcID=1080332">返回列表</a>
</div>
</body>
<!-- Ext Js Lib Start -->
<script type="text/javascript" src="/extjs/ext-base.js"></script>
<script type="text/javascript" src="/extjs/ext-all.js"></script>
<!-- Ext Js Lib End -->
<!-- User Defined Js Start -->
<script src="/js/bootstrap.minn-3.3.5.js"></script>
<script src="extjs/validate_form.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/util.js"></script>
<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="/js/jquery.validate.extend.js"></script>
<script type="text/javascript" src="/js/calendar.js"></script>
<script type="text/javascript" src="/js/footer.js"></script>
<%@include file="/WEB-INF/jsp/common/uploadfile.jsp" %>
<script src="/js/ImageEditor/imageEditor.js"></script>
<script src="/js/focus3d/paintProduct/fileupload.js"></script>
<script src="/js/focus3d/paintProduct/validate.js"></script>
<div id="footer">
	<span><h1>${message }</h1></span>
</div>
</html>