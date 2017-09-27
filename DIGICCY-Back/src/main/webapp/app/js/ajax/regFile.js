//检查excel
function checkExcel(){
	$.ajax({
	    url:"/file/checkExcel.do",
	    type:"post",
	    data:new FormData($('#regform')[0]),
	    success:function(data) {
	    	console.log(data);
	    	var msg = data.msg;
	    	var showMessage = $('#showInfo');
	    	var bachRegDiv = $('#bachReg');
	    	var address_cb = $("#check_cb");
	    	var executeableInfo = $('#executeableInfo');
	    	
	    	//显示检验信息
	    	showMessage.html("excel信息如下：<br />");
	    	//fail有空列或者数据重复
	    	if(msg == "fail"){
	    		showMessage.html("有重复用户或有空用户<br />");
	    		$.each(data.repeat, function(a,b){
	    			showMessage.html(showMessage.html()+b+"&nbsp;&nbsp;重复<br />");
	    		});
	    	}else{
	    		$.each(data.msg, function(a,b){
	    			showMessage.html(showMessage.html()+"<br />"+b);
	    		});
	    		
	    	}
	    	//判断能够注册
	    	if(data.canReg){
	    		bachRegDiv.show();

	    		executeableInfo.html("恭喜，该表格通过审核，可以批量注册<br />");
	    		//判断能够添加地址
	    		if(data.canAddAddress){
	    			address_cb.removeAttr("disabled");
	    			executeableInfo.html(executeableInfo.html()+"该表格有用户地址列，可以选择添加地址。");
	    		}else{
	    			address_cb.attr("disabled","disabled");
	    		}
	    	}else{
	    		executeableInfo.html("很遗憾该表格不能够进行注册<br />");
	    		bachRegDiv.hide();
    			address_cb.attr("disabled","disabled");
	    	}
	    },
	    error:function(e){
	    	var showMessage = $('#showInfo');
	    	showMessage.html("excel信息如下：<br />" +
	    			"上传的文件出错！<br />友情提示：上传的文件必须是Microsoft Excel 97-2003的文件，后缀是xls，请仔细检查您的文件，谢谢！！");
	    },
	    cache:false,
	    processData:false,
	    contentType:false
	  });
}
//开始执行
function startReg(){
	$.ajax({
	    url:"/file/executeExcel.do",
	    type:"post",
	    data:new FormData($('#regform')[0]),
	    success:function(data) {
//	    	alert("处理个数："+data.executeSize);

	    	var executeableInfo = $('#executeableInfo');
	    	executeableInfo.html("系统正在处理"+data.executeSize+"个数据");
	    	
	    },
	    cache:false,
	    processData:false,
	    contentType:false
	  });
}
//查看执行结果
function showResult(){
	var resultSpan = $('#regResult');
	$.ajax({
	    url:"/file/getResult.do",
	    type:"post",
	    data:new FormData($('#regform')[0]),
	    success:function(data) {
	    	console.log(data);
	    	if(data.code == "success"){
	    		resultSpan.html("处理添加地址共："+data.addressCount+"个"+"<br />" +
		    			"注册失败共"+data.errorCount+"个<br />" +
		    					"注册成功共"+data.successCount+"个<br />注册信息如下：<br />");
		    	$.each(data.errList,function(a,b){
		    		resultSpan.html(resultSpan.html()+b+"<br />");
		    	});
		    	resultSpan.html(resultSpan.html()+"添加地址信息如下：<br />");
		    	$.each(data.addressAdd,function(a,b){
		    		resultSpan.html(resultSpan.html()+b+"<br />");
		    	});
	    	}else{
	    		resultSpan.html("获取结果失败");
	    	}
	    
	    },
	    cache:false,
	    processData:false,
	    contentType:false
	  });
}
//当文件改变时
function changeExcel(){
	var bachRegDiv = $('#bachReg');
	var showMessage = $('#showInfo');
	var address_cb = $("#check_cb");
	var executeableInfo = $('#executeableInfo');
	
	bachRegDiv.hide();
	showMessage.html("");
	executeableInfo.html("");
	address_cb.removeAttr("checked");
	address_cb.attr("disabled","disabled");
}