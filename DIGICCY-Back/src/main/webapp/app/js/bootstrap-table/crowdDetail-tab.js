$("#crowdDetail_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/crowFundingDetail/getAllCrowdFundingDetail.do',
    search: true,// 数据源
    // strictSearch: true,
    // 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
    // 设置为limit可以获取limit, offset, search, sort, order
    /*
     * queryParamsType : "undefined", queryParams: function
     * queryParams(params) { // 设置查询参数 var param = { // limit: params.limit,
     * //第几条记录 pageIndex: params.pageNumber, pageSize: params.pageSize,
     * search:params.search }; return param; },
     */
    // server
    sidePagination: "client", // 服务端请求
    idField:'id',
    cache:false,
    columns:[[// 列
        {title:'用户ID',field:'attr1',width:120,align:'center'},
        {title:'众筹ID',field:'ico_id',width:120,align:'center'},
        {title:'众筹贵人通数量',field:'ico_user_number',width:120,align:'center'},
        {title:'众筹茅台酒数量',field:'attr2',width:120,align:'center'},
        {title:'众筹总额',field:'ico_user_sumprice',width:120,align:'center'},
        {title:'时间',field:'date',width:120,align:'center'},
    ]]
});

