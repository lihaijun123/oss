

function isRelease(url,status,is_used)
{
	if(status == "3")
	{
		alert("資源已釋放!");
	}
	else if(status == "0"||status == "4")
	{
		if(is_used == "y"){
			alert("正在使用中不可以釋放該資源!");
		}else{
			if (confirm("是否釋放當前信息?")) {
				window.location.href = url;
			}
		}
	}
	else
	{
		alert("非預訂或續訂資源資源不能釋放!");
	}
}
/*
function modify(url,status,is_used)
{
	if(status == "3")
	{
		alert("資源已釋放不可修改!");
	}else if(status == "2")
	{
		alert("正式合同中使用不可以修改!");
	}else if(status == "0" || status == "4" )
	{
		if(is_used == "是"){
			alert("正在使用中不能修改!");
		}else{
			window.location.href = url;
		}
	}
}

function loading() {
	alert("loading");
	var tabObj = document.getElementById("list00_table");
	alert(tabObj.tagName);
	var trs = tabObj.childNodes;
	alert(trs.length);
	alert(trs[0].class);
	for (var i = 1; i < trs.length; i++) {
		
	}
}
UITool.AddEventHandler(window, "load", loading);
*/
