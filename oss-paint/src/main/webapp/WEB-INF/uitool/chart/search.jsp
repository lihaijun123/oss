<%@page import="com.focustech.uitool.list.utils.UIToolUtils"%>
<%@page import="com.focustech.uitool.framework.utils.StringTools"%>
<%@page import="com.focustech.uitool.list.dt.DisplayField"%>
<%@page import="com.focustech.uitool.list.dt.UIToolChartCtrlBt"%>
<%@page import="com.focustech.uitool.framework.NLGlobal"%>
<%@page import="com.focustech.uitool.list.form.UIToolChartCtrlForm"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map" %>

<%@page import="org.apache.struts.util.ResponseUtils"%>

<% {
UIToolChartCtrlForm form1 = (UIToolChartCtrlForm) request.getAttribute(NLGlobal.CURR_FORM_KEY);
UIToolChartCtrlBt data1 = form1.getChartData();
List findFields = data1.getFindFields();
%>
<%@include file="./hiddenField.jsp" %>
<%
if(findFields != null) {
String[] oper_char = new String[]{"like","=","!=","=","<="};
%>
<script language="javascript">
<!--
var currViewObj ;
function resetSearchByText(obj,defaultValue) {
//	alert(defaultValue);
	if(obj == undefined) return ;
		obj.value = defaultValue ;
}
-->
</script>
<form action="uitoolChart.do" method="get" name="searchForm">
<%
String key = "";
List lisHidenField = (List) request.getAttribute("lisHidenField");
for(int i = 0 ; lisHidenField !=null && i < lisHidenField.size() ; i++) {
	key = (String) lisHidenField.get(i);
%>
<input type="hidden" name="<%=key%>" value="<%=request.getParameter(key)%>">
<%}%>
<tr class="searchTr">
	<td>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#C4D7FF" class="searchTab">
		<tr bgcolor="#FFFFFF">
			<td>
<%
if(findFields != null) {
DisplayField ff = null ;
String unViewField = StringTools.notNull(request.getParameter("unViewField"));
for(int i = 0 ; i < findFields.size() ; i++ ) {
	ff = (DisplayField)findFields.get(i) ;
	if(unViewField.indexOf(ff.getField()) >= 0) {
		continue;
	}

	List lisSearchValue = ff.getSearchValue();
	out.println(ff.getFieldText()+ ":");
	int iFindType = ff.getFindType();
//	if(lisSearchValue == null) {
	if(iFindType == DisplayField.FIND_TYPE_LIKE
	  ||iFindType == DisplayField.FIND_TYPE_EQ
	  || iFindType == DisplayField.FIND_TYPE_NEQ) {
%>
			<input type="text" id="searchValue" name="<%=ff.getField()%>" value="" <%=ff.getFieldEvent()%>>
<%
	} else if(iFindType == DisplayField.FIND_TYPE_SELECT) {
%>
			<select name="<%=ff.getField()%>" id="searchValue" <%=ff.getFieldEvent()%>>
				<option value="">全部</option>
<%
		Map temp = null;
		for(int j = 0 ; lisSearchValue!=null && j < lisSearchValue.size() ; j++ ) {
			temp = (Map)lisSearchValue.get(j);
%>
				<option value="<%=temp.get("key")%>"><%=temp.get("value")%></option>
<%
		}
%>
			</select>
<%
	} else if(iFindType == DisplayField.FIND_TYPE_RANGE) {
%>
		<input type="text" id="searchValue" name="S_<%=ff.getField()%>" value="" <%=ff.getFieldEvent()%>>~<input type="text" id="searchValue" name="E_<%=ff.getField()%>" value="" <%=ff.getFieldEvent()%>>
		<input type="hidden" name="searchOper" value="<%=">="%>">
		<input type="hidden" name="searchField" value="<%=ff.getField()%>">
<%
	} else { //如果不在設定的查詢方法範圍內，則默認為 like
%>
		<input type="text" id="searchValue" name="<%=ff.getField()%>" value="" <%=ff.getFieldEvent()%>>
<%
	}
%>
	<input type="hidden" name="searchOper" value="<%=oper_char[iFindType]%>">
	<input type="hidden" name="searchField" value="<%=ff.getField()%>">
<%
}
}
%>
圖形類型︰<select name="chartType">
			<option value="1">餅圖</option>
			<option value="2">拄圖</option>
			<option value="3">曲線圖</option>
			<option value="4">層拄圖</option>
		</select>
			<input name="searchBtn" type="submit" value="查詢">
			</td>
		</tr>
	</table>
	</td>
</tr>
</form>
<script id="id_JsBackView">
<%
	//回當前查詢的數據
	for(int i = 0 ; findFields!=null && i < findFields.size() ; i++ ) {
		DisplayField ff = (DisplayField)findFields.get(i) ;
		if(ff.getFindType() != DisplayField.FIND_TYPE_RANGE) {
%>
		resetSearchByText(document.all.<%=ff.getField()%>,'<%=ResponseUtils.filter(UIToolUtils.getValueFromRequest(ff.getField(),form1.getReqData()).toString())%>');
<%
		} else {
%>
		resetSearchByText(document.all.S_<%=ff.getField()%>,'<%=ResponseUtils.filter(UIToolUtils.getValueFromRequest("S_"+ff.getField(),form1.getReqData()).toString())%>');
		resetSearchByText(document.all.E_<%=ff.getField()%>,'<%=ResponseUtils.filter(UIToolUtils.getValueFromRequest("E_"+ff.getField(),form1.getReqData()).toString())%>');
<%
		}
	}
%>
	resetSearchByText(document.all.chartType,<%=form1.getChartType()%>);
</script>
<%}
}
%>