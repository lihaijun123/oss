document.writeln('<script type="text/javascript" src="/js/fixed_ie_f5_shortcut.js"></script>');
if(!window.UITool) {
var UITool = {};
//控制回车键查询
UITool.getKey = function getKey(keyStroke) {
	var isNetscape=(document.layers);
	var eventChooser = (isNetscape) ? keyStroke.which : event.keyCode;
	if (eventChooser == 13)
	{
		checkPW();
		return false;
	}else if(eventChooser==27){
	       parent.close();
	       return false;
	}
}
UITool.onlyInputNumber = function onlyInputNumber() {
	onlyInputInteger();
}
UITool.onlyInputInteger = function onlyInputInteger(){
	var keyCode = window.event.keyCode;
	if(keyCode < 48 || keyCode >57) {
		window.event.returnValue = false ;
	}
}
UITool.onlyInputDouble = function onlyInputDouble() {
	var keyCode = window.event.keyCode;
	if(keyCode < 48 || keyCode >57) {
		window.event.returnValue = false ;
	}
}
UITool.trim = function trim(val) {
	var reg = /^\s*|\s*$/g ;
	val = val.replace(reg,"");
	return val ;
}
UITool.endWith = function endWith(orgObj) {
	var reg = /[\+\-\*\/]$/ ;
	return reg.test(orgObj);
}
UITool.trimLastChar = function trimLastChar(arg1) {
	return arg1.substring(0,arg1.length-1);
}

//
UITool.getValue = function getValue(ele) {
	if('select-one' == ele.type) {
		return ele.options[ele.selectedIndex].value;
	} else if('select-multiple' == ele.type) {
		var retArr = new Array();
		for(var i = 0 ; i < ele.options.length ; i++) {
			if(ele.options[i].selected) {
				retArr.push(ele.options[i].value);
			}
		}
		if(retArr.length <=0) {
			return "";
		} else if(retArr.length == 1) {
			return retArr[0] ;
		}
		return retArr;
	} else if('text' == ele.type||'hidden' == ele.type) {
		return ele.value;
	} else if('textarea' == ele.type) {
		return ele.value;
	} else if('radio' == ele.type) {
		var ret = "";
		for(var i = 0 ; i < ele.length ; i++) {
			if(ele[i].checked) {
				ret = ele[i].value;
				break;
			}
		}
		return ret;
	} else if('checkbox' == ele.type) {
		var retArr = new Array();
		for(var i = 0 ; i < ele.length ; i++) {
			if(ele[i].checked) {
				retArr.push(ele[i].value);
			}
		}
		if(retArr.length <=0) {
			return "";
		} else if(retArr.length == 1) {
			return retArr[0] ;
		}
		return retArr;
	} else {
		return ele.value; // $
	}
}

//set the default value for select-on
UITool.setSelectValue = function setSelectValue(obj, value) {
	if(!obj) return ;
	for(var i = 0 ; i < obj.length ; i++) {
		if(obj.options[i].value == value) {
			obj.options[i].selected = true ;
			break;
		}
	}
}

UITool.getLeftTrText = function getLeftTrText(ele) {
	try{
		if(ele == undefined) return "";
		var tdObj = ele.parentElement.parentElement.cells(0);
		if(tdObj != undefined) {
			return tdObj.innerText.replace(/︰|:/gi,'');
		}
	}catch(e){return ""};
}

UITool.checkItem = function checkItem(chkItem,chkItemDefValue) {
	var errMsg = "";
	for(var i = 0 ; i < chkItem.length ; i++){
		var obj = eval('document.all.'+chkItem[i]);
		if(obj == undefined) {
			continue;
		}
		var v = getValue(obj);
		if((typeof v) == 'object') {
			//
		} else {
			if(chkItemDefValue[i] == trim(v)) {
				errMsg += "请输入或选择" + getLeftTrText(obj)+ "正确的值。\r\n";
			}
		}
	}
	if(errMsg != '') {
		alert(errMsg);
		return false;
	}
	return true;
}
/**
 * UITOOL专用方法，取指定“key”的当前回显值
 */
UITool.getBackViewValue = function (key) {
	var ret ;
	var backValues = UITool.trim(window.id_JsBackView.text).split(';');
	var reg=new RegExp("resetSearchByText\\(document\\.all\\."+key+",\\'([\\d]*)\\'\\)","ig");
	for(var i = 0 ; i < backValues.length ; i++) {
		ret = reg.exec(UITool.trim(backValues[i]));
		if(ret) {
			ret = ret[1];
			break;
		}
	}
	return ret ;
}
/**
 * 给对象添加相关事件监听
 */
UITool.AddEventHandler = function(oTarget,sEventType,fnHandler) {
	if(!oTarget) return ;
	if(oTarget.addEventListener) {
		oTarget.addEventListener(sEventType,fnHandler,false);
	} else if(oTarget.attachEvent) {
		oTarget.attachEvent("on"+sEventType,fnHandler);
	} else{
		oTarget["on"+sEventType] = fnHandler;
	}
}
//UITool.AddEventHandler(window,"onload",showDownloadBtn);
}

