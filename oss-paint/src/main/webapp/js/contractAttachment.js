
document.writeln('<script type="text/javascript" src="/extjs/ext-base.js"></script>');
document.writeln('<script type="text/javascript" src="/extjs/ajax_validator.js"></script>');

//�ܶq�n��
var importAttachmentUrl;

/**
 * Ajax�եΡAcheck�Q�ܧ�X�P�U�O�_������
 * @return
 */
function popupImportAttachment(relatedContractId) {	
	ajaxRequest("/contractAttachment.do?method=checkContractAttachment", "&contractId=" + relatedContractId, callBack_importAttachment);	
}

/**
 * �^�ը�ơA�u�X�s���f�i��ɤJ�X�P�����ܡA�u�X�echeck�Q�ܧ�X�P�U�O�_������
 * @return
 */
function callBack_importAttachment(result) {
    var returnValue = eval(result);
    var isExists = returnValue[0]['isExists'];	
	if (isExists == 'true') {
		parent.popDWindow('�ɤJ�Q�ܧ�X�P���� ', importAttachmentUrl, 600, 350);		
	} else {
		alert("�ӸɥR�X�P�S������i�ɤJ!");
	}
}

/**
 * �K�["�ɤJ����"�\���챵
 * @return
 */
function importAttachment() {
	var objTypes = document.getElementsByName("relatedContractType");
	if (objTypes == null || objTypes.length == 0) {
		return;
	}
	
	var relatedContractType = objTypes[0].value;	
	//�ɥR�X�P
	if (relatedContractType == '3') {
		var contractId = document.getElementsByName("contractId")[0].value;
		var relatedContractId = document.getElementsByName("relatedContractId")[0].value;
		var oper = document.getElementById("id_td__oper_");
		importAttachmentUrl = "uitoolList.ui?funcID=1854&contractId=" + contractId + "&relatedContractId=" + relatedContractId;
		var href = "&nbsp;<a href=\"javascript:popupImportAttachment('" + relatedContractId + "');\">�ɤJ����</a>";
		oper.innerHTML += href;
	}
}

UITool.AddEventHandler(window, "load", importAttachment);