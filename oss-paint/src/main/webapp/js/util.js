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
          alert("�ж�J"+checkForm.elements[i].name+"����")
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
*�q���Ũ��
*@param:document.obj
**/
function addTwoBlankEachParagraphForObject(obj)
{
		obj.value=twoBlankEachParagraph(obj.value);
}

/**
*�y�l���r���j�g
*@param:document.obj
**/
function toUpperFirstWordEachSentenceForObject(obj)
{
		obj.value=toUpperFirstWordEachSentence(obj.value);
}
/**
* ���r���j�g
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
* ���r�����j�g
*@param:document.obj
**/
function toUpperWordForObject(obj)
{
		var va=toLowerForAllWord(obj.value);
		obj.value=toUpperAllWord(va);
}

/**
* ���r���p�g
*@param:document.obj
**/
function toLowerEachWordForObject(obj)
{
		obj.value=toLowerEachWord(obj.value);
}
/**
 * �����r���p�g
 */
function toLowerForAllWordForObject(obj)
{
	obj.value = toLowerForAllWord(obj.value);
}

/**
 * �����r���p�g
 */
function toLowerForAllWord(val)
{
	return val.toLowerCase();
}

/*********************************************
*�N���I�Ÿ���[�Ů�*
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
*�N���I�Ÿ��令�[,*
*@param:document.obj
*********************************************/
function addCommaFromCnPunctuation(obj)
{
obj.value=replaceCommaFromCnPunctuation(obj.value);
}

/***********************
*��ƪ�����*
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
*��ƪ�����*
*@param String*
*@return boolean*
***********************/
function isInteger(str)
{
	var patrn=/^(-|\+)?\d+$/;
	return patrn.test(str);
}

/**********************
*�t��ƪ�����*
*@param String*
*@return boolean*
************************/
function isNegativeInteger(str)
{
	var patrn=/^-\d+$/;
	return patrn.test(str);
}

/***********************
*����ƪ�����*
*@param String*
*@return boolean*
***********************/
function isPlusInteger(str)
{
	var patrn=/^(\+)?\d+$/;
	return patrn.test(str);
}

/***********************
*�q�l�l��a�}������*
*@param String*
*@return boolean*
***********************/
function isCorrectEmail(str)
{
	var patrn=/[a-zA-Z0-9][\w\.\-]*@[a-zA-Z0-9][\w\.\-]*\.[a-zA-Z][a-zA-Z\.]*/;
	return patrn.test(str);
}

/***********************
*�ɶ�������(hh:mm:ss)*
*@param String*
*@return boolean*
***********************/
function isTime(str)
{
	var a = str.match(/^(\d{1,2})(:)(\d{1,2})(\2)(\d{1,2})$/);
	//var a = str.match(/^(\d{1,2})(:)(\d{1,2})(:)(\d{1,2})$/);
	if (a == null)
	{
		alert("��J�����O�ɶ��榡(hh:mm:ss)�I");
		return false;
	}
	if (a[1]>24 || a[3]>60 || a[5]>60)
	{
		alert("�ɶ������T�I");
		return false
	}
	return true;
}
/***********************
*���������(yyyy-mm-dd)*
*@param String*
*@return boolean*
***********************/
function isDate(str)
{
	var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})(-|\/)(\d{1,2})$/);
	if(r==null)
	{
		alert("��J���ѼƤ��O����榡(yyyy-mm-dd)�I");
		return false;
	}
	var d= new Date(r[1], r[3]-1, r[5]);
	if(!(d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[5]))
	{
		alert("��������T�I");
		return false;
	}
	return true;
}

/***************************************
*�ɶ����������(yyyy-mm-dd hh:mm:ss)*
*@param String*
*@return boolean*
***************************************/
function isDateTime(str)
{
	var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})(-|\/)(\d{1,2}) (\d{1,2})(:)(\d{1,2})(:)(\d{1,2})$/);
	if(r==null)
	{
		alert("��J���ѼƤ��O����榡(yyyy-mm-dd hh:mm:ss)�I");
		return false;
	}
	var d= new Date(r[1], r[3]-1, r[5],r[6],r[8],r[10]);
	if(!(d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[5]&&d.getHours()==r[6]&&d.getMinutes()==r[8]&&d.getSeconds()==r[10]))
	{
		alert("��������T�I");
		return false;
	}
	return true;
}