function showDownloadBtn (){
	if(document.getElementById("searchBtn")) {
		document.getElementById("searchBtn").outerHTML += ' <input type="button" name="downBtn" title="保存当前条件下的所有数据到Excel文件" value="导出到Excel表格" onclick="download(\'down-xls\')">';
	}
}
function download(dtype) {
	var url = document.location.href;
	url = url.replace(/&nextStep=[\w]*/ig,'');
	url += '&nextStep=download&download='+dtype;
	document.location = url ;
}



//---------------------

function over(obj) {
	if(!obj || obj==undefined) return ;
	while(obj && obj.tagName != 'TR'&&obj.tagName != 'TBODY') {
		obj = obj.parentElement;
	}
	var table = document.getElementById("list00_table");
	var oldInfo = table.oldInfo;
	var index = oldInfo.index;
	if(obj && index != obj.rowIndex) {
		obj.style.backgroundImage = "url('/images/row-over.gif')";
	}
	if(obj.oldClassName!=null){
		obj.style.backgroundImage = "";
		obj.className="red";
	}
	return;
}
function out(obj) {
	if(!obj || obj == undefined) return ;
	while(obj.tagName != 'TR'&&obj.tagName != 'TBODY') {
		obj = obj.parentElement;
	}
	obj.style.backgroundImage = "";
	return;
}

function click(obj) {
	if(!obj || obj == undefined) return ;
	while(obj.tagName != 'TR'&&obj.tagName != 'TBODY') {
		obj = obj.parentElement;
	}
	 obj.style.backgroundImage = "";
	 var table = document.getElementById("list00_table");
	 var oldInfo = table.oldInfo;
	 if(oldInfo.index != -1) {
        var aRow = table.rows[oldInfo.index];
        if(aRow && aRow.style) {
			aRow.style.backgroundColor = oldInfo.color;
		}
	 }
	 table.oldInfo = {'index':obj.rowIndex, 'color':obj.style.backgroundColor};
	 //set click style
	 if(obj.oldClassName==null){
		 obj.style.backgroundColor = "#DFE8F6";
		 obj.style.borderTop = "1";
	 }
	 return;
}

