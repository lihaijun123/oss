//
function removeSearchField() {
	var fields = document.getElementsByName("searchField");
	for(var i =0 ; i < fields.length ; ) {
		fields[i].parentNode.removeChild(fields[i]);
	}
	document.getElementsByName("searchBtn")[0].onclick = reg;
}

function removeHref() {
	var hrefs = document.getElementsByTagName("a");
	
	for(var i =0 ; i < hrefs.length ; i++) {
		if(hrefs[i].href.indexOf("http://")!=-1&&hrefs[i].href.indexOf("total")!=-1){
			var parent = hrefs[i].parentNode;
			var node = document.createTextNode("合計");
			parent.removeChild(hrefs[i]);
			parent.appendChild(node);
		}
		if(hrefs[i].href.indexOf("javascript")!=-1&&hrefs[i].href.indexOf("total")!=-1){
			hrefs[i].parentNode.removeChild(hrefs[i]);
		}
	}
}

function addRemind() {
	var e_time = document.getElementsByName("E_collecttime")[0];
	var cell0=e_time.parentNode.parentNode.insertCell(2);
	cell0.innerHTML=" (格式如︰200801 ! )";
}

UITool.AddEventHandler(window,"load",addRemind);
UITool.AddEventHandler(window,"load",removeSearchField);
UITool.AddEventHandler(window,"load",removeHref);


function reg(){
	if(document.getElementsByName("S_collecttime").length > 0 || document.getElementsByName("E_collecttime").length > 0){
		var startTime = document.getElementsByName("S_collecttime")[0].value;
		var endTime = document.getElementsByName("E_collecttime")[0].value;
		var pattern = /^\d{6}$/;
		if(startTime==""||endTime==""){
			alert("開始時間和結束時間都不能為空，且格式應該為yyyyMM 例如 : 200804 !");
			return false;
		}else{
			if((pattern.test(startTime)&&pattern.test(endTime)))
			{
				if(startTime.substring(4)<=12&&endTime.substring(4)<=12){
					if(startTime<=endTime){
						return true;
					}else{
						alert("起始時間不能大于結束時間！請重新輸入查詢條件！");
						return false;
					}
				}else{
					alert("輸入月份不可以大于12！");
					return false;
				}
			}else{
				alert("你輸入的格式不對！正確的格式應該為yyyyMM 例如 : 200804 !");
				return false;
			}
		}
	}
	return true;
}