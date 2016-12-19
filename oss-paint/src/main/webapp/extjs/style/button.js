Ext.onReady(function() {
	/**
	 * button
	 */
	var objArray = Ext.DomQuery.select("input[type=submit]");
	Ext.each(objArray, function(obj) {
		var btn_cfg = {
			text : obj.value,
			applyTo : obj,
			handler : function() {
				btn.getEl().parent("form").dom.submit();
			},
			type : obj.type,
			style : 'display:inline'
		}
		var btn = new Ext.Button(btn_cfg);
		btn.getEl().replace(Ext.get(obj));
	});
	var objArray = Ext.DomQuery.select("input[type=reset]");
	Ext.each(objArray, function(obj) {
		var btn_cfg = {
			text : obj.value,
			applyTo : obj,
			handler : function() {
				btn.getEl().parent("form").dom.reset();
			},
			type : obj.type,
			style : 'display:inline'
		}
		var btn = new Ext.Button(btn_cfg);
		btn.getEl().replace(Ext.get(obj));
	});
	var objArray = Ext.DomQuery.select("input[type=button]");
	Ext.each(objArray, function(obj) {
		btn_cfg = {
			text : obj.value,
			applyTo : obj,
			handler : obj.onclick,
			type : obj.type,
			style : 'display:inline'
		}
		if (obj.id && obj.id != '') {
			btn_cfg.id = obj.id;
		}
		var btn = new Ext.Button(btn_cfg);
		btn.getEl().replace(Ext.get(obj));
	});