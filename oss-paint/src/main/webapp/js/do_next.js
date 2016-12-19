/**
 * @author gaoying
 */
function doNext1() {
    var sel_id = "";
    var count = 0;
    var selIds = document.getElementsByName("sel_id");
    for (var i = 0; i < selIds.length; i++) {
        if (selIds[i].checked) {
            sel_id = selIds[i].value;
            count++;
        }
    }
    if (count > 1) 
        alert("只能選擇一個目錄！");
    else {
        if (sel_id.length > 0) {
            if (window.opener.document.getElementById("issue") != null) {
                window.opener.document.getElementById("issue").value = sel_id;
            }
            else if (window.opener.document.getElementById("content")) {
                window.opener.document.getElementById("content").value = sel_id;
            }
            else if (window.opener.document.getElementById("industry")) {
                window.opener.document.getElementById("industry").value = sel_id;
            }
            else{
                window.opener.selObj(sel_id);
            }
            window.close();
        }
        else {
            alert("請先在多選框中選擇!");
        }
    }
}

function disPlay() {
    var sel_id = "";
    var selIds = document.getElementsByName("sel_id");
    var level = window.opener.document.getElementById("unLevelType").value;
    var rows = document.getElementById("list00_table").rows;
    if (level != 4) {
        for (var i = 1; i < rows.length; i++) {
            var cells = rows[i].cells;
            if (cells[cells.length - 1].innerHTML != level) {
                selIds[i - 1].disabled = true;
            }
        }
    }
    for (var i = 1; i < rows.length; i++) {
        var cells = rows[i].cells;
        if (cells[cells.length - 1].innerHTML == 3) {
            var reg = /<a\b[^>]*>([^<]*)<\/a>/gi;
            var s = cells[2].innerHTML;
            cells[2].innerHTML = s.replace(reg, "$1");
        }
    }
}

UITool.AddEventHandler(window, "load", aaa);
UITool.AddEventHandler(window, "load", disPlay);
function aaa() {
    var btn = document.getElementsByName("selbtn")[0];
    btn.value = " O K ";
    btn.onclick = doNext1;
}
