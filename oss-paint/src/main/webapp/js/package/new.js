$(function(){
	menu_show("1_3");
});

$("#addBtn").click(function(){

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

$("#onlineMakeForAdd").click(function(){
	var atlasName = $("#atlasName").val();
	if($.trim(atlasName) === ""){
		vAlertWarning("请填写图册名称！");
		return false;
	}
	var exbEncryptSn = $("#exbEncryptSn").val();
	$("#makeAtlasDiv").load("/atlas/get-prod?exbEncryptSn="+exbEncryptSn, function(){
		layer_controller("hezc_bg","chooseProd","closeImg","layer_top","closeBtn");
		var prodSn = $("#prodSns").val();
		if($.trim(prodSn) !== ""){
			var rightHtml = "";
			var prodSns = prodSn.split(",");
			for(var i = 0; i < prodSns.length; i++){
				var thisId = prodSns[i];
				var name = $("a[realId='"+thisId+"']").html();
				rightHtml += '<tr id="tr_'+thisId+'" class="o"><td><input class="right_chk" type="checkbox" onclick="subCheck()" value='+thisId+' /></td><td><a href="javascript:edit(\''+thisId+'\')">'+name.replace("<","&lt;")+'</a></td></tr>';
				$("#chk_"+thisId).attr("conceal","conceal");
				$("#dtree_"+thisId).hide();
			}
			$("#tbodyList").html(rightHtml);
		}else{
			$(".p_chk").attr("checked",false);
			$(".s_chk").attr("checked",false);
			$(".dTreeNode").show();
			$(".s_chk").removeAttr("conceal");
			$("#tbodyList").html("");
		}
	});
});

$("#closeImg").click(function(){
	$("#chooseProdDiv").html("");
})

$("#closeBtn").click(function(){
	$("#chooseProdDiv").html("");
})