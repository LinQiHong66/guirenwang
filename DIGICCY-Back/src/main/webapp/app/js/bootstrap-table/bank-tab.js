//$("#bank_table").bootstrapTable({
//    // search:true,
//    pagination:true,
//    pageList: [10, 25, 50, 100],
//    pageSize:10,
//    // sortable: true, //是否启用排序
//    // sortOrder: "asc", //排序方式
//    url:'/user/getBankInfo.do',  // 数据源
//    search: true,
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
//        {title:'用户名称',field:'atte2',width:100,align:'center'},
//        {title:'开户银行',field:'bank',width:100,align:'center'},
//        {title:'开户省份',field:'province',width:60,align:'center'},
//        {title:'开户城市',field:'city',width:60,align:'center'},
//        {title:'开户支行',field:'branch',width:60,align:'center'},
//        {title:'开户姓名',field:'name',width:60,align:'center'},
//        {title:'开户卡号',field:'name',width:100,align:'center'},
//        {title:'开户姓名',field:'bank_num',width:60,align:'center'},
//        {title:'添加时间',field:'date',width:80,align:'center'},
//    ]],
//});

$('#bank_table').bootstrapTable({
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
	url : "/user/getBankInfo.do",
	// 默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order
	// Else
	// queryParamsType:'',
	// //查询参数,每次调用是会带上这个参数，可自定义
	queryParams : function(params) {

		var curPageNum = params.offset / params.limit + 1;
		var perSize = params.limit;

		var userOrgCode = $('#userOrgCode').val();
		var bankName = $('#bankName').val();
		var bankUserName = $('#bankUserName').val();
		var phone = $('#phone').val();
		var userName = $('#userName').val();
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();

		var param = {
			curPage : curPageNum,
			pageItem : perSize,
			userOrgCode : userOrgCode,
			bankName : bankName,
			bankUserName : bankUserName,
			phone : phone,
			userName : userName,
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
		field : 'remark_name',
		width : 60,
		align : 'center'
	}, {
		title : '姓名',
		field : 'atte2',
		width : 60,
		align : 'center'
	}, {
		title : '用户编号',
		field : 'atte1',
		width : 60,
		align : 'center'
	}, {
		title : '开户银行',
		field : 'bank',
		width : 60,
		align : 'center'
	}, {
		title : '开户省市',
		field : 'city',
		width : 60,
		align : 'center'
	}, {
		title : '开户支行',
		field : 'branch',
		width : 60,
		align : 'center'
	}, {
		title : '开户姓名',
		field : 'name',
		width : 60,
		align : 'center'
	}, {
		title : '开户卡号',
		field : 'bank_num',
		width : 60,
		align : 'center'
	}, {
		title : '添加时间',
		field : 'date',
		width : 60,
		align : 'center',

	} ] ],
	pagination : true
});

function selectDate() {
	$('#bank_table').bootstrapTable('refreshOptions', {
		curPage : 1
	})
}

function deleteAssess(id) {
	$.alertable.confirm('你确定要删除吗？').then(function() {
		$.ajax({
			url : "/coin/deleteCoin.do",
			type : "post",
			dataType : "json",
			data : {
				id : id
			},
			success : function(msg) {
				// if(msg.code == "100"){
				$.alertable.alert(msg.desc);
				$("#box_add").css({
					"display" : "none"
				});
				window.location.reload();
				// }
			}
		});
	});
}