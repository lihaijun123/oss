/**
 * @author gaoying
 */
function checkSpace()
		{
			var content= document.getElementById("time");
			var trimContent=trim(content.value);
			if(""==trimContent||null==trimContent)
			{
				alert("�п�J�ɶ�");
				content.value="";
				content.focus();
				return false;
			}
			return true;
		}
function checkSpaceIssue()
{
	var content= document.getElementById("issue");
	var trimContent=trim(content.value);
	if(""==trimContent||null==trimContent)
	{
		alert("�п�J�����I�I");
		content.value="";
		content.focus();
		return false;
	}
	return true;
}
function checkTime(){
		    var time = document.getElementById("time").value;
		    var reg = /^\s*\d{4}[0,1][0-9]\s*$/;
		    if (!reg.test(time)) {
				alert("�ɶ��榡����A�Э��s��J");
				return false;
			}
			else {
				var month = time.slice(4);
				if (month > '12' || month=='00') {
					alert("��J���ɶ������T�A�Э��s��J");
					return false;
				}
				else {
					return true;
				}
			}
		  }
function checkTimeFormat(){
	 var time = document.getElementById("time").value;
		    var reg = /^\s*\d{4}[0,1][0-9][0,1,2,3][0-9]\s*$/;
		    if (!reg.test(time)) {
				alert("�ɶ��榡����A�Э��s��J");
				return false;
			}
	var tarray = new Array();
	tarray[0]=time.slice(0,4);
	tarray[1]=time.slice(4,6);
	tarray[2]=time.slice(6);
	var tday = tarray.join('/');
	var day = new Date(Date.parse(tday));//�N�ɶ��榡��
	if (day.getDate() != tarray[2] || day.getMonth() != tarray[1] - 1 || day.getFullYear() != tarray[0]) {
		alert("��J���ɶ������T�A�Э��s��J�I�I�I");
		return false;
	}
	else {
		if (day.getDay() != 3) {
			alert("����������P���T�A�бz���s��J�I�I");
			return false;
		}
	}
	return true;
}

/*function checkEndTime()
  {
      var excuteTime = document.getElementById("executeTime").value;
      var endTime = document.getElementById('endTime').value;
      if(excuteTime != '' &&  endTime !=''){
         excuteTime = excuteTime.replace(/-/ig,'');
         endTime = endTime.replace(/-/ig,'');
        }
        excuteTime = parseInt( excuteTime);
        endTime = parseInt(endTime);
      if (excuteTime > endTime) {
        alert("�п�J���T�������ɶ�!");
        return false;
      }
      else
           return true;
     
    }
*/