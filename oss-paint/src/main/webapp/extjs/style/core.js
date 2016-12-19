/*
 * Ext Style Apply to Normal Page @version:2.0 @author:xdy @date:2008.5.6
 * 
 * IE FireFox §¡´ú¸Õ³q¹L
 */
// private
function script_repeat_chk(head, script) {
	for (var i = 0; i < head.childNodes.length; i++) {
		var o = head.childNodes[i];
		if (o.tagName == 'SCRIPT' && o.src) {
			if (o.src == script.src) {
				return true;
			}
		}
	}
	return false;
}
// private
function css_repeat_chk(head, css) {
	for (var i = 0; i < head.childNodes.length; i++) {
		var o = head.childNodes[i];
		if (o.tagName == 'LINK' && o.href) {
			if (o.href == css.href) {
				return true;
			}
		}
	}
	return false;
}
// public
function $import(args) {
	var jsm_domhead = document.getElementsByTagName('HEAD')[0];
	if (typeof(arguments[0]) == 'string') {
		var js = document.createElement('script');
		js.type = 'text/javascript';
		js.src = arguments[0];
		if (script_repeat_chk(jsm_domhead, js) == false) {
			jsm_domhead.appendChild(js);
		}
	}
}
// public
function $importCSS(args) {
	var jsm_domhead = document.getElementsByTagName('HEAD')[0];
	if (typeof(arguments[0]) == 'string') {
		if (css_repeat_chk(jsm_domhead, arguments[0]) == false) {
			var css = document.createElement('link');
			css.rel = 'stylesheet';
			css.type = 'text/css';
			css.href = arguments[0];
			if (css_repeat_chk(jsm_domhead, css) == false) {
				jsm_domhead.appendChild(css);
			}
		}
	}
}
// public
function insertAfter(newElement, targetElement) {
	var parent = targetElement.parentNode;
	if (parent.lastChild == targetElement) {
		parent.appendChild(newElement);
	} else {
		parent.insertBefore(newElement, targetElement.nextSibling);
	}
}
// core code start
$importCSS('/resources/css/page_render.css');
Ext.BLANK_IMAGE_URL = "resources/images/default/s.gif";
