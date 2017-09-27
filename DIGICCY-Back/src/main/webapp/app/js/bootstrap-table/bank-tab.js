$("#bank_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/user/getBankInfo.do',  // 数据源
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
        {title:'用户名称',field:'atte2',width:100,align:'center'},
        {title:'开户银行',field:'bank',width:100,align:'center'},
        {title:'开户省份',field:'province',width:60,align:'center'},
        {title:'开户城市',field:'city',width:60,align:'center'},
        {title:'开户支行',field:'branch',width:60,align:'center'},
        {title:'开户姓名',field:'name',width:60,align:'center'},
        {title:'开户卡号',field:'name',width:100,align:'center'},
        {title:'开户姓名',field:'bank_num',width:60,align:'center'},
        {title:'添加时间',field:'date',width:80,align:'center'},
    ]],
});


function deleteAssess(id){
    $.alertable.confirm('你确定要删除吗？').then(function() {
        $.ajax({
            url:"/coin/deleteCoin.do",
            type: "post",
            dataType: "json",
            data:{
                id:id
            },
            success: function (msg) {
                // if(msg.code == "100"){
                $.alertable.alert(msg.desc);
                $("#box_add").css({"display":"none"});
                window.location.reload();
                // }
            }
        });
    });
}