/*********************************************
*����L��J�ɦp�G���O�Ʀr��������ɴN�����J*
*@return boolean*
*********************************************/
function pressNumbersOnly()
{
	pressFomatOnly("0123456789.","numbers");
}
/*********************************************
*����L��J�ɦp�G���O��Ʀ�������ɴN�����J*
*@return boolean*
*********************************************/
function pressIntegerOnly()
{
	pressFomatOnly("0123456789","integer");
}
/*********************************************
*����L��J�ɦp�G���O��w�榡��������ɴN�����J*
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
*�C�Ӧr�����j�g*
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
*�C�ӳ��r�����j�g*
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
*�C�ӳ��r�����p�g*
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
*�q���Ũ��*
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
		temp='�@�@'+temp;
		temp=temp.replace(/^�@{2,} */g,"�@�@");
		if(temp!="�@�@")
		{
			m=m+temp;
		}
	}
	return m;
}
/***************************************
*�q���Ť@�� by xueduanyang
**************************************/
function addOneBlankEachParagraph(obj)
{
		obj.value=oneBlankEachParagraph(obj.value);
}
function oneBlankEachParagraph(str)
{
	str=str.replace(/[\r\n]{1,}[\s�@\r\n]*/g,"\r\n\r\n");
	return str;
}
/*********************************************
 * �R���q���e�����Ů�
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
	temp=temp.replace(/^�@*/ig,"") ;
	temp=temp.replace(/^ */ig,"") ;
	m=m+temp;
    }
    strValue.value=m;
}
/*********************************************
*�C�ӥy�������r�����j�g*
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
*�N������I�ഫ���^����I*
*@param String*
*@return String*
*********************************************/
function replacePuncFromCnToEn(str)
{
	var replaceString= new Array(/��/g,"`",/��/g,"~",/�I/g,"!",/��/g,"#",/�C/g,"$",/�H/g,"%",/�]/g,"(",/�^/g,")",/��/g,"\"",/��/g,"\"",/�F/g,";",/�J/g,":",/�A/g,",",/�C|�D/g,".",/�H/g,"?",/�@/g," ",/��/g,"'",/��/g,"'",/��/g,"'",/��/g,"/",/��/g,"\"",/��/g,"<",/��/g,">",/��/g,"*",/��/g,"&",/�I/g,"@",/�@/g,"^",/��/g,"+",/��/g,"|",/��/g,"\\",/�D/g,".",/��/g,"_",/��/g,"=",/��/g,"-",/�K�K/g,"...",/��/g,"1",/��/g,"2",/��/g,"3",/��/g,"4",/��/g,"5",/��/g,"6",/��/g,"7",/��/g,"8",/��/g,"9",/��/g,"0",/�B/g,",",/�a/g,"{",/�b/g,"}",/�e/g,"[",/�f/g,"]",/��/g,"A",/��/g,"B",/��/g,"C",/��/g,"D",/��/g,"E",/��/g,"F",/��/g,"G",/��/g,"H",/��/g,"I",/��/g,"J",/��/g,"K",/��/g,"L",/��/g,"M",/��/g,"N",/��/g,"O",/��/g,"P",/��/g,"Q",/��/g,"R",/��/g,"S",/��/g,"T",/��/g,"U",/��/g,"V",/��/g,"W",/��/g,"X",/��/g,"Y",/��/g,"Z",/��/g,"a",/��/g,"b",/��/g,"c",/��/g,"d",/��/g,"e",/��/g,"f",/��/g,"g",/��/g,"h",/��/g,"i",/��/g,"j",/��/g,"k",/��/g,"l",/��/g,"m",/��/g,"n",/��/g,"o",/��/g,"p",/��/g,"q",/��/g,"r",/��/g,"s",/��/g,"t",/��/g,"u",/��/g,"v",/�@/g,"w",/�A/g,"x",/�B/g,"y",/�C/g,"z");
	for(i=0;i<replaceString.length;i=i+2)
	{
		str=str.replace(replaceString[i],replaceString[i+1]);
	}
	return str;
}
/*********************************************
*�N�F�J�B���I�ഫ���^����I*
*@param String*
*@return String*
*********************************************/
function replaceCommaFromCnPunctuation(str)
{
	var replaceString= new Array(/�F/g,",",/�J/g,",",/�B/g,",",/�A/g,",",/;/g,",",/:/g,",");
	for(i=0;i<replaceString.length;i=i+2)
	{
		str=str.replace(replaceString[i],replaceString[i+1]);
	}
	return str;
}
/*********************************************
*�N���I�Ÿ���[�Ů�*
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
	//�P�_�p�G�X�{�H�p�ơA�h���ݭn���I��[�Ů�
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
 * �R���r�ūe�����Ů�
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
//�^����I�त����I
function doPuncEnToCnForObject(obj)
{
		var val = obj.value;
		var replaceString= new Array(/`/g,"��",/~/g,"��",/!/g,"�I",/#/g,"��",/\$/g,"�C",/%/g,"�H",/\(/g,"�]",/\)/g,"�^",/;/g,"�F",/:/g,"�J",/,/g,"�A",/\.|�D/g,"�C",/\?/g,"�H",/ /g,"�@",/'/g,"��",/'/g,"��",/'/g,"��",/\//g,"��",/</g,"��",/>/g,"��",/\*/g,"��",/&/g,"��",/@/g,"�I",/\^/g,"�@",/\+/g,"��",/\|/g,"��",/=/g,"��",/,/g,"�B",/\{/g,"�a",/\}/g,"�b",/\[/g,"�e",/\]/g,"�f",/��/g,"1",/��/g,"2",/��/g,"3",/��/g,"4",/��/g,"5",/��/g,"6",/��/g,"7",/��/g,"8",/��/g,"9",/��/g,"0");
		for(var i=0;i<replaceString.length;i=i+2)
		{
			val=val.replace(replaceString[i],replaceString[i+1]);
		}
		//�p�G���夤�s�b�p�ơA�p1.1�A�h���@����
		var r = val.match(/([0-9]�C[0-9])(�C[0-9])*/g);
		var temp = "" ;
		if(r!=null)
		{
			for(i=0;i<r.length;i++)
			{
				temp=r[i].replace(/�C/g,".");
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
 * �p��r�Ŧ���
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
        alert(display + "��פ���W�L" + (length/2) + "�Ӻ~�r��" + length + "�Ӧr��.") ;
        obj.focus() ;
        return true ;
    }
    return false ;
}
/**
 * �]�m�V.
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
 * �]�m�V.
 */
