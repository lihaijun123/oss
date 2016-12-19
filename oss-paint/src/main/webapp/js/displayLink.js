function scanTable() {
	var tabObj = document.getElementById("list00_table");
	var rows = tabObj.rows;
	for (var i = 1; i < rows.length; i++) {
		var cell = rows[i].cells[rows[i].cells.length - 1];
		var cstCell = rows[i].cells[1];
		var value = cell.innerText;
		var cstId = cstCell.innerText;
		cstId = rows[i].account_id;
		if (cstId == "1") {
			cell.innerHTML = "<a href=\"customer.do?method=modify&customerId=" + cstId + "\">н╫зя</a>";
		} else {
			cell.innerHTML = "&nbsp;";
		}
	}
}
function onLoading() {
	//document.getElementById();
	UITool.AddEventHandler(document.searchForm, "submit", checkSubmit);
	scanTable();
}