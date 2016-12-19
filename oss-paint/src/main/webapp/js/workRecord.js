function diaplayRelet() {
	var td1=document.getElementById("act_panel");
	if(td1) {
		td1.innerHTML=" <input type='button' name='backList' value='返回' onclick='back();'/> ";
	}
	var tab = document.getElementById("list00_rectable");
	var row = tab.insertRow(-1);
	var cell = row.insertCell(-1);
	cell.innerHTML="<span style='color:red'>默認展示最近7天的數據，如需查看更多，請搜索。</span>";
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

