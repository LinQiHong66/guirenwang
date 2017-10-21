$("#crowdDetail_table").bootstrapTable({
	// 请求方法
	method : 'get',
	// contentType : "application/x-www-form-urlencoded",
	// 是否显示行间隔色
	striped : true,
	// 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	cache : false,
	// 是否显示分页（*）
	pagination : true,
	// 是否启用排序
	sortable : false,
	// 排序方式
	// sortOrder : "asc",
	// 初始化加载第一页，默认第一页
	// 我设置了这一项，但是貌似没起作用，而且我这默认是0,- -
	// pageNumber:1,
	// 每页的记录行数（*）
	pageSize : 10,
	// 可供选择的每页的行数（*）
	pageList : [ 10, 25, 50, 100 ],
	// 这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据
	url : "/crowFundingDetail/getAllCrowdFundingDetail.do",
	// 默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order
	// Else
	// queryParamsType:'',
	// //查询参数,每次调用是会带上这个参数，可自定义
	queryParams : function(params) {

		var curPageNum = params.offset / params.limit + 1;
		var perSize = params.limit;

		var userOrgCode = $('#userOrgCode').val();
		var phone = $('#phone').val();
		var userName = $('#userName').val();
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		var icoId = $('#icoId').val();

		var param = {
			curPage : curPageNum,
			pageItem : perSize,
			userOrgCode : userOrgCode,
			phone : phone,
			userName : userName,
			startDate : startDate,
			endDate : endDate,
			icoId : icoId
		};
		return param;
	},
	// 分页方式：client客户端分页，server服务端分页（*）
	sidePagination : "server",
	// 是否显示搜索
	search : false,
	// Enable the strict search.
	strictSearch : true,
	// Indicate which field is an identity field.
	idField : "id",
	columns : [ [// 列
	{
		title : '用户账号',
		field : 'attr1',
		width : 60,
		align : 'center'
	}, {
		title : '姓名',
		field : 'attr2',
		width : 60,
		align : 'center'
	}, {
		title : '用户编号',
		field : 'attr3',
		width : 60,
		align : 'center'
	}, {
		title : '项目名称',
		field : 'attr4',
		width : 60,
		align : 'center'
	}, {
		title : '贵人通数量',
		field : 'attr5',
		width : 60,
		align : 'center'
	}, {
		title : '购酒数量',
		field : 'ico_user_number',
		width : 60,
		align : 'center'
	}, {
		title : '购酒总金额',
		field : 'ico_user_sumprice',
		width : 60,
		align : 'center'
	}, {
		title : '购酒时间',
		field : 'date',
		width : 60,
		align : 'center'
	} ] ],
	pagination : true
});

$.ajax({
	url : '/crowFundingDetail/getAllCrowd.do',
	type : 'post',
	dateType : 'json',
	success : function(date) {
		var icoId = $('#icoId');
		icoId.html("<option value=''>请选择</option>");
		$.each(date.data, function(a, b) {
			icoId.html(icoId.html() + "<option value='" + b.ico_no + "'>"
					+ b.ico_name + "</option>");
		});
	}
});

function AddFunctionAlty(value, row, index) {
	return [ '<a onclick="read(this)"><button class="btn btn-success" id="btn_add" data-toggle="modal" style="margin-bottom: 8px;" data-target="#myModal"><i class="icon-plus icon-white"></i>&nbsp;更新单号</button></a>' ]
			.join("");
}

/**
 * 更新数据
 */

var opt = {
	// search:true,
	pagination : true,
	pageList : [ 10, 25, 50, 100 ],
	pageSize : 10,
	// sortable: true, //是否启用排序
	// sortOrder: "asc", //排序方式
	url : '/crowFundingDetail/getAllCrowdFundingDetail.do',
	search : true,// 数据源
	// strictSearch: true,
	// 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
	// 设置为limit可以获取limit, offset, search, sort, order
	/*
	 * queryParamsType : "undefined", queryParams: function queryParams(params) { //
	 * 设置查询参数 var param = { // limit: params.limit, //第几条记录 pageIndex:
	 * params.pageNumber, pageSize: params.pageSize, search:params.search };
	 * return param; },
	 */
	// server
	sidePagination : "client", // 服务端请求
	idField : 'id',
	cache : false,
	columns : [ [// 列
	{
		title : 'ID',
		field : 'id',
		width : 20,
		align : 'center'
	}, {
		title : '用户ID',
		field : 'attr1',
		width : 120,
		align : 'center'
	}, {
		title : '众筹ID',
		field : 'ico_id',
		width : 120,
		align : 'center'
	}, {
		title : '众筹贵人通数量',
		field : 'ico_user_number',
		width : 120,
		align : 'center'
	}, {
		title : '众筹茅台酒数量',
		field : 'attr2',
		width : 120,
		align : 'center'
	}, {
		title : '众筹总额',
		field : 'ico_user_sumprice',
		width : 120,
		align : 'center'
	}, {
		title : '时间',
		field : 'date',
		width : 120,
		align : 'center'
	}, {
		title : '物流公司',
		field : 'logistics_company',
		width : 120,
		align : 'center'
	}, {
		title : '物流单号',
		field : 'logistics_number',
		width : 120,
		align : 'center'
	}, {
		title : '物流状态',
		field : 'logistics_status',
		width : 120,
		align : 'center'
	}, ] ]
}
// $("#crowdDetail_table")
// 更新数据
function updateLogistics() {
	$("#crowdDetail_table").bootstrapTable('refreshOptions', {
		curPage : 1
	})
}

