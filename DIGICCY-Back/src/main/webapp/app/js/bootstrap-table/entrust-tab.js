/**
 * Created by Administrator on 2017/1/4 0004.
 */
/*
 * $("#entrust_all_table").bootstrapTable({ // search:true, pagination:true,
 * pageList: [10, 25, 50, 100], pageSize:10, // sortable: true, //是否启用排序 //
 * sortOrder: "asc", //排序方式 url:'/entrust/getAllEntrustRecord.do', search:
 * true,// 数据源 // strictSearch: true, //
 * 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder //
 * 设置为limit可以获取limit, offset, search, sort, order
 * 
 * queryParamsType : "undefined", queryParams: function queryParams(params) { //
 * 设置查询参数 var param = { // limit: params.limit, //第几条记录 pageIndex:
 * params.pageNumber, pageSize: params.pageSize, search:params.search }; return
 * param; },
 *  // server sidePagination: "client", // 服务端请求 idField:'id', cache:false,
 * columns:[[// 列 {title:'用户编号',field:'user_no',width:120,align:'center'},
 * {title:'委托币种',field:'entrust_coin',width:120,align:'center'},
 * {title:'委托类型',field:'entrust_type',width:100,align:'center',formatter:function
 * (value) { if(value == 0)return "买"; if(value == 1)return "卖"; }},
 * {title:'委托价格',field:'entrust_price',width:100,align:'center',formatter:function
 * (value) { return value.toFixed(3); }},
 * {title:'委托数量(个)',field:'entrust_num',width:100,align:'center'},
 * {title:'成交数量(个)',field:'deal_num',width:100,align:'center'},
 * {title:'手续费',field:'piundatge',width:100,align:'center',formatter:function
 * (value) { return value.toFixed(3); }},
 * {title:'状态',field:'state',width:100,align:'center',formatter:function (value) {
 * if(value == 0)return "<span style='color: blue'>委托中</span>"; if(value ==
 * 1)return "<span style='color: green'>已完成</span>"; if(value == 2)return
 * "撤销"; if(value == 3)return "全部"; }},
 * {title:'委托时间',field:'date',width:100,align:'center'},
 * {title:'操作',field:'id',width:100,align:'center',
 * formatter:function(value,row,index){ var e; var id= value; if(row.state != 0)
 * return e; e = '&nbsp;&nbsp;<button class="btn btn-warning" id="btn_update"
 * onclick="updateEntrustManage('+id+');">' + '<i class="icon-pencil
 * icon-white"></i>撤销</button> &nbsp;&nbsp;'; return e; } } ]],
 * 
 * });
 */

$
		.ajax({
			url : '/entrust/getAllEntrustRecord.do',
			type : 'GET',
			dataType : 'json',
			success : function(data) {
				var tab = $('#entrust_all_table');
				tab.html('<thead style="width:100%">'
						+ '<tr style="width:100%">' + '<td>用户编号</td>'
						+ '<td>委托币种</td>' + '<td>委托类型</td>' + '<td>委托价格</td>'
						+ '<td>委托数量(个)</td>' + '<td>成交数量(个)</td>'
						+ '<td>手续费</td>' + '<td>状态</td>' + '<td>委托时间</td>'
						+ '<td>操作</td>' + '</tr>'
						+ '</thead><tbody style="width:100%">');
				if (data.code == 100) {
					var d = data.data;
					$
							.each(
									d,
									function(a, b) {
										console.log(b);
										var state = '';
										if (b.state == 0)
											state = "<span style='color: blue'>委托中</span>";
										if (b.state == 1)
											state = "<span style='color: green'>已完成</span>";
										if (b.state == 2)
											state = "撤销";
										if (b.state == 3)
											state = "全部";
										console.log(state);
										var e;
										var id = b.id;
										if (b.state != 0) {
											e = '&nbsp;&nbsp;';
										} else {
											e = '&nbsp;&nbsp;<button class="btn btn-warning" id="btn_update" onclick="updateEntrustManage('
													+ id
													+ ');">'
													+ '<i class="icon-pencil icon-white"></i>撤销</button> &nbsp;&nbsp;';
										}
										console.log(e);
										/**
										 * 
										 */
										var k;

										if (b.entrust_type == 0) {
											k = '买';
										} else {
											k = '卖';
										}
										var str = '<tr style="width:100%;">'

												+ '<td style="width:10%" align="center">'
												+ b.user_no
												+ '</td>'
												+ '<td style="width:10%" align="center">'
												+ b.entrust_coin
												+ '</td>'
												+ '<td style="width:10%" align="center">'
												+ k
												+ '</td>'
												+ '<td style="width:10%" align="center">'
												+ b.entrust_price
												+ '</td>'
												+ '<td style="width:10%" align="center">'
												+ b.entrust_num
												+ '</td>'
												+ '<td style="width:10%" align="center">'
												+ b.deal_num
												+ '</td>'
												+ '<td style="width:10%" align="center">'
												+ b.piundatge
												+ '</td>'
												+ '<td style="width:10%" align="center">'
												+ state
												+ '</td>'
												+ '<td style="width:10%" align="center">'
												+ b.date
												+ '</td>'
												+ '<td style="width:10%" align="center">'
												+ e + '</td>' + '</tr>';
										tab.html(tab.html() + str);
									});
				}

				tab.html(tab.html() + "</tbody>");
				
				
				//分页部分
				console.log(data.count);
				$('#countPage').html(data.count);
				$('#pageNum').html(1);
				$('#pageItem').html('');
				//if(data.count>=0){
				if(pageItem == 10){
					$('#pageItem').html($('#pageItem').html()+'<option value="10" selected>10</option>');	
				}else{
					$('#pageItem').html($('#pageItem').html()+'<option value="10">10</option>');
				}
			//}
			if(data.count > 10){
				if(pageItem == 20){
					$('#pageItem').html($('#pageItem').html()+'<option value="20" selected>20</option>');	
				}else{
					$('#pageItem').html($('#pageItem').html()+'<option value="20">20</option>');
				}
			}
			if(data.count > 20){
				if(pageItem == 50){
					$('#pageItem').html($('#pageItem').html()+'<option value="50" selected>50</option>');	
				}else{
					$('#pageItem').html($('#pageItem').html()+'<option value="50">50</option>');
				}
			}
			if(data.count > 50){
				if(pageItem == 100){
					$('#pageItem').html($('#pageItem').html()+'<option value="100" selected>100</option>');	
				}else{
					$('#pageItem').html($('#pageItem').html()+'<option value="100">100</option>');
				}
			}
				
			},
			data : {
				userName : "",
				state : "",
				startDate : "",
				endDate : "",
				pageItem : 10,
				pageNum : 1
			},
			error : function(e) {
				console.log(e.responseText);
			}
		});

