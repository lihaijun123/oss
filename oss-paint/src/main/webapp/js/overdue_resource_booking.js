function showRemind(){
	var tab = document.getElementById("list00_rectable");
	var row = tab.insertRow(-1);
	var cell = row.insertCell(-1);
	cell.innerHTML="<span style='color:red'>默認過去6天內的數據，搜索查看更多。</span>";
}

UITool.AddEventHandler(window,"load",showRemind);