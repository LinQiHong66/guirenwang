$("#aaa").click(function(){
    $.ajax({
        url:"/crowFundingDetail/getAllCrowdFundingDetail_jigou.do",
        type: "post",
        dataType: "json",
        success: function (msg) {
            $("#crowdDetail_table_jigou").bootstrapTable({
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
                    {title:'用户ID',field:'attr2',width:60,align:'center'},
                    {title:'众筹贵人通数',field:'ico_user_number',width:60,align:'center'},
                    {title:'众筹茅台酒数',field:'attr1',width:60,align:'center'},
                    {title:'日期',field:'date',width:60,align:'center'},
                ]],
            });
        }
    });
});
$("#bbb").click(function(){
    $.ajax({
    	url:"/crowFundingDetail/getAllCrowdFundingDetail_user.do",
        type: "post",
        dataType: "json",
        success: function (msg) {
            $("#crowdDetail_table_user").bootstrapTable({
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
                    {title:'用户ID',field:'attr2',width:60,align:'center'},
                    {title:'众筹贵人通数',field:'ico_user_number',width:60,align:'center'},
                    {title:'众筹茅台酒数',field:'attr1',width:60,align:'center'},
                    {title:'日期',field:'date',width:60,align:'center'},
                ]],
            });
        }
    });
});
$("#ccc").click(function(){
	var orgCode = $("#orgCode").val();
    $.ajax({
    	url:"/crowFundingDetail/getAllCrowdFundingDetail_jigou_detail.do",
        type: "post",
        dataType: "json",
        data: {
        	orgCode : orgCode
        },
        success: function (msg) {
            $("#crowdDetail_table_jigou_detail").bootstrapTable({
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
                    {title:'用户ID',field:'user_id',width:60,align:'center'},
                    {title:'众筹贵人通数',field:'ico_user_number',width:60,align:'center'},
                    {title:'众筹茅台酒数',field:'attr2',width:60,align:'center'},
                    {title:'日期',field:'date',width:60,align:'center'},
                ]],
            });
        }
    });
});

