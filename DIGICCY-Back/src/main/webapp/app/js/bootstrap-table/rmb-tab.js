
$.ajax({
	url:'/rmb/getWithdraw.do',
	type:'get',
	dataType:'json',
	data:{
		userName:'',
		state:'',
		startDate:'',
		endDate:'',
		curPage:1,
		pageItem:10
	},
	success:function(data){
		var countItem = $('#countItemW');
		var curPage = $('#curPageW');
		var pageItem = $('#pageItemW');
		if (data.code = 100) {
			var tab = $('#withdraw_table');
			tab.html("<thead>" + "<tr><th>用户账号</th>  <th>姓名</th>  <th>提现金额</th>  <th>手续费</th>  <th>到账金额</th>  <th>提现时间</th>  <th>用户编号</th>  <th>提现银行</th> <th>订单状态</th> </tr>" + "</thead><body align='center'>");
						console.log(data);
			$.each(data.data, function(a,b){
				var sta = b.state;
				var str1 = b.id+','+b.user_no+','+b.price;
              if (sta == 0){
                  sta = '<button class="btn btn-info" ' +
                      'onclick="confirmWithdraw(\''+ str1 + '\')">' +
                      '<i class="icon-money icon-white"></i>&nbsp;确认到账</button>';
              }
              if (sta == 1){
                 sta = '已到账';
              }
				
  			var str = "<tr align='center'>" +
			"<td align='center'>" +
			b.attr1 +
			"</td>" +
			"<td align='center'>" +
			b.realName +
			"</td>" +
			"<td align='center'>" +
			b.price +
			"</td>" +
			"<td align='center'>" +
			b.poundage +
			"</td>" 
			+"<td align='center'>" +b.actual_price +"</td>" 
			+"<td align='center'>" + b.date + "</td>" 
			+"<td align='center'>" + b.userCode + "</td>" 
			+"<td align='center'>" + b.attr2 + "</td>"
		 	+"<td align='center'>" +sta +"</td>" 
			+"</tr>";

				tab.html(tab.html()+str);
			});
			
			tab.html(tab.html()+"</body>");
			
			var str = '';
			str += '<option value="10" selected>10</option>';
			if (data.count > 19) {
				str += '<option value="20">20</option>';
			}
			if (data.count > 39) {
				str += '<option value="40">40</option>';
			}
			if (data.count > 49) {
				str += '<option value="50">50</option>';
			}

			// 分页数据
			countItem.html(data.count);// 总数
			curPage.html(1);// 当前第几页
			pageItem.html(str);// 每页多少条
		}
	}
});


$
		.ajax({
			url : '/rmb/getRecharge.do',
			type : 'get',
			dataType : 'json',
			data : {
				userName : '',
				state : '',
				startDate : '',
				endDate : '',
				curPage : 1,
				pageItem : 10
			},
			success : function(data) {
				var countItem = $('#countItemR');
				var curPage = $('#curPageR');
				var pageItem = $('#pageItemR');
				// 显示数据
				if (data.code = 100) {
					// alert('test');
					var tab = $('#recharge_table');
					tab.html("<thead>" + "<tr>" + "<th>用户账号</th>"
							+ "<th>姓名</th>" + "<th>订单编号</th>"
							+ "<th>充值金额</th>" + "<th>充值时间</th>"
							+ "<th>用户编号</th>  <th>充值方式</th>  <th>订单状态</th>" + "</tr>" + "</thead><body align='center'>");
					$
							.each(
									data.data,
									function(a, b) {
										var str = b.recharge_order;
										str = '<button class="btn btn-info" '
												+ 'onclick="confirmRecharge(\''
												+ str
												+ '\',this)">'
												+ '<i class="icon-money icon-white"></i>&nbsp;确认到账</button>';
										if (b.state != 0) {
											str = '已到账';
										}
										var tpe = '易付通';
										// if (b.recharge_type == 1) {
										// tpe = '支付宝';
										// }
										// if (b.recharge_type == 2) {
										// tpe = '微信';
										// }
										tab.html(tab.html() + "<tr align='center'>"
												+ "<td align='center'>" + b.attr1
												+ "</td>" 
												+ "<td align='center'>" + b.realName
												+ "</td>" 
												+ "<td align='center'>" + b.recharge_order
												+ "</td>" 
												+ "<td align='center'>"+ b.recharge_price
												+ "</td>"
												+ "<td align='center'>" + b.date
												+ "</td>"
												+ "<td align='center'>" + b.userCode
												+ "</td>"
												+ "<td align='center'>" + (tpe)
												+ "</td>" 
												+ "<td align='center'>" + (str)
												+ "</td>" + "</tr>");
									});
					tab.html(tab.html() + "</body>");

					var str = '';
					str += '<option value="10" selected>10</option>';
					if (data.count > 19) {
						str += '<option value="20">20</option>';
					}
					if (data.count > 39) {
						str += '<option value="40">40</option>';
					}
					if (data.count > 49) {
						str += '<option value="50">50</option>';
					}
					// 分页数据
					countItem.html(data.count);// 总数
					curPage.html(1);// 当前第几页
					pageItem.html(str);// 每页多少条
					
				}
			}
		});
