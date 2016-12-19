//
function removeDiv() {
	var div = document.getElementById("act_panel");
	div.parentNode.removeChild(div);
}

UITool.AddEventHandler(window,"load",removeDiv);


