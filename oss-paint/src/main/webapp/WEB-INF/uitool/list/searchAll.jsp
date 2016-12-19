<%@page import="com.focustech.uitool.list.utils.UIToolConst"%>
<%@page import="com.focustech.uitool.framework.NLGlobal"%>
<%@page import="com.focustech.uitool.list.dt.UIToolListCtrlBt"%>
<%@page import="com.focustech.uitool.list.form.UIToolListCtrlForm"%>
<%@page import="com.focustech.uitool.framework.utils.StringTools"%>
<%@page import="com.focustech.uitool.list.dt.DisplayField" %>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>

<% {
String SF_K =StringTools.notNull((String)request.getAttribute("SF_K"));
if(SF_K.length() <=0) {
    return;
}
UIToolListCtrlForm form2 = (UIToolListCtrlForm) request.getAttribute(NLGlobal.CURR_FORM_KEY);
UIToolListCtrlBt data2 = form2.getData();
List findFields2 = data2.getAllData();
%>

<script>
//-------------
function showAllSearchField() {
	var divAll = document.getElementById("div_searchAll");
	var searchHidShw = document.getElementById("searchHidShw");
	var xy = getAbsPoint(searchHidShw)
	divAll.style.display = "none";
	divAll.style.display = "";
	divAll.style.top = xy.y + "px";
	//alert(document.body.clientWidth + "=" + xy.x + "="+(xy.x+ divAll.clientWidth));
	if((xy.x+ divAll.clientWidth) > document.body.clientWidth ) {
		xy.x += document.body.clientWidth - (xy.x+ divAll.clientWidth) -10 ;
	}
	divAll.style.left = xy.x + "px";
	var div_data = document.getElementById("div_data");
	var h = parseInt(div_data.style.height.replace("px",""),10);
	if(div_data.scrollHeight > h) {
		div_data.style.height = div_data.scrollHeight + 20;
		divAll.style.height = div_data.scrollHeight + 23 + 20;
	}
	var sf = document.getElementById("s_f");
	if(sf.value != ""){
		editCheck();
	}
	return false;
}
function hidAllSearchField() {
	var divAll = document.getElementById("div_searchAll");
	divAll.style.display = "none";
	return false;
}
function getTop(obj) {
	var t = 0;
	while(obj) {
		if(obj.offsetTop)
			t += obj.offsetTop;
		obj=obj.parentNode;
	}
	return t;
}
function getLeft(obj) {
	var l = 0;
	while(obj) {
		if(obj.offsetLeft)
			l += obj.offsetLeft;
		obj=obj.parentNode;
	}
	return l;
}

function getAbsPoint(e){
  var x = e.offsetLeft;
  var y = e.offsetTop + e.offsetHeight;
  while(e = e.offsetParent){
    x += e.offsetLeft;
    y += e.offsetTop;
  }
  return {"x": x, "y": y};
}
function saveSF(save) {
	var sf = document.getElementById("s_f");
	var of = document.getElementById("o_f");
	var df = document.getElementById("d_f");
	//SF
	var SF_ALL = document.getElementsByName("SF_ALL_F");
	var tmp = "";
	for(var i = 0 ; i < SF_ALL.length;i++) {
		if(SF_ALL[i].checked)
			tmp += "," + SF_ALL[i].value;
	}
	sf.value = tmp + ",";
	//OF
	tmp = "";
	SF_ALL = document.getElementsByName("SF_ALL_O");
	for(var i = 0 ; i < SF_ALL.length;i++) {
		if(SF_ALL[i].checked)
			tmp += "," + SF_ALL[i].value;
	}
	of.value = tmp + ",";
	//DF
	tmp = "";
	SF_ALL = document.getElementsByName("SF_ALL_D");
	for(var i = 0 ; i < SF_ALL.length;i++) {
		if(SF_ALL[i].checked)
			tmp += "," + SF_ALL[i].value;
	}
	df.value = tmp + ",";
	//
	hidAllSearchField();
	if(save ==1) {
		var nextStep = document.searchForm.nextStep;
		if(nextStep) {
			nextStep[0].value ="cust";
		} else {
			createHField('nextStep','cust');
		}
	}
	createHField('<%=UIToolConst.REQUEST_KEY_INIT_FETCH_KEY%>','true');
	try {
		document.searchForm.method="post";
		document.searchForm.submit();
	}catch(e){
		document.searchForm.onSubmit();
	}
}
function setDefault() {
	if(confirm("您确定要恢复默认设置吗？") == false) {
		return;
	}
	var nextStep = document.searchForm.nextStep;
	if(nextStep) {
		nextStep[0].value ="custdel";
	} else {
		createHField('nextStep','custdel');
	}
	createHField('<%=UIToolConst.REQUEST_KEY_INIT_FETCH_KEY%>','true');
	document.searchForm.submit();
}
function createHField(name,value) {
	var ele = document.createElement("INPUT");
	ele.type = "hidden";
	ele.name=name;
	ele.value= value;
	document.searchForm.appendChild(ele);
}
function editCheck() {
	document.getElementById("ok").disabled = false;
	document.getElementById("save").disabled = false;
}

UITool.AddEventHandler(window,"load",function(){
    var sf = document.getElementById("s_f");
    if(sf.value != ""){
        document.getElementById("alert_msg").style.display='';
    }
    }
);
</script>
<form name="form_custInfo" action="" method="get">
<div id="div_searchAll" style="display:none;position:absolute;height:190px;width:90%;border:2px solid gray;padding:2px 2px 2px 0px;background-color:#fff;">
<iframe name="frm_custInfo" id="frm_custInfo" style="display:none" src="about:blank"></iframe>
<div style="height:22px;text-align:right;border-bottom:2px solid gray;margin:2px 0px 0px 0px;z-index:999">
<input type="button" id="ok" value=" 确认 " title="预览当前设置" disabled onclick="saveSF()"/>
<input type="button" id="save" value=" 保存 " title="保存当前设置，保存后以后再打开此功能时使用保存的设置显示" disabled onclick="saveSF(1)" />
<input type="button" id="cancel" value=" 清除 " title="恢复默认设置" onclick="setDefault()"/>
<input type="button" id="cancel" value="取消" onclick="hidAllSearchField()"/>
</div>
<div id="div_data" style="height:165px;width:100%;overflow:scroll;">
<%
String[] oper_char2 = new String[]{"模糊","等","不等","下拉框","范围"};
String chkFind = "";
String chkOrder = "";
String chkDisplay = "";
String view ="";
StringBuffer sbHead = new StringBuffer();
StringBuffer sbFind = new StringBuffer();
StringBuffer sbOrder = new StringBuffer();
StringBuffer sbDisplay = new StringBuffer();
for(int i = 0 ; findFields2!=null && i < findFields2.size() ; i++ ) {
	DisplayField ff = (DisplayField)findFields2.get(i) ;
	//
	chkFind = "";
	chkOrder = "";
	chkDisplay = "";
	view="";
	if(ff.isDispaly()) {
		chkDisplay = "checked";
	}
	if(ff.isHasDisplayPure() == false || ff.isDefDispaly() == false) {
		chkDisplay += " disabled";
	}
	//
	if(ff.isOrder()) {
		chkOrder = "checked";
	}
	if(ff.isHasOrderPure() == false || ff.isDefOrder() == false) {
		chkOrder += " disabled";
	}
	//
	if(ff.isFind()) {
		chkFind = "checked";
	}
	if(ff.isHasFindPure() == false || ff.isDefFind() == false) {
		chkFind += " disabled";
	}
	if(UIToolConst.OPER_FIELD_NAME.equals(ff.getField())) {
		chkFind = " disabled";
		chkOrder = " disabled";
		chkDisplay = "checked disabled";
		view="style=\"display:none\"";
	}
	//+"--"+ oper_char2[ff.getFindType()]
	sbHead.append("<td class=\"title\" "+ view +">"+ff.getFieldText() +"</td>");
	sbFind.append("<td "+ view +"><input type=\"checkbox\" "+chkFind+" name=\"SF_ALL_F\" value=\""+ff.getField()+"\" onclick=\"editCheck()\"></td>");
	sbOrder.append("<td "+ view +"><input type=\"checkbox\" "+chkOrder+" name=\"SF_ALL_O\" value=\""+ff.getField()+"\" onclick=\"editCheck()\"></td>");
	sbDisplay.append("<td "+ view +"><input type=\"checkbox\" "+chkDisplay+" name=\"SF_ALL_D\" value=\""+ff.getField()+"\" onclick=\"editCheck()\"></td>");

%>
<%
} // end for
%>
<table id="searchAllTab" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#C4D7FF">
<tr>
<td class="title">&nbsp;</td><%=sbHead%>
</tr>
<tr class="even">
<td class="title">查詢</td><%=sbFind%>
</tr>
<tr class="odd">
<td class="title">排序</td><%=sbOrder%>
</tr>
<tr class="even">
<td class="title">显示</td><%=sbDisplay%>
</tr>
</table>
</div>
</div>
</form>
<div id="alert_msg" onclick="showAllSearchField();" style="cursor:pointer;position:absolute;color: red;font-weight:bold;top: 4px;right: 0px;display: none">是否要保存当前显示配置信息？</div>
<%
}
%>
