//"power_table"
$.ajax({
	url : "/user/getPowerLog.do",
	type : "post",
	dataType : "json",
	data : {
		curPage : 1,
		pageItem : 10
	},
	success : function(data) {
		console.log(data);
		inintData(data, 10, 1);
	}
});

// 给排序点击监听
$('img').live('click',function(){
	var orderName = $('#orderName');
	var orderType = $('#orderType');
	orderName.html(this.title);
	orderType.html(this.name);
	
	// 隐藏自己显示其他
	$('img').show();
	$(this).hide();
	
	var pageItem = $('#pageItem').val();
	var curPage = $('#curPage').html();
	getCurPageInfos(pageItem/1, curPage/1, orderName.html(),orderType.html());
	
});
// 查看详情
function getPowerBasicInfo(infoid) {
	var infoLoad = $('#infoLoad');
	infoLoad.show();
	$.ajax({
		
		url:"/user/getPowerLogInfo.do",
		type:"post",
		dataType:"json",
		data:{
			powerInfoId : infoid
		},
		success:function(data){
			loadInfoData(data);
		},
		error:function(e){
			loadInfoData("error");
		}
	});
}

// 获取某页的数据
function getCurPageInfos(pageItem, curPage, orderItem, orderType) {
	var userName = $('#userName').val();
	var powerInfo = $('#powerInfo').val();
	var powerUrl = $('#powerUrl').val();
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	$.ajax({
		url : "/user/getPowerLog.do",
		type : "post",
		dataType : "json",
		data : {
			curPage : 1,
			pageItem : 10,
			url : powerUrl,
			info : powerInfo,
			userName : userName,
			startDate : startDate,
			endDate : endDate,
			curPage : curPage,
			pageItem : pageItem,
			orderName:orderItem,
			orderType:orderType
		},
		success : function(data) {
			inintData(data, pageItem, curPage,orderItem,orderType);
		}
	});
}



// 加载用户日志数据
function inintData(data, curItem, pageCur, orderName, orderType) {
	var powertab = $('#power_table');
	var countItem = $('#countItem');
	var curPage = $('#curPage');
	var pageItem = $('#pageItem');
	powertab
			.html("<thead style='width:100%'>"
					+ "<th>"
					+ "访问人"
					+ "<br /><img title='userName' id='userNameUp' name='up' src='/app/img/up.png' style='width:4%' />&nbsp;&nbsp;<img title='userName' id='userNameDown' name='down' src='/app/img/down.png' style='width:4%' /></th>"
					+ "<th>"
					+ "权限信息"
					+ "<br /><img title='info' id='powerInfoUp' name='up' src='/app/img/up.png' style='width:4%' />&nbsp;&nbsp;<img title='info' id='powerInfoDown' name='down' src='/app/img/down.png' style='width:4%' /></th>"
					+ "<th>"
					+ "权限地址url"
					+ "<br /><img title='url' id='powerUrlUp' name='up' src='/app/img/up.png' style='width:4%' />&nbsp;&nbsp;<img title='url' id='powerUrlDown' name='down' src='/app/img/down.png' style='width:4%' /></th>"
					+ "<th>"
					+ "操作时间"
					+ "<br /><img title='time' id='timeUp' name='up' src='/app/img/up.png' style='width:4%' />&nbsp;&nbsp;<img title='time' id='timeDown' name='down' src='/app/img/down.png' style='width:4%' /></th>"
					+ "<th>" + "操作" + "</th>"
					+ "</thead><tbody align='center' style='width:100%'>");
	if (data.code == 100) {
		// 加载表格数据
		$.each(data.result, function(a, b) {
			powertab.html(powertab.html() + "<tr style='width:100%'>"
					+ "<td align='center' style='width:20%'>" + b.userName
					+ "</td>" + "<td align='center' style='width:20%'>"
					+ b.info + "</td>"
					+ "<td align='center' style='width:20%'>" + b.url + "</td>"
					+ "<td align='center' style='width:20%'>" + b.time
					+ "</td>" + "<td align='center' style='width:20%'>"
					+ "<button class='btn btn-primary btn-lg' data-toggle='modal' data-target='#showInfo' onclick='getPowerBasicInfo(" + b.id
					+ ")'>查看详情</button>" + "</td>" + "</tr>");
		});
		var countSize = data.size;
		pageItem.html("");
		if (countSize == 10) {
			pageItem.html(pageItem.html()
					+ "<option selected value='10'>10</option>");
		} else {
			pageItem.html(pageItem.html() + "<option value='10'>10</option>");
		}
		if (countSize > 19) {

			if (curItem == 20) {
				pageItem.html(pageItem.html()
						+ "<option selected value='20'>20</option>");
			} else {
				pageItem.html(pageItem.html()
						+ "<option value='20'>20</option>");
			}
		}
		if (countSize > 29) {

			if (curItem == 30) {
				pageItem.html(pageItem.html()
						+ "<option selected value='30'>30</option>");
			} else {
				pageItem.html(pageItem.html()
						+ "<option value='30'>30</option>");
			}
		}
		if (countSize > 49) {

			if (curItem == 50) {
				pageItem.html(pageItem.html()
						+ "<option selected value='50'>50</option>");
			} else {
				pageItem.html(pageItem.html()
						+ "<option value='50'>50</option>");
			}
		}
		// 分页数据加载
		curPage.html(pageCur);
		countItem.html(countSize);
	}
	powertab.html(powertab.html() + "</tbody>");
	$('img[title='+orderName+'][name='+orderType+']').hide();
}

// 加载详情数据
function loadInfoData(data){
	var infoLoad = $('#infoLoad');
	var tab_powerInfo = $('#tab_powerInfo');
	var infoTitle = $('#infoTitle');
	infoTitle.html('权限详情');
	if(data == "error" || data.code != 100){
		infoLoad.html('获取失败！请稍后重试');
	}else{
		var result = data.result;
		infoLoad.hide();
		console.log(data);
		tab_powerInfo.html("<tbody style='width:100%'>" +
				"<tr style='width:100%'>" +
				"<td align='center'>执行人</td>" +
				"<td align='center'>"+result.userName+"</td>" +
				"</tr>" +
				"<tr style='width:100%'>" +
				"<td align='center'>权限信息</td>" +
				"<td align='center'>" +
				result.info +
				"</td>" +
				"</tr>" +
				"<tr style='width:100%'>" +
				"<td align='center'>权限url</td>" +
				"<td align='center'>" +
				result.url +
				"</td>" +
				"</tr>" +
				"<tr style='width:100%'>" +
				"<td align='center'>访问时间</td>" +
				"<td align='center'>" +
				result.time +
				"</td>" +
				"</tr>" +
				"<tr style='width:100%'><td align='center' colspan='3'>参数详情</td></tr>" +
				"<tr>" +
				"<td align='center'>参数名</td>" +
				"<td align='center'>参数详情</td>" +
				"<td align='center'>参数值</td>" +
				"</tr>" );

 $.each(result.params, function(a,b){
	 tab_powerInfo.html(tab_powerInfo.html()+
"<tr>" +
 "<td align='center'>" +
 b.paramName +
 "</td>" +
 "<td align='center'>" +
 b.paramInfo +
 "</td>" +
 "<td align='center'>" +
 b.paramValue +
 "</td>" +
 "</tr>");
 });

		tab_powerInfo.html(tab_powerInfo.html()+
				"</tbody>");
	}
}