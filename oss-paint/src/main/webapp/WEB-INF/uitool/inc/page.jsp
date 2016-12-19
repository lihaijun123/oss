<%--
1、在主文件中添加入 <%@include file="/inc/page.jsp"%>
2、在相應的actionForm中必須給出“totalRec”、“currPage” eg:;
3、在相應需要的時候給出“findWhere”、“operation” eg:;
--%>
<%@page import="com.focustech.uitool.framework.form.NLActionForm"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Map" %>

<%@page import="java.util.List"%>
<%
{
NLActionForm pageForm = (NLActionForm)request.getAttribute(NLGlobal.CURR_FORM_KEY);
//總記錄數
long totalRec = pageForm.getTotalRec();
long total = 0 ;
int maxRec = pageForm.getPageRecMaxLimit();
if(totalRec%maxRec > 0){
	total = totalRec/maxRec +1;
}else{
	total = totalRec/maxRec ;
}
//當前頁碼
long currPage = pageForm.getCurrPage();
if(currPage <= 0) {
	currPage = 1;
}
//當前頁面的查詢條件
String findWhere = pageForm.getFindWhere();
if(findWhere.length() > 0) {
	findWhere = "&findWhere=" + findWhere.replaceAll("\\%","%25").replaceAll("&","%26") ;
	findWhere = findWhere.replaceAll("'","%27");
}
findWhere = "";
//當前操作
String action = pageForm.getNextStep();
action = "&nextStep=" + action ;
String otherFields = pageForm.getOtherFields();
if(!otherFields.startsWith("&") && !"".equals(otherFields)) {
	otherFields = "&" + otherFields;
}
//otherFields = otherFields.replaceAll("\\%","%25").replaceAll("'","%27").replaceAll("\"","%22");
List pageHidFields = (List)request.getAttribute("lisPageHidField");
%>
    <tr align="right">
        <td height="25" colspan="4" align="right" valign="bottom">
            <form name="theForm" id="theForm" method="get" action="?">
<input type="hidden" id="id_orderField" name="orderField" value="<%=StringTools.valueOf(request.getParameter("orderField"))%>">
<input type="hidden" id="id_orderOper" name="orderOper" value="<%=StringTools.valueOf(request.getParameter("orderOper"))%>">
<input type="hidden" name="totalRec" value="<%=totalRec%>">
<input type="hidden" name="nextStep" value="">
<%
for(int i = 0 ; pageHidFields!=null && i < pageHidFields.size() ; i++) {
	out.println(pageHidFields.get(i));
}
if(currPage > 1){
%>
				<a>当前页数/总页数︰<%=currPage%>/<%=total%> 总记录数︰<font color="red"><%=totalRec%></font>&nbsp;|&nbsp;</a>
				<a href="?pageLimit=<%=maxRec%>&totalRec=<%=totalRec%>&currPage=1&orderField=<%=StringTools.valueOf(request.getParameter("orderField"))%>&orderOper=<%=StringTools.valueOf(request.getParameter("orderOper"))%><%=findWhere%><%=action%><%=otherFields%>">首页</a>
                <a href="?pageLimit=<%=maxRec%>&totalRec=<%=totalRec%>&currPage=<%=currPage-1%>&orderField=<%=StringTools.valueOf(request.getParameter("orderField"))%>&orderOper=<%=StringTools.valueOf(request.getParameter("orderOper"))%><%=findWhere%><%=action%><%=otherFields%>">上一页</a>
<%}else{%>
				<a>当前页数/总页数︰<%=currPage%>/<%=total%> 总记录数︰<font color="red"><%=totalRec%></font>&nbsp;|&nbsp;</a>
                <a disabled>首页</a>
                <a disabled>上一页</a>
<%}
if(currPage < total){
%>
                <a href="?pageLimit=<%=maxRec%>&totalRec=<%=totalRec%>&currPage=<%=currPage+1%>&orderField=<%=StringTools.valueOf(request.getParameter("orderField"))%>&orderOper=<%=StringTools.valueOf(request.getParameter("orderOper"))%><%=findWhere%><%=action%><%=otherFields%>">下一页</a>
                <a href="?pageLimit=<%=maxRec%>&totalRec=<%=totalRec%>&currPage=<%=total%>&orderField=<%=StringTools.valueOf(request.getParameter("orderField"))%>&orderOper=<%=StringTools.valueOf(request.getParameter("orderOper"))%><%=findWhere%><%=action%><%=otherFields%>">尾页</a>
<%}else{%>
                <a disabled>下一页</a>
                <a disabled>尾页</a>
<%}%>

               转到第<input type="text" id="currPage" name="currPage" value="<%=currPage%>" size="3" maxlength="3" onkeypress="doPageKeyPress(event)">页;&nbsp;每页显示条数︰<input type="text" name="pageLimit" value="<%=maxRec%>" size="3" maxlength="3" onkeypress="doPageKeyPress(event)">
            </form>
        </td>
    </tr>

<script>
function doPageKeyPress(e) {
	e = e? e : window.event;
	var code = e.keyCode;
	if(code >=0 && code !=13) {
		return ;
	}
	if(code == 13 || code == 0) {
		var currPage = document.getElementById("currPage").value;
		var total = <%=total%>;
		if( currPage > total) {
			currPage = total;
		}
		document.getElementById("currPage").value = currPage ;
		document.theForm.submit();
	}
}
</script>
<%
}
%>