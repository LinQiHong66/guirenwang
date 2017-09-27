$("#helpCenter_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/helpCenter/getAllHelpCenter.do',
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
        {title:'帮助中心名称',field:'help_name',width:120,align:'center'},
        {title:'帮助中心等级',field:'help_grade',width:120,align:'center'},
        {title:'帮助中心父类',field:'help_parent',width:120,align:'center'},
        {title:'操作',field:'id',width:120,align:'center',
            formatter : function(value) {
                var e;
                e = '<button class="btn btn-warning" id="btn_update" ' +
                'onclick="openContent(\''+ value + '\')">' +
                '<i class="icon-pencil icon-white"></i>&nbsp;详细</button>&nbsp;&nbsp;' +
                '&nbsp;&nbsp;<button class="btn btn-primary" id="btn_delete" onclick="deleteHelp(\''+value+'\');">' +
                '<i class="icon-remove icon-white"></i>&nbsp;删除</button> ';
                return e;
        }},
    ]]
});

function openContent(str) {
    window.location.href = '/helpCenter/gotoEdit.do?id='+str;
}

function deleteHelp(id) {
    $.alertable.confirm('你确定要删除吗？').then(function() {
        $.ajax({
            url:"/helpCenter/deleteHelpCenter.do",
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
