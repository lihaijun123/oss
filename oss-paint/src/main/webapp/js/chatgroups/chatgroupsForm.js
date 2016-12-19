
$(document).ready(function(){
	//群组位置定位
	if($("#location_select").val() !== "")	showLocation();

//	$("option[name='locationChoose']").live("click",function(){
//		if($(this).val() !== "0"){
//			//群组类型
//			//$("#locationType").val($("#location_select").val());
//			//群组位置sn（或者key、ID）
//			$("#locationSn").val($(this).val());
//			//群组名称
//			$("#locationName").val($("#location_select").children("option[selected=true]").text()+">"+$(this).text());
//		}
//	});

	$("#nextSelect").live("change",function(){
		$(this).parent().children(".errors").remove();
		$("#locationSn").val("");
		$("#locationName").val("");
		if($(this).val() !== "0"){
			$(this).parent().parent().children(".errors").remove();
			//群组位置sn（或者key、ID）
			$("#locationSn").val($(this).val());
			//群组位置名称
			var locStr =$("#location_select").children("option[selected=true]").text()+" "+$(this).children("option[selected=true]").text();
			$("#locationName").val(locStr);
		}
	});

	/**
	 * 群组位置的二级显示
	 */
	$("#location_select").change(function(){
		$("#selectTd").find("table").remove();
		//清除二级select
		removeNext("next_div");
		$("#locationSn").val("");
		$("#locationName").val("");
		if($.trim($(this).val()) !== "0"){
			//清除提示标记
			$(this).parent().parent().children(".errors").remove();
			//咖啡厅
			if($(this).val()=== "1"){
				addSelectNext(coffeeRoom_Array,"next_div");
				//$(this).append();
			}
			//会议厅
			if($(this).val()=== "2"){
				addSelectNext(meetingRoom_Array,"next_div");
			}
			if($(this).val()=== "3" || $(this).val()=== "4"){
				//群组位置sn（或者key、ID）
				$("#locationSn").val($(this).val());
				//群组位置名称
				$("#locationName").val($(this).children("option[selected=true]").text());
			}
			//展台
			if($(this).val()=== "5"){
				var strInput = "<span name='nextSelect'><input type='text' id='memberId' maxlength='30'/><small>请输入会员ID</small></span>";
				$("#next_div").append(strInput);
			}
		}
	});

	//动态添加的元素绑定事件的方式
	$("a[name='adminChoose']").live("click",function(){
		$("#administrator").val($(this).text());
		//记录管理员sn
		$("#adminUserId").val($($(this).parent().children("input[type='hidden']")[0]).val());
	});
	//动态添加的元素绑定事件的方式
	$("a[name='boothChoose']").live("click",function(){
		$("#memberId").val($(this).text());
		$("#locationName").val("展台 "+$(this).text());
		//记录展台sn
		$("#locationSn").val($($(this).parent().children("input[type='hidden']")[0]).val());
	});

	//展台信息检索
	$("#memberId").live("blur",function(){
		if($.trim($(this).val()) !== ""){
			if($("#exb_select").val()===""){
				vAlertWarning("请先选择所属展会！", "警告");
				return;
			}
			var memberId=$.trim($(this).val());
			$.ajax({
					 type: "post",
				 dataType: "json",
					  url: "/chatGroups.do?method=checkBooth",
					 data: "memberId="+memberId+"&exbSn="+$("#exb_select").val(),
				  success: function(result){
				  		$("#selectTd").children("table").remove();
						$("#selectTd").children(".errors").remove();
							if(result.existFlag){
								var strBoothTable = new StringBuffer();
								strBoothTable.append("<table>");
								strBoothTable.append("<tr><td>展位编号</td><td>展位类型</td></tr>");
								for(var i=0;i< result.boothsJson.length;i++){
									var booth = result.boothsJson[i];
									strBoothTable.append("<tr>");
									strBoothTable.append("<td><input type='hidden' value='"
											+booth.boothSn +"'/><a name='boothChoose' href='#'>"+booth.boothId+"</a></td>");
									strBoothTable.append("<td>"+booth.boothType+"</td>");
									strBoothTable.append("</tr>");
								}
								strBoothTable.append("</table>")
								$("#selectTd").append(strBoothTable.toString());
							}else{
								$("#selectTd").append("<span class='errors'>没有匹配的信息，请重新输入！</span>");
							}
						}
					});
		}
	});
	//管理员信息检索(模糊匹配)
	$("#administrator").bind("blur",function(){
		if($.trim($(this).val()) !== ""){
			//清除提示标记
			$("#administrator").parent().children(".errors").remove();
			//清除原有匹配结果
			$("#administrator").parent().children("table").remove();
			var adminName = $.trim($(this).val());
			if( adminName !== ""){
				$.ajax({
					 type: "post",
				 dataType: "json",
					  url: "/chatGroups.do?method=checkAdmin",
					 data: "adminName="+adminName,
				  success: function(result){
							if(result.existFlag){
								var strAdminTable = new StringBuffer();
								strAdminTable.append("<table>");
								strAdminTable.append("<tr><td>名称</td><td>工号</td><td>部门</td></tr>");
								for(var i=0;i< result.adminsJson.length;i++){
									var admin = result.adminsJson[i];
									strAdminTable.append("<tr>");
									strAdminTable.append("<td><input type='hidden' value='"
											+admin.userId +"'/><a name='adminChoose' href='#'>"+admin.fullName+"</a></td>");
									strAdminTable.append("<td>"+admin.workId+"</td>");
									strAdminTable.append("<td>"+admin.department+"</td>");
									strAdminTable.append("</tr>");
								}
								strAdminTable.append("</table>")
								$("#adminTd").append(strAdminTable.toString());
							}else{
								$("#administrator").parent().append("<span class='errors'>没有匹配的信息，请重新输入！</span>");
							}
						}
					});
			}
		}else{
			//清除原有匹配结果
			$("#administrator").parent().children("table").remove();
			$("#adminUserId").val("");
		}
	});

	//权重输入为整数
	$("#priority_I,#maxPersonCount_I").bind("blur", function() {
		if($.trim($(this).val()) !== ""){
			//清除提示标记
			$(this).parent().children(".errors").remove();
			if($.trim($(this).val()) === ""){
			}else{
				var strPriority = $(this).val();
				var exc = /^\+?[0-9][0-9]*$/;
				if (exc.test(strPriority))
					$(this).next("span").remove();
				else
					$(this).after("<span class='errors'>请输入整数！</span>");
			}
		}
	});

	//权重输入为整数
	$("#groupName,#groupTheme,#summary,#exb_select,#permis_select").bind("blur", function() {
		if($.trim($(this).val()) !== ""){
			//清除提示标记
			$(this).parent().children(".errors").remove();
		}
	});

	$("#saveInfor_B").click(function(){
		//验证
		checkRequired();
		checkLength();
		var submitFlag = true;
		if($("body").find(".errors").length !== 0 ) submitFlag = false;
//		if($("#location_select").val()=="5"){
//		}else{
//			$("#locationSn").val($("#nextSelect").val());
//			$("#locationName").val($("#location_select").children("option[selected=true]").text()+" "+$("#nextSelect").children("option[selected=true]").text());
//		}
//		if($("#locationSn").val()==="") $("#locationSn").val($("#memberId").val());

		if(submitFlag){
			$("#addChatgroups_F").submit();
		}
	});

	//群组状态
	$("input[type='radio']").change(function(){
		if(this.checked){
			$(this).parent().children(".errors").remove();
			$("#status").val($(this).val());
		}
    });

	//非法字符验证
	$("input[type='text'],textarea").each(function(i){
        $(this).keyup(function() { checkIllegalChar($(this)); });
    });

});

