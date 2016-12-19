/**
 * @author chenyunlong
 */
$(document).ready(function() {
	var strBody = initTextEdit("editor");
	var reg=/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/;

	var RECIVER = $("#mailReceiver").val();
    $("#emailTempSn").bind("change", function() {
		removeWarming();
		if($("#emailTempSn option:selected").val() !==""){
			var dataStr = "method=getEmailTempBySn&emailTempSn=" + $("#emailTempSn option:selected").val()+"&memberSn="+$("#memberSn").val();
			if($("#orderSn").val()){
				dataStr = dataStr + "&orderSn="+$("#orderSn").val();
			}
			$.ajax({
	            type: "GET",
	            url: "/email-temp.do",
	            data: dataStr,
	            success: function(result) {
	                var data = (new Function("return " + result))();
	                $("#mailTheme").val(data.mailTheme);
					if(data.mailReceiver && data.mailReceiver !== ""){
		                $("#mailReceiver").val(data.mailReceiver);
					} else {
						$("#mailReceiver").val(RECIVER);
					}
	                $("#mailCc").val(data.mailCc);
	                $("#mailSc").val(data.mailSc);
	                $("#replyAddress").val(data.replyAddress);
	                $("#keyword").val(data.keyword);
					//恢复会从换行符
	                var content = data.content.replace(/@r@n@t/g, "\r\n\t");
	                content = content.replace(/@r@n/g, "\r\n");
					content = replaceTempContent(content,data.memberInfo);
	                CKEDITOR.instances.editor.setData(content);
					if(data.mailSender !==""){
						$.ajax({
				            type: "GET",
				            url: "/email-temp.do",
				            data: "method=getEmailKeyByEmail&email=" + data.mailSender,
				            success: function(result) {
				                $("#mailSender").val(result);
				            }
				        });
					}
	            }
	        });
		}
		else{
			initializationData();
		}
    })
	//邮件主题
	$("#mailTheme")
		.keyup(function(event) {
			valEmailTheme();
		}).blur(function(){
			valEmailTheme();
		});
	//发送人
	$("#mailSender").click(function() {
			valEmailSender();
		})
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
	//提交页面
	$(".submit").bind("click",function(){
		var flag=true;
		valEmailTheme();
		valEmailSender();
		valEmailReceiver(reg);
		valEmailCc(reg);
		valEmailSc(reg);
		valReplyAddress(reg);
		var strBody1 = CKEDITOR.instances["editor"].getData();
		var val = strBody1;
		while (val.indexOf("\"") >= 0) {
			val = val.replace("\"", "\'");
		}
		if(val.length===0 || val==="<br />" ||val.length > 4000){
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
		if(flag){
			//ajax处理提交
			var dataStr ="mailTheme="+$("#mailTheme").val()+"&mailSender="+$("#mailSender").val()+"&mailReceiver="+$("#mailReceiver").val()
						+"&mailCc="+$("#mailCc").val()+"&mailSc="+$("#mailSc").val()+"&replyAddress="+$("#replyAddress").val()
						+"&keyword="+$("#keyword").val()+"&content="+val;
			$.ajax({
				 type: "POST",
				  url: "/email-temp.do?method=sendMail",
				 data: dataStr,
			  success:function(msg){
				  	if(msg=="ok"){
						window.close()
					}
			  	}
			});
		}else{
			return flag;
		}
	})
})
//邮件主题即时提醒
function valEmailTheme(){
	if($.trim($("#mailTheme").val()).length===0){
		$("#mailTheme ~ .errors").remove();
		$("#mailTheme").closest("td").addClass("alertborder");
		$("#mailTheme").after("<span class='errors' id='mailTheme.errors'>请输入邮件主题！</span>");
	}else{
		$("#mailTheme").closest("td").removeClass("alertborder");
		$("#mailTheme ~ .errors").remove();
	}
}
//邮件发送人即时提醒
function valEmailSender(){
	if($("#mailSender").val()===""){
		$("#mailSender ~ .errors").remove();
		$("#mailSender").closest("td").addClass("alertborder");
		$("#mailSender").after("<span class='errors' id='mailSender.errors'>请选择邮箱！</span>");
	}else{
		$("#mailSender").closest("td").removeClass("alertborder");
		$("#mailSender ~ .errors").remove();
	}
}
//邮件接收人即时提醒--暂定邮箱最多10个
function valEmailReceiver(reg){
	if($.trim($("#mailReceiver").val()).length>0){
		var receive =$.trim($("#mailReceiver").val()).split(";");
		if(receive.length<11){
			for(var i=0;i<receive.length;i++){
				if(receive[i].length===0|| receive[i].length>50 || !reg.test(receive[i])){
					$("#mailReceiver ~ .errors").remove();
					$("#mailReceiver").closest("td").addClass("alertborder");
					$("#mailReceiver").after("<span class='errors' id='mailReceiver.errors'>请输入正确的邮箱，邮箱之间用“;”分割，每个邮箱的长度小于50个字符，且数量最多10个！</span>");
				}else{
					$("#mailReceiver").closest("td").removeClass("alertborder");
					$("#mailReceiver ~ .errors").remove();
				}
			}
		}
		else{
			$("#mailReceiver ~ .errors").remove();
			$("#mailReceiver").closest("td").addClass("alertborder");
			$("#mailReceiver").after("<span class='errors' id='mailReceiver.errors'>你的输入的邮箱数量超过10个，请输入正确的邮箱，邮箱之间用“;”分割，每个邮箱的长度小于50个字符，且数量最多10个！</span>");
		}
	}
	else{
		$("#mailReceiver ~ .errors").remove();
		$("#mailReceiver").closest("td").addClass("alertborder");
		$("#mailReceiver").after("<span class='errors' id='mailReceiver.errors'>请输入正确的邮箱，邮箱之间用“;”分割，每个邮箱的长度小于50个字符，且数量最多10个！</span>");
	}
}
//页面数据初始化
function initializationData(){
	$("#mailTheme").val("");
	$("#mailSender").val("");
	$("#mailReceiver").val("");
	$("#mailCc").val("");
	$("#mailSc").val("");
	$("#replyAddress").val("");
	$("#keyword").val("");
	CKEDITOR.instances.editor.setData("");
}
//抄送人
function valEmailCc(reg){
	if($.trim($("#mailCc").val()).length>0){
		var receive =$.trim($("#mailCc").val()).split(";");
		if(receive.length<11){
			for(var i=0;i<receive.length;i++){
				if(receive[i].length===0|| receive[i].length>50 || !reg.test(receive[i])){
					$("#mailCc ~ .errors").remove();
					$("#mailCc").closest("td").addClass("alertborder");
					$("#mailCc").after("<span class='errors' id='mailCc.errors'>请输入正确的邮箱，邮箱之间用“;”分割，每个邮箱的长度小于50个字符，且数量最多10个！</span>");
				}else{
					$("#mailCc").closest("td").removeClass("alertborder");
					$("#mailCc ~ .errors").remove();
				}
			}
		}
		else{
			$("#mailCc ~ .errors").remove();
			$("#mailCc").closest("td").addClass("alertborder");
			$("#mailCc").after("<span class='errors' id='mailCc.errors'>你的输入的邮箱数量超过10个，请输入正确的邮箱，邮箱之间用“;”分割，每个邮箱的长度小于50个字符，且数量最多10个！</span>");
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
				if(receive[i].length===0|| receive[i].length>50 || !reg.test(receive[i])){
					$("#mailSc ~ .errors").remove();
					$("#mailSc").closest("td").addClass("alertborder");
					$("#mailSc").after("<span class='errors' id='mailSc.errors'>请输入正确的邮箱，邮箱之间用“;”分割，每个邮箱的长度小于50个字符，且数量最多10个！</span>");
				}else{
					$("#mailSc").closest("td").removeClass("alertborder");
					$("#mailSc ~ .errors").remove();
				}
			}
		}
		else{
			$("#mailSc ~ .errors").remove();
			$("#mailSc").closest("td").addClass("alertborder");
			$("#mailSc").after("<span class='errors' id='mailSc.errors'>你的输入的邮箱数量超过10个，请输入正确的邮箱，邮箱之间用“;”分割，每个邮箱的长度小于50个字符，且数量最多10个！</span>");
		}
	}
	else{
		$("#mailSc").closest("td").removeClass("alertborder");
		$("#mailSc ~ .errors").remove();
	}
}
//恢复地址
function valReplyAddress(reg){
	if($.trim($("#replyAddress").val()).length>0){
		var receive =$.trim($("#replyAddress").val()).split(";");
		if(receive.length<11){
			for(var i=0;i<receive.length;i++){
				if(receive[i].length===0|| receive[i].length>50 || !reg.test(receive[i])){
					$("#replyAddress ~ .errors").remove();
					$("#replyAddress").closest("td").addClass("alertborder");
					$("#replyAddress").after("<span class='errors' id='replyAddress.errors'>请输入正确的邮箱，邮箱之间用“;”分割，每个邮箱的长度小于50个字符，且数量最多10个！</span>");
				}else{
					$("#replyAddress").closest("td").removeClass("alertborder");
					$("#replyAddress ~ .errors").remove();
				}
			}
		}
		else{
			$("#replyAddress ~ .errors").remove();
			$("#replyAddress").closest("td").addClass("alertborder");
			$("#replyAddress").after("<span class='errors' id='replyAddress.errors'>你的输入的邮箱数量超过10个，请输入正确的邮箱，邮箱之间用“;”分割，每个邮箱的长度小于50个字符，且数量最多10个！</span>");
		}
	}
	else{
		$("#replyAddress ~ .errors").remove();
		$("#replyAddress").closest("td").addClass("alertborder");
		$("#replyAddress").after("<span class='errors' id='replyAddress.errors'>请输入正确的邮箱，邮箱之间用“;”分割，每个邮箱的长度小于50个字符，且数量最多10个！</span>");
	}
}
function removeWarming(){
	$("#mailTheme").closest("td").removeClass("alertborder");
	$("#mailTheme ~ .errors").remove();
	$("#mailSender").closest("td").removeClass("alertborder");
	$("#mailSender ~ .errors").remove();
	$("#mailReceiver").closest("td").removeClass("alertborder");
	$("#mailReceiver ~ .errors").remove();
	$("#mailCc").closest("td").removeClass("alertborder");
	$("#mailCc ~ .errors").remove();
	$("#mailSc").closest("td").removeClass("alertborder");
	$("#mailSc ~ .errors").remove();
	$("#replyAddress").closest("td").removeClass("alertborder");
	$("#replyAddress ~ .errors").remove();
	$("#email").closest("td").removeClass("alertborder");
	$("#email~.errors").remove();
	$("#content\\.errors").remove();
}

