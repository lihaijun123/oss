//用于添加前查詢列表,如果是FrameSet框架就隱藏最後一列
function hiddenAdd() {
	if (window.parent.isFrameSet != null) {//使用FrameSet上下分欄形式
		var tabObj = document.getElementById("list00_table");
		var rows = tabObj.rows;
		for (var i = 0; i < rows.length; i++) {
			var cell = rows[i].cells[rows[i].cells.length - 1];
			cell.innerHTML = "";
		}
	}
}
UITool.AddEventHandler(window, "load", hiddenAdd);

