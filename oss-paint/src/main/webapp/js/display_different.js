
function diaplayDefferent() {
	var tab = document.getElementById("list00_table");
	var rows = tab.rows;
	// 從第二行開始，去除標題行
	for (var i = 1; i < rows.length; i++) {
		var row = rows[i];
		var next_row = rows[i + 1];
		if (next_row) {
			var cells = row.cells;
			var next_cells = next_row.cells;
			//從第二個單元格開始，去除序號單元格
			for (j = 1; j < cells.length; j++) {
				if(cells[j].innerHTML!=next_cells[j].innerHTML){
					cells[j].className="red";
				}
			}
		}
	}
}
UITool.AddEventHandler(window, "load", diaplayDefferent);

