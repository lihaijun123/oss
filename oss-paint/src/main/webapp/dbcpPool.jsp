<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.dbcp.BasicDataSource"%>
<%@page import="com.nl.framework.SystemTool"%>
<%@page import="com.nl.framework.config.DatasourceConfiguration"%>
<%
DatasourceConfiguration dc =(DatasourceConfiguration)SystemTool.getObject(DatasourceConfiguration.ROLE);
String[] names = dc.getDataSourceNames();
%>
<link rel="stylesheet" type="text/css" href="/css/oss.css" />
<table id="list00_table" width="98%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#999999">
<tr bgcolor="#eeeeee" align="center">
	<td>数据源名</td>
	<td>最大等待</td>
	<td>最大空闲</td>
	<td>最大活动数</td>
	<td>活动数</td>
	<td>活动空闲数</td>
</tr>
<%
for(int i = 0 ; names!=null && i < names.length ;i++) {
	BasicDataSource ds = (BasicDataSource)dc.getDataSource(names[i]);
%>
<tr bgcolor="#FFFFFF">
	<td><%=names[i]%></td>
	<td><%=ds.getMaxWait()%></td>
	<td><%=ds.getMaxIdle()%></td>
	<td><%=ds.getMaxActive()%></td>
	<td><%=ds.getNumActive()%></td>
	<td><%=ds.getNumIdle()%></td>
</tr>
<%
}
%>
</table>
