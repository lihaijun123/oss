function showListName1() {
    var valMap = {
        0: "�ڪ��Ȥ�C��",
        2: "�ڪ��W���Ȥ�",
        3: "�ڪ�MIC�|���Ȥ�",
        4: "�ڪ��~���Ȥ�",
        5: "�ڪ��߽L�Ȥ�"
    };
    var str;
    if (!document.getElementsByName("ORIGIN_TYPE")[0]) {
        str = valMap["0"];
    }
    else {
        str = valMap[document.getElementsByName("ORIGIN_TYPE")[0].value];
    }
    document.getElementById('id_list_title').innerHTML = str;
}

UITool.AddEventHandler(window, "load", showListName1);


