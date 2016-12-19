/**
 * html页面标签脚本
 *
 * add by lihaijun
 *
 */


/**
 * 写select标签
 *
 */
function writeSelect(dataAry){
	var tagStr = "" + "<option value='0'>请选择</option>";
	for(var i = 0; i < dataAry.length; i ++){
		var value = (dataAry[i].split(","))[0];
		var text = (dataAry[i].split(","))[1];
		tagStr += "<option value=\"" + value + "\" name='op'>" + text + "</option>";
	}
	return tagStr;
}