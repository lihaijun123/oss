
function diaplayDefferent() {
	var tab = document.getElementById("list00_table");
	var rows = tab.rows;
	// �q�ĤG��}�l�A�h�����D��
	for (var i = 1; i < rows.length; i++) {
		var row = rows[i];
		var next_row = rows[i + 1];
		if (next_row) {
			var cells = row.cells;
			var next_cells = next_row.cells;
			//�q�ĤG�ӳ椸��}�l�A�h���Ǹ��椸��
			for (j = 1; j < cells.length; j++) {
				if(cells[j].innerHTML!=next_cells[j].innerHTML){
					cells[j].className="red";
				}
			}
		}
	}
}
UITool.AddEventHandler(window, "load", diaplayDefferent);

