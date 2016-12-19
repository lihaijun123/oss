/**
 * 選擇書面合同
 */
function chooseWrittenCon() {
    var accountId = $("accountId").value;
    if (accountId == "") {
        alert("請先選擇客戶！");
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
 * 回寫書面合同信息
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
 * 去除首位空格，然後替換全角逗號為半角逗號，再把中間多個全半角空格去掉
 *
 * @param obj
 * @return
 */
function trimSpace(obj) {
    //obj.value = trimSpaceValue(obj.value);
    str = obj.value;
    // 先去除首位全半角空格
    str = str.replace(/(^[\s|　]*)|([\s|　]*$)/g, "");
    // 先將全角"，"替換成","
    str = str.replace(/["，"]+/g, ",");
    // 再把中間多個全半角空格去掉
    str = str.replace(/[\s|　]+/g, "");
    // 去除逗號前後空格
    reg = new RegExp('( )*,( )*', 'gm');
    obj.value = str.replace(reg, ',');
}
