 
/**
 * ��l�ƲĤ@�ӤU�Ԯ�
 * objName:�Ĥ@�ӤU�Ԯت�ID�W��
 * defValue:�ƾڦ^��ɥΤ_�q�{��ܪ���
 * @param {Object} objName
 * @param {Object} value
 * @param {Object} defValue
 * @param {Object} type
 */
function getNext(objName, value, defValue, type) {
    if (value == "3" && defValue == "" && !$("contactSubType.errors")) {
        defValue = "3_2";
    }
    doCall(objName, defValue, "/contactRecord.do?method=next&value=" + value + "&type=" + type);
}

/**
 *
 * @param {Object} v
 */
function displayAccompanier(v) {
    /*
    if ("3_4" == v) {
        $("trUser").style.display = "";
    }
    else {
        $("trUser").style.display = "none";
    }
    */
}
