function diaplayContract() {
	var tab = document.getElementById("list00_table");
	var rows = tab.rows;
	for(var i =0;i<rows.length;i++){
		var row = rows[i];
		if(row.STATUS=="0"){
			row.oldClassName=row.className;
			row.className="red";
		}
	}
}
UITool.AddEventHandler(window,"load",diaplayContract);