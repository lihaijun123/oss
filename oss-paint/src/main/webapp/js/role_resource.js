window.scanTableUrlMail = function(){} ;
window.init = function(){};

//
var delIds = {};

function loading() {
	var selIds = document.getElementsByName("sel_id");
	var dataRanges = document.getElementsByName("dataScope");
	for(var i = 0 ; i < selIds.length ;i++) {
		if(dataRanges && dataRanges.length > i) {
			if(selIds[i].checked == false) {
				dataRanges[i].disabled = true;
			}
		}
		selIds[i].onclick = function(en) {
			en = en || window.event;
			var obj = en.srcElement || en.target;
			var index = obj.index;
			if(dataRanges && dataRanges.length > index) {
				dataRanges[index].disabled = !obj.checked;
			}
			if(obj.checked == false) {
				delIds[obj.value] = obj.value;
			}
		}
	}
	window.doNext = function() {
		try {
			document.getElementsByName("selbtn")[0].disabled=true;
		}catch(e){}
		var checked = false;
		var selIds = document.getElementsByName("sel_id");
		for(var i = 0 ; i < selIds.length ; i++) {
			if(selIds[i].checked) {
				checked = true;
				break;
			}
		}
		var tmp = "";
		for(var key in delIds) {
			if(tmp !="") {
				tmp += ",";
			}
			tmp += key;
		}
		if(checked == true || tmp !="") {
			var hidObj = document.createElement("input");
			hidObj.type = "hidden";
			hidObj.name = "del_id";
			hidObj.value = tmp;
			document.nextForm.appendChild(hidObj);
			document.nextForm.submit();
		}
	}
	window.doUnSelAll = function() {
		var items = document.getElementsByName("sel_id");
		for(var i = 0 ; i < items.length ;i++) {
			items[i].click();
		}
	}
	window.doSelAll = function() {
		var items = document.getElementsByName("sel_id");
		for(var i = 0 ; i < items.length ;i++) {
			items[i].click();
		}
	}
	document.nextForm.target="_blank";
}
UITool.AddEventHandler(window,"load",loading);