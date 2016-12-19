<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>产品目录</title>
		<script type="text/javascript" src="/extjs/ext-base.js"></script>
		<script type="text/javascript" src="/extjs/ext-all.js"></script>
		<script src="extjs/style.js" type="text/javascript"></script>
		<style>
			li,a{width:auto;font-size:12px}
			.yellow_color{background-color: #FFFF00}
		</style>
	</head>
	<body>
		<div id="root">
			<h4 align="center">
				焦点科技产品目录结构
			</h4>
			<div id="main">
				<c:forEach var="m" items="${title}">
					<a href="#${m.catCode }">&nbsp;${m.catNameCn }</a>
				</c:forEach>
				<hr>
				<ul>
					<c:forEach var="a" items="${main}">
						<c:if test="${a.catStatus=='1'}">
							<c:if test="${a.catLevel==1}"><%="<li>"%></c:if>
							<c:if test="${a.catLevel==2}"><%="<ul><li>"%></c:if>
							<c:if test="${a.catLevel==3}"><%="<ul><ul><li>"%></c:if>
										<span <c:if test="${a.recId>99999999}">class="yellow_color"</c:if>>
										<a name="${a.catCode}"> ${a.catCode}</a>&nbsp;${a.catNameCn}&nbsp;(${a.catNameEn})
										</span>
										<c:if test="${a.catType=='1'}">
											<a href="#${a.linkCatCode }"><FONT color="#CC0000">(
													链接目录:${a.linkCatCode})</FONT> </a>
										</c:if>
										<c:if test="${a.catStatus=='2'}">
											<a href="#${a.linkCatCode }"><FONT color="#CC0000">(
													目标目录:${a.aimCatCode})</FONT> </a>
										</c:if>
							<c:if test="${a.catLevel==1}"><%="</li>"%></c:if>
							<c:if test="${a.catLevel==2}"><%="</ul></li>"%></c:if>
							<c:if test="${a.catLevel==3}"><%="</ul></ul></li>"%></c:if>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</div>
	</body>
</html>