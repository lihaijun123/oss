
function setDisplayFormat2Pre() {
	var tab = document.getElementById("list00_table");
	var rows = tab.rows;
	for (var i = 1; i < rows.length; i++) {
		var cells = rows[i].cells;
		for (var j = 0; j < cells.length; j++) {
			var text = cells[j].innerText;
			if (cells[j].innerHTML == text) {
				cells[j].innerHTML = "<pre>" + text + "</pre>";
			}
		}
	}
}
UITool.AddEventHandler(window, "load", setDisplayFormat2Pre);

