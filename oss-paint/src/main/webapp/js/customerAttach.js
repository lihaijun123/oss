function showBackBtn (){
	if(document.getElementById("searchBtn")) {
		var contactRecordId = document.getElementById("contactRecordId");
		if(contactRecordId) {
			contactRecordId = contactRecordId.value;
			document.getElementById("searchBtn").outerHTML += ' <input type="button" name="downBtn" title="返回業務聯系記錄詳細信息界面" value="返回" onclick="gotoUrl(\'contactRecord.do?method=details&contactRecordId='+contactRecordId+'\')">';
		}
	}
}
function gotoUrl(url) {
	window.location = url;
}
UITool.AddEventHandler(window,"load",showBackBtn);