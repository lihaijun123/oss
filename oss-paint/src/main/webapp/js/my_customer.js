function showListName1() {
    var valMap = {
        0: "我的客戶列表",
        2: "我的名片客戶",
        3: "我的MIC會員客戶",
        4: "我的外部客戶",
        5: "我的詢盤客戶"
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


