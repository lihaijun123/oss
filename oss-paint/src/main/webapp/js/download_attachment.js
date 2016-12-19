function addDownLoadBtn() {
	var btn = document.getElementsByName("selbtn")[0];
	var row = btn.parentNode.parentNode;
	var cell1 = row.insertCell(-1);
	row.insertBefore(cell1,btn.parentNode);
	cell1.align="left";
	cell1.innerHTML='<input type="button" value="批量下載附件" onclick="downloadSel();">';
}

function downloadSel(){
	var flag=false;
	var sel_ids = document.getElementsByName("sel_id");
	for(var i = 0 ; i < sel_ids.length ; i++) {
		if(sel_ids[i].checked) {
			flag = true;
			break;
		}
	}
	if(!flag){
		alert("請選擇要下載的附件！");
		return;
	}
	else {
		var form = document.nextForm;
		form.action="attachment.do?method=download";
		form.submit();	
	}
}

function changeTarget() {
	var form = document.nextForm;
	form.target = "_self";
}

UITool.AddEventHandler(window, "load", changeTarget);
UITool.AddEventHandler(window, "load", addDownLoadBtn);
