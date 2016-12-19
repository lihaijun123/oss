//��l�ܤ��q��~������ܶ�
function initComLicence() {
    $("licenceGenderTR").style.display = "none";
    $("licenceFolkTR").style.display = "none";
    $("addressTR").style.display = "";
    $("comCorporationNameTR").style.display = "";
    $("regMoneyTR").style.display = "";
    $("corporationTypeTR").style.display = "";
    $("manageRangeTR").style.display = "";
    $("timeTR").style.display = "";
    $("createdDateTR").style.display = "";
    $("yearCheckRecTR").style.display = "";
    $("remarkTR").style.display = "";
    if ($("importBtn")) {
      $("importBtn").style.display = "";
    }
}

//��l�ƭӤH��������ܶ�
function initPerLicence() {
    $("licenceGenderTR").style.display = "";
    $("licenceFolkTR").style.display = "";
    $("addressTR").style.display = "";
    $("comCorporationNameTR").style.display = "none";
    $("regMoneyTR").style.display = "none";
    $("corporationTypeTR").style.display = "none";
    $("manageRangeTR").style.display = "none";
    $("timeTR").style.display = "none";
    $("createdDateTR").style.display = "none";
    $("yearCheckRecTR").style.display = "none";
    $("remarkTR").style.display = "";
    if ($("importBtn")) {
      $("importBtn").style.display = "none";
    }
}

//��l�ƨ�L��ܶ�
function initOther() {
    $("licenceGenderTR").style.display = "none";
    $("licenceFolkTR").style.display = "none";
    $("addressTR").style.display = "none";
    $("comCorporationNameTR").style.display = "none";
    $("regMoneyTR").style.display = "none";
    $("corporationTypeTR").style.display = "none";
    $("manageRangeTR").style.display = "none";
    $("timeTR").style.display = "none";
    $("createdDateTR").style.display = "none";
    $("yearCheckRecTR").style.display = "none";
    $("remarkTR").style.display = "";
    if ($("importBtn")) {
      $("importBtn").style.display = "none";
    }
}

function setComLicenceValues() {

	var a = $("licenceGender");
	for(var i=0; i<a.length; i++){
		a[i].value = "";
	}
    var b = $("licenceFolk");
    for(var i=0;i<b.length;i++){
    	b[i].value="";
    }
}

function setPerLicenceValues() {
    $("comCorporationName").value = "";
    $("regMoney").value = "";
    $("corporationType").value = "";
    var items = document.getElementsByName("manageRange");
    for (var i = 0; i < items.length; i++) {
        items[i].value = "";
    }
    $("startTime").value = "";
    $("endTime").value = "";
    $("createdDate").value = "";
    items = document.getElementsByName("yearCheckRec");
    for (var i = 0; i < items.length; i++) {
        items[i].value = "";
    }
}

function setOtherValues() {
    $("licenceGender").value = "";
    $("licenceFolk").value = "";
    $("country").value = "";
    $("province").value = "";
    $("city").value = "";
    $("addrOther").value = "";
    $("comCorporationName").value = "";
    $("regMoney").value = "";
    $("corporationType").value = "";
    var items = document.getElementsByName("manageRange");
    for (var i = 0; i < items.length; i++) {
        items[i].value = "";
    }
    $("startTime").value = "";
    $("endTime").value = "";
    $("createdDate").value = "";
    items = document.getElementsByName("yearCheckRec");
    for (var i = 0; i < items.length; i++) {
        items[i].value = "";
    }
}

/**
 * �C�X�Ȥ�����|������W�H���C��
 * @param {Object} accountId
 */
function importCertify(accountId) {
    //
    window.open("/uitoolList.ui?funcID=2109&ACCOUNTID=" + accountId);
}

/**
 * ��ܤ��q��W�H����^���G
 *
 * @param {Object} comId
 */
function selObj(comId) {
    //alert(comId);
    ajaxRequest("/accountLicence.do?method=getCertifyInfo", "&comId=" + comId, convertResult);
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
        setValue($("licenceId"), obj.licenceId);
        setValue($("licenceName"), obj.licenceName);
        setValue($("country"), obj.country);
        setValue($("province"), obj.province);
        getNext(obj.province, obj.city, 'CITY_LIST', 'city');
        setValue($("addrOther"), obj.addrOther || "");
        setValue($("comCorporationName"), obj.comCorporationName);
        setValue($("corporationType"), obj.corporationType);
        setValue($("regMoney"), obj.regMoney || "");
        setValue($("regUnit"), obj.regUnit);
        setValue($("regMoneyUnit"), obj.regMoneyUnit);
        setValue($("startTime"), obj.startTime || "");
        setValue($("endTime"), obj.endTime || "");
        //
        removeValue($("manageRange", name));
        setValue($("manageRange", name), obj.manageRange);
        //
        setValue($("createdDate"), obj.createdDate);
        if (obj.yearCheckRec == '0000') {
            $("yearcheck_0").click();
        }
        else {
            $("yearcheck_1").click();
            //
            setValue($("yearCheckRec", name), obj.yearCheckRec);
        }
    }
}

