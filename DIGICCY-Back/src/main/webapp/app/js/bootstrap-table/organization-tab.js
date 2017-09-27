$("#organization_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/userOrganization/getAllOrganization.do',
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
        {title:'用户ID',field:'user_no',width:110,align:'center'},
        {title:'用户名称',field:'attr1',width:110,align:'center'},
        {title:'申请等级',field:'org_type',width:110,align:'center'},
        {title:'审核状态',field:'state',width:110,align:'center',formatter:function (value) {
            if(value == 0)return "<span style='color: blue'>等待审核</span>";
            if(value == 1)return "<span style='color: blue'>已审核</span>";
        }},
        {title:'时间',field:'date',width:110,align:'center'},
        {title:'操作',field:'id',width:120,align:'center',
            formatter : function(value, row, index) {
            	var str = value+","+row.user_no+","+row.org_type;
                var e;
                if(row.state == 1){
                e = '-';
                }else{
                e = '<button dispaly="" class="btn btn-primary" id="btn_update" onclick="updateOrganization(\''+str+'\');">' +
                    '<i class="icon-remove icon-white"></i>&nbsp;升级</button> ';
                }
                return e;
        }},
    ]]
});

function updateOrganization(str) {
	var strArr = str.split(",");
	var id = strArr[0];
	var userNo = strArr[1];
	var org_type = strArr[2];
    $.alertable.confirm('你确定要升级吗？').then(function() {
        $.ajax({
            url:"/userOrganization/updateOrganization.do",
            type: "post",
            dataType: "json",
            data:{
            	id:id,
            	userNo:userNo,
            	org_type:org_type
            },
            success: function (msg) {
                if(msg.code == 100){
                	alert('升级成功');
                	$("#organization_table").css({"display":"none"});
                	window.location.reload();
                }else{
                	alert(msg.desc);
                	$("#organization_table").css({"display":"none"});
                	window.location.reload();
                }
            }
        });
    });
}
