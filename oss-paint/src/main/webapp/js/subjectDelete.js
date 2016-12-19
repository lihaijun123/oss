function deleteSubject(subjectId) {
	if(confirm("確定要刪除該主題嗎？")){
		var xmlhttp_request;
		// 創建XMLHTTP
		try {
			if (window.ActiveXObject) {
				for (var i = 5; i; i--) {
					try {
						if (i == 2) {
							xmlhttp_request = new ActiveXObject("Microsoft.XMLHTTP");
						} else {
							xmlhttp_request = new ActiveXObject("Msxml2.XMLHTTP."
									+ i + ".0");
							xmlhttp_request.setRequestHeader("Content-Type",
									"text/xml");
							xmlhttp_request.setRequestHeader("Charset", "gb2312");
						}
						break;
					} catch (e) {
						xmlhttp_request = false;
					}
				}
			} else if (window.XMLHttpRequest) {
				xmlhttp_request = new XMLHttpRequest();
				if (xmlhttp_request.overrideMimeType) {
					xmlhttp_request.overrideMimeType('text/xml');
				}
			}
		} catch (e) {
			xmlhttp_request = false;
		}
		// 使用GET方式提交
		var URL = 'subCustomer.do?method=isExistCustomer&subjectId=' + subjectId;
		xmlhttp_request.open('GET', URL, true);
		xmlhttp_request.send(null);
		xmlhttp_request.onreadystatechange = function() {
			if (xmlhttp_request.readyState == 4) {
				if (xmlhttp_request.status == 200) {
					responseStr = eval(xmlhttp_request.responseText);
					if(responseStr==false){
						location.href='massMail.do?method=deleteSubject&id='+subjectId;
					}else{
						if(confirm("當前主題下還有客戶存在，確定要刪除嗎？")){
							location.href='massMail.do?method=deleteSubject&id='+subjectId;
						}
					}
				}
			}
		}
	
	}
}