// 下拉框
$(function() {

	$("#selectId").append("<option value='AJ'>安捷快递</option>");
	$("#selectId").append("<option value='ANE'>安能物流</option>");
	$("#selectId").append("<option value='AXD'>安信达快递</option>");
	$("#selectId").append("<option value='BQXHM'>北青小红帽</option>");
	$("#selectId").append("<option value='BFDF'>百福东方</option>");
	$("#selectId").append("<option value='BTWL'>百世快运</option>");
	$("#selectId").append("<option value='CCES'>CCES快递</option>");
	$("#selectId").append("<option value='CITY100'>城市100</option>");
	$("#selectId").append("<option value='COE'>COE东方快递</option>");
	$("#selectId").append("<option value='CSCY'>长沙创一</option>");
	$("#selectId").append("<option value='CDSTKY'>成都善途速运</option>");
	$("#selectId").append("<option value='DBL'>德邦</option>");
	$("#selectId").append("<option value='DSWL'>D速物流</option>");
	$("#selectId").append("<option value='DTWL'>大田物流</option>");
	$("#selectId").append("<option value='EMS'>EMS</option>");
	$("#selectId").append("<option value='FAST'>快捷速递</option>");
	$("#selectId").append("<option value='FEDEX'>FEDEX联邦(国内件）</option>");
	$("#selectId").append("<option value='FEDEX_GJ'>FEDEX联邦(国际件）</option>");
	$("#selectId").append("<option value='FKD'>飞康达</option>");
	$("#selectId").append("<option value='GDEMS'>广东邮政</option>");
	$("#selectId").append("<option value='GSD'>共速达</option>");
	$("#selectId").append("<option value='GTO'>国通快递</option>");
	$("#selectId").append("<option value='GTSD'>高铁速递</option>");
	$("#selectId").append("<option value='HFWL'>汇丰物流</option>");
	$("#selectId").append("<option value='HHTT'>天天快递</option>");
	$("#selectId").append("<option value='HLWL'>恒路物流</option>");
	$("#selectId").append("<option value='HOAU'>天地华宇</option>");
	$("#selectId").append("<option value='hq568'>华强物流</option>");
	$("#selectId").append("<option value='HTKY'>百世快递</option>");
	$("#selectId").append("<option value='HXLWL'>华夏龙物流</option>");
	$("#selectId").append("<option value='HYLSD'>好来运快递</option>");
	$("#selectId").append("<option value='JGSD'>京广速递</option>");
	$("#selectId").append("<option value='JIUYE'>九曳供应链</option>");
	$("#selectId").append("<option value='JJKY'>佳吉快运</option>");
	$("#selectId").append("<option value='JLDT'>嘉里物流</option>");
	$("#selectId").append("<option value='JTKD'>捷特快递</option>");
	$("#selectId").append("<option value='JXD'>急先达</option>");
	$("#selectId").append("<option value='JYKD'>晋越快递</option>");
	$("#selectId").append("<option value='JYM'>加运美</option>");
	$("#selectId").append("<option value='JYWL'>佳怡物流</option>");
	$("#selectId").append("<option value='KYWL'>跨越物流</option>");
	$("#selectId").append("<option value='LB'>龙邦快递</option>");
	$("#selectId").append("<option value='LHT'>联昊通速递</option>");
	$("#selectId").append("<option value='MHKD'>民航快递</option>");
	$("#selectId").append("<option value='MLWL'>明亮物流</option>");
	$("#selectId").append("<option value='NEDA'>能达速递</option>");
	$("#selectId").append("<option value='PADTF'>平安达腾飞快递</option>");
	$("#selectId").append("<option value='QCKD'>全晨快递</option>");
	$("#selectId").append("<option value='QFKD'>全峰快递</option>");
	$("#selectId").append("<option value='QRT'>全日通快递</option>");
	$("#selectId").append("<option value='RFD'>如风达</option>");
	$("#selectId").append("<option value='SAD'>赛澳递</option>");
	$("#selectId").append("<option value='SAWL'>圣安物流</option>");
	$("#selectId").append("<option value='SBWL'>盛邦物流</option>");
	$("#selectId").append("<option value='SDWL'>上大物流</option>");
	$("#selectId").append("<option value='SF'>顺丰快递</option>");
	$("#selectId").append("<option value='SFWL'>盛丰物流</option>");
	$("#selectId").append("<option value='SHWL'>盛辉物流</option>");
	$("#selectId").append("<option value='ST'>速通物流</option>");
	$("#selectId").append("<option value='STO'>申通快递</option>");
	$("#selectId").append("<option value='STWL'>速腾快递</option>");
	$("#selectId").append("<option value='SURE'>速尔快递</option>");
	$("#selectId").append("<option value='TSSTO'>唐山申通</option>");
	$("#selectId").append("<option value='UAPEX'>全一快递</option>");
	$("#selectId").append("<option value='UC'>优速快递</option>");
	$("#selectId").append("<option value='WJWL'>万家物流</option>");
	$("#selectId").append("<option value='WXWL'>万象物流</option>");
	$("#selectId").append("<option value='XBWL'>新邦物流</option>");
	$("#selectId").append("<option value='XYT'>希优特</option>");
	$("#selectId").append("<option value='YADEX'>新杰物流</option>");
	$("#selectId").append("<option value='YCWL'>远成物流</option>");
	$("#selectId").append("<option value='YD'>韵达快递</option>");
	$("#selectId").append("<option value='YDH'>义达国际物流</option>");
	$("#selectId").append("<option value='YFEX'>越丰物流</option>");
	$("#selectId").append("<option value='YFHEX'>原飞航物流</option>");
	$("#selectId").append("<option value='YFSD'>亚风快递</option>");
	$("#selectId").append("<option value='YTKD'>运通快递</option>");
	$("#selectId").append("<option value='YTO'>圆通速递</option>");
	$("#selectId").append("<option value='YXKD'>亿翔快递</option>");
	$("#selectId").append("<option value='YZPY'>邮政平邮/小包</option>");
	$("#selectId").append("<option value='ZENY'>增益快递</option>");
	$("#selectId").append("<option value='ZHQKD'>汇强快递</option>");
	$("#selectId").append("<option value='ZJS'>宅急送</option>");
	$("#selectId").append("<option value='ZTE'>众通快递</option>");
	$("#selectId").append("<option value='ZTKY'>中铁快运</option>");
	$("#selectId").append("<option value='ZTO'>中通速递</option>");
	$("#selectId").append("<option value='ZTWL'>中铁物流</option>");
	$("#selectId").append("<option value='ZYWL'>中邮物流</option>");
	$("#selectId").append("<option value='AMAZON'>亚马逊物流</option>");
	$("#selectId").append("<option value='SUBIDA'>速必达物流</option>");
	$("#selectId").append("<option value='RFEX'>瑞丰速递</option>");
	$("#selectId").append("<option value='QUICK'>快客快递</option>");
	$("#selectId").append("<option value='CJKD'>城际快递</option>");
	$("#selectId").append("<option value='CNPEX'>CNPEX中邮快递</option>");
	$("#selectId").append("<option value='HOTSCM'>鸿桥供应链</option>");
	$("#selectId").append("<option value='HPTEX'>海派通物流公司</option>");
	$("#selectId").append("<option value='AYCA'>澳邮专线</option>");
	$("#selectId").append("<option value='PANEX'>泛捷快递</option>");
	$("#selectId").append("<option value='PCA'>PCA Express</option>");
	$("#selectId").append("<option value='UEQ'>UEQ Express</option>");

	$('#selectId').chosen();
	$('#selectId').chosen({
		allow_single_deselect : true
	});
});

function submitWl() {

	var text = $("#selectId").find("option:selected").text();
	var value = $("#selectId ").val();
	if (text == "请选择或者搜索" || value === 1) {
		$("#errInput").html("快递公司参数不能为空");
		return;
	}

	var numValue = $("#numValue").val();
	if (numValue === "") {
		$("#errInput").html("订单号不能为空");
		return;
	}

	var id = $("#numId").val();

	$("#errInput").html("");

	data = {
		'id' : id,
		'number' : numValue,
		'company' : text,
		'code' : value
	};
	$.ajax({
		type : 'post',
		url : '/crowFundingDetail/addLogistics.do',
		data : data,
		cache : false,
		dataType : 'text',
		success : function(data) {
			alert(data);
			$("#closeW").click();
			$("#crowdDetail_table").bootstrapTable('refresh', opt);
		}
	});
}

// 拼接id
function read(data) {
	var obj = $(data).parent().siblings();
	console.log(obj);
	var id = obj[0].innerText;
	$("#numValue").val("");
	$("#numId").val("");
	$("#numId").val(id);
}