function setDefaultFrameRows()
{
	if (typeof(parent.frmindex1)!="undefined")
	{
		parent.frmindex1.rows="49%,2%,49%";
	}
}
/**
 *���J�S��r��
 */
function doAddSpecialChar(obj)
{
  var objwin=window.open("/js/spechar.htm","swindow","height=500,width=500,resizable=yes,scrollbars=yes,menubar=no,status=yes");
}
/**
 *���}������O����
 */
function openCatalogWindow(url, value) {
    if (window.replaceCatalogWindowUrl) {
        url = replaceCatalogWindowUrl(url);
    }
    var opener = window.open(url + value);
}

function charPYStr(){
return '�ڪ�J���u��s�J��ħ�G��ê�R�i�b��w�ͫ��t���i�׻��s�W�μ����Ͷƶ�ҿD�ݮå��z�a���K�ͤک޶[�v������Q�}���լf���\�ձѫ���Z�h���{�O����զ�ä�b��̨������]�H�j�νS�F������c�M�]�ǭ����r�O���_���ɰ\�j�z�M�O�d���_��I���X���V�ƾεH�Q�b�f���±Y�^�Ǭ��۰n�G�����������ɽ������h�����ͳ��͹��P���u�װ��@��s�S��K�ܤ˿��G�|�M�гC����ž�x�Oç�l�y�x�ػ���L�B�`���û欱�f�ì��Լ����ڪi�իk�i�`��B�����K��y�鮷�R���ɰ𤣥��Bï�������q�����~�]�B����m�潲�\����ݺF�G��a���ܷ��þ��W�ѱ��Z�����U��h�O���eӳ��d��g��ît�ҩ��[�e�U���a�����겣��Ÿ���s�����`���v�z�t���Z�ۭҶW�۶r�¼J��_�n������M�w����ڨ��б�ըI���XŨ���٫��?�e���{�g��۩ӳx�����Y����ͦ��𦢹������פب��ͥ��K�R�R�αR�d��Så�C�Y�T�w�����䤡���X�o�p�ܾS���Q����¦�x���zĲ�B���t���ܶǲ�ݦ�H���l�����Чj�����諫�K�ϾJ�B�E�����W�ﲫ��ϻ���O�����禸�o���w�^�q�O��ʾL�L�P�O�y«�R�Z�ʯܷ��f�A��s�o�R���b������f�F���D���j�b���V���a�p�N�U�U�ݶe��Ծᤦ���X���x�����H�ϼu�J����ҿ��ɤM�o�Эˮqë�ɨ�_���D�s�w�o���޿O�n����H���C�w�}�Ĳèf���C�ީ詳�a���īҧ̻��l�A�i���K�I���Q�Թq��l���}����M�p�J��N���Q���ն^�R�н������|�B�n�m�v�������w�q��F�V�����ʴ�˾���}�§ݤ�~���r�k�����r�}�WŪ���@�����{�״秪�ݵu��q�_�v��I����[���۴��y�y�w�޹P�s�G�h���W������k�Z���o�Z�X�B�_�Z�c�̧�K�k�j���Ө�պ���|�G�L�o�@����F�֪k�Wÿ�|�f½���s���c�Z�ФϪ�d�c�Ƕ��x�{�ڤ�թШ�����X�����D�ح��έ�ڧp�ͼo�m�O�����h�^���ɼX�I�W���ĥ������T�׫ʷ����p�W���Ʋl�{���_�ة^���_�ҼŽ���ߩؿ�T�t�ť�R�A�B�e�ֵ����j�������y���Ʃ��G�u���н�_�ťI������t�I�r����J���Ǹӧ﷧�t�\�@�z�̱�a��x���P�z���B������z���^����t�o���I�̿|�d��Z�i���q�����F�ު��έ������չj�̭ӦU���ڸ�ѧ�ü�G�ձ�u��\���ǨѰ`���c�}�d�E��^�@�_�ķ��e�����c�ʰ��dۣ�B�T���f�t�h���j�۰����ѬG�U�T������l�豾�o�ĩ�Ǵ����x�a�[���]��D��e��s�}���W�c�@�k�t�ӭy���ެѮ��d���Q�D�@�u���糢��G�q�L���e�Į���`�b�o�w����t�[�H��ۨu���ٮ§�Ѯ��k���~�q�C�����z���@�q�n�Ӹ��E���ܲ���֥ݩM��X����Ҫe�U�����b�P�K�²��ܬ������ū��F���M�i�E�x��������J�U�q�p�ԫ�I�G�����J�����k�򩷪���@�������M�ط�Ƶe���Ƹܺi���h�a�a�w�����ٽw���w��ȸ�صA�Ƥۯ�W���D��®�ӰĴq�׮̷E�����Ǵ��������^�����z�c�f���©�|��׿л�ø����B���V�Ŭ������δb�N�f�����������]�n�ߦ��ȸ�E����V�Z�r�N���ƿ��y���Ϋ�e�V�Y������X��v���޾��uɫ�������ٱH�I�p�O�J�һڧ��~�����E���ήa�[���U��ҹ[���[��[�r����ʰ�y�ඡ�έݪ��}�l�põ�ˬZج�P�z��²��Ŵ����eų��⨣��b��ĥ�C�^���q��ػ�N�ߦ�æ���ռ����K�歰�����G�J���步��ź�b�Z�ͻ��B���}������ú���ϱл����s���������ŵ󶥺I�T�`��ش��H�����e�g��ĵ���V�R�ҷq��|�j�t���v�b���~���s�Ȩh���[�b�E�s�\���¦ݸ��S�N����몮�j�~�s�⧽�C�x�|�q�E�ھڥ���Z�����ѥy�߬��@���Y�S�²����h��̧౸����ܪN����ܼ䵲�ѩj���Ǫ�ɭɤ��λ|���y��������z�̺��A���Զi�ڮʸT���u��ɫl��ı�M�Z�����߶v�x�g�m�T���C�p�@�ة@�d���}�{���ʹn�Z���ɧ���ݱd�B�R���ܤ����ҫ��N�a�V�V�_�ʽW���߫y�i���J��ȽҪְپ����|�\�Ů��ձ���f���F�\��]�W�Ůw�Ǧj���ظ����_��ּe�ڦJ���g���q���m�p�����ɿs��������X�\��[��x�A�X����U�Գ���þ���յܨӿ��Ű����d�x�����i�������i�l���ݷ�}�T�Y���Ԯ����Ҩc�ѨЫ��T�O��Ǽֹp�J���U���w�S�ݦ����\�W���N���p���X�W����z�����U§���O���R�F�y�t��Q�@�ҫW�g�߲��w���O������p���s�I�G����î���y���ʷҽm³�D��d�}���q���G�̼��ṱ��R����F�����ƦC���P�H�y�Y�L�C�M�{�F��O����[��µٹs�ֹa�D�ܭ��F������t�O�ȯ[�h���H�d�B�F�y�h���sŤ�VŢ�K���b�l���Ӱ�O�M�|��Ī�c�`�f�l�۳����|�S�L�S�������S���j�f�T�Q�ȼi���\�{��߲v�o���r���p�e�Z�ñ�������ۨڲ_�������ù���r�Y�[�r�����d����º��X�°��|���ܮI�R�����گ߿f�C�Z�����ҺC����~�?�]�����߭T���ٹg�f�Z�_�U���T�򪴪T����`�ѨS�ܴC��C��N�K�f�A��e�̵޻X�c���r�کs֩�����S�g�����̯��V�c�e�K���֯v��çK�j�Y�q���]�y���Ƭ��q���������ץױӼ��ԩ����ʦW�R�պN��Ĩ�ҽ��i���]�٥������q�j�z�歯�ѦȬY��d�a�i���Ӽǹ��Ҽ}��ط�p�����o�u���R�Ǧ��D���@�`�n�k���n�����o�x��O�k�����g�O�٪d�����A�ο��f���Y��~�V�[�ɩ��Q�C������¿�^��h��I�z�f�񾮹����פ��s���w�@�A�˥��V��k�x�h�Į����z�ծ@���ü��¹ð��x�ԭw�����ȵ]��ƵP�r���k��L�Y�߯`�P�q���e����D�ߩH�S���T�]�w�A�F���p�߳��t�بK�Q�֯y��i��^���׸N�O���B�P���������R��ܼA�\�s��ʯh�֤ǵl����Ĵ�g�����F�ƺ}�]���J�h���W�h�~�u��W���ӥ��̲~��̩Y���C�}�z���˭弳�Q���@���лZ�H��E�������n�r���۴ϱ��d�C�Y���m�P��ѩ_�[���T�����X����M�_�Z�^��ҫ��侹�𨴱�T�_�W�t���o��@�]�d�Eñ�a�����r��X�e�绺�L���մO��p�j��Ī������j�m����V�����@�카���T��¼�k�N¬���X�B���ѴܫI�˯��^�Ԫ��V��G�C���B�ɭ�M�����污���мyã�a��C���y�D�}���s�ͰϳI���ߩ}�X�����T��h����v�Ǭu����������U���P�a�o�N�e�T���ȸs�M�U�T�V�{�[�c�W�����Z¶�S���Ф��H�Զ����{�b���������馥��T�a�ĺ����e�����|�X�ׯ�į�����p�d�Ŧ��J�ȳn������U�|��Y�z���x�ĸ|�ζ��ɤT�ѳʴ���ڳ�k�̱��A����ߴ˹������b�F����ԣ�ٿz�ά��s��s�R���m�{����ĺ�����®�µ�a�˰ӽ�ΤW�|�n�鮴�y�N���c��֭��а�D�ުٳj��g��A���]�~�өD��`�W�ԯ��H�f�T�ƵǷV���n�͵c����÷�ٲ��ѳӸt�v����I��֤r��Q�۬B�ɤ����k���ѥv�ڨϫ˾p�l���ܤh�@�U�ƫ�}�u�լO�ݾ��A�K�������󥫫�ǵ�զ��⭺�u�ر°��G�~���Ϯޮ����βQ����ū�E�������Ƹp�������ݳN�z������ݹֱf�ƺ�����A�L�I�ϫӮ�C����n�֤��ε|�m�����ϻ��Ӯ��{�����R��p�q�����v�x��|���}�x�Q�q���|�e���^�w�j���]��Ĭ�p�U���t�����췹�J�D�»Ļ[�������H�k��H���J�E�G���]�l���u�����Y������Ҷ�L���o��á���Ͻ�L�a��x���@�ӺA�O�~�u�g���y���ȷ���ӽͩZ��R�ұ��Ĭ�����e��Ž���}�ս��I��S���ܷʸl���k�^���Q�M�S���˯k�ñ���O���D�������������c�P�ѲK��в���Q�ɬD��|�����K�K���U�v�L�ŧʰ��F�x�����q�����P�ɧ͵���Ѷ���εh�����Y�z�Y�r��Ϯ{�~�\�O�g�R�ߴ�α��Z�L���ưh�]���v�즫���k��m�򧴩ݳ���z��ګ�����n�~���s�W���x�Y�J���J���ߵp�{�{��U�èL��`�P��������Ѧk���޷L�M���H���߱�������e�������n������ȭG���Q����ױL���ýE�ŰA��D���kí���ݶ���|�⽽���ۧںW�״��U�Ŷ���Q�ûz�εL����^�d��Z�����ȻR��V������ફ�ŰȮ��~�����R��ִ�����H�l����}���Ʊx���i�����m�˦��R��ŧ�u�߷@�߻Ѭ~�t�����ӽM���X���ҷv�l�L�U�U�H�L�~���ܥ�P�A�d�w����~�C�������I�{�m�����`�r�˳����u�۴[�쭻�c����m�����ԷQ�T�ɶ��Ѿ󹳦V�H���v�]�d���۾P��d�c��p���ըv�S���ķ��Ƿ��Ⱦc���⨸�ׯٿӼg����ɾӪn�m�®h�~��N�Y���s��߫H�]�P�{�V�i���D���Ψ���������ʩm�S���ݦI��������ײۦ���?�q�S¸�V���ݵ�N���}�\�W���Ԧ��ǯb�򵶴B����a�٫��a�ۥȿ��~�t���u���ǥ޳�����t�`���ߴM������İV�T��������~�n�r�X�ޤ�H�V�ŲP���רȳY�j�|�I�ϲT�Q�Y���驥�����C�F���u�a�����l�t�A���P���x����۵K�b����o���m�������˺ŦϬv������o�i�˺y�ܸy�����n�󻻽`�����r���ĭnģ���O�C�ݳ��M�]�����~�����ũ]�G�@���崥�v�̥���[�i�򲾻��غè^�y���U���ƭʤw�A�o�H��������z�����r�h�w�̥�ǷN�ݾиq�q����ĳ��Ķ���l��ö�����]�ﭵ���çu�Ȳ]�G���������L�^�����N���ռ���纷�Ǫ�Ĺ�ռv�o�w�M��֦��s��e�l�㸺��a�F�ñv�i�Ϋ��u�y�~�ץѶl�\�S�o�娻���ͥk���V���S�����J�_�������M�֧E�\�O���r����T�B�P����t�y�Хɰ쨡���S�J��n�s�U��|�A�D�J�ιw�ݶ��p�W�ޤ����K�촩�ն���᷽�t���b�@��|���V�D�_���f�뮮�\�ж�ਤùk���Bĭ�߷w��`�{���v�a�_��A�b��������Bż���D�V�wĦ�Ǧ����Dļ���y�m�_��d�ܫh�A���W�����ؤ��?���Թh�w�]�^�Q�E���B�K�N�v���Ź�¤�ָ��ߪg����ӹ�i�ٴ̦�ԯ����̳���s�i�x����V�b��M�ȿ`�٩۬L��h���Ӹn��F�l�B����h����㽩�o��÷r�u�¯z���s�w���E�l�E�_����}�]�øC���u���þ�@���F�V�p�G�Ҫ۪K��s�j���ϯץĤ�´¾���Ӵް���˼�}���k�u���ȧӼ��Y�ܭP�m�m�Ϩ���X�誥�����v�����ة����J�׺ظ~���򲳦�P�{�w��b�y���G�K�z���J�]��禮�޽Ѹݳv����N���f��D�۬W�U�E�Jű�v��`���n�����M�j�༶�Ƚf�β�˧����������@�l�ؼY��ηǮ������Z�_�u�ֵۨ`�B���t�꫺���d�����J�󷺤l�ۺ{�r�O���ܩv���`�a�Q�����~������گ��A����pġ�L�K�̸o�L��Q�����g���@���y��x��_�t��E�Ǧ�������C����R�ײ��ɪ`�P�t��P�^��';
}

