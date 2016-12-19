//using
//<input type="text" value="" name="test" onclick="show(this,'yyyy-MM-dd hh:mm:ss')" />
//

//
var date = new Date();
var y = date.getFullYear();
var sTime = Number(y) - 5;
var eTime = Number(y) + 5;

/**
 * 返回日期
 * @param d the delimiter
 * @param p the pattern of your date yyyy-MM-dd hh:mm:ss
 */
String.prototype.toDate = function(x, p) {
	if(x == null || !x) x = "-";
	if(p == null || !p) p = "ymd";
	var nowT = new Date();
	var reg = /^(\d{0,4})[-]?(\d{1,2})[-]?(\d{0,2})([ ]?(\d{1,2})[:]?(\d{1,2})[:]?(\d{1,2}))?$/;
	var r = this.match(reg);
	if(r != null) {
		var y = parseInt(r[1],10);
		//remember to change this next century ;)
		if(y.toString().length <= 2) y += 2000;
		if(isNaN(y)) y = nowT.getFullYear();
		var m = parseInt(r[2],10) - 1;
		var d = parseInt(r[3],10);
		var hh = parseInt(r[5],10);
		var mi = parseInt(r[6],10);
		var ss = parseInt(r[7],10);
		if(isNaN(m)) m = nowT.getMonth();
		if(isNaN(d)) d = nowT.getDate();
		if(isNaN(hh)) hh = nowT.getHours();
		if(isNaN(mi)) mi = nowT.getMinutes();
		if(isNaN(ss)) ss = nowT.getSeconds();
		return new Date(y, m, d,hh,mi,ss);
	}
	return nowT;
}

/**
 * 格式化日期
 * @param   d the delimiter
 * @param   p the pattern of your date
 * @author  meizz
 */
