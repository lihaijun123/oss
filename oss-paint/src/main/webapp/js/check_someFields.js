/*
*�Τ_�d�߫��w�ϰ�O�_����J
*/
try {
	_searchAll=false;
	isSpecialFieldsHasValue=true;
}
catch (e) {
}
function checkSomeFields(hasValue) {
	if (hasValue) {
		isHave=true;
		var msgCheck="";
		//���^�媩��:�O�_����
		if(document.getElementsByName("LAN_CODE")){
			if(document.getElementsByName("LAN_CODE")[0].options[0].selected==true){
				isHave=false;
				msgCheck+="���^�媩�����e�����!\n";
			}
		}
		//�٥�:�O�_����
		if(document.getElementsByName("PROVINCE")&&document.getElementsByName("CITY")){
			var pro = UITool.trim(document.getElementsByName("PROVINCE")[0].value);
			var city = UITool.trim(document.getElementsByName("CITY")[0].value);
			if((pro=="")&&(city=="")){
				isHave=false;
				msgCheck+="�٥����e���i����!\n";
			}
		}
		//ñ���ɶ�:�O�_����
		if(document.getElementsByName("S_SIGN_TIME")&&document.getElementsByName("E_SIGN_TIME")){
			var startTime = UITool.trim(document.getElementsByName("S_SIGN_TIME")[0].value);
			var endTime = UITool.trim(document.getElementsByName("E_SIGN_TIME")[0].value);
			if((startTime=="")&&(endTime=="")){
				isHave=false;
				msgCheck+="ñ���ɶ����i����!\n";
			}
		}
		if(!isHave){
			alert(msgCheck);
			return false;
		}
		else{
      /*
			var reg=/[^\d]+/g;
			if(document.getElementsByName("TELEPHONE")){
				var tel = document.getElementsByName("TELEPHONE")[0].value;
				document.getElementsByName("TELEPHONE")[0].value = tel.replace(reg,"");
			}
			if(document.getElementsByName("MOBILE")){
				var tel = document.getElementsByName("MOBILE")[0].value;
				document.getElementsByName("MOBILE")[0].value = tel.replace(reg,"");
			}
			if(document.getElementsByName("FAX")){
				var tel = document.getElementsByName("FAX")[0].value;
				document.getElementsByName("FAX")[0].value = tel.replace(reg,"");
			}
      */
			return true;
		}
	}
	return true;
}