/**
 *	展示群组位置信息
 */
function showLocation(){
	var locationType = $("#location_select").val();
	switch(locationType){
		case "1":
			addSelectNext(coffeeRoom_Array,"next_div");
			break;
		case "2":
			addSelectNext(meetingRoom_Array,"next_div");
			break;
		case "3":
			break;
		case "4":
			break;
		case "5":
			var strInput = "<span name='nextSelect'><input type='text' id='memberId' value='"+$("#locationName").val()+"' maxlength='30'/><small>请输入会员ID</small></span>";
			$("#next_div").append(strInput);
			break;
	}
}
function removeNext(obj){
	//$("#"+obj).children("[name='nextSelect']").remove();
	$("#"+obj).html("");
}

/**
 *
 * @param options
 * @param obj
 */
function addSelectNext(options,obj){
	var strSelect = new StringBuffer();
	strSelect.append("<select name='nextSelect' id='nextSelect'><option value='0'>请选择</option>");
	for( var i = 0 ; i<options.length ; i++){
		var optionArray = options[i].split("|");
		if(optionArray[0] === $("#locationSn").val()){
			var strOption = "<option name='locationChoose' selected=true value='"+optionArray[0]+"'>"+optionArray[2]+"</option>";
		}else{
			var strOption = "<option name='locationChoose' value='"+optionArray[0]+"'>"+optionArray[2]+"</option>";
		}
		strSelect.append(strOption);
	}
	strSelect.append("</select>");
	$("#"+obj).append(strSelect.toString());
}

