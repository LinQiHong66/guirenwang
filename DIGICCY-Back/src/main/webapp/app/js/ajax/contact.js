$
		.ajax({
			url : '/other/getAllContact.do',
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				console.log(data);
				if (data.code == 100) {
					$('#recharge_table').html(
							"<thead>" + "<th>id</th>" + "<th>联系信息</th>"
									+ "<th>邮箱</th>" + "<th>微信</th>"
									+ "<th>微信二维码</th>" + "<th>qq</th>" + "<th>电话</th>"
									+ "<th>qq二维码</th>" + "<th>地址</th>"
									+ "<th>操作</th>" + "</thead><tbody>");
					var str = "";
					$
							.each(
									data.list,
									function(a, b) {
										var imgerweima = "<img style='max-height: 60px;' src='"
											+ b.wxqrcord + "' />";
										var qqerweima = "<img style='max-height: 60px;' src='"
											+ b.qqqrcord + "' />";
										str += "<tr>"
												+ "<td style='text-align: center; min-width: 80px;'>"
												+ b.id
												+ "</td>"
												+ "<td style='text-align: center; min-width: 80px;'>"
												+ b.remark
												+ "</td>"
												+ "<td style='text-align: center; min-width: 80px;'>"
												+ b.email
												+ "</td>"
												+ "<td style='text-align: center; min-width: 80px;'>"
												+ b.weixin
												+ "</td>"
												+ "<td style='text-align: center; min-width: 80px;'>"
												+ imgerweima
												+ "</td>"
												+ "<td style='text-align: center; min-width: 80px;'>"
												+ b.qq
												+ "</td>"
												+ "<td style='text-align: center; min-width: 80px;'>"
												+ b.telphone
												+ "</td>"
												+ "<td style='text-align: center; min-width: 80px;'>"
												+ qqerweima
												+ "</td>"
												+ "<td style='text-align: center; min-width: 80px;'>"
												+ b.address
												+ "</td>"
												+ "<td style='text-align: center; min-width: 80px;'><a href='#modifymodal' onclick='updatecontact("
												+ b.id
												+ ")' class='btn btn-primary' data-toggle='modal'>修改 </a></td>"
												+ "</tr>";

									});
					if (data.list.length == 0) {
						str += "<tr>"
								+ "<td colspan='8' style='text-align: center; min-width: 80px;'>"
								+ "<a href='#addmodal' class='btn btn-primary' data-toggle='modal'>新增</a>"
								+ "</td>" + "</tr>";
					}
					str += "</tbody>";
					$('#recharge_table')
							.html($('#recharge_table').html() + str);
				}

			}
		});

function updatecontact(id) {
	console.log(id);
	$.ajax({
		url : '/other/getContactById.do',
		type : 'POST',
		dataType : 'json',
		data : {
			id : id
		},
		success : function(data) {
			console.log(data);
			if (data.code == 100) {
				var k = data.list[0];
				$('#modifymodal input[name=id]').attr("value", k.id);
				$('#modifymodal input[name=email]').attr("value", k.email);
				$('#modifymodal input[name=weixin]').attr("value", k.weixin);
				$('#modifymodal input[name=qq]').attr("value", k.qq);
				$('#modifymodal input[name=address]').attr("value", k.address);
				$('#modifymodal input[name=remark]').attr("value", k.remark);

			}
		}
	});
}
// function delcontact(id) {
// $.ajax({
// url : '/other/delContact.do',
// type : 'POST',
// dataType : 'json',
// data : {
// id : id
// },
// success : function(data) {
// window.location.href = '/other/contact.do';
// }
// });
// }
