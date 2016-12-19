/**
 * textarea
 */
Ext.onReady(function() {
	var objArray = Ext.DomQuery.select("textarea");
	Ext.each(objArray, function(obj) {
		var txtArea = new Ext.form.TextArea({
			applyTo : obj
		});
	});
})