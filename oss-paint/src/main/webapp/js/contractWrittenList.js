function addBatchAllot() {
	var oper = document.getElementById("id_td__oper_");
	oper.innerHTML += "&nbsp;<a href='contractWritten.do?method=batchAllot'>��q���t</a>";
}
UITool.AddEventHandler(window, "load", addBatchAllot);