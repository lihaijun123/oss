function getMICID() {
	var btn = document.getElementsByName("selbtn")[0];
	btn.value = " O K ";
	btn.onclick=next1;
}

function next1(){
	var items = document.getElementsByName("sel_id");
	var id = "";
	var flag = false;
	if(items.length!=0){
		for(var i = 0 ; i < items.length; i++){
			if(items[i].checked==true){
				id+=items[i].value+",";
				flag = true;
			}
		}
		if(flag){
			id = id.substr(0,id.length-1);
		}
		opener.showMICID(id);
		window.close();
	}else{
		window.close();
	}
}

function selMICID(){
	if(document.getElementsByName("micids")[0]){
		var ids = document.getElementsByName("micids")[0].value;
		var arr = ids.split(",");
		var items = document.getElementsByName("sel_id");
		for(var i=0;i<arr.length;i++){
			for(var j=0;j<items.length;j++){
				if(arr[i]==items[j].value){
					items[j].checked=true;
				}
			}
		}
	}
}

UITool.AddEventHandler(window, "load", getMICID);
UITool.AddEventHandler(window, "load", selMICID);