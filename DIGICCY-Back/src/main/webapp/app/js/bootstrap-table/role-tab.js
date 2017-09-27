
$("#role_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/auth/getRoles.do',  // 数据源
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
        {width:80,align:'center',checkbox:'true'},
        {title:'角色名称',field:'name',width:120,align:'center'},
        {title:'角色描述',field:'description',width:120,align:'center'},
        {title:'操作',field:'id',width:120,align:'center',
            formatter : function(value, row, index) {
                var e;
                e = '<button class="btn btn-warning" id="btn_update" ' +
                    'onclick="openUpdateBox(\''+value+","+row.name+","+row.description + '\')">' +
                '<i class="icon-pencil icon-white"></i>&nbsp;修改</button>&nbsp;&nbsp;' +
                    '&nbsp;&nbsp;<button class="btn btn-primary" id="btn_delete" onclick="deleteRole(\''+value+'\');">' +
                    '<i class="icon-remove icon-white"></i>&nbsp;删除</button> '+
                    '&nbsp;&nbsp;<button class="btn btn-info" id="btn_res" onclick="openResBox(\''+value+'\')">' +
                    '<i class="icon-folder-open icon-white"></i>&nbsp;分配权限</button> ';
                return e;
        }},
    ]],
});

function openUpdateBox(str) {
    var strArr = str.split(",");
    $("#update_id").val(strArr[0]);
    $("#update_name").val(strArr[1]);
    $("#update_desc").val(strArr[2]);
    $("#box_update").css({"display":"block"});
}

function openResBox(id) {
    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback:{
            onCheck:onCheck
        }
    };
    var zTree;
    var treeNodes;
    $.ajax({
        url:"/auth/getTreeItem.do",
        type: "post",
        dataType: "json",
        data:{
            roleId:id
        },
        success:function(data){ //请求成功后处理函数。
            //treeNodes = data.data;   //把后台封装好的简单Json格式赋给treeNodes
            zTree =  $.fn.zTree.init($("#tree"), setting, data.data);;
        }
    });

    $("#rolename").val(id);
    $("#box_res").css({"display":"block"});
}

function onCheck(e,treeId,treeNode) {
    var treeObj = $.fn.zTree.getZTreeObj("tree"),
        nodes = treeObj.getCheckedNodes(true),
        v = "";
    for (var i = 0; i < nodes.length; i++) {
        v += nodes[i].id + ",";
        $("#role_res").val(v);
    }
}

// function getTreeItem(id) {
//     var setting = {
//         isSimpleData : true,              //数据是否采用简单 Array 格式，默认false
//         treeNodeKey : "id",               //在isSimpleData格式下，当前节点id属性
//         treeNodeParentKey : "pId",        //在isSimpleData格式下，当前节点的父节点id属性
//         showLine : true,                  //是否显示节点间的连线
//         checkable : true                  //每个节点上是否显示 CheckBox
//     };
//     var zTree;
//     var treeNodes;
//     $.ajax({
//         url:"/auth/getTreeItem.do",
//         type: "post",
//         dataType: "json",
//         data:{
//             roleId:id
//         },
//         success: function (msg) {
//             console.log(msg.data)
//             treeNodes = msg.data;
//         }
//     });
//     zTree =  $.fn.zTree.init($("#tree"), setting, treeNodes);
// }

function deleteRole(id) {

    $.alertable.confirm('你确定要删除此角色吗').then(function() {
        $.ajax({
            url:"/auth/deleteRole.do",
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

