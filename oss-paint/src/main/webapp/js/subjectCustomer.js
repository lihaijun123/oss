function addGoBack() {
	var oper = document.getElementById("id_td__oper_");
	oper.innerHTML += "&nbsp;<a href='uitoolList.ui?funcID=950012'>��^</a>";
}

function deleteCustomer(recordId,subjectId,sendFlag) {
	if(sendFlag!='1'){
		if(window.confirm("�T�w�n�R����e�D�D�U�ӫȤ�H")){
			location.href='subCustomer.do?method=deleteCustomer&recordId='+recordId+'&subjectId='+subjectId;
		}
	}else{
		alert("�w�o�e���Ȥᤣ�i�R���I");
	}
}
UITool.AddEventHandler(window, "load", addGoBack);


