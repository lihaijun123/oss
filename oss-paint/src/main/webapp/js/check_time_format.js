/**
 * @author gaoying
 */
function checkSpace()
		{
			var content= document.getElementById("time");
			var trimContent=trim(content.value);
			if(""==trimContent||null==trimContent)
			{
				alert("請輸入時間");
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
		alert("請輸入分類！！");
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
				alert("時間格式不對，請重新輸入");
				return false;
			}
			else {
				var month = time.slice(4);
				if (month > '12' || month=='00') {
					alert("輸入的時間不正確，請重新輸入");
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
				alert("時間格式不對，請重新輸入");
				return false;
			}
	var tarray = new Array();
	tarray[0]=time.slice(0,4);
	tarray[1]=time.slice(4,6);
	tarray[2]=time.slice(6);
	var tday = tarray.join('/');
	var day = new Date(Date.parse(tday));//將時間格式化
	if (day.getDate() != tarray[2] || day.getMonth() != tarray[1] - 1 || day.getFullYear() != tarray[0]) {
		alert("輸入的時間不正確，請重新輸入！！！");
		return false;
	}
	else {
		if (day.getDay() != 3) {
			alert("日期必須為星期三，請您重新輸入！！");
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
        alert("請輸入正確的結束時間!");
        return false;
      }
      else
           return true;
     
    }
*/