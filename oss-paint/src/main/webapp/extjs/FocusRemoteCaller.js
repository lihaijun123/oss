//
/**
 * @param params �ШD���Ѽ�
 * @param url �ШDurl
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
		ses.options.add(new Option("�п��", " "));
		//�M�ŤU�Ԯ�,�u�d"�п��"���ﶵ
		ses.length = 1;
		//���M�ū᪺�U�Ԯغc�y���e
		for (i = 0; i < values.length; i++) {
			ses.options.add(new Option(values[i], keys[i]));
		}
		//�]�m�ƾڦ^��ɪ��q�{��
		for (i = 0; i < ses.options.length; i++) {
			if (ses.options[i].value == defValue) {
				ses.options[i].selected = true;
			}
		}
		//�]�m�U�@�ӤU�Ԯ�
		if(ses.onchange)
			ses.onchange();
	};
	frc.remoteCall();
}
/*
����U�@�h�ƾ�
value: ��e�襤����,�Y�Τ_��ܤU�@�h�ƾڪ���
defValue:�ƾڦ^��ɥΤ_�q�{��ܪ���
type:�Τ_�ƾڮw��ܼƾڮɫ��w�Ѽ�����
nextName:�U�@�ӤU�Ԯت��W��(�YID)
*/
function getNext(value, defValue, type, nextName) {
	var url = "/area.do?method=next&value=" + value + "&type=" + type;
	doCall(nextName, defValue, url);
}
/*
��l�ƲĤ@�ӤU�Ԯ�
objName:�Ĥ@�ӤU�Ԯت�ID�W��
defValue:�ƾڦ^��ɥΤ_�q�{��ܪ���
*/
function initFirst(objName, defValue) {
	doCall(objName, defValue, "/area.do?method=init");
}
/*
�۸u������l�ƲĤ@�ӤU�Ԯ�
objName:�Ĥ@�ӤU�Ԯت�ID�W��
defValue:�ƾڦ^��ɥΤ_�q�{��ܪ���
�Ӫ�l�ƥu�Τ_�۸u�������a�Ϸj��
*/
function initJobFirst(objName, defValue, type) {
    doCall(objName, defValue, "/area.do?method=initJob&paramType="+type);
}


