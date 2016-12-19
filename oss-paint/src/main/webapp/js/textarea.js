	//去掉空格
	function trimBlankStr_1(contentId){
		var ui = document.getElementById(contentId).value;
		var notValid_1 = /&nbsp;/;
		var notValid_2 = /\s/;
		while(notValid_1.test(ui)){
			ui = ui.replace(notValid_1, "");
		}
		while(notValid_2.test(ui)){
			ui = ui.replace(notValid_2, "");
		}
		document.getElementById(contentId).value = ui;
	}
	//段首空两格
	function headAdTwoBlank_1(contentId){
		var ui = document.getElementById(contentId).value;
		document.getElementById(contentId).value = "  " + ui;
	}
	//删除段间空行
	function deleteParagraphBlank_1(contentId){
		var ui = document.getElementById(contentId).value;
		var notValid_1 = /\n\r/;
		while(notValid_1.test(ui)){
			ui = ui.replace(notValid_1, "");
		}
		document.getElementById(contentId).value = ui;
	}