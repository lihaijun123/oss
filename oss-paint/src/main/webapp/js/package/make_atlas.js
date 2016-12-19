$(document).ready(function() {
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
	function showProd(count){
		var size = $("[id ^= jd]").size();
		if($("#dd"+count).css("display") === "block"){
			$("#jd"+count).attr("src","/new_files/plus.gif");
			$("#id"+count).attr("src","/new_files/folder.gif");
			if(size == count){
				$("#jd"+count).attr("src","/new_files/plusbottom.gif");
			}
			$("#dd"+count).hide();
		}else{
			$("#jd"+count).attr("src","/new_files/minus.gif");
			if(size == count){
				$("#jd"+count).attr("src","/new_files/minusbottom.gif");
			}
			$("#id"+count).attr("src","/new_files/folderopen.gif");
			$("#dd"+count).show();
		}
	}

	$(".p_chk").click(function(){
		var num = $(this).attr("num");
		var status = $(this).attr("checked");
		$(".sonChk_"+num).each(function(){
			if(!$(this).attr("disabled")){
				$(this).attr("checked",status);
			}
		})
	})

	$(".s_chk").click(function(){
		var num = $(this).attr("num");
		if(!$(this).attr("checked")){
			$(".parChk_"+num).attr("checked",false);
		}else{
			var count1 = 0;
			var count2 = 0;
			var aa = $(".sonChk_"+num);
			aa.each(function(){
				if($(this).attr("checked") && !$(this).attr("disabled")){
					count1++;
				}
				if(!$(this).attr("disabled")){
					count2++;
				}
			})
			if(count1 === count2){
				$(".parChk_"+num).attr("checked",true);
			}
		}
	});

	$("#moveToRight").click(function(){
		var count = 0;
		$(".dTreeNode").find(".s_chk:checked").each(function(){
			if(!$(this).attr("conceal")){
				count++;
			}
		})
		if(count === 0){
			vAlertWarning("请在左侧选择需要添加的产品");
			return false;
		}
		var rightHtml = "";
		$(".s_chk:checked").each(function(){
			if(!$(this).attr("conceal")){
				var thisId = $(this).val();
				var name = $("a[realId='"+thisId+"']").html();
				rightHtml += '<tr id="tr_'+thisId+'" class="o"><td><input class="right_chk" type="checkbox" onclick="subCheck()" value='+thisId+' /></td><td><a href="javascript:edit(\''+thisId+'\')">'+name.replace("<","&lt;")+'</a></td></tr>';
				$("#chk_"+thisId).attr("conceal","conceal");
				$("#dtree_"+thisId).hide();
			}
		})
		$("#tbodyList").append(rightHtml);
		$("tr[id^='tr_']:odd").removeClass("o");

		setTreePic();
	});

	function selCheckAll(obj) {
	    var inputs = document.getElementById("tableList").getElementsByTagName("input");
	    for (var i = 0; i < inputs.length; i++) {
	        if (inputs[i].type == 'checkbox')
	            inputs[i].checked = obj.checked;
	    }
	}

	function subCheck() {
		var inputs = document.getElementById("tableList").getElementsByTagName("input");
	    var count = 0;
	    for (var i = 0; i < inputs.length; i++) {
	        if (inputs[i].checked)
	            count++;
	        else
	            break;
	    }
	    if (count == inputs.length)
	        document.getElementById('allCheck').checked = true;
	    else
	        document.getElementById('allCheck').checked = false;
	}

	$("#deleteProd").click(function(){
		if($(".right_chk:checked").size() === 0){
			vAlertWarning("请在右侧选择需要删除的产品");
			return false;
		}
		$(".right_chk:checked").each(function(){
			var id = $(this).val();
			$("#chk_"+id).removeAttr("conceal");
			$("#tr_"+id).remove();
			$("#dtree_"+id).show();
		});
		if($(".right_chk:checked").size() === 0){
			document.getElementById('allCheck').checked = false;
		}
		$("img[class^=img_]").attr("src","/new_files/join.gif");
		setTreePic();
	});

	$("#saveAtlas").click(function(){
		$(this).attr("disabled", true);
		if($(".right_chk").size() === 0){
			vAlertWarning("请添加产品");
			return false;
		}
		var prodSn = "";
		$(".right_chk").each(function(){
			prodSn += $(this).val()+",";
		})
		if(prodSn !== ""){
			prodSn = prodSn.substring(0, prodSn.length-1);
		}

		var atlasName = $("#atlasName").val();
		var exbEncryptSn = $("#exbEncryptSn").val();
		var atlasType = $("input[name='atlasType1']:checked").val();
		var atlasFormat = $("input[name='atlasFormat1']:checked").val();
		if($.trim(atlasName) === ""){
			vAlertWarning("请填写图册名称！");
			return false;
		}

	    $.get("/atlas/make-atlas.json", {prodSn : prodSn, atlasType : atlasType, atlasFormat : atlasFormat, atlasName : atlasName, exbEncryptSn : exbEncryptSn}, function(data) {
			 var _chooseProd = data.chooseProd
			 if(_chooseProd){
				$("#addError").show();
				setTimeout(hideErrorInfo,3000);
			 }else{
		        var _fileSn = data.fileSn;
		        var _fileAddr = data.fileAddr;
				$("#atlasFileSn").val(_fileSn);
				$("#fileAddr").val(_fileAddr);
				$("#makeFlag").val(1);
				$("#atlasFormat").val(atlasFormat);
				$("#atlasType").val(atlasType);
				$("#prodSns").val(prodSn);

				$("#closeBtn").click();
			 }
			$(this).removeAttr("disabled");
	    }, "json");
	});

	function hideErrorInfo(){
		$("#addError").hide();
	}

	function setTreePic(){
		$(".groupName").each(function(){
			var num = "";
			$(this).find(".dTreeNode").each(function(){
				if($(this).css('display') !== 'none'){
					num = $(this).attr("num");
				}
			});
			if("" !== num){
				$(".img_"+num).attr("src","/new_files/joinbottom.gif");
			}
		})
	}
})

