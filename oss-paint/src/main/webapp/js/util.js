document.writeln('<script type="text/javascript" src="/js/fixed_ie_f5_shortcut.js"></script>');
var $ = function(field,type) {
	if(document.getElementById(field)){
		return document.getElementById(field)
	}else{
		return document.getElementsByName(field);
	}
}
function hasChinese(str)
{
	var isChinese = true;
	for (i = 0;  i < str.length;  i++)
	{
		c = str.charCodeAt(i);
		if (c >= 256)
		{
			return true;
		}
	}
	return false;
}
function checkForm(checkForm)
{
  for(i=0;i<checkForm.elements.length;i++)
  {
     if(checkForm.elements[i].type!="submit"&&checkForm.elements[i].type!="reset"&checkForm.elements[i].type!="button")
       {
          var temp=checkForm.elements[i].value;
//          temp=temp.replace(/ /ig,"");
        if(temp.length<1)
        {
          alert("請填入"+checkForm.elements[i].name+"的值")
          checkForm.elements[i].focus();
          return false;
        }
       }
  }
    return true;
}

function getcCookie(cookieName)
{
 var cookieString = new String(document.cookie) ;
   var returnValue=null;
 var cookieHeader = cookieName+"=" ;
 var beginPosition = cookieString.indexOf(cookieHeader);
 if (beginPosition != -1)
  {
 returnValue = cookieString.substring(beginPosition+ cookieHeader.length) ;
 }
	return returnValue;
}

/**
*段首空兩格
*@param:document.obj
**/
function addTwoBlankEachParagraphForObject(obj)
{
		obj.value=twoBlankEachParagraph(obj.value);
}

/**
*句子首字母大寫
*@param:document.obj
**/
function toUpperFirstWordEachSentenceForObject(obj)
{
		obj.value=toUpperFirstWordEachSentence(obj.value);
}
/**
* 單詞首字母大寫
*@param:document.obj
**/
function toUpperEachWordForObject(obj)
{
		var va=toLowerForAllWord(obj.value);
		obj.value=toUpperEachWord(va);
}

function toUpperEachWordForMutiObject(obj)
{
		if(typeof(obj.length)=='undefined')
		{
			toUpperEachWordForObject(obj);
		}
		else
		{
			for(var i=0;i<obj.length;i++)
			{
				toUpperEachWordForObject(obj[i]);
			}
		}
}

/**
* 單詞字母全大寫
*@param:document.obj
**/
function toUpperWordForObject(obj)
{
		var va=toLowerForAllWord(obj.value);
		obj.value=toUpperAllWord(va);
}

/**
* 單詞首字母小寫
*@param:document.obj
**/
function toLowerEachWordForObject(obj)
{
		obj.value=toLowerEachWord(obj.value);
}
/**
 * 全部字母小寫
 */
function toLowerForAllWordForObject(obj)
{
	obj.value = toLowerForAllWord(obj.value);
}

/**
 * 全部字母小寫
 */
function toLowerForAllWord(val)
{
	return val.toLowerCase();
}

/*********************************************
*將標點符號後加空格*
*@param:document.obj
*********************************************/
function addPuncAfterBlankForObject(obj)
{
obj.value=addPuncAfterBlank(obj.value);
}

function addPuncAfterBlankForMultiObject(obj)
{
		if(typeof(obj.length)=='undefined')
		{
			addPuncAfterBlankForObject(obj);
		}
		else
		{
			for(var i=0;i<obj.length;i++)
			{
				addPuncAfterBlankForObject(obj[i]);
			}
		}
}
/*********************************************
*將標點符號改成加,*
*@param:document.obj
*********************************************/
function addCommaFromCnPunctuation(obj)
{
obj.value=replaceCommaFromCnPunctuation(obj.value);
}

/***********************
*實數的驗證*
*@param String*
*@return boolean*
***********************/
function isFloat(str)
{
	var patrn=/^([\d]+)[\.]?([\d]*)$/;
	return patrn.test(str);
}
function isAllFloat(str)
{
	var patrn=/^-?([\d]+)(\.)?([\d]*)$/;
	return patrn.test(str);
}

/***********************
*整數的驗證*
*@param String*
*@return boolean*
***********************/
function isInteger(str)
{
	var patrn=/^(-|\+)?\d+$/;
	return patrn.test(str);
}

/**********************
*負整數的驗證*
*@param String*
*@return boolean*
************************/
function isNegativeInteger(str)
{
	var patrn=/^-\d+$/;
	return patrn.test(str);
}

/***********************
*正整數的驗證*
*@param String*
*@return boolean*
***********************/
function isPlusInteger(str)
{
	var patrn=/^(\+)?\d+$/;
	return patrn.test(str);
}

/***********************
*電子郵件地址的驗證*
*@param String*
*@return boolean*
***********************/
function isCorrectEmail(str)
{
	var patrn=/[a-zA-Z0-9][\w\.\-]*@[a-zA-Z0-9][\w\.\-]*\.[a-zA-Z][a-zA-Z\.]*/;
	return patrn.test(str);
}

/***********************
*時間的驗證(hh:mm:ss)*
*@param String*
*@return boolean*
***********************/
function isTime(str)
{
	var a = str.match(/^(\d{1,2})(:)(\d{1,2})(\2)(\d{1,2})$/);
	//var a = str.match(/^(\d{1,2})(:)(\d{1,2})(:)(\d{1,2})$/);
	if (a == null)
	{
		alert("輸入的不是時間格式(hh:mm:ss)！");
		return false;
	}
	if (a[1]>24 || a[3]>60 || a[5]>60)
	{
		alert("時間不正確！");
		return false
	}
	return true;
}
/***********************
*日期的驗證(yyyy-mm-dd)*
*@param String*
*@return boolean*
***********************/
function isDate(str)
{
	var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})(-|\/)(\d{1,2})$/);
	if(r==null)
	{
		alert("輸入的參數不是日期格式(yyyy-mm-dd)！");
		return false;
	}
	var d= new Date(r[1], r[3]-1, r[5]);
	if(!(d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[5]))
	{
		alert("日期不正確！");
		return false;
	}
	return true;
}

/***************************************
*時間日期的驗證(yyyy-mm-dd hh:mm:ss)*
*@param String*
*@return boolean*
***************************************/
function isDateTime(str)
{
	var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})(-|\/)(\d{1,2}) (\d{1,2})(:)(\d{1,2})(:)(\d{1,2})$/);
	if(r==null)
	{
		alert("輸入的參數不是日期格式(yyyy-mm-dd hh:mm:ss)！");
		return false;
	}
	var d= new Date(r[1], r[3]-1, r[5],r[6],r[8],r[10]);
	if(!(d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[5]&&d.getHours()==r[6]&&d.getMinutes()==r[8]&&d.getSeconds()==r[10]))
	{
		alert("日期不正確！");
		return false;
	}
	return true;
}

