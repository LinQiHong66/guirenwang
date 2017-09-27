$("#sub_core_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/subCore/getSubCore.do',  // 数据源
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
        {title:'认购名称',field:'sub_name',width:100,align:'center'},
        {title:'认购币种',field:'coin_type',width:100,align:'center'},
        {title:'价格',field:'price',width:60,align:'center'},
        {title:'数量',field:'num',width:60,align:'center'},
        {title:'已认购量',field:'already',width:60,align:'center'},
        {title:'认购限量',field:'limit_buy',width:60,align:'center'},
        {title:'解冻次数',field:'thaw_num',width:60,align:'center'},
        {title:'解冻周期',field:'cycle',width:60,align:'center'},
        {title:'日期',field:'date',width:60,align:'center'},
        {title:'货币图标',field:'photo',width:100,align:'center',
            formatter : function(value) {
                if(value == null || value == ''){
                    return '暂无'
                }else{
                    return '<img src="/coin/getImage.do?value='+value+'" width="50px" height="50px"/>'
                }
            }},
        {title:'状态',field:'status',width:60,align:'center',
            formatter : function(value) {
                var e;
                if(value == 1) {
                    e = '启用';
                }else {
                    e = '禁用';
                }
                return e;
            }},
        {title:'操作',field:'id',width:120,align:'center',
            formatter : function(value) {
                var e;
                e = '<button class="btn btn-warning" id="btn_update" ' +
                    'onclick="openUpdateBox(\''+value+'\')">' +
                    '<i class="icon-pencil icon-white"></i>&nbsp;修改</button>&nbsp;&nbsp;' +
                    '&nbsp;&nbsp;<button class="btn btn-primary" id="btn_delete" onclick="deleteSubCore(\''+value+'\');">' +
                    '<i class="icon-remove icon-white"></i>&nbsp;删除</button> '+
                    '&nbsp;&nbsp;';
                return e;
            }},
    ]],
});

function openUpdateBox (id) {
    window.location.href = '/subCore/gotoUpdate.do?id='+id;
}

function deleteSubCore (id) {
    $.alertable.confirm('你确定要删除吗').then(function() {
        $.ajax({
            url:"/subCore/deleteSubCore.do",
            type: "post",
            dataType: "json",
            data:{
                id:id
            },
            success: function (msg) {
                // if(msg.code == "100"){
                $.alertable.alert(msg.desc);
                window.location.reload();
                // }
            }
        });
    });
}