/**
 * tabpanel
 */
$importCSS('/extjs/style/iframe.js');
S.tabframe = function() {
	return {
		getParentIframe : function(f_id) {
			try {
				return window.parent.Ext.getCmp(f_id).getFrameDocument();
			} catch (e) {
				return nullf
			}
		},
		getParent : function() {
			try {
				return window.parent.document;
			} catch (e) {
				return nullf
			}
		},
		getIframe : function(f_id) {
			try {
				return Ext.getCmp(f_id).getFrameDocument();
			} catch (e) {
				return nullf
			}
		}
	}
}();

// tab
Ext.onReady(function() {
	var objArray_tabs = Ext.DomQuery.select("div.tabs");
	Ext.each(objArray_tabs, function(obj_tab) {
		var tabs_div = Ext.DomHelper.insertBefore(obj_tab, {
			tag : 'div'
		});
		var tabs;
		var tabpanel_config = new function() {
			this.id = obj_tab.id;
			this.renderTo = tabs_div, this.resizeTabs = true; // turn on tab
			// resizing
			this.minTabWidth = 115;
			this.tabWidth = 135;
			this.enableTabScroll = true;
		}
		if (obj_tab.attributes.getNamedItem('width')) {
			tabpanel_config.width = parseInt(obj_tab.getAttribute('width'));
		}
		if (obj_tab.attributes.getNamedItem('height')) {
			tabpanel_config.height = parseInt(obj_tab.getAttribute('height'));
		}
		tabpanel_config.bodyStyle = {
			height : '100%',
			width : '100%',
			position : 'relative'
		}
		if (obj_tab.attributes.getNamedItem('closemenu')) {
			tabpanel_config.plugins = new Ext.ux.TabCloseMenu();
		}
		tabs = new Ext.TabPanel(tabpanel_config);
		// 處理tab在遨游下拖拽問題
		tabs.on('add', function(tabs, comp, idx) {
			if (comp.href) {
				var selstr = 'div.x-tab-panel[id=' + tabs.getId() + ']';
				var di = Ext.DomQuery.selectNode(selstr);
				var ul = Ext.get(di).child('div.x-tab-strip-wrap');
				var li = Ext.get(ul).child('ul.x-tab-strip.x-tab-strip-top').dom.childNodes[idx];
				var a = Ext.get(li).child('a.x-tab-right').dom;
				a.href = comp.href;
				a.onclick = function() {
					return false;
				}
			}
		});
		var objArray_tab = Ext.DomQuery.select("div", obj_tab);
		Ext.each(objArray_tab, function(obj_tab, index, array) {
			var tab;
			if (obj_tab.attributes.getNamedItem('href')) {
				// 處理異步加載，待完善，現注釋掉
				if (obj_tab.attributes.getNamedItem('ajax')) {
					// tab = new Ext.Panel({
					// title : obj_tab.attributes.getNamedItem('title')
					// && obj_tab.attributes.getNamedItem('title').value != ''
					// ? obj_tab.attributes.getNamedItem('title').value
					// : '新標簽',
					// iconCls : 'tabs',
					// href : obj_tab.attributes.getNamedItem('href')
					// ? obj_tab.attributes.getNamedItem('href').value
					// : '#',
					// closable : obj_tab.attributes.getNamedItem('close')
					// ? true
					// : false
					// });
					// tab.on('activate', function(p) {
					// if (p.href) {
					// p.load({
					// url : p.href,
					// nocache : true,
					// scripts : true,
					// callback : function() {
					// var objArray_sub = Ext.DomQuery.select(
					// "a[open=tab]", p.dom);
					// Ext.each(objArray_sub, function(obj_sub) {
					// Ext.get(obj_sub).on("click",
					// function() {
					// tabs.add({
					// title : 'aa',
					// iconCls : 'tabs',
					// autoLoad : {
					// url : 'xxx.htm',
					// scripts : true
					// },
					// closable : obj_tab.attributes
					// .getNamedItem('close')
					// ? true
					// : false
					// }).show();
					//
					// window.event.cancelable = false;
					// window.event.returnValue = false;
					// });
					// });
					// }
					// });
					// }
					// });
				} else {
					// 使用iframe
					var managediframepanel_config = {
						title : obj_tab.attributes.getNamedItem('title')
								&& obj_tab.attributes.getNamedItem('title').value != ''
								? obj_tab.attributes.getNamedItem('title').value
								: '新標簽',
						iconCls : 'tabs',
						href : obj_tab.attributes.getNamedItem('href').value,
						closable : obj_tab.attributes.getNamedItem('close')
								? true
								: false,
						bodyStyle : {
							height : '100%',
							width : '100%',
							position : 'relative'
						},
						nocache : obj_tab.attributes.getNamedItem('nocache')
								? true
								: false,
						boolFirstLoad : true,
						openOnTabs : obj_tab.attributes
								.getNamedItem('openontabs') ? true : false
					}
					if (obj_tab.attributes.getNamedItem('id')
							&& obj_tab.attributes.getNamedItem('id').value != '') {
						managediframepanel_config.id = obj_tab
								.getAttribute('id');
					}
					tab = new Ext.ux.ManagedIframePanel(managediframepanel_config);
					// tab = new Ext.ux.ManagedIframePanel({
					// title : obj_tab.attributes.getNamedItem('title')
					// && obj_tab.attributes.getNamedItem('title').value != ''
					// ? obj_tab.attributes.getNamedItem('title').value
					// : '新標簽',
					// iconCls : 'tabs',
					// href : obj_tab.attributes.getNamedItem('href').value,
					// closable : obj_tab.attributes.getNamedItem('close')
					// ? true
					// : false,
					// bodyStyle : {
					// height : '100%',
					// width : '100%',
					// position : 'relative'
					// },
					// nocache : obj_tab.attributes.getNamedItem('nocache')
					// ? true
					// : false,
					// boolFirstLoad : true,
					//
					// // defaultSrc :
					// // obj_tab.attributes.getNamedItem('href').value,
					// openOnTabs : obj_tab.attributes
					// .getNamedItem('openontabs') ? true : false
					//
					// });
					tab.on('activate', function(p) {
						if (p.nocache == true && p.boolFirstLoad == false) {
							p.getFrameWindow().location.reload();
						}
						if (p.boolFirstLoad == true) {
							p.setSrc(p.href, true, function() {
								if (p.openOnTabs == true) {
									proceIfmLink(p, tabs);
								}
							});
							p.boolFirstLoad = false;
						}
					})
				}
			} else {
				tab = new Ext.Panel({
					title : obj_tab.attributes.getNamedItem('title')
							&& obj_tab.attributes.getNamedItem('title').value != ''
							? obj_tab.attributes.getNamedItem('title').value
							: '新標簽',
					iconCls : 'tabs',
					html : obj_tab.innerHTML,
					closable : obj_tab.attributes.getNamedItem('close')
							? true
							: false
				});
				// tab.on('activate', proceLink);
			}
			if (obj_tab.attributes.getNamedItem('show')) {
				tabs.add(tab).show();
			} else {
				tabs.add(tab);
			}
		});
		Ext.get(obj_tab).replaceWith(tabs);
	});
	var open_tab_link_Array = Ext.DomQuery.select("a[open=tab]",
			Ext.getBody().dom);
	Ext.each(open_tab_link_Array, function(o) {
		Ext.get(o).on("click", function() {
			window.event.returnValue = false;
		});
	});
});
function proceIfmLink(p, tabs) {
	var objArray_sub = Ext.DomQuery.select("a[open=tab]", p.getFrameBody());
	Ext.each(objArray_sub, function(obj_sub) {
		Ext.get(obj_sub).on("click", function() {
			var new_tab = new Ext.ux.ManagedIframePanel({
				title : obj_sub.attributes.getNamedItem('title')
						&& obj_sub.attributes.getNamedItem('title').value != ''
						? obj_sub.attributes.getNamedItem('title').value
						: '新標簽',
				iconCls : 'tabs',
				href : obj_sub.attributes.getNamedItem('href').value,
				closable : obj_sub.attributes.getNamedItem('close')
						? true
						: false,
				bodyStyle : {
					height : '100%',
					width : '100%',
					position : 'relative'
				},
				nocache : obj_sub.attributes.getNamedItem('nocache')
						? true
						: false
			});
			new_tab.on('activate', function(p) {
				if (p.nocache == true) {
					p.setSrc(p.href, false, function() {
						proceIfmLink(p, tabs);
					});
					p.boolLoaded = true;
				}
				if (p.boolLoaded == false) {
					p.setSrc(p.href, false, function() {
						proceIfmLink(p, tabs);
					});
					p.boolLoaded = true;
				}
			});
			tabs.add(new_tab);
		});
	});
}
/*
 * Ext JS Library 2.1 Copyright(c) 2006-2008, Ext JS, LLC. licensing@extjs.com
 * 
 * http://extjs.com/license
 */
// Very simple plugin for adding a close context menu to tabs
Ext.ux.TabCloseMenu = function() {
	var tabs, menu, ctxItem;
	this.init = function(tp) {
		tabs = tp;
		tabs.on('contextmenu', onContextMenu);
	}
	function onContextMenu(ts, item, e) {
		if (!menu) { // create context menu on first right click
			menu = new Ext.menu.Menu([{
				id : tabs.id + '-close',
				text : 'Close Tab',
				handler : function() {
					tabs.remove(ctxItem);
				}
			}, {
				id : tabs.id + '-close-others',
				text : 'Close Other Tabs',
				handler : function() {
					tabs.items.each(function(item) {
						if (item.closable && item != ctxItem) {
							tabs.remove(item);
						}
					});
				}
			}]);
		}
		ctxItem = item;
		var items = menu.items;
		items.get(tabs.id + '-close').setDisabled(!item.closable);
		var disableOthers = true;
		tabs.items.each(function() {
			if (this != item && this.closable) {
				disableOthers = false;
				return false;
			}
		});
		items.get(tabs.id + '-close-others').setDisabled(disableOthers);
		menu.showAt(e.getPoint());
	}
};