/**
 * ��ܮѭ��X�P
 */
function chooseWrittenCon() {
    var accountId = $("accountId").value;
    if (accountId == "") {
        alert("�Х���ܫȤ�I");
        return;
    }
    var reletedType = $("relatedContractType").value;
    var contractId = -100;
    if ($("contractId")) {
        contractId = $("contractId").value;
    }
    var parentContractId = -100;
    if (reletedType == "3") {
        parentContractId = $("parentContract").value;
    }
    //alert(accountId + " : " + contractId);
    window.open('/uitoolList.ui?funcID=1674&multiple=1&accountId=' + accountId + '&contractId=' + contractId + "&parentContractId=" + parentContractId);
}

/**
 * �^�g�ѭ��X�P�H��
 * @param {Object} v
 */
function getSelValue(v) {
    if (v == "") {
        return;
    }
    var wConId = $("writtenContractId").value;
    if (wConId != "") {
        wConId += ",";
    }
    $("writtenContractId").value = wConId + v;
}


/**
 * �h������Ů�A�M����������r�����b���r���A�A�⤤���h�ӥ��b���Ů�h��
 *
 * @param obj
 * @return
 */
function trimSpace(obj) {
    //obj.value = trimSpaceValue(obj.value);
    str = obj.value;
    // ���h��������b���Ů�
    str = str.replace(/(^[\s|�@]*)|([\s|�@]*$)/g, "");
    // ���N����"�A"������","
    str = str.replace(/["�A"]+/g, ",");
    // �A�⤤���h�ӥ��b���Ů�h��
    str = str.replace(/[\s|�@]+/g, "");
    // �h���r���e��Ů�
    reg = new RegExp('( )*,( )*', 'gm');
    obj.value = str.replace(reg, ',');
}
