<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	</head>
	<body>
	<div>
		<form:form modelAttribute="StaffDetail" action="/excel.do?method=importExcel"
					method="post" enctype="multipart/form-data" id="staffinfoDataForm" name="staffinfoDataForm">
					<input type="hidden" name="method" value="importExcel">
					<table>
						<tr><th style="text-align: left"><b>文件选择</b></th></tr>
						<tr><td>
						<input type="file" value="浏览" style="height: 25px;width: 1000px" name="staffinfoFile" />
						<input name="submitBtn" type="submit" style="height: 25px" value=" 提 交 " />
						</td></tr>
					</table>
		 </form:form>
	</div>
	</body>
</html>