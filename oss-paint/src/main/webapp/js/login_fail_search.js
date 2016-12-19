//
function removeSearchField() {
	var fields = document.getElementsByName("searchField");
	for(var i =0 ; i < fields.length ; ) {
		fields[i].parentNode.removeChild(fields[i]);
	}
}

UITool.AddEventHandler(window,"load",removeSearchField);

