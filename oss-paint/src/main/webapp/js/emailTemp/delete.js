
function deleteEmailTemp(url) {
    var tempSn = url.split("&")[1].split("=")[1];
    var status = url.split("&")[2].split("=")[1];
    if (status === "1") {
        alert("该邮件模板已删除！");
    }
    else {
        if (window.confirm("你确定要删除当前信息吗？")) {
            window.location.href = url.substring(0,url.indexOf("&status"));
        }
    }
}