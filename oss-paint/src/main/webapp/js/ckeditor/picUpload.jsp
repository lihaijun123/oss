<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<script type="text/javascript" src="/js/jquery.js"></script>
	<%@include file="/WEB-INF/jsp/common/uploadfile.jsp" %>
	<style type="text/css">
		#file_uploadUploader{width: 50%;height: 25px;}
	</style>
	<script type="text/javascript">
		window.onload = function(){
			var initJson = {};
			initJson.fileDesc = "图像文件(*.jpg,*.jpeg)";
			initJson.fileExt = "*.jpg;*.jpeg";
			initJson.sizeLimit = 102400;//限制上传大小为300kb
			initJson.onComplete = uploadComplete;
			veUploadify(initJson, "file_upload");
		}

		function uploadComplete(event, ID, fileObj, response, data){
			//文件上传空间对象的id
			var uploadFileId = event.currentTarget.id;

			//文件文本域id一般对应model中的一个属性
			var filedId = eval("get" + event.currentTarget.id + "Id()");
			//返回文件id,url
			var rv = eval("(" + response + ")");
			//新保存的id
			var newUrl = rv[0].fileUrl;
			closeWin(newUrl);
		}

		function getfile_uploadId() {
			return "picSn";
		}

		function closeWin(value){
			window.returnValue = value;
			window.close();
		}
	</script>
</head>
<body>
	<div style="width: 99%;float: left;margin: 20px 0 0 20px">
		<input id="file_upload" name="file_upload" type="file" /><br>
		 <!--
	    <a href="javascript:jQuery('#file_upload').uploadifyUpload()">上传图片</a>
	     -->
        <a href="javascript:closeWin('')">取消</a>
	    <input type="hidden" id="picSn" name="picSn"/>
   </div>
</body>
</html>