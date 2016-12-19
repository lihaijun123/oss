if(!window.ajax) {
var ajax=function () {};
//
ajax.prototype.send = function (method,url,params,caller) {
	caller.xmlHttp = creatReq();
    if(caller.xmlHttp)
    {
        caller.xmlHttp.open(method||"POST",url,true);//與服務端建立連接(請求方式post或get，地址,true表示異步)
		caller.xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		caller.xmlHttp.setRequestHeader("X-Requested-With","XMLHttpRequest");
        //指定回調函數
        caller._this = this;
        caller.xmlHttp.onreadystatechange = function() {
			if(caller.xmlHttp.readyState==4) //請求狀態為4表示成功
			{
				if(caller.xmlHttp.status==200) //http狀態200表示OK
                {
					var data = eval(caller.xmlHttp.responseText);
					caller.callBack(data,caller);
                }
                else //http返回狀態失敗
                {
                    alert("服務端返回狀態︰" + caller.xmlHttp.statusText);
                }
			}
			else //請求狀態還沒有成功，頁面等待
			{
				//document.getElementById ("myTime").innerHTML ="數據加載中";
			}
        };
        caller.xmlHttp.send(params); //發送請求
    }
};
function creatReq()
{
	var req;
	//非IE瀏覽器，用xmlhttprequest對象創建
    if(window.XMLHttpRequest)
    {
        req=new XMLHttpRequest();
    }
    //IE瀏覽器用activexobject對象創建
    else if(window.ActiveXObject)
    {
        req=new ActiveXObject("Microsoft.XMLHttp");
    }
    return req;
};//req
//window.xmlHttp = creatReq();
}