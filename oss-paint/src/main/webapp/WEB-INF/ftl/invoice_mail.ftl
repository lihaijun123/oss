<html>
<head>
<title>發票郵寄通知</title>
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
To︰${userName}<br>

<p></p>
<p>你客戶的發票已寄出，詳細信息如下，請及時與客戶聯系確認。若兩周後客戶仍未收到發票，請回復此郵件以便協助查找。</p>
<p>合同號:${contractId?default()}</p>
<p>付款名稱:${paymentName}</p>
<p>開票名稱:${invoiceTitle}</p>
<p>合同總價:${contractMoney}${moneyUnit}</p>
<p>收票單位(人):${invoiceCompany}</p>
<p>收票地址(郵編):${invoiceAddress}</p>
<p>發票郵寄方式:${invoiceMode}</p>
<p><pre>${others}</pre></p>
  <br>
財務部<br>
${sendMan}<br>
</#escape>
</BODY>
</HTML>