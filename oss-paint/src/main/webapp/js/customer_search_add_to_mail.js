
function searchAdd2mailList() {
	var btnTd = document.getElementById("selOperTd");
	btnTd.innerHTML = "<input type='button' value='添加至客戶郵寄信息列表>>' name='mailBtn' onclick='_doNext()'>  " + "  <input type=\"button\" name=\"selbtn\" value=\"下一步>>\" onclick=\"_doNext1()\"/>";
}
UITool.AddEventHandler(window, "load", searchAdd2mailList);
function _doNext() {
	var checked = false;
	var selIds = document.getElementsByName("sel_id");
	for (var i = 0; i < selIds.length; i++) {
		if (selIds[i].checked) {
			checked = true;
			break;
		}
	}
	if (checked) {
		if (confirm("確認所要導入的數據無誤，否則發送郵件失敗時，後果自負！")) {
			document.nextForm.action="customerMailManage.do?method=add2MailList";
			document.nextForm.submit();
		}
	} else {
		alert("請先在多選框進行選擇！");
	}
}
function _doNext1() {
	document.nextForm.action = "customer.do?method=operateView";
	doNext();
}

