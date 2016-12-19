
document.writeln('<script type="text/javascript" src="/extjs/ext-base.js"></script>');
document.writeln('<script type="text/javascript" src="/extjs/ajax_validator.js"></script>');

//變量聲明
var importAttachmentUrl;

/**
 * Ajax調用，check被變更合同下是否有附件
 * @return
 */
function popupImportAttachment(relatedContractId) {	
	ajaxRequest("/contractAttachment.do?method=checkContractAttachment", "&contractId=" + relatedContractId, callBack_importAttachment);	
}

/**
 * 回調函數，彈出新窗口進行導入合同附件選擇，彈出前check被變更合同下是否有附件
 * @return
 */
function callBack_importAttachment(result) {
    var returnValue = eval(result);
    var isExists = returnValue[0]['isExists'];	
	if (isExists == 'true') {
		parent.popDWindow('導入被變更合同附件 ', importAttachmentUrl, 600, 350);		
	} else {
		alert("該補充合同沒有附件可導入!");
	}
}

/**
 * 添加"導入附件"功能鏈接
 * @return
 */
function importAttachment() {
	var objTypes = document.getElementsByName("relatedContractType");
	if (objTypes == null || objTypes.length == 0) {
		return;
	}
	
	var relatedContractType = objTypes[0].value;	
	//補充合同
	if (relatedContractType == '3') {
		var contractId = document.getElementsByName("contractId")[0].value;
		var relatedContractId = document.getElementsByName("relatedContractId")[0].value;
		var oper = document.getElementById("id_td__oper_");
		importAttachmentUrl = "uitoolList.ui?funcID=1854&contractId=" + contractId + "&relatedContractId=" + relatedContractId;
		var href = "&nbsp;<a href=\"javascript:popupImportAttachment('" + relatedContractId + "');\">導入附件</a>";
		oper.innerHTML += href;
	}
}

UITool.AddEventHandler(window, "load", importAttachment);