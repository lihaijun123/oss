
/**
�N�ҿ�Ȥ�[�J��l�H�o�e�C��
*/
function add2mailList() {
	var btn = document.getElementsByName("selbtn")[0];
	btn.value = "�K�[�ܫȤ�l�H�H���C��>>";
	btn.onclick = function _doNext() {
		var checked = false;
		var selIds = document.getElementsByName("sel_id");
		for (var i = 0; i < selIds.length; i++) {
			if (selIds[i].checked) {
				checked = true;
				break;
			}
		}
		if (checked) {
			if (confirm("�нT�{�ҭn�ɤJ���ƾڵL�~�I")) {
				document.nextForm.submit();
			}
		} else 
			alert("�Х��b�h��ضi���ܡI");
	};
}
UITool.AddEventHandler(window, "load", add2mailList);

