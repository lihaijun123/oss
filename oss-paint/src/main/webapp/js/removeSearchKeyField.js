
/*
*用于添加前查詢列表中是否全部沒有值的判斷
*/
try {
	window._searchAll = false;
//是否需要防止把這些字段的值拼到列表的SQL中
	var isNeedClearFields = true;
}
catch (e) {
}
function removeSearchKeyField(hasValue) {
	if (hasValue) {
		var fields = document.getElementsByName("searchField");
		for (var i = 0; i < fields.length; ) {
			fields[i].parentNode.removeChild(fields[i]);
		}
		if(document.getElementsByName("searchBtn") && document.getElementsByName("searchBtn")[0]) {
			document.getElementsByName("searchBtn")[0].outerHTML="<font color='red'>正在查詢……</font>";
		}
	}
}

