$("#param_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/param/getStaticParam.do',  // 数据源
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
    cardView:true,
    columns:[[// 列
        {title:'参数名称',field:'param', width:300,align:'center'},
        {title:'参数数值',field:'value',width:300,align:'center'},
        {title:'参数code', field:"code", width:300,align:'center'},
        {title:'操作',field:'id',align:'center',
            formatter : function(value, row, index) {
                var e;
                var str = value + "," + row.param + "," + row.value;
                e = '<button class="btn btn-link" id="btn_update" ' +
                    'onclick="openUpdateBox(\''+str+'\')">' +
                    '修改</button>';
                return e;
            }},
    ]],
});
//添加验证--刘科领注
function testNumber(tex){
	var el = /^[0-9]{1,}.{0,1}[0-9]{1,}$/;
	if(!el.test(tex)){
		return false;
	}
	return true;
}
$('#add_value').on('change',function(){
	if(!testNumber(this.value)){
		alert('请输入数字或小数');
		this.value="";
	}
});
$('#update_value').on('change',function(){
	if(!testNumber(this.value)){
		alert('请输入数字或小数');
	}
});
function openUpdateBox (str) {
    var strArr = str.split(",");
    $("#param_id").val(strArr[0]);
    $("#update_param").val(strArr[1]);
    $("#update_value").val(strArr[2]);
    $("#box_update").css({"display":"block"});
}
