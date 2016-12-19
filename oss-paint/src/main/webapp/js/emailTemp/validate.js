/**
 * @author chenyunlong
 */
$(document).ready(function(){
var strBody = initTextEdit("editor");
	//文本编辑器-初始化
//	window.CKEDITOR.replace( 'editor' );
//	CKEDITOR.instances.editor.setData($("#content").val());
	var reg = /^([a-zA-Z0-9])+((-\w+)|(\.\w+)|(\_\w+))*@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/i;
	$("#tempTheme")
		.keyup(function(event) {
			valTempTheme();
		}).blur(function(){
			valTempTheme();
		});
	$("#mailTheme")
		.keyup(function(event) {
			valEmailTheme();
		}).blur(function(){
			valEmailTheme();
		});
	//接收人
	$("#mailReceiver")
		.keyup(function(event) {
			valEmailReceiver(reg);
		}).blur(function(){
			valEmailReceiver(reg);
		});
	//抄送人
	$("#mailCc")
		.keyup(function(event) {
			valEmailCc(reg);
		}).blur(function(){
			valEmailCc(reg);
		});
	//密件人
	$("#mailSc")
		.keyup(function(event) {
			valEmailSc(reg);
		}).blur(function(){
			valEmailSc(reg);
		});
	//恢复地址
	$("#replyAddress")
		.keyup(function(event) {
			valReplyAddress(reg);
		}).blur(function(){
			valReplyAddress(reg);
		});
	$(".submit").bind("click",function(){
		var flag=true;
		valTempTheme();
		valEmailTheme();
		//var val = CKEDITOR.instances.editor.getData();
		var strBody1 = CKEDITOR.instances["editor"].getData();
		var val = strBody1;
		//var val = strBody;
		while (val.indexOf("\"") >= 0) {
			val = val.replace("\"", "\'");
		}
		if($.trim(val).length===0 || $.trim(val)==="<br />" ||$.trim(val).length > 4000){
			$("#email~.errors").remove();
			$("#content\\.errors").remove();
			$("#email").closest("td").addClass("alertborder");
			$("#email").append("<span class='errors' id='content.errors'>请输入内容且不能超过4000个字符！</span>");
		}else{
			$("#email").closest("td").removeClass("alertborder");
			$("#email~.errors").remove();
			$("#content\\.errors").remove();
		}
		//将文本编辑域的内容赋值给隐藏域
		$("#content").val(val);
		if($(".errors").length > 0){
			flag=false;
		}
		return flag;
	})
})
//模板主题即时提醒
function valTempTheme(){
	if($.trim($("#tempTheme").val()).length===0){
		$("#tempTheme ~ .errors").remove();
		$("#tempTheme").closest("td").addClass("alertborder");
		$("#tempTheme").after("<span class='errors' id='tempTheme.errors'>请输入模板主题且不能超过200个字符！</span>");
	}else{
		$("#tempTheme").closest("td").removeClass("alertborder");
		$("#tempTheme ~ .errors").remove();
	}
}
//邮件主题即时提醒
function valEmailTheme(){
	if($.trim($("#mailTheme").val()).length===0){
		$("#mailTheme ~ .errors").remove();
		$("#mailTheme").closest("td").addClass("alertborder");
		$("#mailTheme").after("<span class='errors' id='mailTheme.errors'>请输入邮件主题且不能超过200个字符！</span>");
	}else{
		$("#mailTheme").closest("td").removeClass("alertborder");
		$("#mailTheme ~ .errors").remove();
	}
}

//邮件接收人即时提醒--暂定邮箱最多10个
function valEmailReceiver(reg){
	if($.trim($("#mailReceiver").val()).length>0){
		var receive =$.trim($("#mailReceiver").val()).split(";");
		if(receive.length<11){
			for(var i=0;i<receive.length;i++){
				if(receive[i].length===0|| receive[i].length>80 || !reg.test(receive[i])){
					$("#mailReceiver ~ .errors").remove();
					$("#mailReceiver").closest("td").addClass("alertborder");
					$("#mailReceiver").after("<span class='errors' id='mailReceiver.errors'>请输入正确的邮箱，邮箱之间用“;”分割，每个邮箱的长度小于80个字符，且数量最多10个！</span>");
				}else{
					$("#mailReceiver").closest("td").removeClass("alertborder");
					$("#mailReceiver ~ .errors").remove();
				}
			}
		}
		else{
			$("#mailReceiver ~ .errors").remove();
			$("#mailReceiver").closest("td").addClass("alertborder");
			$("#mailReceiver").after("<span class='errors' id='mailReceiver.errors'>你的输入的邮箱数量超过10个，请输入正确的邮箱，邮箱之间用“;”分割，每个邮箱的长度小于80个字符，且数量最多10个！</span>");
		}
	}
	else{
		$("#mailReceiver").closest("td").removeClass("alertborder");
		$("#mailReceiver ~ .errors").remove();
	}
}