/**
 * 必填字段校验
 */
function checkRequired(){
	if($("#groupName").val() === ""){
		if($("#groupName").parent().find(".errors").size() === 0){
			$("#groupName").parent().append("<span class='errors'>群组名称不能为空！</span>");
		}else{
			$("#groupName").parent().find(".errors").text("群组名称不能为空！");
		}
	}
	if($("#groupTheme").val() === ""){
		if($("#groupTheme").parent().find(".errors").size() === 0){
			$("#groupTheme").parent().append("<span class='errors'>主题不能为空！</span>");
		}else{
			$("#groupTheme").parent().find(".errors").text("主题不能为空！");
		}
	}
	if($("#exb_select").val() === ""){
		if($("#exb_select").parent().find(".errors").size() === 0){
			$("#exb_select").parent().append("<span class='errors'>请选择所属展会！</span>");
		}else{
			$("#exb_select").parent().find(".errors").text("请选择所属展会！");
		}
	}
	if($("#location_select").val() === "0" || $("#locationSn").val()===""){
		if($("#location_select").parent().parent().find(".errors").size() === 0){
			$("#location_select").parent().parent().append("<span class='errors'>请选择群组位置！</span>");
		}else{
			$("#location_select").parent().parent().find(".errors").text("请选择群组位置！");
		}
	}
	if($("#permis_select").val() === ""){
		if($("#permis_select").parent().find(".errors").size() === 0){
			$("#permis_select").parent().append("<span class='errors'>请选择使用权限！</span>");
		}else{
			$("#permis_select").parent().find(".errors").text("请选择使用权限！");
		}
	}
	if($("#maxPersonCount_I").val() === ""){
		if($("#maxPersonCount_I").parent().find(".errors").size() === 0){
			$("#maxPersonCount_I").parent().append("<span class='errors'>最高人数不能为空！</span>");
		}else{
			$("#maxPersonCount_I").parent().find(".errors").text("最高人数不能为空！");
		}
	}
	if($("#priority_I").val() === ""){
		if($("#priority_I").parent().find(".errors").size() === 0){
			$("#priority_I").parent().append("<span class='errors'>权重不能为空！</span>");
		}else{
			$("#priority_I").parent().find(".errors").text("权重不能为空！");
		}
	}
	if($("#administrator").val() === ""){
		if($("#administrator").parent().find(".errors").size() === 0){
			$("#administrator").parent().append("<span class='errors'>管理员不能为空！</span>");
		}else{
			$("#administrator").parent().find(".errors").text("管理员不能为空！");
		}
	}
	if($("#summary").val() === ""){
		if($("#summary").parent().find(".errors").size() === 0){
			$("#summary").parent().append("<span class='errors'>群组描述信息不能为空！</span>");
		}else{
			$("#summary").parent().find(".errors").text("群组描述信息不能为空！");
		}
	}
	if(!$("input:checked[name='statusR']").val()){
		if($("input[name='statusR']").parent().find(".errors").size() === 0){
			$("input[name='statusR']").parent().append("<span class='errors'>请选择状态！</span>");
		}else{
			$("input[name='statusR']").parent().find(".errors").text("请选择状态！");
		}
	}
}
/**
 * 长度限制校验
 */
function checkLength(){
		if($("#groupName").val().length > 40){
			if($("#groupName").parent().find(".errors").size() === 0){
				$("#groupName").parent().append("<span class='errors'>请输入40个字符以内的群组名称！</span>");
			}
		}
		if($("#groupTheme").val().length > 40){
			if($("#groupTheme").parent().find(".errors").size() === 0){
				$("#groupTheme").parent().append("<span class='errors'>请输入40个字符以内的主题信息！</span>");
			}
		}
		if($("#summary").val().length > 4000){
			if($("#summary").parent().find(".errors").size() === 0){
				$("#summary").parent().append("<span class='errors'>请输入4000个字符以内的描述信息！</span>");
			}
		}
}
//字符串拼接
function StringBuffer(){
	this._strings=new Array;
}
StringBuffer.prototype.append=function(str){
	this._strings.push(str);
}
StringBuffer.prototype.toString=function(){
	// join(seperator)以seperator指定的字符作为分隔符，将数组转换为字符串
return this._strings.join("");
}

//删除确认
function deleteConfirm(url){
	if (confirm("您确定要删除该条记录吗？")) {
		location.href = url;
	}
}