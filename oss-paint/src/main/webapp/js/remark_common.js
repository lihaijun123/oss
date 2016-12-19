/**
 * 處理備注（包括新增、修改、刪除）
 * @param {Object} url
 */
function dealRemark(url) {
    $("remarkFrm").src = url;
}
/**
 * 處理備注iframe的大小
 */
function resize() {
    $("remarkFrm").setAttribute('height', window.frames['remarkFrm'].document.body.offsetHeight);
}
