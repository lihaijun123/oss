function diaplayRelet() {
	var tab = document.getElementById("list00_table");
	var rows = tab.rows;
	for(var i = 1 ; i < rows.length ;i++) {
		// �q�{���� ��w �챵
		if(rows[i].IS_RELET_DISPLAY == 0) {
			//���ιw�w���~�]�Y���q���~�^
			rows[i].cells[rows[i].cells.length-2].innerHTML = "N/A";
		}else if(rows[i].IS_RELET_DISPLAY == 2) {
		   //���w�w�귽�����~�A�B�w�Q��w 
			rows[i].cells[rows[i].cells.length-2].innerHTML = "�w��w";
		}
		if(rows[i].RELET_PACT1 == 0) {
			//�M�\�l���~�������
			rows[i].cells[rows[i].cells.length-1].innerHTML = "N/A";
		}
	}
}
UITool.AddEventHandler(window,"load",diaplayRelet);