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
	var data={'currentPageNum':currentPageNum,'perPageSize':totle,'grade':firstname};
	
	$.ajax({  
	    type:'post',    
	    url:'/Integral/Grade/queryIntegralGrade.do',  
	    data:data,  
	    cache:false,  
	    dataType:'json',  
	    success:function(data){  
	    	Data_assembly(data);
	    	footer(data.data.totalPageNum,data.data.currentPageNum);
	    }  
	  });
}

//数据组装方法
function Data_assembly(data){
	  var tbody=data.data.entitys;
	  var html="";
	  for(var i=0;i<tbody.length;i++){
		  var fh="";
		  if(tbody[i].additional==0){
			  fh="=";
		  }else if(tbody[i].additional==1){
			  fh=">";
		  }else if(tbody[i].additional==2){
			  fh="<";
		  }else if(tbody[i].additional==10){
			  fh="≥";
		  }else if(tbody[i].additional==20){
			  fh="≤";
		  }
		  
		  html+='<tr>';
		  html+='<td>'+tbody[i].grade+'</td>';
		  html+='<td>'+fh+tbody[i].conditions+'</td>';
		  html+='<td>'+tbody[i].quicks+'%</td>';
		  html+='<td>'+tbody[i].speed+'%</td>';
		  html+='<td><button type="button" onclick="updateSelect('+"'"+tbody[i].id+"'"+');" class="btn btn-warning">修改</button>&nbsp;&nbsp;'
			  +'<button type="button" onclick="delcfm('+"'"+tbody[i].id+"'"+');" class="btn btn-danger">删除</button></td>';
		  html+='</tr>';
	  }
	  
	  $("#complete_tbody").html(html);
}

//添加前将提交地址改为添加
function addRead(){
	emptyingInput();
	$("#submit").attr("onclick","addRule();");
}

//添加一个任务信息
function addRule(){
	
	var gradeValue=$("#gradeValue").val();
	var conditionsValue=$("#conditionsValue").val();
	var quicksValue=$("#quicksValue").val();
	var speedValue=$("#speedValue").val();
	var additional=$("#additional").val();
	var err="";
	
	if(gradeValue==null || gradeValue==""){
		err+=" 等级不能为空!"
	}
	if(additional==null || additional==""){
		err+=" 页面异常 请刷新页面重新提交";
	}
	if(quicksValue==null || quicksValue =="" || quicksValue<0 || quicksValue>100 ){
		err+=" 快速提现率只能为非负数且不能为空,不能小于0大于100!"
	}
	if(speedValue==null || speedValue =="" || speedValue<0 || speedValue>100){
		err+=" 极速提现率只能为只能为非负数且不能为空,不能小于0大于100!"
	}
	if(conditionsValue==null || conditionsValue==""){
		err+=" 条件不能为空!"
	}
	
	
	
	if(err!==""){
		$("#errInput").html(err);
		return;
	}else{
		$("#errInput").html("");
	}
	$("#submit").attr("disabled", true); 
	var data={'grade':gradeValue,'conditions':conditionsValue,'quicks':quicksValue,'speed':speedValue,"additional":additional};
	$.ajax({  
	    type:'post',    
	    url:'/Integral/Grade/addIntegralGrade.do',  
	    data:data,  
	    cache:false,  
	    dataType:'json',  
	    success:function(data){ 
	    	console.log(data);
	    	$("#submit").attr("disabled", false); 
	    	if(data.code==0){
	    		closeWindow();
	    		toastr.success('提交数据成功');
	    		emptyingInput();
	    		ready();
	    	}else{
	    		toastr.error(data.msg);
	    	}
	    }  
	  });
}

//清空文本框
function emptyingInput(){
	$("#errInput").html("");
	$("#gradeValue").val("");
	$("#conditionsValue").val("");
	$("#quicksValue").val("");
	$("#speedValue").val("");
	$("#additional").text("");
	$("#additional").text("10");
	$("#symbol").html("大于等于");
}

//关闭遮罩层
function closeWindow(){
	$("#closeW").click();
}

//正则验证只能为数字
function isNum(num){
	 var reg=/^\\d+(\\.\\d+)?$/;
	 return reg.test(num);
}

//删除弹出确认框
function delcfm(id) {
	$("#removeA").attr("onclick","remove('"+id+"');");
    $('#delcfmModel').modal();  
} 


