<%@page import="com.focustech.uitool.list.utils.UIToolUtils"%>
<%@page import="com.focustech.uitool.framework.utils.ListTools"%>
<%@page import="com.focustech.uitool.framework.utils.MapTools"%>
<%@page import="com.focustech.uitool.framework.utils.StringTools"%>
<%@page import="com.focustech.uitool.list.dt.DisplayField"%>
<%@page import="com.focustech.uitool.framework.NLGlobal"%>
<%@page import="com.focustech.uitool.list.dt.UIToolOperCtrlBt"%>
<%@page import="com.focustech.uitool.list.form.UIToolOperCtrlForm"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%@ page import="java.util.ArrayList" %>

<%@page import="java.io.Reader"%>
<%@page import="java.io.InputStream"%>
<%
{
//
/**
 * 本JSP根據配置顯示按照一定的顯示規則對添加、修改兩個功能的頁面元素進行顯示，
 * 顯示規則︰
 * 1、首先檢查當前顯示字段的是否根據擴展查詢來取值
 * 2、其次根據顯示字段配置的顯示TAG值一一顯示
 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
 * 注:頁面上的顯示元素都為各功能的共用信息，所以如果要對此JSP中的HTML元素做任何修改之前請先考慮是否影響其它功能。
 * 如果沒有特別的要求請使用JS做相關處理，在IE顯示頁面上如果看到的元素在HTML代碼中找不到請查看相應的JS文件中是否有特殊處理
 */
//
UIToolOperCtrlForm form = (UIToolOperCtrlForm)request.getAttribute(NLGlobal.CURR_FORM_KEY);
UIToolOperCtrlBt operData = form.getOperData() ;
%>

<html>
<head>
<title><%=operData.getPageTitle()%></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/oss.css" rel="stylesheet" type="text/css">
<link href="css/uitool.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="js/uitool.js"></script>
<script language="javascript">
<!--
function openWindow(url,selectType,field) {
	if(url.indexOf('?') > 0) {
		url += "&selectType=" + selectType ;
	} else {
		url += "?selectType=" + selectType ;
	}
	url += "&field="+field;
//	callServer(url);
	var objWin = window.open(url,null,'height=400,width=650,status=yes,toolbar=no,scrollbars=yes,menubar=no,location=no') ;
}
function cleanValue(field) {
	var obj = document.getElementById(field);
	if(obj == undefined) {
		alert(field+"对象不存在。");
	} else {
		obj.value = '' ;
	}
}
function checkSubWindow() {
}
function setBackValue(field,value) {
	var obj = document.getElementById(field);
	if(obj == undefined) {
		alert(field+"对象不存在。");
	} else {
		obj.value = value ;
	}
}
/**
 * 在定義頁面JS文件的時候,如果當前頁面有在提交之前要做檢查的字段,就重寫些方法
 * @return true:檢查結果正確;false:檢查結果不正確
 */
//function check() {}
function doSubmit() {
	parseExtendSelectObj();
	alert(1);
	if(window.check != undefined) {
		if(check())
			document.operForm.submit();
	} else {
		document.operForm.submit();
	}
}
function createHidden(name,value) {
	var hidObj = document.createElement("INPUT");
	hidObj.type = 'hidden';
	hidObj.name = name;
	hidObj.value = value;
	return hidObj;
}
///////////////////////////////////////////

var extendFields = new Array();
function parseExtendSelectObj() {
	if(extendFields == undefined) return ;
	for(var i = 0 ; i < extendFields.length ; i++ ) {
		if(extendFields[i] == '' ) {
			continue;
		}
		var obj = document.getElementById('f_'+extendFields[i]);

		var values =parse(obj.value);
		if(values != undefined || values != null) {
			for(var j = 0 ; j < values.length ; j++) {
				var hid= createHidden(extendFields[i],values[j].replace(/\(|\)/g,""));
				document.operForm.appendChild(hid);
			}
		}
	}
}
function parse(arg) {
	var reg = /\(([\d]+)\)/g;
	var ret = arg.match(reg);
	return ret ;
}
-->
</script>
<!-- css with func_id -->
<%
String[] css = operData.getPageCss() ;
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
String[] js = operData.getPageJs() ;
if(js !=null)
for(int i = 0 ; i < js.length ; i++) {
	if(!"".equals(js[i])) {
%>
<SCRIPT language="javascript" src="<%=js[i]%>"></SCRIPT>
<%
	}
}
%>
</head>

<body onunload="checkSubWindow();">
<form action="uitoolOper.ui" method="get" name="operForm">
<input type="hidden" name="funcID" value="<%=form.getFuncID()%>" />
<input type="hidden" name="toFuncID" value="<%=form.getToFuncID()%>" />
<input type="hidden" name="nextStep" value="execute" />
<%
String otherFields = form.getOtherFields();
%>
<input type="hidden" name="otherFields" value="<%=otherFields%>">
<table id="list00_table" width="100%">
<caption class="x-panel-header"><%=operData.getPageTitle()%></caption>
<tr bgcolor="#FFFFFF">
	<td align="center" bgcolor="#FFFFFF" colspan="2"><html:errors /></td>
</tr>
<%
List lisFields = operData.getLisTitleData();
boolean hasField = false ;
if(lisFields != null && lisFields.size() > 0) {
hasField = true;
DisplayField field = null ;
String value = "";
Object oValue = null;
String readonly = "";
String fieldEvent = "";
String fieldDesc = "";
for(int i = 0 ; i < lisFields.size() ; i++ ){
	field = (DisplayField)lisFields.get(i);
	value = StringTools.notNull(request.getParameter(field.getField()));
//	System.out.println(field.getField()+":1:"+value);
	if(value == null || value.length() <= 0) {
		value = MapTools.getString(form.getReqData(),field.getField().toUpperCase(),field.getDefaultValue()) ;
		oValue = MapTools.getObjectIgnoreCase(form.getReqData(),field.getField().toUpperCase());
	}
//	System.out.println(field.getField()+":2:"+value);
	int displayTag = field.getDisplayTag() ;
	fieldEvent = field.getFieldEvent() ;
	fieldDesc = field.getFieldDesc();
	if(!"".equals(fieldDesc)) {
		fieldDesc = "<font color=\"red\">" + fieldDesc + "</font>;" ;
	}
	// 首先檢查當前顯示是否使用擴展顯示
	if(!"".equals(field.getUrl())) {
		if("".equals(value) && field.getLisDefValue() !=null && field.getLisDefValue().size() > 0) {
			//value = field.getLisDefValue().toString();//(String)field.getLisDefValue().get(0);
			value = ListTools.toString(field.getLisDefValue(),";");
		}
		if("".equals(value)) {
			value=StringTools.notNull(request.getParameter("f_"+field.getField()));
		}
		String trStyle = "";
		if(displayTag == DisplayField.DISPLAY_TAG_HIDDEN) {
			trStyle = "style=\"display:none\"";
		}
%>
	<tr <%=trStyle%>>
		<th><%=field.getFieldText()%>︰</th>
		<td><font color="#ff0000">选择的每個元素以";"为分隔</font>
		<textarea id="f_<%=field.getField()%>" name="f_<%=field.getField()%>" class="extendText" readonly><%=value%></textarea>
		<input type="button" name="selBtn" value="选择..." onclick="openWindow('<%=UIToolUtils.formatUrl(field.getUrl(),request)%>','<%=displayTag%>','f_<%=field.getField()%>')"/>
		<input type="button" name="cleanBtn" value="清除" onclick="cleanValue('f_<%=field.getField()%>')"/>
		<script>extendFields.push('<%=field.getField()%>')</script><%=fieldDesc%> <%=UIToolUtils.showAuditItemAlias(field.getField())%>
		<input type="hidden" name="old_f_<%=field.getField()%>" value="<%=StringTools.notNull(request.getParameter("f_"+field.getField()))%>" />
		</td>
	</tr>
<%
	} else if(displayTag == DisplayField.DISPLAY_TAG_HIDDEN) {
		if("".equals(value) && field.getLisDefValue() !=null && field.getLisDefValue().size() > 0) {
			//value = StringTools.notNull((String)field.getLisDefValue().get(0));
			value = ListTools.toString(field.getLisDefValue(),";");
		}
%>
		<input type="hidden" name="<%=field.getField()%>" value="<%=value%>"/>
<%
	} else if(displayTag == DisplayField.DISPLAY_TAG_TEXT) {
		readonly = field.isWritable()?"":"readonly";
		String typeClass = "strText";
		if(field.getValueType() != DisplayField.VALUE_TYPE_STRING) {
			typeClass = "noStrText";
		}
%>
	<tr>
		<th><%=field.getFieldText()%>︰</th>
		<td>
			<input type="text" class="<%=typeClass%>" name="<%=field.getField()%>" value="<%=value%>" <%=readonly%> <%=fieldEvent%>/>
			<%=fieldDesc%> <%=UIToolUtils.showAuditItemAlias(field.getField())%>
		</td>
	</tr>
<%
	} else if(displayTag == DisplayField.DISPLAY_TAG_TEXTAREA) {
		readonly = field.isWritable()?"":"readonly";
		if(oValue !=null && oValue instanceof oracle.sql.CLOB) {
			Reader is = null;
			StringBuffer sb = new StringBuffer();
			try {
				is= ((oracle.sql.CLOB)oValue).getCharacterStream();
				char[] b = new char[1024];
				int ir = -1 ;
				while((ir=is.read(b))>0) {
					sb.append(new String(b,0,ir));
				}
			} catch(Exception e) {
				//
			} finally {
				if(is !=null)
					is.close();
			}
			value = sb.toString();
		}
%>
	<tr>
		<th><%=field.getFieldText()%>︰</th>
		<td>
			<textarea name="<%=field.getField()%>" cols="80" rows="8" <%=readonly%> <%=fieldEvent%>><%=value%></textarea>
			<%=fieldDesc%>
		</td>
	</tr>
<%
	} else if(displayTag == DisplayField.DISPLAY_TAG_SELECT || displayTag == DisplayField.DISPLAY_TAG_MULTI_SELECT) {
		readonly = field.isWritable()?"":"disabled";
%>
	<tr>
		<th><%=field.getFieldText()%>︰</th>
		<td>
<%
		{
			String multiple = (displayTag == DisplayField.DISPLAY_TAG_MULTI_SELECT)?"multiple size=\"5\"":"";
			String select = "";
			List lisDefValue = null;
			if(field.getLisDefValue() !=null && field.getLisDefValue().size() > 0)  {
				lisDefValue = field.getLisDefValue();
			} else {
				lisDefValue = new ArrayList();
				lisDefValue.add(value);
			}
%>
			<select name="<%=field.getField()%>" <%=multiple%> <%=readonly%> <%=fieldEvent%>>
<%
				if("".equals(multiple)) {
%>
				<option value="<%=field.getDefaultValue()%>">请选择</option>
<%
				} // end if
			List lisValue = field.getSearchValue() ;
			Map temp = null;
			for(int j = 0 ; lisValue!=null && j < lisValue.size() ; j++) {
			temp = (Map)lisValue.get(j);
			if(lisDefValue.indexOf(temp.get("key"))>=0) {
				select = "selected";
			} else {
				select = "";
			}
%>
				<option value="<%=temp.get("key")%>" <%=select%>><%=temp.get("value")%></option>
<%
			}
%>
			</select>
<%
		} // end if "" == field.getUrl()
%>
		<%=fieldDesc%> <%=UIToolUtils.showAuditItemAlias(field.getField())%>
		</td>
	</tr>
<%
	} else if(displayTag == DisplayField.DISPLAY_TAG_RADIO || displayTag == DisplayField.DISPLAY_TAG_CHECKBOX) {
		readonly = field.isWritable()?"":"disabled";
%>
	<tr>
		<th><%=field.getFieldText()%>︰</th>
		<td>
<%
		{
			List lisValue = field.getSearchValue() ;
			Map temp = null;
			String tagType = (displayTag == DisplayField.DISPLAY_TAG_RADIO)?"radio":"checkbox";
			String select = "";
			List lisDefValue = null;
			if(field.getLisDefValue() !=null && field.getLisDefValue().size() > 0)  {
				lisDefValue = field.getLisDefValue();
			} else {
				lisDefValue = new ArrayList();
				if (value.indexOf(",") > -1)
				{
					String[] vaStr = value.split(",");
						for (String str : vaStr)
						{
							lisDefValue.add(str);
						}
				}else if(value.indexOf(";") > -1){
					String[] vaStr = value.split(";");
						for (String str : vaStr)
						{
							lisDefValue.add(str);
						}
				}
				else
				{
				lisDefValue.add(value);
				}
			}

			for(int j = 0 ; lisValue!=null && j < lisValue.size() ; j++) {
				temp = (Map)lisValue.get(j);
				if(lisDefValue.indexOf(temp.get("key"))>=0) {
					select = "checked";
				} else {
					select = "";
				}
%>
				<input type="<%=tagType%>" name="<%=field.getField()%>" value="<%=temp.get("key")%>" <%=select%> <%=readonly%> <%=fieldEvent%>/><%=temp.get("value")%>
<%
			}
		} // end if "" == field.getUrl()
%>
		<%=fieldDesc%>
		</td>
	</tr>
<%
	} else {
		;//info
	} // end if displayTag ...........
} // end for
} // end if lisField !=null
otherFields = UIToolUtils.parseUrlSpecialChar(otherFields,false);
%>
</table>
<br>
<div class="buttons" id="act_panel">
	<input name="Submit" type="button" value="提交" onclick="doSubmit()" <%=hasField?"":"disabled"%>>
	<input name="reset" type="reset" id="reset" value="重置">
	<input name="Submit2" type="button" onClick="window.location='uitoolList.ui?funcID=<%=form.getToFuncID()%>&func_id=<%=StringTools.notNull(request.getParameter("func_id"))%>&detail_func_id=<%=StringTools.notNull(request.getParameter("detail_func_id"))%><%=otherFields%>'" value="返回">
</div>
</form>
</body>
</html>
<%}%>
