Ext.onReady(function() {
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
})