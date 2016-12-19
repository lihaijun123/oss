<%@page import="com.focustech.uitool.framework.NLGlobal"%>
<%@page import="com.focustech.uitool.list.dt.UIToolListCtrlBt"%>
<%@page import="com.focustech.uitool.list.form.UIToolListCtrlForm"%>
<link href="css/oss.css" rel="stylesheet" type="text/css">
<%@ taglib uri="/WEB-INF/tags/focus.tld" prefix="f"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>

<%@page import="java.util.Map"%>

<jsp:directive.page import="java.util.HashMap"/>
<jsp:directive.page import="java.util.ArrayList"/>

<%@page import="com.focustech.oss2008.utils.RoleResourceUtils"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="org.apache.struts.util.ResponseUtils"%>
<%@page import="com.focustech.oss2008.Constants"%>
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
<%
if(data1.getTotalLimit() <=0 || form1.getTotalRec() <= data1.getTotalLimit()) {
long totalRec = form1.getTotalRec();
long total = 0 ;
int maxRec = form1.getPageRecMaxLimit();
if(totalRec%maxRec > 0){
	total = totalRec/maxRec +1;
}else{
	total = totalRec/maxRec ;
}
%>
<table id="list00_rectable" width="98%" border="0" align="center" cellpadding="1" cellspacing="0" bgcolor="#C4D7FF">
<tr><td>当前页数/总页数︰<%=form1.getCurrPage()<=0?1:form1.getCurrPage()%>/<%=total%> 总记录数︰<font color="red"><%=totalRec%></font></td></tr>
</table>
<%} %>
<table id="list00_table" align="center" cellpadding="1" cellspacing="1" bgcolor="#C4D7FF">
	<tr id="id_tr_0" class="title">
	<td width="5%">序号</td>
	<td width="30%">联系人</td>
	<td width="50%">联系信息</td>
	<td width="10%" align="center"><%if(RoleResourceUtils.check(555L,pageContext)){ %><a href="javascript:popDWindow('添加联系人信息','contact.do?method=add&customerId=<%=request.getParameter("customerId")%>',750,480)">添加</a><%}%></td>
	</tr>
