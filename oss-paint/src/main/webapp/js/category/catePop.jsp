<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/css/uitool.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript">
window.onload = function(){
	$.get("/cate.do?level=1", ajaxCallBack);

}

function ajaxCallBack(responseText, textStatus, XMLHttpRequest){
	var rv = eval("(" + responseText + ")");
	var firstLevelAry = rv[0];
	var tb = "<table  width=\"400px\"><tr bgcolor='#FFFFFF'><td id='id_list_title'>序号</td><td id='id_list_title'>名称</td></tr>"

	for(var i = 0; i < firstLevelAry.length; i ++){
		var firstLevelJson = firstLevelAry[i];
		var text = firstLevelJson["text"];
		var value = firstLevelJson["value"];
		tb += "<tr onclick='returnVal(\"" + text + "\"," + value + ")'>";
		tb += "<td>" + (i + 1) + "</td><td>" + text + "</td>";
		tb += "</tr>";
	}
	tb += "</table>";
	document.getElementById("content").innerHTML = tb;
}

function returnVal(text, val){
	var json = {};
	json["text"] = text;
	json["value"] = val;
	window.returnValue = json;
	window.close();
}
</script>
</head>
<body>
	<div id="content" >

	</div>
</body>
</html>