 
document.writeln('<script src="/extjs/ajax_validator.js" type="text/javascript"></script>');
document.writeln('<script type="text/javascript" src="/extjs/ext-base.js"></script>');
function diaplayRelet() {
    var aaa = '  <input type="button" name="downBtn" title="�O�s��e����U���Ҧ��ƾڨ�Excel���" value="�U��" onclick="download(\'down-xls\')"> ' +
    '<input type="button" name="process" value="�p�ⴣ��" onclick="processTC();"> ';
    
    if (document.getElementsByName("searchBtn")[0]) {
    
        var span = document.createElement('SPAN');
        span.innerHTML = aaa;
        document.getElementsByName("searchBtn")[0].parentNode.appendChild(span);
        
    }
}

function download(dtype) {
    var url = document.location.href;
    if (url.indexOf("&_i_f_k_=true") > 0) {
        alert("�d�ߤ���A�ɥXExcel���I");
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
                alert("�έp�������j�_��e����I");
                return;
            }
            if (currDate != factDate.value) {
                if (confirm("�έp��������_��e��A�O�_�ݭn�p�ⴣ���H")) {
                    AjaxValidate('/saleTc.do?method=processTC', '&searchDate=' + factDate.value, explainResult);
                }
            }
            else {
                AjaxValidate('/saleTc.do?method=processTC', '&searchDate=' + factDate.value, explainResult);
            }
        }
        else {
            alert("�п�J���T�έp����榡�I");
        }
    }
    else {
        alert("�п�J�έp����A�i�洣���p��I");
    }
}

function explainResult(result) {
    if (result[0].result == "" || result[0] == null) {
        alert('�P�ⴣ���p�⥢�ѡI');
    }
    else {
        alert(result[0].result);
    }
}


UITool.AddEventHandler(window, "load", diaplayRelet);

