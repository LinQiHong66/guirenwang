//用户信息rw
$.ajax({
	url : '/user/getUserInfoById.do',
	type : 'POST',
	dataType : 'json',
	data : {
		id : id,
	},
	success : function(data) {
		if (data.code == 100) {
			$('#messageset_id').attr("value", data.result.id);
			$('#limit_date').attr("value", data.result.limit_date);
			$('#limit_number').attr("value", data.result.limit_number);
			$('#limit_ip').attr("value", data.result.limit_ip);
			$('#limit_name').attr("value", data.result.limit_name);
		} else {
			alert("获取失败");
		}
	}
});
// 查询用户
function selectUser() {
	var phone = $('#phone').val();
	$
			.ajax({
				url : '/user/getUserByPhone.do',
				dataType : 'json',
				type : 'get',
				data : {
					phone : phone,
				},
				success : function(data) {
					var code = data.code;
					var tab = $('#user_table');
					if (code == 100) {
						tab.html("<thead>" + "<th>用户编号</th>" + "<th>用户账号</th>"
								+ "<th>真实姓名</th>" + "<th>联系电话</th>"
								+ "<th>状态</th>" + "<th>操作</th>"
								+ "</thead><body align='center'>");
						$
								.each(
										data.data,
										function(a, b) {
											var sta = '冻结';
											if (b.state == 1) {
												sta = '正常';
											}
											var str = b.id + "," + b.name + ","
													+ b.password + ","
													+ b.roleId;
											// 这个state不应该是
											// value,row.state应该是row.user_no,row.state--刘科领注
											var czstate = b.user_no + ","
													+ b.state;
											var e = '<button class="btn btn-warning" id="btn_update" '
													+ 'onclick="openUpdateBox(\''
													+ b.id
													+ '\')">'
													+ '<i class="icon-pencil icon-white"></i>&nbsp;详细</button>'
											tab.html(tab.html()
													+ "<tr align='center'>"
													+ "<td align='center'>"
													+ b.user_no + "</td>"
													+ "<td align='center'>"
													+ b.username + "</td>"
													+ "<td align='center'>"
													+ b.real_name + "</td>"
													+ "<td align='center'>"
													+ b.phone + "</td>"
													+ "<td align='center'>"
													+ sta + "</td>"
													+ "<td align='center'>" + e
													+ "</td>" + "</tr>");
										});
						tab.html(tab.html() + "</body>");
					}
				}
			});
}

function openUpdateBox(str) {
	window.location.href = "/user/gotoSensitiveinformation.do?id=" + str;
}
// 更新信息
function updateInfo() {
	$.ajax({
		url : "/user/updateUserInfo.do",
		type : "post",
		dataType : "json",
		data : {
			no : $('#no').val(),
			name : $('#name').val(),
			real : $('#realname').val(),
			phone : $('#phone').val(),
			certificate : $('#certificate_num').val(),
			alipay : $('#alipay').val()
		},
		success : function(msg) {
			$.alertable.alert(msg.desc).then(function() {
				window.location.href = window.location.href;
			});
		}
	});
}