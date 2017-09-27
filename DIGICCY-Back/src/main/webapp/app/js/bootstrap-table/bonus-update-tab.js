$("#bonus_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/bonus/getBonusDetail.do',  // 数据源
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
        {title:'分红名称',field:'bonus_name',width:120,align:'center'},
        {title:'分红货币',field:'attr1',width:80,align:'center'},
        {title:'分红总量',field:'num',width:60,align:'center'},
        {title:'日期',field:'date',width:60,align:'center'},
        {title:'操作',field:'id',width:120,align:'center',
            formatter : function(value, row, index) {
                var e;
                var str = value + "," + row.bonus_name + "," + row.coin_type + "," +row.num;
                var bonus = row.bonus_name + "," + row.coin_type + "," +row.num;
                e = '<button class="btn btn-warning" id="btn_update" ' +
                    'onclick="openUpdateBox(\''+str+'\')">' +
                    '<i class="icon-pencil icon-white"></i>&nbsp;修改</button>&nbsp;&nbsp;' +
                    '&nbsp;&nbsp;<button class="btn btn-primary" id="btn_delete" onclick="deleteBonus(\''+value+'\');">' +
                    '<i class="icon-remove icon-white"></i>&nbsp;删除</button> '+
                    '&nbsp;&nbsp;<button class="btn btn-info" id="btn_share" onclick="doBonus(\''+bonus+'\');">' +
                    '<i class="icon-money icon-white"></i>&nbsp;开始分红</button>';
                return e;
            }},
    ]],
});

function openUpdateBox (str) {
    $("#up_coin option[value='1']").attr("selected","selected");
    var strArr = str.split(",");
    $("#up_id").val(strArr[0]);
    $("#up_name").val(strArr[1]);
    $("#up_coin option").each(function (){
        if($(this).val() == strArr[2]){
            $("#up_coin option[value='1']").removeAttr("selected");
            $("#up_coin option[value='"+strArr[2]+"']").attr("selected","selected");
        }
    });
    $("#up_num").val(strArr[3]);
    $("#box_update").css({"display":"block"});
}

function doBonus(value){
    var strArr = value.split(",");
    var name = strArr[0];
    var coin = strArr[1];
    var num = strArr[2];
    $.ajax({
        url:"/bonus/doBonus.do",
        type: "post",
        dataType: "json",
        data:{
            bonusName:name,
            coinId:coin,
            num:num
        },
        success: function (msg) {
            // if(msg.code == "100"){
            $.alertable.alert(msg.desc);
            // }
        }
    });
}

function deleteBonus(id){
    $.alertable.confirm('你确定要删除吗').then(function() {
        $.ajax({
            url:"/bonus/deleteBonusDetail.do",
            type: "post",
            dataType: "json",
            data:{
                id:id
            },
            success: function (msg) {
                // if(msg.code == "100"){
                $.alertable.alert(msg.desc);
                window.location.reload();
                // }
            }
        });
    });
}