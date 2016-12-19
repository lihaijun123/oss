
function addModifyContract(){
	var contractId=document.getElementById("contractId").value;
	var oper=document.getElementById("id_td__oper_");
	oper.innerHTML+='&nbsp;&nbsp;<a href="javascript:S.opWin.open({src:\'contractProduct.do?method=addAlter&contractId='+ contractId +'\',title:\'增加變更產品\',winid:\'op_win\'})">添加變更產品</a>';
}
UITool.AddEventHandler(window,"load",addModifyContract);