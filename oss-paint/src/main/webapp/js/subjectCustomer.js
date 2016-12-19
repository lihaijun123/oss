function addGoBack() {
	var oper = document.getElementById("id_td__oper_");
	oper.innerHTML += "&nbsp;<a href='uitoolList.ui?funcID=950012'>返回</a>";
}

function deleteCustomer(recordId,subjectId,sendFlag) {
	if(sendFlag!='1'){
		if(window.confirm("確定要刪除當前主題下該客戶？")){
			location.href='subCustomer.do?method=deleteCustomer&recordId='+recordId+'&subjectId='+subjectId;
		}
	}else{
		alert("已發送的客戶不可刪除！");
	}
}
UITool.AddEventHandler(window, "load", addGoBack);


