<%@page import="com.focustech.uitool.framework.NLGlobal"%>
<%@page import="com.focustech.uitool.list.dt.UIToolChartCtrlBt"%>
<%@page import="com.focustech.uitool.list.form.UIToolChartCtrlForm"%>
<%@page contentType="text/html; charset=UTF-8" language="java"%>

<%@page import="java.util.List"%>
<%
{
UIToolChartCtrlForm form1 = (UIToolChartCtrlForm) request.getAttribute(NLGlobal.CURR_FORM_KEY);
UIToolChartCtrlBt data1 = form1.getChartData();
%>
<table id="chart00_table" width="98%" border="0" align="center"	cellpadding="2" cellspacing="1" bgcolor="#C4D7FF">
	<tr align="center" bgcolor="#E7F0FE">
<%
List showHtmls = data1.getShowHtmls();
if(showHtmls !=null) {
	for(int i = 0 ; i < showHtmls.size() ;i++ ) {
%>
		<td><%=showHtmls.get(i)%></td>
<%}} %>
	</tr>
</table>
<%
}
%>
