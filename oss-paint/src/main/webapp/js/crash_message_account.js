function showListName1() {
    var valMap = {
        967: "<a href='uitoolList.ui?funcID=967&charlength=1000&SEARCH_DATA=9999' id='myCrashHisTitle' style='color:red'>�ڪ��Ȥᬡ�ʾ��v</a>",
        969: "<a href='uitoolList.ui?funcID=969&charlength=1000&SEARCH_DATA=9999' id='myCrashHisTitle' style='color:red'>���իȤᬡ�ʾ��v</a>",
        970: "<a href='uitoolList.ui?funcID=970&charlength=1000&SEARCH_DATA=9999' id='myCrashHisTitle' style='color:red'>�Ҧ��Ȥᬡ�ʾ��v</a>"
    };
    var str;
    str = valMap[document.getElementsByName("funcID")[0].value];
    document.getElementById('id_list_title').innerHTML += str;
    if (document.getElementsByName("SEARCH_DATA")[0]) {
        document.title = document.getElementById('myCrashHisTitle').innerHTML;
        document.getElementById('id_list_title').innerHTML = document.getElementById('myCrashHisTitle').innerHTML;
    }
}

UITool.AddEventHandler(window, "load", showListName1);


