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
        {title:'货币编号',field:'coin_no',width:10,align:'center'},
        {title:'货币名称',field:'coin_name',width:10,align:'center'},
        {title:'推荐人比例',field:'level_one',width:10,align:'center'},//level_one
        {title:'推荐人上级比例',field:'level_two',width:10,align:'center'},
        {title:'操作',field:'id',width:120,align:'center',
            formatter : function(value, row, index) {
                var str = value+","+row.coin_no+","+row.coin_name+","+row.level_one+","+row.level_two;
                var e;
                e = '&nbsp;&nbsp;<button class="btn btn-warning" id="btn_update" ' +
                    'onclick="openUpdateBox(\''+ str + '\')">' +
                    '<i class="icon-pencil icon-white"></i>修改</button>&nbsp;&nbsp;'
                 return e;
        }},
    ]]
});

function voteCount(id) {
    $.ajax({
        url:"/coin/voteCount.do",
        type: "post",
        dataType: "json",
        data:{
            id:id,
        },
        success: function (msg) {
            var support = msg.data.support;
            var opposition = msg.data.opposition;
            var total = Number(support)+Number(opposition);
            var sup = (Math.round((Number(support)/Number(total))*100)+'%');
            var opp = (Math.round((Number(opposition)/Number(total))*100)+'%');
            //不能用append不然会有多个结果，显示却只显示一个
//            $("#sup").append("<div style='width:"+sup+";' class='bar'></div>");
//            $("#opp").append("<div style='width:"+opp+";' class='bar'></div>");
            $('#sup')[0].innerHTML="<div style='width:"+sup+";' class='bar'></div>";
            $('#opp')[0].innerHTML="<div style='width:"+opp+";' class='bar'></div>";
        }
    });
    $("#box_vote").css({"display":"block"});
}

function openUpdateBox(str) {
    var strArr = str.split(",");
    $("#update_id").val(strArr[0]);
    $("#update_coin_no").val(strArr[1]);
    $("#update_coin_name").val(strArr[2]);
    $("#update_level_one").val(strArr[3]);
    $("#update_level_two").val(strArr[4]);
    $("#box_update").css({"display":"block"});
}

/*function openIconBox(value) {
    $('#id').val(value);
    $("#box_icon").css({"display":"block"});
}

function deleteCoin(no) {
    $.alertable.confirm('你确定要删除此虚拟货币吗？').then(function() {
        $.ajax({
            url:"/coin/deleteCoin.do",
            type: "post",
            dataType: "json",
            data:{
                no:no
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
}*/