/*********************************************
*當鍵盤輸入時如果不是數字有關的鍵時就不讓輸入*
*@return boolean*
*********************************************/
function pressNumbersOnly()
{
	pressFomatOnly("0123456789.","numbers");
}
/*********************************************
*當鍵盤輸入時如果不是整數有關的鍵時就不讓輸入*
*@return boolean*
*********************************************/
function pressIntegerOnly()
{
	pressFomatOnly("0123456789","integer");
}
/*********************************************
*當鍵盤輸入時如果不是指定格式有關的鍵時就不讓輸入*
*@return boolean*
*********************************************/
function pressFomatOnly(fomatter,style){
var keypress;
	var keychar;
	keypress = window.event.keyCode;
	keychar = String.fromCharCode(keypress);
	if((keypress == null)||(keypress == 0)||(keypress == 8)||(keypress == 9)||(keypress == 13)||(keypress == 27))
	{
		window.status ="";
		return true;
	}
	else if((fomatter).indexOf(keychar)>-1)
	{
		window.status ="";
		return true;
	}
	else
	{
		window.event.keyCode=0;
		window.status = "Field excepts "+style+" only";
		return false;
	}
}
/*********************************************
*每個字母都大寫*
*@param String*
*@return String*
*********************************************/
function toUpperAllWord(str)
{
	var r = str;
	var m="";
	m=r.toUpperCase();
	return m;
}
/*********************************************
*每個單詞的首字母都大寫*
*@param String*
*@return String*
*********************************************/
function toUpperEachWord(str)
{
	var r = str.match(/([a-zA-Z0-9\']*[^a-zA-Z0-9\']*)/g);
	var m="";
	for(i=0;i<r.length;i++)
	{
		r[i]=r[i].substring(0,1).toUpperCase()+r[i].substring(1);
		m=m+r[i];
	}
	return m;
}
/*********************************************
*每個單詞的首字母都小寫*
*@param String*
*@return String*
*********************************************/
function toLowerEachWord(str)
{
	var r = str.match(/([a-zA-Z0-9]*[^a-zA-Z0-9]*)/g);
	var m="";
	for(i=0;i<r.length;i++)
	{
		r[i]=r[i].substring(0,1).toLowerCase()+r[i].substring(1);
		m=m+r[i];
	}
	return m;
}
/*********************************************
*段首空兩格*
*@param String*
*@return String*
*********************************************/
function twoBlankEachParagraph(str)
{
	var r = str.match(/([^\n]*[\n]*)/g);
	var m="";
	var temp = "" ;
	for(i=0;i<r.length;i++)
	{
		temp = ltrim(r[i]) ;
		temp='　　'+temp;
		temp=temp.replace(/^　{2,} */g,"　　");
		if(temp!="　　")
		{
			m=m+temp;
		}
	}
	return m;
}
/***************************************
*段間空一行 by xueduanyang
**************************************/
function addOneBlankEachParagraph(obj)
{
		obj.value=oneBlankEachParagraph(obj.value);
}
function oneBlankEachParagraph(str)
{
	str=str.replace(/[\r\n]{1,}[\s　\r\n]*/g,"\r\n\r\n");
	return str;
}
/*********************************************
 * 刪除段首前面的空格
 * @param strValue
*********************************************/
function trimTxt(strValue)
{
    str=strValue.value;
    var r=str.match(/([^\n]*[\n]*)/g);
    var m="";
    var temp = "" ;
    for(i=0;i<r.length;i++)
    {
	temp = r[i];
	temp=temp.replace(/^　*/ig,"") ;
	temp=temp.replace(/^ */ig,"") ;
	m=m+temp;
    }
    strValue.value=m;
}
/*********************************************
*每個句首的首字母都大寫*
*@param String*
*@return String*
*********************************************/
function toUpperFirstWordEachSentence(str)
{
	var r = str.match(/([^.:;!?\n]*[.:;!?\n]*)/g);
	var m="";
	var temp = "" ;
	for(i=0;i<r.length;i++)
	{
		temp = ltrim(r[i]) ;
		temp=temp.substring(0,1).toUpperCase()+temp.substring(1);
		m=m+temp
	}
	return m;
}
function replacePuncFromCnToEnForObject(obj)
{
	if(obj.value == '') return ;
	obj.value = replacePuncFromCnToEn(obj.value) ;
}
/*********************************************
*將中文標點轉換為英文標點*
*@param String*
*@return String*
*********************************************/
function replacePuncFromCnToEn(str)
{
	var replaceString= new Array(/ˋ/g,"`",/～/g,"~",/！/g,"!",/＃/g,"#",/＄/g,"$",/％/g,"%",/（/g,"(",/）/g,")",/“/g,"\"",/”/g,"\"",/；/g,";",/︰/g,":",/，/g,",",/。|．/g,".",/？/g,"?",/　/g," ",/’/g,"'",/‘/g,"'",/’/g,"'",/╱/g,"/",/〞/g,"\"",/＜/g,"<",/＞/g,">",/＊/g,"*",/＆/g,"&",/＠/g,"@",/　/g,"^",/＋/g,"+",/∣/g,"|",/╲/g,"\\",/．/g,".",/＿/g,"_",/＝/g,"=",/－/g,"-",/……/g,"...",/１/g,"1",/２/g,"2",/３/g,"3",/４/g,"4",/５/g,"5",/６/g,"6",/７/g,"7",/８/g,"8",/９/g,"9",/０/g,"0",/、/g,",",/｛/g,"{",/｝/g,"}",/〔/g,"[",/〕/g,"]",/Ａ/g,"A",/Ｂ/g,"B",/Ｃ/g,"C",/Ｄ/g,"D",/Ｅ/g,"E",/Ｆ/g,"F",/Ｇ/g,"G",/Ｈ/g,"H",/Ｉ/g,"I",/Ｊ/g,"J",/Ｋ/g,"K",/Ｌ/g,"L",/Ｍ/g,"M",/Ｎ/g,"N",/Ｏ/g,"O",/Ｐ/g,"P",/Ｑ/g,"Q",/Ｒ/g,"R",/Ｓ/g,"S",/Ｔ/g,"T",/Ｕ/g,"U",/Ｖ/g,"V",/Ｗ/g,"W",/Ｘ/g,"X",/Ｙ/g,"Y",/Ｚ/g,"Z",/ａ/g,"a",/ｂ/g,"b",/ｃ/g,"c",/ｄ/g,"d",/ｅ/g,"e",/ｆ/g,"f",/ｇ/g,"g",/ｈ/g,"h",/ｉ/g,"i",/ｊ/g,"j",/ｋ/g,"k",/ｌ/g,"l",/ｍ/g,"m",/ｎ/g,"n",/ｏ/g,"o",/ｐ/g,"p",/ｑ/g,"q",/ｒ/g,"r",/ｓ/g,"s",/ｔ/g,"t",/ｕ/g,"u",/ｖ/g,"v",/ｗ/g,"w",/ｘ/g,"x",/ｙ/g,"y",/ｚ/g,"z");
	for(i=0;i<replaceString.length;i=i+2)
	{
		str=str.replace(replaceString[i],replaceString[i+1]);
	}
	return str;
}
/*********************************************
*將；︰、標點轉換為英文標點*
*@param String*
*@return String*
*********************************************/
function replaceCommaFromCnPunctuation(str)
{
	var replaceString= new Array(/；/g,",",/︰/g,",",/、/g,",",/，/g,",",/;/g,",",/:/g,",");
	for(i=0;i<replaceString.length;i=i+2)
	{
		str=str.replace(replaceString[i],replaceString[i+1]);
	}
	return str;
}
/*********************************************
*將標點符號後加空格*
*@param String*
*@return String*
*********************************************/
function addPuncAfterBlank(str)
{
	var m="";
	m=str.replace(/ {0,}\./g,". ");
	m=m.replace(/ {0,},/g,", ");
	m=m.replace(/ {0,}:/g,": ");
	m=m.replace(/ {0,};/g,"; ");
	m=m.replace(/ {0,}!/g,"! ");
	m=m.replace(/ {0,}\?/g,"? ");
	m=m.replace(/ {2,}/g," ");
	m=m.replace(/(\. ){2,}/g,"...");
	//判斷如果出現以小數，則不需要標點後加空格
	var r = m.match(/([0-9]\. [0-9])(\. [0-9])*/g);
	var temp = "" ;
	if(r!=null)
	{
		for(i=0;i<r.length;i++)
		{
			temp=r[i].replace(/\. /g,".");
			m=m.replace(r[i],temp);		}
	}
	return m;
}
/**
 * 刪除字符前面的空格
 * @param strValue
 */
function ltrim(strValue)
{
    if(strValue.length == 0)
    {
		return ("");
    }
    strValue = strValue.replace(/^( )*/ig,"") ;
    return strValue;
}

function doAutoUpperEachWordAndPuncAfterBlankForObjects(objects)
{
	for(var i=0;i<objects.length;i++)
	{
		with(document.all)
		{
			var obj = eval(objects[i]);
			var va1=addPuncAfterBlank(obj.value);
			var va2=toLowerForAllWord(va1);
			obj.value=toUpperEachWord(va2);
		}
	}
}

function doAutoLowerEachWord(objects)
{
	for(var i=0;i<objects.length;i++)
	{
	with(document.all)
	{
			var obj = eval(objects[i]) ;
			toLowerForAllWordForObject(obj);
	}
	}
}
//英文標點轉中文標點
function doPuncEnToCnForObject(obj)
{
		var val = obj.value;
		var replaceString= new Array(/`/g,"ˋ",/~/g,"～",/!/g,"！",/#/g,"＃",/\$/g,"＄",/%/g,"％",/\(/g,"（",/\)/g,"）",/;/g,"；",/:/g,"︰",/,/g,"，",/\.|．/g,"。",/\?/g,"？",/ /g,"　",/'/g,"’",/'/g,"‘",/'/g,"’",/\//g,"╱",/</g,"＜",/>/g,"＞",/\*/g,"＊",/&/g,"＆",/@/g,"＠",/\^/g,"　",/\+/g,"＋",/\|/g,"∣",/=/g,"＝",/,/g,"、",/\{/g,"｛",/\}/g,"｝",/\[/g,"〔",/\]/g,"〕",/１/g,"1",/２/g,"2",/３/g,"3",/４/g,"4",/５/g,"5",/６/g,"6",/７/g,"7",/８/g,"8",/９/g,"9",/０/g,"0");
		for(var i=0;i<replaceString.length;i=i+2)
		{
			val=val.replace(replaceString[i],replaceString[i+1]);
		}
		//如果中文中存在小數，如1.1，則不作替換
		var r = val.match(/([0-9]。[0-9])(。[0-9])*/g);
		var temp = "" ;
		if(r!=null)
		{
			for(i=0;i<r.length;i++)
			{
				temp=r[i].replace(/。/g,".");
				val=val.replace(r[i],temp);
			}
		}
		obj.value=val;
}
function getHeightForTestarea(obj,intWidth,intDefault,lan)
{
	var strValue=obj.value;
	var valArr=strValue.split("\n");
	var line=valArr.length;
	for(var i=0;i<valArr.length;i++)
	{
		if(lan=="0")
		{
		var over_line=parseInt(valArr[i].length/intWidth, 10)+1;
		}
		else
		{
		var over_line=parseInt(valArr[i].length/intWidth*2, 10)+1;
		}
		if(over_line>1)
		line=line+over_line-1;
	}
	line=line+3;
	if(line<intDefault)
	{
		line=intDefault;
	}
	obj.rows=line;
}
function toHtmlDispaly(obj)
{
	var strValue=obj.value;
	//strValue= strValue.replace(/&/g, "&amp;");
	//strValue= strValue.replace(/'/, "&apos;");
	//strValue= strValue.replace(/\"/g, "&quot;");
	//strValue= strValue.replace(/</g, "&lt;");
	//strValue= strValue.replace(/>/g, "&gt;");
	strValue=strValue.replace(/\n/g,"<br>");
	strValue=strValue.replace(/  /g," &nbsp;");
	document.all.view_effect.innerHTML=strValue;
}

function dispalyObjToHtml(obj,toObj)
{
	var strValue=obj.value;
	strValue=strValue.replace(/\n/g,"<br>");
	strValue=strValue.replace(/  /g," &nbsp;");
	toObj.innerHTML=strValue;
}
/**
 * 計算字符串長度
 */
function getByteLength(strValue)
{
  len = 0;

  for(var i = 0; i < strValue.length; i++){
    chrcode=strValue.charCodeAt(i);
    if ((chrcode < 126) || (chrcode > 65376 && chrcode < 65440)){
      len++;
    }else{
      len += 2;
    }
  }
  return len;
}
function trim(strValue)
{
	if(strValue.length == 0)
    {
		return ("");
    }
    strValue = strValue.replace(/^( )*|( )*$/ig,"") ;
    return strValue;
}
function isOverLength(obj ,length, display)
{
	var val = obj.value ;
	val = trim(val) ;
    if(getByteLength(val) > length)
    {
        alert(display + "長度不能超過" + (length/2) + "個漢字或" + length + "個字母.") ;
        obj.focus() ;
        return true ;
    }
    return false ;
}
/**
 * 設置幀.
 */
function setFrameRows(rowstr)
{
	if (typeof(parent.frmindex1)!="undefined")
	{
		if("50%,50%" == rowstr)
		{
			setDefaultFrameRows();
		}
		else if ("100%,*" == rowstr)
		{
			parent.frmindex1.rows="98%,2%,*";
		}
		else if ("*,100%" == rowstr)
		{
			parent.frmindex1.rows="*,2%,98%";
		}
		else
		{
			parent.frmindex1.rows=rowstr;
		}

	}
}
/**
 * 設置幀.
 */
function setDefaultFrameRows()
{
	if (typeof(parent.frmindex1)!="undefined")
	{
		parent.frmindex1.rows="49%,2%,49%";
	}
}
/**
 *插入特殊字符
 */
function doAddSpecialChar(obj)
{
  var objwin=window.open("/js/spechar.htm","swindow","height=500,width=500,resizable=yes,scrollbars=yes,menubar=no,status=yes");
}
/**
 *打開選擇類別頁面
 */
function openCatalogWindow(url, value) {
    if (window.replaceCatalogWindowUrl) {
        url = replaceCatalogWindowUrl(url);
    }
    var opener = window.open(url + value);
}

function charPYStr(){
return '啊阿埃挨哎唉哀皚癌藹矮艾礙愛隘鞍氨安俺按暗岸胺案骯昂盎凹敖熬翱襖傲奧懊澳芭捌扒叭吧笆八疤巴拔跋靶把耙壩霸罷爸白柏百擺佰敗拜稗斑班搬扳般頒板版扮拌伴瓣半辦絆邦幫梆榜膀綁棒磅蚌鎊傍謗苞胞包褒剝薄雹保堡飽寶抱報暴豹鮑爆杯碑悲卑北輩背貝鋇倍狽備憊焙被奔苯本笨崩繃甭泵蹦迸逼鼻比鄙筆彼碧蓖蔽畢斃毖幣庇痹閉敝弊必闢壁臂避陛鞭邊編貶扁便變卞辨辯辮遍標彪膘表鱉憋別癟彬斌瀕濱賓擯兵冰柄丙秉餅炳病並玻菠播撥缽波博勃搏鉑箔伯帛舶脖膊渤泊駁捕卜哺補埠不布步簿部怖擦猜裁材才財睬踩采彩菜蔡餐參蠶殘慚慘燦蒼艙倉滄藏操糙槽曹草廁策側冊測層蹭插叉茬茶查碴搽察岔差詫拆柴豺攙摻蟬饞讒纏鏟產闡顫昌猖場嘗常長償腸廠敞暢唱倡超抄鈔朝嘲潮巢吵炒車扯撤掣徹澈郴臣辰塵晨忱沉陳趁襯撐稱城橙成呈乘程懲澄誠承逞騁秤吃痴持匙池遲弛馳恥齒侈尺赤翅斥熾充沖蟲崇寵抽酬疇躊稠愁籌仇綢瞅丑臭初出櫥廚躇鋤雛滁除楚礎儲矗搐觸處揣川穿椽傳船喘串瘡窗幢床闖創吹炊捶錘垂春椿醇唇淳純蠢戳綽疵茨磁雌辭慈瓷詞此刺賜次聰蔥囪匆從叢湊粗醋簇促躥篡竄摧崔催脆瘁粹淬翠村存寸磋撮搓措挫錯搭達答瘩打大呆歹傣戴帶殆代貸袋待逮怠耽擔丹單鄲撢膽旦氮但憚淡誕彈蛋當擋黨蕩檔刀搗蹈倒島禱導到稻悼道盜德得的蹬燈登等瞪凳鄧堤低滴迪敵笛狄滌翟嫡抵底地蒂第帝弟遞締顛掂滇碘點典靛墊電佃甸店惦奠澱殿碉叼雕凋刁掉吊釣調跌爹碟蝶迭諜疊丁盯叮釘頂鼎錠定訂丟東冬董懂動棟侗恫凍洞兜抖斗陡豆逗痘都督毒犢獨讀堵睹賭杜鍍肚度渡妒端短鍛段斷緞堆兌隊對墩噸蹲敦頓囤鈍盾遁掇哆多奪垛躲朵跺舵剁惰墮蛾峨鵝俄額訛娥惡厄扼遏鄂餓恩而兒耳爾餌洱二貳發罰筏伐乏閥法琺藩帆番翻樊礬釩繁凡煩反返範販犯飯泛坊芳方肪房防妨仿訪紡放菲非啡飛肥匪誹吠肺廢沸費芬酚吩氛分紛墳焚汾粉奮份忿憤糞豐封楓蜂峰鋒風瘋烽逢馮縫諷奉鳳佛否夫敷膚孵扶拂輻幅氟符伏俘服浮涪福袱弗甫撫輔俯釜斧脯腑府腐赴副覆賦復傅付阜父腹負富訃附婦縛咐噶嘎該改概鈣蓋溉干甘桿柑竿肝趕感稈敢贛岡剛鋼缸肛綱崗港杠篙皋高膏羔糕搞鎬稿告哥歌擱戈鴿胳疙割革葛格蛤閣隔鉻個各給根跟耕更庚羹埂耿梗工攻功恭龔供躬公宮弓鞏汞拱貢共鉤勾溝苟狗垢構購夠辜菇咕箍估沽孤姑鼓古蠱骨谷股故顧固雇刮瓜剮寡掛褂乖拐怪棺關官冠觀管館罐慣灌貫光廣逛瑰規圭　歸龜閨軌鬼詭癸桂櫃跪貴劊輥滾棍鍋郭國果裹過哈骸孩海氦亥害駭酣憨邯韓含涵寒函喊罕翰撼捍旱憾悍焊汗漢夯杭航壕嚎豪毫郝好耗號浩呵喝荷渮核禾和何合盒貉閡河涸赫褐鶴賀嘿黑痕很狠恨哼亨橫衡恆轟哄烘虹鴻洪宏弘紅喉侯猴吼厚候後呼乎忽瑚壺葫胡蝴狐糊湖弧虎唬護互滬戶花嘩華猾滑畫劃化話槐徊懷淮壞歡環桓還緩換患喚瘓豢煥渙宦幻荒慌黃磺蝗簧皇凰惶煌晃幌恍謊灰揮輝徽恢蛔回毀悔慧卉惠晦賄穢會燴匯諱誨繪葷昏婚魂渾混豁活伙火獲或惑霍貨禍擊圾基機畸稽積箕肌饑跡激譏雞姬績緝吉極棘輯籍集及急疾汲即嫉級擠幾脊己薊技冀季伎祭劑悸濟寄寂計記既忌際妓繼紀嘉枷夾佳家加莢頰賈甲鉀假稼價架駕嫁殲監堅尖箋間煎兼肩艱奸緘繭檢柬堿鹼揀撿簡儉剪減薦檻鑒踐賤見鍵箭件健艦劍餞漸濺澗建僵姜將漿江疆蔣槳獎講匠醬降蕉椒礁焦膠交郊澆驕嬌嚼攪鉸矯僥腳狡角餃繳絞剿教酵轎較叫窖揭接皆秸街階截劫節睫楮晶鯨京驚精粳經井警景頸靜境敬鏡徑痙靖竟競淨炯窘揪究糾玖韭久灸九酒廄救舊臼舅咎就疚鞠拘狙疽居駒菊局咀矩舉沮聚拒據巨具距踞鋸俱句懼炬劇捐鵑娟倦眷卷絹撅攫抉掘倔爵桔杰捷睫竭潔結解姐戒藉芥界借介疥誡屆巾筋斤金今津襟緊錦僅謹進靳晉禁近燼浸盡勁荊兢覺決訣絕均菌鈞軍君峻俊竣浚郡駿喀咖卡咯開揩楷凱慨刊堪勘坎砍看康慷糠扛抗亢炕考拷烤靠坷苛柯棵磕顆科殼咳可渴克刻客課肯啃墾懇坑吭空恐孔控摳口扣寇枯哭窟苦酷庫褲夸垮挎跨胯塊筷儈快寬款匡筐狂框礦眶曠況虧盔巋窺葵奎魁傀饋愧潰坤昆捆困括擴廓闊垃拉喇蠟臘辣啦萊來賴藍婪欄攔籃闌蘭瀾讕攬覽懶纜爛濫瑯榔狼廊郎朗浪撈勞牢老佬姥酪烙澇勒樂雷鐳蕾磊累儡壘擂肋類淚稜楞冷厘梨犁黎籬狸離灕理李里鯉禮莉荔吏栗麗厲勵礫歷利　例俐痢立粒瀝隸力璃哩倆聯蓮連鐮廉憐漣簾斂臉鏈戀煉練糧涼梁粱良兩輛量晾亮諒撩聊僚療燎寥遼潦了撂鐐廖料列裂烈劣獵琳林磷霖臨鄰鱗淋凜賃吝拎玲菱零齡鈴伶羚凌靈陵嶺領另令溜琉榴硫餾留劉瘤流柳六龍聾嚨籠窿隆壟攏隴樓婁摟簍漏陋蘆盧顱廬爐擄鹵虜魯麓碌露路賂鹿潞祿錄陸戮驢呂鋁侶旅履屢縷慮氯律率濾綠巒攣孿灤卵亂掠略掄輪倫侖淪綸論蘿螺羅邏鑼籮騾裸落洛駱絡媽麻瑪碼螞馬罵嘛嗎埋買麥賣邁脈瞞饅蠻滿蔓曼慢漫謾芒茫盲氓忙莽貓茅錨毛矛鉚卯茂冒帽貌貿麼玫枚梅黴霉煤沒眉媒鎂每美昧寐妹媚門悶們萌蒙檬盟錳猛夢孟眯醚靡糜迷謎彌米秘覓泌蜜密冪棉眠綿冕免勉娩緬面苗描瞄藐秒渺廟妙蔑滅民抿皿敏憫閩明螟鳴銘名命謬摸摹蘑模膜磨摩魔抹末莫墨默沫漠寞陌謀牟某拇牡畝姆母墓暮幕募慕木目睦牧穆拿哪吶鈉那娜納氖乃奶耐奈南男難囊撓腦惱鬧淖呢餒內嫩能妮霓倪泥尼擬你匿膩逆溺蔫拈年碾攆捻念娘釀鳥尿捏聶孽嚙鑷鎳涅您檸獰凝寧擰濘牛扭鈕紐膿濃農弄奴努怒女暖虐瘧挪懦糯諾哦歐鷗毆藕嘔偶漚啪趴爬帕怕琶拍排牌徘湃派攀潘盤磐盼畔判叛乓龐旁耪胖拋咆刨炮袍跑泡呸胚培裴賠陪配佩沛噴盆砰抨烹澎彭蓬棚硼篷膨朋鵬捧踫坯砒霹批披劈琵毗啤脾疲皮匹痞僻屁譬篇偏片騙飄漂瓢票撇瞥拼頻貧品聘乒坪隻萍平憑瓶評屏坡潑頗婆破魄迫粕剖撲鋪僕莆葡菩蒲埔樸圃普浦譜曝瀑期欺棲戚妻七淒漆柒沏其棋奇歧畦崎臍齊旗祈祁騎起豈乞企啟契砌器氣迄棄汽泣訖掐洽牽扦　鉛千遷簽仟謙乾黔錢鉗前潛遣淺譴塹嵌欠歉槍嗆腔羌牆薔強搶橇鍬敲悄橋瞧喬僑巧鞘撬翹峭俏竅切茄且怯竊欽侵親秦琴勤芹擒禽寢沁青輕氫傾卿清擎晴氰情頃請慶瓊窮秋丘邱球求囚酋泅趨區蛆曲軀屈驅渠取娶齲趣去圈顴權醛泉全痊拳犬券勸缺炔瘸卻鵲榷確雀裙群然燃冉染瓤壤攘嚷讓饒擾繞惹熱壬仁人忍韌任認刃妊紉扔仍日戎茸蓉榮融熔溶容絨冗揉柔肉茹蠕儒孺如辱乳汝入褥軟阮蕊瑞銳閏潤若弱撒灑薩腮鰓塞賽三參傘散桑嗓喪搔騷掃嫂瑟色澀森僧莎砂殺剎沙紗傻啥煞篩曬珊苫杉山刪煽衫閃陝擅贍膳善汕扇繕墑傷商賞晌上尚裳梢捎稍燒芍勺韶少哨邵紹奢賒蛇舌舍赦攝射懾涉社設砷申呻伸身深娠紳神沈審嬸甚腎慎滲聲生甥牲升繩省盛剩勝聖師失獅施濕詩尸虱十石拾時什食蝕實識史矢使屎駛始式示士世柿事拭誓逝勢是嗜噬適仕侍釋飾氏市恃室視試收手首守壽授售受瘦獸蔬樞梳殊抒輸叔舒淑疏書贖孰熟薯暑曙署蜀黍鼠屬術述樹束戍豎墅庶數漱恕刷耍摔衰甩帥栓拴霜雙爽誰水睡稅吮瞬順舜說碩朔爍斯撕嘶思私司絲死肆寺嗣四伺似飼巳松聳慫頌送宋訟誦搜艘擻嗽蘇酥俗素速粟僳塑溯宿訴肅酸蒜算雖隋隨綏髓碎歲穗遂隧祟孫損筍簑梭唆縮瑣索鎖所塌他它她塔獺撻蹋踏胎苔抬台泰　太態汰坍攤貪癱灘壇檀痰潭譚談坦毯袒碳探嘆炭湯塘搪堂棠膛唐糖倘躺淌趟燙掏濤滔絛萄桃逃淘陶討套特藤騰疼謄梯剔踢銻提題蹄啼體替嚏惕涕剃屜天添填田甜恬舔腆挑條迢眺跳貼鐵帖廳听烴汀廷停亭庭挺艇通桐酮瞳同銅彤童桶捅筒統痛偷投頭透凸禿突圖徒途涂屠土吐兔湍團推頹腿蛻褪退吞屯臀拖托脫鴕陀馱駝橢妥拓唾挖哇蛙窪娃瓦襪歪外豌彎灣玩頑丸烷完碗挽晚皖惋宛婉萬腕汪王亡枉網往旺望忘妄威巍微危韋違桅圍唯惟為濰維葦萎委偉偽尾緯未蔚味畏胃喂魏位渭謂尉慰衛瘟溫蚊文聞紋吻穩紊問嗡翁甕撾蝸渦窩我斡臥握沃巫嗚鎢烏污誣屋無蕪梧吾吳毋武五捂午舞伍侮塢戊霧晤物勿務悟誤昔熙析西硒矽晰嘻吸錫犧稀息希悉膝夕惜熄烯溪汐犀檄襲席習媳喜銑洗系隙戲細瞎蝦匣霞轄暇峽俠狹下廈夏嚇掀杴先仙鮮縴咸賢餃舷閑涎弦嫌顯險現獻縣腺餡羨憲陷限線相廂瓖香箱襄湘鄉翔祥詳想響享項巷橡像向象蕭硝霄削哮囂銷消宵淆曉小孝校肖嘯笑效楔些歇蠍鞋協挾攜邪斜脅諧寫械卸蟹懈泄瀉謝屑薪芯鋅欣辛新忻心信釁星腥猩惺興刑型形邢行醒幸杏性姓兄凶胸匈洶雄熊休修羞朽嗅?秀袖繡墟戌需虛噓須徐許蓄酗敘旭序畜恤絮婿緒續軒喧宣懸旋玄選癬眩絢靴薛學穴雪血勛燻循旬詢尋馴巡殉汛訓訊遜迅壓押鴉鴨呀丫芽牙蚜崖衙涯雅啞亞訝焉咽閹煙淹鹽嚴研蜒岩延言顏閻炎沿奄掩眼衍演艷堰燕厭硯雁唁彥焰宴諺驗殃央鴦秧楊揚佯瘍羊洋陽氧仰癢養樣漾邀腰妖瑤搖堯遙窯謠姚咬舀藥要耀椰噎耶爺野冶也頁掖業葉曳腋夜液一壹醫揖銥依伊衣頤夷遺移儀胰疑沂宜姨彝椅蟻倚已乙矣以藝抑易邑屹億役臆逸肄疫亦裔意毅憶義益溢詣議誼譯異翼翌繹茵蔭因殷音陰姻吟銀淫寅飲尹引隱印英櫻嬰鷹應纓瑩螢營熒蠅迎贏盈影穎硬映喲擁佣臃癰庸雍踴蛹詠泳涌永恿勇用幽優悠憂尤由郵鈾猶油游酉有友右佑釉誘又幼迂淤于盂榆虞愚輿余俞逾魚愉渝漁隅予娛雨與嶼禹宇語羽玉域芋郁吁遇喻峪御愈欲獄育譽浴寓裕預豫馭鴛淵冤元垣袁原援轅園員圓猿源緣遠苑願怨院曰約越躍鑰岳粵月悅閱耘雲鄖勻隕允運蘊醞暈韻孕匝砸雜栽哉災宰載再在咱攢暫贊贓髒葬遭糟鑿藻棗早澡蚤躁噪造皂灶燥責擇則澤賊怎增憎曾贈扎喳渣札軋鍘閘眨柵榨咋乍炸詐摘齋宅窄債寨瞻氈詹粘沾盞斬輾嶄展蘸棧佔戰站湛綻樟章彰漳張掌漲杖丈帳賬仗脹瘴障招昭找沼趙照罩兆肇召遮折哲蟄轍者鍺蔗這浙珍斟真甄砧臻貞針偵枕疹診震振鎮陣蒸掙睜征猙爭怔整拯正政幀癥鄭證芝枝支吱蜘知肢脂汁之織職直植殖執值佷址指止趾只旨紙志摯擲至致置幟峙制智秩稚質炙痔滯治窒中盅忠鐘衷終種腫重仲眾舟周州洲謅粥軸肘帚咒皺宙晝驟珠株蛛朱豬諸誅逐竹燭煮拄矚囑主著柱助蛀貯鑄築住注祝駐抓爪拽專磚轉撰賺篆樁莊裝妝撞壯狀椎錐追贅墜綴諄準捉拙卓桌琢茁酌啄著灼濁茲咨資姿滋淄孜紫仔籽滓子自漬字鬃棕蹤宗綜總縱鄒走奏揍租足卒族祖詛阻組鑽纂嘴醉最罪尊遵昨左佐柞做作坐座制產台歷布于系涇余準佔采為寧顏托表沖匯眾升注致咨麼周回志';
}

function ftPYStr(){
return '啊阿埃挨哎唉哀  癌  矮艾  --隘鞍氨安俺按暗岸胺案骯昂盎凹敖熬    傲--懊澳芭捌扒叭吧笆八疤巴拔跋靶把耙--霸  爸白柏百--佰--拜稗斑班搬扳般  板版扮拌伴瓣半    邦--梆榜膀  棒磅蚌  傍  苞胞包褒--薄雹保堡  --抱--暴豹  爆杯碑悲卑北  背    倍  ----焙被奔苯本笨崩  甭泵蹦迸逼鼻比鄙  彼碧蓖蔽  --毖--庇痹  敝弊必闢壁臂避陛鞭      扁便  卞辨    遍--彪膘表  憋--  彬斌----  --兵冰柄丙秉  炳病--玻菠播--  波博勃搏  箔伯帛舶脖膊渤泊  捕  哺  埠不布步簿部怖擦猜裁材才  睬踩采彩菜蔡餐--  ------      ----藏操糙槽曹草--策--------蹭插叉茬茶查碴搽察岔差  拆柴豺----                昌猖----常  --  --敞--唱倡超抄  朝嘲潮巢吵炒  扯撤掣--澈郴臣辰--晨忱沈  趁  --  城橙成呈乘程--澄  承逞  秤吃  持匙池  弛  --  侈尺赤翅斥--充--  崇--抽酬    稠愁  仇  瞅  臭初出----躇    滁除楚  --矗搐    揣川穿椽--船喘串  窗幢床  --吹炊捶  垂春椿醇唇淳  蠢戳  疵茨磁雌  慈瓷  此刺  次    --匆------粗醋簇促  篡  摧崔催脆瘁粹淬翠村存寸磋撮搓措挫  搭  答瘩打大呆歹傣戴--殆代  袋待逮怠耽--丹--  --  旦氮但--淡  --蛋  --    --刀--蹈倒--  --到稻悼道  德得的蹬--登等瞪凳  堤低滴迪--笛狄--翟嫡抵底地蒂第帝弟      掂滇碘  典靛--  佃甸店惦奠--殿碉叼雕凋刁掉吊    跌爹碟蝶疊    丁盯叮    鼎  定  ----冬董懂----侗恫--洞兜抖  陡豆逗痘都督毒      堵睹  杜  肚度渡妒端短  段--  堆--  --墩--蹲敦  囤  盾遁掇哆多--垛躲朵跺舵剁惰--蛾峨  俄    娥--厄扼遏鄂  恩而--耳    洱二      筏伐乏  法  藩帆番翻樊    繁凡--反返    犯  泛坊芳方肪房防妨仿    放菲非啡  肥匪  吠肺--沸  芬酚吩氛分  --焚汾粉--份忿--    封--蜂峰      烽逢      奉  佛否夫敷  孵扶拂  幅氟符伏俘服浮涪福袱弗甫--  俯釜斧脯腑府腐赴副覆    傅付阜父腹  富  附--  咐噶嘎  改概    溉--甘桿柑竿肝  感  敢  ----  缸肛  --港杠篙  高膏羔糕搞  稿告哥歌--戈  胳疙割革葛格蛤  隔  --各  根跟耕更庚羹埂耿梗工攻功恭  供躬公--弓  汞拱  共  勾--苟狗垢--  --辜菇咕箍估沽孤姑鼓古  骨谷股故  固雇刮瓜--寡掛褂乖拐怪棺  官冠  管  罐--灌  光--逛瑰  圭矽--      鬼  癸桂--跪  --  --棍  郭--果裹  哈骸孩海氦亥害  酣憨邯  含涵寒函喊罕翰撼捍旱憾悍焊汗--夯杭航壕嚎豪毫郝好耗  浩呵喝荷渮核禾和何合盒貉  河涸赫褐    嘿黑痕很狠恨哼亨--衡恆  哄烘虹  洪宏弘  喉侯猴吼厚候後呼乎忽瑚--葫胡蝴狐糊湖弧虎唬  互----花--  猾滑  --化  槐徊--淮----  桓    --患--  豢----宦幻荒慌  磺蝗簧皇凰惶煌晃幌恍  灰--  徽恢蛔回--悔慧卉惠晦    --  --        昏婚魂--混豁活夥火  或惑霍    --圾基--畸稽  箕肌  跡激    姬    吉--棘  籍集及急疾汲即嫉  ----脊己  技冀季伎祭--悸--寄寂    既忌  妓    嘉枷--佳家加      甲  假稼--架  嫁--  --尖    煎兼肩  奸    --柬--  ----  --剪--  --          箭件健  --  ------建僵姜----江疆  --    匠  降蕉椒礁焦  交郊--  --嚼--    --  狡角      剿教酵    叫窖揭接皆  街  截劫    楮晶  京  精粳  井警景    境敬  --  靖竟  --炯窘揪究  玖韭久灸九酒--救  臼舅咎就疚鞠拘狙疽居  菊局咀矩  沮聚拒--巨具距踞  俱句--炬--捐  娟倦眷卷  撅攫抉掘倔爵桔--捷睫竭--  解姐戒藉芥界借介疥  --巾筋斤金今津襟    --    靳--禁近  浸  --  兢  --    均菌    君峻俊竣浚郡  喀咖卡咯  揩楷--慨刊堪勘坎砍看康慷糠扛抗亢炕考拷烤靠坷苛柯棵磕  科--咳可渴克刻客  肯啃----坑吭空恐孔控--口扣寇枯哭窟苦酷--    垮挎跨胯--筷--快--款匡筐狂框  眶----  盔--  葵奎魁傀  愧--坤昆捆困括--廓  垃拉喇    辣啦  --    婪----      --  --  --    --瑯榔狼廊郎朗浪----牢老佬姥酪烙--勒--雷  蕾磊累儡--擂肋  --稜楞冷厘梨犁黎  狸  灕理李      莉荔吏栗  ----  --利　例俐痢立粒--  力璃哩--        廉----  --    ----    --梁粱良--  量晾亮  撩聊僚  燎寥  潦了撂  廖料列裂烈劣  琳林磷霖      淋--  吝拎玲菱零    伶羚--  陵--  另令溜琉榴硫  留--瘤流柳六    --  窿隆----  ------  漏陋      --  --      麓碌露路  鹿潞      戮  --  --旅履--  --氯律率--  --------卵--掠略--  ------      螺          裸落洛    --麻          嘛--埋                --蔓曼慢漫  芒茫盲氓忙莽  茅  毛矛  卯茂冒帽貌  麼玫枚梅黴  煤--眉媒  每美昧寐妹媚  ----萌蒙檬盟  猛--孟眯醚靡糜迷  --米秘  泌蜜密--棉眠  冕免勉娩  面苗描瞄藐秒渺--妙蔑--民抿皿敏--  明螟    名命  摸摹蘑模膜磨摩魔抹末莫墨默沫漠寞陌  牟某拇牡  姆母墓暮幕募慕木目睦牧穆拿哪吶  那娜  氖乃奶耐奈南男  囊--  --  淖呢  --嫩能妮霓倪泥尼--你匿  逆溺蔫拈年碾----念娘    尿捏  孽      涅您--  凝甯----牛扭      --  弄奴努怒女暖虐  挪懦糯  哦--  --藕--偶--啪趴爬帕怕琶拍排牌徘湃派攀潘  磐盼畔判叛乓  旁耪胖拋咆刨炮袍跑泡呸胚培裴  陪配佩沛--盆砰抨烹澎彭蓬棚硼篷膨朋  捧踫坯砒霹批披劈琵毗啤脾疲皮匹痞僻屁譬篇偏片    漂瓢票撇瞥拼    品聘乒坪  萍平--瓶  屏坡--  婆破魄迫粕剖--  僕莆葡菩蒲埔--圃普浦  曝瀑期欺--戚妻七--漆柒沏其棋奇歧畦崎    旗祈祁  起  乞企--契砌器--迄--汽泣  掐洽  --    千    仟  乾黔    前--遣--  --嵌欠歉----腔羌    ----橇  敲悄--瞧----巧鞘撬  峭俏  切茄且怯  --侵  秦琴勤芹擒禽--沁青  ----卿清擎晴氰情    --    秋丘邱球求囚酋泅  --蛆曲  屈  渠取娶  趣去圈  --醛泉全痊拳犬券--缺炔瘸--  榷  雀裙群然燃冉染瓤壤攘嚷    --  惹--壬仁人忍  任  刃妊  扔仍日戎茸蓉--融熔溶容  冗揉柔肉茹蠕儒孺如辱乳汝入褥  阮蕊瑞    --若弱撒--  腮  塞  三三--散桑嗓--搔  --嫂瑟色--森僧莎砂--剎沙  傻啥煞  --珊苫杉山--煽衫    擅  膳善汕扇  墑--商  晌上尚裳梢捎稍--芍勺韶少哨邵  奢  蛇舌舍赦--射--涉社  砷申呻伸身深娠  神沈----甚  慎--  生甥牲升  省盛剩--  --失  施--  --虱十石拾--什食  --  史矢使屎  始式示士世柿事拭誓逝--是嗜噬  仕侍    氏市恃室    收手首守--授售受瘦  蔬--梳殊抒  叔舒淑疏--  孰熟薯暑曙署蜀黍鼠--  述--束戍  墅庶--漱恕刷耍摔衰甩--栓拴霜  爽  水睡  吮瞬  舜    朔  斯撕嘶思私司  死肆寺嗣四伺似  巳松  --  送宋    搜艘--嗽  酥俗素速粟僳塑溯宿    酸蒜算  隋    髓碎--穗遂隧祟----  簑梭唆    索  所塌他它她塔  --蹋踏胎苔--台泰　太--汰坍--    ----檀痰潭    坦毯袒碳探--炭--塘搪堂棠膛唐糖倘躺淌趟  掏--滔  萄桃逃淘陶  套特藤  疼  梯剔踢  提  蹄啼  替嚏惕涕剃--天添填田甜恬舔腆挑--迢眺跳    帖--  --汀廷停亭庭挺艇通桐酮瞳同  彤童桶捅筒  痛偷投  透凸  突--徒途--屠土吐兔湍--推  腿  褪退吞屯臀拖托    陀    --妥拓唾挖哇蛙  娃瓦  歪外豌----玩  丸烷完碗挽晚皖惋宛婉  腕汪王亡枉  往旺望忘妄威巍微危    桅--唯惟  --    萎委----尾  未蔚味畏胃喂魏位渭  尉慰  瘟--蚊文    吻  紊--嗡翁  --  --  我斡  握沃巫--  ----  屋--  梧吾--毋武五捂午舞伍侮--戊  晤物勿--悟  昔熙析西硒矽晰嘻吸    稀息希悉膝夕惜熄烯溪汐犀檄  席  媳喜  洗系隙--  瞎  匣霞  暇----  下--夏--掀  先仙          舷  涎弦嫌          腺    --陷限  相--  香箱襄湘  翔祥  想  享  巷橡像向象  硝霄削哮--  消宵淆--小孝校肖--笑效楔些歇  鞋------邪斜    --械卸蟹懈泄--  屑薪芯  欣辛新忻心信  星腥猩惺  刑型形邢行醒幸杏性姓兄凶胸匈--雄熊休修羞朽嗅  秀袖  墟戌需  --  徐  蓄酗--旭序畜恤絮婿      喧宣--旋玄    眩  靴薛--穴雪血--燻循旬  --  巡殉汛      迅--押    呀丫芽牙蚜崖衙涯雅----  焉咽  --淹  --研蜒岩延言    炎沿奄掩眼衍演  堰燕--  雁唁--焰宴    殃央  秧----佯  羊洋  氧仰    --漾邀腰妖  ----      姚咬舀  要耀椰噎耶  野冶也  掖--  曳腋夜液一壹  揖  依伊衣  夷  移--胰疑沂宜姨--椅  倚已乙矣以  抑易邑屹--役臆逸肄疫亦裔意毅--  益溢          翼翌  茵  因殷音  姻吟  淫寅  尹引  印英----  --        --  迎  盈影  硬映------臃  庸雍  蛹  泳--永恿勇用幽--悠--尤由      油  酉有友右佑釉  又幼迂淤于盂榆虞愚  余俞逾  愉渝--隅予--雨  --禹宇  羽玉域芋郁  遇喻峪  愈欲  育  浴寓裕  豫    --冤元垣袁原援  ------猿源    苑  怨院曰  越    --  月--  耘    --  允      --  孕匝砸  栽哉--宰  再在咱----      葬遭糟  藻--早澡蚤躁噪造皂  燥  ------  怎增憎曾    喳渣--      眨--榨咋乍炸  摘  宅窄--寨瞻--詹粘沾  --  --展蘸--佔--站湛  樟章彰漳--掌--杖丈--  仗  瘴障招昭找沼  照罩兆肇召遮折哲    者  蔗  浙珍斟真甄砧臻    --枕疹  震振    蒸--  征    怔整拯正政--癥    芝枝支吱蜘知肢脂汁之    直植殖--值佷址指止趾只旨  志----至致置--峙制智秩稚  炙痔--治窒中盅忠  衷      重仲  舟周州洲  粥  肘帚咒  宙--  珠株蛛朱      逐竹  煮拄  --主著柱助蛀      住注祝  抓爪拽--    撰  篆--    --撞--  椎  追  --    準捉拙卓桌琢茁酌啄著灼--  咨  姿滋淄孜紫仔籽滓子自--字鬃棕  宗        走奏揍租足卒族祖  阻    纂嘴醉最罪尊遵昨左佐柞做作坐座      ----於----  ----------        --                  ';
}

function traditionalized(cc){
	var str='';
	for(var i=0;i<cc.length;i++){
		if(charPYStr().indexOf(cc.charAt(i))!=-1)
			str+=ftPYStr().charAt(charPYStr().indexOf(cc.charAt(i)));
		else
			str+=cc.charAt(i);
	}
	return str;
}

function simplized(cc){
	var str='';
	for(var i=0;i<cc.length;i++){
		if(ftPYStr().indexOf(cc.charAt(i))!=-1)
			str+=charPYStr().charAt(ftPYStr().indexOf(cc.charAt(i)));
		else
			str+=cc.charAt(i);
	}
	return str;
}

function convert(convertType,obj){
	if(convertType==0)
		obj.value=simplized(obj.value);
	else
		obj.value=traditionalized(obj.value);
}
function selectQPDir(value)
{
	if(arguments.length>2) {
		returnCatName = arguments[2];
	}
	if(arguments.length>1) {
		returnCatCode = arguments[1];
	}
	returnCatName = returnCatName || "quickProductsCatName";
	returnCatCode = returnCatCode || "quickProductsCatCode";
	window.open("/relatedlink.do?action=selectDir&returnCatCode="+ returnCatCode +"&returnCatName="+ returnCatName +"&lanCode=1&selectValue="+value,"DIR","height=600,width=500,resizable=yes,scrollbars=yes,menubar=no,status=yes");
}
function selectQPDirRadio(value)
{
	//var varray = new Array();
	//varray = value.split(";");
	window.open("/relatedlink.do?action=selectDir&multi=0&returnCatCode=quickProductsCatCode&returnCatName=quickProductsCatName&lanCode=1&selectValue="+value,"DIR","height=600,width=500,resizable=yes,scrollbars=yes,menubar=no,status=yes");
}
/**
*將特殊字符轉換成相應的URL編碼，用于URL傳輸不會引起混亂
*/
function convertSpecial2Code(val)
{
	var str=val;
	//var replaceString= new Array(/+/g,"%20",/?/g,"%3F",/%/g,"%25",/#/g,"%23",/&/g,"%26");
	//for(i=0;i<replaceString.length;i=i+2)
	//{
	//	str=str.replace(replaceString[i],replaceString[i+1]);
	//}
	str=str.replace('+','%20');
	str=str.replace('?','%3F');
	str=str.replace('%','%25');
	str=str.replace('#','%23');
	str=str.replace('&','%26');
	str=str.replace('/','%2F');
	return str;
}
//打開新窗口(帶參數,如果不傳參數則采用默認值,永遠彈出新窗口)
function openwin1(url,iWidth,iHeight){
	if(iWidth==null||iWidth==0)
		iWidth=800;
	if(iHeight==null||iHeight==0)
		iHeight=520;
	var url;  //轉向網頁的地址;
	var name='_blank'; //網頁名稱，可為空;
	var iTop = (screen.availHeight-30-iHeight)/2; //獲得窗口的垂直位置;
	var iLeft = (screen.availWidth-10-iWidth)/2; //獲得窗口的水平位置;
	window.open(url,name,'height='+iHeight+',innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=yes,resizeable=no,location=no,status=no');
}
//打開新窗口(帶參數,如果不傳參數則采用默認值)
function openwin(url,iWidth,iHeight){
	if(iWidth==null||iWidth==0)
		iWidth=800;
	if(iHeight==null||iHeight==0)
		iHeight=520;
	var url;  //轉向網頁的地址;
	var name='op_win'; //網頁名稱，可為空;
	var iTop = (screen.availHeight-30-iHeight)/2; //獲得窗口的垂直位置;
	var iLeft = (screen.availWidth-10-iWidth)/2; //獲得窗口的水平位置;
	window.open(url,name,'height='+iHeight+',innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=yes,resizeable=no,location=no,status=no');
}
//打開新窗口(帶需要轉換的參數)
function openwinParam(url,param){
	openwin(url+convertSpecial2Code(param));
}
//將指定的按鈕設置為不可用狀態(即disabled狀態)
function setBtn2Disabled(BtnName){
document.getElementById(BtnName).disabled=true;
}

function getBack(){
	var backInfo =$("_h_referer").value;
	if(backInfo!=undefined && backInfo!=null && backInfo!=""
		&& backInfo.indexOf("logon.do") < 0
		&& backInfo.indexOf("workspace.do") < 0) {
		window.location.href=backInfo;
	} else {
		window.opener=null;
		window.open('','_top');
		window.close();
	}
}

function setValue(obj, value) {
	if(!obj) return ;
	var type = "";
	if(obj.item && obj.type == undefined ) {
		if(obj.length <=0) return;
		type = obj.item(0).type;
	} else {
		type = obj.type;
	}
	if(type == 'select-one') {
		for(var i = 0 ; i < obj.length ; i++) {
			if(obj.options[i].value == value) {
				obj.options[i].selected = true ;
				break;
			}
		}
	} else if(type =='radio') {
		for(var i = 0 ; i < obj.length ; i++) {
			if(obj[i].value == value) {
				obj[i].checked = true ;
				break;
			}
		}
	} else if(type == 'checkbox') {
		value = value.split(",");
		for(var i = 0 ; i < obj.length ; i++) {
			for(var j = 0 ; j < value.length ; j++) {
				if(obj[i].value == value[j]) {
					obj[i].checked = true;
					break;
				}
			}
		}
	} else {
		obj.value = value;
	}
}

/**
 * 去除該對象的值:目前先支持checkbox
 * @param {Object} obj
 */
function removeValue(obj){
  if(!obj) return ;
  var type = "";
  if(obj.item && obj.type == undefined ) {
    if(obj.length <=0) return;
    type = obj.item(0).type;
  } else {
    type = obj.type;
  }
  if(type == 'checkbox') {
    for(var i = 0 ; i < obj.length ; i++) {
      obj[i].checked = false;
    }
  }
}

function readonlyFileInput(fileInputs) {
	var tmps = fileInputs.split(",");
	for(var i = 0 ; i < tmps.length;i++) {
		AddEventHandler($(tmps[i]),"keypress",readonlyFileInputReturn);
		AddEventHandler($(tmps[i]),"contextmenu",readonlyFileInputReturn);
		AddEventHandler($(tmps[i]),"change",previewImageReturn);
	}
}
function readonlyFileInputReturn() {
	return false;
}
/**
 * 添加圖片回顯預覽功能
 * @return
 */
function previewImageReturn(){
	var objImg1 = $("img_images1");
	var objImg2 = $("img_images2");
	var imgUrl;
	if (objImg1&&document.getElementById("images1")&&document.getElementById("images1").value!="")
	{
		/*
		if ("Microsoft Internet Explorer" == navigator.appName)
		{
			//兼容IE7、IE8
			imgUrl = document.getElementById("images1").value;
			imgUrl = "file://127.0.0.1/" + imgUrl.replace(":","$");
			document.getElementById("img_images1").src = imgUrl;
		}
		else if ("Netscape" == navigator.appName)
		{
			document.getElementById("img_images1").src = document.getElementById("images1").files[0].getAsDataURL();
		}
		*/
		document.getElementById("img_images1").src = document.getElementById("images1").value;
	}
	if (objImg2&&document.getElementById("images2")&&document.getElementById("images2").value!="")
	{
		/*
		if ("Microsoft Internet Explorer" == navigator.appName)
		{
			imgUrl = document.getElementById("images2").value;
			imgUrl = "file://127.0.0.1/" + imgUrl.replace(":","$");
			document.getElementById("img_images2").src = imgUrl;
		}
		else if ("Netscape" == navigator.appName)
		{
			document.getElementById("img_images2").src = document.getElementById("images2").files[0].getAsDataURL();
		}
		*/
		document.getElementById("img_images2").src = document.getElementById("images2").value;
	}
	if (objImg1&&document.getElementById("image")&&document.getElementById("image").value!="")
	{
		/*
		if ("Microsoft Internet Explorer" == navigator.appName)
		{
			//兼容IE7、IE8
			imgUrl = document.getElementById("image").value;
			imgUrl = "file://127.0.0.1/" + imgUrl.replace(":","$");
			document.getElementById("img_images1").src = imgUrl;
		}
		else if ("Netscape" == navigator.appName)
		{
			document.getElementById("img_images1").src = document.getElementById("image").files[0].getAsDataURL();
		}
		*/
		document.getElementById("img_images1").src = document.getElementById("image").value;
	}
}
function AddEventHandler(oTarget,sEventType,fnHandler) {
	if(!oTarget) return ;
	if(oTarget.addEventListener) {
		oTarget.addEventListener(sEventType,fnHandler,false);
	} else if(oTarget.attachEvent) {
		oTarget.attachEvent("on"+sEventType,fnHandler);
	} else{
		oTarget["on"+sEventType] = fnHandler;
	}
}
/**
 *
 * @param text
 * @param head
 * @return
 */
function startWith(text,head) {
	var reg = new RegExp("^"+head,"ig");
	return reg.test(text);
}

/**
 * 去除首尾空格
 * @param {Object} obj
 */
function trimSpace(obj){
  // 先去除首位全半角空格
  var str = obj.value;
  str =  str.replace(/(^[\s|　]*)|([\s|　]*$)/g,"");
  // 再把中間多個全半角空格替換成一個半角空格
  obj.value = str.replace(/[\s|　]+/g, " ");
}

/**
 * 去除首尾空格
 * @param {String} value
 */
function trimSpaceExt(strValue){
  // 先去除首尾全半角空格
  strValue =  strValue.replace(/(^[\s|　]*)|([\s|　]*$)/g,"");
  // 再把中間多個全半角空格替換成一個半角空格
  return strValue.replace(/[\s|　]+/g, " ");
}

function htmlEncode(strValue) {
	strValue = strValue.replace(/&/g, "&amp;");
	strValue = strValue.replace(/'/, "&apos;");
	strValue = strValue.replace(/\"/g, "&quot;");
	strValue = strValue.replace(/</g, "&lt;");
	strValue = strValue.replace(/>/g, "&gt;");
	strValue = strValue.replace(/\n/g, "<br>");
	strValue = strValue.replace(/  /g," &nbsp;");
	return strValue;
}

function htmlDecode(strValue)
{
	strValue = strValue.replace(/&amp;/g, "&");
	strValue = strValue.replace(/&apos;/g, "\'");
	strValue = strValue.replace(/&quot;/g, "\"");
	strValue = strValue.replace(/&lt;/g, "<");
	strValue = strValue.replace(/&gt;/g, ">");
	strValue = strValue.replace(/<br>/g, "\n");
	strValue = strValue.replace(/&nbsp;/g, " ");
	return strValue;
}

function URLencode(sStr) {
    /*
     * return escape(sStr).replace(/\+/g, '%2B').replace(/\"/g, '%22')
     * .replace(/\'/g, '%27').replace(/\//g, '%2F');
     */
    return encodeURIComponent(encodeURIComponent(sStr))
}

function formatSpecialword(fromObj, id) {
	var callBack = null;
	var content = fromObj.value;
	if (id == "contentName") {
		callBack = callBack_contentName;
	} else if (id == "accountName") {
		callBack = callBack_accountName;
	} else if (id == "contentUrlAlt") {
		callBack = callBack_contentUrlAlt;
		content = toUpperEachWord(content);
	}

	ajaxRequest("/topRank.do?method=formatSpecialWord", "&checkString="+URLencode(content), callBack);
}

function callBack_contentName(result) {
    var returnValue = eval(result);
    $("contentName").value = returnValue[0]['txt'];
}

function callBack_accountName(result) {
    var returnValue = eval(result);
    $("accountName").value = returnValue[0]['txt'];
}

function callBack_contentUrlAlt(result) {
    var returnValue = eval(result);
    $("contentUrlAlt").value = returnValue[0]['txt'];
}

/**
 * 給對象添加相關事件監听
 *
 * @param {Object} oTarget
 * @param {Object} sEventType
 * @param {Object} fnHandler
 */
function addEventHandler(oTarget,sEventType,fnHandler) {
  if(!oTarget) return ;
  if(oTarget.addEventListener) {
    oTarget.addEventListener(sEventType,fnHandler,false);
  } else if(oTarget.attachEvent) {
    oTarget.attachEvent("on"+sEventType,fnHandler);
  } else{
    oTarget["on"+sEventType] = fnHandler;
  }
}

/**
 * 讓錯誤信息顯示更醒目
 */
function showErrorCss() {
    var eles = document.getElementsByTagName("SPAN");
    for (var ele in eles) {
        if (eles[ele] && eles[ele].className == 'errors') {
            eles[ele].parentNode.className = eles[ele].parentNode.className + " alertborder";
        }
    }
}

//
addEventHandler(window, "load", showErrorCss);
