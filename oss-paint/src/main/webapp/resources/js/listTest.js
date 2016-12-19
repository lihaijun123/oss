
function catInfoBtn() {
	var btnTd = document.getElementById("selOperTd");
	btnTd.innerHTML = "<input type='button' value='  PDF生成  ' onclick='create_pdf()' />&nbsp;&nbsp;";
}

function create_pdf(){
	window.location="/listTest.do?method=createPdf";
}

UITool.AddEventHandler(window, "load", catInfoBtn);