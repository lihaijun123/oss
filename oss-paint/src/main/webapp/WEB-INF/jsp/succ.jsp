<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
	</head>
	<body>
		<SCRIPT language="javascript">
/*如果提示信息不為空,則彈出提示信息框*/
if(${view!=null})
	alert("${view}");
/*如果轉發鏈接存在,則立即轉發到相應頁面*/
if(${url!=null})
	window.location="${url}";
/*如果沒有轉發鏈接，則認為是EXT彈出框，並要求關閉該彈出框*/
else
{
/*
	if(window.parent.Ext.getCmp('op_win2')!=null)//如果彈出框的彈出框存在,則只關閉彈出框的彈出框
		window.parent.Ext.getCmp('op_win2').close;
	else
		window.parent.Ext.getCmp('op_win').close;
	window.parent.location.reload();
*/
	if(parent == top) {
		try{
			parent.closeDWindow2();
		}catch(e){
			parent.opener=null;
			parent.open('','_top');
			parent.close();
		}
	} else if(parent && parent.closeDWindow) {
		window.parent.location.reload();
		parent.closeDWindow();
	}
	else{
		window.history.back(-1);
	}
}
</SCRIPT>
	</body>
</html>