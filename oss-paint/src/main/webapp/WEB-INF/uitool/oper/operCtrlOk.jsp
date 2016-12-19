<%@page import="com.focustech.uitool.framework.NLGlobal"%>
<%@page import="com.focustech.uitool.list.utils.UIToolUtils"%>
<%@page import="com.focustech.uitool.framework.utils.StringTools"%>
<%@page import="com.focustech.uitool.list.form.UIToolOperCtrlForm"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<%
{
UIToolOperCtrlForm form = (UIToolOperCtrlForm)request.getAttribute(NLGlobal.CURR_FORM_KEY);
String func_id = StringTools.notNull(request.getParameter("func_id"));
String detail_func_id = StringTools.notNull(request.getParameter("detail_func_id"));
String otherFields = form.getOtherFields();
String url = "uitoolList.ui?funcID="+form.getToFuncID()+"&func_id="+func_id+"&detail_func_id=" + detail_func_id + otherFields ;
url =UIToolUtils.parseUrlSpecialChar(url,false);
%>

<link href="css/uitool.css" rel="stylesheet" type="text/css">
<%--
<p> 更新成功<p/>
<a href="<%=url%>">返回列表</a>
--%>

<script>
window.location='<%=url%>';
</script>
<%}%>