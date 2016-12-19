<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tags/focus.tld" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>缓存刷新</title>
		<link rel="stylesheet" type="text/css" href="/css/oss.css" />
		<script type="text/javascript" src="/extjs/ext-base.js"></script>
		<script type="text/javascript" src="/extjs/ext-all.js"></script>
		<script type="text/javascript" src="/js/util.js"></script>
		<script src="extjs/validate_form.js" type="text/javascript"></script>
	</head>
	<body>
		<form name="freshForm" method="POST"
			action="/system_config.do?method=refresh_cache"
			enctype="multipart/form-data">
			<table>
				<tr>
					<td align="center">
					请选择要刷新的缓存组件
					</td>
				</tr>
				<tr>
				<td align="center">
				<input id="cacheType" name="cacheType" type="checkbox"
					value="menuTree" checked="checked">
				菜单管理
				<!--
				<input id="cacheType" name="cacheType" type="checkbox"
					value="urlCache">
				url缓存管理
				<input id="cacheType" name="cacheType" type="checkbox"
					value="parameterCache">
				参数管理
				<input id="cacheType" name="cacheType" type="checkbox"
					value="urlScopeCache">
				连接范围管理
				<input id="cacheType" name="cacheType" type="checkbox"
					value="sortScopeCache">
				产品目录管理
				<input id="cacheType" name="cacheType" type="checkbox"
					value="roleModelAspect">
				角色模块管理
				<input id="cacheType" name="cacheType" type="checkbox"
					value="loginUserCache">
				登陸用户管理
				 -->
				</td>
				</tr>
			</table>
			<div class="buttons">
				<input type="submit" value=" 选择 " class="submitc">
			</div>
		</form>
	</body>
</html>