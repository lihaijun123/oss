/**
 * 目录选择控件，依赖于jquery.linkage
 * @author geliang
 * liushuyan 修改 只显示到二级目录
 */
(function($) {
    function CategoryLayer(options) {
        var linkageOption = [{}, {}, {}];
        $.each(linkageOption, function(i, op) {
            if (options["cate_els"]) {
                op["el"] = options["cate_els"][i];
            }
            else {
                op["el"] = "#cate_" + i;
            }
            if (options["cate_urls"]) {
                op["url"] = options["cate_urls"][i];
            }
            else {
                op["url"] = "/cate.do?format=json&level=" + (i + 1);
            }
        });
        var _this = this;
        this.layer = $(options["layer"] || "#cateLayer");
        this.okButton = $(options["layer_ok"] || "#okCateLayer");
        this.select0 = $(linkageOption[0]["el"]);
        this.select1 = $(linkageOption[1]["el"]);
        this.select2 = $(linkageOption[2]["el"]);
        this.relatedInput = null;
        this.selected = false;

		this.select2.hide();
        //由于有可能第二级元素即是可选择的目录，所以加上一个afterNextRender回调函数
        linkageOption[1]["afterNextRender"] = function() {
            //如果第二级的change事件是由用户触发的
            if (_this.select1.val() !== null) {
                //有第三级元素时，显示第三级框
//                if (_this.select2.children().size() > 0) {
//                    _this.select2.show();
//                }//没有时，隐藏它
//                else {
//                    _this.select2.hide();
//                    _this.showSelected([_this.select0, _this.select1]);
//                }
            	_this.showSelected([_this.select0, _this.select1]);
            }//如果change事件不是由用户触发，则显示未选中
            else {
                _this.showUnSelect();
            }
        };
        $.linkage(linkageOption);
        //对第三级的选择事件捕获，显示可选项
//        this.select2.change(function() {
//            if (_this.select2.children().size() > 0) {
//                if (_this.select2.val() !== null) {
//                    _this.showSelected([_this.select0, _this.select1, _this.select2]);
//                }
//                else {
//                    _this.showUnSelect();
//                }
//            }
//        });
        //关闭层
        $(options["layer_close"] || "#closeCateLayer").click(function() {
            _this.layer.popHide();
        });
        //确定按钮
        this.okButton.click(function() {
            _this.submitSelected();
        });
    }

    $.extend(CategoryLayer.prototype, {
        /**
         * 弹出层
         */
        pop: function() {
            this.layer.pop();
        },

        /**
         * 取得select元素选中值的text
         * @param {Object} el select元素，必须是jquery对象
         */
        getSelText: function(el) {
            var index = el[0].selectedIndex;
            if (index > -1) {
                return el[0].options[index].text;
            }
            else {
                return "";
            }
        },

        /**
         * 在页面展示所选中的目录
         * @param {Object} els select元素列表
         */
        showSelected: function(els) {
            var html = " 您当前选中的为";
            var _this = this;
            $.each(els, function(i, el) {
                html += "<em>" + _this.getSelText(el) + "</em>";
                if (i !== els.length - 1) {
                    html += "&gt;";
                }
            });
            $("#selectedCate").html(html);
            this.selected = true;
            this.okButton.removeClass().addClass("fixed");
        },

        /**
         * 展示未选中任何项的提示
         */
        showUnSelect: function() {
            $("#selectedCate").html("您尚未选中任何一项");
            this.selected = false;
            this.okButton.removeClass().addClass("fixedGray");
        },

        /**
         * 设置当前设置目录关联的input框
         * @param {Object} relatedInput
         */
        setRelatedInput: function(relatedInput) {
            this.relatedInput = relatedInput;
        },

		/**
		 * 点确定提交时的操作，将填写一个text和一个hidden
		 */
        submitSelected: function() {
            if (this.selected === false) {
                return;
            }
            if (this.select2.val() !== null) {
                this.relatedInput.val(this.getSelText(this.select2));
				this.relatedInput.next().val(this.select2.val());
            }
            else if (this.select1.val() !== null) {
                this.relatedInput.val(this.getSelText(this.select1));
				this.relatedInput.next().val(this.select1.val());
            }
			this.layer.popHide();
        }
    });

    $.fn.category = function(options) {
        var cateLayer = new CategoryLayer(options || {});
        return this.each(function(i, el) {
            $(el).click(function() {
                cateLayer.pop();
                cateLayer.setRelatedInput($(this));
            }).focus(function() {
                $(this).click();
            }).keydown(function(e) {
                e.preventDefault();
            });
        });
    }
})(jQuery)
