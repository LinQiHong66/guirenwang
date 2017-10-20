//$("#bonus_level_tab").bootstrapTable({
//    // search:true,
//    pagination:true,
//    pageList: [10, 25, 50, 100],
//    pageSize:10,
//    // sortable: true, //是否启用排序
//    // sortOrder: "asc", //排序方式
//    url:'/bonuslevel/queryAll.do',
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
//        {title:'来源订单',field:'bonus_source',width:10,align:'center'},
//        {title:'货币种类',field:'bonus_coin',width:10,align:'center'},
//        {title:'获取分红用户',field:'bonus_rel',width:10,align:'center'},
//        {title:'获取分红用户编号',field:'bonus_rel_code',width:10,align:'center'},
//        {title:'产生分红用户',field:'bonus_user',width:10,align:'center'},
//        {title:'产生分红用户账号',field:'bonus_user_name',width:10,align:'center'},
//        {title:'产生分红用户编号',field:'bonus_user_code',width:10,align:'center'},
//        {title:'交易类型',field:'bonus_type',width:10,align:'center',formatter:function (value) {
//            if(value == 0)return "<span style='color: blue'>买</span>";
//            if(value == 1)return "<span style='color: blue'>卖</span>";
//            if(value != 1 && value != 0)return "<span style='color: blue'>其他</span>";
//        }},
//        {title:'分红比例',field:'level_scale',width:10,align:'center'},
//        {title:'分红金额',field:'bonus',width:10,align:'center'},
//        {title:'订单总金额',field:'sum_bonus',width:10,align:'center'},
//        {title:'备注',field:'remark',width:30,align:'center'},
//    ]]
//});
$("#bonus_level_tab")
		.bootstrapTable(
				{

					// 请求方法
					method : 'get',
					// 服务器获取不到值就用这个属性
//					contentType : "application/x-www-form-urlencoded",
					// 是否显示行间隔色
					striped : true,
					// 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
					cache : false,
					// 是否显示分页（*）
					pagination : true,
					// 是否启用排序
					sortable : false,
					pageSize : 10,
					// 可供选择的每页的行数（*）
					pageList : [ 10, 25, 50, 100 ],
					// 这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据
					url : "/bonuslevel/queryAll.do",
					// 默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order
					// Else
					// queryParamsType:'',
					// //查询参数,每次调用是会带上这个参数，可自定义
					queryParams : function(params) {

						// 设置分页数据
						var curPageNum = params.offset / params.limit + 1;
						var perSize = params.limit;

						// 查询条件
						var userCode = $('#userCode').val();
						var userPhone = $('#userPhone').val();
						var userName = $('#userName').val();
						var startDate = $('#startDate').val();
						var endDate = $('#endDate').val();
						var param = {
							curPage : curPageNum,
							pageItem : perSize,
							userCode : userCode,
							userPhone : userPhone,
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
								field : 'attr1',
								width : 60,
								align : 'center'
							},
							{
								title : '姓名',
								field : 'attr2',
								width : 60,
								align : 'center'
							},
							{
								title : '用户编号',
								field : 'bonus_rel_code',
								width : 60,
								align : 'center'
							},
							{
								title : '分润金额',
								field : 'bonus',
								width : 60,
								align : 'center'
							},
							{
								title : '分润时间',
								field : 'date',
								width : 60,
								align : 'center'
							},
							{
								title : '产生分润人',
								field : 'attr3',
								width : 60,
								align : 'center'
							},
							{
								title : '产生分润人编号',
								field : 'bonus_user_code',
								width : 60,
								align : 'center'
								
							},
							{
								title : '分润比例',
								field : 'level_scale',
								width : 60,
								align : 'center'
								
							} ,
							{
								title : '货币类型',
								field : 'attr4',
								width : 60,
								align : 'center'
								
							},
							{
								title : '交易金额',
								field : 'sum_bonus',
								width : 60,
								align : 'center'
								
							},
							{
								title : '类型',
								field : 'remark',
								width : 60,
								align : 'center'
								
							}] ],
					pagination : true
				});
function selectBonus() {
	$(bonus_level_tab).bootstrapTable('refreshOptions', {
		curPage : 1
	})
}