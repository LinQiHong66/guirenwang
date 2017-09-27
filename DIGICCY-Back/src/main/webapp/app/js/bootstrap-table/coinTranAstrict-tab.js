$("#coinTranAstrict_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/coinTranAstrict/getAllCoinTranAstrict.do',
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
        {title:'货币编号',field:'coin_no',width:120,align:'center'},
        {title:'买入最低价',field:'buy_min_price',width:120,align:'center'},
        {title:'买入最高价',field:'buy_max_price',width:120,align:'center'},
        {title:'卖出最低价',field:'sell_min_price',width:120,align:'center'},
        {title:'卖出最高价',field:'sell_max_price',width:120,align:'center'},
        {title:'单笔最低价',field:'single_min_price',width:120,align:'center'},
        {title:'单笔最高价',field:'single_max_price',width:120,align:'center'},
        {title:'涨幅',field:'rose_astrict',width:120,align:'center'},
        {title:'跌幅',field:'drop_astrict',width:120,align:'center'},
        {title:'状态',field:'state',width:120,align:'center',formatter : statusFormatter},
        {title:'开始时间',field:'begin_date',width:120,align:'center'},
        {title:'结束时间',field:'end_date',width:120,align:'center'},
        {title:'操作',field:'id',width:120,align:'center',
            formatter : function(value) {
                var e;
                e = '&nbsp;&nbsp;<button class="btn btn-warning" id="btn_update" ' +
                    'onclick="openContent(\''+ value + '\')">' +
                    '<i class="icon-pencil icon-white"></i>&nbsp;详细</button>&nbsp;&nbsp;' +
                    '&nbsp;&nbsp;<button class="btn btn-primary" id="btn_delete" onclick="deleteTran(\''+value+'\');">' +
                    '<i class="icon-remove icon-white"></i>&nbsp;删除</button> ';
                return e;
        }},
    ]]
});

function statusFormatter(value,row,index) {
    if(value == 1) return '<span style="color: green" >启用</span>';
    if(value == 0) return '<span style="color: red" >禁止</span>';
}

function openContent(str) {
    window.location.href = '/coinTranAstrict/gotoEdit.do?id='+str;
}

function deleteTran(id) {
    $.alertable.confirm('你确定要删除吗？').then(function() {
        $.ajax({
            url:"/coinTranAstrict/delete.do",
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