function selectWithdraw(curPagev, pageItemv){
	var userCode = $('#userCode').val();
	var realName =$('#realName').val(); 
	var phone = $('#phone').val();
	var state = $('#state').val();
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
//	alert('test');
	$.ajax({
		url:'/rmb/getWithdraw.do',
		type:'get',
		dataType:'json',
		data:{
			userCode:userCode,
			realName:realName,
			phone:phone,
			state:state,
			startDate:startDate,
			endDate:endDate,
			curPage:curPagev,
			pageItem:pageItemv
		},
		success:function(data){
			var countItem = $('#countItemW');
			var curPage = $('#curPageW');
			var pageItem = $('#pageItemW');
			if (data.code = 100) {
				var tab = $('#withdraw_table');
				tab.html("<thead>" + "<tr><th>用户账号</th>  <th>姓名</th>  <th>提现金额</th>  <th>手续费</th>  <th>到账金额</th>  <th>提现时间</th>  <th>用户编号</th>  <th>提现银行</th> <th>订单状态</th> </tr>" + "</thead><body align='center'>");
				$.each(data.data, function(a,b){
					var sta = b.state;
					var str1 = b.id+','+b.user_no+','+b.price;
	              if (sta == 0){
	                  sta = '<button class="btn btn-info" ' +
	                      'onclick="confirmWithdraw(\''+ str1 + '\')">' +
	                      '<i class="icon-money icon-white"></i>&nbsp;确认到账</button>';
	              }
	              if (sta == 1){
	                 sta = '已到账';
	              }
					var str = "<tr align='center'>" +
					"<td align='center'>" +
					b.attr1 +
					"</td>" +
					"<td align='center'>" +
					b.realName +
					"</td>" +
					"<td align='center'>" +
					b.price +
					"</td>" +
					"<td align='center'>" +
					b.poundage +
					"</td>" 
					+"<td align='center'>" +b.actual_price +"</td>" 
					+"<td align='center'>" + b.date + "</td>" 
					+"<td align='center'>" + b.userCode + "</td>" 
					+"<td align='center'>" + b.attr2 + "</td>"
				 	+"<td align='center'>" +sta +"</td>" 
					+"</tr>";
 
					
					tab.html(tab.html()+str);
				});
				
				tab.html(tab.html()+"</body>");
				var str = '';
				str += '<option value="10" selected>10</option>';
				if (data.count > 19) {
					str += '<option value="20">20</option>';
				}
				if (data.count > 39) {
					str += '<option value="40">40</option>';
				}
				if (data.count > 49) {
					str += '<option value="50">50</option>';
				}
				// 分页数据
				countItem.html(data.count);// 总数
				curPage.html(curPagev);// 当前第几页
				pageItem.html(str);// 每页多少条
			}
		}
	});
}
function selectRecharge(curPagev, pageItemv) {
	var userCode = $('#userCode').val();
	
	var orderNumber = $('#orderNumber').val();
	var phone = $('#phone').val();
	var realName =$('#realName').val(); 
	var state = $('#state').val();
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	
	$
			.ajax({
				url : '/rmb/getRecharge.do',
				type : 'get',
				dataType : 'json',
				data : {
					userCode : userCode,
					orderNumber:orderNumber,
					phone : phone,
					realName : realName,
					state : state,
					startDate : startDate,
					endDate : endDate,
					curPage : curPagev,
					pageItem : pageItemv
					
				},
				success : function(data) {
					if (data.code = 100) {
						var countItem = $('#countItemR');
						var curPage = $('#curPageR');
						var pageItem = $('#pageItemR');
						// alert('test');
						var tab = $('#recharge_table');
						tab.html("<thead>" + "<tr>" + "<th>用户账号</th>"
								+ "<th>姓名</th>" + "<th>订单编号</th>"
								+ "<th>充值金额</th>" + "<th>充值时间</th>"
								+ "<th>用户编号</th>  <th>充值方式</th>  <th>订单状态</th>" + "</tr>" + "</thead><body align='center'>");
						$
								.each(
										data.data,
										function(a, b) {
											var str = b.recharge_order;
											str = '<button class="btn btn-info" '
													+ 'onclick="confirmRecharge(\''
													+ str
													+ '\',this)">'
													+ '<i class="icon-money icon-white"></i>&nbsp;确认到账</button>';
											if (b.state != 0) {
												str = '已到账';
											}
											if(b.state == 2){
												str = '人工到账';
											}
											var tpe = '易付通';
											// if (b.recharge_type == 1) {
											// tpe = '支付宝';
											// }
											// if (b.recharge_type == 2) {
											// tpe = '微信';
											// }
											tab.html(tab.html() + "<tr align='center'>"
													+ "<td align='center'>" + b.attr1
													+ "</td>" 
													+ "<td align='center'>" + b.realName
													+ "</td>" 
													+ "<td align='center'>" + b.recharge_order
													+ "</td>" 
													+ "<td align='center'>"+ b.recharge_price
													+ "</td>"
													+ "<td align='center'>" + b.date
													+ "</td>"
													+ "<td align='center'>" + b.userCode
													+ "</td>"
													+ "<td align='center'>" + (tpe)
													+ "</td>" 
													+ "<td align='center'>" + (str)
													+ "</td>" + "</tr>");
										});
						tab.html(tab.html() + "</body>");

						var str = '';
						if (pageItemv == 10) {
							str += '<option value="10" selected>10</option>';
						} else {
							str += '<option value="10" >10</option>';
						}
						if (data.count > 19) {
							if (pageItemv == 20) {
								str += '<option value="20" selected>20</option>';
							} else {
								str += '<option value="20" >20</option>';
							}
						}
						if (data.count > 39) {
							if (pageItemv == 40) {
								str += '<option value="40" selected>40</option>';
							} else {
								str += '<option value="40" >40</option>';
							}
						}
						if (data.count > 49) {
							if (pageItemv == 50) {
								str += '<option value="50" selected>50</option>';
							} else {
								str += '<option value="50" >50</option>';
							}
						}
						// 分页数据
						countItem.html(data.count);// 总数
						curPage.html(curPagev);// 当前第几页
						pageItem.html(str);// 每页多少条
					}
				}
			});

}
function confirmRecharge(str,btn){
	var r = confirm("确认到账?");
	if(r){
		$.ajax({
			url:'/rmb/doRecharge.do',
			type : "post",
			dataType : "json",
			data : {
				ordId:str,
			},
			success : function(msg) {
				window.location.reload();
			},
			error:function(e){
				window.location.reload();
			}
		});
	}
	console.log(btn);
	btn.attr('disable','disable');
}
function confirmWithdraw(value) {
	var strArr = value.split(",");
//	alert(strArr[2]);
	var r = confirm("是否确认到账？");
	if (r == true) {
		$.ajax({
			url : "/rmb/doWithdraw.do",
			type : "post",
			dataType : "json",
			data : {
				recId : strArr[0],
				name : strArr[1],
				price : strArr[2]
			},
			success : function(msg) {
				// if(msg.code == "100"){
				window.location.reload();
				// }
			}
		});

	}
}