<html>
<head>
<title>詢盤</title>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<STYLE TYPE="text/css">
<!--
A {COLOR: #000070; TEXT-DECORATION: underline}
A:hover {COLOR: #ef7500; TEXT-DECORATION: underline}
A:active {COLOR: #ef7500; TEXT-DECORATION: underline}
td{font-size:9pt;font-family:verdana,arial}
body {font-size:9pt;font-family:verdana,arial}
p{font-size:9pt;font-family:verdana,arial}
-->
</STYLE>
</head>
<body>
<#escape x as (x)!>
<p></p>
<p><b>詢盤時間︰</b>${time}</p>
<p><b>感興趣的服務:</b>${faver}</p>
<p><b>公司ID:</b>${comId}</p>

<p><b>詢盤內容:</b>${content}</p>
<p><b>客戶聯系信息</b></p>
<p>公司名稱:${comName}</p>
<p>發信人:${sender}</p>
<p>發信人mail:${mail}</p>
<p>電話:${tel}</p>
<p>傳真:${fax}</p>
<p>主頁:${homepage}</p>
<p>國家:${country}</p>
<p>省份/州:${province}</p>
  <br>

</#escape>
</BODY>
</HTML>