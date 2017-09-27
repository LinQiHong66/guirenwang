/**
 * 短信设置
 */
$.ajax({
	url : '/message/getmessageset.do',
	type : 'POST',
	dataType : 'json',
	success : function(data) {
		if (data.code == 100) {
			$('#messageset_id').attr("value", data.result.id);
			$('#limit_date').attr("value", data.result.limit_date);
			$('#limit_number').attr("value", data.result.limit_number);
			$('#limit_ip').attr("value", data.result.limit_ip);
			$('#limit_name').attr("value", data.result.limit_name);
		} else {
			alert("获取失败");
		}
	}
});
// 保存短信设置
function saveMessageSet() {
	var id = $('#messageset_id').val();
	var limit_date = $('#limit_date').val();
	var limit_number = $('#limit_number').val();
	var limit_ip = $('#limit_ip').text();
	var limit_name = $('#limit_name').text();
	$.ajax({
		url : '/message/modifymessageset.do',
		type : 'POST',
		dataType : 'json',
		data : {
			id : id,
			limit_date : limit_date,
			limit_number : limit_number,
			limit_ip : limit_ip,
			limit_name : limit_name
		},
		success : function(data) {
			if (data.code == 100) {
				alert('更新成功');
			} else {
				alert('更新失败');
			}
			window.location.href = '/message/messageset.do';
		}
	});
}
// 查询短信记录
function queryMessageRecord() {
	var phonenumber = $('#phonenumber').val();
	$.ajax({
		url : '/message/querymessage.do',
		type : 'POST',
		dataType : 'json',
		data : {
			phoneNumber : phonenumber,
		},
		success : function(data) {
			if (data.code == 100) {
		
			} else {
				alert('查询失败');
			}
			window.location.href = '/message/messagequery.do';
		}
	});
}

$("#messagelog_table").bootstrapTable({
	    // search:true,
	    pagination:true,
	    pageList: [10, 25, 50, 100],
	    pageSize:10,
	    // sortable: true, //是否启用排序
	    // sortOrder: "asc", //排序方式
	    url:'/message/querymessage.do',
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
	        {title:'编号',field:'id',width:110,align:'center'},
	        {title:'接收人',field:'receive_name',width:110,align:'center'},
	        {title:'手机号',field:'phone_number',width:110,align:'center'},
	        {title:'发送内容',field:'sms_content',width:110,align:'center'},
	        {title:'发送时间',field:'update_time',width:110,align:'center'},
	    ]]
	});