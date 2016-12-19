<%@page import="com.focustech.uitool.framework.utils.StringTools"%>
<%@page import="com.focustech.uitool.list.utils.UIToolUtils"%>
<%@page import="com.focustech.uitool.list.dt.HtmlTDBean"%>
<%@page import="com.focustech.uitool.list.dt.HtmlTRBean"%>
<%@page import="com.focustech.uitool.list.dt.HtmlTableBean"%>
<%@page import="com.focustech.uitool.framework.NLGlobal"%>
<%@page import="com.focustech.uitool.list.dt.UIToolListCtrlBt"%>
<%@page import="com.focustech.uitool.list.form.UIToolListCtrlForm"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>

<%@page import="java.util.Map"%>

<%
{
UIToolListCtrlForm form1 = (UIToolListCtrlForm) request.getAttribute(NLGlobal.CURR_FORM_KEY);
UIToolListCtrlBt data1 = form1.getData();
%>
<script>
function deleteConfirm(url) {
	if(confirm("你确定要刪除当前信息吗？")) {
		window.location.href = url;
	}
}
</script>

<table id="list00_table" width="98%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#C4D7FF">
<%--        --%>
<%-- 顯示標題 --%>
<%--         --%>
<%
String otherFields = form1.getOtherFields();
if(!otherFields.startsWith("&") && !"".equals(otherFields)) {
	otherFields = "&" + otherFields;
}
otherFields = UIToolUtils.parseUrlSpecialChar(otherFields,true);

//----------------------------------------------------------------------------------------------------------------------
List titleData = data1.getLisTitleData() ;

if(titleData != null && titleData.size() > 0) {
HtmlTableBean table = data1.preparedTitleRows();
List rows = table.getRows();
titleData = table.getLastRowTitle();
HtmlTRBean row = null;
for(int i = 0 ; i < rows.size();i++) {
	row = (HtmlTRBean)rows.get(i);
	row.setClazz("title");
	row.setRequest(request);
	row.setReqParam(form1.getReqData());
	out.println(row.toString());
}
%>
<%--         --%>
<%-- 顯示數據  --%>
<%--         --%>
<%
//
String style = "odd";
int index = 0 ;
while(data1.next()) {
	if(index++%2 == 0) {
		style = "odd";
	} else {
		style = "even";
	}
%>
			<tr align="center" bgcolor="#FFFFFF" class="<%=style%>" id="data_tr">
<%
	HtmlTDBean title = null;
	Object value = "";
	String format = "";
	String tdClass = "lisTd";
	Map line = null;
	int cellCharLength = 20;
	for(int j = 0 ; j < titleData.size();j++) {
		title = (HtmlTDBean)titleData.get(j);
		line = data1.getLineData();
		//System.out.println("titleid="+title.getId());
		value = line.get(title.getId().toUpperCase());
		if(title.getSearchValue() != null) {
			value = UIToolUtils.getValue(title.getSearchValue(),StringTools.valueOf(value));
		}
		//如果當前值是數字，則使用不同的className
		if(value instanceof Number){
			tdClass = "lisNumTd";
		} else {
			tdClass = "lisTd" + title.getField() ;;//+ "_" + j;
		}
		//格式化顯示內容,如果是使用JAVACODE
		format = title.getFieldFormat();
		String align="";
		if( format == null || format.length() == 0) {
			//
		} else if(format.toLowerCase().startsWith("javacode://")) {
			format = UIToolUtils.formatUrl(UIToolUtils.formatUrl(format,data1.getLineData(),false),request,form1.getReqData(),false);
			value = String.valueOf(UIToolUtils.parseJavaCode(format));
		} else if(format.equals("%")){ //處理顯示百分數
			value = StringTools.toPercentString(StringTools.valueOf(value));
		} else if(format.startsWith(".")) { //處理顯示小數位數
			value = StringTools.format(((Number)value).doubleValue(),format.length()-1);
		} else if(format.toLowerCase().startsWith("jtvt://")) { //java code target validate tool
			//數據範圍檢查plugin==javacode://TargetValidateTool/validateTData/#func_id#/#dimvalues#/#targetdata#[/#dim_split#]
			String uri = format.substring(7);
			String[] uris = uri.split("\\|",10);
			//
			if(uris.length == 3) { //jtvj://#func_id#/#dimvlaues#/#checkdata#/|對齊|格式
				uri = uris[0]; //#func_id#/#dimvlaues#/#checkdata#/#viewData#
				align = uris[1]; //對齊
				format = uris[2]; //格式
				//
				Object viewValue="";
				if(uri !=null && uri.length() >0 ) {
					String viewDataField = uri.substring(uri.lastIndexOf("/")+1);
					viewDataField = viewDataField.replaceAll("#|\\$","");
					viewValue = line.get(viewDataField.toUpperCase());
				} else {
					viewValue = value;
				}
				//
				uri = uri.substring(0,uri.lastIndexOf("/")+1);
				if("%".equals(format)){ //處理顯示百分數
					viewValue = StringTools.toPercentString(StringTools.valueOf(viewValue));
				} else if(format.startsWith(".")) { //處理顯示小數位數
					viewValue = StringTools.format(((Number)viewValue).doubleValue(),format.length()-1);
				}
				if(uris[0]!= null && uris[0].length() > 0) {
					format = "javacode://com.nl.tv.tools.TargetValidateTool/validateTData/" + uri + viewValue;
					format = UIToolUtils.formatUrl(UIToolUtils.formatUrl(format,data1.getLineData(),false),request,form1.getReqData(),false);
					viewValue = String.valueOf(UIToolUtils.parseJavaCode(format));
				}
				value = viewValue;
			} else {
				format = uris[0];
				if(format !=null && format.length() >0) {
					format = "javacode://com.nl.tv.tools.TargetValidateTool/validateTData/" + format;
					format = UIToolUtils.formatUrl(UIToolUtils.formatUrl(format,data1.getLineData(),false),request,form1.getReqData(),false);
					value = String.valueOf(UIToolUtils.parseJavaCode(format));
				}
			}
			cellCharLength = 1000;
		}
		if("".equals(title.getUrl())) {
%>
				<td class="<%=tdClass%>" title="<%=value%>" align="<%=align%>" nowrap><%=UIToolUtils.getSubString(StringTools.valueOf(value),cellCharLength)%></td>
<%
		} else {
			if("_oper_".equalsIgnoreCase(title.getId())) {
				String[] uriArg = title.getUrl().split(";");
				String[] disTexts = title.getFieldText().split(";");
%>
				<td nowrap >
				<%
				//處理單行多值的問題
				if(disTexts.length > 1) {
					for(int i = 1 ; i < disTexts.length;i++) {
				%>
					<a href="<%=UIToolUtils.formatUrl(UIToolUtils.formatUrl(uriArg[i-1],data1.getLineData()),request,form1.getReqData())%>&otherFields=<%=otherFields%>"><%=disTexts[i]%></a>
				<%
					}
				} else { //兼容以前的配置信息
				%>
					<a href="<%=UIToolUtils.formatUrl(UIToolUtils.formatUrl(uriArg[0],data1.getLineData()),request,form1.getReqData())%>&otherFields=<%=otherFields%>">修改</a>
					<a href="#" onclick="javascript:deleteConfirm('<%=UIToolUtils.formatUrl(UIToolUtils.formatUrl(uriArg[1],data1.getLineData()),request,form1.getReqData())%>&otherFields=<%=otherFields%>');return false;">刪除</a>
				<%} %>
				</td>
<%
			} else {
				boolean encode = title.getUrl().toLowerCase().startsWith("javascript:") == false;
%>
				<td class="<%=tdClass%>" nowrap align="<%=align%>"><a href="<%=UIToolUtils.formatUrl(UIToolUtils.formatUrl(title.getUrl(),data1.getLineData(),encode),request,form1.getReqData(),encode)%>"><%=StringTools.valueOf(value)%></a></td>
<%
			} // enf if
		} // end if
	} // end for
%>
			</tr>
<%
} // end while
} // end if(titleData !=null)
else {out.println("<tr><td align='center'><font color='red'>沒有配置显示信息.</font></td></tr>");}
}
%>
</table>