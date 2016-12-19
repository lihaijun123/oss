function layer_controller(id1,id2,id3,id4,id5){
	// 获取遮蔽层、弹出层、弹出层关闭按钮、弹出层标题栏
	var l = $("#"+id1), // 遮蔽层
		d = $("#"+id2), // 弹出层
		c = $("#"+id3), // 弹出层关闭按钮
		c1 = $("#"+id5), // 弹出层关闭按钮
		t = $("#"+id4), // 弹出层标题栏
		ds = d[0].style; // 弹出层的style对象

	// 控制遮蔽层的显示以及显示位置的调整
	controlLayer(l);

	// 控制弹出层的显示以及显示位置的调整
	controlDiv(d);

	// 给浏览器注册onresize（窗体大小改变事件）
	window.onresize = function(){
		controlLayer(l);
		controlDiv(d);
	};

	$(window).scroll(function(){
		controlDiv(d);
	});

	t.mouseover(function(){
		$(this).css("cursor","move");
	});
	// 给弹出层标题栏注册模拟拖动事件
	t[0].onmousedown = function(e1){
		var h1 = mouseX(e1) - parseInt(ds.left), // X轴坐标
			h2 = mouseY(e1) - parseInt(ds.top); // Y轴坐标
		var m1 = mouseX(e1), m2 = mouseY(e1);

		document.onmousemove = function(e2){
			var l = parseInt(ds.left), t = parseInt(ds.top);
			var ww = $(document).width(), wh = $(document).height();
			var dw = d.width(), dh = d.height();
			var mx = mouseX(e2) - m1, my = mouseY(e2) - m2;

			if(l <= 0 && mx < 0){
				ds.left = "0";
			}else if(l >= (ww - dw) && mx >= 0){
				ds.left = (ww - dw) + "px";
			}else{
				ds.left = (mouseX(e2)-h1) + "px";
			}

			if(t <= 0 && my < 0){
				ds.top = "0";
			}else if(t >= (wh - dh) && my >= 0){
				ds.top = (wh - dh) + "px";
			}else{
				ds.top = (mouseY(e2)-h2) + "px";
			}
		};

		document.onmouseup = function(e3){
			document.onmousemove = null;
			document.onmouseup = null;
		};

	};

	// 关闭按钮click事件
	c[0].onclick = function(){
		$(window).unbind("scroll");
		window.onresize = null;
		l.hide();
		d.hide();
	};
	// 关闭按钮click事件
	c1[0].onclick = function(){
		$(window).unbind("scroll");
		window.onresize = null;
		l.hide();
		d.hide();
	};

	/**
	 * 控制遮蔽层的显示
	 * @param layer $("#layer"),遮蔽层
	 */
	function controlLayer(layer){
		layer.css({
			width : width(),
			height : height(),
			display : "block"
		});
	}

	/**
	 * 控制模拟弹出窗口的显示位置
	 * @param div $("#div"),要显示的弹出层
	 * @returns null
	 */
	function controlDiv(div){
		var wh = windowHeight(),
			ww = windowWidth(),
			dh = div.height(),
			dw = div.width(),
			sh = scrollY(),
			sw = scrollX();

		var _left = 0, _top = 0;

		// 情况1：浏览器当前视口比模拟弹出窗口小
		if(dh >= wh && dw >= ww){
			$(window).unbind("scroll");
			_top = 0;
			_left = 0;
		}
		// 情况2：浏览器当前视口高度或宽度小于模拟弹出窗口的高度或宽度
		else if(dh > wh && dw < ww){
			$(window).unbind("scroll");
			_top = 0;
			_left = (ww - dw)/2;
		}else if(dh < wh && dw > ww){
			$(window).unbind("scroll");
			_top = (wh - dh)/2;
			_left = 0;
		}
		// 情况3：浏览器当前视口比模拟弹出窗口大
		else{
			_top = (wh - dh)/2;
			_left = (ww - dw)/2;
		}

		div.css({
			"left" : (sw + _left) + "px",
			"top" : (sh + _top) + "px",
			"position" : "absolute",
			"display" : "block"
		});
	}

	function height() {
		var scrollHeight,
			offsetHeight;
		// handle IE 6
		if ($.browser.msie && $.browser.version < 7) {
			scrollHeight = Math.max(
				document.documentElement.scrollHeight,
				document.body.scrollHeight
			);
			offsetHeight = Math.max(
				document.documentElement.offsetHeight,
				document.body.offsetHeight
			);

			if (scrollHeight < offsetHeight) {
				return $(window).height() + 'px';
			} else {
				return scrollHeight + 'px';
			}
		// handle "good" browsers
		} else {
			return $(document).height() + 'px';
		}
	}

	function width() {
		var scrollWidth,
			offsetWidth;
		// handle IE
		if ( $.browser.msie ) {
			scrollWidth = Math.max(
				document.documentElement.scrollWidth,
				document.body.scrollWidth
			);
			offsetWidth = Math.max(
				document.documentElement.offsetWidth,
				document.body.offsetWidth
			);

			if (scrollWidth < offsetWidth) {
				return $(window).width() + 'px';
			} else {
				return scrollWidth + 'px';
			}
		// handle "good" browsers
		} else {
			return $(document).width() + 'px';
		}
	}

	/**
	 * 获取鼠标x轴的位置
	 * @param e 事件对象
	 * @returns
	 */
	function mouseX(e){
		e = e || window.event;
		return e.pageX || e.clientX + document.body.scrollLeft;
	}

	/**
	 * 获取鼠标y轴的位置
	 * @param e 事件对象
	 * @returns
	 */
	function mouseY(e){
		e = e || window.event;
		return e.pageY || e.clientY + document.body.scrollTop;
	}

	/**
	 * 获取整个页面的高度
	 */
	function pageHeight(){
		return document.body.scrollHeight;
	}

	/**
	 * 获取整个页面的宽度
	 */
	function pageWidth(){
		return document.body.scrollWidth;
	}

	/**
	 * 获取滚动条x轴的滚动距离
	 */
	function scrollX(){
		var de = document.documentElement;
		return self.pageXOffset ||
			   (de && de.scrollLeft) ||
			   document.body.scrollLeft;
	}

	/**
	 * 获取滚动条y轴的滚动距离
	 */
	function scrollY(){
		var de = document.documentElement;
		return self.pageYOffset ||
			   (de && de.scrollTop) ||
			   document.body.scrollTop;
	}

	/**
	 * 获取页面可见区域的高度
	 */
	function windowHeight(){
		var de = document.documentElement;
		return self.innerHeight ||
			   (de && de.clientHeight) ||
			   document.body.clientHeight;
	}

	/**
	 * 获取页面可见区域的宽度
	 */
	function windowWidth(){
		var de = document.documentElement;
		return self.innerWidth ||
			   (de && de.clientWidth) ||
			   document.body.clientWidth;
	}
}

