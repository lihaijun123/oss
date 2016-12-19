//
function selQP() {
    var btn = document.getElementsByName("selbtn")[0];
    btn.onclick = doNext1;
}

function doNext1() {
    var arr = document.getElementsByName("sel_id");
    var v = "";
    for (var i = 0; i < arr.length; i++) {
        if (arr[i].checked == true) {
            var checkValue = arr[i].value.toLowerCase();
            if (v.length == 0) {
                v += checkValue;
            }
            else {
                v += "," + checkValue;
            }
        }
    }
    opener.selectQP(v);
    window.close();
}

function show() {
    var tr = document.getElementById("id_tr_0");
    var cells = tr.cells;
    if (cells[0].innerHTML == "") {
        cells[0].innerHTML = " ¿ï¾Ü ";
    }
}

UITool.AddEventHandler(window, "load", selQP);

UITool.AddEventHandler(window, "load", show);

function lower(){
  var objs = document.getElementsByName("ACTION_CONTENT2");
  for(var i = 0 ; i < objs.length ; i++){
    objs[i].value = objs[i].value.toLowerCase();
  }
}
