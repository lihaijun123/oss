/**
 * 彈出確認窗口
 * @param {Object} url
 */
function check(url) {
	if(window.confirm("確定允許重發。如允許，系統將AR報告發送給買家服務專員，讓其發給買家客戶。")){
      var locUrl = location.href;
      location.href=url+"&_h_referer="+escape(locUrl);
  }
}

