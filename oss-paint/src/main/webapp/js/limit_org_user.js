//為驗證框架傳值
function validateFields(obj) {
 	validateField(obj,obj,"validateMethod,limitNumAdmin");
}
// 為驗證框架傳值（批量頁面用）
function validateFieldMod(obj) {
	validateField(obj,obj,"validateMethod,batchAction");
}
// 獲取單選鈕的值
function getRadioValue(name){
	var radios = $(name,0);
	for(var i=0;i<radios.length;i++){
		if(radios[i].checked==true){
			return radios[i].value;
		}
	}
}
// 批量修改提交判斷
function doSubmit() {
	if(getRadioValue("batchAction")=='0'){
		if(confirm("您是否確認要刪除當前部門角色的系統數量限制？如果刪除對應的角色將沒有數量的上限控制。")){
			document.getElementById("crmAccountLimitOrgRole").submit();
	    }
	}else{
		/*
		 * if(!isPlusInteger($("limitNum").value)){ alert("客戶上限不是正整數"); return
		 * false; }
		 */
		if($("funcID").value=='951155'){
			var check = true;
			var tmps = $("limitNums").value.split(",");
			for ( var i = 0; i < tmps.length; i++) {
				if($("limitNum").value < parseInt(tmps[i]) && $("limitNum").value!="-1"){
					//alert($("limitNum").value+"--"+tmps[i]);
					check = false;
					break;
				}else if(tmps[i]=="無限量" && $("limitNum").value!="-1"){
					check = false;
					break;
				}
			}
			if(check){
				document.getElementById("crmAccountLimitOrgRole").submit();
			}else{
				if(confirm("調整後的限制數量小于原來的限制數量，這樣可能導致有些數據會超出調整的限制數量，您是否確認要更新？")){
					document.getElementById("crmAccountLimitOrgRole").submit();
				}else{
					return false;
				}
			}
		}
		document.getElementById("crmAccountLimitOrgRole").submit();
	}
}
// 批量頁面初始化設置
function init(){
	if(getRadioValue("batchAction")=='0'){
		$("limitNum").disabled=true;
	}else{
		$("limitNum").disabled=false;
	}
}
// ajax獲取當前角色、部門的系統管理員設定的限制（銷售客戶數量限制管理）
function getLimitNumAdmin(id,text){
	//alert(id + "--" + text);
	if(id!=''){
		ajaxRequest("/accountLimitUser.do?method=getLimitNumAdmin", "userId="+id, limitNumAdminResult);
	}
}
function limitNumAdminResult(result){
	if(result && result[0]) {
		result = result[0];
		if(result.limitNumAdmin=='-1'){
			$("limitNum").innerHTML = '無限量';
		}else{
			$("limitNum").innerHTML = result.limitNumAdmin;
		}
		$("limitNumAdmin").value = result.limitNumAdmin;
	}
}
// ajax獲取當前角色、部門的系統管理員設定的限制（部門/角色客戶數量限制管理）
function getLimitNum(departmentId,text){
	  //alert(departmentId+"--"+$("roleId").value);
	  if(departmentId!=''){
		  $("tmpDepId").value = departmentId;
	  }else{
		  departmentId = $("tmpDepId").value;
	  }
	  // alert(departmentId+"--"+$("roleId").value);
	  if(departmentId!='' && $("roleId").value!=''){
		  ajaxRequest("/accountLimitOrg.do?method=getLimitNumAdmin", "roleId="+$("roleId").value+"&departmentId="+departmentId, limitNumResult);
	  }
}
function limitNumResult(result){
	if(result && result[0]) {
		result = result[0];
		if(result.limitNumAdmin=='-1'){
			$("limitNum").innerHTML = '無限量';
		}else{
			$("limitNum").innerHTML = result.limitNumAdmin;
		}
		$("limitNumAdmin").value = result.limitNumAdmin;
	}
}
function doSubmitUser() {
	if(getRadioValue("batchAction")=='0'){
		if(confirm("您是否確認要刪除以上所有銷售的客戶數量限制？")){
			document.getElementById("crmAccountLimitUser").submit();
	    }
	}else{
		document.getElementById("crmAccountLimitUser").submit();
	}
}
function doSubmitAdmin(){
	//alert($("limitNumAdmin").value+"--"+$("limitNum").value);
	if(parseInt($("limitNum").value) < parseInt($("limitNumAdmin").value) && $("limitNum").value!="-1"){
		if(confirm("調整後的限制數量小于原來的限制數量，這樣可能導致有些數據會超出調整的限制數量，您是否確認要更新？")){
			document.getElementById("crmAccountLimitOrgRole").submit();
	    }
	}else if($("limitNumAdmin").value=="無限量" && $("limitNum").value!="-1"){
		if(confirm("調整後的限制數量小于原來的限制數量，這樣可能導致有些數據會超出調整的限制數量，您是否確認要更新？")){
			document.getElementById("crmAccountLimitOrgRole").submit();
	    }
	}else{
		document.getElementById("crmAccountLimitOrgRole").submit();
	}
}