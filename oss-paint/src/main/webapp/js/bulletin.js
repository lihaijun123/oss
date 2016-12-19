
function show(aut_model,needShow) {
	if(needShow)
		popDWindow("公 告 欄", "bulletin.do?method=more&top=5&aut_model=" + aut_model, 600, 350);
}
function hidden() {
	if (parent && parent.closeDWindow) {
		parent.closeDWindow();
	}
}
function redirectTo(url) {
	if (parent && parent.closeDWindow) {
		//將鏈接在工作區打開
		window.parent.document.getElementById("centerPage").src = url;
		//激活工作區TAB
		window.parent.top.tabObj.activate("workid");
		//關閉公告欄
		hidden();
	} else {
		window.location.href = url;
	}
}

