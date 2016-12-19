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
		<title>動態密碼認證服務器地址刷新</title>
		<link rel="stylesheet" type="text/css" href="/css/oss.css" />
		<script type="text/javascript" src="/extjs/ext-base.js"></script>
		<script type="text/javascript" src="/extjs/ext-all.js"></script>
		<script type="text/javascript" src="/js/util.js"></script>
		<script src="extjs/validate_form.js" type="text/javascript"></script>
	</head>
	<body>
		<form name="freshForm" method="POST"
			action="/system_config.do?method=refresh_dyn">
			<table>
				<c:if test="${!empty msg}">
					<tr>
						<td align="center" colspan="6">
						<font color="red">${msg}</font>
						</td>
					</tr>
				</c:if>
				<tr><td>
				<input id="type" name="type" type="radio"
					value="0" checked="checked">自動開始尋找
				</td></tr>
				<tr><td>
				<input id="type" name="type" type="radio"
					value="1">設置指定的服務器地址︰
					<input id="addr" name="addr" type="text" size="70"/>
				</td></tr>
				<tr><td>
				注︰當前使用的認證服務器地址為︰<b>${nowDynServer}</b>
				</td></tr>
			</table>
			<div class="buttons">
				<input type="submit" value=" 選 擇 " class="submitc">
			</div>
		</form>
	</body>
</html>