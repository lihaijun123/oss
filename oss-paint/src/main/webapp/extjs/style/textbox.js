Ext.onReady(function() {
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
})