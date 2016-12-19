/**
 * 修改名稱為“批量領用”
 */
function modifyDisplayName() {
    var btn = document.getElementsByName("selbtn")[0];
    btn.value = " 批量領用 ";
}

UITool.AddEventHandler(window, "load", modifyDisplayName);
