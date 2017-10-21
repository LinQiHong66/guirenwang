//$("#poundatge_table").bootstrapTable({
//    // search:true,
//    pagination:true,
//    pageList: [10, 25, 50, 100],
//    pageSize:10,
//    // sortable: true, //是否启用排序
//    // sortOrder: "asc", //排序方式
//    url:'/poundatge/queryAll.do',
//    search: true,// 数据源
//    // strictSearch: true,
//    // 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
//    // 设置为limit可以获取limit, offset, search, sort, order
//    /*
//     * queryParamsType : "undefined", queryParams: function
//     * queryParams(params) { // 设置查询参数 var param = { // limit: params.limit,
//     * //第几条记录 pageIndex: params.pageNumber, pageSize: params.pageSize,
//     * search:params.search }; return param; },
//     */
//    // server
//    sidePagination: "client", // 服务端请求
//    idField:'id',
//    cache:false,
//    columns:[[// 列
//        {title:'用户ID',field:'user_no',width:10,align:'center'},
//        {title:'用户账号',field:'user_name',width:10,align:'center'},
//        {title:'用户编号',field:'user_code',width:10,align:'center'},
//        {title:'手续费来源类型',field:'optype',width:10,align:'center',formatter:function (value) {
//            if(value == 0)return "<span style='color: blue'>买</span>";
//            if(value == 1)return "<span style='color: blue'>卖</span>";
//            if(value == 2)return "<span style='color: blue'>充值</span>";
//            if(value == 3)return "<span style='color: blue'>提现</span>";
//        }},
//        {title:'交易货币编号',field:'type',width:10,align:'center'},
//        {title:'手续费货币编号',field:'attr1',width:10,align:'center'},
//        {title:'手续费金额',field:'money',width:10,align:'center'},
//        {title:'订单金额',field:'sum_money',width:10,align:'center'},
//        {title:'时间',field:'date',width:10,align:'center'},
//    ]]
//});

$("#poundatge_table").bootstrapTable({
	// 请求方法
	method : 'get',
	// contentType : "application/x-www-form-urlencoded",
	// 是否显示行间隔色
	striped : true,
	// 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	cache : false,
	// 是否显示分页（*）
	pagination : true,
	// 是否启用排序
	sortable : false,
	// 排序方式
	// sortOrder : "asc",
	// 初始化加载第一页，默认第一页
	// 我设置了这一项，但是貌似没起作用，而且我这默认是0,- -
	// pageNumber:1,
	// 每页的记录行数（*）
	pageSize : 10,
	// 可供选择的每页的行数（*）
	pageList : [ 10, 25, 50, 100 ],
	// 这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据
	url : "/poundatge/queryAll.do",
	// 默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order
	// Else
	// queryParamsType:'',
	// //查询参数,每次调用是会带上这个参数，可自定义
	queryParams : function(params) {

		var curPageNum = params.offset / params.limit + 1;
		var perSize = params.limit;

		var userOrgCode = $('#userOrgCode').val();
		var phone = $('#phone').val();
		var username = $('#username').val();
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();

		var param = {
			curPage : curPageNum,
			pageItem : perSize,
			userOrgCode : userOrgCode,
			phone : phone,
			username : username,
			startDate : startDate,
			endDate : endDate
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
		title : '用户账号',
		field : 'attr2',
		width : 60,
		align : 'center'
	}, {
		title : '姓名',
		field : 'user_name',
		width : 60,
		align : 'center'
	}, {
		title : '用户编号',
		field : 'user_code',
		width : 60,
		align : 'center'
	}, {
		title : '创建时间',
		field : 'date',
		width : 60,
		align : 'center'
	}, {
		title : '手续费货币类型',
		field : 'attr1',
		width : 60,
		align : 'center'
	}, {
		title : '交易货币类型',
		field : 'attr3',
		width : 60,
		align : 'center'
	}, {
		title : '手续费',
		field : 'money',
		width : 60,
		align : 'center'
	}, {
		title : '交易金额',
		field : 'sum_money',
		width : 60,
		align : 'center'
	}, {
		title : '类型',
		field : 'optype',
		width : 60,
		align : 'center',
		formatter : function(value, row, index) {
			// (0:买，1:卖，2:充值，3:提现
			var e;
			switch (value) {
			case 0:
				e = "买";
				break;
			case 1:
				e = "卖"
				break;
			case 2:
				e = "充值";
				break;
			case 3:
				e = "提现";
				break;
			}
			return e;
		}
	} ] ],
	pagination : true
});
function selectPound() {
	$("#poundatge_table").bootstrapTable('refreshOptions', {
		curPage : 1
	})
}