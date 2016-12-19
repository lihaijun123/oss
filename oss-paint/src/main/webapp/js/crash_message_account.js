function showListName1() {
    var valMap = {
        967: "<a href='uitoolList.ui?funcID=967&charlength=1000&SEARCH_DATA=9999' id='myCrashHisTitle' style='color:red'>我的客戶活動歷史</a>",
        969: "<a href='uitoolList.ui?funcID=969&charlength=1000&SEARCH_DATA=9999' id='myCrashHisTitle' style='color:red'>本組客戶活動歷史</a>",
        970: "<a href='uitoolList.ui?funcID=970&charlength=1000&SEARCH_DATA=9999' id='myCrashHisTitle' style='color:red'>所有客戶活動歷史</a>"
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


