/**
將結果返回給指定的控件 比如文本框
*/
AjaxResult.prototype.reportSuccess = function (response, options)
{
	var responseObj = eval(response.responseText);
	if(responseObj.length>0) {	
             document.getElementById(this.viewID).value = responseObj;
	}else {
		
	}
	
}


    /**
	viewID:用于顯示結果的控件ID
	parameters: url的形式 比如name=mike&age=22
	*/
	function AjaxResult(url, parameters, viewID) {
	var turl = url;
	var _this = this;
	var param = "";
	if(url.indexOf("?")!= -1) {
    	 turl = url.substring(0, url.indexOf("?"));
		 param = url.substring(url.indexOf("?")+1, url.length);
	}
	
	
	
	this.viewID = viewID;
	this.myAjax = null;
	this.myAjax =  Ext.Ajax.request({
    url: turl,
   success:  function(response, options) { _this.reportSuccess(response, options)},
   failure:  function(response, options) {},  
   params: param+"&"+parameters
});
}