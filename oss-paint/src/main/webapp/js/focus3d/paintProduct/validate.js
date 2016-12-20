

$(function(){
	$("form").validate({
		onfocusout:function(element){$(element).valid();},
    	errorPlacement: function(error, element){
    		if (element.attr("name") == "readCount"){
    			error.appendTo(element.parent().parent());
    		} else {
    			error.appendTo(element.parent());
    		}
    	},
    	rules: {
    		colorName:{
    			required: true,
    			maxlength: 30
    		},
	    	picFileSn:{
	    		required: true,
    			maxlength: 30
	    	},
	    	modelFileSn:{
    			required: true,
    			maxlength: 30
    		},
    		name:{
    			required: true,
    			maxlength: 30
    		},
    		productId:{
    			required: true,
    			maxlength: 30
    		},
    		size:{
    			required: true,
    			maxlength: 30
    		},
    		dosage:{
    			required: true,
    			maxlength: 30
    		}
    		
    	},
    	messages: {
    		colorName:{
				required: "请输入颜色名称",
				maxlength: "请输入{0}个字以内"
			},
	    	picFileSn:{
	    		required: "请输上传图片",
	    		maxlength: "请输入{0}个字以内"
	    	},
	    	modelFileSn:{
				required: "请输上传模型文件",
				maxlength: "请输入{0}个字以内"
			},
			name:{
				required: "请输入涂料名称",
				maxlength: "请输入{0}个字以内"
			},
			productId:{
				required: "请输入产品ID",
				maxlength: "请输入{0}个字以内"
			},
			size:{
				required: "请输入规格",
				maxlength: "请输入{0}个字以内"
			},
			dosage:{
				required: "请输入用量",
				maxlength: "请输入{0}个字以内"
			}
    	},
    	submitHandler:function(form){
    		form.submit();
    	}
    });
});

