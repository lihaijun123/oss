

function isRelease(url,status,is_used)
{
	if(status == "3")
	{
		alert("�귽�w����!");
	}
	else if(status == "0"||status == "4")
	{
		if(is_used == "y"){
			alert("���b�ϥΤ����i�H����Ӹ귽!");
		}else{
			if (confirm("�O�_�����e�H��?")) {
				window.location.href = url;
			}
		}
	}
	else
	{
		alert("�D�w�q����q�귽�귽��������!");
	}
}
/*
function modify(url,status,is_used)
{
	if(status == "3")
	{
		alert("�귽�w���񤣥i�ק�!");
	}else if(status == "2")
	{
		alert("�����X�P���ϥΤ��i�H�ק�!");
	}else if(status == "0" || status == "4" )
	{
		if(is_used == "�O"){
			alert("���b�ϥΤ�����ק�!");
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
