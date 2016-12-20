

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
    		}
    	},
    	messages: {
    		name:{
				required: "请输入名称",
				maxlength: "请输入{0}个字以内"
			}
    	},
    	submitHandler:function(form){
    		form.submit();
    	}
    });
});