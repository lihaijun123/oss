<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.net.URLDecoder" %>
<%
UIToolListCtrlForm form = (UIToolListCtrlForm) request.getAttribute(NLGlobal.CURR_FORM_KEY);
UIToolListCtrlBt data = form.getData();
form.setFindWhere("");
String tiltle = URLDecoder.decode(UIToolUtils.formatUrl(data.getPageTitle(),request));
%>
<html>
	<head>
		<title>
			<%=tiltle%>
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="css/uitool.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" src="js/uitool.js"></script>
		<%@include file="./title.jsp"%>
	</head>

	<body>
		<table width="98%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#C4D7FF">
			<tr bgcolor="#FFFFFF">
				<td id="id_list_title">
					<%=tiltle%>
				</td>
			</tr>
			<%-- 顯示查詢信息 --%>
			<%@include file="./search.jsp"%>
			<%-- 顯示排序信息 --%>
			<%@include file="./order.jsp"%>
		</table>
		<!-- display table -->
		<%@include file="./table_contact.jsp"%>
		<!-- display pagination page -->
		<table>
		<%@include file="../inc/page.jsp"%>
		</table>
	</body>
</html>