function showRemind(){
	var tab = document.getElementById("list00_rectable");
	var row = tab.insertRow(-1);
	var cell = row.insertCell(-1);
	cell.innerHTML="<span style='color:red'>�q�{�i�̪ܳ�3�Ѫ��ƾڡA�p�ݬd�ݧ�h�A�зj���C</span>";
}

UITool.AddEventHandler(window,"load",showRemind);