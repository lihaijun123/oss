//
function removeSearchField() {
	var fields = document.getElementsByName("searchField");
	for(var i =0 ; i < fields.length ; ) {
		fields[i].parentNode.removeChild(fields[i]);
	}
	document.getElementsByName("searchBtn")[0].onclick = reg;
}

UITool.AddEventHandler(window,"load",removeSearchField);

function reg(){
	var contractId = document.getElementsByName("CONTRACT_ID")[0];
	var accountId = document.getElementsByName("ACCOUNT_ID")[0];
	if(contractId.value==""&&accountId.value==""){
		alert("�п�J�n�d�ߪ��X�P�s���Ϊ̫Ȥ�s���I");
		return false;
	}else{
		return true;
	}
}