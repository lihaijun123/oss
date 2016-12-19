<%@page import="com.focustech.uitool.framework.NLGlobal"%>
<%@page import="com.focustech.uitool.list.dt.UIToolChartCtrlBt"%>
<%@page import="com.focustech.uitool.list.form.UIToolChartCtrlForm"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<%
{
UIToolChartCtrlForm form1 = (UIToolChartCtrlForm) request.getAttribute(NLGlobal.CURR_FORM_KEY);
UIToolChartCtrlBt data1 = form1.getChartData();
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