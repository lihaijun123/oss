<%@page import="com.focustech.uitool.list.utils.UIToolUtils"%>
<%@page import="com.focustech.uitool.list.dt.DisplayField"%>
<%@page import="com.focustech.uitool.list.utils.UIToolConst"%>
<%@page import="com.focustech.uitool.framework.NLGlobal"%>
<%@page import="com.focustech.uitool.list.form.UIToolListCtrlForm"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
{
UIToolListCtrlForm formH = (UIToolListCtrlForm) request.getAttribute(NLGlobal.CURR_FORM_KEY);
List allFields = formH.getData().getLisFindFields();
//in search form
List lisHidenField = new ArrayList();
//in page form
List lisPageHidField = new ArrayList();
//
//處理otherWhere和查詢時要做hidden的字段，同時過濾掉不要hidden的字段
Enumeration eParam = request.getParameterNames();
String key = "";
List cancelKey = new ArrayList();
cancelKey.add("searchOper");
cancelKey.add("searchField");
cancelKey.add("searchValue");
cancelKey.add("searchBtn");
cancelKey.add("findWhere");
cancelKey.add("totalRec");
cancelKey.add("superID");
cancelKey.add("currPage");
cancelKey.add("nextStep");
cancelKey.add("pageLimit");
cancelKey.add("orderOper");
cancelKey.add("orderField");
cancelKey.add("uplike");
cancelKey.add("condoper");
cancelKey.add("otherFields");
cancelKey.add("chartType");
cancelKey.add("_st_");
cancelKey.add(UIToolConst.CUSTOMER_FIELD_SEARCH);
cancelKey.add(UIToolConst.CUSTOMER_FIELD_ORDER);
cancelKey.add(UIToolConst.CUSTOMER_FIELD_DISPLAY);
cancelKey.add(UIToolConst.REQUEST_KEY_INIT_FETCH_KEY);
//
//過濾當前查詢的字段信息，同時把查詢的值放入查詢信息中
for(int i = 0 ; allFields!=null && i < allFields.size() ; i++ ) {
	DisplayField ff = (DisplayField)allFields.get(i) ;
	cancelKey.add(ff.getField());
}

String otherWhere = "";
while (eParam.hasMoreElements()) {
	key = (String)eParam.nextElement();
	request.setAttribute(key,request.getParameter(key));
	if(key.equalsIgnoreCase("totalRec") || key.equalsIgnoreCase("currPage") || key.equalsIgnoreCase("nextStep")
		|| key.equalsIgnoreCase("pageLimit")
		|| key.equalsIgnoreCase("otherFields")
		|| key.equalsIgnoreCase("orderField")
		|| key.equalsIgnoreCase("orderOper")
		|| key.equalsIgnoreCase("searchBtn")) {
	} else {
		String[] values = request.getParameterValues(key);
		for(int i = 0 ; values!=null && i < values.length ;i++) {
			otherWhere += "&" + key+"=" + UIToolUtils.parseUrlSpecialChar(values[i],true).replaceAll("\"","%22");
			lisPageHidField.add("<input type=\"hidden\" id=\"hid_"+ key +"\" name=\""+key+"\" value=\""+ values[i] +"\" />");
		}
	}
	if(cancelKey.contains(key) || key.startsWith("E_") || key.startsWith("S_"))
		continue;
	lisHidenField.add(key);
}
//
request.setAttribute("lisPageHidField",lisPageHidField);
request.setAttribute("lisHidenField",lisHidenField);
formH.setOtherFields(otherWhere);

}
%>