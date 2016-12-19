$(function() {
	$("#atlasName")
			.bind(
					"paste",
					function() {
						var isIE = navigator.userAgent.toLowerCase().indexOf(
								"msie");
						if (isIE > -1
								&& (clipboardData.getData("text").length > $(
										this).attr("maxlength"))) {
							$(this).closest("tr.item").addClass("wrongBg");
							$(this).closest("tr.item").find(
									"div[name='cutOut']").remove();
							$(this)
									.after(
											"<div name='cutOut' htmlFor='"
													+ $(this).attr("name")
													+ "' generated='true' style='color:red'>字符数量超过"+$(this).attr("maxlength")+"个，系统进行了自动截取！</div>");
							setTimeout(removeCutError, 3000);
						}
					});

	$("textarea[name='summary']")
			.bind(
					"paste",
					function() {
						var isIE = navigator.userAgent.toLowerCase().indexOf(
								"msie");
						if (isIE > -1
								&& clipboardData.getData("text").length > $(
										this).attr("maxlength")) {
							$(this).parent("td").find("div[name='cutOut']")
									.remove();
							$(this)
									.parent("td")
									.append(
											"<div class='wrong' name='cutOut' htmlFor='"
													+ $(this).attr("name")
													+ "' generated='true'>字符数量超过"+$(this).attr("maxlength")+"个，系统进行了自动截取！</div>");
							clipboardData.setData("text",
									clipboardData.getData("text")
											.substring(
													0,
													parseInt($(this).attr(
															"maxlength"))));
							setTimeout(removeCutError, 3000);
						}
					});

});

function removeCutError() {
	$("[name='cutOut']").remove();
}

function valAtlasName() {
	if ($.trim($("#atlasName").val()).length === 0
			|| $.trim($("#atlasName").val()).length > 40) {
		$("#atlasName ~ .wrong").remove();
		$("#atlasName").closest("tr").addClass("wrongBg");
		$("#atlasName").after("<div class='wrong'>请填写图册名称，名称不能超过40个字符！</div>");
	} else {
		$("#atlasName ~ .wrong").remove();
	}
}

function valSummary() {
	if ($.trim($("#summary").val()).length == 0
			|| $.trim($("#summary").html()).length > 2000) {
		$("#summary").parent("td").find(".wrong").remove();
		$("#summary")
				.parent("td")
				.append(
						"<div class='wrong' style='display:block'>请填写图册描述，描述不能超过2000个字符！</div>");
	} else {
		$("#summary").parent("td").find(".wrong").remove();
	}
}

function valPublishedCycleNum() {
	if ($("#publishedCycle").val() !== "") {
		var reg = new RegExp("^(([1-9]\\d*)|(\\d))$");
		if (!reg.test($("#publishedCycle").val())
				|| $("#publishedCycle").val() <= 0) {
			$("#publishedCycle ~ .wrong").remove();
			$("#publishedCycle").after("<div class='wrong'>请输入正整数！</div>");
		} else {
			$("#publishedCycle ~ .wrong").remove();
		}
	} else{
		$("#publishedCycle ~ .wrong").remove();
	}

}

$("#atlasName").keyup(function(event) {
	valAtlasName();
	if ($.trim($(this).val()).length > 40) {
		$(this).val($(this).val().substring(0, 40));
	}
}).blur(function() {
	valAtlasName();
	if ($.trim($(this).val()).length > 40) {
		$(this).val($(this).val().substring(0, 40));
	}
});

$("#summary").keyup(
		function(event) {
			if ($.trim($(this).val()).length > 2000) {
				$(this).val($(this).val().substring(0, 2000));
			} else if ($.trim($(this).val()).length <= 2000
					&& $.trim($(this).val()).length > 0) {
				$("#summaryNum").html("已有字数：" + $.trim($(this).val()).length);
			} else {
				$("#summaryNum").html("");
			}
			valSummary();
		}).blur(
		function() {
			valSummary();
			if ($.trim($(this).val()).length > 2000) {
				$(this).val($(this).val().substring(0, 2000));
			} else if ($.trim($(this).val()).length <= 2000
					&& $.trim($(this).val()).length > 0) {
				$("#summaryNum").html("已有字数：" + $.trim($(this).val()).length);
			} else {
				$("#summaryNum").html("");
			}
		});

$("#publishedCycle").keyup(function(event) {
	valPublishedCycleNum();
}).blur(function() {
	valPublishedCycleNum();
});

function validateAtlas() {
	valAtlasName();
	valSummary();
	valPublishedCycleNum();
	if ($("#picAddr").val() === "") {
		$("#picAddr").parent("td").find(".wrong").remove();
		$("#picAddr").parent("td").append(
				"<div class='wrong' style='display:block'>请添加封面图片！</div>");
	} else {
		$("#picAddr").parent("td").find(".wrong").remove();
	}

	if ($("#fileAddr").val() === "") {
		$("#fileAddr").parent("td").find(".wrong").remove();
		$("#fileAddr").parent("td").append(
				"<div class='wrong' style='display:block'>请添加图册文件！</div>");
	} else {
		$("#fileAddr").parent("td").find(".wrong").remove();
	}
}