function checkExcel() {
	$
			.ajax({
				url : "/file/checkExcel.do",
				type : "post",
				data : new FormData($('#balanceform')[0]),
				success : function(data) {
					var executeGload = $('#executeGload');
					var executeInfo = $('#executeInfo');
					var showMessage = $('#showInfo');
					var batchView = $('#bachBalance');
					showMessage.html('excel信息如下：<br />');
					executeInfo.html("");
					// 显示各种信息
					if (data.msg != "fail") {
						if ("has no code" == data.goldCode) {
							executeInfo.html("没有设置金额阈值！！！<br />");
						} else {
							executeInfo.html(executeInfo.html() + "当前金额阈值编号为"
									+ data.goldCode + "<br />");
						}
						if (data.toMuchGold) {
							executeInfo.html(executeInfo.html()
									+ "表格总金额超过当前所设阈值！！！");
						}

						if (data.toMuchGold) {
							showMessage.html(showMessage.html()
									+ "表格总金额超过当前所设阈值！！！" + "<br />");
						}
						$.each(data.msg,
								function(a, b) {
									showMessage.html(showMessage.html() + b
											+ "<br />");
								});
						// 判断能否执行
						if (data.canBalance) {
							batchView.show();
							executeInfo
									.html(executeInfo.html() + "恭喜，表格可以批量入金");
							executeGload.removeAttr("disabled");
							return;
						}
					} else {
						executeInfo.html("表格有重复用户！！！<br />");
						showMessage.html(showMessage.html() + "表格有重复用户！！！"
								+ "<br />");
					}
					executeInfo.html(executeInfo.html() + "很抱歉该表格不能批量入金");
					// 不能执行excel
					batchView.hide();
				},
				error : function(e) {
					var showMessage = $('#showInfo');
					showMessage
							.html("excel信息如下：<br />"
									+ "上传的文件出错！<br />友情提示：上传的文件必须是Microsoft Excel 97-2003的文件，后缀是xls，请仔细检查您的文件，谢谢！！");
				},
				cache : false,
				processData : false,
				contentType : false
			});
}
function changeExcel() {
	var batchView = $('#bachBalance');
	var showMessage = $('#showInfo');
	var regResult = $('#regResult');
	var executeInfo = $('#executeInfo');

	executeInfo.html("");
	batchView.hide();
	showMessage.html('');
	regResult.html('');
}
// 执行入金
function startBalance() {
	if (confirm("确定入金？")) {
		var executeGload = $('#executeGload');
		executeGload.attr("disabled", "disabled");
		$.ajax({
			url : "/file/executeExcel.do",
			type : "post",
			data : new FormData($('#balanceform')[0]),
			success : function(data) {
				var executeInfo = $('#executeInfo');

				executeInfo.html("正在处理个数：" + data.executeSize);
			},
			cache : false,
			processData : false,
			contentType : false
		});
	}
}

// 查看执行结果
function showResult() {
	var resultSpan = $('#regResult');
	$.ajax({
		url : "/file/getResult.do",
		type : "post",
		data : new FormData($('#balanceform')[0]),
		success : function(data) {
			console.log(data);
			var balanceResult = $('#balanceResult');
			balanceResult.html("执行结果如下：<br />");
			balanceResult.html(balanceResult.html() + "一共处理" + data.total
					+ "条数据，成功执行" + data.successCount + "条数据,执行失败"
					+ data.errorCount + "条数据 <br />");
			balanceResult.html(balanceResult.html() + "失败的：<br />");
			$.each(data.errorList, function(a, b) {
				balanceResult.html(balanceResult.html() + b + "<br />");
			});
			balanceResult.html(balanceResult.html() + "成功的：<br />");
			$.each(data.successList, function(a, b) {
				balanceResult.html(balanceResult.html() + b + "<br />");
			});
		},
		cache : false,
		processData : false,
		contentType : false
	});
}