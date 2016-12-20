

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
    		name:{
    			required: true,
    			maxlength: 30
    		},
	    	picFileSn:{
	    		required: true,
    			maxlength: 30
	    	}
    		/*,
	    	modelFileSn:{
    			required: true,
    			maxlength: 30
    		}*/
    	},
    	messages: {
    		name:{
				required: "请输入名称",
				maxlength: "请输入{0}个字以内"
			},
	    	picFileSn:{
	    		required: "请输上传图片",
	    		maxlength: "请输入{0}个字以内"
	    	}
			/*,
	    	modelFileSn:{
				required: "请输上传模型文件",
				maxlength: "请输入{0}个字以内"
			}*/
    	},
    	submitHandler:function(form){
    		form.submit();
    	}
    });
	
	typeChange($("#modelType").val());
	
	$("#modelType").change(function(){
		var vl = $(this).val();
		typeChange(vl);
	});
});

function typeChange(selectType){
	if(selectType == 1){
		$("table tr:eq(3)").hide();
		$("table tr:eq(4)").hide();
	} else {
		$("table tr:eq(3)").show();
		$("table tr:eq(4)").show();
	}
}