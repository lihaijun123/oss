function sendMail() {
    var btn = document.getElementsByName("selbtn")[0];
    btn.value = "發送底單郵件通知";
    btn.onclick = doNext1;
}

function doNext1() {
    document.nextForm.action = "contractAttachment.do?method=sendAffixMail";
    doNext();
}

function initIframeHeight() {
    parent.initIframeHeightManage();
}

UITool.AddEventHandler(window, "load", sendMail);

UITool.AddEventHandler(window, "load", initIframeHeight);
