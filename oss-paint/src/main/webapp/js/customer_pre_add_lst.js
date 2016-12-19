/**
 * 對于開放客戶名稱下添加鏈接
 * SALERQ-1897 私有客戶也要添加鏈接
 */
function initCustomerLink() {
    var rows = document.getElementsByTagName("tr");
    for (var i = 0; i < rows.length; i++) {
		var property = rows[i].getAttribute("PROPERTY");
        var cells = rows[i].cells;
        var id;
        for (var j = 0; j < cells.length; j++) {
            if(cells[j].className == "lisNumTd"){
                id = cells[j].innerHTML;
            }
            if (cells[j].className == "lisTdACCOUNT_NAME") {				
				if (property == "1") {
					//開放客戶
                	cells[j].innerHTML = "<a href='/customer.do?method=checkOpenCustomer&customerId="+id+"'>" + cells[j].innerHTML + "</a>";
				} else if (property == "0") {
					//私有客戶
					cells[j].innerHTML = "<a href='/customer.do?method=details&customerId="+id+"'>" + cells[j].innerHTML + "</a>";
				}
            }
        }
    }
}

//
UITool.AddEventHandler(window, "load", initCustomerLink);
