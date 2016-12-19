//進入頁面初始化元素
UITool.AddEventHandler(window, "load", batchAction);
// 設置按鈕和單擊事件
function batchAction() {
	var btn = document.getElementsByName("selbtn")[0];
	btn.value = " 下一步 ";
	btn.onclick = batch;
}
// 批量操作
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
		alert('請先選擇數據');
	}
}
//刪除數據
function deleteLimitOrg(url) {
	var id = document.searchForm.funcID.value;
	var path = window.location.href;
	if (confirm("您是否確認要刪除當前信息？")) {
		window.location.href = url + "&funcID=" + id + "&path=" + encodeURIComponent(path);
	}
}