function diaplayRelet() {
	var button1=document.all.selbtn;
	// alert("  sel "+button1.onclick);
	button1.style.display="none";
	var td1=button1.parentNode;
	// alert(" td "+td1.innerHTML);
	td1.innerHTML=" <input type='button' name='selbtn1' value='下一步>>' onclick='doNext1()'/> ";
	// alert("  sel "+button1.onclick);
}
function doNext1() {
	// alert("aa");
	var text1="";
	var checked1 = false;
	var selIds1 = document.getElementsByName("sel_id");
	// alert("  selIds1 "+selIds1);
	for(var i = 0 ; i < selIds1.length ; i++) {
		if(selIds1[i].checked) {
			checked1 = true;
			break;
		}
	}
	for(var i = 0 ; i < selIds1.length ; i++) {
		if(selIds1[i].checked) {
			text1+=","+selIds1[i].value;
			// alert(text1);
		}
	}
	text1=text1.substr(1);
	//  alert(text1);
	if(checked1){
		opener.selObj(text1);
		window.close();
	}else{
		alert("請選擇!");
	}
}

UITool.AddEventHandler(window,"load",diaplayRelet);