<%@page language="java" pageEncoding="GB2312" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%
	Exception excep = (Exception) request.getAttribute("exception");
	String errMsg = "";
	if (excep != null) {
		errMsg = excep.getMessage();
	}
%>
<html>
	<head>
		<title><%=errMsg%></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="img/css.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" src="js/common.js"></script>
	</head>
<body>
<center>
	<br /><br /><br />
	<p>
		<%
			if(!errMsg.isEmpty() && errMsg.contains("µÇÂ¼")){
		%>
				<jsp:forward page="/login_error.do"></jsp:forward>
		<%
			}
		%>
		<%=errMsg%>
	</p>
	<p>
		<html:errors property="user.messages" />
	</p>
	<p>
		<input type="button" value="·µ »Ø" onclick="history.back()">
	</p>
</center>
</body>
</html>
