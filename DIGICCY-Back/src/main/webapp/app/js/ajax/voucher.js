showInput();
getVoucher();
$('#state')
		.html(
				$('#state').html()
						+ "<option value='1'>未验证</option><option value='2'>正在验证</option><option value='3'>验证失败</option><option value='4'>验证通过</option>");
// 根据什么查询
function showInput() {
	var query = $('#query').val();
	if (query == "user_no") {
		$('#state').hide();
		$('#name').show();
	}
	if (query == "state_type") {
		$('#name').hide();
		$('#state').show();
	}
}
// 更改状态
// 查询
function getVoucher() {
	var searchKey = $('#query').val();
	var searchValue = "";
	if (searchKey == "user_no") {
		searchKey = "userNo";
		searchValue = $('#name').val();
	}
	if (searchKey == "state_type") {
		searchKey = "voucher_state";
		searchValue = $('#state').val();
	}
	selectbyfild(
			searchKey,
			searchValue,
			function(data) {
				if (data.result != "none") {
					var list = data.result;
					var tab = $('#voucher_table');
					tab
							.html("<thead sthle='width:99%;'>"
									+ "<th style='text-align: center; min-width: 80px; width:20%;'>id</th>"
									+ "<th style='text-align: center; min-width: 80px; width:20%;'>用户编号</th>"
									+ "<th style='text-align: center; min-width: 80px; width:20%;'>真实姓名</th>"
									+ "<th style='text-align: center; min-width: 80px; width:20%;'>审核状态</th>"
									+ "<th style='text-align: center; min-width: 80px; width:20%;'>操作</th>"
									+ "</thead><tbody sthle='width:99%;'>");
					var str = "";
					$
							.each(
									list,
									function(a, b) {
										var sta = "";
										var use = "";
										switch (b.state) {
										case 1:
											sta = "未验证";
											use = "<button onclick='loadModify("
													+ b.userNo
													+ ",1)' class='btn btn-primary btn-lg' data-toggle='modal' data-target='#myModal'>开始验证</button>";
											break;
										case 2:
											sta = "正在验证";
											use = "<button onclick='loadModify("
													+ b.userNo
													+ ",0)' class='btn btn-primary btn-lg' data-toggle='modal' data-target='#myModal'>查看</button>";
											break;
										case 3:
											sta = "验证失败";
											use = "<button onclick='loadModify("
													+ b.userNo
													+ ",0)' class='btn btn-primary btn-lg' data-toggle='modal' data-target='#myModal'>查看</button>";
											break;
										case 4:
											sta = "验证通过";
											use = "<button onclick='loadModify("
													+ b.userNo
													+ ",0)' class='btn btn-primary btn-lg' data-toggle='modal' data-target='#myModal'>查看</button>";
											break;

										default:
											sta = "未识别";
											use = "<button onclick='loadModify("
													+ b.userNo
													+ ",1)' class='btn btn-primary btn-lg' data-toggle='modal' data-target='#myModal'>开始验证</button>";
											break;
										}
										str += "<tr style='width:100%;'>"
												+ "<td style='text-align: center; min-width: 80px; width:20%;'>"
												+ b.id
												+ "</td>"
												+ "<td style='text-align: center; min-width: 80px; width:20%;'>"
												+ b.userNo
												+ "</td>"
												+ "<td style='text-align: center; min-width: 80px; width:20%;'>"
												+ b.trueName
												+ "</td>"
												+ "<td style='text-align: center; min-width: 80px; width:20%;'>"
												+ sta
												+ "</td>"
												+ "<td style='text-align: center; min-width: 80px; width:20%;'>"
												+ use + "</td>" + "</tr>";
									});
					str += "</tbody>";
					tab.html(tab.html() + str);
				}
			});
}
// 强制审核
function masttocontroll(k) {
	if (k.checked) {
		$('#modifyTable select[mytag=myselectstate]').removeAttr('disabled');
	} else {
		$('#modifyTable select[mytag=myselectstate]').attr("disabled",
				"disabled");
		$('#modifyTable select[mytag=myselectstate] option[value=-1]').attr("selected","selected");
	}
}
// 加载单个
function loadModify(id, k) {

	var mycontroll = "";
	if (k == 1) {
		// 更改状态为正在校验
		modifystate(id, 2, function(data) {

		});
	} else {
		mycontroll = "<input type='checkbox' onchange='masttocontroll(this)' />强制操作<br />";
	}
	mycontroll += "<select " + (k == 1 ? "" : "disabled='disabled'")
			+ " mytag='myselectstate' >" + "<option value='4'>通过审核</option>"
			+ "<option value='3'>审核失败</option>"
			+ "<option selected='selected' value='" + (k == 1 ? 1 : -1)
			+ "'>不审核</option>" + "</select>";
	selectbyfild('userNo', id, function(data) {
		if (data.result.length > 0) {
			var item = data.result[0];
			var tab = $('#modifyTable');
			var zhentype = item.cardType;
			switch (zhentype) {
			case 0:
				zhentype = item.myvoucherType;
				break;

			case 1:
				zhentype = "身份证";
				break;
			case 2:
				zhentype = "军官证";
				break;
			case 3:
				zhentype = "护照";
				break;
			case 4:
				zhentype = "台湾居民通行证";
				break;
			case 5:
				zhentype = "港澳居民通行证";
				break;
			}
			tab.html("<tbody>" + "<tr>" + "<td>真实姓名:</td>" + "<td>"
					+ item.trueName
					+ "<input type='hidden' id='modifyid' value='"+item.userNo+"' /></td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td>证件类型:</td>"
					+ "<td>"
					+ zhentype
					+ "</td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td>证件号码:</td>"
					+ "<td>"
					+ item.cardId
					+ "</td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td>验证图</td>"
					+ "<td>"
					+ (item.imgUrl1 == "" ? "" : "<img src='" + item.imgUrl1
							+ "' /><br />")
					+ (item.imgUrl2 == "" ? "" : "<img src='" + item.imgUrl2
							+ "' /><br />")
					+ (item.imgUrl3 == "" ? "" : "<img src='" + item.imgUrl3
							+ "' />") + "</td>" + "</tr>" + "<tr>"
					+ "<td>操作</td>" + "<td>" + mycontroll + "</td>" + "</tr>"
					+ "</tbody>");
		}
	});

}


//提交审核
function tovoucher(){
	var userNo = $('#modifyid').val();
	var state = $('#modifyTable select[mytag=myselectstate]').val();
	if(state != -1){
		modifystate(userNo, state, function(data){
			if(data.code==100){
				alert("提交成功");
			}else{
				alert("提交失败");
			}
			location.reload();
		});
	}
}

// 单个查询
function selectbyfild(field, id, successfun) {
	$.ajax({
		url : '/user/getallvoucher.do',
		data : {
			field : field,
			value : id
		},
		type : 'POST',
		dataType : 'json',
		success : successfun
	});
}
// 更改状态
function modifystate(id, state, successfun) {
	$.ajax({
		url : '/user/modifyvoucherstate.do',
		data : {
			userNo : id,
			state : state
		},
		type : 'POST',
		dataType : 'json',
		success : successfun,
		error : function(e) {
			console.log(e.responseText);
		}
	});
}