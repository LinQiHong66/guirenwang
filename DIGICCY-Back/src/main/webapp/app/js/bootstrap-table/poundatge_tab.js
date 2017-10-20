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
	
});
