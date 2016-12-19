/**
 * 新一站公共js
 * @author xingshikang
 */
if (!!window.jQuery) {
    (function() {
        $.extend($.fn, {
            mask: function(msg, maskDivClass, opacity) {
                this.unmask();
                var op = {
                    opacity: opacity,
                    z: 10000,
                    bgcolor: '#000000'
                };
                var original = $(document.body);
                var position = {
                    top: 0,
                    left: 0
                };
                if (this[0] && this[0] !== window.document) {
                    original = this;
                    position = original.position();
                }
                var maskDiv = $('<div class="maskdivgen"></div>');
                maskDiv.appendTo(original);
                var maskWidth = original.outerWidth();
                if (!maskWidth) {
                    maskWidth = original.width();
                }				
                var maskHeight = original.outerHeight();
                if (!maskHeight) {
                    maskHeight = original.height();
                }
                maskDiv.css({
                    position: 'absolute',
                    top: position.top,
                    left: position.left,
                    'z-index': op.z,
                    width: maskWidth,
                    height: maskHeight,
                    'background-color': op.bgcolor,
                    opacity: 0
                });
                if (maskDivClass) {
                    maskDiv.addClass(maskDivClass);
                }
                if (msg && typeof(msg) == "string" && $.trim(msg) != "") {
                    var msgDiv = $('<div style="position:absolute;border:#6593cf 1px solid; padding:1px;background:#ccca"><div style="line-height:24px;border:#a3bad9 1px solid;background:white;padding:2px 10px 2px 10px"><img align="absMiddle" src="/images/ico_loading.gif"/>&nbsp;' + msg + '</div></div>');
                    msgDiv.appendTo(maskDiv);
                    var widthspace = (maskDiv.width() - msgDiv.width());
                    var heightspace = (maskDiv.height() - msgDiv.height());
                    msgDiv.css({
                        cursor: 'wait',
                        top: (heightspace / 2 - 2),
                        left: (widthspace / 2 - 2)
                    });
                }
                maskDiv.bgiframe();
                maskDiv.fadeIn('fast', function() {
                    $(this).fadeTo('slow', op.opacity);
                });
                return maskDiv;
            },
            unmask: function() {
                var original = $(document.body);
                if (this[0] && this[0] !== window.document) {
                    original = $(this[0]);
                }
                original.find("> div.maskdivgen").fadeOut('slow', function() {
                    $(this).remove();
                });
            }
        });
    })(jQuery);
    (function($) {
        $.fn.bgiframe = ($.browser.msie && /msie 6\.0/i.test(navigator.userAgent) ? function(s) {
            s = $.extend({
                top: 'auto',
                left: 'auto',
                width: 'auto',
                height: 'auto',
                opacity: true,
                src: 'javascript:false;'
            }, s);
            var html = '<iframe class="bgiframe"frameborder="0"tabindex="-1"src="' + s.src + '"' + 'style="display:block;position:absolute;z-index:-1;' +
            (s.opacity !== false ? 'filter:Alpha(Opacity=\'0\');' : '') +
            'top:' +
            (s.top == 'auto' ? 'expression(((parseInt(this.parentNode.currentStyle.borderTopWidth)||0)*-1)+\'px\')' : prop(s.top)) +
            ';' +
            'left:' +
            (s.left == 'auto' ? 'expression(((parseInt(this.parentNode.currentStyle.borderLeftWidth)||0)*-1)+\'px\')' : prop(s.left)) +
            ';' +
            'width:' +
            (s.width == 'auto' ? 'expression(this.parentNode.offsetWidth+\'px\')' : prop(s.width)) +
            ';' +
            'height:' +
            (s.height == 'auto' ? 'expression(this.parentNode.offsetHeight+\'px\')' : prop(s.height)) +
            ';' +
            '"/>';
            return this.each(function() {
                if ($(this).children('iframe.bgiframe').length === 0) 
                    this.insertBefore(document.createElement(html), this.firstChild);
            });
        }
 : function() {
            return this;
        });
        $.fn.bgIframe = $.fn.bgiframe;
        function prop(n) {
            return n && n.constructor === Number ? n + 'px' : n;
        }
    })(jQuery);
    (function($) {
        $.fn.center = function(settings) {
            var style = $.extend({
                position: 'absolute',
                top: '50%',
                left: '50%',
                zIndex: 10001,
                relative: true,
                offset: 50
            }, settings || {});
            return this.each(function() {
                var $this = $(this);
                if (style.top == '50%') 
                    style.marginTop = -$this.outerHeight() / 2;
                if (style.left == '50%') 
                    style.marginLeft = -$this.outerWidth() / 2;
                if (style.relative && !$this.parent().is('body') && $this.parent().css('position') == 'static') 
                    $this.parent().css('position', 'relative');
                delete style.relative;
                if ((style.position == 'fixed' && $.browser.version == '6.0')) {
                    style.marginTop += $(window).scrollTop();
                    style.position = 'absolute';
                    $(window).scroll(function() {
                        $this.stop().animate({
                            marginTop: $(window).scrollTop() - $this.outerHeight() / 2
                        });
                    });
                }
//                if ($("iframe", window.parent.document).length > 0) {
//                    var iframeLookHeight = $(window.parent.window).height() - $("iframe", window.parent.document).offset().top - style.offset;
//                    style.top = $(window.parent.document).scrollTop() + iframeLookHeight / 2
//                    style.position = 'absolute';
//                    $(window.parent.document).scroll(function() {
//                        $this.stop().animate({
//                            top: $(window.parent.document).scrollTop() + iframeLookHeight / 2
//                        }, "fast");
//                        return false;
//                    });
//                }
                $this.css(style);
            });
        };
    })(jQuery);
}
(function() {
    if (!window.aidtip) {
        window.aidtip = {};
    }
    window.aidtip = function() {
        if (!window.aidtip.submiting) {
            window.aidtip.submiting = false;
        }
        if (!window.aidtip.formed) {
            window.aidtip.formed = {};
        }
        var memory = {};
        $.each($("input[type='text']"), function(i, n) {
            if ($(n).attr("aidtip") && $(n).attr("aidtip").length > 0) {
                $(n).bind("click", function() {
                    if (this.value == $(n).attr("aidtip")) {
                        this.value = "";
                    }
                });
                $(n).bind("blur", function() {
                    if ((this.value.length < 1 || this.value == $(n).attr("aidtip")) && !window.aidtip.submiting) {
                        this.value = $(n).attr("aidtip");
                        $(n).css("color", "#aaa");
                    }
                });
                $(n).bind("keydown", function() {
                    if (this.value == $(n).attr("aidtip")) {
                        this.value = "";
                    }
                    $(n).css("color", "#000");
                });
                $(n).blur();
                var formSelector = "";
                $.each($("form"), function(i, f) {
                    if ($(f).get(0) == $(n).parentsUntil("form").parent().get(0)) {
                        formSelector = "form:eq(" + i + ")";
                        return false;
                    }
                });
                if (memory[formSelector]) {
                    memory[formSelector].push(n);
                }
                else {
                    memory[formSelector] = [n];
                }
            }
        });
        var existedVal = false;
        for (x in memory) {
            existedVal = true;
            break;
        }
        if (existedVal) {
            var _Listener = null;
            $.each(memory, function(formSelector, inputObj) {
                var formObj = $(formSelector);
                if (formObj.data("events") != null && formObj.data("events")["submit"] != null) {
                    _Listener = formObj.data("events")["submit"][0].handler;
                }
                else if (formObj.attr("onsubmit") != null) {
                    _Listener = formObj.attr("onsubmit");
                }
                formObj.removeAttr("onsubmit");
                formObj.unbind("submit");
                formObj.bind("submit", function() {
                    window.aidtip.submiting = true;
                    $.each(inputObj, function(i, n) {
                        if ($(n).val() == $(n).attr("aidtip")) {
                            $(n).val("");
                        }
                    });
                    var listenerR = true;
                    if (_Listener != null) {
                        listenerR = _Listener.call(this);
                    }
                    window.aidtip.submiting = false;
                    if (listenerR || typeof(listenerR) == "undefined") {
                        return true;
                    }
                    else {
                        $.each(inputObj, function(i, n) {
                            if ((this.value.length < 1 || this.value == $(n).attr("aidtip")) && !window.aidtip.submiting) {
                                this.value = $(n).attr("aidtip");
                                $(n).css("color", "#aaa");
                            }
                        });
                        return listenerR;
                    }
                });
            });
        }
    }
    if ($.fn.hashchange) {
        $(window).hashchange(function() {
            setTimeout("window.aidtip()", 1);
        });
    }
    else {
        aidtip();
    }
})(jQuery);
(function($) {
    $.extend($.fn, {
        mouseoverTip: function(settings) {
            var _this = $(this);
            function tipBuilder(obj) {
                if (settings.context != "error") {
                    $(".popTip").remove();
                    obj.parent().prepend("<div class='collapsed popTip' style='word-wrap:break-word;color:black;'></div>");
                    var _pop = obj.parent().children(".popTip");
                    var results = settings.context.split(/[\r\n]/);
                    $.each(results, function(i, n) {
                        if (n.length > 0) {
                            _pop.get(0).appendChild(document.createTextNode(n));
                            _pop.append("<br/>");
                        }
                    });
                    
                    _pop.bind("mouseover", function() {
                        var _this = $(this);
                        _this.stop(true, true);
                        _this.show();
                        getTopOffset($(top.document), 0);
                        var _offsetTop = $("#offsetTop").val() * 1 + $(".popTip").offset().top;//获得元素真实的 offset().top
                        var _y = $(top.window).height() + $(top.window).scrollTop() - _offsetTop - $(".popTip").height();
                        if (_y < 0) {
                            $(".popTip").css("top", _this.parent().offset().top - $(".popTip").outerHeight());
                        }
                    });
                    _pop.bind("mouseout", function() {
                        var _this = $(this);
                        _this.stop(true, true);
                        _this.hide();
                    });
                    _pop.mouseover();
                }
                else {//出现非法请求页面刷新重新载入 
                    window.location.reload();
                }
            }
            function getTopOffset(obj, _top) {//获取真实offset().top
                var _ifr = obj.find("iframe:eq(0)");
                if (_ifr.length > 0) {
                    _top += _ifr.offset().top;
                    getTopOffset(_ifr.contents(), _top);
                }
                else {//使用隐藏域存放高度
                    if ($("#offsetTop").length > 0) {
                        $("#offsetTop").val(_top);
                    }
                    else {
                        $("#content").prepend('<input type="hidden" id="offsetTop" value="' + _top + '"/>');
                    }
                }
            }
            _this.bind("mouseover", function() {
                var _obj = $(this);
                if (settings.url) {
                    $.get(settings.url + "&t=" + new Date().getTime(), function(response) {
                        settings.context = response;
                        tipBuilder(_obj);
                    });
                }
                else {
                    tipBuilder(_obj);
                }
            });
            _this.bind("mouseout", function() {
                var popDiv = $(this).parent().children(".popTip");
                if (popDiv.length > 0) {
                    popDiv.mouseout();
                }
            });
        }
    });
    var CLASSES = $.fn.mouseoverTip.classes = ["collapsed", ""];
    $.fn.MouseoverTip = $.fn.mouseoverTip;
})(jQuery);
window.asyn_alert=function(msg){setTimeout(function(){alert(msg);},1);}