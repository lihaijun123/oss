function diaplayRelet() {
	var td1=document.getElementById("act_panel");
	if(td1) {
		td1.innerHTML=" <input type='button' name='backList' value='��^' onclick='back();'/> ";
	}
	var tab = document.getElementById("list00_rectable");
	var row = tab.insertRow(-1);
	var cell = row.insertCell(-1);
	cell.innerHTML="<span style='color:red'>�q�{�i�̪ܳ�7�Ѫ��ƾڡA�p�ݬd�ݧ�h�A�зj���C</span>";
}
function back(){
	if((document.all && history.length>0) || history.length>1) {
		history.back(-1);
	}else{
		window.opener = null;
		window.open('','_top');
		window.close();
	}
}
UITool.AddEventHandler(window,"load",diaplayRelet);

