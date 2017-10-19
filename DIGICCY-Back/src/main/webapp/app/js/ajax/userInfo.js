/**
 * Created by Administrator on 2016/12/1 0001.
 */
$(function() {
	initWalletTab();
})

function updateInfo() {
	$.ajax({
		url : "/user/updateUserInfo.do",
		type : "post",
		dataType : "json",
		data : {
			no : $('#no').val(),
			name : $('#name').val(),
			real : $('#realname').val(),
			mail : $('#mail').val(),
			phone : $('#phone').val(),
			certificate : $('#certificate_num').val(),
			alipay : $('#alipay').val()
		},
		success : function(msg) {
			$.alertable.alert(msg.desc).then(
					function() {
						window.location.href = "/user/gotoUserInfo.do?id="
								+ $('#no').val();
					});
		}
	});
}

function updatePass() {
	$.alertable
			.confirm('你确定要重置密码为123456吗？')
			.then(
					function() {
						$
								.ajax({
									url : "/user/updateUserPass.do",
									type : "post",
									dataType : "json",
									data : {
										no : $('#no').val(),
										pass : '123456',
										deal : '123456',
									},
									success : function(msg) {
										$.alertable
												.alert(msg.desc)
												.then(
														function() {
															window.location.href = "/user/gotoUserInfo.do?id="
																	+ $('#no')
																			.val();
														});
									}
								});
					});
}

function initWalletTab() {
	$.ajax({
		url : "/user/getWallet.do",
		type : "post",
		dataType : "json",
		data : {
			condition : 'user_no',
			value : $('#no').val()
		},
		success : function(msg) {
			$("#wallet_table").bootstrapTable({
				// search:true,
				pagination : true,
				pageList : [ 10, 25, 50, 100 ],
				pageSize : 10,
				// sortable: true, //是否启用排序
				// sortOrder: "asc", //排序方式
				// url:'/assess/getAssess.do',
				data : msg.data,
				// strictSearch: true,
				// 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
				// 设置为limit可以获取limit, offset, search, sort, order
				/*
				 * queryParamsType : "undefined", queryParams: function
				 * queryParams(params) { // 设置查询参数 var param = { // limit:
				 * params.limit, //第几条记录 pageIndex: params.pageNumber, pageSize:
				 * params.pageSize, search:params.search }; return param; },
				 */
				// server
				sidePagination : "client", // 服务端请求
				idField : 'id',
				cache : false,
				columns : [ [// 列
				{
					title : '货币类型',
					field : 'attr2',
					width : 80,
					align : 'center'
				}, {
					title : '可用数量',
					field : 'enable_coin',
					width : 60,
					align : 'center'
				}, {
					title : '冻结数量',
					field : 'unable_coin',
					width : 60,
					align : 'center'
				}, {
					title : '总量',
					field : 'total_price',
					width : 60,
					align : 'center'
				}, {
					title : '钱包地址',
					field : 'wallet_address',
					width : 120,
					align : 'center'
				}, ] ],
			});
		}
	});
}

function initEntrustTab() {
	$.ajax({
		url : "/user/getUserEntrust.do",
		type : "post",
		dataType : "json",
		data : {
			userNo : $('#no').val()
		},
		success : function(msg) {
			$("#entrust_table").bootstrapTable({
				// search:true,
				pagination : true,
				pageList : [ 10, 25, 50, 100 ],
				pageSize : 10,
				// sortable: true, //是否启用排序
				// sortOrder: "asc", //排序方式
				// url:'/assess/getAssess.do',
				data : msg.data,
				// strictSearch: true,
				// 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
				// 设置为limit可以获取limit, offset, search, sort, order
				/*
				 * queryParamsType : "undefined", queryParams: function
				 * queryParams(params) { // 设置查询参数 var param = { // limit:
				 * params.limit, //第几条记录 pageIndex: params.pageNumber, pageSize:
				 * params.pageSize, search:params.search }; return param; },
				 */
				// server
				sidePagination : "client", // 服务端请求
				idField : 'id',
				cache : false,
				columns : [ [// 列
				{
					title : '委托货币',
					field : 'attr1',
					width : 80,
					align : 'center'
				}, {
					title : '委托类型',
					field : 'entrust_type',
					width : 60,
					align : 'center',
					formatter : function(value) {
						var e;
						if (value == 1) {
							e = '卖';
						}
						if (value == 0) {
							e = '买';
						}
						return e;
					}
				}, {
					title : '委托价格',
					field : 'entrust_price',
					width : 60,
					align : 'center'
				}, {
					title : '委托数量',
					field : 'entrust_num',
					width : 60,
					align : 'center'
				}, {
					title : '成交数量',
					field : 'deal_num',
					width : 60,
					align : 'center'
				}, {
					title : '手续费',
					field : 'piundatge',
					width : 60,
					align : 'center'
				}, {
					title : '状态',
					field : 'state',
					width : 60,
					align : 'center',
					formatter : function(value) {
						var e;
						if (value == 0) {
							e = '委托中';
						}
						if (value == 1) {
							e = '已完成';
						}
						if (value == 2) {
							e = '撤销';
						}
						return e;
					}
				}, {
					title : '日期',
					field : 'date',
					width : 60,
					align : 'center'
				}, ] ],
			});
		}
	});
}