window._init_ = true;
function init() {
	if(window._init_ == false) {
		return ;
	}
	var obj = document.getElementById("list00_table");
	if (obj) {
    var rows = obj.rows;
    var k = 0;
    obj.oldInfo = {
      'index': -1,
      'color': "#ffffff"
    };
    for (k = 0; k < rows.length; k++) {
      UITool.AddEventHandler(rows[k], "mouseover", function(en) {
        en = en || window.event;
        if (en.srcElement) {
          en.ele = en.srcElement;
        }
        else if (en.target) {
          en.ele = en.target;
        }
        else {
          return;
        }
        over(en.ele.parentNode);
      });
      UITool.AddEventHandler(rows[k], "mouseout", function(en) {
        en = en || window.event;
        if (en.srcElement) {
          en.ele = en.srcElement;
        }
        else if (en.target) {
          en.ele = en.target;
        }
        else {
          return;
        }
        out(en.ele.parentNode);
      });
      UITool.AddEventHandler(rows[k], "click", function(en) {
        en = en || window.event;
        if (en.srcElement) {
          en.ele = en.srcElement;
        }
        else if (en.target) {
          en.ele = en.target;
        }
        else {
          return;
        }
        click(en.ele.parentNode);
      });
    }
  }
	//
	//timeField();
}
UITool.AddEventHandler(window,"load",init);
document.writeln('<script src="js/calendar.js"></script>');
var timeFields = ["S_PAYMENT_TIME","E_PAYMENT_TIME","BEGIN_TIME","S_START_TIME","E_START_TIME","END_TIME","S_BEGIN_TIME","E_BEGIN_TIME","S_END_TIME","E_END_TIME","S_ADD_TIME","E_ADD_TIME","S_ISSUE_TIME","E_ISSUE_TIME",
              "S_EXPIRY_TIME","E_EXPIRY_TIME","S_JOIN_TIME","E_JOIN_TIME","S_CREATED_TIME","E_CREATED_TIME","S_PROTECT_PERIOD_END_DATE","E_PROTECT_PERIOD_END_DATE","S_CONTACT_TIME","E_CONTACT_TIME",
              "S_MODIFIED_TIME","E_MODIFIED_TIME","S_ALLOT_TIME","E_ALLOT_TIME","S_EXE_TIME","E_EXE_TIME","S_INVOICE_SEND_TIME","E_INVOICE_SEND_TIME","S_DATE_PRIORD","E_DATE_PRIORD","S_ADD_TIME2",
              "E_ADD_TIME2","S_ALLOT_TIME2","E_ALLOT_TIME2","S_CREATED_TIME2","E_CREATED_TIME2","S_MAX_RECEIVABLE_TIME2","E_MAX_RECEIVABLE_TIME2","S_LAST_RELEASE_TIME2","E_LAST_RELEASE_TIME2",
              "S_LAST_CHECK_TIME","E_LAST_CHECK_TIME","SEARCH_TIME","S_SEARCH_TIME","E_SEARCH_TIME","S_SEND_TIME2","E_SEND_TIME2","S_ENTER_TIME2","E_ENTER_TIME2","S_RECEIVE_TIME2","E_RECEIVE_TIME2",
              "SIGN_TIME","S_SIGN_TIME","E_SIGN_TIME","S_ACTIVE_TIME","E_ACTIVE_TIME","S_EXPIRE_TIME","E_EXPIRE_TIME","S_testtime","E_testtime","S_begin_time","E_begin_time","S_end_time","E_end_time",
              "PRE_PUBLISH_TIME","PUBLISH_TIME","FILE_TIME","ADD_TIME","UPDATE_TIME","S_PRE_PUBLISH_TIME","E_PRE_PUBLISH_TIME","S_PUBLISH_TIME","E_PUBLISH_TIME","S_FILE_TIME","E_FILE_TIME",
              "S_AUDIT_TIME","E_AUDIT_TIME","S_LIVE_DATE","E_LIVE_DATE"
              // wangliang begin
              ,"S_auditTime","E_auditTime"
              ,"S_EFFT_TIME_END","E_EFFT_TIME_END"
              // wangliang end
              // lsy begin
              ,"S_UPDATE_TIME","E_UPDATE_TIME"
              // lsy end
              ,"S_EXECUTION_TIME","E_EXECUTION_TIME"
              ,"S_AD_BEGIN_TIME","E_AD_BEGIN_TIME"
              ,"S_AD_END_TIME","E_AD_END_TIME"
              ,"S_ORDER_TIME", "E_ORDER_TIME"
              ,"S_PAY_TIME", "E_PAY_TIME"
              ];
