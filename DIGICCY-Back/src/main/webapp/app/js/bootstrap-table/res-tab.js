$("#res_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/auth/getRes.do',
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
    detailView: true,
    sidePagination: "client", // 服务端请求
    idField:'id',
    cache:false,
    columns:[[// 列
        {title:'资源名称',field:'type',width:120,align:'center'},
        {title:'资源路径',field:'value',width:120,align:'center'},
        {title:'操作',field:'id',width:120,align:'center',
            formatter : function(value, row, index) {
                var str = value+","+row.type+","+row.value+","+row.parent;
                var e;
                e = '<button class="btn btn-warning" id="btn_update" ' +
                    'onclick="openUpdateBox(\''+ str + '\')">' +
                '<i class="icon-pencil icon-white"></i>&nbsp;修改</button>&nbsp;&nbsp;' +
                    '&nbsp;&nbsp;<button class="btn btn-primary" id="btn_delete" onclick="deleteRes(\''+value+'\');">' +
                    '<i class="icon-remove icon-white"></i>&nbsp;删除</button> ';
                return e;
        }},
    ]],
    //注册加载子表的事件。注意下这里的三个参数！
    //index：父表当前行的行索引。
    //row：父表当前行的Json数据对象。
    //$detail：当前行下面创建的新行里面的td对象。
    onExpandRow: function (index, row, $detail) {
        InitSubTable(index, row, $detail);
    }
});

InitSubTable = function (index, row, $detail) {
    var parentid = row.id;
    var cur_table = $detail.html('<table></table>').find('table');
    $(cur_table).bootstrapTable({
        url: '/auth/getSubRes.do',
        queryParams: { id: parentid },
        ajaxOptions: { id: parentid },
        clickToSelect: true,
        uniqueId: "id",
        pageSize: 5,
        pageList: [5, 10],
        columns: [{
            width:120,
            field: 'type',
            title: '资源名称'
        }, {
            width:120,
            field: 'value',
            title: '资源路径'
        }, {title:'操作',field:'id',align:'center', width:120,
            formatter : function(value, row, index) {
                var str = value+","+row.type+","+row.value+","+row.parent;
                var e;
                e = '<button class="btn btn-warning" id="btn_update" ' +
                    'onclick="openUpdateBox(\''+ str + '\')">' +
                    '<i class="icon-pencil icon-white"></i>&nbsp;修改</button>&nbsp;&nbsp;' +
                    '&nbsp;&nbsp;<button class="btn btn-primary" id="btn_delete" onclick="deleteRes(\''+value+'\');">' +
                    '<i class="icon-remove icon-white"></i>&nbsp;删除</button> ';
                return e;}}
        ],
    });
};


function openUpdateBox(str) {
    var strArr = str.split(",");
    $("#update_id").val(strArr[0]);
    $("#update_type").val(strArr[1]);
    $("#update_value").val(strArr[2]);
    var parent = strArr[3];
    $("#update_menu option[value='']").attr("selected","selected");
    $("#update_menu option").each(function (){
        if($(this).val() == parent){
            $("#update_menu option[value='']").removeAttr("selected");
            $("#update_menu option[value='"+parent+"']").attr("selected","selected");
        }});
    $("#box_update").css({"display":"block"});
}

function deleteRes(id) {
    $.alertable.confirm('你确定要删除此权限(包括其下级权限)吗？').then(function() {
        $.ajax({
            url:"/auth/deleteRes.do",
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