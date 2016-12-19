//
function setCurTime()
{
	curDate.setSeconds(curDate.getSeconds()+1);
	
	var years = curDate.getFullYear();
	var months = curDate.getMonth()+1;
	var days = curDate.getDate();
	var hours = curDate.getHours();
	var minutes = curDate.getMinutes();
	var seconds = curDate.getSeconds();
	
	var formatMonths ;
	var formatDays ;
	var formatHours ;
	var formatMinutes ;
	var formatSeconds ;
	
	if(months < 10)
	{
		formatMonths = "0"+months;
	}
	else
	{
		formatMonths = months;
	}
	if(days < 10)
	{
		formatDays = "0"+days;
	}
	else
	{
		formatDays = days;
	}
	if(hours < 10)
	{
		formatHours = "0"+hours;
	}
	else
	{
		formatHours = hours;
	}
	if(minutes < 10)
	{
		formatMinutes = "0"+minutes;
	}
	else
	{
		formatMinutes = minutes;
	}
	if(seconds < 10)
	{
		formatSeconds = "0"+seconds;
	}
	else
	{
		formatSeconds = seconds;
	}
	document.getElementById("showTime").innerHTML = years +"-"+formatMonths + "-" + formatDays + " " + formatHours +":"+formatMinutes+":"+formatSeconds;
	window.setTimeout("setCurTime()",1000);
}	