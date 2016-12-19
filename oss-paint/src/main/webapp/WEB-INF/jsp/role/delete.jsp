<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>刪除角色</title>

		<script src="extjs/json.js" type="text/javascript"></script>
		<script type="text/javascript" src="extjs/ext-base.js"></script>
		<script type="text/javascript" src="extjs/ext-all.js"></script>
		<script type="text/javascript" src="extjs/msg_box.js"></script>
		<script src="extjs/validate_form.js" type="text/javascript"></script>
		<script src="extjs/style.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css"
			href="resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css"
			href="resources/css/msg_box.css" />
	</head>
	<body>
		<script language="javascript">
alert("刪除成功!");
document.location="role.do?method=list";
</script>

	</body>
</html>