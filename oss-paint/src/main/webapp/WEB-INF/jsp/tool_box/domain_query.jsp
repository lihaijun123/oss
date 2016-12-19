<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<HEAD>
		<TITLE>域名查詢</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=UTF-8">
		<LINK href="images/style.css" type=text/css rel=stylesheet>
		<script type="text/javascript" src="/extjs/ext-base.js"></script>
		<script type="text/javascript" src="/extjs/ext-all.js"></script>
		<script type="text/javascript" src="/js/util.js"></script>
		<script src="extjs/validate_form.js" type="text/javascript"></script>
		<style>
.submitc,.submitc:focus {
	font-size: 12px;
	height: 21px;
	padding-top: 2px !important;
	padding-top: 3px;
	background: transparent url(/images/fade-butt1.png) repeat scroll 0% 0%;
	border-color: #AAC5E3 rgb(96, 146, 202) rgb(96, 146, 202)
		rgb(170, 197, 227);
	border-style: double;
	border-width: 1px;
}

body {
	font-size: 12px;
	color: #000000;
	font-family: "verdana", "宋體";
	margin: 0px;
	SCROLLBAR-FACE-COLOR: #CFE2FC;
	SCROLLBAR-HIGHLIGHT-COLOR: #fcfcfc;
	SCROLLBAR-3DLIGHT-COLOR: #7eabf7;
	SCROLLBAR-ARROW-COLOR: #2F5FAE;
	SCROLLBAR-TRACK-COLOR: #e3f0ff;
	SCROLLBAR-DARKSHADOW-COLOR: #CEE1FD;
	background: white;
}
</style>
		<SCRIPT language="javascript">
		
		<!--
		function domainCheck(arg) 
		{
			if(arg=="")
			{
				alert("二級域名不能為空！")
				var key=document.getElementById("LeadongDomain");
				return false;
			}
			else
			{
				var key;
				key=document.getElementById("LeadongDomain");
				if(key.value.length>20||!(/^[a-zA-Z0-9\-]+$/).test(key.value)){
					key.focus();
					alert("只能使用字母(A-Z,a-z)、數字(0-9)、連字號(-)，不能含空格或中文!");
					key.focus();
					return false;
				}
			}
		}
		-->
	</script>
	</HEAD>
	<BODY leftmargin=0 topmargin=2 marginwidth=0 marginheight=0 >


		<br>
		<br>
		<br>
		<table align="center" width=450 border="1" bordercolor="#CFE2FC">
			<tr>
				<td>
					<font size="3" style="font: bold">一級域名查詢:</font>
				</td>
			</tr>
			<tr>
				<td>
					<form method="POST"
						action="http://www.focuschina.com/whois/whois.php3"
						target="_blank">
						<table align=left border=0 cellpadding=2 cellspacing=1>

							<TR>
								<td>
									<TABLE align=center border=0 cellSpacing=0 width="100%"
										height=80>



										<TR>
											<TD align="center" valign="middle">
												<input type="text" name="domain" size="20">
												<select name="domain_append" class="font" size="1">
													<option value=".com" selected>
														.com
													</option>
													<option value=".net">
														.net
													</option>
													<option value=".org">
														.org
													</option>
													<option value=".com.cn">
														.com.cn
													</option>
													<option value=".org.cn">
														.org.cn
													</option>
													<option value=".net.cn">
														.net.cn
													</option>
													<option value=".gov.cn">
														.gov.cn
													</option>
													<option value=".cc">
														.cc
													</option>
												</select>
												<input type="submit" value="域名查詢" name="begin" class="submitc">
											</TD>
										</tr>
									</TABLE>
								</TD>
							</TR>
						</TABLE>
					</FORM>
				</td>
			</tr>
			<tr>
				<td>
					<font size="3" style="font: bold">領動二級域名查詢:</font>
				</td>
			</tr>
			<tr>
				<td>
					<form:form action="/smartDomain.do?method=check"
						onsubmit="return domainCheck(document.getElementById('LeadongDomain').value)">
						<table align=left border=0 cellpadding=2 cellspacing=1>
							<TR>
								<td>
									<TABLE align=center border=0 cellSpacing=0 width="100%"
										height=80>
										<TR>
											<TD align="center" valign="middle">
												<input type="text" name="domain" id="LeadongDomain"
													size="20" value="${domain}">

												.Leadong.com
											</TD>
											<TD align="center" valign="middle">
												<input type="submit" value="領動二級域名查詢" class="submitc"/>
											</TD>
											</TD>
										</tr>
										<TR>
											<td align="left">
												<font color="red">${message}</font>
											</td>
										</tr>
									</TABLE>
								</TD>
							</TR>
						</TABLE>
					</form:form>
				</td>
			</tr>
		</table>
	</BODY>
</HTML>
