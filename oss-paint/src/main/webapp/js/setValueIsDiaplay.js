function diaplayRelet() {
	var tab = document.getElementById("list00_table");
	var rows = tab.rows;
	for(var i = 1 ; i < rows.length ;i++) {
		// 默認提供 續定 鏈接
		if(rows[i].IS_RELET_DISPLAY == 0) {
			//不用預定產品（即普通產品）
			rows[i].cells[rows[i].cells.length-2].innerHTML = "N/A";
		}else if(rows[i].IS_RELET_DISPLAY == 2) {
		   //有預定資源的產品，且已被續定 
			rows[i].cells[rows[i].cells.length-2].innerHTML = "已續定";
		}
		if(rows[i].RELET_PACT1 == 0) {
			//套餐子產品不能續約
			rows[i].cells[rows[i].cells.length-1].innerHTML = "N/A";
		}
	}
}
UITool.AddEventHandler(window,"load",diaplayRelet);