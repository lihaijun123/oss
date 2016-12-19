<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>角色菜单资源授权</title>
		<!-- Ext Css -->
		<link rel="stylesheet" type="text/css"
			href="/resources/css/ext-all.css" />
		<!-- Ext Js Lib -->
		<script type="text/javascript" src="/extjs/ext-base.js"></script>
		<script type="text/javascript" src="/extjs/ext-base-main.js"></script>
		<script type="text/javascript" src="/extjs/ext-all-main.js"></script>
		<!-- User Defined Js -->
		<script type="text/javascript" src="/extjs/TreeCheckNodeUI.js"></script>
		<script src="extjs/style.js" type="text/javascript"></script>
		<script src="extjs/validate_form.js" type="text/javascript"></script>
		<script src="extjs/tree_checknode_ui.js " type="text/javascript"></script>
		<script src="extjs/select_tree.js " type="text/javascript"></script>
		<script type="text/javascript" src="/js/jquery.js"></script>
		<script>
		function check() {
			mml.getSelects();
			document.forms[0].submit();
		}
		var mml;
		$(function(){
			mml = new SelectTree(document.forms[0].resources, 'tree-div', '/showmenu2.do');
			Ext.onReady(
				 function() {
				 	mml.init();
				 }
			 );
			setTimeout(changeImg, 100);
		});
		function changeImg(){
			$("img").each(function(i){
				this.src = "/images/level-plus.png";
			});
		}
		</script>
	</head>
	<body>
		<form:form modelAttribute="ossAdminRole">
			<table width="100%">
				<caption class="x-panel-header">
					角色菜单资源授权
				</caption>
				<tr>
					<th class="required">
						角色名称︰

					</th>
					<td align="left">
						<form:input path="roleName" onblur="validateField(this)"
							readonly="true" />
						<form:errors path="roleName" cssClass="errors" />
					</td>
				</tr>

				<tr>
					<th>
						角色资源︰

					</th>
					<td align="left">
						<div id="tree-div"
							style="overflow: auto; height: 300px; width: 250px; border: 1px solid #c3daf9;"></div>
						<select style="display: none" id="resources" name="resources"
							multiple="multiple"></select>
						<script>
						<c:forEach var="ids" items="${resourceSelected}">
						selectChecks.push("${ids.resourceId}");
						</c:forEach>
						</script>
						<form:errors path="resources" cssClass="errors" />
						<!--

						 <input type="hidden" name="resourceSet"/>
						 -->
					</td>


				</tr>

				<tr>
					<th> 业务状态 </th>
					<td>
						<form:radiobuttons path="active"
							items="${crmBaseParameterActiveList}"
							itemLabel="parameterValue" itemValue="parameterKey"/>
						<form:errors path="active" cssClass="errors"/>
					</td>

				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<!--form內容結束-->
						<div class="buttons">
							<input type="button" value=" 确认 "  onclick="check()" />
							<input type="button" value=" 返 回 "
								onclick="window.location.href='/role.do?method=list'">
						</div>
					</td>
				</tr>
			</table>
		</form:form>
	</body>
</html>