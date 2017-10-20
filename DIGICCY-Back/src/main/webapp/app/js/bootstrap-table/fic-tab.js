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
 


 $('#recharge_table').bootstrapTable({
    //请求方法
     method: 'get',
  /* contentType : "application/x-www-form-urlencoded",*/
    //是否显示行间隔色
   striped: true,
   //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）     
   cache: false,    
   //是否显示分页（*）  
   pagination: true,   
    //是否启用排序  
   sortable: false,    
    //排序方式 
   sortOrder: "asc",    
   //初始化加载第一页，默认第一页
   //我设置了这一项，但是貌似没起作用，而且我这默认是0,- -
   //pageNumber:1,   
   //每页的记录行数（*）   
   pageSize: 10,  
   //可供选择的每页的行数（*）    
   pageList: [10, 25, 50, 100],
   //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据  
   url: "/fic/getRecharge.do",
   //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
   //queryParamsType:'',   
   ////查询参数,每次调用是会带上这个参数，可自定义                         
   queryParams: function(params) {
       return {
             pageNumber: params.offset / params.limit + 1,
             pageSize:params.limit
             
           };
   },
   //分页方式：client客户端分页，server服务端分页（*）
   sidePagination: "server",
   //是否显示搜索
   search: false,  
   //Enable the strict search.    
   strictSearch: true,
   //Indicate which field is an identity field.
   idField : "id",
   columns:[[// 列
	   {field: 'id',title: 'id',align: 'center'},
	   {field: 'userName',title: '用户账号',align: 'center'},
	   {field: 'realName',title: '真实姓名',align: 'center'},
	   {field: 'userCode',title: '用户编号',align: 'center'},
	   {field: 'coinName',title: '转入币种',align: 'center'},
	   {field: 'addressFrom',title: '转入地址',align: 'center'},
	   {field: 'number',title: '转入数量',align: 'center'},
	   {field: 'realNumber',title: '到账数量',align: 'center'},
	   {field: 'date',title: '转入时间',align: 'center'},
	   {field: 'state',title: '转入状态',align: 'center',formatter:function(value){
           if(value == 1) return "已到账";
           if(value == 0) return "未到账";
       }}
   ]],
   pagination:true
});
 

 
//获取货币类型
/*$.ajax({
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
});*/
/*function confirm1(value) {
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
}*/
 function getRechargedatas(){
	var userCode = $('#userCode').val();
	var phone = $('#phone').val();
	var realName = $('#realName').val();
	var state = $('#state').val();
	var coinType = $('#coinTypes').val();
	var startData = $('#startData').val();
	var endData = $('#endData').val();
	//这里会调用一次接口
	$("#recharge_table").bootstrapTable('destroy'); 
	$('#recharge_table').bootstrapTable({
	    //请求方法
	     method: 'get',
	   contentType : "application/x-www-form-urlencoded",
	    //是否显示行间隔色
	   striped: true,
	   //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）     
	   cache: false,    
	   //是否显示分页（*）  
	   pagination: true,   
	    //是否启用排序  
	   sortable: false,    
	    //排序方式 
	   sortOrder: "asc",    
	   //初始化加载第一页，默认第一页
	   //我设置了这一项，但是貌似没起作用，而且我这默认是0,- -
	   //pageNumber:1,   
	   //每页的记录行数（*）   
	   pageSize: 10,  
	   //可供选择的每页的行数（*）    
	   pageList: [10, 25, 50, 100],
	   //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据  
	   url: "/fic/getRecharge.do",
	   //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
	   //queryParamsType:'',   
	   ////查询参数,每次调用是会带上这个参数，可自定义                         
	   queryParams: function(params) {
	       return {
	             pageNumber: params.offset / params.limit + 1,
	             pageSize:params.limit,
	             userCode:userCode,
	             phone : phone,
	             realName : realName,
	             state : state,
	             coinType : coinType,
	             startData : startData,
	             endData : endData
	 
	           };
	   },
	   //分页方式：client客户端分页，server服务端分页（*）
	   sidePagination: "server",
	   //是否显示搜索
	   search: false,  
	   //Enable the strict search.    
	   strictSearch: true,
	   //Indicate which field is an identity field.
	   idField : "id",
	   columns:[[// 列
		   {field: 'id',title: 'id',align: 'center'},
		   {field: 'userName',title: '用户账号',align: 'center'},
		   {field: 'realName',title: '真实姓名',align: 'center'},
		   {field: 'userCode',title: '用户编号',align: 'center'},
		   {field: 'coinName',title: '转入币种',align: 'center'},
		   {field: 'addressFrom',title: '转入地址',align: 'center'},
		   {field: 'number',title: '转入数量',align: 'center'},
		   {field: 'realNumber',title: '到账数量',align: 'center'},
		   {field: 'date',title: '转入时间',align: 'center'},
		   {field: 'state',title: '转入状态',align: 'center',formatter:function(value){
	           if(value == 1) return "已到账";
	           if(value == 0) return "未到账";
	       }}
	   ]],
	   pagination:true
	});

}
 

/*function getWithdrawdatas(){

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
	    
	     * queryParamsType : "undefined", queryParams: function
	     * queryParams(params) { // 设置查询参数 var param = { // limit: params.limit,
	     * //第几条记录 pageIndex: params.pageNumber, pageSize: params.pageSize,
	     * search:params.search }; return param; },
	     
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
}*/