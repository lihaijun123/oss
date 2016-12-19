
$(function(){
	var initJson = {};
	initJson.fileExt = "*.jpg;*.jpeg";
	initJson.fileDesc = "*.jpg;*.jpeg";
	veUploadify(initJson, "file_upload2");
	veUploadify(initJson, "file_upload_0");
	veUploadify(initJson, "file_upload_1");
	veUploadify(initJson, "file_upload_2");
	veUploadify(initJson, "file_upload_3");
	veUploadify(initJson, "file_upload_4");

	//prodcase file
	var initJson3 = {};
	initJson3.fileExt = "*.apk;*.mp4;*.swf";
	initJson3.fileDesc = "*.apk;*.mp4;*.swf";
	veUploadify(initJson, "file_upload1");
});

//需提供文件html元素id
function getfile_upload1Id(){
	return "fileSn";
}

function getfile_upload2Id(){
	return "imageFileSn";
}

function getfile_upload_0Id(){
	return "fileSn_0";
}

function getfile_upload_1Id(){
	return "fileSn_1";
}

function getfile_upload_2Id(){
	return "fileSn_2";
}

function getfile_upload_3Id(){
	return "fileSn_3";
}

function getfile_upload_4Id(){
	return "fileSn_4";
}

function checkInt(obj){
	var cknum = /^[1-9]\d*$/;
	if(!cknum.test(obj.value)){
		$("#" + obj.id).val("");
	}
}

function checkPrice(obj){
	var cknum = /^[1-9]\d*[.1-9]*$/;
	if(!cknum.test(obj.value)){
		$("#" + obj.id).val("");
	}
}

function clean(idx){
	$("#tb_" + idx).find("input").val("");
	$("#tb_" + idx).find("select").val("-1");
	$("#tb_" + idx).find("img").attr("src","/js/uploadFile/pic120.gif");
}