<%@page import="org.acegisecurity.context.SecurityContextHolder"%>
<%@page import="org.acegisecurity.Authentication"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="com.focustech.oss2008.model.OssAdminUser"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tags/focus.tld" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>修改用戶信息</title>
		<link rel="stylesheet" type="text/css" href="/css/oss.css" />
		<script type="text/javascript" src="/extjs/ext-base.js"></script>
		<script type="text/javascript" src="/extjs/ext-all.js"></script>
		<script src="/extjs/validate_form.js" type="text/javascript"></script>
		<script type="text/javascript" src="/js/util.js"></script>
	</head>
	<body onload="PwdTableManage()">
		<script type="text/javascript">
	function jump() {
		if(parent && parent.closeDWindow) {
			parent.closeDWindow();
		}
	}
	function PwdTableManage()
	{
		if(document.getElementById("pwdDisplayCtrl").checked==true)
		{
			document.getElementById("pwdTable").style.display="block";
			document.getElementById("password").value="";
		}
		else
		{
			document.getElementById("pwdTable").style.display="none";
			document.getElementById("password").value="${ossAdminUser.password}";
		}
	}
	function checkForm()
	{
		/*if(trim(document.getElementById("fullname").value)=="")
		{
			alert("姓名必須填寫！");
			document.getElementById("fullname").focus();
			return false;
		}
		else*/
		if(trim(document.getElementById("agname").value)=="")
		{
			alert("別名（英文名）必须填写！");
			document.getElementById("agname").focus();
			return false;
		}
		else if(trim(document.getElementById("extension").value)=="")
		{
			alert("分机号必须填写！");
			document.getElementById("extension").focus();
			return false;
		}
		else if(trim(document.getElementById("email").value)=="")
		{
			alert("Email必须填写！");
			document.getElementById("email").focus();
			return false;
		}

		else if(document.getElementById("pwdDisplayCtrl").checked==true)
		{
			var pass=document.getElementById("password").value;
			if(document.getElementById("oldPwd").value=="")
			{
				alert("请填写原密码！");
				document.getElementById("oldPwd").focus();
				return false;
			}
			else if(pass==""&&document.getElementById("conformPwd").value=="")
			{
				alert("新密码与确认密码不能为空！");
				document.getElementById("password").focus();
				return false;
			}
			else if(pass.length<6)
			{
				alert("密码长度不能小于6位！");
				document.getElementById("password").focus();
				return false;
			}
			else if(pass.length>20)
			{
				alert("密码长度不能大于20位！");
				document.getElementById("password").focus();
				return false;
			}
			else if(pass.length>=6 && pass.length<=20)
			{
				var containChar=/^(([0-9]+[a-zA-Z]+)|([a-zA-Z]+[0-9]+))+/ig;
				if(!pass.match(containChar)){
					alert("密码必须是数字和字母组合！");
					document.getElementById("password").focus();
					return false;
				}
				else if(pass!=document.getElementById("conformPwd").value)
				{
					alert("新密码和确认密码不一致！");
					return false;
				}
			}

		}
		else if(trim(document.getElementById("email").value)!="")
		{
			var reEmail = /^.*@(-?_?\w+)+(\.\w+)+/ig;
	        if (!trim(document.getElementById("email").value).match(reEmail))
	        {
				alert("Email格式不正确！");
				document.getElementById("email").focus();
				return false;
	        }
		}
		document.getElementById("ossAdminUser").submit();
	}

	</script>

		<form:form modelAttribute="ossAdminUser"
			onsubmit="setBtn2Disabled('modifySub')">
			<table width="100%">

				<tr>
					<td colspan="2" align="center">
						<font color="red">${message}</font>
					</td>
				</tr>
				<tr>
					<th>
						所属部门名称︰
					</th>
					<td>
						<input type="hidden"
							value="${ossAdminUser.ossAdminDepartment.departmentId}"
							name="ossAdminDepartment">
						<b>${ossAdminUser.ossAdminDepartment.departmentName}</b>
					</td>
				</tr>
				<tr>
					<th>
						用户登录名︰
					</th>
					<td>
						<form:hidden path="loginName" />
						<b>${ossAdminUser.loginName }</b>
						<!--<form:input path="loginName" size="40" readonly="true" />-->
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<hr>
					</td>
				</tr>
				<tr>
					<th class="required">
						姓名︰
					</th>
					<td>
						<form:input path="fullname" size="40" readonly="true" />
						<font color="red">为了记录查看方便，禁止修改姓名</font>
					</td>
				</tr>
				<tr>
					<th class="required">
						別名（英文名）︰
					</th>
					<td align="left">
						<form:input path="agname" size="40" />
					</td>
				</tr>
				<tr>
					<th class="required">
						性別︰
					</th>
					<td>
						<select name="gender">
							<option value="0" ${ossAdminUser.gender eq 0 ? "selected='selected'" : ""}>女士</option>
							<option value="1" ${ossAdminUser.gender eq 1 ? "selected='selected'" : ""}>先生</option>
						</select>
						<form:errors path="gender" cssClass="errors" />
					</td>
				</tr>
				<tr>
					<th>
						手机号︰
					</th>
					<td align="left">
						<form:input path="mobileTelephone" size="40" />
						<br />
						<font color="red">请再次确认以上手机号填写正确，否则将影响您和客户的联系! </font>
					</td>
				</tr>
				<tr>
					<th class="required">
						分机号︰
					</th>
					<td align="left">
						<form:input path="extension" size="40" />
						<br />
						<font color="red">请再次确认以上分机号填写正确，否则将影响您和客户的联系! </font>
					</td>
				</tr>
				<tr>
					<th class="required">
						EMAIL︰
					</th>
					<td>
						<form:input path="email" size="40" />
						<br />
						<font color="red">请再次确认以上EMAIL拼写正确，否则将影响您和客户的联系! </font>
					</td>
				</tr>
					<tr>
						<td colspan="2" align="left">
							<input type="checkbox" id="pwdDisplayCtrl" name="pwdDisplayCtrl"
								value="1" onclick="PwdTableManage();">
							修改密码
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<table width="100%" id="pwdTable" style="display: none">
								<tr>
									<th class="required">
										用户原密码︰
									</th>
									<td>
										<input type="password" name="oldPwd" id="oldPwd" size="30">
									</td>
								</tr>
								<tr>
									<th class="required">
										用户新密码︰
									</th>
									<td>
										<input type="password" name="password" id="password" size="30">
									</td>
								</tr>
								<tr>
									<th class="required">
										确认新密码︰
									</th>
									<td>
										<input type="password" name="conformPwd" id="conformPwd"
											size="30">
									</td>
								</tr>
							</table>
						</td>
					</tr>

			</table>
			<div class="buttons">
				<input type="button" value=" 修 改 " onclick="checkForm()"
					id="modifySub" class="submitc">
				<input type="button" value=" 关闭 " onclick="jump()" class="submitc">
			</div>
		</form:form>
	</body>
</html>