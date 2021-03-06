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
	var data={'currentPageNum':currentPageNum,'perPageSize':totle,'type':firstname};
	
	$.ajax({  
	    type:'post',    
	    url:'/Integral/Rule/queryIntegralRule.do',  
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
	  console.log(data);
	  console.log(tbody);
	  var tbody=data.data.entitys;
	  var html="";
	  for(var i=0;i<tbody.length;i++){
		  html+='<tr>';
		  html+='<td>'+tbody[i].type+'</td>';
		  html+='<td>'+tbody[i].instruction+'</td>';
		  html+='<td>'+tbody[i].reward+'</td>';
		  html+='<td>'+tbody[i].number+'</td>';
		  html+='<td>'+tbody[i].identifier+'</td>';
		  html+='<td>'+tbody[i].conditions+'</td>';
		  if(tbody[i].state==1){
			  html+='<td>启用</td>';
		  }else{
			  html+='<td>禁用</td>';
		  }
		  html+='<td><button type="button" onclick="updateSelect('+"'"+tbody[i].id+"'"+');" class="btn btn-warning">修改</button>&nbsp;&nbsp;';
		       if(tbody[i].state==0){
		    	   html+='<button type="button" onclick="updateState('+"'"+tbody[i].id+"','1'"+');" class="btn btn-danger">启用</button>';
		  		}else{
		  		   html+='<button type="button" onclick="updateState('+"'"+tbody[i].id+"','0'"+');" class="btn btn-danger">禁用</button>';
		  		}
		  		
			 // +'<button type="button" onclick="delcfm('+"'"+tbody[i].id+"'"+');" class="btn btn-danger">删除</button>';
		  html+='</td></tr>';
	  }
	  
	  $("#complete_tbody").html(html);
}

//添加前将提交地址改为添加
function addRead(){
	emptyingInput();
	$("#submit").attr("onclick","addRule();");
}

//添加一个规则信息
function addRule(){
	
	var tyepValue=$("#tyepValue").val();
	var numValue=$("#numValue").val();
	var instructionValue=$("#instructionValue").val();
	var numValue=$("#numValue").val();
	var rewardValue=$("#rewardValue").val();
	var identifierValue=$("#identifierValue").val();
	var conditionsValue=$("#conditionsValue").val();
	var err="";
	
	if(tyepValue==null || tyepValue==""){
		err+=" 类型不能为空!"
	}
	if(!isNum(numValue)){
		err+=" 积分只能为数字且不能为空!"
	}
	if(rewardValue==null || rewardValue==""){
		err+=" 操作内容不能为空!"
	}
	if(!isNum(rewardValue)){
		err+=" 具体奖励分只能为数字且不能为空!"
	}
	if(identifierValue==null || identifierValue==""){
		err+=" 操作标识符内容不能为空!"
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
	var data={'type':tyepValue,'number':numValue,'instruction':instructionValue,"reward":rewardValue,"identifier":identifierValue,"conditions":conditionsValue};
	$.ajax({  
	    type:'post',    
	    url:'/Integral/Rule/addIntegralRule.do',  
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
	$("#tyepValue").val("");
	$("#numValue").val("");
	$("#instructionValue").val("");
}

//关闭遮罩层
function closeWindow(){
	$("#closeW").click();
}

//正则验证只能为数字
function isNum(num){
	 var reg=/^[0-9]*$/;
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
	    url:'/Integral/Rule/deleteIntegralRule.do',  
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
	$("#fakeloader").show();//显示等待动画
	$.ajax({  
	    type:'post',    
	    url:'/Integral/Rule/queryIntegralRule.do',  
	    data:data,  
	    cache:false,  
	    dataType:'json',  
	    success:function(data){  
	    	$("#fakeloader").hide();//显示等待动画
	    	var result=data.data.entitys[0];
	    	$("#tyepValue").val(result.type);
	    	$("#numValue").val(result.number);
	    	$("#instructionValue").val(result.instruction);
	    	$("#rewardValue").val(result.reward);
	    	$("#identifierValue").val(result.identifier);
	        $("#conditionsValue").val(result.conditions);
	    	$("#btn_update").click();
	    	$("#submit").attr("onclick","update('"+id+"');");
	    }  
	
	  });
}

//修改方法
function update(id){
	
	var tyepValue=$("#tyepValue").val();
	var numValue=$("#numValue").val();
	var instructionValue=$("#instructionValue").val();
	var rewardValue=$("#rewardValue").val();
	var identifierValue=$("#identifierValue").val();
	var conditionsValue=$("#conditionsValue").val();
	var err="";
	
	if(tyepValue==null || tyepValue==""){
		err+=" 类型不能为空!"
	}
	if(!isNum(numValue)){
		err+=" 积分只能为数字且不能为空!"
	}
	
	if(instructionValue==null || instructionValue==""){
		err+=" 操作内容不能为空!"
	}
	
	if(!isNum(rewardValue)){
		err+=" 具体奖励分只能为数字且不能为空!"
	}
	if(identifierValue==null || identifierValue==""){
		err+=" 操作标识符内容不能为空!"
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
	var data={'id':id,'type':tyepValue,'number':numValue,'instruction':instructionValue,"reward":rewardValue,"identifier":identifierValue,"conditions":conditionsValue};
	closeWindow();
	$("#fakeloader").show();//显示等待动画
	$.ajax({  
	    type:'post',    
	    url:'/Integral/Rule/updateIntegralRule.do',  
	    data:data,  
	    cache:false,  
	    dataType:'json',  
	    success:function(data){
	    	$("#fakeloader").hide();
	    	if(data.code==0){
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


//修改方法
function updateState(id,state){
	
	var data={'id':id,'state':state};
	$("#fakeloader").show();//显示等待动画
	$.ajax({  
	    type:'post',    
	    url:'/Integral/Rule/updateStateIntegralRule.do',  
	    data:data,  
	    cache:false,  
	    dataType:'json',  
	    success:function(data){
	    	$("#fakeloader").hide();
	    	if(data.code==0){
	    		toastr.success('修改数据成功');
	    		emptyingInput();
	    		ready();
	    	}else{
	    		toastr.error(data.msg);
	    	}
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

$("#fakeloader").fakeLoader({
    timeToHide:1200, //Time in milliseconds for fakeLoader disappear
    zIndex:999, // Default zIndex
    spinner:"spinner1",//Options: 'spinner1', 'spinner2', 'spinner3', 'spinner4', 'spinner5', 'spinner6', 'spinner7' 
    bgColor:"rgba(0,0,0,.3)", //Hex, RGB or RGBA colors
}); 

//$("#fakeloader").fakeLoader();