function initTradeTab() {
	$.ajax({
		url : "/user/getUserDeal.do",
		type : "post",
		dataType : "json",
		data : {
			userNo : $('#no').val()
		},
		success : function(msg) {
			$("#deal_table").bootstrapTable({
				// search:true,
				pagination : true,
				pageList : [ 10, 25, 50, 100 ],
				pageSize : 10,
				// sortable: true, //是否启用排序
				// sortOrder: "asc", //排序方式
				// url:'/assess/getAssess.do',
				data : msg.dealDetailList,
				// strictSearch: true,
				// 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
				// 设置为limit可以获取limit, offset, search, sort, order
				/*
				 * queryParamsType : "undefined", queryParams: function
				 * queryParams(params) { // 设置查询参数 var param = { // limit:
				 * params.limit, //第几条记录 pageIndex: params.pageNumber, pageSize:
				 * params.pageSize, search:params.search }; return param; },
				 */
				// server
				sidePagination : "client", // 服务端请求
				idField : 'id',
				cache : false,
				columns : [ [// 列
				{
					title : '交易货币',
					field : 'attr1',
					width : 80,
					align : 'center'
				}, {
					title : '交易类型',
					field : 'deal_type',
					width : 60,
					align : 'center',
					formatter : function(value) {
						var e;
						if (value == 1) {
							e = '卖';
						}
						if (value == 0) {
							e = '买';
						}
						return e;
					}
				}, {
					title : '交易价格',
					field : 'deal_price',
					width : 60,
					align : 'center'
				}, {
					title : '交易数量',
					field : 'deal_num',
					width : 60,
					align : 'center'
				}, {
					title : '总额',
					field : 'sum_price',
					width : 60,
					align : 'center'
				}, {
					title : '手续费',
					field : 'piundatge',
					width : 60,
					align : 'center'
				}, {
					title : '日期',
					field : 'date',
					width : 60,
					align : 'center'
				}, ] ],
			});
		}
	});
}

