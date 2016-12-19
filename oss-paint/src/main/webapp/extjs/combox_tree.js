/**
 * combox下拉選擇樹
 * 
 * @author zhanghua
 */
function ComBoxTree(config, compID) {
	var treeField02 = new Ext.form.TreeField();
	treeField02.init({
		name : config.name,
		url : config.url,
		value : config.value
	});
	treeField02.applyToMarkup(compID);
	treeField02.setGivenValue2(config.value, config.text);
}
Ext.form.TreeField = Ext.extend(Ext.form.TriggerField, {
	defalutValue : null,
	// global array
	array : new Array(),
	// all nodes getAllNodes
	allNodes : new Array(),
	root : null,
	getAllNodes : function(aNode) {
		if (aNode.leaf) {
			this.add2Array({
				'id' : aNode.id,
				'ui' : aNode
			}, this.allNodes);
		} else {
			var i = 0;
			var childs = aNode.childNodes;
			for (i = 0; i < childs.length; i++) {
				this.add2Array({
					'id' : childs[i].id,
					'ui' : childs[i]
				}, this.allNodes);
				this.getAllNodes(childs[i]);
			}
		}
	},

	hasNode2 : function hasNode2(obj, nodeArray) {
		var i = 0;
		var exitFlag = false;
		for (i = 0; i < nodeArray.length; i++) {
			if (nodeArray[i].id == obj.id) {
				exitFlag = true;
				return exitFlag;
			}
		}

		return exitFlag;
	},

	add2Array : function(node, array) {
		var i = 0;
		var len = array.length;
		if (hasNode2(node, array)) {
		} else {
			array.push(node);
		}

	},
	getNodeByID : function(myID) {
		var selectedNode = null;
		var len = this.allNodes.length;
		for (j = 0; j < len; j++) {
			if (this.allNodes[j].id == myID) {
				selectedNode = this.allNodes[j];
			}
		}
		return selectedNode;
	},
	selectNode : function(nodeID) {
		var j = 0;
		var selectedNode = null;
		var len = this.allNodes.length;
		for (j = 0; j < len; j++) {
			if (this.allNodes[j].id == nodeID) {
				selectedNode = this.allNodes[j].ui;
			}
			this.allNodes[j].ui.collapse();
		}
		if (selectedNode == null) {
			return;
		}
		var parArray = this.getParents(selectedNode);
		this.setGivenValue(selectedNode);
		this.expandGivenNodes(parArray);

	},

	setGivenValue : function(aNode) {
		var text = aNode.text;
		var myID = aNode.id;
		Ext.form.TreeField.superclass.setValue.call(this, text);
		if (this.hiddenField) {
			this.hiddenField.value = myID;
		}
	},

	setGivenValue2 : function(myID, text) {
		if (myID == "" || myID == null) {
			this.hasDefaultValue = false;
			return;
		}
		this.hasDefaultValue = true;
		Ext.form.TreeField.superclass.setValue.call(this, text);
		if (this.hiddenField) {
			this.hiddenField.value = myID;
		}

	},

	expandAllNodes : function() {
		var len = this.allNodes.length;
		for (j = 0; j < len; j++) {
			this.allNodes[j].expand();
		}
	},
	expandGivenNodes : function(nodes) {
		var len = nodes.length;
		for (j = 0; j < len; j++) {
			nodes[j].expand();
		}
	},
	getParents : function(aNode) {
		var parArray = new Array();
		var pN = aNode.parentNode;
		while (pN.id != "root") {
			parArray.push(pN);
			pN = pN.parentNode;
		}
		return parArray;
	},
	selectNodes : function(nodes) {
		this.allNodes = new Array();
		this.getAllNodes(this.root);
		var len = nodes.length;
		var k = 0;
		for (k = 0; k < len; k++) {
			this.selectNode(nodes[k]);
		}

	},
	/*
	 * getChecked : function(obj) { var i = 0; var res = obj;//
	 * (document.forms[0].resources); for (i = 0; i < array.length; i++) {
	 * res.options[res.options.length] = new Option(array[i], array[i]);
	 * res.options[res.options.length - 1].selected = true; } },
	 * 
	 * getSelectedItems : function() { allNodes = new Array();
	 * getAllNodes(root); array = new Array(); for (i = 0; i < allNodes.length;
	 * i++) { if (allNodes[i].ui.checked) { array.push(allNodes[i].id); } }
	 * getChecked(this.obj); },
	 */
	// ///////////////////////////////////////////////////////////
	/**
	 * @cfg {Boolean} readOnly 設置為只讀狀態
	 * 
	 */
	readOnly : true,
	/**
	 * @cfg {String} displayField 用于顯示數據的字段名
	 * 
	 */
	displayField : 'text',
	/**
	 * @cfg {String} valueField 用于保存真實數據的字段名
	 */
	valueField : null,
	/**
	 * @cfg {String} hiddenName 保存真實數據的隱藏域名
	 */
	hiddenName : null,
	/**
	 * @cfg {Integer} listWidth 下拉框的寬度
	 */
	listWidth : null,
	/**
	 * @cfg {Integer} minListWidth 下拉框最小寬度
	 */
	minListWidth : 300,
	/**
	 * @cfg {Integer} listHeight 下拉框高度
	 */
	listHeight : null,
	/**
	 * @cfg {Integer} minListHeight 下拉框最小高度
	 */
	minListHeight : 200,
	/**
	 * @cfg {String} dataUrl 數據地址
	 */
	dataUrl : null,
	/**
	 * @cfg {Ext.tree.TreePanel} tree 下拉框中的樹
	 */
	tree : null,
	/**
	 * @cfg {String} value 默認值
	 */
	value : null,
	/**
	 * @cfg {String} displayValue 用于顯示的默認值
	 */
	displayValue : null,
	/**
	 * @cfg {Object} baseParams 向後台傳遞的參數集合
	 */
	baseParams : {},
	/**
	 * @cfg {Object} treeRootConfig 樹根節點的配置參數
	 */

	hasDefaultValue : false,
	treeRootConfig : {
		id : 'root',
		text : '請選擇...',
		draggable : false
	},
	/**
	 * @cfg {String/Object} autoCreate A DomHelper element spec, or true for a
	 *      default element spec (defaults to {tag: "input", type: "text", size:
	 *      "24", autocomplete: "off"})
	 */
	defaultAutoCreate : {
		tag : "input",
		type : "text",
		size : "24",
		autocomplete : "on"
	},

	init : function(config) {
		this.hiddenName = config.name;
		this.dataUrl = config.url;
		this.defalutValue = config.value;
		if (this.defalutValue != null && this.defalutValue != ""
				&& this.defalutValue != "undefined") {
			this.hasDefaultValue = true;
		}
	},
	initComponent : function() {
		Ext.form.TreeField.superclass.initComponent.call(this);
		this.addEvents('select', 'expand', 'collapse', 'beforeselect');

	},
	initList : function() {
		if (!this.list) {
			var cls = 'x-treefield-list';

			this.list = new Ext.Layer({
				shadow : this.shadow,
				cls : [cls, this.listClass].join(' '),
				constrain : false
			});

			var lw = this.listWidth
					|| Math.max(this.wrap.getWidth(), this.minListWidth);
			this.list.setWidth(lw);
			this.list.swallowEvent('mousewheel');

			this.innerList = this.list.createChild({
				cls : cls + '-inner'
			});
			this.innerList.setWidth(lw - this.list.getFrameWidth('lr'));
			this.innerList.setHeight(this.listHeight || this.minListHeight);
			if (!this.tree) {
				this.tree = this.createTree(this.innerList);
			}
			this.tree.on('click', this.select, this);

			this.tree.render();
		}
	},
	onRender : function(ct, position) {
		Ext.form.TreeField.superclass.onRender.call(this, ct, position);
		if (this.hiddenName) {
			this.hiddenField = this.el.insertSibling({
				tag : 'input',
				type : 'hidden',
				name : this.hiddenName,
				id : (this.hiddenId || this.hiddenName)
			}, 'before', true);
			this.hiddenField.value = this.hiddenValue !== undefined
					? this.hiddenValue
					: this.value !== undefined ? this.value : '';
			this.el.dom.removeAttribute('name');
		}
		if (Ext.isGecko) {
			this.el.dom.setAttribute('autocomplete', 'off');
		}

		this.initList();

	},
	select : function(node) {
		if (this.fireEvent('beforeselect', node, this) != false) {
			this.onSelect(node);
			this.fireEvent('select', this, node);
		}
	},
	onSelect : function(node) {
		this.setValue(node);
		this.collapse();
	},
	createTree : function(el) {

		var Tree = Ext.tree;

		var tree = new Tree.TreePanel({
			el : el,
			onlyLeafCheckable : false,
			checkModel : 'cascade',
			useArrows : true,
			autoScroll : true,
			animate : false,
			enableDD : false,
			containerScroll : true,
			loader : new Tree.TreeLoader({
				dataUrl : this.dataUrl,
				baseParams : this.baseParams
			})
		});

		var root = new Tree.AsyncTreeNode(this.treeRootConfig);
		tree.setRootNode(root);
		this.root = root;
		if (this.hasDefaultValue) {
			root.expand(true, false, true);
		}
		return tree;
	},

	getValue : function() {
		return this.hiddenField.value;
		if (this.valueField) {
			return typeof this.value != 'undefined' ? this.value : '';
		} else {
			return Ext.form.TreeField.superclass.getValue.call(this);
		}
	},
	setValue : function(node) {
		// if(!node)return;

		var text, value, myID;
		if (node && typeof node == 'object') {
			text = node[this.displayField];
			value = node[this.valueField || this.displayField];
			myID = node.id;
		} else {
			text = node;
			value = node;

		}
		if (this.hiddenField) {
			this.hiddenField.value = myID;
			this.defalutValue = myID;
		}
		Ext.form.TreeField.superclass.setValue.call(this, text);
        
		this.value = value;
	},
	onResize : function(w, h) {
		Ext.form.TreeField.superclass.onResize.apply(this, arguments);
		if (this.list && this.listWidth == null) {
			var lw = Math.max(w, this.minListWidth);
			this.list.setWidth(lw);
			this.innerList.setWidth(lw - this.list.getFrameWidth('lr'));
		}
	},
	validateBlur : function() {
		return !this.list || !this.list.isVisible();
	},
	onDestroy : function() {
		if (this.list) {
			this.list.destroy();
		}
		if (this.wrap) {
			this.wrap.remove();
		}
		Ext.form.TreeField.superclass.onDestroy.call(this);
	},
	collapseIf : function(e) {
		if (!e.within(this.wrap) && !e.within(this.list)) {
			this.collapse();
		}
	},

	collapse : function() {
		if (!this.isExpanded()) {
			return;
		}
		this.list.hide();
		Ext.getDoc().un('mousewheel', this.collapseIf, this);
		Ext.getDoc().un('mousedown', this.collapseIf, this);
		this.fireEvent('collapse', this);
	},
	expand : function() {

		if (this.isExpanded() || !this.hasFocus) {
			return;
		}
		this.onExpand();
		this.list.alignTo(this.wrap, this.listAlign);
		this.list.show();
		Ext.getDoc().on('mousewheel', this.collapseIf, this);
		Ext.getDoc().on('mousedown', this.collapseIf, this);
		this.fireEvent('expand', this);
	},
	onExpand : function() {
		var doc = Ext.getDoc();
		this.on('click', function() {
			alert(111)
		}, this);
	},
	isExpanded : function() {
		return this.list && this.list.isVisible();
	},
	onTriggerClick : function() {
		if (this.disabled) {
			return;
		}
		if (this.isExpanded()) {
			this.collapse();
		} else {
			this.onFocus({});
			this.expand();
		}
		if (this.defalutValue != null) {
			this.selectNodes([this.defalutValue]);
		}
		this.el.focus();
	}
});
Ext.reg('treeField', Ext.form.TreeField);
