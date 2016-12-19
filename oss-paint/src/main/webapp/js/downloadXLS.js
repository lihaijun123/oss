//用于創建將開放客戶列表導出到Excel表格中的按鈕
UITool.AddEventHandler(window, "load", showDownloadBtn_preCheck);



//導出之前，檢查列表中是否有數據記錄
function showDownloadBtn_preCheck() {

	if (document.getElementById("searchBtn")) {
		document.getElementById("searchBtn").outerHTML += ' <input type="button" name="downBtn" title="保存當前條件下的所有數據到Excel文件" value="導出到Excel表格" onclick="download_preCheck(\'down-xls\')">';
	}
}
function download_preCheck(dtype) {
	var listTable=document.getElementById("list00_table");//列表內容對應的table對象（若它包含的行數>1就說明有數據）
	if(listTable.rows.length>1){
		download(dtype) ;
	}
	else{
		alert("目前沒有數據可導出，請先查詢！");
	}
}