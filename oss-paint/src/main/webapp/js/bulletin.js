
function show(aut_model,needShow) {
	if(needShow)
		popDWindow("�� �i ��", "bulletin.do?method=more&top=5&aut_model=" + aut_model, 600, 350);
}
function hidden() {
	if (parent && parent.closeDWindow) {
		parent.closeDWindow();
	}
}
function redirectTo(url) {
	if (parent && parent.closeDWindow) {
		//�N�챵�b�u�@�ϥ��}
		window.parent.document.getElementById("centerPage").src = url;
		//�E���u�@��TAB
		window.parent.top.tabObj.activate("workid");
		//�������i��
		hidden();
	} else {
		window.location.href = url;
	}
}

