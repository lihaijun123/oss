if(!window.ajax) {
var ajax=function () {};
//
ajax.prototype.send = function (method,url,params,caller) {
	caller.xmlHttp = creatReq();
    if(caller.xmlHttp)
    {
        caller.xmlHttp.open(method||"POST",url,true);//�P�A�Ⱥݫإ߳s��(�ШD�覡post��get�A�a�},true��ܲ��B)
		caller.xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		caller.xmlHttp.setRequestHeader("X-Requested-With","XMLHttpRequest");
        //���w�^�ը��
        caller._this = this;
        caller.xmlHttp.onreadystatechange = function() {
			if(caller.xmlHttp.readyState==4) //�ШD���A��4��ܦ��\
			{
				if(caller.xmlHttp.status==200) //http���A200���OK
                {
					var data = eval(caller.xmlHttp.responseText);
					caller.callBack(data,caller);
                }
                else //http��^���A����
                {
                    alert("�A�Ⱥݪ�^���A�J" + caller.xmlHttp.statusText);
                }
			}
			else //�ШD���A�٨S�����\�A��������
			{
				//document.getElementById ("myTime").innerHTML ="�ƾڥ[����";
			}
        };
        caller.xmlHttp.send(params); //�o�e�ШD
    }
};
function creatReq()
{
	var req;
	//�DIE�s�����A��xmlhttprequest��H�Ы�
    if(window.XMLHttpRequest)
    {
        req=new XMLHttpRequest();
    }
    //IE�s������activexobject��H�Ы�
    else if(window.ActiveXObject)
    {
        req=new ActiveXObject("Microsoft.XMLHttp");
    }
    return req;
};//req
//window.xmlHttp = creatReq();
}