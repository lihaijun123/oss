<html>
<head>
<title>用戶提醒通知</title>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<style type="text/css">
*{padding:0px;margin:0px}
body{
	font-size: 12px; color: #000000; font-family: "verdana",  "宋體";
	margin:0px;
	SCROLLBAR-FACE-COLOR: #CFE2FC;
	SCROLLBAR-HIGHLIGHT-COLOR: #fcfcfc;
	SCROLLBAR-3DLIGHT-COLOR: #7eabf7;
	SCROLLBAR-ARROW-COLOR: #2F5FAE;
	SCROLLBAR-TRACK-COLOR: #e3f0ff;
	SCROLLBAR-DARKSHADOW-COLOR: #CEE1FD;
}

table {
	background-color:#EDEDED;
	border:1px solid #EDEDED;
	border-collapse: collapse;
	border-spacing: 1px;
}
textarea {
	width:90%;
	height:80px;
}
table{width:100%;border-width:0px;}
th {
	background:#f2f8ff;
	border-bottom:1px solid #d4eef7;
	border-top:1px solid #ffffff;
	padding:3px 10px;
	border-right:1px solid #ffffff;
	vertical-align:top;
	font-weight:normal;
	text-align:left;
}

td {
	border-bottom:1px solid #e2e2e2;
	border-top:1px solid #ffffff;
	padding:3px 10px;
	border-right:1px solid #ffffff;
	vertical-align:top;
}

</style>
</head>
<body>
<p>
尊敬的${userName}︰以下是您今天是需要做的事情
</p>
<table>
	<tr><th width=30%>提醒標題<th width=30%>提醒時間<th width=40%>提醒內容
	<#list reminds as remind>
		<tr><td>${remind.remindTitle}<td>${remind.remindTime}<td>${remind.remindContent}
	</#list>
</table>
</BODY>
</HTML>