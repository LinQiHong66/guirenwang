$("#sub_core_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/assess/getAssessCount.do',  // 数据源
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
        {title:'币种名称',field:'coin',width:100,align:'center'},
        {title:'评价等级',field:'assess',width:60,align:'center'},
        {title:'技术评分',field:'skill_assess',width:60,align:'center'},
        {title:'应用评分',field:'apply_assess',width:60,align:'center'},
        {title:'前景评分',field:'vista_assess',width:60,align:'center'},
    ]],
});

$("#assess_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/assess/getAssess.do',  // 数据源
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
        {title:'用户名称',field:'attr2',width:80,align:'center'},
        {title:'货币名称',field:'attr1',width:80,align:'center'},
        {title:'评分星级',field:'assess_level',width:60,align:'center'},
        {title:'技术评分',field:'skill_assess',width:60,align:'center'},
        {title:'应用评分',field:'apply_assess',width:60,align:'center'},
        {title:'前景评分',field:'vista_assess',width:60,align:'center'},
        {title:'应用评分',field:'apply_assess',width:60,align:'center'},
        {title:'评论',field:'assess',width:150,align:'center'},
        {title:'日期',field:'date',width:100,align:'center'},
        {title:'操作',field:'id',width:120,align:'center',
            formatter : function(value) {
            	//删除暂时不做--刘科领注
                var e;
//                e = '<button class="btn btn-primary" id="btn_delete" onclick="deleteAssess(\''+value+'\');">' +
//                    '<i class="icon-remove icon-white"></i>&nbsp;删除</button> ';
                e = '<button class="btn btn-primary" id="btn_delete" onclick="">' +
                '<i class="icon-remove icon-white"></i>&nbsp;删除</button> ';
                return e;
            }
        },
    ]],
});

//function deleteAssess(id){
//    $.alertable.confirm('你确定要删除吗？').then(function() {
//        $.ajax({
//            url:"/coin/deleteCoin.do",
//            type: "post",
//            dataType: "json",
//            data:{
//                id:id
//            },
//            success: function (msg) {
//                // if(msg.code == "100"){
//                $.alertable.alert(msg.desc);
//                $("#box_add").css({"display":"none"});
//                window.location.reload();
//                // }
//            }
//        });
//    });
//}