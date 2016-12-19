//
function selSubject() {
	var btn = document.getElementsByName("selbtn")[0];
	btn.onclick = doNext1;
}

function doNext1() {
	var arr = document.getElementsByName("sel_id");
	var v="";
	for(var i=0;i<arr.length;i++){
		if(arr[i].checked==true){
			if(v.length==0){
				v+=arr[i].value;
			}else{
				v+=","+arr[i].value;
			}
		}
	}
	if(v!=""){
		opener.selectSubject(v);
	}
	window.close();
}

function showSubjectType(){
	var arrKey={2:'展台',5:'BUTTON AD',6:'摩天大樓',b:'大展台'};
	var tab = document.getElementById("list00_table");
	for(var i=1;i<tab.rows.length;i++){
		var row = tab.rows[i];
		var cell = row.cells[row.cells.length-1];
		var key = cell.childNodes[0].data;
		var keys = key.split(",");
		var value="";
		for(var j=0;j<keys.length;j++){
			if(arrKey[keys[j]]!=undefined){
				value+=arrKey[keys[j]]+";";
			}
		}
		if(value==""){
			value=key;
		}else{
			value=value.substring(0,value.length-1);
		}
		cell.childNodes[0].data=value;
	}
}

UITool.AddEventHandler(window, "load", selSubject);
UITool.AddEventHandler(window, "load", showSubjectType);