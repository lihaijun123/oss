/**
 * ��l�ơi�U�@�B�j���s�ƥ�
 * @return
 */
function initEvent() {

	window.doNext = function() {
		var checked = false;
		var selIds = document.getElementsByName("sel_id");
		for (var i = 0; i < selIds.length; i++) {
			if (selIds[i].checked) {
				checked = true;
				break;
			}
		}
		if (checked) {
			var contractId = document.getElementsByName("contractId")[0].value;
			var form = document.nextForm;
			form.action = "contractAttachment.do?method=importAttachment&contractId=" + contractId;
			form.submit();	
			if (parent) {
				parent.closeDWindow();	
			}	
		}
		else {
			alert("�п�ܻݭn�ɤJ������I");
		}	
	}
	
}

UITool.AddEventHandler(window, "load", initEvent);