/**
 * ��_�}��Ȥ�W�٤U�K�[�챵
 * SALERQ-1897 �p���Ȥ�]�n�K�[�챵
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
					//�}��Ȥ�
                	cells[j].innerHTML = "<a href='/customer.do?method=checkOpenCustomer&customerId="+id+"'>" + cells[j].innerHTML + "</a>";
				} else if (property == "0") {
					//�p���Ȥ�
					cells[j].innerHTML = "<a href='/customer.do?method=details&customerId="+id+"'>" + cells[j].innerHTML + "</a>";
				}
            }
        }
    }
}

//
UITool.AddEventHandler(window, "load", initCustomerLink);
