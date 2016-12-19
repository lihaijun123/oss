document.writeln('<script src="extjs/FocusRemoteCaller.js" type="text/javascript"></script>');

function AjaxValidate(url, parameters, action) {
	var _this = this;
	var turl = url;
	var param = "";
	if (url.indexOf("?") != -1) {
		turl = url.substring(0, url.indexOf("?"));
		param = url.substring(url.indexOf("?") + 1, url.length);
	} else {
		turl = url;
	}
	if (param != "") {
		param = "" + param;
	} else {
		param = "";
	}
	var frc = new FRC();
	frc.url = url;
	frc.paramStr= param + "&" + parameters;
	frc.callBack = action;
	frc.remoteCall();
}
AjaxValidate.prototype.reportSuccess = function (response) {
	this.action(response.responseText);
};
function ajaxRequest(url, parameters, action) {
	new AjaxValidate(url, parameters, action);
}

