<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>uitool列表</title>
		<!-- Ext Css -->
		<link rel="stylesheet" type="text/css" href="/resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css" href="/css/oss.css" />
		<!-- Ext Js Lib -->
		<script type="text/javascript" src="/extjs/ext-base.js"></script>
		<script type="text/javascript" src="/extjs/ext-all.js"></script>
		<!-- User Defined Js -->
		<script src="js/jquery.js" type="text/javascript"></script>
		<script src="extjs/style.js" type="text/javascript"></script>
		<script src="extjs/validate_form.js" type="text/javascript"></script>
		<script type="text/javascript">
			function copySql(){
				window.clipboardData.setData('text', $("#sql").text());
			}

		</script>

	</head>
	<body>
		<input type="button" value="复制" onclick="copySql()"/><a href="uitool.do?method=list">返回</a><br>
		<textarea id="sql" style="width: 800;height: 700px;">
		${exportSql }
		</textarea>
	</body>
</html>