$("#crowd_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/crowFunding/getAllCrowdFunding.do',
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
        {title:'ICO名称',field:'ico_name',width:110,align:'center'},
        {title:'目标份数',field:'ico_target',width:110,align:'center'},
        {title:'当前份数',field:'ico_current',width:110,align:'center'},
        {title:'众筹进度',field:'ico_status',width:110,align:'center'},
        {title:'众筹状态',field:'ico_state',width:110,align:'center',formatter:function (value) {
            if(value == 0)return "<span style='color: blue'>进行中</span>";
            if(value == 1)return "<span style='color: blue'>已结束</span>";
        }},
        {title:'货币类型',field:'ico_price_type',width:110,align:'center'},
        {title:'开始时间',field:'begin_date',width:110,align:'center'},
        {title:'结束时间',field:'end_date',width:110,align:'center'},
        {title:'操作',field:'ico_no',width:120,align:'center',
            formatter : function(value) {
                var e;
                e = '<button class="btn btn-warning" id="btn_update" ' +
                    'onclick="openContent(\''+ value + '\')">' +
                    '<i class="icon-pencil icon-white"></i>&nbsp;详细</button>&nbsp;&nbsp;' +
                    '&nbsp;&nbsp;<button class="btn btn-primary" id="btn_delete" onclick="deleteNotice(\''+value+'\');">' +
                    '<i class="icon-remove icon-white"></i>&nbsp;删除</button> ';
                return e;
        }},
    ]]
});

function openContent(str) {
    window.location.href = '/crowFunding/gotoEdit.do?icoNo='+str;
}

function deleteNotice(ico_no) {
    $.alertable.confirm('你确定要删除吗？').then(function() {
        $.ajax({
            url:"/crowFunding/deleteCrowdFunding.do",
            type: "post",
            dataType: "json",
            data:{
                icoNo:ico_no
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

function changeState(str){
    var strArr = str.split(",");
    var id = strArr[0];
    var state;
    if(strArr[1] == 1){
        state = 2;
    }
    if(strArr[1] == 2){
        state = 1;
    }
    $.ajax({
        url:"/coin/changeState.do",
        type: "post",
        dataType: "json",
        data:{
            id:id,
            state:state
        },
        success: function (msg) {
            // if(msg.code == "100"){
            $.alertable.alert(msg.desc);
            $("#box_add").css({"display":"none"});
            window.location.reload();
            // }
        }
    });
}

function startVote(str){
    var strArr = str.split(",");
    var id = strArr[0];
    var vote;
    if(strArr[1] == 1){
        vote = 2;
    }
    if(strArr[1] == 2){
        vote = 1;
    }
    $.ajax({
        url:"/coin/startVote.do",
        type: "post",
        dataType: "json",
        data:{
            id:id,
            vote:vote
        },
        success: function (msg) {
            // if(msg.code == "100"){
            $.alertable.alert(msg.desc);
            $("#box_add").css({"display":"none"});
            window.location.reload();
            // }
        }
    });
}
