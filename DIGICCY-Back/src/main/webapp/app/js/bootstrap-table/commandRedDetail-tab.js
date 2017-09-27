$("#commandRedDetail_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/commandRedDetail/getAllCommandRedDetail.do',
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
        {title:'序号', field : 'id',width:120,align: 'center'},
        {title:'用户ID',field:'user_id',width:120,align:'center'},
        {title:'口令随机数',field:'command_number',width:120,align:'center'},
        {title:'奖品名称/货币金额',field:'command_name_price',width:120,align:'center'},
        {title : '状态', field : 'state',width:120, align: 'center', formatter : statusFormatter},
        {title:'时间',field:'date',width:120,align:'center'},
        {title : '操作', field : 'attr1',width:120, align: 'center', formatter : stateFormatter}
    ]]
});

function statusFormatter(value,row,index) {
    if(value == 0) return '<span style="color: green">进行中</span>';
    if(value == 1) return '<span style="color: red">已结束</span>';
}

function stateFormatter(value,row,index) {
    if(value == 0) return '<button style="color: green" onclick="froze('+row.id+')">拨币</button>';
    if(value == 1) return '<button style="color: red"">奖品</button>';
}

function froze(id) {
    $.ajax({
        url : "/commandRedDetail/editCommandRedInfoState.do",
        type : "POST",
        data : {
            id : id,
            state : '1'
        },
        success : function(data) {
            if (data.code == 100) {
            	alert("拨币成功");
                javascript:location.replace(location.href);
            }else{
            	alert("拨币失败");
                return false;
            }
        },
        error : function(data) {
            alert("服务器无响应");
            return false;
        }
    });
}