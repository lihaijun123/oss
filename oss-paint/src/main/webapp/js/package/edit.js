$(function(){
	menu_show("1_3");
});

$("#saveBtn").click(function(){

	validateAtlas();
	if($(".wrong").length > 0){
		$("#addError").show();
		return false;
	}else{
		$("#addError").hide();
	}
	if($("#makeFlag").val() === 2){
		$("#prodSns").val("");
	}
	$("#form").submit();
});

$("#delAtlas").click(function() {
	vConfirm("确定删除?", "", function(isTrue){
		if(isTrue){
			$("#deleteForm").attr("action","/atlas/"+$("#encryptSn").val()).submit();
		}
	});
});

$(function(){
	var PRODSNS = "";
	$(".right_chk").each(function(){
		var chkVal = $(this).val();
		// PRODSNS:是为了点击在线制作的时候设置左右两边的默认值
		PRODSNS += chkVal +",";
	})
	if(PRODSNS !== ""){
		PRODSNS = PRODSNS.substring(0, PRODSNS.length-1);
	}
	if($("#prodSns").val() === ""){
		$("#prodSns").val(PRODSNS);
	}
});
