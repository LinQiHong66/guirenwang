$.ajax({
	url:'/other/getAllLink.do',
	type:'POST',
	dataType:'json',
	success:function(data){
		console.log(data);
		var items = data.result;
		if(items != null && items.length > 0){
			var tab = $('#recharge_table');
			tab.html("<thead>" +
					"<th>编号</th>" +
					"<th>链接名称 </th>" +
					"<th>链接地址</th>" +
					"<th>链接类型</th>" +
					"<th>操作</th>" +
					"</thead><tbody>");
			$.each(items, function(a,b){
				var str="<tr style='width:100%;'>" +
						"<td style='text-align:center; min-width:80px; width:20%'>"+b.id+"</td>" +
						"<td style='text-align:center; min-width:80px; width:20%'>"+b.linkName+"</td>" +
						"<td style='text-align:center; min-width:80px; width:20%'>"+b.linkUrl+"</td>" +
						"<td style='text-align:center; min-width:80px; width:20%'>"+b.linkType+"</td>" +
						"<td style='text-align:center; min-width:80px; width:20%'><button class='btn btn-warning' onclick='delLink("+b.id+")' >删除</button><a href='#modifymodal' onclick='updateLink("+b.id+")' class='btn btn-primary' data-toggle='modal'>修改 </a></td>" +
						"</tr>";
				tab.html(tab.html()+str);
			});
			tab.html(tab.html()+"</tbody>");
		}
	}
});
function updateLink(id){
	$.ajax({
		url:'/other/getLinkById.do',
		type:'POST',
		dataType:'json',
		data:{
			id:id
		},
		success:function(data){
			console.log(data);
			if(data.code == 100){
				var item = data.result[0]
				$('#modifymodal input[name=id]').attr("value", item.id);
				$('#modifymodal input[name=linkName]').attr("value", item.linkName);
				$('#modifymodal input[name=linkUrl]').attr("value", item.linkUrl);
				$('#modifymodal input[name=linkType]').attr("value", item.linkType);
				
			}
		}
	});
}
function delLink(id){
	$.ajax({
		url:'/other/delLink.do',
		type:'POST',
		dataType:'json',
		data:{
			id:id
		},
		success:function(data){
			if(data.code == 100){
				alert('删除成功');
				window.location.href='/other/link.do';
			}else{
				alert('删除失败');
			}
		}
	});
}