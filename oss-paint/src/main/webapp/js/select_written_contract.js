/**
 * 改寫按鈕方法
 */
function selWrittenContract() {
    var btn = document.getElementsByName("selbtn")[0];
    btn.onclick = doNext1;
}
/**
 * 按鈕方法實現
 */
function doNext1() {
    var arr = document.getElementsByName("sel_id");
    var v = "";
    for (var i = 0; i < arr.length; i++) {
        if (arr[i].checked == true) {
            if (v.length == 0) {
                v += arr[i].value;
            }
            else {
                v += "," + arr[i].value;
            }
        }
    }
    opener.getSelValue(v);
    window.close();
}

function show() {
    var tr = document.getElementById("id_tr_0");
    var cells = tr.cells;
    if (cells[0].innerHTML == "") {
        cells[0].innerHTML = " 選擇 ";
    }
}

UITool.AddEventHandler(window, "load", selWrittenContract);

UITool.AddEventHandler(window, "load", show);
