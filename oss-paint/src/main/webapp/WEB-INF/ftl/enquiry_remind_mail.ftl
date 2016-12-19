<html>
<head>
<title>詢盤</title>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<STYLE TYPE="text/css">
<!--
A {COLOR: #000070; TEXT-DECORATION: underline}
A:hover {COLOR: #ef7500; TEXT-DECORATION: underline}
A:active {COLOR: #ef7500; TEXT-DECORATION: underline}
td,th{font-size:10pt;font-family:verdana,arial}
body {font-size:10pt;font-family:verdana,arial}
-->
</STYLE>
</head>
<body>
<#escape x as (x)!>
<p></p>
<p>您好！</p>
<p>你有一封詢盤要處理︰</p>
<br>
<table border="0" cellpadding="1" cellspacing="1" bgcolor="#999999">
<tr bgcolor="#cccccc">
<th>詢盤代碼</th><th>詢盤來源</th><th>發信人</th><th>發信時間</th><th>語言</th><th>公司ID</th>
<th>分配狀態</th><th>分配人</th><th>分配時間</th><th>關聯客戶</th>
</tr>
<tr bgcolor="#ffffff">
<td><a href="${SAL}enquiryAllot.do?method=showEnquiry&recId=${id}&origin=${origin}&flag=${flag}&lanCode=${lan_code}&come=1">${id}</a></td><td>${origin2}</td><td>${sender}</td><td>${send_time}</td><td>${lan_code2}</td><td>${com_id}</td>
<td>${allot_status}</td><td>${alloter}</td><td>${allot_time}</td><td>${account_id}</td>
</tr>
</table>
 <br>
</#escape>
</BODY>
</HTML>