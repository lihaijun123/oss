/*
 * @copyright 2012 Focus Technology, Co., Ltd. All rights reserved.
 */
/**
 * @description
 * @author chenyang
 */

/**
 * 打开图片编辑器参数
 * @param {Object} file_sn
 * @param {Object} file_url
 * @param {Object} callBack
 */
function openImageEditor(fieldId, imageFieldId, file_sn, file_url, callBack){
	if(file_sn&&file_url&&callBack){
		var params=
		"height=768,"+
		"width=1024,"+
		"toolbar=no,"+
		"menubar=no,"+
		"scrollbars=no,"+
		"status=yes";
		window.open('/script/ImageEditor/ImageEditor.html?fieldId=' + fieldId + '&imageFieldId=' + imageFieldId + '&file_url='+file_url+"&file_sn="+file_sn+"&callBack="+callBack,'imageEditor',params);
	}
}

function imageEdit(fieldId, imageFieldId){
	openImageEditor(fieldId, imageFieldId, $("#" + fieldId).val(), $("#" + imageFieldId).attr("src"), "file_callback");
}

function file_callback(fieldId, imageFieldId, fileId, fileUrl){
	//替换新的图片
	$("#" + fieldId).val(fileId);
	$("#" + imageFieldId).attr("src", fileUrl);
}

