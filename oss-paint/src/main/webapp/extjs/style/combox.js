Ext.onReady(function() {
	/**
	 * select or comboBox
	 */
	var objArray = Ext.DomQuery.select("select");
	Ext.each(objArray, function(obj) {
		var div = Ext.DomHelper.insertAfter(obj, {
			tag : 'div'
		});
		Ext.get(div).appendChild(obj);
		if (obj.multiple == false) {
			var combox = new Ext.form.ComboBox({
				typeAhead : true,
				triggerAction : "all",
				transform : obj,
				disabled : obj.disabled,
				hidden : (obj.style.display == "none") ? true : false,
				allowBlank : (obj.attributes.getNamedItem('nullable'))
						? true
						: false,
				forceSelection : true,
				editable : (obj.attributes.getNamedItem('editable'))
						? true
						: false,
				lazyInit : false,
				style : 'display:inline'
			});
			if (obj.onchange) {
				combox.addListener("select", obj.onchange);
			}
		}
	});
})