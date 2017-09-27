  $("#user_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/auth/getUser.do',
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
        {width:80,align:'center',checkbox:'true'},
        {title:'用户账号',field:'name',width:120,align:'center'},
        {title:'用户角色',field:'disabled',width:120,align:'center'},
        {title:'操作',field:'id',width:120,align:'center',
            formatter : function(value, row, index) {
                var str = value+","+row.name+","+row.password+","+row.roleId;
                var e;
                e = '<button class="btn btn-warning" id="btn_update" ' +
                    'onclick="openUpdateBox(\''+ str + '\')">' +
                    '<i class="icon-pencil icon-white"></i>&nbsp;修改</button>&nbsp;&nbsp;' +
                    '&nbsp;&nbsp;<button class="btn btn-primary" id="btn_delete" onclick="deleteUser(\''+value+'\');">' +
                    '<i class="icon-remove icon-white"></i>&nbsp;删除</button> ';
                return e;
            }},
    ]],

});

function openUpdateBox(str) {
    var strArr = str.split(",");
    $("#update_id").val(strArr[0]);
    $("#update_name").val(strArr[1]);
    $("#update_pass").val(strArr[2]);
    var role = strArr[3];
    $("#update_role option[index=0]").attr("selected","selected");
    $("#update_role option").each(function (){
        if($(this).val() == role){
            $("#update_role option[index=0]").removeAttr("selected");
            $("#update_role option[value='"+role+"']").attr("selected","selected");
        }});
    $("#box_update").css({"display":"block"});
}

function deleteUser(id) {
    $.alertable.confirm('你确定要删除此用户吗？').then(function() {
        $.ajax({
            url:"/auth/deleteUser.do",
            type: "post",
            dataType: "json",
            data:{
                userId:id
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