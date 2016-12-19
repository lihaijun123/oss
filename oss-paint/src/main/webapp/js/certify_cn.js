/**
 * ��l�Ʀa�����
 */
function init() {
    $("comCountry").value = "China";
    $("contry_list").style.display = '';
    $("china_zone").style.display = 'block';
}

/**
 *
 * @param {Object} value
 */
function changeRadio(value) {
    var id_div_yearCheckRec = $("id_div_yearCheckRec");
    if (value == '0') {
        id_div_yearCheckRec.disabled = true;
        $("hidYearCheck").value = "0000";
    }
    else {
        id_div_yearCheckRec.disabled = false;
        $("hidYearCheck").value = null;
    }
}

/**
 *
 * @param {Object} obj
 */
function check(obj) {
    if (obj.value == "China") {
        $("contry_list").style.display = '';
        $("china_zone").style.display = 'block';
    }
    else {
        $("contry_list").style.display = '';
        $("china_zone").style.display = 'none';
        $("comProvince").value = " ";
        $("comCity").value = " ";
        $("address").value = "";
    }
}

/**
 * ������q�H��
 * @param {Object} v
 */
function getComInfo(comId) {
    // alert(comId);
    var val = checkComInfo(comId);
    if (val == -1) {
        return;
    }
    ajaxRequest("/certifyCn.do?method=getComInfo", "comId=" + val, convertComResult);
}

/**
 * �B�z��^���G
 * @param {Object} result
 */
function convertComResult(result) {
    if (result && result[0]) {
        result = result[0];
        if (result.errMsg) {
            alert(result.errMsg);
            return;
        }
        if (result.msg) {
            alert(result.msg);
        }
        // alert(result.comName + " : " + result.comStatus + " : " + result.csLevel);
        setValue($("comInfo.comName"), result.comName);
        //
        setValue($("comStatusSel"), result.comStatus);
        setValue($("comStatusHid"), result.comStatus);
        //
        setValue($("csLevelSel"), result.csLevel);
        setValue($("csLevelHid"), result.csLevel);
    }
}


/**
 * ���}�d�ߤ��q�H�������f
 * @param {Object} comId
 * @param {Object} lanCode
 */
function openComInfo(comId) {
    //
    var val = checkComInfo(comId);
    if (val == -1) {
        return;
    }
    window.open('/edt/companychk.do?action=modify&comId=' + val + '&lan=1');
}

/**
 * �ˬd���qID
 * @param {Object} comId
 */
function checkComInfo(comId) {
    var val = trim(comId);
    if (val == "") {
        alert("�Х���g���qID�I");
        return -1;
    }
    if (isNaN(val)) {
        alert("���qID���O�Ʀr�I");
        return -1;
    }
    return val;
}

/**
 * �C�X���q�������ҥ�H���C��
 * @param {Object} comId
 */
function importCertify(comId) {
    if (checkComInfo(comId) != -1) {
        //
        window.open("/uitoolList.ui?funcID=2110&COMID=" + comId);
    }
}

/**
 * ��ܤ��q��W�H����^���G
 * @param {Object} recId
 */
function selObj(recId) {
    //alert(recId);
    ajaxRequest("/certifyCn.do?method=getLicenceInfo", "&recId=" + recId, convertResult);
}

/**
 * �^�ը�ơA�B�z��
 * @param {Object} result
 */
function convertResult(result) {
    if (result && result[0]) {
        obj = result[0];
        if (obj.errMsg) {
            alert(obj.errMsg);
            return;
        }
        //
        setValue($("licenseNo"), obj.licenseNo);
        setValue($("comName"), obj.comName);
        setValue($("comCountry"), obj.comCountry);
        setValue($("comProvince"), obj.comProvince);
        getNext(obj.comProvince, obj.comCity, 'CITY_LIST', 'comCity');
        setValue($("address"), obj.address || "");
        setValue($("legalRepresentative"), obj.legalRepresentative);
        setValue($("comType"), obj.comType);
        setValue($("registeredCapital"), obj.registeredCapital || "");
        setValue($("capitalUnit"), obj.capitalUnit);
        setValue($("capitalType"), obj.capitalType);
        setValue($("startTime"), obj.startTime);
        setValue($("endTime"), obj.endTime);
        //
        removeValue($("businessScope", name));
        var business = ","+obj.businessScope+",";
        if(business.indexOf(',1600000000,')>=0){
          alert("�^�媩���q��q�l(1600000000)����~�������줤�媩�A�Э��s��ܡI");
        }
        //
        business = business.replace(',1600000000,',',');
        business = business.substring(1,business.length-1);
        setValue($("businessScope", name), business);
        //
        setValue($("establishmentTime"), obj.establishmentTime);
        //
        if (obj.annualRecords == '0000') {
            removeValue($("annualRecords", name));
            $("yearcheck_0").click();
        }
        else {
            $("yearcheck_1").click();
            //
            var v = $("annualRecords", name);
            var max = -1;
            for (var i = 0; i < v.length; i++) {
                if (v[i].checked == true && v[i].value > max) {
                    max = v[i].value;
                }
            }
            var tmp = obj.annualRecords.split(",");
            for(i = 0; i < tmp.length; i++){
                if(tmp[i]>max){
                  max = tmp[i];
                }
            }
            //
            removeValue($("annualRecords", name));
            setValue($("annualRecords", name), max);
        }
    }
}

//
var fIndex = 4;
/**
 *
 */
function addFile() {
    //alert(fIndex);
    var tab = $("tabFile");
    var row = tab.insertRow(-1);
    var cell = row.insertCell(-1);
    cell.innerHTML = "<input type='file' name='file3_" + fIndex + "'>";
    //
    readonlyFileInput("file3_" + fIndex);
    fIndex++;
}

/**
 * �����J�إuŪ
 */
function fileReadOnly() {
    var objs = document.getElementsByTagName("input");
    for (var i in objs) {
        // alert(i);
        if (objs[i].type == 'file') {
            readonlyFileInput(objs[i].name);
        }
    }
}