function initAddressTab() {
	$
			.ajax({
				url : "/user/getUserAddress.do",
				type : "post",
				dataType : "json",
				data : {
					userNo : $('#no').val()
				},
				success : function(msg) {
					$("#address_table")
							.bootstrapTable(
									{
										// search:true,
										pagination : true,
										pageList : [ 10, 25, 50, 100 ],
										pageSize : 10,
										// sortable: true, //是否启用排序
										// sortOrder: "asc", //排序方式
										// url:'/assess/getAssess.do',
										data : msg.data,
										// strictSearch: true,
										// 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
										// 设置为limit可以获取limit, offset, search,
										// sort, order
										/*
										 * queryParamsType : "undefined",
										 * queryParams: function
										 * queryParams(params) { // 设置查询参数 var
										 * param = { // limit: params.limit,
										 * //第几条记录 pageIndex: params.pageNumber,
										 * pageSize: params.pageSize,
										 * search:params.search }; return param; },
										 */
										// server
										sidePagination : "client", // 服务端请求
										idField : 'id',
										cache : false,
										columns : [ [// 列
												{
													title : '用户ID',
													field : 'user_no',
													width : 60,
													align : 'center'
												},
												{
													title : '用户姓名',
													field : 'name',
													width : 60,
													align : 'center'
												},
												{
													title : '手机号码',
													field : 'phone',
													width : 60,
													align : 'center'
												},
												{
													title : '居住地省市区',
													field : 'remark_address',
													width : 60,
													align : 'center'
												},
												{
													title : '居住地详细地址',
													field : 'address',
													width : 60,
													align : 'center'
												},
												{
													title : '邮政编码',
													field : 'card',
													width : 60,
													align : 'center'
												},
												{
													title : '日期',
													field : 'date',
													width : 60,
													align : 'center'
												},
												{
													title : '操作',
													field : 'id',
													width : 120,
													align : 'center',
													formatter : function(value,
															row, index) {
														var str = row.user_no
																+ ","
																+ row.name
																+ ","
																+ row.phone
																+ ","
																+ row.remark_address
																+ ","
																+ row.address
																+ ","
																+ row.card;
														var e;
														e = '<button class="btn btn-warning" id="btn_update" '
																+ 'onclick="openUpdateBox(\''
																+ str
																+ '\')">'
																+ '<i class="icon-pencil icon-white"></i>修改</button>&nbsp;&nbsp;';
														return e;
													}
												}, ] ],
									});
				}
			});
}
// ICO众筹
function initICOTab() {
	$.ajax({
		url : "/crowFundingDetail/getCrowdFundingDetailById_user.do",
		type : "post",
		dataType : "json",
		data : {
			userId : $('#no').val()
		},
		success : function(msg) {
			$("#ico_table").bootstrapTable({
				// search:true,
				pagination : true,
				pageList : [ 10, 25, 50, 100 ],
				pageSize : 10,
				// sortable: true, //是否启用排序
				// sortOrder: "asc", //排序方式
				// url:'/assess/getAssess.do',
				data : msg.data,
				// strictSearch: true,
				// 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
				// 设置为limit可以获取limit, offset, search, sort, order
				/*
				 * queryParamsType : "undefined", queryParams: function
				 * queryParams(params) { // 设置查询参数 var param = { // limit:
				 * params.limit, //第几条记录 pageIndex: params.pageNumber, pageSize:
				 * params.pageSize, search:params.search }; return param; },
				 */
				// server
				sidePagination : "client", // 服务端请求
				idField : 'id',
				cache : false,
				columns : [ [// 列
				{
					title : '用户账号',
					field : 'attr1',
					width : 60,
					align : 'center'
				}, {
					title : '众筹贵人通数',
					field : 'ico_user_number',
					width : 60,
					align : 'center'
				}, {
					title : '众筹茅台酒数',
					field : 'attr1',
					width : 60,
					align : 'center'
				}, {
					title : '日期',
					field : 'date',
					width : 60,
					align : 'center'
				}, ] ],
			});
		}
	});
}

// 用户积分获取记录
function initIntegralTab(pageItem, curPage) {
	var userno = $('#no').val();
	$("#initIntegral_table").bootstrapTable(
			{

				// 请求方法
				method : 'post',
				contentType : "application/x-www-form-urlencoded",
				// 是否显示行间隔色
				striped : true,
				// 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
				cache : false,
				// 是否显示分页（*）
				pagination : true,
				// 是否启用排序
				sortable : false,
				// 排序方式
				sortOrder : "asc",
				// 初始化加载第一页，默认第一页
				// 我设置了这一项，但是貌似没起作用，而且我这默认是0,- -
				// pageNumber:1,
				// 每页的记录行数（*）
				pageSize : 10,
				// 可供选择的每页的行数（*）
				pageList : [ 3, 6, 10, 25, 50, 100 ],
				// 这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据
				url : "/user/getUserIntegralLog.do",
				// 默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order
				// Else
				// queryParamsType:'',
				// //查询参数,每次调用是会带上这个参数，可自定义
				queryParams : function(params) {
					// var subcompany = $('#subcompany option:selected').val();
					// var name = $('#name').val();
					// return {
					// pageNumber: params.offset+1,
					// pageSize: params.limit,
					// companyId:subcompany,
					// name:name
					// };
					var curPageNum = params.offset / params.limit + 1;
					var perSize = params.limit;

					var param = {
						currentPageNum : curPageNum,
						perPageSize : perSize,
						userId : userno
					};
					return param;
				},
				// 分页方式：client客户端分页，server服务端分页（*）
				sidePagination : "server",
				// 是否显示搜索
				search : false,
				// Enable the strict search.
				strictSearch : true,
				// Indicate which field is an identity field.
				idField : "id",
				columns : [ [// 列
				{
					title : '用户名称',
					field : 'real_name',
					width : 60,
					align : 'center'
				}, {
					title : '积分获取',
					field : 'number',
					width : 60,
					align : 'center'
				}, {
					title : '获取方式',
					field : 'type',
					width : 60,
					align : 'center'
				}, {
					title : '日期',
					field : 'createTime',
					width : 60,
					align : 'center'
				}, ] ],
				pagination : true
			});
}

function openUpdateBox(str) {
	var strArr = str.split(",");
	$("#user_no").val(strArr[0]);
	$("#user_name").val(strArr[1]);
	$("#user_phone").val(strArr[2]);
	$("#remark_address").val(strArr[3]);
	$("#address").val(strArr[4]);
	$("#card").val(strArr[5]);

	$("#box_update").css({
		"display" : "block"
	});
}