function timeField() {
	for(var i = 0 ; i < timeFields.length ;i++) {
		var field = document.getElementsByName(timeFields[i]);
		if(field && field.length > 0) {
			field[0].readOnly = true;
			field[0].onclick = function(en) {
				en = en || window.event;
				en = en.srcElement || en.target
				show(en,'yyyy-MM-dd');
			}
		}
	}

	var fields2 = ["S_AUDIT_TIME","E_AUDIT_TIME"];
	for(var i = 0 ; i < fields2.length ;i++) {
		var field2 = document.getElementsByName(fields2[i]);
		if(field2 && field2.length > 0) {
			field2[0].readOnly = true;
			field2[0].onclick = function(en) {
				en = en || window.event;
				en = en.srcElement || en.target
				show(en,'yyyy-MM-dd');
			}
		}
	}
}
UITool.AddEventHandler(window,"load",timeField);

function scanTableUrlMail() {
/*
	var tab = document.getElementById("list00_table");
	var rows = tab.rows;
	for(var i = 1 ;i < rows.length ;i++) {
		var cells = rows[i].cells;
		for(var j = 0 ; j < cells.length ;j++) {
			var text = cells[j].innerText;
			if(cells[j].innerHTML == text) {
				if(isMail(text)) {
					cells[j].innerHTML = '<a href="mailto:'+text+'">'+text+'</a>';
				} else if(isUrl(text)) {
					if(!startWith(text.toLowerCase(),"http://") && !startWith(text.toLowerCase(),'https://')) {
						text= "http://"+text;
					}
					cells[j].innerHTML = '<a href="'+text+'" target="_blank">'+text+'</a>';
				}
			}
		}
	}
*/
}

function initInputLike() {
	var searchOper = document.getElementsByName("searchOper");
	var searchField = document.getElementsByName("searchField");
	for(var i = 0 ; i < searchField.length ; i++) {
		var field = searchOper[i].value;
		if(field == 'like' ){
			field = searchField[i].value;
			field = document.getElementsByName(field);
			if(field && field.length > 0) {
				field = field[0];
			}
			if(field) {
				if(field.value =='' || field.value == undefined) {
					field.style.background='url(/images/like.gif)';
				}
				field.onfocus = function() {
						this.style.background='';
				};
				field.onblur = function() {
						if(this.value == '' || this.value == undefined) {
							this.style.background='url(/images/like.gif)';
						} else {
							this.style.background='';
						}
				}
			}
		}
	}
}
UITool.AddEventHandler(window,"load",initInputLike);

