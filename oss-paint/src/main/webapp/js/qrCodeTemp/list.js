

$(function(){


	//删除
	$("a[id^='deleteLk_']").click(function(){
		if(window.confirm("是否删除？")){
			if(window.confirm("是否删除？")){
				var sn = $(this).attr("sn");
				window.location.href = "/mxjqrcodetemp.do?method=delete&sn=" + sn;
			}
		}
	});
	var sn = 0;
	var remark = "";
	$("a[id^='setRemark_']").click(function(){
		sn = $(this).attr("sn");
		remark = $(this).text();
		$('#remarkModal').modal('show');
	});

	$('#remarkModal').on('show.bs.modal', function () {
		$(this).find("textarea").val(remark);
	});

	$('#remarkModal').on('hide.bs.modal', function () {
		sn = 0;
		remark = "";
		$("#remark").val("");
	});

	$("#submitRemark").click(function(){
		$.ajax({
			url:"/mxjqrcodetemp.do?method=remark",
			data:{
				sn : sn,
				remark : $("#remark").val()
			},
			type:"post",
			cache:false,
			dataType:"json",
			success:function(data){
				$('#remarkModal').modal('hide');
				window.location.reload();
			},
			error:function(){

			}
		});
	});
});