//抄送人
function valEmailCc(reg){
	if($.trim($("#mailCc").val()).length>0){
		var receive =$.trim($("#mailCc").val()).split(";");
		if(receive.length<11){
			for(var i=0;i<receive.length;i++){
				if(receive[i].length===0|| receive[i].length>80 || !reg.test(receive[i])){
					$("#mailCc ~ .errors").remove();
					$("#mailCc").closest("td").addClass("alertborder");
					$("#mailCc").after("<span class='errors' id='mailCc.errors'>请输入正确的邮箱，邮箱之间用“;”分割，每个邮箱的长度小于80个字符，且数量最多10个！</span>");
				}else{
					$("#mailCc").closest("td").removeClass("alertborder");
					$("#mailCc ~ .errors").remove();
				}
			}
		}
		else{
			$("#mailCc ~ .errors").remove();
			$("#mailCc").closest("td").addClass("alertborder");
			$("#mailCc").after("<span class='errors' id='mailCc.errors'>你的输入的邮箱数量超过10个，请输入正确的邮箱，邮箱之间用“;”分割，每个邮箱的长度小于80个字符，且数量最多10个！</span>");
		}
	}
	else{
		$("#mailCc").closest("td").removeClass("alertborder");
		$("#mailCc ~ .errors").remove();
	}
}
//密件人
function valEmailSc(reg){
	if($.trim($("#mailSc").val()).length>0){
		var receive =$.trim($("#mailSc").val()).split(";");
		if(receive.length<11){
			for(var i=0;i<receive.length;i++){
				if(receive[i].length===0|| receive[i].length>80 || !reg.test(receive[i])){
					$("#mailSc ~ .errors").remove();
					$("#mailSc").closest("td").addClass("alertborder");
					$("#mailSc").after("<span class='errors' id='mailSc.errors'>请输入正确的邮箱，邮箱之间用“;”分割，每个邮箱的长度小于80个字符，且数量最多10个！</span>");
				}else{
					$("#mailSc").closest("td").removeClass("alertborder");
					$("#mailSc ~ .errors").remove();
				}
			}
		}
		else{
			$("#mailSc ~ .errors").remove();
			$("#mailSc").closest("td").addClass("alertborder");
			$("#mailSc").after("<span class='errors' id='mailSc.errors'>你的输入的邮箱数量超过10个，请输入正确的邮箱，邮箱之间用“;”分割，每个邮箱的长度小于80个字符，且数量最多10个！</span>");
		}
	}
	else{
		$("#mailSc").closest("td").removeClass("alertborder");
		$("#mailSc ~ .errors").remove();
	}
}
//回复地址
function valReplyAddress(reg){
	if($.trim($("#replyAddress").val()).length>0){
		var receive =$.trim($("#replyAddress").val()).split(";");
		if(receive.length<11){
			for(var i=0;i<receive.length;i++){
				if(receive[i].length===0|| receive[i].length>80 || !reg.test(receive[i])){
					$("#replyAddress ~ .errors").remove();
					$("#replyAddress").closest("td").addClass("alertborder");
					$("#replyAddress").after("<span class='errors' id='replyAddress.errors'>请输入正确的邮箱，邮箱之间用“;”分割，每个邮箱的长度小于80个字符，且数量最多10个！</span>");
				}else{
					$("#replyAddress").closest("td").removeClass("alertborder");
					$("#replyAddress ~ .errors").remove();
				}
			}
		}
		else{
			$("#replyAddress ~ .errors").remove();
			$("#replyAddress").closest("td").addClass("alertborder");
			$("#replyAddress").after("<span class='errors' id='replyAddress.errors'>你的输入的邮箱数量超过10个，请输入正确的邮箱，邮箱之间用“;”分割，每个邮箱的长度小于80个字符，且数量最多10个！</span>");
		}
	}
	else{
		$("#replyAddress").closest("td").removeClass("alertborder");
		$("#replyAddress ~ .errors").remove();
	}
}

/**
 * 获取文本编辑器的id
 */
function getEditorId(){
	return "editor";
}

function initTextEdit(contentId){
		CKEDITOR.replace(contentId, {
			toolbar : [
			    ['Maximize'],
				['Preview', 'Cut', 'Copy', 'PasteText'],
				['Undo','Redo'],['SelectAll','RemoveFormat'],
				['JustifyLeft','JustifyCenter','JustifyRight','JustifyFull'],
				['AddImage'],['SpecialChar'],['Table'],
				['Link', 'RemoveLink', 'Anchor'],['helloworld'],
				'/',
				['Format', 'Font', 'FontSize'],
				['TextColor', 'BGColor'],
				['Bold', 'Italic', 'Underline', 'Strike'],
				['Subscript', 'Superscript'],
				['NumberedList', 'BulletedList'],
				['Outdent', 'Indent']
	 		],
			height : 300,
			width : 800
		});
		//replace html content elem
		//get content of textEdit
		var oEditor = CKEDITOR.instances[contentId];
	    var strBody = oEditor.getData();
	    // add event
		CKEDITOR.instances[contentId].on("instanceReady", function () {
			//set keyup event
			this.document.on("keyup", function (){ckeditorKeyUp(contentId)});
			//and click event
			//this.document.on("click", AutoSave);
			//and select event
			//this.document.on("select", AutoSave);
		});
	    return encodeURIComponent(strBody);
	}
