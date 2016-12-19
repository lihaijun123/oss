
var timeObj;
$(function(){
	if($("input[name='dataComplete']").val() > 0){
		$("#generateSpan").text("制作完成");
	}
	if($("input[name='packageComplete']").val() > 0){
		$("#packageSpan").text("打包完成");
	}
	if($("input[name='downloadComplete']").val() > 0){
		$("#downloadSpan").text("下载完成");
	}
	if($("input[name='publishComplete']").val() > 0){
		$("#publishSpan").text("发布完成");
	}
	if($("input[name='printComplete']").val() > 0){
		$("#printSpan").text("印刷完成");
	}

	//制作
	$("#generateBtn").click(function(){
		var msg = "请确认 ：生成二维码类型：" + $("#type option:selected").text() + ", 数量：" + $("#quantity").val() + "？";
		if(confirm(msg)){
			$.ajax({
				url:"/mxjqrcodetemp.do?method=generate",
				data:{
					sn:$("input[name='sn']").val(),
					quantity:$("#quantity").val(),
					type:$("#type").val(),
					packageName:$("input[name='packageName']").val(),
					packagePath:$("input[name='packagePath']").val()
				},
				type:"post",
				cache:false,
				dataType:"json",
				success:function(data){
					$("#generateSpan").text("制作完成");
					if(timeObj){
						clearInterval(timeObj);
					}
					alert("制作完成！");
					window.location.href = "/mxjqrcodetemp.do?method=edit&sn=" + data
				},
				error:function(){

				}
			});
			//获取进度
			timeObj = setInterval(getProcess, 1000);
		}
	});
	//打包
	$("#packageBtn").click(function(){
		if(confirm("打包时间可能会稍长，需要耐心等待，确认？")){
			$.ajax({
				url:"/mxjqrcodetemp.do?method=package",
				data:{
					sn:$("input[name='sn']").val(),
					strategyType:$("#strategyType").val()
				},
			type:"post",
			cache:false,
			dataType:"json",
			success:function(data){
					$("#packageSpan").text("打包完成");
					if(timeObj){
						clearInterval(timeObj);
					}
					alert("打包完成！");
					window.location.href = "/mxjqrcodetemp.do?method=edit&sn=" + data
			},
			error:function(){

			}
			});
			//获取进度
			timeObj = setInterval(getProcess, 1000);
		}
	});
	//下载
	$("#downloadBtn").click(function(){
		window.location.href = "/mxjqrcodetemp.do?method=download&sn=" + $("input[name='sn']").val();
	});
	//发布
	$("#completeAllBtn").click(function(){
		if(window.confirm("请检查数据无误后在点‘发布’？")){
			if(window.confirm("确认？")){
				$("form").get(1).submit();
			}
		}
	});
	//回滚
	$("#rollbackBtn").click(function(){
		if(window.confirm("确认执行回滚操作？")){
			if(window.confirm("二维码印刷成功后不可以回滚，确认？")){
				if(window.confirm("确认？")){
					$.ajax({
						url:"/mxjqrcodetemp.do?method=rollback",
						data:{
						sn:$("input[name='sn']").val()
					},
					type:"post",
					cache:false,
					dataType:"json",
					success:function(data){
						alert("回滚完成！");
						window.location.href = "/mxjqrcodetemp.do?method=edit&sn=" + data
					},
					error:function(){

					}
					});
				}
			}
		}
	});
	//印刷
	$("#printBtn").click(function(){
		if(window.confirm("确认后数据不可以修改，确认印刷？")){
			if(window.confirm("确认？")){
				$("form").get(2).submit();
			}
		}
	});
	//备注
	$("#remark").blur(function(){
		$.ajax({
			url:"/mxjqrcodetemp.do?method=remark",
			data:{
				sn:$("input[name='sn']").val(),
				remark:$("#remark").val()
			},
		type:"post",
		cache:false,
		dataType:"json",
		success:function(data){

		},
		error:function(){

		}
		});
	});


});

function getProcess(){
	$.ajax({
		url:"/mxjqrcodetemp.do?method=process",
		data:{},
		type:"get",
		cache:false,
		dataType:"json",
		success:function(data){
			var progressValue = data * 100;
			$("#progressDiv").css("width", progressValue);
			$("#progressSpan").text(progressValue + "%");
			if(data >= 1){
				clearInterval(timeObj);
				$("#progressDiv").css("width", 0);
			}
		},
		error:function(){

		}
	});
}