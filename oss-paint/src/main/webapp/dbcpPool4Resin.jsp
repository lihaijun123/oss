<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.dbcp.BasicDataSource"%>
<%@page import="com.nl.framework.SystemTool"%>
<%@page import="com.nl.framework.config.DatasourceConfiguration"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="com.caucho.sql.DBPool"%>
<%
Context c = new InitialContext();
DBPool pool = (DBPool)c.lookup("java:comp/env/jdbc/OssDatabase");
%>
<table id="list00_table" width="98%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#999999">
<tr bgcolor="#eeeeee" align="center">
	<td>数据源名</td>
	<td>最大连接数</td>
	<td>总连接数</td>
	<td>活动数</td>
	<td>溢出数</td>
</tr>
<tr bgcolor="#FFFFFF">
	<td><%=pool.getName()%></td>
	<td><%=pool.getMaxConnections()%></td>
	<td><%=pool.getTotalConnections()%></td>
	<td><%=pool.getActiveConnections()%></td>
	<td><%=pool.getMaxOverflowConnections()%></td>
</tr>
<%
pool = (DBPool)c.lookup("java:comp/env/jdbc/MicDatabase");
%>
<tr bgcolor="#FFFFFF">
	<td><%=pool.getName()%></td>
	<td><%=pool.getMaxConnections()%></td>
	<td><%=pool.getTotalConnections()%></td>
	<td><%=pool.getActiveConnections()%></td>
	<td><%=pool.getMaxOverflowConnections()%></td>
</tr>

</table>
