function showBackBtn (){
	if(document.getElementById("searchBtn")) {
		var contactRecordId = document.getElementById("customerId");
		if(contactRecordId) {
			contactRecordId = contactRecordId.value;
			document.getElementById("searchBtn").outerHTML += ' <input type="button" name="downBtn" title="��^�ȪA�p�t�O���ԲӫH���ɭ�" value="��^" onclick="gotoUrl(\'uitoolList.ui?funcID=547&customerId='+contactRecordId+'\')">';
		}
	}
}
function gotoUrl(url) {
	window.location = url;
}
UITool.AddEventHandler(window,"load",showBackBtn);