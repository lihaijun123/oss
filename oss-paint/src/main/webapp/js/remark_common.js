/**
 * �B�z�ƪ`�]�]�A�s�W�B�ק�B�R���^
 * @param {Object} url
 */
function dealRemark(url) {
    $("remarkFrm").src = url;
}
/**
 * �B�z�ƪ`iframe���j�p
 */
function resize() {
    $("remarkFrm").setAttribute('height', window.frames['remarkFrm'].document.body.offsetHeight);
}
