function showRemind(){
	var tab = document.getElementById("list00_rectable");
	var row = tab.insertRow(-1);
	var cell = row.insertCell(-1);
	cell.innerHTML="<span style='color:red'>�q�{�L�h6�Ѥ����ƾڡA�j���d�ݧ�h�C</span>";
}

UITool.AddEventHandler(window,"load",showRemind);