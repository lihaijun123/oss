$(function(){
	if($(".ok")){
		setTimeout(removeOkInfo,3000);
	}

	$("#publishedTime").datetimepicker({});

	var initJson1 = {};
	//文件上传之后获取文件编号和访问的Url
	initJson1.multi = false;
	initJson1.sizeLimit = 307200;
//	initJson1.fileExt = "*.jpg;*.gif;*.jpeg";
//	initJson1.fileDesc = "*.jpg;*.gif;*.jpeg";
	initJson1.fileExt = "*.jpg;*.jpeg";
	initJson1.fileDesc = "*.jpg;*.jpeg";
	initJson1.onComplete = uploadComplete1;
	veUploadify(initJson1, "file_upload1");

	var initJson2 = {};
	initJson2.multi = false;
	initJson2.sizeLimit = 10485670;
	initJson2.fileExt = "*.pdf;*.doc;*.docx;*.xls;*.swf";
	initJson2.fileDesc = "*.pdf;*.doc;*.docx;*.xls;*.swf";
	initJson2.onComplete = uploadComplete2;
	veUploadify(initJson2, "file_upload2");

});
function getfile_uploadUrl1Param(){
	return {type:"_pic_"};
}

//需提供文件html元素id
function getfile_upload1Id(){
	return "file_upload1";
}
function getfile_upload2Id(){
	return "file_upload2";
}

//上传到服务器后获取id和url
function uploadComplete1(event, ID, fileObj, response, data){
	var data = (new Function("return " + response))();
	data = data[0];
	var $fileSn = data.fileId;
	var $fileUrl = data.fileUrl;

	$.ajax({
		url : "/product/get-file?fileSn="+$fileSn+"&timeStamp=" + new Date().getTime(),
	    type : "POST",
	    success : function(json){
			var result = (new Function("return " + json))();
			if("notexist" == result.notexist){
				vAlertWarning("文件不存在！");
			}else{
				var width = result.width;
				var height = result.height;
				if(width > 80 || height > 100){
					height = "100px";
					width = "80px";
				}
				if(width <= 80 && height <= 100){
					width = width+"px";
					height = height+"px";
				}
				$("#titlePageSn").val($fileSn);
				$("#picAddr").val($fileUrl);
				$("#picImg").attr("src",$fileUrl).css({"width":width,"height":height});
			}
		}
	});
}

function uploadComplete2(event, ID, fileObj, response, data){
	var name = fileObj.name;
	var fileType = name.substring(name.lastIndexOf(".")+1,name.length).toLowerCase();
	if(fileType.toLowerCase() === "pdf"){
		$("#atlasFormat").val(1);
	}
	if(fileType.toLowerCase() === "doc" || fileType.toLowerCase() === "docx"){
		$("#atlasFormat").val(2);
	}
	if(fileType.toLowerCase() === "xls"){
		$("#atlasFormat").val(3);
	}
	if(fileType.toLowerCase() === "swf"){
		$("#atlasFormat").val(4);
	}
	var data = (new Function("return " + response))();
	data = data[0];
	$("#atlasFileSn").val(data.fileId);
	$("#fileAddr").val(data.fileUrl);
	$("#makeFlag").val(2);
}

$("#backAtlas").click(function() {
	location.href="/atlas?exbEncryptSn="+$("#exbEncryptSn").val();
});

function removeOkInfo(){
	$(".ok").remove();
}

$("#onlineMake").click(function(){
	var prodSn = $("#prodSns").val();
	if($.trim(prodSn) !== ""){
		var rightHtml = "";
		var prodSns = prodSn.split(",");
		for(var i = 0; i < prodSns.length; i++){
			var thisId = prodSns[i];
			var name = $("a[realId='"+thisId+"']").html();
			rightHtml += '<tr id="tr_'+thisId+'" class="o"><td><input class="right_chk" type="checkbox" onclick="subCheck()" value='+thisId+' /></td><td><a href="javascript:edit(\''+thisId+'\')">'+name.replace("<","&lt;")+'</a></td></tr>';
			$("#chk_"+thisId).attr("conceal","conceal");
			$("#dtree_"+thisId).hide();
		}
		$("#tbodyList").html(rightHtml);
	}else{
		$(".p_chk").attr("checked",false);
		$(".s_chk").attr("checked",false);
		$(".dTreeNode").show();
		$(".s_chk").removeAttr("conceal");
		$("#tbodyList").html("");
	}
	$("#saveAtlas").removeAttr("disabled");
	layer_controller("hezc_bg","chooseProd","closeImg","layer_top","closeBtn");
});
