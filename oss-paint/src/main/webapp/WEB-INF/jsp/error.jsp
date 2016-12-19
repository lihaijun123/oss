<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>发生错误</title>
	</head>
	<body>
		<h2 style="text-align: center">
			发生错误
		</h2>
		<center>
			<%
				Exception ex = new Exception();
				if (null != request.getAttribute("exception"))
					ex = (Exception) request.getAttribute("exception");
				else if (null != request.getAttribute("ACEGI_SECURITY_403_EXCEPTION"))
					ex = (Exception) request.getAttribute("ACEGI_SECURITY_403_EXCEPTION");
			%>
			<%
				String errMsg = ex.getMessage();
				if(!errMsg.isEmpty() && errMsg.contains("登录")){
			%>
					<jsp:forward page="/login_error.do"></jsp:forward>
			<%
				}
			%>
			错误信息︰
			<font style="color: red; font-weight: bold;"><%=ex.getMessage()%></font>
			<div style="display: none">
				<%
				ex.printStackTrace(new java.io.PrintWriter(out));
				%>
			</div>

		</center>
		<p style="text-align: center">
			<a href="javascript:history.back();">返回</a>
		</p>
	</body>
</html>