/**
 * �ק�W�٬�����q��Ρ�
 */
function modifyDisplayName() {
    var btn = document.getElementsByName("selbtn")[0];
    btn.value = " ��q��� ";
}

UITool.AddEventHandler(window, "load", modifyDisplayName);
