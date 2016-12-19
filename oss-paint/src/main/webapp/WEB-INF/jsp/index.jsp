<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="com.focustech.oss2008.service.OssAdminParameterService"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ taglib uri="/WEB-INF/tags/focus.tld" prefix="f" %>
<%
List datas = new ArrayList();
Map tmp = new HashMap();
tmp.put("value","value_test1");
tmp.put("text","text_test1");
datas.add(tmp);
tmp = new HashMap();
tmp.put("value","value_test2");
tmp.put("text","text_test2");
datas.add(tmp);
tmp = new HashMap();
tmp.put("value","value_test3");
tmp.put("text","text_test3");
datas.add(tmp);
%>
<html>
<link rel="stylesheet" type="text/css" href="/resources/css/ext-all.css" />
<script type="text/javascript" src="/extjs/ext-base.js"></script>
<script type="text/javascript" src="/extjs/ext-all.js"></script>
<script type="text/javascript" src="/extjs/department_user.js"></script>
<body>
<!-- User Defined Js -->
<%--
<script src="extjs/style.js" type="text/javascript"></script>
<script src="extjs/validate_form.js" type="text/javascript"></script>
--%>

<f:complex name="radio1" paramType="PARAM_TYPE_ACCOUNT_TYPE" siteType="1" type="radio" firstItemText="請選擇" firstItemValue="" itemLabel="parameterValue" itemValue="parameterKey" defaultValue="1" />
<br/>
<f:complex name="chk1" paramType="PARAM_TYPE_CONTACT_STATUS" siteType="1" type="checkbox"  firstItemText="請選擇" firstItemValue="" itemLabel="parameterValue" itemValue="parameterKey" defaultValue="2" />
<br/>
<f:complex name="sel1" paramType="PARAM_TYPE_ACTIVE" siteType="1" type="select"  firstItemText="請選擇" firstItemValue="0" itemLabel="parameterValue" itemValue="parameterKey" defaultValue="2" />
<br/><br/>
 
 <form id="ossAdminUser" method="post" action="/user.do?method=add">
<f:complex name="email" items="<%=datas%>" itemLabel="text" itemValue="value" otherAttrs="onchange='validateField(this)'" siteType="1" firstItemText="請選擇" firstItemValue="1" defaultValue="0" />
</form>


<br/>
<br/>
<br/>
<script>

</script>
<!-- 
<div id="test"></div>
<div id="viewTarget1">XXX︰</div>
<script>new DepUserTag("viewTarget1",{"departmentId":"","orgType":"2","orgRange":"2","defOrgId":"","userId":"","userType":"2","defUserId":""},"departmentId","saleId");</script>
-->
<br/><br/>
<div id="viewTarget2">YYY︰</div>
<script>new DepUserTag("viewTarget2",{"departmentId":"0000","orgType":"2","orgRange":"2","defOrgId":"","userId":"","userType":"1","defUserId":""},"departmentId1","dd");</script>


<br/><br/>
<div id="viewTarget3">zzzz︰</div>
<script>new DepUserTag("viewTarget3",{},"departmentId2","saleId2","editable");</script>
</body>
</html>