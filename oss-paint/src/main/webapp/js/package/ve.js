//来自jQuery一款插件的封装
//add by lihaijun 2011-10-18
//message:显示的消息
//title：标题
//callback:回调函数
vConfirm = function(message, title, callback) {
	if(!title){
		title = "确定";
	}
	var isOkFlag = false;
	if(confirm(message)){
		isOkFlag = true;
	}
	callback(isOkFlag);
	//jConfirm(message, title, callback);
};

vPrompt = function(message, value, title, callback) {
	jPrompt(message, value, title, callback);
};
//错误消息
vAlertError = function(message, title, callback) {
	if(!title){
		title = "错误";
	}
	jAlert('error', message, title, callback);
};
//警告消息
vAlertWarning = function(message, title, callback) {
	if(!title){
		title = "警告";
	}
	//jAlert('warning', message, title, callback);
	alert(message);
};
//成功消息
vAlertSuccess = function(message, title, callback) {
	if(!title){
		title = "成功";
	}
	jAlert('success', message, title, callback);
};
//普通信息
vAlertInfo = function(message, title, callback) {
	if(!title){
		title = "提示";
	}
	jAlert('info', message, title, callback);
};

