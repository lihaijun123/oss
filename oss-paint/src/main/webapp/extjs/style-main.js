/*
 * Ext Style Apply to Normal Page @version:2.0 @author:xdy @date:2008.5.6
 * 
 * IE FireFox 均測試通過
 */
// core
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
$importCSS('/resources/css/page_render.css');
// page_render
Ext.onReady(function() {
	// 默認等待圖標
	Ext.BLANK_IMAGE_URL = "resources/images/default/s.gif";
	// Ext.form.Field.prototype.msgTarget = '';
	// Ext.QuickTips.init();
	/**
	 * button
	 */
	var objArray = Ext.DomQuery.select("input[type=submit]");
	Ext.each(objArray, function(obj) {
		var btn_cfg = {
			text : obj.value,
			applyTo : obj,
			disabled : (obj.getAttribute('disabled') && obj
					.getAttribute('disabled') != 'false') ? true : false,
			type : 'button',
			style : 'display:inline'
		}
		if (btn_cfg.disabled == false) {
			btn_cfg.handler = function() {
				var onSub = btn.getEl().parent("form").dom.onsubmit;
				if (onSub) {
					if (onSub())
						btn.getEl().parent("form").dom.submit();
					else
						;
				} else if (!onSub) {
					btn.getEl().parent("form").dom.submit();
				}
			}
		}
		var btn = new Ext.Button(btn_cfg);
		btn.getEl().replace(Ext.get(obj));
	});
	var objArray = Ext.DomQuery.select("input[type=reset]");
	Ext.each(objArray, function(obj) {
		var btn_cfg = {
			text : obj.value,
			applyTo : obj,
			disabled : (obj.getAttribute('disabled') && obj
					.getAttribute('disabled') != 'false') ? true : false,
			type : 'button',
			style : 'display:inline'
		}
		if (btn_cfg.disabled == false) {
			btn_cfg.handler = function() {
				btn.getEl().parent("form").dom.reset();
			}
		}
		var btn = new Ext.Button(btn_cfg);
		btn.getEl().replace(Ext.get(obj));
	});
	var objArray = Ext.DomQuery.select("input[type=button]");
	Ext.each(objArray, function(obj) {
		btn_cfg = {
			text : obj.value,
			applyTo : obj,
			disabled : (obj.getAttribute('disabled') && obj
					.getAttribute('disabled') != 'false') ? true : false,
			type : 'button',
			style : 'display:inline'
		}
		if (btn_cfg.disabled == false) {
			btn_cfg.handler = obj.onclick;
		}
		if (obj.id && obj.id != '') {
			btn_cfg.id = obj.id;
		}
		var btn = new Ext.Button(btn_cfg);
		btn.getEl().replace(Ext.get(obj));
	});
	/**
	 * text and textarea and password and file
	 */
	var objArray = Ext.DomQuery.select("input[type=text]");
	Ext.each(objArray, function(obj) {
		// var p = Ext.DomHelper.insertBefore(obj, new Ext.Element({
		// tag : 'div',
		// cls : 'x-form-element'
		// }));
		// p.appendChild(obj);
		var txtField = new Ext.form.TextField({
			applyTo : obj
		});
	});
	var objArray = Ext.DomQuery.select("input[type=password]");
	Ext.each(objArray, function(obj) {
		var txtField = new Ext.form.TextField({
			applyTo : obj,
			inputType : "password"
		});
	});
	var objArray = Ext.DomQuery.select("input[type=file]");
	Ext.each(objArray, function(obj) {
	});
	var objArray = Ext.DomQuery.select("textarea");
	Ext.each(objArray, function(obj) {
		var txtArea = new Ext.form.TextArea({
			applyTo : obj
		});
	});
	/**
	 * checkbox
	 * 
	 * var objArray = Ext.DomQuery.select("input[type=checkbox]");
	 * Ext.each(objArray, function(obj) { var checkbox = new Ext.form.Checkbox({
	 * applyTo : obj }); });
	 */
	/**
	 * select or comboBox
	 */
	// var objArray = Ext.DomQuery.select("select");
	// Ext.each(objArray, function(obj) {
	// // if (obj.selectedIndex == 0 && obj.options[0].defaultSelected == true)
	// // {
	// // obj.options.add(new Option("請選擇", "asd"), 0);
	// // } else {
	// // obj.options.add(new Option("請選擇", "asd"), 0);
	// // obj.selectedIndex = 0;
	// // }
	// var div = Ext.DomHelper.insertAfter(obj, {
	// tag : 'div'
	// });
	// Ext.get(div).appendChild(obj);
	// if (obj.multiple == false) {
	// var combox = new Ext.form.ComboBox({
	// typeAhead : true,
	// triggerAction : "all",
	// transform : obj,
	// disabled : obj.disabled,
	// hidden : (obj.style.display == "none") ? true : false,
	// allowBlank : (obj.attributes.getNamedItem('nullable'))
	// ? true
	// : false,
	// forceSelection : true,
	// editable : (obj.attributes.getNamedItem('editable'))
	// ? true
	// : false,
	// lazyInit : false,
	// style : 'display:inline'
	// });
	// if (obj.onchange) {
	// combox.addListener("select", obj.onchange);
	// }
	// }
	// });
	/**
	 * tr color
	 */
	var objArray = Ext.DomQuery.select("tr");
	Ext.each(objArray, function(obj, index) {
		if (index % 2 == 1) {
			Ext.get(obj).addClass("o");
		}
	});
	/**
	 * required
	 */
	var objArray = Ext.DomQuery.select("*.required");
	Ext.each(objArray, function(obj) {
		obj.innerHTML = "<span class='requiredredstar'>*</span>"
				+ obj.innerHTML;
	});
});
// 關閉EXT生成的彈出框
function jump() {
	if(window.parent.Ext.getCmp('op_win2')!=null)//彈出框的彈出框
		window.parent.Ext.getCmp('op_win2').close();
	else
		window.parent.Ext.getCmp('op_win').close();
}
// 刪除前的確認提示框
function deleteConfirm(url) {
	if (confirm("你確定要刪除當前信息嗎？")) {
		window.location.href = url;
	}
}
//定義內置的trim()方法
String.prototype.trim=function(){return   this.replace(/(^\s*)|(\s*$)/g,"");}