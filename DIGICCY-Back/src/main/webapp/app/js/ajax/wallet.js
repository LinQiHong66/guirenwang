/**
 * Created by Administrator on 2016/12/1 0001.
 */
$(function () {
    $('#coin').hide();
    $('#name').show();
    $.ajax({
        url:"/coin/getAllCoin.do",
        type: "get",
        dataType: "json",
        success: function (msg) {
            var coin = $("#coin");
            var dataJson = JSON.stringify(msg.data);
            var data = JSON.parse(dataJson);
            for(var i=0;i<data.length;i++){
                coin.append("<option value='"+data[i].coin_no+"'>"+data[i].coin_name+"</option>");
            }
        }
    });
    getWallet();
})

function showInput() {
    var query = $('#query').val();
    if(query == "user_no"){
        $('#coin').hide();
        $('#name').show();
    }
    if(query == "coin_type"){
        $('#name').hide();
        $('#coin').show();
    }
}

function getWallet() {

    $("#wallet_table").bootstrapTable("destroy");
    var condition = $('#query').val();
    var values;
    var name = $('#name').val();
    var coin = $('#coin').val();
    //--刘科领注
//    if(coin == "0"){
//        values = name;
//    }
//    if(coin != "0"){
//        values = coin;
//    }
    if(condition == "user_no"){
    	values = name;
    }
    if(condition == "coin_type"){
    	values = coin;
    	if(coin == 0){
    		//如果没选择就让他全查询
    		values = "";
    	}
    }
    $.ajax({
        url:"/user/getWallet.do",
        type: "post",
        dataType: "json",
        data:{
            condition:condition,
            value:values
        },
        success: function (msg) {
        	msg.data.wallet_address=null;
        	//将所有的空字符串变成空，不然界面显示不一致--刘科领注
        	$.each(msg.data,function(a,b){
            	b.wallet_address=b.wallet_address==""?null:b.wallet_address;
        	});
            $("#wallet_table").bootstrapTable({
                // search:true,
                pagination:true,
                pageList: [10, 25, 50, 100],
                pageSize:10,
                // sortable: true, //是否启用排序
                // sortOrder: "asc", //排序方式
                // url:'/assess/getAssess.do',
                data:msg.data,
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
                    {title:'用户名称',field:'attr1',width:80,align:'center'},
                    {title:'货币类型',field:'attr2',width:80,align:'center'},
                    {title:'可用数量',field:'enable_coin',width:60,align:'center'},
                    {title:'冻结数量',field:'unable_coin',width:60,align:'center'},
                    {title:'总量',field:'total_price',width:60,align:'center'},
                    {title:'钱包地址',field:'wallet_address',width:120,align:'center'},
                ]],
            });
        }
    });
}