function startWith(text,head) {
	var reg = new RegExp("^"+head,"ig");
	return reg.test(text);
}
function isMail(mail) {
	mail = trim(mail);
	var reg = /^\w+(-\w)*@(-?_?\w+)+(\.\w+)+/ig;
	return reg.test(mail);
}
function isUrl(url) {
	url = trim(url)
	var reg = /^(http\:\/\/)?(www\.)?(-?_?\w+)+(\.\w+)+(\.\w+)+$/ig;
	return reg.test(url);
}
UITool.AddEventHandler(window,"load",scanTableUrlMail);
//
//----------------------------- trim left and right space
//
function commonLoading_() {
	UITool.AddEventHandler(document.searchForm, "submit", commonTrimSpace);
}
var _searchAll = true;
//是否清除页面字段,防止后台把它们拼接到SQL
var isNeedClearFields=false;
//是否检查所有字段的长度大于某个值
var isLengthLargeThan=false;
//是否检查"指定区域是否有输入"
var isSpecialFieldsHasValue=false;
//是否检查"指定字段为大于0的整數"
var isCheckLargeThanZero=false;
function commonTrimSpace(show) {
	var evnt = arguments[0] ||window.event;
	var fields = document.getElementsByName("searchField");
	var opers = document.getElementsByName("searchOper");
	var hasValue = false;
	var startEndFields = new Object();
	for (var i = 0; i < fields.length; i++) {
		var field = fields[i].value;
		if(field == "") continue;
		if(opers[i].value == '>=') {
			startEndFields["S_" + field] = "E_" + field;
			field = "S_"+field;
		} else if(opers[i].value == '<=') {
			field = "E_"+field;
		} else {
		}
		if(field.length >0 && document.getElementsByName(field).length > 0) {
			var value = UITool.trim(document.getElementsByName(field)[0].value);
			if(value !="") {
				hasValue = true;
			}
			document.getElementsByName(field)[0].value = value;
		}
	}
	if(!validNumber()) {
		if(evnt.preventDefault)
			evnt.preventDefault();
		evnt.returnValue = false;
		return false;
	}
	if(validStartEnd(startEndFields) == false) {
		if(evnt.preventDefault)
			evnt.preventDefault();
		evnt.returnValue = false;
		return false;
	}
	if(isLengthLargeThan)
		if(!checkLengthLarge(hasValue)) {
			if(evnt.preventDefault)
				evnt.preventDefault();
			evnt.returnValue = false;
			return false;
		}
	if(isNeedClearFields)
		removeSearchKeyField(hasValue);
	if(hasValue == false && (_searchAll == false)) {
		if(show)
			alert("请至少输入一个以上的查询条件!");
		if(evnt.preventDefault)
			evnt.preventDefault();
		evnt.returnValue = false;
		return false;
	}
	if (isSpecialFieldsHasValue) {
		if (!checkSomeFields(hasValue)) {
			return false;
		}
	}
	if (isCheckLargeThanZero) {
		if(!validLargeThanZero()) {
			if(evnt.preventDefault)
				evnt.preventDefault();
			evnt.returnValue = false;
			return false;
		}
	}
  if(typeof lower == 'function'){
    lower();
  }
	return true;
}
//
// 针对查询界面的只能为数值的处理
//
var VALID_NUMBER = {"RECORD_ID":"编号","ACCOUNT_ID":"客户编号","CONTRACT_ID":"合同编号","PRODUCT_ID":"产品编号","PRODUCT_TYPE_ID":"产品类型ID"
					,"LABEL_ID":"标签ID","CST_ID":"会员ID","AGENT_ID":"代理商ID","BAIL":"保证金","LAST_TIME_BALANCE":"原帐户余额"
					,"CURRENT_BALANCE":"现有余额","REMIND_ID":"提醒编号","ATTACHMENT_ID":"附件编号","OFFICE_ID":"办事处编号","BOOKING_ID":"预定ID"
					,"MEMBER_ID":"会员ID","INDENT_ID":"订单ID","PAYMENT_VALUE":"支付值","failTimes":"登录失败次数"
					,"S_PAYMENT_MONEY":"回款金额","E_PAYMENT_MONEY":"回款金额","S_TOTAL_MONEY":"合同总金额","E_TOTAL_MONEY":"合同总金额"
					,"PRINTING_ID":"打印编号","PRINT_COUNT":"打印张数","REC_ID":"流水号","BE_ACCOUNT_ID":"被撞客戶ID","CRASH_ACCOUNT_ID":"撞單方ID"
					,"CONTACT_ID":"聯系人ID","COM_ID":"會員ID","SMART_ID":"領動會員ID","ACCOUNT_AMOUNT":"數量","MIC_COM_ID":"MIC公司ID"
					,"S_STATISTICS_DATA":"統計值","E_STATISTICS_DATA":"統計值","ADS_ID":"廣告ID","RELATED_ID":"被操作記錄號","RESOURCE_ID":"資源編號"
					,"LOG_RECORD_ID":"流水號","CONT_ID_S":"合同編號","COM_ID_S":"公司編號","SERVICE_ID":"服務信息ID","S_MORE_PAY_M":"多余回款"
					,"E_MORE_PAY_M":"多余回款","S_CONTRACT_AMOUNT":"關聯電子合同數量","E_CONTRACT_AMOUNT":"關聯電子合同數量","ORDER_ID":"訂單編號"
					,"S_PAY_NETAMT":"到帳金額","E_PAY_NETAMT":"到帳金額","S_GOODS_AMT":"銷售金額","E_GOODS_AMT":"銷售金額","S_PAY_AMT":"實際金額","E_PAY_AMT":"實際金額"
					,"ITEM_ID":"訂單商品ID","S_TOTAL_FEE":"總金額","E_TOTAL_FEE":"總金額","S_DISCOUNT_FEE":"優惠金額","E_DISCOUNT_FEE":"優惠金額"
					,"S_ARRIVE_FEE":"到帳金額","E_ARRIVE_FEE":"到帳金額","S_LIMIT":"圖數額度","E_LIMIT":"圖數額度","S_POWER":"權重","E_POWER":"權重"
					};
