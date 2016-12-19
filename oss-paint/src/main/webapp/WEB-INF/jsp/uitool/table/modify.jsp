<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="f" uri="/WEB-INF/tags/focus.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>表格配置</title>
		<link rel="stylesheet" type="text/css" href="/css/oss.css" />
		<!-- Ext Js Lib -->
		<script type="text/javascript" src="/extjs/ext-base.js"></script>
		<script type="text/javascript" src="/extjs/ext-all.js"></script>
		<script src="extjs/validate_form.js" type="text/javascript"></script>
		<style type="text/css">
			textarea { width: 90%; height: 90% !important;}
		</style>
	</head>
	<body>
		<br>
		<form action="/uitool.do">
			&nbsp;
			<input type="hidden" name="method" value="table"/>
			<input type="hidden" name="funcId" value="${funcId }"/>
			表名称：
			<select name="tableName">
				<c:forEach var="tn" items="${tableNameList }">
					<option value="${tn }" ${tableName eq tn ? "selected='selected'" : ""}>${tn }</option>
				</c:forEach>
			</select>
			<input type="submit" value="查询"/>
		</form>
		<br>
		<table>
			<tr>
				<td><b>列名称</b></td>
				<td><b>显示名称</b></td>
				<td><b>链接</b></td>
				<td><b>顺序</b></td>
				<td><b>是否显示</b></td>
				<td><b>是否排序</b></td>
				<td><b>是否查询</b></td>
				<td><b>查询sql</b></td>
				<td><b>标题url</b></td>
				<td><b>查询类型</b></td>
			</tr>
			<form:form action="/uitool.do" method="post" modelAttribute="funcDisplayInfo">
				<input type="hidden" name="method" value="table"/>
				<input type="hidden" name="funcId" value="${funcId }"/>
				<input type="hidden" name="tableName" value="${tableName }"/>
				<c:forEach var="line" items="${lines }" varStatus="status">
					<tr>
						<td title="${line.remarks }">
							<input type="hidden" name="displayFields[${status.index }].funcId" value="${line.funcId }"/>
							<input type="hidden" name="displayFields[${status.index }].fieldName" value="${line.fieldName }"/>
							${line.fieldName }
						</td>
						<td>
							<input type="text" name="displayFields[${status.index }].displayText" value="${line.displayText }"/>
						</td>
						<td title="${line.url }">
							<input type="text" name="displayFields[${status.index }].url" value="${line.url }"/>
						</td>
						<td>
							<input type="text" name="displayFields[${status.index }].displayIndex" value="${line.displayIndex }"/>
						</td>
						<td>
							<input type="radio" value="1" name="displayFields[${status.index }].isDisplay" ${line.isDisplay eq "1" ? "checked='checked'" : "" } checked="checked"/>是<input type="radio" value="0" name="displayFields[${status.index }].isDisplay" ${line.isDisplay eq "0" ? "checked='checked'" : "" }/>否
						</td>
						<td>
							<input type="radio" value="1" name="displayFields[${status.index }].isOrder" ${line.isOrder eq "1" ? "checked='checked'" : "" }/>是<input type="radio" value="0" name="displayFields[${status.index }].isOrder" ${line.isOrder eq "0" ? "checked='checked'" : "" }/>否
						</td>
						<td>
							<input type="radio" value="1" name="displayFields[${status.index }].isFind" ${line.isFind eq "1" ? "checked='checked'" : "" }/>是<input type="radio" value="0" name="displayFields[${status.index }].isFind" ${line.isFind eq "0" ? "checked='checked'" : "" }/>否
						</td>
						<td title="${line.fetchValueType }">
							<input type="text" name="displayFields[${status.index }].fetchValueType" value="${line.fetchValueType }"/>
						</td>
						<td title="${line.titleUrl }">
							<input type="text" name="displayFields[${status.index }].titleUrl" value="${line.titleUrl }"/>
						</td>
						<td>
							<select name="displayFields[${status.index }].findType">
								<option value="0" ${line.findType eq "0" ? "selected='selected'" : "" }>模糊</option>
								<option value="1" ${line.findType eq "1" ? "selected='selected'" : "" }>精确</option>
								<option value="3" ${line.findType eq "3" ? "selected='selected'" : "" }>下拉框</option>
								<option value="4" ${line.findType eq "4" ? "selected='selected'" : "" }>日期</option>
							</select>
						</td>
					</tr>
				</c:forEach>
				<tr align="center">
					<td><b>生成列表SQL配置文件</b></td>
					<td colspan="9" align="left">
						<input type="radio" value="1" name="isCreateConfigXml" />是<input type="radio" value="0" name="isCreateConfigXml" checked="checked"/>否
					</td>
				</tr>
				<tr align="center">
					<td colspan="10">
						<input type="submit" class="submitc" value="保存" />
						<input type="button" value=" 返 回 "
					onclick="window.location.href='uitool.do?method=list'"
					class="submitc">
					</td>
				</tr>
			</form>
		</table>
		<form:form action="/uitool.do" method="post">
			<input type="hidden" name="method" value="updateSql"/>
			<input type="hidden" name="funcId" value="${funcId }"/>
			<input type="hidden" name="tableName" value="${tableName }"/>
			<input type="hidden" name="sqlFileName" value="${sqlFileName }"/>
			<table>
				<tr>
					<td>SQL：</td>
					<td>
						<textarea id="sql" name="sql" rows="20" cols="100">${sql }</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" class="submitc" value="保存" /></td>
				</tr>
			</table>

		</form:form>
		<form:form action="/uitool.do" method="post">
			<input type="hidden" name="method" value="addLine"/>
			<input type="hidden" name="tableName" value="${tableName }"/>
			<table>
				<tr>
					<td><b>FuncId</b></td>
					<td><b>列名称</b></td>
					<td><b>显示名称</b></td>
					<td><b>链接</b></td>
					<td><b>顺序</b></td>
					<td><b>是否显示</b></td>
					<td><b>是否排序</b></td>
					<td><b>是否查询</b></td>
					<td><b>查询sql</b></td>
					<td><b>标题url</b></td>
					<td><b>查询类型</b></td>
				</tr>
				<tr>
						<td>
							<input type="hidden" name="funcId" value="${funcId }"/>
							${funcId }
						</td>
						<td>
							<input type="text" name="fieldName"/>
						</td>
						<td>
							<input type="text" name="displayText" />
						</td>
						<td>
							<input type="text" name="url" />
						</td>
						<td>
							<select name="displayIndex">
								<option value="0">0</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
								<option value="13">13</option>
								<option value="14">14</option>
								<option value="15">15</option>
								<option value="16">16</option>
								<option value="17">17</option>
								<option value="18">18</option>
								<option value="19">19</option>
								<option value="20">20</option>
							</select>
						</td>
						<td>
							<input type="radio" value="1" name="isDisplay" ${line.isDisplay eq "1" ? "checked='checked'" : "" } checked="checked"/>是<input type="radio" value="0" name="isDisplay" ${line.isDisplay eq "0" ? "checked='checked'" : "" }/>否
						</td>
						<td>
							<input type="radio" value="1" name="isOrder" ${line.isOrder eq "1" ? "checked='checked'" : "" }/>是<input type="radio" value="0" name="isOrder" ${line.isOrder eq "0" ? "checked='checked'" : "" }/>否
						</td>
						<td>
							<input type="radio" value="1" name="isFind" ${line.isFind eq "1" ? "checked='checked'" : "" }/>是<input type="radio" value="0" name="isFind" ${line.isFind eq "0" ? "checked='checked'" : "" }/>否
						</td>
						<td >
							<input type="text" name="fetchValueType" />
						</td>
						<td >
							<input type="text" name="titleUrl" />
						</td>
						<td>
							<select name="findType">
								<option value="0" ${line.findType eq "0" ? "selected='selected'" : "" }>模糊</option>
								<option value="1" ${line.findType eq "1" ? "selected='selected'" : "" }>精确</option>
								<option value="3" ${line.findType eq "3" ? "selected='selected'" : "" }>下拉框</option>
								<option value="4" ${line.findType eq "4" ? "selected='selected'" : "" }>日期</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="11" align="center"><input type="submit" class="submitc" value="保存" /></td>
					</tr>
			</table>

		</form:form>
	</body>
</html>