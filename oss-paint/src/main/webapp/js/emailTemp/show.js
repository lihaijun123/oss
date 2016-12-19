/**
 * @author chenyunlong
 */
$(document).ready(function(){
	//删除模板
	$("#deleteTemp").bind("click",function(){
		if(confirm("确认删除？")){
			window.location.href="/email-temp.do?method=delete&emailTempSn="+$("#emailTempSn").val();
		}
	})
	$("#updateTemp").bind("click",function(){
		window.location.href="/email-temp.do?method=update&emailTempSn="+$("#emailTempSn").val();
	})		
})
