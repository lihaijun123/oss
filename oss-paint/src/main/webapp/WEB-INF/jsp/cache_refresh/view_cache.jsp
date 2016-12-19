<%@ page language="java" import="net.sf.ehcache.*"
	pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="/css/oss.css" />
		<title>连接、缓存查看</title>
	</head>
	<body>
		<%
			Cache parameterCache = (Cache) request.getAttribute("parameterCache");
			Cache urlScopeCache = (Cache) request.getAttribute("urlScopeCache");
			Cache resourceCache = (Cache) request.getAttribute("resourceCache");
			Cache sortScopeCache = (Cache) request.getAttribute("sortScopeCache");
			Cache loginUserCache = (Cache) request.getAttribute("loginUserCache");
			Cache roleModelCache = (Cache) request.getAttribute("roleModelCache");
			Integer resourceCacheActSize = (Integer) request.getAttribute("resourceCache_act_size");
		%>


		<table id="list00_table3" width="98%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#999999">
			<thead align="center">
				连接资源缓存
			</thead>
			<tr bgcolor="#eeeeee" align="center">
				<th>
					缓存数量
				</th>
				<td><%=resourceCache.getStatistics().getObjectCount()%>/<%=resourceCacheActSize%></td>
			</tr>
			<tr bgcolor="#eeeeee" align="center">
				<th>
					缓存平均获取时间(ms)
				</th>
				<td><%=resourceCache.getAverageGetTime()%></td>
			</tr>
			<tr bgcolor="#eeeeee" align="center">
				<th>
					缓存命中次数
				</th>
				<td><%=resourceCache.getStatistics().getCacheHits()%></td>
			</tr>
		</table>


	</body>
</html>
