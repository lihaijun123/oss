function showBackBtn (){
	if(document.getElementById("searchBtn")) {
		var contactRecordId = document.getElementById("customerId");
		if(contactRecordId) {
			contactRecordId = contactRecordId.value;
			document.getElementById("searchBtn").outerHTML += ' <input type="button" name="downBtn" title="返回客服聯系記錄詳細信息界面" value="返回" onclick="gotoUrl(\'uitoolList.ui?funcID=547&customerId='+contactRecordId+'\')">';
		}
	}
}
function gotoUrl(url) {
	window.location = url;
}
UITool.AddEventHandler(window,"load",showBackBtn);