<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>后台管理系统--登录</title>
<style type="text/css">
html { height: 100% }
body { height: 100%; background-image: url(images/bg.gif); background-position: left -1200px; font-size: 14px; margin: 0px; padding: 0px; background-repeat: repeat-x; }
form { width: 550px; height: 340px; background: url(images/login.png) no-repeat; padding-top: 10px; margin-right: auto; margin-left: auto; margin-top: 100px; }
#inputs { padding-top: 70px; padding-right: 20px; padding-bottom: 0; padding-left: 100px; }
#inputs div { padding: 7px }
#inputs span { float: left; width: 105px; text-align: right; margin-top: 5px; margin-right: 10px; }
#inputs input { border: 1px solid #999999; background-color: #FFFFFF; background-image: url(images/input.gif); background-repeat: repeat-x; background-position: left top; height: 18px; font-family: Arial, Helvetica, sans-serif; }
#inputs img { margin-left: 6px; height: 40px; width: 95px; margin-bottom: -15px; }
#inputs #system { margin-bottom:15px }
#inputs #system select { margin-top:3px; > margin:0;
width:150px }
button { border: none; margin-top: 30px; margin-left: 160px;background-image: none; background-color: transparent; height: 36px; width: 119px; padding: 0px; }
img { vertical-align:bottom }
strong { display: block; line-height: 22px; background-image: url(images/echo.gif); width: 290px; color: #FFFFFF; font-size: 12px; margin-right: auto; margin-left: auto; text-align: center; margin-top: 30px; }
.hid { display: none }
#c { position:fixed; >position: absolute;
bottom: 0; background: #505050; border-top: 1px solid #fff; color: #FFFFFF; width: 100%; text-align: center; padding-top: 10px; padding-bottom: 10px; margin: 0px; padding-right: 0px; padding-left: 0px; font-family: Arial, Helvetica, sans-serif; font-size: 12px; }
.err { background-color: red; }
</style>
<script type="text/javascript">
if(window.top != window) {
	window.top.location = "/";
}
function initPage() {
	<c:if test="${!(isAgt || isSgs || aut_security_source==\"SGS\" || aut_security_source==\"AGT\")}">
	var secSource = document.getElementById("aut_security_source");
	for (i =0; i < secSource.options.length;i++){
		if (secSource.options[i].value == '${aut_security_source}'){
			secSource.options[i].selected = true;
			break;
		}
	}
	</c:if>
	<c:if test="${isLdapUser}">
		document.getElementById("ldapEcho").className ="exp";
	</c:if>
	document.getElementById("user").focus();
}

function doSubmit(show) {
	if(document.getElementById("user").value == "") {
		document.getElementById("user").focus();
		showHidErrMsg("user","请输入正确的用户名。",true);
		return ;
	}
	showHidErrMsg("user","",false);
	if(document.getElementById("pass").value == "") {
		document.getElementById("pass").focus();
		showHidErrMsg("pass","请输入正确的密码。",true);
		return ;
	}
	showHidErrMsg("pass","",false);
	if(document.getElementById("vNum").value == "") {
		document.getElementById("vNum").focus();
		showHidErrMsg("vNum","请输入正确的验证码。",true);
		return ;
	}
	showHidErrMsg("vNum","",false);
	if(document.getElementById("aut_security_source").value == "") {
		document.getElementById("aut_security_source").focus();
		showHidErrMsg("aut_security_source","请选择要登录的系统。",true);
		return ;
	}
	if(document.getElementById("aut_security_source").value == "AGT") {
		document.getElementById("aut_security_source").value = "SAL";
	}
	showHidErrMsg("aut_security_source","",false);
	document.getElementById("form").submit();
}
function doKeyPress(e) {
	e = e? e : window.event;
	var code = e.keyCode;
	if(code >=0 && code !=13) {
		return ;
	}
	doSubmit(false);
}
function showHidErrMsg(obj,msg,err) {
	var divObj = document.getElementById(obj).parentNode;
	obj = document.getElementById(obj);
	if(err) {
		divObj.className = "err";
		showHide("echo",msg);
	} else {
		divObj.className = "";
		showHide("echo","");
	}
}
function refreshValiCode(o) {
	var date=(new Date()).getTime();
	o.src="/validateimage/"+date+".gif";
	showHide('echo','${string}');
}
function showHide(obj,msg) {
	if(msg =="") return ;
	if (msg == "") {
		document.getElementById(obj).className =document.getElementById(obj).className.replace("exp","hid");
	} else {
		document.getElementById(obj).className =document.getElementById(obj).className.replace("hid","exp");
		document.getElementById(obj).innerHTML = msg;
	}
}
</script>
</head>
<body onload="initPage('form');refreshValiCode(document.forms[0].validateImage)">
<form id="form" name="f" action="login.do" method="post" onkeypress="return doKeyPress(event)" onsubmit="return false;">
	<div id="inputs">
		<div id="system">
			<c:choose>
				<c:when test="${isAgt || isSgs || aut_security_source==\"SGS\" || aut_security_source==\"AGT\"}">
					<strong>您即将登录的是${aut_security_name}</strong>
					<input type="hidden" name="aut_security_source"
						id="aut_security_source" value="${aut_security_source}" />
				</c:when>
				<c:otherwise>
					<div style="display: none;">
					<span>登录系统</span>
					<select name="aut_security_source" id="aut_security_source">
						<!-- <option value="">请选择</option> -->
						<option value="SAL">运营系统</option>
					</select>
					</div>
				</c:otherwise>
			</c:choose>
		</div>

	<div>
		<span>用户名</span>
		<input type="text" id="user" tabindex="0" name="j_username" value="${ACEGI_SECURITY_LAST_USERNAME}" />
	</div>
	<div>
		<span>密码</span>
		<input type="password" id="pass" name="j_password" />&nbsp&nbsp&nbsp<font color="red" id="ldapEcho" class="hid"><a href="https://password.focuschina.com/" target="_blank">修改域帐户密码</a></font>
	</div>
	<div>
		<span>验证码</span>
		<input type="text" size="5" id="vNum" name="validateNumber" autocomplete="off" />
		<img id="validateImage" style="cursor: pointer;" src="about:blank" alt="点击刷新验证码" width="105" height="55" onclick="refreshValiCode(this)" />
	</div>
	<div style="display: none">
		<span>动态密码</span>
		<input type="text" size="8" id="dynNum" name="dynPassword" />
	</div>
	</div>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button onclick="doSubmit(true)"><img src="images/loginbutton.gif" alt="登录" /></button>
	<strong id="echo" class="hid"></strong>
</form>
<div id="c">Copyright &copy; 2008 焦点科技. 版权所有</div>
</body>
</html>
