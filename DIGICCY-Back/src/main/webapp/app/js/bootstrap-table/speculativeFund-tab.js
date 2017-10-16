$("#speculativeFunds_table")
		.bootstrapTable(
				{
					// search:true,
					pagination : true,
					pageList : [ 10, 25, 50, 100 ],
					pageSize : 10,
					// sortable: true, //是否启用排序
					// sortOrder: "asc", //排序方式
					url : '/speculativeFunds/querySpeculativeFunds.do',
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
								title : '编号',
								field : 'id',
								width : 110,
								align : 'center'
							},
							{
								title : '进入资产',
								field : 'inProperty',
								width : 110,
								align : 'center'
							},
							{
								title : '流出资产',
								field : 'outProperty',
								width : 110,
								align : 'center'
							},
							{
								title : '总资产',
								field : 'totalProperty',
								width : 110,
								align : 'center'
							},
							{
								title : '操作',
								field : 'id',
								width : 110,
								align : 'center',
								formatter : function(value) {
									var e;
									e = '<button class="btn btn-warning" id="btn_update" '
											+ 'onclick="openContent(\''
											+ value
											+ '\')">'
											+ '<i class="icon-pencil icon-white"></i>&nbsp;详细</button>';
									return e;
								}
							},

					] ]
				});

function openContent(id) {
	window.location.href = '/speculativeFunds/gotoSpeculativeFundsMotify.do?id='
			+ id;
}

function updateSpeculativeFund() {
	$.ajax({
		url : "/speculativeFunds/updateSpeculativeFunds.do",
		type : "post",
		dataType : "json",
		data : {
			id : $('#id').val(),
			addProperty : $('#addProperty').val(),
			typeProperty : $('#typeProperty').val(),
			
		},
		success : function(msg) {
			$.alertable.alert(msg.desc).then(
					function() {
						openContent($('#id').val());
					});
		}
	});
}
