//初始化气泡位置
toastr.options.positionClass = 'toast-center-center';

//页面初始化方法
window.onload = function(){ 
	ready("","");
} 

//界面加载方法
function ready(currentPageNum,totle){
	
	// 分页参数设定
	if(currentPageNum=="" || currentPageNum==null){
		currentPageNum=1;
	}
	// 分页参数设定
	if(totle=="" || totle==null){
		totle=10;
	}
	var firstname=$("#firstname").val();
	var data={'currentPageNum':currentPageNum,'userName':firstname,'perPageSize':totle};
	$.ajax({  
	    type:'post',    
	    url:'/Integral/Detail/queryIntegralDetail.do',  
	    data:data,  
	    cache:false,  
	    dataType:'json',  
	    success:function(data){  
	    	Data_assembly(data);
	    	console.log(data);
	    	footer(data.data.totalPageNum,data.data.currentPageNum);
	    }  
	  });
}

//数据组装方法
function Data_assembly(data){
	  var tbody=data.data.entitys;
	  var html="";
	  for(var i=0;i<tbody.length;i++){
		  html+='<tr>';
		  html+='<td>'+tbody[i].createTime+'</td>';
		  html+='<td>'+tbody[i].type+'</td>';
		  html+='<td>+'+tbody[i].number+'</td>';
		  html+='<td>'+tbody[i].userName+'</td>';
//		  html+='<td><button type="button" onclick="updateSelect('+"'"+tbody[i].id+"'"+');" class="btn btn-warning">修改</button>&nbsp;&nbsp;'
//			  +'<button type="button" onclick="delcfm('+"'"+tbody[i].id+"'"+');" class="btn btn-danger">删除</button></td>';
		  html+='</tr>';
	  }
	  
	  $("#complete_tbody").html(html);
}





//分页页脚生成方法
//dataSize 总页数
//unmSize 当前所在页数
function footer(dataSize, unmSize) {
	$("#paging").find("li").remove();
	var html = '<li><a href="javascript:skip(1,' + unmSize + ',' + dataSize
			+ ');">&laquo;</a></li>';
	$("#paging").append(html);

	var number = 12;

	// 判断生成前面部分页脚
	if (unmSize > 5) {
		html = '<li><a href="javascript:skip(0,1);">1</a></li>';
		$("#paging").append(html);
		html = '<li><a href="javascript:skip(0,2);">2</a></li>';
		$("#paging").append(html);
		html = '<li><a href="javascript:void(0);">...</a></li>';
		$("#paging").append(html);
		number = number - 3;
		// 循环生成前半部分
		for (var i = 3; i > 0; i--) {
			if (unmSize - 2 - i > 3) {
				for (i; i > 0; i--) {
					number--;
					var finlSize = unmSize;
					finlSize = finlSize - i;
					html = '<li><a href="javascript:skip(0,' + finlSize + ','
							+ dataSize + ');">' + finlSize + '</a></li>';
					$("#paging").append(html);
				}
				break;
			}
		}

	} else {
		for (var i = 1; i < unmSize; i++) {
			number--;
			html = '<li><a href="javascript:skip(0,' + i + ',' + dataSize
					+ ');">' + i + '</a></li>';
			$("#paging").append(html);
		}
	}

	// 生成当前页
	html = '<li><a style="color:#000;" href="javascript:skip(0,' + unmSize
			+ ',' + dataSize + ');">' + unmSize + '</a></li>';
	$("#paging").append(html);

	// 循环生成后半部分
	for (var i = number; i > 0; i--) {

		if (unmSize + i <= dataSize) {
			number = i;
			var size = unmSize;
			for (i; i > 0; i--) {
				size = size + 1;
				html = '<li><a href="javascript:skip(0,' + size + ','
						+ dataSize + ');">' + size + '</a></li>';
				$("#paging").append(html);
			}
		}
	}

	// 判断生成最后部分页脚
	var endNumber = dataSize - unmSize - number;
	if (endNumber > 3) {
		// 尾部长度超出三个，用省略号代替
		html = '<li><a href="javascript:void(0);">...</a></li>';
		$("#paging").append(html);
		var endSize = dataSize - 1;
		html = '<li><a href="javascript:skip(0,' + endSize + ');">' + endSize
				+ '</a></li>';
		$("#paging").append(html);
		html = '<li><a href="javascript:skip(0,' + dataSize + ');">' + dataSize
				+ '</a></li>';
		$("#paging").append(html);

	} else {
		// 尾部长度没有超出三个，不用省略号
		for (var i = endNumber; i > 0; i--) {
			html = '<li><a href="javascript:skip(0,' + dataSize + ');">'
					+ dataSize + '</a></li>';
			$("#paging").append(html);
			dataSize = dataSize - i;
		}
	}
	html = '<li><a href="javascript:skip(2,' + unmSize + ',' + dataSize
			+ ');">&raquo;</a></li>';
	$("#paging").append(html);
}



//分页前置配参执行方法
function skip(type, num, dataSize) {
	if (type == 1) {
		if (num > 1) {
			num = num - 1
		}else{
			return;
		}
		ready(num,10);
	} else if (type == 2) {

		if (num < dataSize) {
			num = num + 1
			var data={'currentPageNum':num};
			ready(num,10);
		}else{
			return;
		}
	} else if (type == 0) {
		ready(num,10);
	} else {

	}

}