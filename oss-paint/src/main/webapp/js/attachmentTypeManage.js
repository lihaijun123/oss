/**
 * 
 */
function initIframeHeight() {
    parent.initIframeHeightType();
}

/**
 * 合同狀態為正式合同(或者作廢合同)，附件類型為領導批復郵件的附件對銷售代表不可見
 * 附件信息下不顯示附件名稱，為空
 * 取消“修改”、“上傳附件”功能
 * 銷售代表:
 * 分公司（辦事處）高級銷售代表（100800）、分公司（辦事處）銷售代表（孕婦）（100500）、分公司（辦事處）銷售代表（10010）
 */
function hiddenAttachmentInfo() {	
	var table = document.getElementById("list00_table");
	var rows = table.rows;
	for (var i = 1 ; i < rows.length; i++){		
		var infoNode = rows[i].cells[4];
		var operNode = rows[i].cells[6];	
		var flag = rows[i].getAttribute("FLAG");
		if (flag == '0') {
			infoNode.innerHTML = "";
			operNode.innerHTML = "";
		}
	}
}

UITool.AddEventHandler(window, "load", initIframeHeight);
UITool.AddEventHandler(window, "load", hiddenAttachmentInfo);