function validNumber() {
	for(var key in VALID_NUMBER) {
		var field = document.getElementsByName(key);
		if(field && field.length > 0) {
			if(isNaN(field[0].value)) {
				alert("“"+VALID_NUMBER[key]+"”请输入正确的数值！");
				field[0].focus();
				return false;
			}
		}
	}
	return true;
}
//
// 针对查询界面的只能为大于0的整数的处理
//
var FIELD_LARGE_THAN_ZERO = {"CONTRACT_ID":"合同ID"};
function validLargeThanZero() {
	for(var key in FIELD_LARGE_THAN_ZERO) {
		var field = document.getElementsByName(key);
		if(field && field.length > 0) {
			if(field[0].value != null && field[0].value != "" && !/^[1-9]\d*$/.test(field[0].value)) {
				alert("“"+VALID_NUMBER[key]+"”请填写有效的信息！");
				field[0].focus();
				return false;
			}
		}
	}
	return true;
}
// 针对查询时间时,填写时间范围 后面时间应大于等于前面时间问题
function validStartEnd(startEndFields) {
	var sfieldVal = "";
	var efieldVal = "";
	for(var key in startEndFields) {
		var sfield = document.getElementsByName(key);
		if(!sfield) continue;
		var efield = document.getElementsByName(startEndFields[key]);
		if(!efield) continue;
		sfieldVal = sfield[0].value || "";
		efieldVal = efield[0].value || "";
		if(sfieldVal == "" || efieldVal == "")
			continue;
		if(sfieldVal.length == 10 || efieldVal.length == 10){
			sfieldVal = sfieldVal.replace(/-/ig,'');
			efieldVal = efieldVal.replace(/-/ig,'');
		}
		sfieldVal = parseInt(sfieldVal,10);
		efieldVal = parseInt(efieldVal,10);
		if(sfieldVal > efieldVal ) {
			if(in_array(key, timeFields)){
				var dateFildObj = $("input[name*='" + key + "']");
				try{
					var dateFildPreText = dateFildObj.parent().prev("td").text();
					if(dateFildPreText.indexOf(":") != -1){
						dateFildPreText = dateFildPreText.substr(0, dateFildPreText.length - 1);
					}
					alert(dateFildPreText + "搜索条件中：开始时间不能大于结束时间!");
				}catch(e){
					alert("开始时间不能大于结束时间!");
				}
				return false;
			}
		}
	}
	return true;
}

//
UITool.AddEventHandler(window,"load",commonLoading_);

//打开新窗口(带参数,如果不传参数则采用默认值)
function openwin(url,iWidth,iHeight,openName){
	if(iWidth==null||iWidth==0)
		iWidth=800;
	if(iHeight==null||iHeight==0)
		iHeight=520;
	var url;  //转向网页的地址
	if(openName==null){
	 var name='op_win'; //网页名称，可为空
	}else{
    var name=openName;
  }
	var iTop = (screen.availHeight-30-iHeight)/2; //获取窗口垂直位置
	var iLeft = (screen.availWidth-10-iWidth)/2; //获取窗口水平位置
	window.open(url,name,'height='+iHeight+',innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
}


//打开新窗口
function openwinParam(url,param){
	openwin(url+convertSpecial2Code(param));
}
/**
*将特殊字符转换成相应的URL编码，用于URL传输不会引起混乱
*/
function convertSpecial2Code(val)
{
	var str=val;
	str=str.replace('+','%20');
	str=str.replace('?','%3F');
	str=str.replace('%','%25');
	str=str.replace('#','%23');
	str=str.replace('&','%26');
	str=str.replace('/','%2F');
	str=str.replace('\'','��');
	return str;
}

/**
 * 判断是否在数组中
 * @param needle
 * @param haystack
 * @returns {Boolean}
 */
function in_array(needle, haystack)
{
    // 得到needle的类型
    var type = typeof needle;
    if(type === "string" || type === "number"){
        for(var i in haystack){
            if(haystack[i] == needle){
               return true;
            }
       }
    }
   return false;
}
