<%@page import="com.focustech.uitool.framework.NLGlobal"%>
<%@page import="com.focustech.uitool.list.dt.UIToolListCtrlBt"%>
<%@page import="com.focustech.uitool.list.form.UIToolListCtrlForm"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<%
{
UIToolListCtrlForm form1 = (UIToolListCtrlForm) request.getAttribute(NLGlobal.CURR_FORM_KEY);
UIToolListCtrlBt data1 = form1.getData();
%>

<!-- css with func_id -->
<%
String[] css = data1.getPageCss() ;
if(css !=null)
for(int i = 0 ; i < css.length ; i++) {
	if(!"".equals(css[i])) {
%>
<link href="<%=css[i]%>" rel="stylesheet" type="text/css" />
<%
	}
}
%>
<!-- js with func_id -->
<%
String[] js = data1.getPageJs() ;
if(js !=null)
for(int i = 0 ; i < js.length ; i++) {
	if(!"".equals(js[i])) {
%>
<script language="javascript" src="<%=js[i]%>"></script>
<%
	}
}
}
%>