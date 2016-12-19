function initBtn() {
	var btn1 = document.getElementsByName("Submit")[0];
	btn1.parentNode.removeChild(btn1);
	var btn2 = document.getElementById("reset");
	btn2.parentNode.removeChild(btn2);
	var btn3 = document.getElementsByName("Submit2")[0];
	btn3.className = "submitc";
	btn3.onclick = back;
}

function back() {
	if (history.length == 0) {
		window.opener = null;
		window.open('', '_top');
		window.close();
	} else {
		history.back();
	}
}

UITool.AddEventHandler(window, "load", initBtn);