Date.prototype.format = function(style) {
  var o = {
    "M+" : this.getMonth() + 1, //month
    "d+" : this.getDate(),      //day
    "h+" : this.getHours(),     //hour
    "m+" : this.getMinutes(),   //minute
    "s+" : this.getSeconds(),   //second
    "w+" : "天一二三四五六".charAt(this.getDay()),   //week
    "q+" : Math.floor((this.getMonth() + 3) / 3),  //quarter
    "S"  : this.getMilliseconds() //millisecond
  }
  if(/(y+)/.test(style)) {
    style = style.replace(RegExp.$1,
    (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  }
  for(var k in o){
    if(new RegExp("("+ k +")").test(style)){
      style = style.replace(RegExp.$1,
        RegExp.$1.length == 1 ? o[k] :
        ("00" + o[k]).substr(("" + o[k]).length));
    }
  }
  return style;
}

/**
 * 日期头
 * @param   beginYear 1990
 * @param   endYear   2010
 * @param   lang      0(中文)|1(英语) 可自由扩充
 * @param   dateFormatStyle  "yyyy-MM-dd";
 * @version 2006-04-01
 * @author  KimSoft (jinqinghua [at] gmail.com)
 * @update
 */
function Calendar(currentDate, beginYear, endYear, lang, dateFormatStyle,idStr) {
  this.dateFormatStyle = "yyyy-MM-dd";
  if(!currentDate) {
  	var now = new Date();
  	currentDate = now.format(this.dateFormatStyle);
  }
  this.currentDate =currentDate;
  var ymdArray = currentDate.split("-");
  this.nowYear = parseInt(ymdArray[0],10);
  this.nowMonth = ymdArray[1]-1;
  //this.nowDay = parseInt(ymdArray[2]);
  this.nowDay = ymdArray[2];
  this.beginYear = 1980;
  this.endYear = 2100;
  this.lang = 0;            //0(中文) | 1(英文)

  if (beginYear != null && endYear != null){
    this.beginYear = beginYear;
    this.endYear = endYear;
  }
  if (lang != null){
    this.lang = lang
  }

  if (dateFormatStyle != null){
    this.dateFormatStyle = dateFormatStyle
  }

  this.dateControl = null;
  this.panel = document.getElementById("calendarPanel");
  this.panelWin = window.calendarPanel;
  this.form  = null;

  this.year = 2008;
  this.month = 1;
  this.day = 1;
  this.hh = 0;
  this.mi = 0;
  this.ss = 0;
  this.days = 0;
  this.lastMonthShowDays = 0;
  this.nextMonthShowDays = 0;
  this.controlHasValue = false;
  this.panelShow = false;
  this.idArray = [];
  if(idStr!=null)
  {
  	if(idStr.indexOf(',')>0)
  		this.idArray=idStr.split(',');
	else
		this.idArray.push(idStr);
  }
  this.showHours = true;
  this.colors = {
	  "cur_word"      : "#FFFFFF",  //当日日期文字颜色
	  "cur_bg"        : "#00FF00",  //当日日期单元格背影色
	  "has_bg"        : "#F0FF00",  //目前日期单元格背影色
	  "sun_word"      : "#FF0000",  //星期天文字颜色
	  "sat_word"      : "#0000FF",  //星期六文字颜色
	  "td_word_light" : "#333333",  //单元格文字颜色
	  "td_word_dark"  : "#CCCCCC",  //单元格文字暗色
	  "td_bg_out"     : "#DFECFB",  //单元格背影色
	  "td_bg_over"    : "#DFECFB",  //单元格背影色
	  "td_bg"         : "#FFFFFF",  //单元格默认背景色
	  "tr_word"       : "#FFFFFF",  //日期头文字颜色
	  "tr_bg"         : "#666666",  //日期头背影色
	  "input_border"  : "#CCCCCC",  //input控件的边框顏色
	  "input_bg"      : "#DFECFB"   //input控件的背影色
  }
/**
  this.draw();
  this.bindYear();
  this.bindMonth();
  this.changeSelect();
  this.bindData();
**/
}

/**
 * 日期头属性（语言包，可自由扩展）
 */
Calendar.language = {
	  "year"   : [[""], [""]],
	  "months" : [["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
	              ["JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"]
	             ],
	  "weeks"  : [["S","M","T","W","T","F","S"],
	  			  ["日","一","二","三","四","五","六"]
	             ],
	  "clear"  : [["清空"], ["CLS"]],
	  "today"  : [["今天"], ["TODAY"]],
	  "close"  : [["关闭"], ["CLOSE"]]
}

Calendar.prototype.draw = function() {
	calendar = this;

	var mvAry = [];
	mvAry[mvAry.length]  = '<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">';
	mvAry[mvAry.length]  = '<html>';
	mvAry[mvAry.length]  = '<head>';
	mvAry[mvAry.length] = '<style>';
	mvAry[mvAry.length] = '#calendarForm input {width:16px;height:20px;}';
	mvAry[mvAry.length] = '#calendarForm select {font-size:12px;}';
	mvAry[mvAry.length] = '#calendarTable {width:100%;border:0px solid #FFFFFF;background-color: #FFFFFF;padding: 1px;}';
	mvAry[mvAry.length] = '.calendarButton {width:100%;height:20px;font-size:7px;cursor: pointer;}';
	mvAry[mvAry.length] = '#calendarTable tr {text-align: center;}';
	mvAry[mvAry.length] = '#calendarForm td {cursor: pointer;background-color: #FFFFFF}';
	mvAry[mvAry.length] = 'body {background-color: #DFECFB;font-size:12px;}';
	mvAry[mvAry.length] = '*{margin:0px;padding:0px;}';
	mvAry[mvAry.length] = '#hhmmss input{height:15px;border:1px solid #DFECFB;width:25px;text-align:right}';
	mvAry[mvAry.length] = '</style>';
	mvAry[mvAry.length] = '</head>';
	mvAry[mvAry.length] = '<body>';
	mvAry[mvAry.length]  = '  <form id="calendarForm">';
	mvAry[mvAry.length]  = '    <table width="100%" border="0" cellpadding="0" cellspacing="0">';
	mvAry[mvAry.length]  = '      <tr>';
	mvAry[mvAry.length]  = '        <th align="left" width="1%"><input style="cursor: pointer;border: 0px solid ' + calendar.colors["input_border"] + ';background-color:' + calendar.colors["input_bg"] + ';" type="button" id="prevYear" value="&lt;&lt;" /></th>';
	mvAry[mvAry.length]  = '        <th align="left" width="1%"><input style="cursor: pointer;border:0px;border-left: 1px solid ' + calendar.colors["input_border"] + ';background-color:' + calendar.colors["input_bg"] + ';" type="button" id="prevMonth" value="&lt;" /></th>';
	mvAry[mvAry.length]  = '        <th align="center" width="96%" nowrap="nowrap"><select id="calendarYear"></select><select id="calendarMonth"></select></th>';
	mvAry[mvAry.length]  = '        <th align="right" width="1%"><input style="cursor: pointer;border:0px;border-right: 1px solid ' + calendar.colors["input_border"] + ';background-color:' + calendar.colors["input_bg"] + ';" type="button" id="nextMonth" value="&gt;" /></th>';
	mvAry[mvAry.length]  = '        <th align="right" width="1%"><input style="cursor: pointer;border: 0px solid ' + calendar.colors["input_border"] + ';background-color:' + calendar.colors["input_bg"] + ';" type="button" id="nextYear" value="&gt;&gt;" /></th>';
	mvAry[mvAry.length]  = '      </tr>';
	mvAry[mvAry.length]  = '    </table>';
	mvAry[mvAry.length]  = '    <table id="calendarTable" border="0" cellpadding="0" cellspacing="0">';
	mvAry[mvAry.length]  = '      <tr>';
	for(var i = 0; i < 7; i++)
	{
		mvAry[mvAry.length]  = '      <th style="font-weight:normal;background-color:' + calendar.colors["td_bg_out"] + ';">' + Calendar.language["weeks"][this.lang][i] + '</th>';
	}
	mvAry[mvAry.length]  = '      </tr>';
	for(var i = 0; i < 6;i++)
	{
		mvAry[mvAry.length]  = '    <tr>';
		for(var j = 0; j < 7; j++)
		{
			if (j == 0)
			{
				mvAry[mvAry.length]  = '  <td style="color:' + calendar.colors["sun_word"] + ';"></td>';
			}
		  	else if(j == 6)
		 	{
		    	mvAry[mvAry.length]  = '  <td style="color:' + calendar.colors["sat_word"] + ';"></td>';
		  	}
		  	else
		  	{
		    	mvAry[mvAry.length]  = '  <td></td>';
		  	}
		}
		mvAry[mvAry.length]  = '    </tr>';
	}
	mvAry[mvAry.length]  = '      <tr id="hhmmss" style="background-color:' + calendar.colors["input_bg"] + ';height:10px;'+ (calendar.showHours?'':'display:none') +'">';
	mvAry[mvAry.length]  = '        <th colspan="8" valign="top"><input type="text" id="hh" value="" size="5" maxlength="5"/>:<input type="text" id="mi" value="" size="5" maxlength="5"/>:<input type="text" id="ss" value="" size="5" maxlength="5"/></th>';
	mvAry[mvAry.length]  = '      </tr>';
	mvAry[mvAry.length]  = '      <tr style="background-color:' + calendar.colors["input_bg"] + ';">';
	if(calendar.nowYear>=calendar.beginYear && calendar.nowYear <=calendar.endYear)
	{
		mvAry[mvAry.length]  = '        <th colspan="2"><input type="button" id="calendarClear" value="' + Calendar.language["clear"][this.lang] + '" style="cursor: pointer;border: 1px solid ' + calendar.colors["input_border"] + ';background-color:' + calendar.colors["input_bg"] + ';width:100%;height:20px;font-size:12px;"/></th>';
		mvAry[mvAry.length]  = '        <th colspan="3"><input type="button" id="calendarToday" value="' + Calendar.language["today"][this.lang] + '" style="cursor: pointer;border: 1px solid ' + calendar.colors["input_border"] + ';background-color:' + calendar.colors["input_bg"] + ';width:100%;height:20px;font-size:12px;"/></th>';
		mvAry[mvAry.length]  = '        <th colspan="2"><input type="button" id="calendarClose" value="' + Calendar.language["close"][this.lang] + '" style="cursor: pointer;border: 1px solid ' + calendar.colors["input_border"] + ';background-color:' + calendar.colors["input_bg"] + ';width:100%;height:20px;font-size:12px;"/></th>';
	}
	else
	{
		mvAry[mvAry.length]  = '        <th colspan="4"><input type="button" id="calendarClear" value="' + Calendar.language["clear"][this.lang] + '" style="cursor: pointer;border: 1px solid ' + calendar.colors["input_border"] + ';background-color:' + calendar.colors["input_bg"] + ';width:100%;height:20px;font-size:12px;"/></th>';
		mvAry[mvAry.length]  = '        <th colspan="4"><input type="button" id="calendarClose" value="' + Calendar.language["close"][this.lang] + '" style="cursor: pointer;border: 1px solid ' + calendar.colors["input_border"] + ';background-color:' + calendar.colors["input_bg"] + ';width:100%;height:20px;font-size:12px;"/></th>';
	}
	mvAry[mvAry.length]  = '      </tr>';
	mvAry[mvAry.length]  = '    </table>';
	mvAry[mvAry.length]  = '  </form>';
	mvAry[mvAry.length]  = '</body>';
	mvAry[mvAry.length]  = '</html>';
//
  //this.panel.innerHTML = mvAry.join("");
  this.panelWin.document.open();
  this.panelWin.document.writeln(mvAry.join("\r\n"));
  //this.form = document.forms["calendarForm"];
  this.form=this.panelWin.document.forms["calendarForm"];

  this.form.prevMonth.onclick = function () {calendar.goPrevMonth(this);}
  this.form.nextMonth.onclick = function () {calendar.goNextMonth(this);}
  this.form.prevYear.onclick = function () {calendar.goPrevYear(this);}
  this.form.nextYear.onclick = function () {calendar.goNextYear(this);}

  this.form.calendarClear.onclick = function () {calendar.dateControl.value = "";calendar.hide();calendar.dateControl.focus();}
  this.form.calendarClose.onclick = function () {calendar.hide();calendar.dateControl.focus();}
  this.form.calendarYear.onchange = function () {calendar.update(this);}
  this.form.calendarMonth.onchange = function () {calendar.update(this);}
  if(calendar.nowYear>=calendar.beginYear && calendar.nowYear <=calendar.endYear)
  {
	  	this.form.calendarToday.onclick = function () {
	    	calendar.year = calendar.nowYear;
	    	calendar.month = calendar.nowMonth;
	    	calendar.changeSelect();
	    	calendar.bindData();
	    	//calendar.year,calendar.month,calendar.nowDay
	    	calendar.dateControl.value = new Date().format(calendar.dateFormatStyle);
	    	calendar.hide();
			calendar.dateControl.focus();
	  }
  }
}

//年份下拉框绑定数据源
Calendar.prototype.bindYear = function() {
  var cy = this.form.calendarYear;
  cy.length = 0;
  for (var i = this.beginYear; i <= this.endYear; i++){
    cy.options[cy.length] = new Option(i + Calendar.language["year"][this.lang], i);
  }
}

//月份下拉框绑定数据源
Calendar.prototype.bindMonth = function() {
  var cm = this.form.calendarMonth;
  cm.length = 0;
  for (var i = 0; i < 12; i++){
    cm.options[cm.length] = new Option(Calendar.language["months"][this.lang][i], i);
  }
}
//向前一年
Calendar.prototype.goPrevYear = function(e){
  if (this.year == this.beginYear)
  {
  	this.form.prevYear.disabled=true;
  	return;
  }
  this.form.prevYear.disabled=false;
  this.form.nextYear.disabled=false;
  this.year--;
  this.changeSelect();
  this.bindData();
}

//向前一月
Calendar.prototype.goPrevMonth = function(e){
  if (this.year == this.beginYear && this.month == 0)
  {
  	this.form.prevMonth.disabled=true;
  	return;
  }
  this.form.prevMonth.disabled=false;
  this.form.nextMonth.disabled=false;
  this.month--;
  if (this.month == -1) {
    this.year--;
    this.month = 11;
  }
  this.changeSelect();
  this.bindData();
}

//向后一月
Calendar.prototype.goNextMonth = function(e){
  if (this.year == this.endYear && this.month == 11)
  {
  	this.form.nextMonth.disabled=true;
  	return;
  }
  this.form.prevMonth.disabled=false;
  this.form.nextMonth.disabled=false;
  this.month++;
  if (this.month == 12) {
    this.year++;
    this.month = 0;
  }
  this.changeSelect();
  this.bindData();
}
//向后一年
Calendar.prototype.goNextYear = function(e){
  if (this.year == this.endYear)
  {
  	this.form.nextYear.disabled=true;
  	return;
  }
  this.form.prevYear.disabled=false;
  this.form.nextYear.disabled=false;
  this.year++;
  this.changeSelect();
  this.bindData();
}
//改变SELECT选中状态
Calendar.prototype.changeSelect = function() {
  var cy = this.form.calendarYear;
  var cm = this.form.calendarMonth;
  for (var i= 0; i < cy.length; i++){
    if (cy.options[i].value == this.year){
      cy[i].selected = true;
      break;
    }
  }
  for (var i= 0; i < cm.length; i++){
    if (cm.options[i].value == this.month){
      cm[i].selected = true;
      break;
    }
  }
}

//更新年、月
Calendar.prototype.update = function (e){
  this.year  = e.form.calendarYear.options[e.form.calendarYear.selectedIndex].value;
  this.month = e.form.calendarMonth.options[e.form.calendarMonth.selectedIndex].value;
  this.changeSelect();
  this.bindData();
}
Calendar.prototype.updateTargetValue = function(e,y,m,curDate) {
	this.year  = y?y:e.form.calendarYear.options[e.form.calendarYear.selectedIndex].value;
	if(typeof m == 'undefined' || m == null) {
		this.month = e.form.calendarMonth.options[e.form.calendarMonth.selectedIndex].value;
	} else {
		this.month = m;
	}
	var hh = parseInt(e.getElementById("hh").value,10);
	var mm = parseInt(e.getElementById("mi").value,10);
	var ss = parseInt(e.getElementById("ss").value,10);
	e.dateControl.value = new Date(this.year,this.month,parseInt(curDate,10),hh,mm,ss).format(calendar.dateFormatStyle);
}
//绑定数据源到月视图
Calendar.prototype.bindData = function () {
  var calendar = this;
  if(calendar.year == calendar.beginYear) {
  	calendar.form.prevYear.disabled=true;
  }
  if(calendar.year == calendar.endYear) {
  	calendar.form.nextYear.disabled=true;
  }
  if (calendar.year == calendar.beginYear && calendar.month == 0)
  {
  	calendar.form.prevMonth.disabled=true;
  }
  else
  {
  	calendar.form.prevMonth.disabled=false;
  }
  if (calendar.year == calendar.endYear && calendar.month == 11)
  {
  	calendar.form.nextMonth.disabled=true;
  }
  else
  {
  	calendar.form.nextMonth.disabled=false;
  }
  var calendarDateFormatStyle = new Date(this.year,this.month,this.day).format(calendar.dateFormatStyle);
  var nowDateFormatStyle = new Date(this.nowYear,this.nowMonth,this.nowDay).format(calendar.dateFormatStyle);
  //
  var dateArray = this.getMonthViewArray(this.year, this.month);
  //
	this.getElementById("hh").value = this.hh;
	this.getElementById("mi").value = this.mi;
	this.getElementById("ss").value = this.ss;
  //
  var tds = this.getElementById("calendarTable").getElementsByTagName("td");

  var tempYear = parseInt(calendar.year,10);
  var tempMonth = parseInt(calendar.month,10);
  for(var i = 0; i < tds.length; i++) {
	tds[i].onclick = function () {return;}
	tds[i].onmouseover = function () {return;}
	tds[i].onmouseout = function () {return;}
  	tds[i].style.color = "#000000";
  	tds[i].style.backgroundColor = "#FFFFFF";
	tds[i].style.fontWeight="normal";
	tds[i].style.border="";
	tds[i].title="";

	if(i < this.lastMonthShowDays) {
		 tds[i].style.color = "#AAAAAA";
		 tds[i].innerHTML = dateArray[i];
		 tds[i].onclick = function () {
		    if (calendar.dateControl != null){
		    	 tempMonth--;
		    	 if(tempMonth==-1)
		    	 {
					if(tempYear==calendar.beginYear)
					{
					  tempMonth++;
					  return;
					}
					tempYear--;
		    	 	tempMonth=11;
		    	 }
		    	 this.day = parseInt(this.innerHTML,10);
		         calendar.updateTargetValue(calendar,tempYear,tempMonth,this.day);
		    }
		    calendar.hide();
			calendar.dateControl.focus();
		 }
	    tds[i].onmouseover = function () {
		   this.style.color = "#000000";
		   this.style.backgroundColor = "#DFECFB";
	    }
	  	tds[i].onmouseout = function () {
		    this.style.color = "#AAAAAA";
		    this.style.backgroundColor = "#FFFFFF";
		}
	}else if(i >= (this.lastMonthShowDays+this.days)){
		 tds[i].style.color = "#AAAAAA";
		 tds[i].innerHTML = dateArray[i];
		 tds[i].onclick = function () {
		    if (calendar.dateControl != null){
		    	 tempMonth++;
		    	 if(tempMonth==12)
		    	 {
				    if(tempYear==calendar.endYear)
					{
					  tempMonth--;
					  return;
					}
					tempYear++;
		    	 	tempMonth=0;
		    	 }
		    	 this.day = parseInt(this.innerHTML,10);
				calendar.updateTargetValue(calendar,tempYear,tempMonth,this.day);
		    }
		    calendar.hide();
			calendar.dateControl.focus();
		 }
	    tds[i].onmouseover = function () {
		   this.style.color = "#000000";
		   this.style.backgroundColor = "#DFECFB";
	    }
	  	tds[i].onmouseout = function () {
		    this.style.color = "#AAAAAA";
		    this.style.backgroundColor = "#FFFFFF";
		}
	}
	else
	{
	    	  tds[i].innerHTML = dateArray[i];

		      tds[i].onclick = function () {
		        if (calendar.dateControl != null){
		          this.day = parseInt(this.innerHTML,10);
		          calendar.updateTargetValue(calendar,null,null,this.day);
		        }
		        calendar.hide();
				calendar.dateControl.focus();
		      }

			  tds[i].onmouseover = function () {
				  this.style.backgroundColor = "#DFECFB";
			  }
			  tds[i].onmouseout = function () {
				  this.style.backgroundColor = "#FFFFFF";
			  }

		      var tempDateFormatStyle=new Date(calendar.year,calendar.month,dateArray[i]).format(calendar.dateFormatStyle);
			      if (calendarDateFormatStyle == tempDateFormatStyle) {
				  	if(calendar.controlHasValue)
					{
						tds[i].title="已选择";
					}
			        tds[i].style.fontWeight="bolder";
			        tds[i].style.backgroundColor = "#CCFF33";

			        tds[i].onmouseover = function () {
			          this.style.backgroundColor = "#CCCCFF";
			        }
			        tds[i].onmouseout = function () {
			          this.style.backgroundColor = "#CCFF33";
			        }
			      }

		      if (nowDateFormatStyle == tempDateFormatStyle) {
		        tds[i].style.border="1px solid darkred";
		        tds[i].title="今天";
		        if(calendar.controlHasValue==false){
		        	tds[i].style.fontWeight="bolder";
		        	tds[i].style.backgroundColor = "#CCFF33";
		        }
		        tds[i].onmouseover = function () {
		          if(calendar.controlHasValue)
		          {
		          	this.style.backgroundColor = "#CCCCFF";
		          }
		          else
		          {
					this.style.backgroundColor = "#FFFFFF";
		          }
		        }
		        tds[i].onmouseout = function () {
		          if(calendar.controlHasValue)
		          {
		          	this.style.backgroundColor = "#FFFFFF";
		          }
		          else
		          {
					this.style.backgroundColor = "#CCFF33";
		          }
		        }
		      }
	 }
  }
}

//根据年、月得到月视图数据(数组形式)
Calendar.prototype.getMonthViewArray = function (y, m) {
  var mvArray = [];
  var dayOfFirstDay = new Date(y, m, 1).getDay();
  //var daysOfMonth = new Date(y, m + 1, 0).getDate();
  var daysOfMonth = new Date(y, parseInt(m,10)+1 , 0).getDate();
  this.days = daysOfMonth;
  if(m==0)
  {
  	y--;
  }
  var daysOfLastMonth = new Date(y, m, 0).getDate();
  for (var i = 0; i < 42; i++) {
    mvArray[i] = "&nbsp;";
  }
  this.lastMonthShowDays = dayOfFirstDay;
  for (var i = dayOfFirstDay-1; i >= 0; i--, daysOfLastMonth--){
  	mvArray[i]=daysOfLastMonth;
  }
  for (var i = 0; i < daysOfMonth; i++){
    mvArray[i + dayOfFirstDay] = i + 1;
  }
  var j=1;
  for (var i = (dayOfFirstDay+daysOfMonth); i < 42; i++,j++){
	mvArray[i] = j;
  }
  this.nextMonthShowDays = j-1;
  return mvArray;
}

//扩展 document.getElementById(id) 多浏览器兼容性 from meizz tree source
Calendar.prototype.getElementById = function(id){
  if (typeof(id) != "string" || id == "") return null;
  if (panelWin.document.getElementById) return panelWin.document.getElementById(id);
  if (panelWin.document.all) return panelWin.document.all(id);
  try {return eval(id);} catch(e){ return null;}
}

//扩展object.getElementsByTagName(tagName)
Calendar.prototype.getElementsByTagName = function(object, tagName){
  if (document.getElementsByTagName) return document.getElementsByTagName(tagName);
  if (document.all) return document.all.tags(tagName);
}

//取得HTML控件绝对位置
Calendar.prototype.getAbsPoint = function (e){
  var x = e.offsetLeft;
  var y = e.offsetTop;
  while(e = e.offsetParent){
    x += e.offsetLeft;
    y += e.offsetTop;
  }
  return {"x": x, "y": y};
}

//显示日期
Calendar.prototype.show = function (dateControl, popControl) {
  if (dateControl == null){
    throw new Error("arguments[0] is necessary")
  }
  this.dateControl = dateControl;
/*
  if (dateControl.value.length > 0){
	  var ymdArray = dateControl.value.split("-");
	  var year = parseInt(ymdArray[0]);
	  var month = ymdArray[1]-1;
	  //var day = parseInt(ymdArray[2]);
	  var day = ymdArray[2];
	  this.year = year;
	  this.month = month;
	  this.day = day;
  }
  else
  {
  	  var date=null;
  	  if(this.nowYear>=this.beginYear && this.nowYear <=this.endYear)
	  {
		date = new Date(this.nowYear,this.nowMonth,this.nowDay);
	  }
	  else
	  {
		date = new Date(this.endYear,0,1);
	  }
	  this.year = date.getFullYear();
	  this.month = date.getMonth();
	  this.day = date.getDate();
  }
*/
  this.controlHasValue = true;
  var nowValue = this.currentDate || dateControl.value;
  nowValue = nowValue.toDate(null,this.dateFormatStyle);
  this.year = nowValue.getFullYear();
  this.month = nowValue.getMonth();
  this.day = nowValue.getDate();
  this.hh = nowValue.getHours();
  this.mi = nowValue.getMinutes();
  this.ss = nowValue.getSeconds();
  //
  this.draw();
  this.bindYear();
  this.bindMonth();
  this.changeSelect();
  this.bindData();
  if (popControl == null){
    popControl = dateControl;
  }
  var xy = this.getAbsPoint(popControl);
  this.panel.style.left = xy.x + "px";
  this.panel.style.top = (xy.y + dateControl.offsetHeight) + "px";
  this.setDisplayStyleForSelect("hidden");
  this.panelShow = true;
  //this.panel.style.visibility = "visible";
  this.panel.style.display = "";
}

//隐藏日期
Calendar.prototype.hide = function() {
  this.setDisplayStyleForSelect("visible");
  this.panelShow = false;
 // this.panel.style.visibility = "hidden";
 this.panel.style.display = "none";
if(this.dateControl.onchange)
	this.dateControl.onchange.apply(this.dateControl);
}

//设置控件显示或隐藏
Calendar.prototype.setDisplayStyle = function(tagName, style) {
	if (window.navigator.userAgent.indexOf("Firefox") < 0) {
  		var tags = this.getElementsByTagName(null, tagName)
  		for(var i = 0; i < tags.length; i++) {
    		if (tagName.toLowerCase() == "select" && (tags[i].id == "calendarYear" || tags[i].id == "calendarMonth")){
      			continue;
    }
    //tags[i].style.visibility=tags[i].style.visibility=='visible'?'hidden':'visible';
    tags[i].style.visibility = style;
  }
 }
}
//根据select控件id数组设置控件显示或者隐藏
Calendar.prototype.setDisplayStyleForSelect = function(style) {
	if (window.navigator.userAgent.indexOf("Firefox") < 0) {
		var tempObj=this;
  		for(var i = 0; i < tempObj.idArray.length; i++) {
    		document.getElementById(tempObj.idArray[i]).style.visibility = style;
		}
  }
}
document.write('<iframe id="calendarPanel" name="calendarPanel" style="position: absolute;display:none;z-index: 1000;border: 1px solid #718BB7;width:181px;height:165px;" src="about:blank" frameBorder="0" scrolling="no"></iframe>');

window.panelWin = window.frames["calendarPanel"];

/**
var calendarGlobal=null;
document.onclick=function(e)
{
	if(calendarGlobal)
		calendarGlobal.hide();
}
function searchEvent(e){
	caller=searchEvent.caller;
	while(!caller.arguments[0] || !caller.arguments[0] instanceof Event){
		caller=caller.caller;
	}
	return caller.arguments[0];
}
function stopEvent(e){
e=e||window.event||searchEvent(e);
if(e.stopPropagation)
	e.stopPropagation();
else
	e.cancelBubble=true;
if(e.preventDefault)
	e.preventDefault();
else
	e.returnValue=false;
}
**/

Calendar.prototype.AddEventHandler =function(oTarget,sEventType,fnHandler) {
	if(!oTarget) return ;
	if(oTarget.addEventListener) {
		oTarget.addEventListener(sEventType,fnHandler,false);
	} else if(oTarget.attachEvent) {
		oTarget.attachEvent("on"+sEventType,fnHandler);
	} else{
		oTarget["on"+sEventType] = fnHandler;
	}
}

function showCalendar(curObj, dateFormatStyle, flag) {
    if (typeof(flag) == 'undefined') {
        var calendarGlobal = new Calendar(curObj.value, null, null, 0, dateFormatStyle, arguments[5]);
    }
    else {
        var calendarGlobal = new Calendar(curObj.value, sTime, eTime, 0, dateFormatStyle, arguments[5]);
    }
    calendarGlobal.showHours = dateFormatStyle.indexOf(" ") > 0;
    window.panelWin = window.panelWin ? window.panelWin : window.frames["calendarPanel"];
    calendarGlobal.panelWin = window.panelWin;
    calendarGlobal.panelWin.document.close();
    calendarGlobal.show(curObj);
    calendarGlobal.panelWin.document.close();
    /*
     calendarGlobal.AddEventHandler(document,"click",function(ev){
     ev= ev||window.event;
     ev = ev.target?ev.target:ev.srcElement;
     if(ev == curObj) {
     return;
     }
     calendarGlobal.setDisplayStyleForSelect("visible");
     calendarGlobal.panelShow = false;
     calendarGlobal.panel.style.display = "none";
     });
     */
}

function showWithDef(curObj, dateFormatStyle, flag) {
    var v = "";
    if (curObj.value == "") {
        var t = new Date();
        var tmp = t.format("yyyy-MM-dd");
        v = tmp + " 00:00:00";
    }
    else {
        v = curObj.value;
    }
    if (typeof(flag) == 'undefined') {
        var calendarGlobal = new Calendar(v, null, null, 0, dateFormatStyle, arguments[5]);
    }
    else {
        var calendarGlobal = new Calendar(v, sTime, eTime, 0, dateFormatStyle, arguments[5]);
    }
    calendarGlobal.showHours = dateFormatStyle.indexOf(" ") > 0;
    window.panelWin = window.panelWin ? window.panelWin : window.frames["calendarPanel"];
    calendarGlobal.panelWin = window.panelWin;
    calendarGlobal.panelWin.document.close();
    calendarGlobal.show(curObj);
    calendarGlobal.panelWin.document.close();

}

function showWithDefEnd(curObj,dateFormatStyle)
{
	var v = "";
	if(curObj.value==""){
		var t = new Date();
		var tmp = t.format("yyyy-MM-dd");
		v = tmp+" 23:59:59";
	}else{
		v = curObj.value;
	}
	var calendarGlobal=new Calendar(v, null, null, 0, dateFormatStyle,arguments[5]);
	calendarGlobal.showHours = dateFormatStyle.indexOf(" ") >0;
	window.panelWin = window.panelWin?window.panelWin:window.frames["calendarPanel"];
	calendarGlobal.panelWin = window.panelWin;
	calendarGlobal.panelWin.document.close();
	calendarGlobal.show(curObj);
	calendarGlobal.panelWin.document.close();

}

function show(curObj, dateFormatStyle, flag) {
    if (typeof(flag)=='undefined') {
        showCalendar(curObj, dateFormatStyle);
    }
    else {
        showCalendar(curObj, dateFormatStyle, sTime, eTime);
    }
}

