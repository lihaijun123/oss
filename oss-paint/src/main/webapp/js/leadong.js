function doRadomNum()
{
	var varRadomChar = new Array("a","b","c","d","e","f","g","h","i","j"
	,"k","l","m","n","o","p","q","r","s","t"
	,"u","v","w","x","y","z");
	var varRadom;
	var varRadomMod="";
	for (var i = 0; i < 3;i++)
	{
		varRadom = Math.round(Math.random()*1000000)
		varRadomMod = varRadomMod.concat(varRadomChar[varRadom%25]);
	}
	document.forms[0].smartLogonPassword.value = (varRadom+"").substring(0,3)+varRadomMod;
}	
function doChange()
{
	var obj1 = document.all.trCustomerEmail;	
	for(var i=0;i<document.all.emailFlag.length;i++)
	{
		if(document.all.emailFlag[i].checked && i == 0)
		{
			obj1.style.display= '' ;
			break;
		}
		else
		{
			obj1.style.display= 'none' ;
		}
	}
}	

//為驗證框架傳值
function validateFields(obj) {
 	validateField(obj,obj,"emailFlag");
}


function domainCheck(arg) 
{

		var key;
		key=$("sld");
		if(key.value.length>20||!(/^[a-zA-Z0-9\-]+$/).test(key.value)){
			key.focus();
			alert("只能使用字母(A-Z,a-z)、數字(0-9)、連字號(-)，不能含空格或中文!");
			key.focus();
		}
		else
		{
			AjaxValidate('/smartDomain.do?method=addDomainCheck','&domain='+key.value,callBack);
		}
}
function callBack(result){
	document.getElementById('2ndValiResu').innerHTML=result[0].message;
}	

var http_request = false;
function send_request(url) 
{
	http_request = false;
	if(window.XMLHttpRequest) 
	{ 
		http_request = new XMLHttpRequest();
		if (http_request.overrideMimeType) 
		{
			http_request.overrideMimeType('text/xml');
		}
	}
	else if (window.ActiveXObject) 
	{
		try 
		{
			http_request = new ActiveXObject("Msxml2.XMLHTTP");
		} 
		catch (e) 
		{
			try 
			{
				http_request = new ActiveXObject("Microsoft.XMLHTTP");
			} 
			catch (e) 
			{}
		}
	}
	if (!http_request)
	{ 
		// 異常，創建對象實例失敗
		window.alert("不能創建XMLHttpRequest對象實例.");
		return false;
	}
	http_request.onreadystatechange = processRequest;
	// 確定發送請求的方式和URL以及是否同步執行下段代碼
	http_request.open("GET", url, true);
	http_request.send(null);
}
// 處理返回信息的函數
function processRequest() 
{
	if (http_request.readyState == 4)
	{ 
		if (http_request.status == 200) 
		{ 
			document.getElementById('2ndValiResu').innerHTML=http_request.responseText;
		} 
		else 
		{ 
			alert("您所請求的頁面有異常。");
		}
	}
}

function processConfirm(url) {
	if(confirm("你確定要對該域名做解析麼？")) {
		window.location.href = url;
	}
}
function deleteReletedConfirm(url) {
	if(confirm("你確定要刪除該續約麼？")) {
		window.location.href = url;
	}
}
//結束日期等于開始日期加3個月-1天
function addDateStartTimeAddThreeMonth(startTime, endTime) {
	if($(startTime).value=="")
	{
		alert("開始時間不能為空！");
		return false;
	}
	var d1 = document.getElementById(startTime).value;
	if (d1.length > 0) {
		//var date1 = new Date(d1.replace(/\-/g, '\/'));
		try {
			var date1 = toDate(d1);
			date1.setMonth(date1.getMonth() + 3);
			date1.setDate(date1.getDate() - 1);
			if(date1.toString() == 'NaN' || date1.toString() == 'Invalid Date') {
				alert("日期格式不正確。");
				document.getElementById(endTime).value="";
				return ;
			}
			document.getElementById(endTime).value = date1.format("yyyy-MM-dd")+" 23:59:59";
		} catch(e) {
			alert(e);
			document.getElementById(endTime).value="";
		}
	}
}

/**
 * 結束日期等于開始日期加n個月減1天，格式為yyyy-MM-dd hh:mi:ss.
 * 
 * @param {String} idStartTime
 * @param {String} idEndTime
 * @param {HTMLElement} oMonths
 */
function addMonthsToStartTime(idStartTime, idEndTime, oMonths) {
  	var startTime = document.getElementById(idStartTime).value;
  	if (startTime == null || startTime.length <= 0) {
		alert("開始時間不能為空！");
		oMonths.selectedIndex = 0;
		return;
	}
	
	var n = oMonths.value;
	if(Number(n) < 0){
	    document.getElementById(idEndTime).value = "";
	    return;
  	}

	try {
		var startDate = toDate(startTime);
		startDate.setMonth(startDate.getMonth() + Number(n));
		startDate.setDate(startDate.getDate() - 1);
		if(startDate.toString() == 'NaN' || startDate.toString() == 'Invalid Date') {
			alert("日期格式不正確。");
			document.getElementById(idEndTime).value = "";
			return;
		}
		document.getElementById(idEndTime).value = startDate.format("yyyy-MM-dd") + " 23:59:59";
	} catch(e) {
		alert(e);
		document.getElementById(idEndTime).value = "";
	}
}
