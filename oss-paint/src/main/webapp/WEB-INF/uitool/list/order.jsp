<%@page import="com.focustech.uitool.list.dt.DisplayField"%>
<%@page import="com.focustech.uitool.list.dt.UIToolListCtrlBt"%>
<%@page import="com.focustech.uitool.list.form.UIToolListCtrlForm"%>
<%@page contentType="text/html; charset=UTF-8"%>

<%@page import="java.util.List"%>

<script>
function doOrder(fieldName,orderStr) {
	document.getElementById("id_orderField").value = fieldName;
	document.getElementById("id_orderOper").value = orderStr;
	document.getElementById("theForm").submit();
}
</script>

<tr class="orderTr">
	<td>
<% {
UIToolListCtrlForm form1 = (UIToolListCtrlForm) request.getAttribute(NLGlobal.CURR_FORM_KEY);
UIToolListCtrlBt data1 = form1.getData();
List findFields = data1.getLisOrderFields();
if(findFields!=null ) {
DisplayField field = null;
%>
正排︰<%
String orderField = (String)form1.getReqData().get("orderfield");
String orderOper =  (String)form1.getReqData().get("orderoper");
for(int i = 0 ; findFields!=null && i < findFields.size();i++ ) {
	field = (DisplayField) findFields.get(i);
	String style ="";
	if(field.getField().equalsIgnoreCase(orderField) && "asc".equalsIgnoreCase(orderOper)) {
		style = "style=\"color:#990000\"";
	}
%>
	<a href="javascript:doOrder('<%=field.getField()%>','asc');" <%=style%>><%=field.getFieldText()%></a>
<%
}
%>

<br/>反排︰<%
for(int i = 0 ; i < findFields.size();i++ ) {
	field = (DisplayField) findFields.get(i);
	String style ="";
	if(field.getField().equalsIgnoreCase(orderField) && "desc".equalsIgnoreCase(orderOper)) {
		style = "style=\"color:#990000\"";
	}
%>
	<a href="javascript:doOrder('<%=field.getField()%>','desc');" <%=style%>><%=field.getFieldText()%></a>
<%
}
%>

<%
}
}
%>
</td></tr>