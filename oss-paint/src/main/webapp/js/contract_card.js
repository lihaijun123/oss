

function checkWritable() {
	var cardType=document.getElementById("cardType");
	var cardNo=document.getElementById("cardNo");
	var cardUrl=document.getElementById("cardAttachmentId");
	if (cardType.value != "0") {
		cardNo.disabled = false;
		cardUrl.disabled = false;
		cardNo.focus();
	} else {
		cardNo.disabled = true;
		cardUrl.disabled = true;
	}
}
function doCheck() {
	var cardType=document.getElementById("cardType");
	var cardNo=document.getElementById("cardNo");
	var cardUrl=document.getElementById("cardAttachmentId");
	//var containChar = /^(([0-9]+[a-zA-Z]*)|([a-zA-Z]*[0-9]+))+/ig;
	if (cardType.value != "0") {
		if (cardNo.value == null || trim(cardNo.value) == "") {
			alert("�ҥ�s��������g�I");
			cardNo.focus();
			return false;
		}
		if (cardUrl.value == null || trim(cardUrl.value) == "") {
			alert("�ҥ���󤣯ର�šI");
			cardUrl.focus();
			return false;
		}
		/*if (!cardNo.value.match(containChar)) {
			alert("�ҥ�s���u��t���Ʀr�M�r��(�����t���Ʀr)�I");
			cardNo.focus();
			return false;
		}*/
	}
	document.getElementById("crmContract").submit();
}

function initUrl(objName, defValue, contractId) {
	var relatedId = contractId;
	if (relatedId == undefined || trim(relatedId) == "") {
		relatedId = "0";
	}
	doCall(objName, defValue, "/slave.do?method=getSlaveInfo&slaveType=1&attachmentRelatedId=" + relatedId);
}
