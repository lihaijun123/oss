//�����Үج[�ǭ�
function validateFields(obj) {
 	validateField(obj,obj,"validateMethod,writtenContractIdMin,writtenContractIdMax");
}
//�����Үج[�ǭ�
function validateFields2(obj) {
 	validateField(obj,obj,"validateMethod");
}
//�p�妸���������X�P�w�g�����A�h�t�ε��X�G������
function checkBatchIncept(){
	if($("batchId").value==''){
		alert("�п�J�妸");
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
			if (confirm("��"+$("batchId").value+"�媺�s�����J"+$("writtenContractIds").value + "�w�����A�T�w�~�򱵦��H")) {
				document.getElementById("crmContractWrittenInfo").submit();
			} else {
				return false;
			}
		}
		document.getElementById("crmContractWrittenInfo").submit();
	 }
}
//��l�ƭקﭶ�� 
function initModify(){
	setContractStatus();//�X�P���A�ק��v��
	setContractAllotStatus();//�X�P���t���A�ק��v��
}
//�p�ѭ��X�P�s�����d��϶����A���w�g�Q���t���X�P�A�t�μu�X�����A�ХΤ�T�{���s��ܽd��
function checkBatchAllot(){
	if($("writtenContractIdMin").value=='' || $("writtenContractIdMax").value==''){
		alert("�п�J�X�P�s���d��");
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
			if (confirm("�s�����J"+$("writtenContractIds").value + "���ƾڤw�Q���t�A�T�w�~����t�H")) {
				document.getElementById("crmContractWrittenInfo").submit();
			} else {
				return false;
			}
		}
		document.getElementById("crmContractWrittenInfo").submit();
	 }
}
/**
 * �ˬd�X�P�k�ݤH�W�U���ťծѭ��X�P�ƶq
 */
function checkUsed(){
  var userId;
  var obj = $("hid_u_owner");
  if(!obj||obj.value==""){
    alert("�Х���ܦX�P�k�ݡI");
    return false;
  }
  else{
    userId = obj.value;
    ajaxRequest("/contractWritten.do?method=checkUsed", "userId="+userId, checkResult);
    return false;
  }
}
/**
 * �^�ը�ơA�ˬd�X�P�k�ݤH�W�U���ťծѭ��X�P�ƶq����m�ʧ@
 * @param {Object} result
 */
function checkResult(result){
  var objs = result;
  var flag = true;
  if(objs[0].count>3){
    if(!window.confirm("��e�P��W�U���ťծѭ��X�P�w�g�W�L3���A�T�w���t�H")){
      flag = false;
    }
  }
  if(flag){
    var form = $("wConForm");
    form.submit();
  }
}
/**
 * �R������
 * 
 * @param {Object} id
 */
function delConfirm(id){
  if(window.confirm("�T�w�n�R���Ӫ���H")){
    var url = location.href;
    location.href="/attachment.do?method=delete&attachmentId="+id+"&url="+escape(url);
  }
}