//---------------
function addNewTel(tabId,nationalNo,countryNo,telNo,ext,id,remark) {
	var id_tab_tel = document.getElementById(tabId);
	var lRow = id_tab_tel.rows[id_tab_tel.rows.length-1];
	var index = lRow.rowIndex;
	var nationalNoV = document.getElementsByName(nationalNo);
	nationalNoV  = nationalNoV[index-2].value || '86';
	var countryNoV = document.getElementsByName(countryNo);
	countryNoV  = countryNoV[index-2].value ;
	var html ='<input type="text" name="'+nationalNo+'" value="'+ nationalNoV +'" maxlength="4" size="5"/> ';
	html += '- <input type="text" name="'+countryNo+'" value="'+ countryNoV +'" maxlength="4" size="5"/> ';
	html += '- <input type="text" name="'+telNo+'" value="" maxlength="11" size="13"/> ';
	html += '- <input type="text" name="'+ext+'" value="" maxlength="6" size="11"/> ';
	html += '- <input type="text" name="'+remark+'" value="" maxlength="25" size="25"/> &nbsp;&nbsp';
	html += '<input type="button" value="X" onclick="delRowTel(this,\''+ tabId +'\')" class="submitc">';
	html += '<input type="hidden" name="'+id+'" value="-1">';
	var newRow = id_tab_tel.insertRow(-1);
	var newCell = newRow.insertCell(-1);
	newCell.innerHTML = html;
}
function delRowTel(btnObj,tabId,name) {
	var pTrObj = btnObj.parentNode.parentNode;
	var id_tab_tel = document.getElementById(tabId);
	/*
	var delId = btnObj.nextSibling.value;
	if(delId!=-1){
		alert(delId);
		addDelIDs('delIds',delId);
	}
	*/
	var tdObj = btnObj.parentNode;
	var arr = tdObj.childNodes;
	for(var i =0;i<arr.length;i++){
		if(arr[i].type=="hidden"){
			var delId = arr[i].value;
			if(delId!=-1){
				addDelIDs('delIds',delId);
			}
		}
	}
	id_tab_tel.deleteRow(pTrObj.rowIndex);
}


function addUrlTel(tabId) {
	var id_tab_tel = document.getElementById(tabId);
	var html ='<input type="text" name="urls" value="" size="40"> &nbsp;&nbsp';
	html += '<input type="button" value="X" onclick="delRowTel(this,\''+ tabId +'\')" class="submitc">';
	var newRow = id_tab_tel.insertRow(-1);
	var newCell = newRow.insertCell(-1);
	newCell.innerHTML = html;
}

//
// 記錄被刪除的ID，中間用‘,’號隔開
function addDelIDs(id,delId){
	var v = document.getElementById(id);
	 if(v.value==""){
		 v.value = delId;
	 }else{
		 v.value = v.value+","+delId;
	 }
}

//
function delGroup(){
	document.getElementById("groupId").value="";
	document.getElementById("groupNameCn").value="";
}