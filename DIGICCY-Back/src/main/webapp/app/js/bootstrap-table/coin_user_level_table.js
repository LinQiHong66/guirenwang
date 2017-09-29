$("#coin_user_level_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/coinproportion/queryAll.do',
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
        {title:'货币编号',field:'attr1',width:10,align:'center'},
        {title:'推荐人比例',field:'level_one',width:10,align:'center'},
        {title:'推荐人一代比例',field:'level_two',width:10,align:'center'},
        {title:'推荐人二代比例',field:'level_three',width:10,align:'center'},
        {title:'推荐人三代比例',field:'level_four',width:10,align:'center'},
        {title:'推荐人四代比例',field:'level_five',width:10,align:'center'},
        {title:'分红类型（货币编号）',field:'attr2',width:10,align:'center'},
        {title:'分红状态',field:'state',width:10,align:'center',formatter:function (value) {
            if(value == 0)return "<span style='color: blue'>关闭</span>";
            if(value == 1)return "<span style='color: blue'>启用</span>";
        }},
        {title:'操作',field:'id',width:120,align:'center',
            formatter : function(value) {
                var e;
                e = '<button class="btn btn-warning" id="btn_update" ' +
                    'onclick="openContent(\''+ value + '\')">' +
                    '<i class="icon-pencil icon-white"></i>&nbsp;详细</button>&nbsp;&nbsp;' +
                    '&nbsp;&nbsp;<button class="btn btn-primary" id="btn_delete" onclick="deletes(\''+value+'\');">' +
                    '<i class="icon-remove icon-white"></i>&nbsp;删除</button> ';
                return e;
        }},
    ]]
});

function openContent(str) {
    window.location.href = '/coinproportion/gotoEdit.do?id='+str;
}

function deletes(id) {
    $.alertable.confirm('你确定要删除吗？').then(function() {
        $.ajax({
            url:"/coinproportion/delete.do",
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
