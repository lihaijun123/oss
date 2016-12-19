/**
 * 弹出层
 * @author geliang
 */
(function($) {
    $.fn.pop = function() {
        var win = $(this);
        var top = ($(window).height() - win.height()) / 2 + $(window).scrollTop();
        var left = ($(window).width() - win.width()) / 2 + $(window).scrollLeft();
        win.css({
            'top': top > 0 ? top : 0,
            'left': left > 0 ? left : 0,
            'z-index': 101,
            'position': 'absolute'
        }).fadeIn("fast", function() {
            if ($("#pop_mask").size() > 0) {
                $("#pop_mask").css({
                    'width': document.documentElement.scrollWidth + "px",
                    'height': document.documentElement.scrollHeight + "px"
                }).appendTo($('body')).show();
            }
            else {
                var mask_css = {
                    'position': 'absolute',
                    'background': 'black',
                    'z-index': 100,
                    'width': document.documentElement.scrollWidth + "px",
                    'height': document.documentElement.scrollHeight + "px",
                    'top': 0,
                    'left': 0,
                    'margin': 0,
                    'padding': 0,
                    'opacity': 0.6
                };
                $('<div id="pop_mask"></div>').css(mask_css).appendTo($('body'));
                //IE6 select box bug
                if ($.browser.msie && $.browser.version < 7) {
                    var mask_iframe = $('<iframe frameborder="0"  tabindex="-1" src="javascript:void(0);"></iframe>');
                    mask_iframe.css({
                        'display': 'block',
                        'position': 'absolute',
                        'z-index': '-1',
                        'opacity': 0,
                        'width': '100%',
                        'height': '100%',
                        'top': 0,
                        'left': 0
                    });
                    $('#pop_mask').append(mask_iframe);
                }
            }
        });
    }
    $.fn.popHide = function() {
        $(this).fadeOut('fast', function() {
            $("#pop_mask").hide();
        })
    }
})(jQuery);
