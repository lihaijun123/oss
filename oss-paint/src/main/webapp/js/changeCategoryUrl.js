/**
 * 根據語言版本值替換目錄鏈接中語言版本
 * @param {Object} url
 */
function replaceCatalogWindowUrl(url) {
    var obj = $('lanCode', 'name');
    var lanCode = getHtmlEleValue(obj);
    if (lanCode != '0' && lanCode != '1') {
        lanCode = '0';
    }
    if (url.indexOf("&lanCode") > 0) {
        return url.replace(/&lanCode=\d&/g, "&lanCode=" + lanCode + "&");
    }
    return url;
}

/**
 * 獲取元素的值
 * @param {Object} ele 數組︰document.getElementByNames();
 */
function getHtmlEleValue(ele) {
    if (typeof(ele) == 'undefined' || ele.length == 0 || ele[0] == null) {
        return "";
    }
    var obj = ele[0];
    if ('text' == obj.type || 'hidden' == obj.type || 'textarea' == obj.type) {
        return obj.value;
    }
    else if ('select-one' == obj.type) {
        return obj.options[obj.selectedIndex].value;
    }
    else if ('select-multiple' == obj.type) {
        var retArr = new Array();
        for (var i = 0; i < obj.options.length; i++) {
            if (obj.options[i].selected) {
                retArr.push(obj.options[i].value);
            }
        }
        if (retArr.length <= 0) {
            return "";
        }
        else if (retArr.length == 1) {
            return retArr[0];
        }
        return retArr;
    }
    else if ('radio' == obj.type) {
        var ret = "";
        for (var i = 0; i < ele.length; i++) {
            if (ele[i].checked) {
                ret = ele[i].value;
                break;
            }
        }
        return ret;
    }
    else if ('checkbox' == obj.type) {
        var retArr = new Array();
        for (var i = 0; i < ele.length; i++) {
            if (ele[i].checked) {
                retArr.push(ele[i].value);
            }
        }
        if (retArr.length <= 0) {
            return "";
        }
        else if (retArr.length == 1) {
            return retArr[0];
        }
        return retArr;
    }
    else {
        return obj.value;
    }
}

/**
 * 去除目錄內容
 */
function clearCat() {
    for (var i = 0; i < arguments.length; i++) {
        //
        $(arguments[i]).value = '';
        $('divViewCatName_' + arguments[i]).innerHTML = '';
    }
}
