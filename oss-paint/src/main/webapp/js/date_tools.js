/**
 * 格式化日期
 * @param   p the pattern of your date "yyyy-MM-dd hh:mm:ss"
 * @author  hexuey
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
};
var DAY_OF_MONTH = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
var FEB_DAY_OF_MONTH = [29, 28, 28, 28];
function toDate(strDate, x, p) {
	if(x == null || !x) x = "-";
	if(p == null || !p) p = "ymd";
	var reg = /^(\d{0,4})[-]?(\d{1,2})[-]?(\d{0,2})([ ]?(\d{1,2})[:]?(\d{1,2})[:]?(\d{1,2}))?$/;
	var r = strDate.match(reg);
	if(r != null) {
		var y = parseInt(r[1],10);
		//remember to change this next century ;)
		if(y.toString().length <= 2) y += 2000;
		if(isNaN(y)) throw "年份格式不對!";
		var m = parseInt(r[2],10) - 1;
		var d = parseInt(r[3],10);
		var hh = parseInt(r[5],10);
		var mi = parseInt(r[6],10);
		var ss = parseInt(r[7],10);
		if(isNaN(m) || m < 0 || m >= 12 ) throw "月份格式不對!";
		if(isNaN(d) || d <= 0 ) {
			throw "天的格式不對!";
		} else if(m !=1 && d > DAY_OF_MONTH[m] ) {
			throw "天的格式不對!";
		} else if(m==1 && d > FEB_DAY_OF_MONTH[y%4]) {
			throw "天的格式不對!";
		} else {
		}
		if(r[4]) {
			if(isNaN(hh) || hh < 0 || hh > 23) throw "小時格式不對!";
			if(isNaN(mi) || mi < 0 || mi > 59) throw "分格式不對!";
			if(isNaN(ss) || ss < 0 || ss > 59) throw "秒格式不對!";
		} else {
			hh =0 ;
			mi =0;
			ss = 0;
		}
		return new Date(y, m, d,hh,mi,ss);
	} else {
		throw "日期格式不對!";
	}
}

//結束日期等于開始日期加一年
function addDateStartTimeAddOneYear(startTime, endTime) {
	var d1 = document.getElementById(startTime).value;
	if (d1.length > 0) {
		//var date1 = new Date(d1.replace(/\-/g, '\/'));
		try {
			var date1 = toDate(d1);
			date1.setFullYear(date1.getFullYear() + 1);
			date1.setDate(date1.getDate() - 1);
			if(date1.toString() == 'NaN' || date1.toString() == 'Invalid Date') {
				alert("日期格式不正確。");
				document.getElementById(endTime).value="";
				return ;
			}
			document.getElementById(endTime).value = date1.format("yyyy-MM-dd");
		} catch(e) {
			alert(e);
			document.getElementById(endTime).value="";
		}
	}
}

//結束日期等于開始日期加3個月
function addDateStartTimeAddThreeMonth(startTime, endTime) {
	var d1 = document.getElementById(startTime).value;
	if (d1.length > 0) {
		//var date1 = new Date(d1.replace(/\-/g, '\/'));
		try {
			var date1 = toDate(d1);
			date1.setMonth(date1.getMonth() + 3);
			date1.setDate(date1.getDate() - 1);
			if(date1.toString() == 'NaN' || date1.toString() == 'Invalid Date') {
				alert("日期格式不正確。");
				document.getElementById(endTime).value="";
				return ;
			}
			document.getElementById(endTime).value = date1.format("yyyy-MM-dd");
		} catch(e) {
			alert(e);
			document.getElementById(endTime).value="";
		}
	}
}

//結束日期等于開始日期加n個月
function addDateStartTimeAddMonths(startTime, endTime, n) {
  if(Number(n)<0){
    document.getElementById(endTime).value="";
    return;
  }
  var d1 = document.getElementById(startTime).value;
  if (d1.length > 0) {
    //var date1 = new Date(d1.replace(/\-/g, '\/'));
    try {
      var date1 = toDate(d1);
      date1.setMonth(date1.getMonth() + Number(n));
      date1.setDate(date1.getDate() - 1);
      if(date1.toString() == 'NaN' || date1.toString() == 'Invalid Date') {
        alert("日期格式不正確。");
        document.getElementById(endTime).value="";
        return ;
      }
      document.getElementById(endTime).value = date1.format("yyyy-MM-dd");
    } catch(e) {
      alert(e);
      document.getElementById(endTime).value="";
    }
  }
}

/**
 * 創建時間對象
 * 
 * @param val1
 * @return
 */
function getTime(val1) {
	var ss = val1.split(" ");
	var s1 = ss[0].split("-");
	var s2 = ss[1].split(":");
	var date = new Date();
	if (s1.length > 0) {
		date.setFullYear(s1[0], s1[1] - 1, s1[2]);
	} 
	if (s2.length > 0) {
		date.setHours(s2[0]);
		date.setMinutes(s2[1]);
		date.setSeconds(s2[2]);
	}
	return date;
}
/**
 * 時間對比
 * @return
 */
function compareDate(id1,id2){
	var v1 = $(id1).value;
	var v2 = $(id2).value;
	var d1 = getTime(v1);
	var d2 = getTime(v2);
	return d1>d2;
}