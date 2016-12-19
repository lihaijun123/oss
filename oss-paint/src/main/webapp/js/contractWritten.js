//為驗證框架傳值
function validateFields(obj) {
 	validateField(obj,obj,"validateMethod,writtenContractIdMin,writtenContractIdMax");
}
//為驗證框架傳值
function validateFields2(obj) {
 	validateField(obj,obj,"validateMethod");
}
//如批次內有部分合同已經接收，則系統給出二次提醒
function checkBatchIncept(){
	if($("batchId").value==''){
		alert("請輸入批次");
	}else{
		checkIncept();
	}
}
function checkIncept(){
	ajaxRequest("/contractWritten.do?method=checkBatchIncept", "batchId="+$("batchId").value, checkInceptResult);
}
function checkInceptResult(result){
	 if(result && result[0]) {
		result = result[0];
		$("writtenContractIds").value = result.writtenContractIds;
		if($("writtenContractIds").value !=''){
			if (confirm("第"+$("batchId").value+"批的編號為︰"+$("writtenContractIds").value + "已接收，確定繼續接收？")) {
				document.getElementById("crmContractWrittenInfo").submit();
			} else {
				return false;
			}
		}
		document.getElementById("crmContractWrittenInfo").submit();
	 }
}
//初始化修改頁面 
function initModify(){
	setContractStatus();//合同狀態修改權限
	setContractAllotStatus();//合同分配狀態修改權限
}
//如書面合同編號的範圍區間內，有已經被分配的合同，系統彈出提醒，請用戶確認重新選擇範圍
function checkBatchAllot(){
	if($("writtenContractIdMin").value=='' || $("writtenContractIdMax").value==''){
		alert("請輸入合同編號範圍");
	}else{
		checkAllot();
	}
}
function checkAllot(){
	ajaxRequest("/contractWritten.do?method=checkBatchAllot", "min="+$("writtenContractIdMin").value+"&max="+$("writtenContractIdMax").value, checkAllotResult);
}
function checkAllotResult(result){
	if(result && result[0]) {
		result = result[0];
		$("writtenContractIds").value = result.writtenContractIds;
		if($("writtenContractIds").value !=''){
			if (confirm("編號為︰"+$("writtenContractIds").value + "的數據已被分配，確定繼續分配？")) {
				document.getElementById("crmContractWrittenInfo").submit();
			} else {
				return false;
			}
		}
		document.getElementById("crmContractWrittenInfo").submit();
	 }
}
/**
 * 檢查合同歸屬人名下的空白書面合同數量
 */
function checkUsed(){
  var userId;
  var obj = $("hid_u_owner");
  if(!obj||obj.value==""){
    alert("請先選擇合同歸屬！");
    return false;
  }
  else{
    userId = obj.value;
    ajaxRequest("/contractWritten.do?method=checkUsed", "userId="+userId, checkResult);
    return false;
  }
}
/**
 * 回調函數，檢查合同歸屬人名下的空白書面合同數量的後置動作
 * @param {Object} result
 */
function checkResult(result){
  var objs = result;
  var flag = true;
  if(objs[0].count>3){
    if(!window.confirm("當前銷售名下的空白書面合同已經超過3份，確定分配？")){
      flag = false;
    }
  }
  if(flag){
    var form = $("wConForm");
    form.submit();
  }
}
/**
 * 刪除附件
 * 
 * @param {Object} id
 */
function delConfirm(id){
  if(window.confirm("確定要刪除該附件？")){
    var url = location.href;
    location.href="/attachment.do?method=delete&attachmentId="+id+"&url="+escape(url);
  }
}