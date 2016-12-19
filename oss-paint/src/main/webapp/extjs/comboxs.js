
function Comboxs(url, ids, displayTexts) {
	this.comboxs = new Array();
	var _this = this;
	this.param = new Array();
	this.paramValues = new Array();
	Comboxs.prototype.listChange = function (a, b, c) {
		this.param = new Array();
		this.paramValues = new Array();         
          //參數數組
		var aIndex = this.getIndex(a.id);
		var i = 0;
		for (i = 0; i <= aIndex; i++) {
			this.param.push(this.comboxs[i].id);
			var aValue = Ext.get(this.comboxs[i].id).getValue();
			aValue = escape(aValue);
			this.paramValues.push(aValue);
		}
		Ext.Ajax.request({method:"POST", url:url, params:{"names":this.param, "values":this.paramValues}, success:function (response) {
			var x = response.responseText;
			var result = eval("[" + x + "]");
			var resultData = result[0].data;
			var aIndex = result[0].index;
			if (_this.comboxs[aIndex] != null) {
				_this.clearValue(aIndex);
				_this.comboxs[aIndex].clearValue();
				_this.comboxs[aIndex].store.loadData(resultData);
			}
		}});
	};
	Comboxs.prototype.getIndex = function (anID) {
		var index = -1;
		var i = 0;
		for (i = 0; i < this.comboxs.length; i++) {
			if (anID == this.comboxs[i].id) {
				index = i;
				break;
			}
		}
		return index;
	};
	/**根據index清空值 */
	Comboxs.prototype.clearValue = function (index) {
		var i = 0;
		for (i = index; i < this.comboxs.length; i++) {
			this.comboxs[i].clearValue();
			this.comboxs[i].store.loadData([]);
		}
	};
	var i = 0;
	for (i = 0; i < ids.length; i++) {
		var combo = new Ext.form.ComboBox({store:new Ext.data.SimpleStore({fields:["value", "name"], data:[["", ""]]}), valueField:"value", displayField:"name", typeAhead:true, mode:"local", id:ids[i], name:ids[i], triggerAction:"all", emptyText:displayTexts[i], selectOnFocus:true, applyTo:ids[i]});
		this.comboxs.push(combo);
		combo.on("select", function (a, b, c) {
			_this.listChange(a, b, c);
		});
	}
	Ext.Ajax.request({method:"POST", url:url, params:{"names":null, "values":null}, success:function (response) {
		var x = response.responseText;
		var result = eval("[" + x + "]");
		var resultData = result[0].data;
		var aIndex = result[0].index;
		if (_this.comboxs[aIndex] != null) {
			_this.clearValue(aIndex);
			_this.comboxs[aIndex].clearValue();
			_this.comboxs[aIndex].store.loadData(resultData);
		}
	}});
}

