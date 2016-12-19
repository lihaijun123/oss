/**
 * 
 */
function initIframeHeight() {
    parent.initIframeHeightType();
}

/**
 * �X�P���A�������X�P(�Ϊ̧@�o�X�P)�A������������ɧ�_�l�󪺪����P��N���i��
 * ����H���U����ܪ���W�١A����
 * �������ק�B���W�Ǫ��󡨥\��
 * �P��N��:
 * �����q�]��ƳB�^���žP��N��]100800�^�B�����q�]��ƳB�^�P��N��]�����^�]100500�^�B�����q�]��ƳB�^�P��N��]10010�^
 */
function hiddenAttachmentInfo() {	
	var table = document.getElementById("list00_table");
	var rows = table.rows;
	for (var i = 1 ; i < rows.length; i++){		
		var infoNode = rows[i].cells[4];
		var operNode = rows[i].cells[6];	
		var flag = rows[i].getAttribute("FLAG");
		if (flag == '0') {
			infoNode.innerHTML = "";
			operNode.innerHTML = "";
		}
	}
}

UITool.AddEventHandler(window, "load", initIframeHeight);
UITool.AddEventHandler(window, "load", hiddenAttachmentInfo);
