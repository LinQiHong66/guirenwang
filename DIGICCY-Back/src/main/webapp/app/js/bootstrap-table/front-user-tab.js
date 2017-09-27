
$
		.ajax({
			url : '/user/getAllUser.do',
			dataType : 'json',
			type : 'get',
			data : {
				username : '',
				phone : '',
				state : -1,
				curpage : 1,
				pageItem : 10
			},
			success : function(data) {
				var code = data.code;
				var tab = $('#user_table');
				if (code == 100) {
					tab.html("<thead>" + "<th>用户编号</th>" + "<th>用户账号</th>"
							+ "<th>真实姓名</th>" + "<th>联系电话</th>" + "<th>状态</th>"
							+ "<th>操作</th>" + "</thead><body align='center'>");
					$
							.each(
									data.data,
									function(a, b) {
										var sta = '冻结';
										if (b.state == 1) {
											sta = '正常';
										}
										var str = b.id + "," + b.name + ","
												+ b.password + "," + b.roleId;
										// 这个state不应该是
										// value,row.state应该是row.user_no,row.state--刘科领注
										var czstate = b.user_no + "," + b.state;
										var e = '<button class="btn btn-warning" id="btn_update" '
												+ 'onclick="openUpdateBox(\''
												+ b.id
												+ '\')">'
												+ '<i class="icon-pencil icon-white"></i>&nbsp;详细</button>&nbsp;&nbsp;'
												+ '&nbsp;&nbsp;<button class="btn btn-primary" id="btn_delete" onclick="deleteUser(\''
												+ czstate
												+ '\');">'
												+ '<i class="icon-remove icon-white"></i>&nbsp;冻结/解封</button> ';
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
												+ "<td align='center'>" + sta
												+ "</td>"
												+ "<td align='center'>" + e
												+ "</td>" + "</tr>");
									});
					var checkItem = '<option value="10" selected>10</option>';

					if (data.count >= 19) {
						checkItem = checkItem
								+ '<option value="20">20</option>';
					}
					if (data.count >= 29) {
						checkItem = checkItem
								+ '<option value="30">30</option>';
					}
					if (data.count >= 39) {
						checkItem = checkItem
								+ '<option value="40">40</option>';
					}
					if (data.count >= 49) {
						checkItem = checkItem
								+ '<option value="50">50</option>';
					}

					tab.html(tab.html() + "</body>");
					$('#countItem').html(data.count);
					$('#curPage').html(1);
					$('#checkItem').html(checkItem);
				}
			}
		});
