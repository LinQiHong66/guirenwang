loadAllImg();
function loadAllImg() {
	$
			.ajax({
				url : '/other/getHomeImg.do',
				type : "POST",
				dataType : 'json',
				success : function(data) {
					if (data.desc == "success") {
						var dataShow = $('#recharge_table');
						dataShow.html("");
						dataShow
								.append("<thead style='width:99%;'>"
										+ "<th style='text-align: center; min-width: 80px; width:25%; '>编号</th>"
										+ "<th style='text-align: center; min-width: 80px; width:25%; '>说明</th>"
										+ "<th style='text-align: center; min-width:80px; width:25%; '>图片</th>"
										+ "<th style='text-align: center; min-width:80px; width:25%; '>操作</th>"
										+ "</thead>");
						dataShow.append("<tbody style='width:99%'>");
						$
								.each(
										data.imgResult,
										function(a, b) {
											dataShow
													.append("<tr style='width:100%'><td style='text-align:center; min-width:80px; width:25%'>"
															+ b.id
															+ "</td>"
															+ "<td style='text-align:center; min-width:80px; width:25%'>"
															+ b.imgDescription
															+ "</td>"
															+ "<td style='text-align:center; min-width:80px; width:25%'>"
															+ "<img src='"
															+ b.imgUrl
															+ "' style='max-width:90px';width:70% />"
															+ "</td>"
															+ "<td style='text-align:center; min-width:80px; width:25%'>"
															+ "<button class='btn btn-warning' onclick='delimg("
															+ b.id
															+ ")'>删除</button><a href='#modifymodal' onclick='modify("
															+ b.id
															+ ")' class='btn btn-primary' data-toggle='modal'>修改</a>"
															+ "</td></tr>");
										});
						dataShow.append("</tbody>");
					} else {
						alert('获取失败!!!');
					}
				}
			});
}
function delimg(id) {
	if (confirm("确认删除？")) {
		$.ajax({
			url : '/other/delhomeimg.do',
			type : 'POST',
			dataType : 'json',
			data : {
				id : id
			},
			success : function(data) {
				alert(data.result);
				loadAllImg();
			},
			error : function(e) {
				console.log(e.responseText);
			}
		});
	}
}
function modify(id) {
	$.ajax({
		url : '/other/gethomeimgbyid.do',
		type : 'POST',
		dataType : 'json',
		data : {
			id : id
		},
		success : function(data) {
			if (data.code == 100) {
				loadmodifyimgmodal(data);
			} else {
				failloadmodifymodal(data.img.id);

			}
		},
		error : function(e) {
			failloadmodifymodal(id);
		}
	});
}
function loadmodifyimgmodal(data) {
	$('#success').show();
	$('#fail').hide();
	// 是否修改文件的复选框
	$('#modifyfile').on('change', function() {
		if (this.checked) {
			$('#modifymodal input[name=imgFile]').attr('disable', 'disable');
		} else {
			$('#modifymodal input[name=imgFile]').removeAttr('disable');
		}
	});
	console.log(data);
	// 图片描述
	$('#modifymodal input[name=imgInfo]')
			.attr('value', data.img.imgDescription);
	$('#modifymodal input[name=id]').attr('value', data.img.id);
	$('#modifymodal input[type=submit]').attr('value', '修改');
	$('#modifymodal').attr('onsubmit', 'return true');
}
function failloadmodifymodal(id) {
	$('#modifymodal').attr('onsubmit', 'return false');
	$('#modifymodal input[type=submit]').attr('value', '重试');
	$('#modifymodal input[type=submit]').attr('onclick', 'modify(' + id + ')');
	$('#success').hide();
	$('#fail').show();
}