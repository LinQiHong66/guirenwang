$("#userlevel_tab").bootstrapTable({
	
	 pagination:true,
	 pageList: [10, 25, 50, 100],
	 pageSize:10,
	 url:'/userlevel/queryByStatus.do',
	 search: true,
	 sidePagination: "client", // 服务端请求
	    idField:'id',
	    cache:false,
	    columns:[[// 列
	              {title:'用户编号',field:'user_id',width:80,align:'center'},
	              {title:'接口作用',field:'address_code',width:60,align:'center'},
	              
	    ]]
})
