
$(function(){
	var initJson1 = {};
	initJson1.fileExt = "*.jpg;*.jpeg";
	initJson1.fileDesc = "*.jpg;*.jpeg";
	veUploadify(initJson1, "file_upload1");
	
	var initJson2 = {};
	initJson2.fileExt = "*.unity3d";
	initJson2.fileDesc = "*.unity3d";
	veUploadify(initJson2, "file_upload2");
});


function getfile_upload2Id(){
	return "modelFileSn";
}

function getfile_upload1Id(){
	return "picFileSn";
}