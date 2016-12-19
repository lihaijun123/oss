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
<p>您好！</p>

<p>有產品已執行的合同中最新上傳了附件，詳細信息如下！</p>

<p>合同編號:${contractId}</p>
<p>合同甲方:${damander}</p>
<p>銷售人員:${sales}</p>
<p>附件名稱:${attachment}</p>
<p>附件大小:${size}</p>
<p>上傳者:${uploader}</p>
<p>上傳時間:${uploadTime}</p>


<p>謝謝！</p>
  <br>

</#escape>
</BODY>
</HTML>