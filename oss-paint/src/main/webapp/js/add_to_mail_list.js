
/**
將所選客戶加入到郵寄發送列表中
*/
function add2mailList() {
	var btn = document.getElementsByName("selbtn")[0];
	btn.value = "添加至客戶郵寄信息列表>>";
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
			if (confirm("請確認所要導入的數據無誤！")) {
				document.nextForm.submit();
			}
		} else 
			alert("請先在多選框進行選擇！");
	};
}
UITool.AddEventHandler(window, "load", add2mailList);

