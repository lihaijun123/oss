<%@page import="com.focustech.uitool.framework.utils.StringTools"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ page import="java.net.URLDecoder" %>
<%@page import="java.util.ArrayList"%>
<%
UIToolListCtrlForm form = (UIToolListCtrlForm) request.getAttribute(NLGlobal.CURR_FORM_KEY);
UIToolListCtrlBt data = form.getData();
form.setFindWhere("");
String tiltle = URLDecoder.decode(UIToolUtils.formatUrl(data.getPageTitle(),request));
String SF_K =request.getParameter("SF_K");
if(SF_K==null) {
    SF_K= (String)request.getSession().getAttribute("SF_K");
}
SF_K = StringTools.notNull(SF_K);
if(SF_K.length() >0) {
    request.setAttribute("SF_K",SF_K);
}
%>
<html>
	<head>
		<title>
			<%=tiltle%>
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="css/uitool.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" src="js/uitool.js"></script>
		<script language="JavaScript" src="/js/jquery.js"></script>
		<%@include file="./title.jsp"%>
	</head>

	<body>
		<table width="98%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#C4D7FF">
			<tr bgcolor="#FFFFFF">
				<td id="id_list_title">
					<%=tiltle%>
				</td>
			</tr>
			<%-- 显示查询信息 --%>
			<jsp:include page="./search_new.jsp" />
			<jsp:include page="./search.jsp" />
			<%--
			<%@include file="./search_new.jsp"%>
			<%@include file="./search.jsp"%>
			 --%>
			<%-- 显示排序信息 --%>
			<%@include file="./order.jsp"%>
		</table>
		<!-- display table -->
<form name="nextForm" action="<%=request.getParameter("gotoUrl")%>" method="post" style="border:none;padding: 0px;margin: 0px;" target="_blank">
<%
{
List lisHidenField = (List) request.getAttribute("lisHidenField");
if(lisHidenField == null) {
    lisHidenField = new ArrayList();
}
String key = "";
for(int i = 0 ; i < lisHidenField.size() ; i++) {
    key = (String) lisHidenField.get(i);
%>
<input type="hidden" name="<%=key%>" value="<%=request.getParameter(key)%>">
<%}
}%>
		<%@include file="./table.jsp"%>
</form>
		<!-- display pagination page -->
		<table>
		<%@include file="../inc/page.jsp"%>
		</table>
        <%--@include file="./searchAll.jsp"--%>
        <jsp:include page="./searchAll.jsp" />
	</body>
</html>