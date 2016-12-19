
function hiddenLastCol() {
	if (document.theForm.action.indexOf("isExbtion=yes")>0) {
		var tabObj = document.getElementById("list00_table");
		var rows = tabObj.rows;
		for (var i = 0; i < rows.length; i++) {
			var cell = rows[i].cells[rows[i].cells.length - 1];
			var cstCell = rows[i].cells[1];
			var value = cell.innerText;
			var cstId = cstCell.innerText;
			cstId = rows[i].account_id;
			cell.innerHTML = "&nbsp;";
		}
	}
}
UITool.AddEventHandler(window, "load", hiddenLastCol);

