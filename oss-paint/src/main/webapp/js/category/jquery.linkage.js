/**
 * 多级联动框
 * @author geliang
 */
(function($) {
    function Linkage(options) {
        this.elements = [];
        this.urls = [];
        this.cache = [];
        this.curr = [];
        this.afterNextRender = [];
        for (var i = 0; i < options.length; i++) {
            var op = options[i];
            if (!op["el"] || !op["url"]) {
                alert("Missing linkage config of " + i);
                break;
            }
            var el = $(op["el"]);
            el.data("index", i);
            this.elements.push(el);
            this.urls.push(op["url"]);
            this.curr.push(op["curr"]);
            this.afterNextRender.push(op["afterNextRender"]);
            this.cache.push({});
        }
    };
    $.extend(Linkage.prototype, {
        /**
         * 初始化控件
         */
        init: function() {
            var _this = this;
            for (var i = 0; i < this.elements.length; i++) {
                var el = this.elements[i];
                el.change(function(e) {
                    _this.changeHandler(e);
                });
            }
            this.getData(0);
        },
        /**
         * 处理change事件
         */
        changeHandler: function(evt) {
            var index = parseInt($(evt.target).data("index"));
            if (index < this.urls.length - 1) {
                //本级元素的change事件触发下一级元素的数据载入
                this.getData(index + 1);
            }
        },
        /**
         * 取数据，从缓存或通过AJAX
         * @param {Object} index 当前第几个元素取数据
         */
        getData: function(index) {
            var _this = this;
            var url = this.urls[index];
            var el = this.elements[index];
            var value = index === 0 ? "main" : this.elements[index - 1].val(); //上一级元素的选择值
            if (value === null) {
                this.render(el,[]);
                return;
            }
            //检查缓存
            if (this.cache[index][value]) {
                this.render(el, this.cache[index][value]);
            }
            else {
                $.get(url, {
                    "value": value
                }, function(data) {
                    _this.ajaxCallback(el, eval(data));
                });
            }

        },
        /**
         * 处理ajax请求
         * @param {Object} el 需要渲染的元素
         * @param {Object} data 数据
         */
        ajaxCallback: function(el, data) {
            //缓存当前元素的结果集
            var index = parseInt(el.data("index"));
            var value = index === 0 ? "main" : this.elements[index - 1].val();
            this.cache[index][value] = data[0];
            //缓存当前元素后级元素的结果集
            for (var i = 1; i < data.length; i++) {
                if (this.cache[index + i]) {
                    this.cache[index + i][data[i - 1][0]["value"]] = data[i];
                }
            }
            this.render(el, data[0]);
        },
        /**
         * 渲染元素
         * @param {Object} el
         * @param {Object} data
         */
        render: function(el, data) {
            el.empty();
            $.each(data, function(i, d) {
                el.append($("<option>" + d["text"] + "</option>").val(d["value"]));
            });
            var index = parseInt(el.data("index"));
            if (this.curr[index] && this.curr[index] != "") {
                el.val(this.curr[index]);
            }
            else {
                //如果select是一个列表框时，将不会自动选择第一个option
                //这时根据cate的需求，只给第一级的自动选上
                if (index === 0) {
                    el.val(data[0]["value"]);
                }
            }
            //执行回调函数
            if (this.afterNextRender[index - 1]) {
                this.afterNextRender[index - 1]();
            }
            el.change();
        }
    });

    /**
     * 全局方法
     * @param {Object} options 数组，每个数组元素应包含el和url属性
     */
    $.linkage = function(options) {
        var linkage = new Linkage(options);
        linkage.init();
    }
})(jQuery);
