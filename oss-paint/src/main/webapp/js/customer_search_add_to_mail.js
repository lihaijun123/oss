
function searchAdd2mailList() {
	var btnTd = document.getElementById("selOperTd");
	btnTd.innerHTML = "<input type='button' value='�K�[�ܫȤ�l�H�H���C��>>' name='mailBtn' onclick='_doNext()'>  " + "  <input type=\"button\" name=\"selbtn\" value=\"�U�@�B>>\" onclick=\"_doNext1()\"/>";
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
		if (confirm("�T�{�ҭn�ɤJ���ƾڵL�~�A�_�h�o�e�l�󥢱ѮɡA��G�ۭt�I")) {
			document.nextForm.action="customerMailManage.do?method=add2MailList";
			document.nextForm.submit();
		}
	} else {
		alert("�Х��b�h��ضi���ܡI");
	}
}
function _doNext1() {
	document.nextForm.action = "customer.do?method=operateView";
	doNext();
}

