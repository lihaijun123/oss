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
		alert("請輸入要查詢的合同編號或者客戶編號！");
		return false;
	}else{
		return true;
	}
}