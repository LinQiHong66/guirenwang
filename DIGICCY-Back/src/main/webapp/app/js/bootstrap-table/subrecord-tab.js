$("#sub_record_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/subCore/getSubRecord.do',  // 数据源
    search: true,
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
        {title:'用户名称',field:'username',width:100,align:'center'},
        {title:'认购名称',field:'sub_name',width:100,align:'center'},
        {title:'认购币种',field:'coin_no',width:60,align:'center'},
        {title:'价格',field:'sub_price',width:60,align:'center'},
        {title:'数量',field:'sub_num',width:60,align:'center'},
        {title:'总价',field:'sum_price',width:60,align:'center'},
        {title:'解冻次数',field:'thaw_num',width:60,align:'center'},
        {title:'解冻时间',field:'thaw_time',width:60,align:'center'},
        {title:'剩余冻结',field:'sur_frozen',width:60,align:'center'},
        {title:'解冻数量',field:'thaw_sum',width:60,align:'center'},
        {title:'状态',field:'state',width:60,align:'center',
            formatter : function(value) {
                var e;
                if(value == 0) {
                    e = '未解冻';
                }else if(value == 1){
                    e = '解冻中';
                }else{
                    e = '已解冻';
                }
                return e;
            }},
    ]],
});