$("#entrust_day_table")
		.bootstrapTable(
				{
					// search:true,
					pagination : true,
					pageList : [ 10, 25, 50, 100 ],
					pageSize : 10,
					// sortable: true, //是否启用排序
					// sortOrder: "asc", //排序方式
					url : '/entrust/getAllEntrustRecordByDay.do',
					search : true,// 数据源
					// strictSearch: true,
					// 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
					// 设置为limit可以获取limit, offset, search, sort, order
					/*
					 * queryParamsType : "undefined", queryParams: function
					 * queryParams(params) { // 设置查询参数 var param = { // limit:
					 * params.limit, //第几条记录 pageIndex: params.pageNumber,
					 * pageSize: params.pageSize, search:params.search }; return
					 * param; },
					 */
					// server
					sidePagination : "client", // 服务端请求
					idField : 'id',
					cache : false,
					columns : [ [// 列
							{
								title : '用户编号',
								field : 'user_no',
								width : 120,
								align : 'center'
							},
							{
								title : '委托币种',
								field : 'entrust_coin',
								width : 120,
								align : 'center'
							},
							{
								title : '委托类型',
								field : 'entrust_type',
								width : 100,
								align : 'center',
								formatter : function(value) {
									if (value == 0)
										return "买";
									if (value == 1)
										return "卖";
								}
							},
							{
								title : '委托价格',
								field : 'entrust_price',
								width : 100,
								align : 'center',
								formatter : function(value) {
									return value.toFixed(3);
								}
							},
							{
								title : '委托数量(个)',
								field : 'entrust_num',
								width : 100,
								align : 'center'
							},
							{
								title : '成交数量(个)',
								field : 'deal_num',
								width : 100,
								align : 'center'
							},
							{
								title : '手续费',
								field : 'piundatge',
								width : 100,
								align : 'center',
								formatter : function(value) {
									return value.toFixed(3);
								}
							},
							{
								title : '状态',
								field : 'state',
								width : 100,
								align : 'center',
								formatter : function(value) {
									if (value == 0)
										return "<span style='color: blue'>委托中</span>";
									if (value == 1)
										return "<span style='color: green'>已完成</span>";
									if (value == 2)
										return "撤销";
									if (value == 3)
										return "全部";
								}
							},
							{
								title : '委托时间',
								field : 'date',
								width : 100,
								align : 'center'
							},
							{
								title : '操作',
								field : 'id',
								width : 100,
								align : 'center',
								formatter : function(value, row, index) {
									var e;
									var id = value;
									if (row.state != 0)
										return e;
									e = '&nbsp;&nbsp;<button class="btn btn-warning" id="btn_update" onclick="updateEntrustManage('
											+ id
											+ ');">'
											+ '<i class="icon-pencil icon-white"></i>撤销</button> &nbsp;&nbsp;';
									return e;
								}
							} ] ],

				});

function updateEntrustManage(id) {
	if (id == null || id == '') {
		return false;
	}

	layer.confirm('确认撤销?', {
		icon : 3,
		title : '提示'
	}, function(index) {
		// do something
		var load = layer.load(2);
		var url = '/entrust/updateEntrustManage.do';
		$.post(url, {
			id : id
		}, function(e) {
			layer.close(load);
			console.log(e);
			if (e.msg == 200) {
				// success
				layer.msg("撤销成功");
				window.location.reload();
			} else {
				// fail
				layer.msg("撤销失败");
			}
		});
		layer.close(index);
	});
}