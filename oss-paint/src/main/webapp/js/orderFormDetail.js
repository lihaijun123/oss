function diaplayRelet() {
	var td1=document.getElementById("act_panel");
	if(td1) {
		td1.innerHTML=" <input type='button' name='backList' value='ªð¦^' onclick='back();'/> ";
	}
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

