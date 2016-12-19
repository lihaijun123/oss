/**
 * 产品类别1级目录选择
 * add by lihaijun
 * 2012/1/11
 */


function openCategoryWin(showId, hiddenId){
	var rv = window.showModalDialog("/js/category/catePop.jsp","","dialogWidth=400px;dialogHeight=400px");
	if(rv){
		document.getElementById(showId).value = rv["text"];
		document.getElementById(hiddenId).value = rv["value"];
	}
}
