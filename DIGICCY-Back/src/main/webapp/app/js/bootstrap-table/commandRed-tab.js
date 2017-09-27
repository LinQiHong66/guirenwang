$("#commandRed_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/commandRed/getAllCommandRed.do',
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
        {title:'红包项目名称',field:'command_name',width:120,align:'center'},
        {title:'红包奖品类型',field:'command_prize_type',width:120,align:'center',formatter:function (value) {
            if(value == 0)return "<span style='color: blue'>人民币</span>";
            if(value == 1)return "<span style='color: blue'>奖品</span>";
        }},
        {title:'奖品名称/货币金额',field:'command_name_price',width:120,align:'center'},
        {title:'状态',field:'state',width:120,align:'center'},
        {title:'时间',field:'date',width:120,align:'center'},
        {title:'操作',field:'command_no',width:120,align:'center',
            formatter : function(value) {
                var e;
                e = '<button class="btn btn-info" id="btn_list" ' +
                	'onclick="openContent(\''+ value + '\')">' +
                	'<i class=""></i>&nbsp;详细</button>&nbsp;&nbsp;' +
                	'<button class="btn btn-warning" id="btn_warning" ' +
                    'onclick="editContent(\''+ value + '\')">' +
                    '<i class=""></i>&nbsp;修改</button>&nbsp;&nbsp;' +
                    '&nbsp;&nbsp;<button class="btn btn-primary" id="btn_delete" onclick="deleteNotice(\''+value+'\');">' +
                    '<i class=""></i>&nbsp;删除</button> ';
                return e;
        }},
    ]]
});

$("#commandRedInfo_table").bootstrapTable({
	
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/commandRed/getCommandRedInfo.do?command_no=' + $("#commandNo").val(),
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
        {title:'红包项目名称',field:'command_name',width:120,align:'center'},
        {title:'红包奖品类型',field:'command_prize_type',width:120,align:'center',formatter:function (value) {
            if(value == 0)return "<span style='color: blue'>人民币</span>";
            if(value == 1)return "<span style='color: blue'>奖品</span>";
        }},
        {title:'奖品名称/货币金额',field:'command_name_price',width:120,align:'center'},
        {title:'红包口令随机数',field:'command_number',width:120,align:'center'},
        {title:'状态',field:'state',width:120,align:'center'},
        {title:'时间',field:'date',width:120,align:'center'}
    ]]
});

function openContent(str) {
    window.location.href = '/commandRed/gotoCommandRedInfo.do?command_no='+str;
}

function editContent(str) {
    window.location.href = '/commandRed/gotoEdit.do?command_no='+str;
}

function deleteNotice(command_no) {
    $.alertable.confirm('你确定要删除吗？').then(function() {
        $.ajax({
            url:"/commandRed/deleteCommandRed.do",
            type: "post",
            dataType: "json",
            data:{
            	command_no:command_no
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
