 
document.writeln('<script src="/extjs/ajax_validator.js" type="text/javascript"></script>');
document.writeln('<script type="text/javascript" src="/extjs/ext-base.js"></script>');
function diaplayRelet() {
    var aaa = '  <input type="button" name="downBtn" title="保存當前條件下的所有數據到Excel文件" value="下載" onclick="download(\'down-xls\')"> ' +
    '<input type="button" name="process" value="計算提成" onclick="processTC();"> ';
    
    if (document.getElementsByName("searchBtn")[0]) {
    
        var span = document.createElement('SPAN');
        span.innerHTML = aaa;
        document.getElementsByName("searchBtn")[0].parentNode.appendChild(span);
        
    }
}

function download(dtype) {
    var url = document.location.href;
    if (url.indexOf("&_i_f_k_=true") > 0) {
        alert("查詢之後再導出Excel文件！");
        return;
    }
    
    url = url.replace('funcID=951138', 'funcID=1744');
    url = url.replace(/&nextStep=[\w]*/ig, '');
    url += '&nextStep=download&download=' + dtype;
    document.location = url;
}

function processTC() {
    var factDate = document.getElementsByName("FACT_DATE")[0];
    if (factDate.value != "") {
        var reg = /^\d{4}\-[0,1][1-9]$/
        if (reg.test(factDate.value)) {
            var d = new Date();
            var currDate = d.getFullYear() + '-' + ((d.getMonth() + 1) > 9 ? d.getMonth() + 1 : '0' + (d.getMonth() + 1));
            if (currDate < factDate.value) {
                alert("統計月份不能大于當前月份！");
                return;
            }
            if (currDate != factDate.value) {
                if (confirm("統計月份不等于當前月，是否需要計算提成？")) {
                    AjaxValidate('/saleTc.do?method=processTC', '&searchDate=' + factDate.value, explainResult);
                }
            }
            else {
                AjaxValidate('/saleTc.do?method=processTC', '&searchDate=' + factDate.value, explainResult);
            }
        }
        else {
            alert("請輸入正確統計月份格式！");
        }
    }
    else {
        alert("請輸入統計月份再進行提成計算！");
    }
}

function explainResult(result) {
    if (result[0].result == "" || result[0] == null) {
        alert('銷售提成計算失敗！');
    }
    else {
        alert(result[0].result);
    }
}


UITool.AddEventHandler(window, "load", diaplayRelet);

