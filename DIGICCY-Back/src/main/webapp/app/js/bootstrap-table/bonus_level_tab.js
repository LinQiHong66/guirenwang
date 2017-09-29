$("#bonus_level_tab").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/bonuslevel/queryAll.do',
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
        {title:'来源订单',field:'bonus_source',width:10,align:'center'},
        {title:'货币种类',field:'bonus_coin',width:10,align:'center'},
        {title:'获取分红用户',field:'bonus_rel',width:10,align:'center'},
        {title:'产生分红用户',field:'bonus_user',width:10,align:'center'},
        {title:'交易类型',field:'bonus_type',width:10,align:'center',formatter:function (value) {
            if(value == 0)return "<span style='color: blue'>买</span>";
            if(value == 1)return "<span style='color: blue'>卖</span>";
            if(value != 1 && value != 0)return "<span style='color: blue'>其他</span>";
        }},
        {title:'分红金额',field:'bonus',width:10,align:'center'},
        {title:'备注',field:'remark',width:10,align:'center'},
    ]]
});