function ftPYStr(){
return '�ڪ�J���u��s  ��  �G��  --�i�b��w�ͫ��t���i�׻��s�W�μ�    ��--�ҿD�ݮå��z�a���K�ͤک޶[�v���--�Q  ���լf��--��--����Z�h���  �O����զ�ä�b    ��--���]�H  �νS�F  ��  �c�M�]��--���r�O��  --��--�ɰ\  �z�M�O�d���_  �I    ��  ----�H�Q�b�f���±Y  �Ǭ��۰n�G����  �����ɽ�  --�h--����  �͹��P���u�װ��@      ��K  �˿�    �M--�C����  �x--  �l�y----  --�L�B�`����  ���f--���Լ�--  �i�իk�i  ��B�����K��y  ��  ��  �𤣥��Bï�������q�����~  �B����m�潲�\--  ------      ----�þ��W�ѱ��--��--------�O���eӳ��d��g��ît  ���[----                ���s----�`  --  --��--�ۭҶW��  �¼J��_�n��  ��M�w--����ڨ�--��ըH  �X  --  ���?�e���{--��  �ӳx  ���Y  ��ͦ�  ��  --  �פب��ͥ�--�R--  �R--��S    �Y�T  ��  ��  ���X----��    �Q����  --���z    ���t����--��ݦ�  ���l��  --�j����  ���K�ϾJ�B�E  ���W  ����ϻ�  �O��  ����  ��    --�^------�ʾL�L�P  �y  �R�Z�ʯܷ��f�A��s�o�R���b����  �f  ���D���j�b���V��--�p�N  �U�ݶe���--��--  --  �����--�H  --�J  --    --�M--�Э�--  --��_���D  �w�o����--�n����  ���C�w�}--�èf--�C�ީ詳�a���īҧ�      �i���K  ���Q--  ��l���}��--���M�p�J��N���Q    �^�R�н��|    �B�n�m    ��  �w  ----�V����----˾��--�}�§�  �~���r�k�����r      ���@  ��  �{�״秪�ݵu  �q--  ��--  --�[--�۴�  �y  �޹P�s�G�h--�W������k--���o  �X    �Z--�̧�K�k  ����--��    �|�G      ����F  �k  ÿ�|�f½��    �c�Z--�Ϫ�    ��  �x�{�ڤ�թШ�����    ���D��  �έ�  �p��--�m  �����h�^��  --�I�W��--����--    ��--���p      �l�{      �^  ��_�Ҽ�  ��ߩ�  �T�t�ť�R�A�B�e�ֵ����j--  ���y���Ʃ��G�u����    �ťI�����  �I  ��--  �J����  �﷧    �@--�̱�a��x  �P  ��  ----  ��z  --����t  ���I�̿|�d  �Z�i���q--��  �ު��έ������  �j  --�U  �ڸ�ѧ�ü�G�ձ�u��\��  �Ѱ`��--�}  �E��  �@  ��--�e����--  --�dۣ�B�T���f�t�h���j  �����ѬG  �T�����--�豾�o�ĩ�Ǵ�  �x�a  ��  ��--��  ��--�}��  �c��--      ��  �Ѯ�--��  --  --��  ��--�G�q  ���e�Į���`  �o�w��  �t�[�H��ۨu���ٮ§�Ѯ��k��--�q�C�����z���@�q�n��  �E���ܲ���֥ݩM��X����  �e�U����    �K�²��ܬ�����--�ū�  ���M�i  �x����  ��J�U�q�p�ԫ�I�G����--���J�����k�򩷪��  ��----��--  ���  --��  �i��--�a----  ��    --�w--  ��----�Ƥۯ�W  �D��®�ӰĴq�׮̷E��  ��--  �����^--���z�c�f��    --  --        ��B��--�V�Ŭ��٤�  �δb�N    --����--��]  �ߦ�  ��E    �V    �N--��  �y���Ϋ�e�V�Y��  ----��v  �޾��uɫ��--��--�H�I    �J��  ��    ���E--�ήa�[      ��  ���[--�[  ��--  --�y    �έݪ�  �l    --�Z--  ----  --��--  --          �b��  --  ------�ػ�----��æ  --    �K  �������G�J  �步--  --�Z--    --  ����      �ϱл�    �s��������  ��  �I�T    �ش�  ��  ��e  ��ĵ��    �ҷq  --  �t��  --���~���s  �h���[�b�E�s--��  �ݸ��S�N����몮�j�~  �⧽�C�x  �q�E��--����Z��  �ѥy--��--��  �S�²���  ��̧౸�����--�����--  �ѩj���Ǫ�ɭɤ���  --�y��������z��    --    ��--�T��  ��  --  ��  --    ����    �g�m�T���C�p  �ة@�d��  �{��--�n�Z���ɧ���ݱd�B�R���ܤ����ҫ��N�a�V�V�_�ʽW  ��--�y�i���J���  �ְ�----�|�\�Ů��ձ�--�f���F�\��]�W��--    ���ظ��--�_--��--�ڦJ���g��  ��----  ��--  �������  �\--�[��x�A--��  �U�Գ�    ����  --    ��----      --  --  --    --��}�T�Y���Ԯ�----�c�ѨЫ��T�O--��--�p  ���U���w--�ݦ�  --�W���N���p��  �W  ��z��      ���O��  ----  --�Q�@�ҫW�g�߲�--  �O����--        �G----  --    ----    --��d�}--  �q���G  ���ṱ  �R��  ��F��  ��ƦC���P�H  �Y�L�C�M      �O--  �[��µٹs    �D��--  ��--  �t�O�ȯ[�h��  �d--�F�y�h��    --  �K��----  ------  �|��      --  --      �S�L�S��  ����      ��  --  --�ȼi--  --��߲v--  --------�Z--����--  ------      ��          �r����    --��          ��--�I                --���ҺC��  �~�?�]����  �T  ���  �f�Z�_�U��  �򪴪T����  ��--�ܴC  �C��N�K�f�A  ----�޻X�c��  �r--�s֩�����S�g  --�̯�  �c�e�K--�֯v  �çK�j�Y  ���]�y���Ƭ��--����--���ץױ�--  ���    �W�R  �N��Ĩ�ҽ��i���]�٥������q�j�z�歯  �ȬY��d  �i���Ӽǹ��Ҽ}��ط�p�����o  ���R  ���D���@�`�n�k  �n--  --  ��O  --���g�O�٪d��--�A��  �f���Y��~�V----���Q    ����  �^      �I�z--  ��ڬ----���      --  �˥��V��k�x�h  �����z  �@--  --��--��--�ԭw�����ȵ]��ƵP�r���k��  �Y�߯`�P�q��  ����D�ߩH�S���T�]�w�A�F���p  ���t�بK--�֯y��i��^���׸N�O���B  ���������R��ܼA�\�s��ʯh�֤ǵl����Ĵ�g����    �}�]���J�h��    �~�u��W  �ӥ�--�~  �̩Y--  �C�}�z���˭�--  ���@���лZ�H--�E����  �n�r����--���d�C--���m�P��ѩ_�[���T    �X���  �_  �^��--���侹--��--�T�_  �t��  --    �d    �a  ���r    �e--��--  --�O��p----�Ī�    ----��  �V��--�@----���T��  �k�N  ���X�B��  --�I  ���^�Ԫ��V--�G�C  ----��M�����污    --    ��C���y�D�}���s  --�I��  �}  ����  ��h��  --�Ǭu���������--���P�a--  �e  ���ȸs�M�U�T�V�{�[�c�W    --  �S--�Ф��H��  ��  �b��  �����馥��T--�ĺ����e  ���|�X�ׯ�į�����p�d�Ŧ��J��  ������    --�Y�z��--  �|  ��  �T�T--�����--�k  --�A���--�˹����--�b�F  ��ԣ��  --���s��s--���m    ��  �����®�  �a--��  �ΤW�|�n�鮴�y--���c��֭��  ��  �D�ުٳj--�g--�A��  �~�өD��`�W  ���H----��  �V--  �͵c����  �ٲ���--  --��  �I--  --��Q�۬B--����  --  �v�ڨϫ�  �l���ܤh�@�U�ƫ�}�u--�O�ݾ�  �K��    �󥫫��    ���⭺�u--�°��G  ��--�ޮ��  ��βQ��--  �E�������Ƹp������--  �z--����  �ֱf--������A�L�I��--��C��  �n  ����  �m��  ��    ��  �����R��p�q  ���v�x��|���  �x�Q  --  �e��    �j��--��  �p�U���t�����췹�J    �Ļ[��  ��    ��H--�J�E�G��----  �u����    ��  �Ҷ�L���o��  --�Ͻ�L�a--�x���@��--�O�~--    ----�ȷ��    �Z��R�ұ�--��--��e��Ž���}�ս��I��  ��--��  ���k�^��  �M�S��  �k  ����  ��  ���  ���������c--�ѲK��в���Q�ɬD--�|����    ��--  --�ŧʰ��F�x�����q�����P  �͵���Ѷ��  �h����  �z�Y  ��--�{�~--�O�g�R�ߴ�--��  �L  �ưh�]���v�즫    ��    --���ݳ���z��  ����  �n�~��----��  �Y�J���J���ߵp�{�{��  �èL��`�P  ������Ѧk���޷L�M    ��--�߱�  --    ��e----��  ������ȭG���Q���  �L��  �E--�A��    �k  ��--���  --  --  �ںW  ���U��--  ----  ��--  ��^--��Z�����ȻR��V--��  �ફ��--��  �����R��ִ�����H�l    �}���Ʊx���i�����m�˦��R��  �u  �@��  �~�t��--  �M  �X��  �v----  �U--�L--��  ��P          ��  �C����          ��    --����  ��--  ���c����  ����  �Q  ��  �Ѿ󹳦V�H  �v�]�d��--  ��d�c--�p���ըv--���ķ��Ƿ�  �c------����    --����ɾӪn--  �h�~��  �Y���s��߫H  �P�{�V�i  �D���Ψ���������ʩm�S���ݦI--������ײۦ���  �q�S  �V����  --  �}  �W��--���ǯb�򵶴B      �٫�--�ۥ�    �t  �u��--�޳���--�t�`��  --  �����      ��--��    �r�X�ޤ�H�V�ŲP��----  �j�|  --�T  --���驥����    ���u�a�����l�t  ���P--  ����--�K�b    �o��  ��----��  �Ϭv  ���    --�y�ܸy��  ----      ���r��  �nģ���O�C  ���M�]  ��--  ���ũ]�G�@��  ��  �̥��  �i  ��--�غè^�y��--��  �ʤw�A�o�H  ������z--���r�h�w�̥�ǷN��--  �q��          �l��  ��  �]�ﭵ  �çu  �]�G  ����  �L�^----  --        --  ��  �ռv  �w�M------�s  �e�l  ��  �a--�ñv�i�Ϋ�--�y--�ץ�      �o  �����ͥk���V  �S�����J�_�������M  �E�\�O  �r��--����--�B  --��t  �Хɰ쨡��  �J��n  �U��  �|  �D�J��  ��    --�ޤ����K�촩  ------�᷽    �b  ��|��  �V    --  ��--  ��    --  ��      --  ���`�{  ��v--�_  �A�b��----      ���D�V  Ħ--�����Dļ���y�m  ��  ------  ��W����    ���--      �w--�^�Q�E��  �K  �v��--��¤--���ߪg  --  --�i��--��--����  �̳���s--�x--��V--  �M  �`�٩۬L��h  �Ӹn��F�l�B���    ��  ��  ��÷r�u�¯z��    --�E�l  �_��    �]--  ��    �þ�@���F--�p    �۪K��s�j���ϯץĤ�    ���Ӵ�--��˼�}���k�u��  ��----�ܭP�m--�Ϩ���X  ����--�v�����ة�  �J      ����  ��P�{�w  ��  �y���G  �z--  �]��禮      �v��  �N��  --�D�۬W�U�E      ��`��  �����--    ��  �f--    --��--  ��  �l  --    �Ǯ������Z�_�u�ֵۨ`--  �t  �����d�����J�󷺤l��--�r�O��  �v        �����~������گ�  ��    ġ�L�K�̸o�L��Q�����g���@���y      ----��----  ----------        --                  ';
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
*�N�S��r���ഫ��������URL�s�X�A�Τ_URL�ǿ餣�|�ް_�V��
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
//���}�s���f(�a�Ѽ�,�p�G���ǰѼƫh�����q�{��,�û��u�X�s���f)
function openwin1(url,iWidth,iHeight){
	if(iWidth==null||iWidth==0)
		iWidth=800;
	if(iHeight==null||iHeight==0)
		iHeight=520;
	var url;  //��V�������a�};
	var name='_blank'; //�����W�١A�i����;
	var iTop = (screen.availHeight-30-iHeight)/2; //��o���f��������m;
	var iLeft = (screen.availWidth-10-iWidth)/2; //��o���f��������m;
	window.open(url,name,'height='+iHeight+',innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=yes,resizeable=no,location=no,status=no');
}
//���}�s���f(�a�Ѽ�,�p�G���ǰѼƫh�����q�{��)
function openwin(url,iWidth,iHeight){
	if(iWidth==null||iWidth==0)
		iWidth=800;
	if(iHeight==null||iHeight==0)
		iHeight=520;
	var url;  //��V�������a�};
	var name='op_win'; //�����W�١A�i����;
	var iTop = (screen.availHeight-30-iHeight)/2; //��o���f��������m;
	var iLeft = (screen.availWidth-10-iWidth)/2; //��o���f��������m;
	window.open(url,name,'height='+iHeight+',innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=yes,resizeable=no,location=no,status=no');
}
//���}�s���f(�a�ݭn�ഫ���Ѽ�)
function openwinParam(url,param){
	openwin(url+convertSpecial2Code(param));
}
//�N��w�����s�]�m�����i�Ϊ��A(�Ydisabled���A)
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
 * �h���ӹ�H����:�ثe����checkbox
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
 * �K�[�Ϥ�^��w��\��
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
			//�ݮeIE7�BIE8
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
			//�ݮeIE7�BIE8
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
 * �h�������Ů�
 * @param {Object} obj
 */
function trimSpace(obj){
  // ��h��������b���Ů�
  var str = obj.value;
  str =  str.replace(/(^[\s|�@]*)|([\s|�@]*$)/g,"");
  // �A�⤤���h�ӥ��b���Ů�������@�ӥb���Ů�
  obj.value = str.replace(/[\s|�@]+/g, " ");
}

/**
 * �h�������Ů�
 * @param {String} value
 */
function trimSpaceExt(strValue){
  // ��h���������b���Ů�
  strValue =  strValue.replace(/(^[\s|�@]*)|([\s|�@]*$)/g,"");
  // �A�⤤���h�ӥ��b���Ů�������@�ӥb���Ů�
  return strValue.replace(/[\s|�@]+/g, " ");
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
 * ����H�K�[�����ƥ�ʧv
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
 * ���~�H����ܧ����
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