//删除
function remove(id){
	
	if(id==null || id==""){
		toastr.error("关键字缺失,删除失败,请联系管理员");
		return;
	}
	
	
	var data={'id':id};
	$.ajax({  
	    type:'post',    
	    url:'/Integral/Grade/deleteGrade.do',  
	    data:data,  
	    cache:false,  
	    dataType:'json',  
	    success:function(data){ 
	    	if(data.code==0){
	    		toastr.success('删除成功');
	    		ready();
	    	}else{
	    		toastr.error(data.msg);
	    	}
	    }  
	  });
}

//修改前查询
function updateSelect(id){
	var data={'id':id};
	$.ajax({  
	    type:'post',    
	    url:'/Integral/Grade/queryIntegralGrade.do',  
	    data:data,  
	    cache:false,  
	    dataType:'json',  
	    success:function(data){  
	    	var result=data.data.entitys[0];
	    	$("#gradeValue").val(result.grade);
	    	$("#conditionsValue").val(result.conditions);
	    	$("#quicksValue").val(result.quicks);
	    	$("#speedValue").val(result.speed);
	    	$("#quicksValue").val(result.quicks);
	    	setValue(result.additional);//设置下拉框的值
	    	$("#btn_update").click();
	    	$("#submit").attr("onclick","update('"+id+"');");
	    }  
	
	  });
}

//修改方法
function update(id){
	
	var gradeValue=$("#gradeValue").val();
	var conditionsValue=$("#conditionsValue").val();
	var quicksValue=$("#quicksValue").val();
	var speedValue=$("#speedValue").val();
	var additional=$("#additional").val();
	var err="";
	
	if(gradeValue==null || gradeValue==""){
		err+=" 等级不能为空!"
	}
	if(additional==null || additional==""){
		err+=" 页面异常 请刷新页面重新提交";
	}
	if(quicksValue==null || quicksValue =="" || quicksValue<0 || quicksValue>100 ){
		err+=" 快速提现率只能为非负数且不能为空,不能小于0大于100!"
	}
	if(speedValue==null || speedValue =="" || speedValue<0 || speedValue>100){
		err+=" 极速提现率只能为只能为非负数且不能为空,不能小于0大于100!"
	}
	if(conditionsValue==null || conditionsValue==""){
		err+=" 条件不能为空!"
	}
	
	
	if(err!==""){
		$("#errInput").html(err);
		return;
	}else{
		$("#errInput").html("");
	}
	$("#submit").attr("disabled", true); 
	var data={'id':id,'grade':gradeValue,'conditions':conditionsValue,'quicks':quicksValue,'speed':speedValue,"additional":additional};
	
	$.ajax({  
	    type:'post',    
	    url:'/Integral/Grade/updateGrade.do',  
	    data:data,  
	    cache:false,  
	    dataType:'json',  
	    success:function(data){ 
	    	if(data.code==0){
	    		closeWindow();
	    		toastr.success('修改数据成功');
	    		emptyingInput();
	    		ready();
	    	}else{
	    		toastr.error(data.msg);
	    	}
	    	$("#submit").attr("disabled", false); 
	    }  
	
	  });
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
			ready(num,10);
		}else{
			return;
		}
	} else if (type == 0) {
		ready(num,10);
	} else {

	}

}


//添加下拉框
function getValue(id){
	 id=$(id).text();
	 $("#symbol").html(id);
	 if(id=='等于'){
		 $("#additional").val(0);
	 }
	 if(id=='大于'){
		 $("#additional").val(1);
	 }
	 if(id=='小于'){
		 $("#additional").val(2);
	 }
	 if(id=='大于等于'){
		 $("#additional").val(10);
	 }
	 if(id=='小于等于'){
		 $("#additional").val(20);
	 }
}

function setValue(number){
	 if(number=='0'){
		 $("#additional").val(0);
		 $("#symbol").html("等于");
	 }
	 if(number=='1'){
		 $("#additional").val(0);
		 $("#symbol").html("大于");
	 }
	 if(number=='2'){
		 $("#additional").val(0);
		 $("#symbol").html("小于");
	 }
	 if(number=='10'){
		 $("#additional").val(0);
		 $("#symbol").html("大于等于");
	 }
	 if(number=='20'){
		 $("#additional").val(0);
		 $("#symbol").html("小于等于");
	 }
}