/**
 *
 * @param {Object} memberInfo(json格式的会员信息)
 */
function replaceTempContent(content,memberInfo){
	content = "<div>" + content + "</div>";
	var $content = $(content);
	//alert($content.find("#emailtemp_memberName").size());
	$content.find("a[name='emailtemp_memberName']").replaceWith("<span>"+memberInfo.emailtemp_memberName+"</span>");
	$content.find("a[name='emailtemp_sysTime']").replaceWith("<span>"+memberInfo.emailtemp_sysTime+"</span>");
	$content.find("a[name='emailtemp_memberGrade']").replaceWith("<span>"+memberInfo.emailtemp_memberGrade+"</span>");
	$content.find("a[name='emailtemp_memberMainBusiness']").replaceWith("<span>"+memberInfo.emailtemp_memberMainBusiness+"</span>");
	$content.find("a[name='emailtemp_memberPhone']").replaceWith("<span>"+memberInfo.emailtemp_memberPhone+"</span>");
	$content.find("a[name='emailtemp_memberFax']").replaceWith("<span>"+memberInfo.emailtemp_memberFax+"</span>");
	$content.find("a[name='emailtemp_memberEmail']").replaceWith("<span>"+memberInfo.emailtemp_memberEmail+"</span>");
	$content.find("a[name='emailtemp_comName']").replaceWith("<span>"+memberInfo.emailtemp_comName+"</span>");
	//订单服务
	$content.find("table[name='emailtemp_orders']").find("tbody").html(memberInfo.emailtemp_orders);
	//$content.find("#emailtemp_memberContact").replaceWith("<span>"+memberInfo.emailtemp_memberContact+"</span>");
	//alert($content.html());
	//alert($content[0].innerHTML);
	//alert($content[0].outerHTML);
	//$("#mailReceiver").val(memberInfo.emailtemp_memberEmail);
	return $content.html();
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
