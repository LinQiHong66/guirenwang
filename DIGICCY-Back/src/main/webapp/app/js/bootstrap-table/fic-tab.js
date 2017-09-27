$("#withdraw_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/fic/getWithdraw.do',
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
        {title:'用户名称',field:'attr1',width:120,align:'center'},
        {title:'货币种类',field:'attr2',width:120,align:'center'},
        {title:'提现金额',field:'coin_sum',width:100,align:'center'},
        {title:'手续费',field:'poundage',width:100,align:'center'},
        {title:'实际到账',field:'actual_price',width:100,align:'center'},
        {title:'状态',field:'sate',width:100,align:'center',
            formatter : function(value,row,index) {
                var id = row.id;
                if (value == 0){
                    return '<button class="btn btn-info" ' +
                        'onclick="confirmWithdraw(\''+ id + '\')">' +
                        '<i class="icon-money icon-white"></i>&nbsp;确认到账</button>';
                }
                if (value == 1){
                   return '已到账';
                }
            }},
    ]],

});
$("#recharge_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/fic/getRecharge.do',
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
        {title:'用户名称',field:'attr1',width:120,align:'center'},
        {title:'货币种类',field:'attr2',width:120,align:'center'},
        {title:'充值金额',field:'sum_price',width:100,align:'center'},
        {title:'实际金额',field:'actual_price',width:100,align:'center'},
        {title:'赠送金额',field:'give_price',width:100,align:'center'},
        {title:'操作',field:'id',width:120,align:'center',
            formatter : function(value, row, index) {
                var e;
                if(row.state == 1){
                    e = '未到账';
                }else{
                    e = '已到账';
                }
                return e;
            }
        },
    ]],

});
//获取所有用户
$.ajax({
	url:'/user/getAllUser.do',
	type:'GET',
	dataType:'json',
	success:function(data){
		if(data.code==100){
			var obj = $('#names');
			obj.html('<option value="-1" selected>请选择</option>');
			console.log(data);
			$.each(data.data,function(a,b){
				obj.html(obj.html()+'<option value="'+b.user_no+'">'+b.user_no+'--'+b.username+'</option>');
			});
		}
	}
});
//获取货币类型
$.ajax({
	url:'/coin/getAllCoinType.do',
	type:'post',
	dataType:'json',
	success:function(data){
		if(data.code==100){
			var types = data.result;
			var obj = $('#coinTypes');
			obj.html('<option value="-1" selected>请选择</option>');
			$.each(types, function(a,b){
				obj.html(obj.html()+'<option value="'+b.coin_no+'">'+b.coin_name+'</option>');
			});
		}
	}
});
function confirm1(value) {
    $.ajax({
        url:"/bonus/doBonus.do",
        type: "post",
        dataType: "json",
        data:{
            recordId:value
        },
        success: function (msg) {
            // if(msg.code == "100"){
            $.alertable.alert(msg.desc);
            // }
        }
    });
}
function getRechargedatas(){
	var coinType = $('#coinTypes').val();
	var userNo = $('#names').val();
	var startData = $('#startData').val();
	var endData = $('#endData').val();
	//这里会调用一次接口
	$("#recharge_table").bootstrapTable('destroy'); 
	$("#recharge_table").bootstrapTable({
	    // search:true,
	    pagination:true,
	    pageList: [10, 25, 50, 100],
	    pageSize:10,
	    url:'/fic/getRecharge.do',
	    queryParams:{
	    	
	    	userName:userNo,//刷新改成了aaa
	    	coinTypeSearch:coinType,
	    	startData:startData,
	    	endData:endData
	    },
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
	    cache:true,
	    columns:[[// 列
	        {title:'用户名称',field:'attr1',width:120,align:'center'},
	        {title:'货币种类',field:'attr2',width:120,align:'center'},
	        {title:'充值金额',field:'sum_price',width:100,align:'center'},
	        {title:'实际金额',field:'actual_price',width:100,align:'center'},
	        {title:'赠送金额',field:'give_price',width:100,align:'center'},
	        {title:'操作',field:'id',width:120,align:'center',
	            formatter : function(value, row, index) {
	                var e;
	                if(row.state == 1){
	                    e = '未到账';
	                }else{
	                    e = '已到账';
	                }
	                return e;
	            }
	        },
	    ]],

	});

}
function getWithdrawdatas(){

	var coinType = $('#coinTypes').val();
	var userNo = $('#names').val();
	var startData = $('#startData').val();
	var endData = $('#endData').val();
	//这里会调用一次接口
	$("#withdraw_table").bootstrapTable('destroy'); 
	$("#withdraw_table").bootstrapTable({
	    // search:true,
	    pagination:true,
	    pageList: [10, 25, 50, 100],
	    pageSize:10,
	    // sortable: true, //是否启用排序
	    // sortOrder: "asc", //排序方式
	    url:'/fic/getWithdraw.do',
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
 queryParams:{
	    	
	    	userName:userNo,
	    	coinTypeSearch:coinType,
	    	startData:startData,
	    	endData:endData
	    },
	    // server
	    sidePagination: "client", // 服务端请求
	    idField:'id',
	    cache:false,
	    columns:[[// 列
	        {title:'用户名称',field:'attr1',width:120,align:'center'},
	        {title:'货币种类',field:'attr2',width:120,align:'center'},
	        {title:'提现金额',field:'coin_sum',width:100,align:'center'},
	        {title:'手续费',field:'poundage',width:100,align:'center'},
	        {title:'实际到账',field:'actual_price',width:100,align:'center'},
	    ]],

	});
}

function confirmWithdraw(id) {
  
    var r=confirm("是否确认到账？");
    if(r==true){
    	 
    $.ajax({
        url:"/fic/ficWithSH.do",
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
    }
}