function selectUser(pageNum, pageItem) {
	var userName = $('#names').val();
	var phone = $('#phone').val();
	var state = $('#state').val();
	$
			.ajax({
				url : '/user/getAllUser.do',
				dataType : 'json',
				type : 'get',
				data : {
					username : userName,
					phone : phone,
					state : state,
					curpage : pageNum,
					pageItem : pageItem
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
													+ '<i class="icon-pencil icon-white"></i>&nbsp;详细</button>&nbsp;&nbsp;'
													+ '&nbsp;&nbsp;<button class="btn btn-primary" id="btn_delete" onclick="deleteUser(\''
													+ czstate
													+ '\');">'
													+ '<i class="icon-remove icon-white"></i>&nbsp;冻结/解封</button> ';
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
						var checkItem = '';
						if (pageItem == 10) {
							checkItem = '<option value="10" selected>10</option>';
						} else {
							checkItem = '<option value="10">10</option>';
						}
						if (data.count >= 19) {
							if (pageItem == 20) {

								checkItem = checkItem
										+ '<option value="20" selected>20</option>';
							} else {

								checkItem = checkItem
										+ '<option value="20">20</option>';
							}
						}
						if (data.count >= 29) {
							if (pageItem == 30) {
								checkItem = checkItem
								+ '<option value="30" selected>30</option>';
							} else {
								checkItem = checkItem
								+ '<option value="30">30</option>';
							}
						}
						if (data.count >= 39) {
							if (pageItem == 40) {
								checkItem = checkItem
								+ '<option value="40" selected>40</option>';
							} else {
								checkItem = checkItem
								+ '<option value="40">40</option>';
							}
						}
						if (data.count >= 49) {
							if (pageItem == 50) {
								checkItem = checkItem
								+ '<option value="50" selected>50</option>';
							} else {
								checkItem = checkItem
								+ '<option value="50">50</option>';
							}
						}

						tab.html(tab.html() + "</body>");
						$('#countItem').html(data.count);
						$('#curPage').html(pageNum);
						$('#checkItem').html(checkItem);
					}
				}
			});
}
// $("#user_table").bootstrapTable({
// // search:true,
// pagination:true,
// pageList: [10, 25, 50, 100],
// pageSize:10,
// // sortable: true, //是否启用排序
// // sortOrder: "asc", //排序方式
// url:'/user/getAllUser.do',
// search: true,// 数据源
// // strictSearch: true,
// // 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
// // 设置为limit可以获取limit, offset, search, sort, order
// /*
// * queryParamsType : "undefined", queryParams: function
// * queryParams(params) { // 设置查询参数 var param = { // limit: params.limit,
// * //第几条记录 pageIndex: params.pageNumber, pageSize: params.pageSize,
// * search:params.search }; return param; },
// */
// // server
// sidePagination: "client", // 服务端请求
// idField:'id',
// cache:false,
// columns:[[// 列
// {width:80,align:'center',checkbox:'true'},
// {title:'用户编号',field:'user_no',width:80,align:'center'},
// {title:'用户账号',field:'username',width:120,align:'center'},
// {title:'真实姓名',field:'real_name',width:100,align:'center'},
// {title:'电子邮箱',field:'mail',width:80,align:'center'},
// {title:'联系电话',field:'phone',width:120,align:'center'},
// {title:'支付宝',field:'alipay',width:80,align:'center'},
// {title:'状态',field:'state',width:120,align:'center',formatter :
// function(value) {
// var e;
// if(value == 1){
// e = '正常';
// }
// if(value ==2){
// e = '冻结';
// }
// return e;
// }},
// {title:'操作',field:'id',width:120,align:'center',
// formatter : function(value, row, index) {
// var str = value+","+row.name+","+row.password+","+row.roleId;
// //这个state不应该是 value,row.state应该是row.user_no,row.state--刘科领注
// var state = row.user_no+","+row.state;
// var e;
// e = '<button class="btn btn-warning" id="btn_update" ' +
// 'onclick="openUpdateBox(\''+ value + '\')">' +
// '<i class="icon-pencil icon-white"></i>&nbsp;详细</button>&nbsp;&nbsp;' +
// '&nbsp;&nbsp;<button class="btn btn-primary" id="btn_delete"
// onclick="deleteUser(\''+state+'\');">' +
// '<i class="icon-remove icon-white"></i>&nbsp;冻结/解封</button> ';
// return e;
// }},
// ]],
//
// });

function openUpdateBox(str) {
	window.location.href = "/user/gotoUserInfo.do?id=" + str;
}

function deleteUser(str) {

    var strArr = str.split(",");
    var state = strArr[1];
    var s;
    //这里的sate默认是0所以要用else判断，否则state会为空！！！--刘科领
//    if(state == 1){
//        s = 2;
//    }
//    alert(state);
    if(state == 0){
        s = 1;
    }else{
    	s = 0;
    }
    $.alertable.confirm('你确定要冻结/解封此用户吗？').then(function() {
        $.ajax({
            url:"/user/updateUserState.do",
            type: "post",
            dataType: "json",
            data:{
                no:strArr[0],
                state:s
            },
            success: function (msg) {
                // if(msg.code == "100"){
                $.alertable.alert(msg.desc);
                $("#box_add").css({"display":"none"});
                window.location.reload();
                // }
            }
        });
    });

}