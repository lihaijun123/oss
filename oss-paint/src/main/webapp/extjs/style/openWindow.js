/*
 * 彈出窗口
 */
$importCSS('/extjs/style/iframe.js');
S.opWin = function() {
	var ifmid;
	var winid;
	var title;
	var src;
	var width;
	var height;
	var init = function(args) {
		ifmid = args.ifmid;
		winid = args.winid;
		title = args.title;
		src = args.src;
		width = args.width;
		height = args.height;
	};/*
		 * 彈出窗口
		 */
	S.opWin = function() {
		var ifmid;
		var winid;
		var title;
		var src;
		var width;
		var height;
		var init = function(args) {
			ifmid = args.ifmid;
			winid = args.winid;
			title = args.title;
			src = args.src;
			width = args.width;
			height = args.height;
		};
		return {
			open : function(args) {
				init(args);
				var win;
				if (!win) {
					var fm_cfg = {
						defaultSrc : src,
						bodyStyle : {
							height : '100%',
							width : '100%',
							position : 'relative'
						}
					}
					if (ifmid && ifmid != '') {
						fm_cfg.id = ifmid;
					}
					var win_cfg = {
						layout : 'fit',
						width : width && width != '' ? parseInt(width) : 600,
						height : height && height != ''
								? parseInt(height)
								: 400,
						closeAction : 'hide',
						plain : true,
						modal : true,
						title : title && title != '' ? title : '新窗口',
						items : new Ext.ux.ManagedIframePanel(fm_cfg)
					}
					if (winid && winid != '') {
						win_cfg.id = winid;
					}
					win = new Ext.Window(win_cfg);
				}
				win.show();
			},
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
	Ext.onReady(function() {
		var objAry = Ext.DomQuery.select('*[opwin_src]');
		Ext.each(objAry, function(o) {
			var args = {
				title : o.attributes.getNamedItem('opwin_title') ? o
						.getAttribute('opwin_title') : null,
				winid : o.attributes.getNamedItem('opwin_id') ? o
						.getAttribute('opwin_id') : null,
				ifmid : o.attributes.getNamedItem('opifm_id') ? o
						.getAttribute('opifm_id') : null,
				src : o.attributes.getNamedItem('opwin_src') ? o
						.getAttribute('opwin_src') : null,
				btntext : o.attributes.getNamedItem('opwin_btntext') ? o
						.getAttribute('opwin_btntext') : '彈出窗口'
			}
			var clkstr = '{src:\'' + args.src + '\'';
			if (args.ifmid) {
				clkstr += ',ifmid:\'' + args.ifmid + '\'';
			}
			if (args.winid) {
				clkstr += ',winid:\'' + args.winid + '\'';
			}
			if (args.title) {
				clkstr += ',title:\'' + args.title + '\'';
			}
			clkstr += '}';
			if (o.tagName == 'INPUT'
					&& !o.attributes.getNamedItem('opwin_btntext')) {
				Ext.get(o).on('click', function() {
					S.opWin.open(args)
				});

			} else {
				Ext.DomHelper.insertHtml('afterEnd', o,
						'<a href="#" onclick="S.opWin.open(' + clkstr + ')">'
								+ args.btntext + '</a>');
			}
		});
	})
	return {
		open : function(args) {
			init(args);
			var win;
			if (!win) {
				var fm_cfg = {
					defaultSrc : src,
					bodyStyle : {
						height : '100%',
						width : '100%',
						position : 'relative'
					}
				}
				if (ifmid && ifmid != '') {
					fm_cfg.id = ifmid;
				}
				var win_cfg = {
					layout : 'fit',
					width : width && width != '' ? parseInt(width) : 600,
					height : height && height != '' ? parseInt(height) : 400,
					closeAction : 'hide',
					plain : true,
					modal : true,
					title : title && title != '' ? title : '新窗口',
					items : new Ext.ux.ManagedIframePanel(fm_cfg)
				}
				if (winid && winid != '') {
					win_cfg.id = winid;
				}
				win = new Ext.Window(win_cfg);
			}
			win.show();
		},
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
Ext.onReady(function() {
	var objAry = Ext.DomQuery.select('*[opwin_src]');
	Ext.each(objAry, function(o) {
		var args = {
			title : o.attributes.getNamedItem('opwin_title') ? o
					.getAttribute('opwin_title') : null,
			winid : o.attributes.getNamedItem('opwin_id') ? o
					.getAttribute('opwin_id') : null,
			ifmid : o.attributes.getNamedItem('opifm_id') ? o
					.getAttribute('opifm_id') : null,
			src : o.attributes.getNamedItem('opwin_src') ? o
					.getAttribute('opwin_src') : null,
			btntext : o.attributes.getNamedItem('opwin_btntext') ? o
					.getAttribute('opwin_btntext') : '彈出窗口'
		}
		var clkstr = '{src:\'' + args.src + '\'';
		if (args.ifmid) {
			clkstr += ',ifmid:\'' + args.ifmid + '\'';
		}
		if (args.winid) {
			clkstr += ',winid:\'' + args.winid + '\'';
		}
		if (args.title) {
			clkstr += ',title:\'' + args.title + '\'';
		}
		clkstr += '}';
		if (o.tagName == 'INPUT' && !o.attributes.getNamedItem('opwin_btntext')) {
			Ext.get(o).on('click', function() {
				S.opWin.open(args)
			});

		} else {
			Ext.DomHelper.insertHtml('afterEnd', o,
					'<a href="#" onclick="S.opWin.open(' + clkstr + ')">'
							+ args.btntext + '</a>');
		}
	});
})