<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title==null?"快捷菜单":title}</title>
</head>
<script type="text/javascript">
document.domain = "wenbi.com";
var isRedirect = true;
var backUrl = "${backUrl}";
var redirect ="${redirect}";
if(backUrl.indexOf("logon.do") >= 0 || backUrl.indexOf("aut_security_source") >= 0){//如果是總的頁面框架，則返回到轉發頁面
	backUrl = redirect;
}
</script>
<body style="margin:0px;padding:0px" style="width: 100%; height: 100%;">
	<iframe src="<%=request.getAttribute("redirect")%>" frameborder="0" style="width: 100%; height: 100%;position:relative;"> </iframe>
</body>
</html>