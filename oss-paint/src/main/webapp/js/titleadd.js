
function addModifyContract(){
	var contractId=document.getElementById("contractId").value;
	var oper=document.getElementById("id_td__oper_");
	oper.innerHTML+='&nbsp;&nbsp;<a href="javascript:S.opWin.open({src:\'contractProduct.do?method=addAlter&contractId='+ contractId +'\',title:\'�W�[�ܧ󲣫~\',winid:\'op_win\'})">�K�[�ܧ󲣫~</a>';
}
UITool.AddEventHandler(window,"load",addModifyContract);