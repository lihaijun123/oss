//�i�J������l�Ƥ���
UITool.AddEventHandler(window, "load", batchAction);
// �]�m���s�M�����ƥ�
function batchAction() {
	var btn = document.getElementsByName("selbtn")[0];
	btn.value = " �U�@�B ";
	btn.onclick = batch;
}
// ��q�ާ@
function batch() {
	var checked = false;
	var selIds = document.getElementsByName("sel_id");
	var userId = "'";
	for ( var i = 0; i < selIds.length; i++) {
		if (selIds[i].checked) {
			userId = userId + selIds[i].value + "','";
			checked = true;
		}
	}
	var realUserId = userId.substring(0, userId.length - 2)
	if (checked) {
		var url = "/accountLimitUser.do?method=batchModify&userId=" + realUserId;
		var id = document.searchForm.funcID.value;
		var path = window.location.href;
		window.location.href = url + "&funcID=" + id + "&path=" + encodeURIComponent(path);
	} else {
		alert('�Х���ܼƾ�');
	}
}
//�R���ƾ�
function deleteLimitOrg(url) {
	var id = document.searchForm.funcID.value;
	var path = window.location.href;
	if (confirm("�z�O�_�T�{�n�R����e�H���H")) {
		window.location.href = url + "&funcID=" + id + "&path=" + encodeURIComponent(path);
	}
}