<%--         --%>
<%-- 顯示數據  --%>
<%--         --%>
<%
//
String style = "odd";
int index = 0 ;
Map line = null;
Map roleCache = new HashMap();
String tel ="";
String mobile ="";
String fax ="";
String[] tels = null;
String[] mobs = null;
String[] faxs = null;
while(data1.next()) {
	if(index++%2 == 0) {
		style = "odd";
	} else {
		style = "even";
	}
	//
	line = data1.getLineData();
	tel =(String)line.get("TELEPHONE");
	if(tel!=null){
		tels = tel.split(",");
	}
	mobile =(String)line.get("MOBILE_TELEPHONE");
	if(mobile!=null){
		mobs = mobile.split(",");
	}
	fax =(String)line.get("FAX");
	if(fax!=null){
		faxs = fax.split(",");
	}
%>

<%if(Constants.CONTACT_TYPE_DISABLED.equals((String)line.get("TYPE"))&&!RoleResourceUtils.check(974L,pageContext)){ %>
			<tr align="center" bgcolor="#FFFFFF" class="<%=style%>" id="data_tr">
			<td id="rownum"><%=line.get("NUM_ROW_ZZZ")%></td>
			<td colspan="3">
				<table width="100%">
					<tr>
						<th width="15%">联系人ID:</th>
						<td width="25%"><%=line.get("CONTACT_ID")%></td>
						<th width="15%">联系人:</th>
						<td width="45%"><%=line.get("FULLNAME")%>&nbsp;&nbsp;(<f:SystemParamTag paramKey='<%=(String)line.get("TYPE")%>' paramType='PARAM_TYPE_CONTACT_TYPE' />)</td>
					</tr>
					<tr>
						<th>称呼:</th>
						<td><f:SystemParamTag paramKey='<%=(String)line.get("SALUTATION")%>' paramType="PARAM_TYPE_CONTACT_SALUTATION" /></td>
						<th>性別:</th>
						<td><f:SystemParamTag paramKey='<%=(String)line.get("SEX")%>' paramType="PARAM_TYPE_SEX_TYPE" /></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<th>部门:</th>
						<td>
						<% String depart = (String)line.get("DEPARTMENT");
						if(depart!=null)
						{%>
						<%=depart%>
						<%} %>
						</td>
						<th>职位:</th>
						<td>
						<% String duty = (String)line.get("DUTY");
						if(duty!=null)
						{%>
						<%=duty%>
						<%} %>
						</td>
					</tr>
				</table>
			</td>
			</tr>
<%}else{ %>
			<tr align="center" bgcolor="#FFFFFF" class="<%=style%>" id="data_tr">
			<td id="rownum"><%=line.get("NUM_ROW_ZZZ")%></td>
			<td>
				<table width="100%">
					<tr>
						<th>联系人ID:</th>
						<td>
						<%if(RoleResourceUtils.check(557L,pageContext)){ %>
						<a href="javascript:popDWindow('联系人详细信息','contact.do?method=details&contactId=<%=line.get("CONTACT_ID")%>',750,480);"><%=line.get("CONTACT_ID")%></a>
						<%}else{ %>
						<%=line.get("CONTACT_ID")%>
						<%} %>
						</td>
					</tr>
					<tr>
						<th>联系人:</th>
						<td><%=line.get("FULLNAME")%>&nbsp;&nbsp;(<f:SystemParamTag paramKey='<%=(String)line.get("TYPE")%>' paramType="PARAM_TYPE_CONTACT_TYPE" />)</td>
					</tr>
					<tr>
						<th>称呼:</th>
						<td><f:SystemParamTag paramKey='<%=(String)line.get("SALUTATION")%>' paramType="PARAM_TYPE_CONTACT_SALUTATION" /></td>
					</tr>
					<tr>
						<th>职位:</th>
						<td>
						<% String duty = (String)line.get("DUTY");
						if(duty!=null)
						{%>
						<%=duty%>
						<%} %>
						</td>
					</tr>
					<tr>
						<th>部门:</th>
						<td>
						<% String depart = (String)line.get("DEPARTMENT");
						if(depart!=null)
						{%>
						<%=depart%>
						<%} %>
						</td>
					</tr>
					<tr>
						<th>性別:</th>
						<td><f:SystemParamTag paramKey='<%=(String)line.get("SEX")%>' paramType="PARAM_TYPE_SEX_TYPE" /></td>
					</tr>
				</table>
			</td>
			<td colspan="2" width="50%">
				<table width="100%">
					<tr>
					<th>电话:</th>
					<td>
						<table>
						<%
						if(tels!=null)
						{
							for(int iTel = 0 ; iTel < tels.length ; iTel++) {
						%>
							<tr><td><%=tels[iTel]%></td></tr>
						<%	}
						}%>
						</table>
					</td>
					</tr>
					<tr>
					<th>手机:</th>
					<td>
						<table>
						<%
						if(mobs!=null)
						{
							for(int iMob = 0 ; iMob < mobs.length ; iMob++) {
						%>
							<tr><td><%=mobs[iMob]%></td></tr>
						<%	}
						}%>
						</table>
					</td>
					</tr>
					<tr>
					<th>传真:</th>
					<td>
						<table>
						<%
						if(faxs!=null)
						{
							for(int iFax = 0 ; iFax < faxs.length ; iFax++) {
						%>
							<tr><td><%=faxs[iFax]%></td></tr>
						<%	}
						}%>
						</table>
					</td>
					</tr>
					<tr>
					<th>EMAIL1:</th>
					<td>
					<% String email = (String)line.get("EMAIL1");
						if(email!=null)
						{%>
						<a href="mailto:<%=email%>"><%=email%></a>
						<%} %>
					</td>
					</tr>
					<tr>
					<th>EMAIL2:</th>
					<td>
					<% email = (String)line.get("EMAIL2");
						if(email!=null)
						{%>
						<a href="mailto:<%=email%>"><%=email%></a>
						<%} %>
					</td>
					</tr>
				</table>
			</td>
			</tr>
			<tr align="right">
				<td colspan="4">
				<%if(RoleResourceUtils.check(556L,pageContext)){ %><a href="javascript:popDWindow('修改联系人信息','contact.do?method=modify&contactId=<%=line.get("CONTACT_ID")%>&relatedId=<%=request.getParameter("customerId") %>',750,480)">修改</a>&nbsp;&nbsp;<%}%>
				<%if(RoleResourceUtils.check(558L,pageContext)){ %><a href="javascript:deleteConfirm('contact.do?method=delete&operType=0&contactId=<%=line.get("CONTACT_ID")%>&relatedId=<%=request.getParameter("customerId") %>')">刪除</a>&nbsp;&nbsp;<%}%>
				<%if(RoleResourceUtils.check(564L,pageContext)){ %><a href="javascript:openwinParam('contactRecord.do?method=add&customerId=<%=request.getParameter("customerId") %>&contactId=<%=line.get("CONTACT_ID")%>&contactName=','<%=line.get("FULLNAME")%>')">添加联系记录</a><%}%>
				</td>
			</tr>
<%} %>
<%
tels = null;
mobs = null;
faxs = null;
	}
}
%>
</table>
