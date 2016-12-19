CKEDITOR.editorConfig = function(config){
    config.toolbar = [
	    /*['Maximize'],
		['Preview', 'Cut', 'Copy', 'PasteText'],
		['Undo','Redo'],['SelectAll','RemoveFormat'],
		['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
		['AddImage'],['SpecialChar'],['Table'],
		['Link', 'Unlink', 'Anchor'],
		'/',
		['Format', 'Font', 'FontSize'],
		['TextColor', 'BGColor'],
		['Bold', 'Italic', 'Underline', 'Strike'],
		['Subscript', 'Superscript'],
		['NumberedList', 'BulletedList'],
		['Outdent', 'Indent']*/
	 ];
	 config.font_names = '仿宋—GB1312;黑体;华文彩云;华文宋体;华文琥珀;华文楷体;华文隶书;华文宋体;华文细黑;楷书;隶书;宋体;宋体-pua;微软雅黑;新宋体;幼圆';
	 config.enterMode = CKEDITOR.ENTER_BR;
	 config.shiftEnterMode = CKEDITOR.ENTER_P;

	 config.extraPlugins = "myAddImage";
	 //关闭展开工具栏按钮
	 config.toolbarCanCollapse=false;
	 //自定义会员信息按钮
	 config.extraPlugins += (config.extraPlugins ? ',helloworld' : "helloworld");

	 // 从word里复制内容时是否移除样式
	 config.pasteFromWordRemoveStyles = false;
	 config.pasteFromWordRemoveFontStyles = false;

	 // 字体格式
	 config.format_tags = "p;h1;h2;h3;h4;h5;h6";
};

CKEDITOR.on('dialogDefinition', function(ev) {
    var dialogName = ev.data.name;
    var dialogDefinition = ev.data.definition;
    if (dialogName == 'myAddImage') {
    	// 图象层的ID是photo
    	var infoTab = dialogDefinition.getContents('addImage');
    	infoTab.add({
    		type: 'button',
    		id: 'upload_image',
    		align: 'center',
    		label: '上传',
    		onClick: function(evt) {
    			var thisDialog = this.getDialog();
    			var txtUrlObj = thisDialog.getContentElement('addImage', 'photo');
    			var txtUrlId = txtUrlObj.getInputElement().$.id;
	    		// 得到起addImage下所有元素中寻找得到photo元素
	    		// 得到 txtUrl中input元素 id
    			//var imgVal = CKEDITOR.instances.editor.getData();
    			var editorId = "";
				try{
	    			editorId = getEditorId();
				}catch(e){
					editorId = "inforContent";
				}
    			var imgVal = CKEDITOR.instances[editorId].getData();
    			var imgNum = imgVal.match(/<img/g);
    			if(imgNum != null){
    				var imgCount = ckeditorImgCountLimit();
    				if(imgNum.length >= imgCount)
    				{
    					alert("图片的数量超出了限制" + imgCount + "张");
    					return false;
    				}
    			}
	    		addUploadImage(txtUrlId);
    	}

    	}, 'browse');
    }
	if(dialogName == 'helloworld'){
		var infoTab = dialogDefinition.getContents('cb');
//		infoTab.add({
//    		type: 'button',
//    		id: 'member1',
//    		label: 'member1',
//    		onClick: function(evt) {
//				alert('member1');
//    		}
//    	}, 'browse');
	}
})

function addUploadImage(theURLElementId){
    //自己的处理文件/图片上传的页面URL
	var uploadUrl = "/js/ckeditor/picUpload.jsp";
	//定义打开父窗口后子窗口不能操作，获取子窗口window.returnValue的值
    var imgUrl = window.showModalDialog(uploadUrl,"","dialogWidth=300px;dialogHeight=200px");
    //在upload结束后将图片url返回给imgUrl变量。
    if(!imgUrl){
    	imgUrl = "";
    }
    var urlObj = document.getElementById(theURLElementId);
    // 源文件框值修改
    urlObj.value = imgUrl;
    //源文件框值修改是通过js修改的，要想生效，最后要其change方法
    if (document.all) {
    	//inputStr[i].fireEvent('onchange');
    }
    else
    {
    	var evt = document.createEvent('HTMLEvents');
    	evt.initEvent('change',true,true);
    	urlObj.dispatchEvent(evt);
    }
 }

function ckeditorKeyUp(contentId) {
	//CKEDITOR.tools.setTimeout(function () {
		var rwords = ckeditorWordsCountLimit();
		var cwords = CKEDITOR.instances[contentId].getData();
		var twords = "";
		if(cwords.length > rwords){
			//CKEDITOR.instances[contentId].setData("");
			//alert("字数不能超过" + rwords + "!");
		}
	//}, 0);
}

//半角转全角
function toDBC(contentId){
	var txtstring = CKEDITOR.instances[contentId].getData();
	alert('暂时不实现' + txtstring);
	var ui = CKEDITOR.instances[contentId].getData();
	var notValid_1 = /<[^>]*>/;debugger;
	while(!notValid_1.test(ui)){
		ui = ui.replace(notValid_1, "");
	}
	CKEDITOR.instances[contentId].setData(ui);
}

//去掉空格
function trimBlankStr(contentId){
	var ui = CKEDITOR.instances[contentId].getData();
	var notValid_1 = /&nbsp;/;
	var notValid_2 = /\s/;
	while(notValid_1.test(ui)){
		ui = ui.replace(notValid_1, "");
	}
	while(notValid_2.test(ui)){
		ui = ui.replace(notValid_2, "");
	}
	CKEDITOR.instances[contentId].setData(ui);
}

//段首空两格
function headAdTwoBlank(contentId){
	var ui = CKEDITOR.instances[contentId].getData();
	CKEDITOR.instances[contentId].setData("&nbsp;&nbsp" + ui);
}
//删除段间空行
function deleteParagraphBlank(contentId){
	var ui = CKEDITOR.instances[contentId].getData();
	var notValid_1 = /<br \/>/;
	while(notValid_1.test(ui)){
		ui = ui.replace(notValid_1, "");
	}
	CKEDITOR.instances[contentId].setData(ui);
}
///////////textarea

//去掉空格
function trimBlankStr_1(contentId){
	var ui = document.getElementById(contentId).value;
	var notValid_1 = /&nbsp;/;
	var notValid_2 = /\s/;
	while(notValid_1.test(ui)){
		ui = ui.replace(notValid_1, "");
	}
	while(notValid_2.test(ui)){
		ui = ui.replace(notValid_2, "");
	}
	document.getElementById(contentId).value = ui;
}

//段首空两格
function headAdTwoBlank_1(contentId){
	var ui = document.getElementById(contentId).value;
	document.getElementById(contentId).value = "&nbsp;&nbsp" + ui;
}
//删除段间空行
function deleteParagraphBlank_1(contentId){
	var ui = document.getElementById(contentId).value;
	var notValid_1 = /<br \/>/;
	while(notValid_1.test(ui)){
		ui = ui.replace(notValid_1, "");
	}
	document.getElementById(contentId).value = ui;
}
