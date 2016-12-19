/*
*用于添加前查詢列表中檢查每個字段的長度是否不小于3
*/
try {
	isLengthLargeThan=true;
}
catch (e) {
}
function checkLengthLarge(hasValue) {
	if (hasValue) {
		isLargeThan=true;
		var fields = document.getElementsByName("searchField");
		for (var i = 0; i < fields.length;i++ ) {
			var field = fields[i].value;
			if(field.length >0 && document.getElementsByName(field)!=null && document.getElementsByName(field).length > 0) {
				var value = UITool.trim(document.getElementsByName(field)[0].value);
				if(value.length>0 && value.length<2){
					isLargeThan = false;
				}
			}
		}
		if(!isLargeThan){
			alert("輸入的字符長度不能小于2！");
			return false;
		}
		else{
      /*
			var reg=/[^\d]+/g;
			if(document.getElementsByName("TELEPHONE")){
				var tel = document.getElementsByName("TELEPHONE")[0].value;
				document.getElementsByName("TELEPHONE")[0].value = tel.replace(reg,"");
			}
			if(document.getElementsByName("MOBILE")){
				var tel = document.getElementsByName("MOBILE")[0].value;
				document.getElementsByName("MOBILE")[0].value = tel.replace(reg,"");
			}
			if(document.getElementsByName("FAX")){
				var tel = document.getElementsByName("FAX")[0].value;
				document.getElementsByName("FAX")[0].value = tel.replace(reg,"");
			}
      */
			if(document.getElementsByName("searchBtn") && document.getElementsByName("searchBtn")[0]) {
				document.getElementsByName("searchBtn")[0].outerHTML="<font color='red'>正在查詢……</font>";
			}
			return true;
		}
	}
	return true;
}

