<%@page import="com.focustech.uitool.list.dt.UIToolListCtrlBt"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.focustech.uitool.framework.utils.StringTools" %>

<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.struts.util.ResponseUtils"%>

<% {
String _st_ = StringTools.notNull(request.getParameter("_st_"));
if(_st_.length() >0) {
    return;
}
UIToolListCtrlForm form1 = (UIToolListCtrlForm) request.getAttribute(NLGlobal.CURR_FORM_KEY);
UIToolListCtrlBt data1 = form1.getData();
List findFields = data1.getLisFindFields();
String SF_K = StringTools.notNull((String)request.getAttribute("SF_K"));
%>
<%@include file="./hiddenField.jsp" %>
<%
//if(findFields != null)
{
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
<form action="?" method="get" name="searchForm" id="searchForm">
<input type="hidden" id="orderField" name="orderField" value="<%=StringTools.notNull(request.getParameter("orderField"))%>">
<input type="hidden" id="orderOper" name="orderOper" value="<%=StringTools.notNull(request.getParameter("orderOper"))%>">
<input type="hidden" id="uplike" name="uplike" value="<%=data1.getUpLike()%>">
<input type="hidden" id="pageLimit" name="pageLimit" value="<%=form1.getPageLimit()%>">
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
<%
}
%>
<tr class="searchTr">
	<td>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#C4D7FF" class="searchTab">
		<tr bgcolor="#FFFFFF">
<%
int colspan = 0;
int index = 0;
if(findFields != null) {
colspan = 1;
DisplayField ff = null ;
String unViewField = StringTools.notNull(request.getParameter("unViewField"));
String sf = StringTools.notNull(request.getParameter("S_F"));
for(int i = 0 ; i < findFields.size() ; i++ ) {
	ff = (DisplayField)findFields.get(i) ;
	if(SF_K.length() >0) {
		/*
		if(sf.length() <=0 && i < 8) {
			//
		} else if(sf.length() > 0 && sf.indexOf("," + ff.getField() + ",") >= 0 ) {
			//
		} else {
			continue;
		}
		*/
	}
	if(unViewField.indexOf(ff.getField()) >= 0) {
		continue;
	}
%>
	<td width="10%" align="center" nowrap><%=ff.getFieldText()+ ":"%></td>
	<td width="15%">
<%
	List lisSearchValue = ff.getSearchValue();

	int iFindType = ff.getFindType();
//	if(lisSearchValue == null) {
	if(iFindType == DisplayField.FIND_TYPE_LIKE
	  ||iFindType == DisplayField.FIND_TYPE_EQ
	  || iFindType == DisplayField.FIND_TYPE_NEQ) {
%>
			<input type="text" id="searchValue" name="<%=ff.getField()%>" value="<%=ResponseUtils.filter(UIToolUtils.getValueFromRequest(ff.getField(),form1.getReqData()).toString())%>" <%=ff.getFieldEvent()%>>
<%
	} else if(iFindType == DisplayField.FIND_TYPE_SELECT) {
%>
			<select name="<%=ff.getField()%>" id="searchValue" <%=ff.getFieldEvent()%> style="width: 153px;">
				<option value="">全部</option>
<%
		Map temp = null;
		String sel = "";
		String sValue = UIToolUtils.getValueFromRequest(ff.getField(),form1.getReqData()).toString();
		for(int j = 0 ; lisSearchValue!=null && j < lisSearchValue.size() ; j++ ) {
			temp = (Map)lisSearchValue.get(j);
			if(StringTools.valueOf(temp.get("key")).equals(sValue)) sel = "selected"; else sel="";
%>
				<option value="<%=temp.get("key")%>" <%=sel%> title="<%=temp.get("value")%>"><%=temp.get("value")%></option>
<%
		}
%>
			</select>
<%
	} else if(iFindType == DisplayField.FIND_TYPE_RANGE) {
%>
		<input type="text" id="searchValue" class="f_field_rang_s" name="S_<%=ff.getField()%>" value="<%=ResponseUtils.filter(UIToolUtils.getValueFromRequest("S_"+ff.getField(),form1.getReqData()).toString())%>" <%=ff.getFieldEvent()%>>~<input type="text" id="searchValue" class="f_field_rang_e" name="E_<%=ff.getField()%>" value="<%=ResponseUtils.filter(UIToolUtils.getValueFromRequest("E_"+ff.getField(),form1.getReqData()).toString())%>" <%=ff.getFieldEvent()%>>
		<input type="hidden" name="searchOper" value="<%=">="%>">
		<input type="hidden" name="searchField" value="<%=ff.getField()%>">
<%
	} else { //如果不在设定的查询方法范围内，则默认为 like
%>
		<input type="text" id="searchValue" name="<%=ff.getField()%>" value="<%=ResponseUtils.filter(UIToolUtils.getValueFromRequest(ff.getField(),form1.getReqData()).toString())%>" <%=ff.getFieldEvent()%>>
<%
	}
%>
	<input type="hidden" name="searchOper" value="<%=oper_char[iFindType]%>">
	<input type="hidden" name="searchField" value="<%=ff.getField()%>">
	</td>
<%
	colspan = 3-index%4;
	if(i>0 && index%4 == 3) {
%>
	</tr><tr>
<%
	}
	index++;
}
}
	colspan =colspan*2==0?8:colspan*2;
%>
			<td colspan="<%=colspan%>">&nbsp;</td>
<%
} // end if
%>
		</tr>
		<tr><td colspan="8" align="right"><span id="selOperTd"></span>
			<span><input name="searchBtn" type="submit" value="查询"></span>
<%if(SF_K.length() >0) {%>
			<input id="searchHidShw" type="button" value="&gt;&gt;" onclick="showAllSearchField()">
<%}%>
		</td></tr>
	</table>
	</td>
</tr>
</form>
<script id="id_JsBackView">
</script>
<%
} // end block
%>