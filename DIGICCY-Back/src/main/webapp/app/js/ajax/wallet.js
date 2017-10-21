
    $("#wallet_table").bootstrapTable({
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
      url: "/user/getWallet.do",
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
    	   {field: 'coinName',title: '货币类型',align: 'center'}, 
    	   {field: 'address',title: '钱包地址',align: 'center'},  
    	   {field: 'addressTag',title: '地址标签',align: 'center'},
    	   {field: 'date',title: '创建时间',align: 'center'} 
  
      ]],
      pagination:true

    });


$.ajax({
	url:'/coin/getAllCoinType.do',
	type:'get',
	dataType:'json',
	success:function(data){
		if(data.code==100){
			var types = data.result;
			var obj = $('#coinTypes');
			obj.html('<option value="-1" selected>请选择</option>');
			$.each(types, function(a,b){
				if(b.coin_no!=0){
					obj.html(obj.html()+'<option value="'+b.coin_no+'">'+b.coin_name+'</option>');
				}
				
			});
		}
	}
});

 

function getWallet() {
	var userCode = $("#userCode").val();
	var coinType = $("#coinTypes").val();
	var phone = $("#phone").val();
	var realName = $("#realName").val();
	var startData = $('#startData').val();
	var endData = $('#endData').val();
	
    $("#wallet_table").bootstrapTable("destroy");
    $("#wallet_table").bootstrapTable({
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
      url: "/user/getWallet.do",
      //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
      //queryParamsType:'',   
      ////查询参数,每次调用是会带上这个参数，可自定义                         
      queryParams: function(params) {
          return {
                pageNumber: params.offset / params.limit + 1,
                pageSize:params.limit,
                userCode : userCode,
                coinType : coinType,
                phone : phone,
                realName : realName,
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
    	   {field: 'coinName',title: '货币类型',align: 'center'}, 
    	   {field: 'address',title: '钱包地址',align: 'center'},  
    	   {field: 'addressTag',title: '地址标签',align: 'center'},
    	   {field: 'date',title: '创建时间',align: 'center'} 
  
      ]],
      pagination:true

    });
}