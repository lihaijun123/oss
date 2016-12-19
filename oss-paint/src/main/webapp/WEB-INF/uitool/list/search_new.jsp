<%@page import="com.focustech.uitool.framework.utils.MapTools"%>
<%@page import="com.focustech.uitool.list.dt.UIToolListCtrlBt"%>
<%@page import="com.focustech.uitool.framework.utils.StringTools"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Enumeration" %>

<%@page import="java.util.ArrayList"%>

<%@page import="java.util.Map"%>

<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<% {
String _st_ = StringTools.notNull(request.getParameter("_st_"));
if(_st_.length() <=0) {
    return;
}
UIToolListCtrlForm form1 = (UIToolListCtrlForm) request.getAttribute(NLGlobal.CURR_FORM_KEY);
UIToolListCtrlBt data1 = form1.getData();
List findFields = data1.getLisFindFields();
String SF_K = StringTools.notNull((String)request.getAttribute("SF_K"));
%>
<%@include file="./hiddenField.jsp" %>
<%
if(findFields != null) {
//String[] oper_char = new String[]{"like","=","!=",">=","<="};
//String[] oper_char = new String[]{"0","1","2","7"};
//String[] cText = new String[]{"模糊","等于","不等于","空值"};
//boolean[] needValue = new boolean[]{true,true,true,false};
%>
<script language="javascript">
<!--
var currViewObj ;
function resetSearchByText(obj,defaultValue) {
//	alert(defaultValue);
	if(obj == undefined) return ;
	obj.value = defaultValue ;
}
function showFieldArea(evObj){
	var field = evObj.options[evObj.selectedIndex].value;
	var index = evObj.index;
	//
	field = document.getElementById("sv_"+field);
	if(field) {
		var sv_div = document.getElementById("sv_div_"+index);
		sv_div.innerHTML = field.outerHTML;
	}
}
function hidenCondOper() {
	document.getElementById("condoper").style.display='none';
}
function showCondOper() {
	document.getElementById("condoper").style.display='';
}
function changeField(obj) {
	var index = obj.getAttribute("index");
	var value = obj.options[obj.selectedIndex].value;
	var selObj = document.getElementById("div_sel_" + value);
	var td_searchValue = document.getElementById("td_searchValue_" + index);
	if(selObj) {
		if(td_searchValue) {
			td_searchValue.innerHTML = selObj.innerHTML;
		}
	} else {
		if(td_searchValue) {
			td_searchValue.innerHTML = '<input type="text" name="searchValue" value="">';;
		}
	}
	var oper = obj.options[obj.selectedIndex].getAttribute("oper");
	var operObj = document.getElementById("sel_searchOper_" + index);
	resetSearchByText(operObj,oper);
	if(operObj.value =='') {
		resetSearchByText(operObj,'1');
	}

}
function changeOper(oper) {
	var value = oper.options[oper.selectedIndex].value;
	var needValue = oper.options[oper.selectedIndex].getAttribute("needValue");
	var index = oper.getAttribute("index");
	var searchValue = document.getElementById("searchValue_" + index);
	if(needValue == 'false') {
		searchValue.readOnly=true;
	} else {
		searchValue.readOnly=false;
	}
}
-->
</script>
<form action="?" method="get" name="searchForm" id="searchForm">
<input type="hidden" name="orderField" id="orderField" value="">
<input type="hidden" name="orderOper" id="orderOper" value="">
<input type="hidden" name="pageLimit" id="pageLimit" value="<%=form1.getPageLimit()%>">
<input type="hidden" name="_st_" id="_st_" value="n">
<input type="hidden" name="s_f" id="s_f" value="<%=StringTools.valueOf(form1.getReqData().get("s_f"))%>">
<input type="hidden" name="o_f" id="o_f" value="<%=StringTools.valueOf(form1.getReqData().get("o_f"))%>">
<input type="hidden" name="d_f" id="d_f" value="<%=StringTools.valueOf(form1.getReqData().get("d_f"))%>">
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
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#C4D7FF" class="searchTab_n">
		<tr bgcolor="#FFFFFF">
<%
String defField = "", defOper = "";
int colspan = 0;
final int maxRow = 6;
final int maxCol = 3;
final int maxColIdx=maxCol-1;
DisplayField defDisField = null ;
for(int iRow = 0 ; iRow < maxRow; iRow++) {
	defField =""; defOper ="";defDisField=null;
	if(form1.getSearchField() !=null && form1.getSearchField().length> iRow) defField = form1.getSearchField()[iRow] ;
	if(form1.getSearchOper()!=null&& form1.getSearchOper().length> iRow) defOper = form1.getSearchOper()[iRow];
%>
		<td>
		<select name="searchField" index="<%=iRow%>" onchange="changeField(this)">
			<option value="">请选择</option>
		<%
		String checked = "";
		if(findFields != null) {
		DisplayField ff = null ;
		for(int i = 0 ; i < findFields.size() ; i++ ) {
			ff = (DisplayField)findFields.get(i) ;
			checked = "";
			if(ff.getField().equalsIgnoreCase(defField)) {
				checked = "selected";
				defDisField = ff;
			}
		%>	<option value="<%=ff.getField()%>" <%=checked %> oper="<%=ff.getFindType()%>"><%=ff.getFieldText()%></option>
		<%}}%>
		</select>
	</td><td>
		<select id="sel_searchOper_<%=iRow%>" name="searchOper" index="<%=iRow%>" onchange="changeOper(this)">
			<option value="">请选择</option>
<%
		Map tmp =UIToolUtils.getSearchOperConfigs();
		Iterator keys = tmp.keySet().iterator();
		key ="";
		while(keys.hasNext()) {
			key = String.valueOf(keys.next());
			checked = "";
			if(key.equalsIgnoreCase(defOper)) checked = "selected";
%>			<option value="<%=key%>" <%=checked %> needValue="<%=UIToolUtils.getUISysBooleanParam(key,"needValue",true)%>"><%=tmp.get(key)%></option>
<%		}%>
		</select>
	</td>
	<td id="td_searchValue_<%=iRow%>">
<%
if(defDisField !=null && defDisField.getFindType() == DisplayField.FIND_TYPE_SELECT) {
	List lisSearchValue = defDisField.getSearchValue();
%>
	<select name="searchValue" id="searchValue_<%=iRow%>" <%=defDisField.getFieldEvent()%>>
		<option value="">全部</option>
<%
		Map temp = null;
		String sel = "";
		String sValue = MapTools.getString(form1.getReqData(),"searchvalue"+iRow);
		for(int j = 0 ; lisSearchValue!=null && j < lisSearchValue.size() ; j++ ) {
			temp = (Map)lisSearchValue.get(j);
			if(StringTools.valueOf(temp.get("key")).equals(sValue)) sel = "selected"; else sel="";
%>
		<option value="<%=temp.get("key")%>" <%=sel%>><%=temp.get("value")%></option>
<%
		}
%>
	</select>
<%
} else {
%>
	<input type="text" name="searchValue" id="searchValue_<%=iRow%>" value="<%=MapTools.getString(form1.getReqData(),"searchvalue"+iRow)%>" />
<%
}// end if
%>
	</td>
<%
	colspan = maxColIdx-iRow%maxCol;
	if((iRow%maxCol) == maxColIdx) {
%></tr><tr><%
	}
}	colspan=colspan*3==0?maxCol*3:colspan*3;
%>
	<td colspan="<%=colspan%>" align="right">
	<select name="condoper" id="condoper">
		<option value="AND" selected>与</option>
		<option value="OR">或</option>
	</select>
	<input name="searchBtn" type="submit" value="查询">
<%if(SF_K.length() >0) {%>
			<input id="searchHidShw" type="button" value="&gt;&gt;" onclick="showAllSearchField()">
<%}%></td>
	</tr>
</table>
	</td>
</tr>
</form>
<!-- ====================================================== -->
<%
DisplayField ff = null ;
for(int i = 0 ; findFields!=null && i < findFields.size() ; i++ ) {
	ff = (DisplayField)findFields.get(i) ;
	if(ff.getFindType() == DisplayField.FIND_TYPE_SELECT) {
	List lisSearchValue = ff.getSearchValue();
%>
<div id="div_sel_<%=ff.getField()%>" style="display:none">
	<select name="searchValue" id="searchValue_<%=ff.getField()%>" <%=ff.getFieldEvent()%>>
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
</div>
<%
	}
}
%>
<!-- ====================================================== -->
<script>
resetSearchByText(document.getElementById('condoper'),'<%=MapTools.getString(form1.getReqData(),"condoper","AND")%>');
</script>
<%}
}
%>


