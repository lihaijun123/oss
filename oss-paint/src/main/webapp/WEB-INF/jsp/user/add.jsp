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
		<title>添加用户</title>
		<!-- Ext Css -->
		<link rel="stylesheet" type="text/css" href="/css/oss.css" />
		<link rel="stylesheet" type="text/css" href="/js/combox_all.jscss" />
		<!-- Ext Js Lib -->
		<script type="text/javascript" src="/extjs/ext-base.js"></script>
		<script type="text/javascript" src="/extjs/ext-all.js"></script>
		<!-- User Defined Js -->
		<script src="/js/util.js" type="text/javascript"></script>
		<script src="extjs/validate_form.js" type="text/javascript"></script>
		<script type="text/javascript" src="/extjs/department_user.js"></script>
		<script type="text/javascript" src="/js/combox_all.jscss"></script>
	</head>
	<body>
		<form:form modelAttribute="ossAdminUser" id="form">
			<table width="100%">
				<caption class="x-panel-header">
					添加用户
				</caption>
				<tr align="left"  >
					<td colspan="2" style="background-color: #99CCCC">
						用户登录信息
					</td>
				</tr>
				<tr>
					<th class="required">
						<font color="red">*</font>用户登录名︰
					</th>
					<td>
						<form:input path="loginName" onblur="validateField(this)"
							size="40" />
						<form:errors path="loginName" cssClass="errors" />
					</td>
				</tr>
				<!--
				<tr>
					<th>
						是否使用LDAP登陸驗證
					</th>
					<td align="left">
						<input type="radio" value="0" name="userLdap" id="userLdap1" onclick="changeUseLdap('0')"/>
						否
						<input type="radio" value="1" name="userLdap" id="userLdap2" onclick="changeUseLdap('1')" checked/>
						是
					</td>
					<form:hidden path="ldapUserId" />
				</tr>
				-->
				<tr>
					<th class="required">
						<font color="red">*</font>用户登录密码︰
					</th>
					<td>
						<form:input path="password" size="40" maxlength="100"/>
						<form:errors path="password" cssClass="errors" />
					</td>
				</tr>
				<tr align="left">
					<td colspan="2" style="background-color: #99CCCC">
						用户基本信息
					</td>
				</tr>
				<tr>
					<th>
						工号︰

					</th>
					<td align="left">
						<form:input path="workerId" size="40" maxlength="10" />
					</td>
				</tr>
				<tr>
					<th class="required">
						<font color="red">*</font>姓名︰

					</th>
					<td>
						<form:input path="fullname" onblur="validateField(this)" size="40" />
						<form:errors path="fullname" cssClass="errors" />
					</td>
				</tr>

				<tr>
					<th>
						別名（英文名）︰

					</th>
					<td align="left">
						<form:input path="agname" size="40" />
					</td>


				</tr>
				<tr>
					<th class="required">
						部门名称︰
					</th>
					<td>
						<form:select path="ossAdminDepartment" items="${departmentList }" itemLabel="departmentName" itemValue="departmentId"/>
					</td>
				</tr>
				<tr>
					<th class="required">
						性別︰
					</th>
					<td>
						<f:complex name="gender" paramType="USER_SEX"
							siteType="1" type="radio" itemLabel="parameterValue"
							itemValue="parameterKey" defaultValue="${ossAdminUser.gender}" />
						<form:errors path="gender" cssClass="errors" />
					</td>
				</tr>
				<tr>
					<th>
						分机号︰

					</th>
					<td align="left">
						<form:input path="extension" size="40" maxlength="100" />
					</td>
				</tr>
				<tr>
					<th>
						手机号码︰

					</th>
					<td align="left">
						<form:input path="mobileTelephone" size="40" maxlength="100" />
					</td>
				</tr>

				<tr>
					<th>
						EMAIL︰
					</th>
					<td>
						<form:input path="email" onblur="validateField(this)" size="40" />
						<form:errors path="email" cssClass="errors" />
					</td>
				</tr>

				<tr>
					<th class="required">
						用户角色︰
					</th>
					<td>
						<form:select path="roles" multiple="false">
							<form:option label="请选择" value="" />
							<form:options items="${ossAdminRolesList}" itemLabel="roleName" itemValue="roleId" />
						</form:select>
					</td>
				</tr>

				<tr>
					<th class="required">
						业务状态︰
					</th>
					<td>
						<label><input type="radio" value="1" name="active" id="active_1" checked="checked"/>启用</label><label for="active_0"><input type="radio" value="0" name="active" id="active_0" />停用</label>
						<!--<f:complex name="active" paramType="HElP_INFO_STATUS"
							siteType="1" type="radio" itemLabel="parameterValue"
							itemValue="parameterKey" defaultValue="${ossAdminUser.active}"
							otherAttrs="disabled" />
						-->
					</td>
				</tr>

				<tr>
					<th>
						备注︰
					</th>
					<td>
						<form:textarea path="description" cols="40" rows="10" />
					</td>
				</tr>
			</table>

			<div align="center">
				<input type="button" value=" 添 加 " id="addSub" class="submitc" onclick="doSubmit()">
				<input type="button" value=" 返 回 "
					onclick="window.location='/user.do?method=list'"
					class="submitc">
			</div>
		</form:form>
	</body>
	<script type="text/javascript"><!--
		function doSubmit(){
			/*setBtn2Disabled('addSub');
			var useLdap;
			var useLdaps = document.getElementsByName("userLdap");
			for(var i = 0 ;i < useLdaps.length; i++){
				if (useLdaps[i].checked){
					useLdap = useLdaps[i].value;
					break;
				}
			}
			if (useLdap == "1")
				document.getElementById("ldapUserId").value = document.getElementById("loginName").value;
			else
				document.getElementById("ldapUserId").value = "";*/
			document.getElementById("form").submit();
		}
		function changeUseLdap(value){
			if ("1" == value)
				document.getElementById("password").readOnly = true;
			else
				document.getElementById("password").readOnly = false;
		}
	--></script>
</html>