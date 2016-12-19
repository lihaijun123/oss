var regexEnum = {
	intege : "^-?[1-9]\\d*$",
	intege1 : "^[1-9]\\d*$",
	intege2 : "^-[1-9]\\d*$",
	num : "^([+-]?)\\d*\\.?\\d+$",
	num1 : "^[1-9]\\d*|0$",
	num2 : "^-[1-9]\\d*|0$",
	decmal : "^([+-]?)\\d*\\.\\d+$",
	decmal1 : "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$",
	decmal2 : "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$",
	decmal3 : "^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$",
	decmal4 : "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$",
	decmal5 : "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$",
	email : "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+$",
	color : "^[a-fA-F0-9]{6}$",
	url : "^([a-zA-z]+://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*).*)*(\\?\\S*)?$",
	chinese : "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]|+$",
	notchinese : "^[\\x00-\\xFF]+$",
	textareacheck : "^[\\x00-\\xFF\\u20ac\\u2122\\u3b1\\u3b2\\u3b3\\u3b4\\u3b7\\u3b8\\u3bb\\u3bc\\u3bd\\u3be\\u3c0\\u3c3\\u3c4\\u3c1\\u3c6\\u3c7\\u3c9\\u394\\u3a3\\u3a6\\u3a9\\u2014\\u2264\\u2265\\u2208\\u2030\\u2260\\u2228\\u2227\\u2211\\u221e\\u2261\\u221a\\u2018\\u20ac\\u2122\\u3b1\\u3b2\\u3b3\\u3b4\\u3b7\\u3b8\\u3bb\\u3bc\\u3bd\\u3be\\u3c0\\u3c3\\u3c4\\u3c1\\u3c6\\u3c7\\u3c9\\u394\\u3a3\\u3a6\\u3a9\\u2014\\u2264\\u2265\\u2208\\u2030\\u2260\\u2228\\u2227\\u2211\\u221e\\u2261\\u221a\\u2018]+$",
	ascii : "^[\\x00-\\xFF]+$",
	zipcode : "^\\d{6}$",
	mobile : "^(13|15)[0-9]{9}$",
	ip4 : "^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5]).(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5]).(d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5]).(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$",
	notempty : "\\S",
	picture : "(.*)\\.(jpg|gif|jpeg)$",
	rar : "(.*)\\.(rar|zip|7zip|tgz)$",
	date : "^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$",
	qq : "^[1-9]*[1-9][0-9]*$",
	tel : "(\\d{3}-|\\d{4}-)?(\\d{8}|\\d{7})",
	username : "^\\w+$",
	letter : "^[A-Za-z]+$",
	letter_u : "^[A-Z]+$",
	letter_l : "^[a-z]+$",
	password : "^[a-zA-Z0-9\-]{6,20}$",
	micmobile : "^[a-zA-Z0-9].*$",
	micusername : "^[a-zA-Z0-9\-]+$"
}
function isTime(str) {
	var a = str.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
	if (a == null) {
		return false
	}
	if (a[1] > 24 || a[3] > 60 || a[4] > 60) {
		return false;
	}
	return true;
}
function isDate(str) {
	var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
	if (r == null)
		return false;
	var d = new Date(r[1], r[3] - 1, r[4]);
	return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d
			.getDate() == r[4]);
}
function isDateTime(str) {
	var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
	var r = str.match(reg);
	if (r == null)
		return false;
	var d = new Date(r[1], r[3] - 1, r[4], r[5], r[6], r[7]);
	return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3]
			&& d.getDate() == r[4] && d.getHours() == r[5]
			&& d.getMinutes() == r[6] && d.getSeconds() == r[7]);
}

function checkkeyWord(str) {
	var error = true;
	var pattern = /[^\x00-\xff]/g;
	var msg = "";
	var p = jQuery.makeArray($(".full,.key,.tiny"));
	for (var i = 0; i < p.length; i++) {
		if ($.trim(p[i].value).length != 0) {
			if (pattern.test(p[i].value)) {
				error = false;
				msg = errorMsg1;
				break;
			}
		}
	}
	if (!error)
		return msg;
	var s = 0;
	for (var i = 0; i < p.length; i++) {
		if ($.trim(p[i].value).length == 0) {
			s++;
		}
		if (s == p.length) {
			error = false;
			msg = errorMsg3;
		}
	}
	return error ? error : msg;
}
function notChinese(value) {
	var pattern = /[^\x00-\xff]/g;
	if (pattern.test(value))
		return false;
	return true;
}
var cnen = [["!", "¡I"], ["@", "¡E"], ["$", "¢D"], ["^", "¡K¡K"], ["&", "¡X"],
		["(", "¡]"], [")", "¡^"], ["_", "¡X¡X"], [":", "¡J"], [";", "¡F"],
		["\"", "¡§"], ["\"", "¡¨"], ["'", "¡¥"], ["|", "|"], ["\\", "¡B"],
		["<", "¡m"], [",", "¡A"], [">", "¡n"], [".", "¡C"], ["?", "¡H"]];
var cnenCode = {
	65281 : 33,
	183 : 64,
	65509 : 36,
	8230 : 94,
	8212 : 38,
	65288 : 40,
	65289 : 41,
	8212 : 95,
	65306 : 58,
	65307 : 59,
	8220 : 34,
	8221 : 34,
	8216 : 39,
	124 : 124,
	12289 : 92,
	12298 : 60,
	65292 : 44,
	12299 : 62,
	12290 : 46,
	65311 : 63,
	8217 : 39,
	12304 : 91,
	12305 : 93
};
function replaceCn(str) {
	var result = "";
	var charCode = 0;
	var char1 = "";
	for (var i = 0; i < str.length; i++) {
		charCode = str.charCodeAt(i);
		if (charCode == 12288) {
			char1 = String.fromCharCode(charCode - 12256);
			result += char1;
			continue;
		}
		if (charCode > 65280 && charCode < 65375) {
			charCode = charCode - 65248;
		}
		if (cnenCode[charCode])
			char = String.fromCharCode(cnenCode[charCode]);
		else
			char = String.fromCharCode(charCode);
		result += char;
	}
	return result;
}
function cnToEn(obj) {
	if (obj.value) {
		var str = obj.value;
	} else {
		var str = obj;
	}
	str = replaceCn(str);
	var reg = /[\u4E00-\u9FA5]|[\uFE30-\uFFA0]|[\u300e-\u300f]|[\u3016-\u3017]|<textarea>|<\/textarea>|<textarea\/>/gi;
	str = str.replace(reg, "");
	if (obj.value == str) {
		return;
	}
	// tips
	obj.value = str;
}
function cnToEn4T(str) {
	str = replaceCn(str);
	var reg = /[\u4E00-\u9FA5]|[\uFE30-\uFFA0]/gi;
	str = str.replace(reg, "");
	return str;
}