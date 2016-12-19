//
/**
 * @param params 請求的參數
 * @param url 請求url
 * @param method
 * @param callBack
 */
if (!window.FRC) {
	var FRC = function () {
		this.params = {};
		this.paramStr = "";
		this.url = undefined;
		this.method = "";
		this.callBack = function (data) {
		};
	};
	FRC.prototype.remoteCall = function () {
		var _this = this;
		//
		var tUrl = this.url;
		if (!tUrl) {
			tUrl = "";
			tUrl = window.location.href;
			if (tUrl.indexOf("?") > 0) {
				tUrl = tUrl.substring(0, tUrl.indexOf("?"));
			}
			tUrl = tUrl + "?method=" + this.method;
		}
		var call = new ajax();
		var tmp ="";
		for(var key in _this.params) {
			if(tmp !="") tmp += "&";
			tmp += key + "=" + _this.params[key];
		}
		if(tmp == "") {
			tmp = _this.paramStr;
		}
		//alert(tUrl);
		//alert(tmp);
		call.send("POST",tUrl,tmp,_this);
	};
}

function doCall(objName, defValue, url) {
	var frc = new FRC();
	frc.url = url;
	frc.callBack = function (data) {
		data = data[0];
		var values = data["value"];
		var keys = data["key"];
		var ses = document.getElementById(objName);
		ses.options.add(new Option("請選擇", " "));
		//清空下拉框,只留"請選擇"的選項
		ses.length = 1;
		//給清空後的下拉框構造內容
		for (i = 0; i < values.length; i++) {
			ses.options.add(new Option(values[i], keys[i]));
		}
		//設置數據回顯時的默認值
		for (i = 0; i < ses.options.length; i++) {
			if (ses.options[i].value == defValue) {
				ses.options[i].selected = true;
			}
		}
		//設置下一個下拉框
		if(ses.onchange)
			ses.onchange();
	};
	frc.remoteCall();
}
/*
獲取下一層數據
value: 當前選中的值,即用于選擇下一層數據的值
defValue:數據回顯時用于默認顯示的值
type:用于數據庫選擇數據時指定參數類型
nextName:下一個下拉框的名稱(即ID)
*/
function getNext(value, defValue, type, nextName) {
	var url = "/area.do?method=next&value=" + value + "&type=" + type;
	doCall(nextName, defValue, url);
}
/*
初始化第一個下拉框
objName:第一個下拉框的ID名稱
defValue:數據回顯時用于默認顯示的值
*/
function initFirst(objName, defValue) {
	doCall(objName, defValue, "/area.do?method=init");
}
/*
招聘網站初始化第一個下拉框
objName:第一個下拉框的ID名稱
defValue:數據回顯時用于默認顯示的值
該初始化只用于招聘網站中地區搜索
*/
function initJobFirst(objName, defValue, type) {
    doCall(objName, defValue, "/area.do?method=initJob&paramType="+type);
}


