document.writeln('<script src="extjs/FocusRemoteCaller.js" type="text/javascript"></script>');

/*
 * Ext asyn validate Apply to Normal Page @version:1.0 @author:xdy
 * @date:2008.5.7
 *
 * IE FireFox ����ճq�L
 */

/**
 * asynValidate ����r�Ŵ���
 */

function validateField(target,showTarget,otherFields) {
	var url = target.form.action;
	showTarget = showTarget || target;
	url = url.substring(0, url.indexOf('?'));
	var frc = new FRC();
	frc.url = url+'?method=preValidate';
	frc.callBack = success;
	frc.target = target;
	frc.showTarget = showTarget;
	frc.params[target.name]= (target.value);
	//
	if(otherFields) {
		var tmp = otherFields.split(",");
		for(var i = 0 ; i < tmp.length;i++){
			frc.params[tmp[i]]=escape(getValue(tmp[i]));
			//frc.params[tmp[i]]=escape(getValue(tmp[i]));
		}
	}
	//
	frc.remoteCall();
}

function success(data,caller) {
	var target ={};
	target.parentNode= caller.showTarget.parentNode;

	var showName = caller.showTarget.getAttribute("showName");
	target.name = showName ||caller.showTarget.name;
	var fTarget = caller.target;
	var codes= data["codes"];
	var msg = "";
	for(var i = 0 ; i < data.length ;i++ ){
		if(data[i].field == fTarget.name) {
			msg += data[i].defaultMessage;
		}
	}
	var pNode = target.parentNode;
	if(pNode.tagName !='TD' && pNode.tagName !='td') {
		if(pNode.parentNode && (pNode.parentNode.tagName=='TD' || pNode.parentNode.tagName =='td')) {
			pNode = pNode.parentNode;
		}
	}
	if(msg !="") {
		pNode.className = pNode.className + " alertborder";
		if(document.getElementById(target.name + ".errors")) {
			document.getElementById(target.name + ".errors").innerHTML = msg;
		} else if(document.getElementById(target.name + ".errors.panel")) {
			document.getElementById(target.name + ".errors").innerHTML = msg;
		} else {
			var panel = document.createElement("span");
			panel.id = target.name + ".errors";
			panel.className = "errors";
			panel.innerHTML = msg;
			pNode.appendChild(panel);
		}
	} else {
		pNode.className = pNode.className.replace("alertborder","");
		if(document.getElementById(target.name + ".errors")) {
			document.getElementById(target.name + ".errors").innerHTML = "";
		} else if(document.getElementById(target.name + ".errors.panel")) {
			document.getElementById(target.name + ".errors").innerHTML = "";
		}
	}
}
function getValue(field) {
	var val = "";
	var obj = document.getElementsByName(field);
	var type = "";
	if(obj.length >=0 ) {
		type = obj[0].type;
	}
	if(type == 'select-one') {
		obj = obj[0];
		if(obj.selectedIndex >= 0) {
			val = obj.options[obj.selectedIndex].value;
		}
	} else if(type == 'radio') {
		for(var i = 0 ; i < obj.length;i++) {
			if(obj[i].checked) {
				val = obj[i].value;
				break;
			}
		}
	} else if(type == 'checkbox') {
		for(var i = 0 ; i < obj.length;i++) {
			if(obj[i].checked) {
				if(val != "") val += ",";
				val += obj[i].value;
			}
		}
	} else {
		val = obj[0].value;
	}
	return val ;
}


function jump() {
	if(parent && parent.closeDWindow) {
		parent.closeDWindow();
	}
}
// �R���e���T�{���ܮ�
function deleteConfirm(url) {
	if (confirm("确认要删除吗？")) {
		window.location.href = url;
}
}