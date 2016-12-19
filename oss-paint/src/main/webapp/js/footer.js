	$(function(){
		var msg = $("#footer > span").text();
		if(msg){
			$("#footer").show();
			setTimeout(function(){$("#footer").hide();}, 3000);
		}
	})