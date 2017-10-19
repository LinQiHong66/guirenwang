$("#coin_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/coin/getAllCoin.do',
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
        {title:'货币代码',field:'coin_core',width:10,align:'center'},
        {title:'接口地址',field:'address',width:10,align:'center'},
        {title:'钱包地址',field:'host',width:10,align:'center'},
        {title:'钱包端口',field:'post',width:10,align:'center'},
        {title:'钱包名',field:'wallet_name',width:10,align:'center'},
        {title:'钱包密码',field:'wallet_pwd',width:10,align:'center'},
        {title:'钱包锁',field:'wallet_lockpwd',width:10,align:'center'},
        {title:'状态',field:'state',width:80,align:'center',
            formatter : function(value, row, index) {
            	console.log(row);
                var e;
                var str = row.id+","+value;
                if(value == 2) {
                    e = '<button class="btn btn-info" id="btn_update" ' +
                        'onclick="changeState(\''+ str + '\')">' +
                        '<i class="icon-unlock icon-white"></i>&nbsp;启用</button>';
                }else {
                    e = '<button class="btn btn-info" id="btn_update" ' +
                        'onclick="changeState(\''+ str + '\')">' +
                        '<i class="icon-lock icon-white"></i>&nbsp;禁用</button>';
                }
                return e;
        }},
        /*{title:'投票状态',field:'vote',width:100,align:'center',
            formatter : function(value, row, index) {
                var e;
                var str = row.id+","+value;
                    e = '<button class="btn btn-info" id="btn_update" ' +
                        'onclick="startVote(\''+ str + '\')">' +
                        '<i class="icon-inbox icon-white"></i>'+(value==1?'结束':'开始')+'</button>&nbsp;&nbsp;' +
                        '&nbsp;<button class="btn btn-primary" id="btn_delete" onclick="voteCount(\''+row.id+'\');">' +
                        '<i class="icon-eye-open icon-white"></i>结果</button>&nbsp;&nbsp; ';
                return e;
            }},*/
        {title:'货币图标',field:'icon',width:100,align:'center',
            formatter : function(value) {
             
               if(value == null || value == ''){
                   return '暂无'
               }else{
                   return '<img src="'+value+'" width="50px" height="50px"/>'
                 
               }
            }},
        {title:'操作',field:'id',width:120,align:'center',
            formatter : function(value, row, index) {
                var str = value+","+row.coin_no+","+row.coin_name+","+row.coin_core+","+row.address+","+row.host+","+row.post+","+row.wallet_name+","+row.wallet_pwd+","+row.wallet_lockpwd+","+row.buy_poundatge+","+row.sell_poundatge+","+row.withdraw_poundatge_one+","+row.withdraw_poundatge_twe+","+row.withdraw_poundatge_three+","+row.block;
                var e;
                console.log('_______________'+value);
                console.log(row);
                e = '<button class="btn btn-info" id="btn_update" ' +
                    'onclick="openIconBox(\''+ row.id + '\')">' +
                    '<i class="icon-picture icon-white"></i>图标</button>&nbsp;&nbsp;'+
                    '&nbsp;&nbsp;<button class="btn btn-warning" id="btn_update" ' +
                    'onclick="openUpdateBox(\''+ str + '\')">' +
                    '<i class="icon-pencil icon-white"></i>修改</button>&nbsp;&nbsp;'/* +
                    '&nbsp;&nbsp;<button class="btn btn-primary" id="btn_delete" onclick="deleteCoin(\''+row.coin_no+'\');">' +
                    '<i class="icon-remove icon-white"></i>删除</button> &nbsp;&nbsp;';*/
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
    $("#update_no").val(strArr[1]);
    $("#update_name").val(strArr[2]);
    $("#update_code").val(strArr[3]);
    $("#update_address").val(strArr[4]);
    $("#update_host").val(strArr[5]);
    $("#update_post").val(strArr[6]);
    $("#update_wallet_name").val(strArr[7]);
    $("#update_wallet_pwd").val(strArr[8]);
    $("#update_wallet_lockpwd").val(strArr[9]);
    $("#update_buy_poundatge").val(strArr[10]);
    $("#update_sell_poundatge").val(strArr[11]);
    $("#update_sell_withdraw_poundatge_one").val(strArr[12]);
    $("#update_sell_withdraw_poundatge_twe").val(strArr[13]);
    $("#update_sell_withdraw_poundatge_three").val(strArr[14]);
    $("#update_block").val(strArr[15]);
    
    
    $("#box_update").css({"display":"block"});
}

function openIconBox(value) {
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
}
