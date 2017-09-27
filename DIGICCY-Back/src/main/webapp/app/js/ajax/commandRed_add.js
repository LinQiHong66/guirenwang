/**
 * Created by Administrator on 2016/12/1 0001.
 */
$("#addCommandRed").click(
		function() {
			var textRemark = commandRemark.$txt.html();
		    //将html源码放在隐藏域中
			$("#command_remark").val(textRemark);
			var command_name = $("#command_name").val();
		    var command_prize_type = $("#command_prize_type").val();
		    var command_name_price = $("#command_name_price").val();
		    var command_remark = $("#command_remark").val();
		    var number = $("#number").val();
		    if(command_name == '' || command_prize_type ==''
		    	|| command_name_price == '' || command_remark == ''
		    	|| number == ''){
		    	alert("必填项目不能为空！")
		    	return false;
		    }
			$.ajax({
					url : "/commandRed/addCommandRed.do",
					type : "POST",
					dataType : "json",
					data : {
						command_name : command_name,
						command_prize_type : command_prize_type,
						command_name_price : command_name_price,
						command_remark : command_remark,
						number : number
					},
					success : function(data) {
						if (data.code == "100") {
							alert('添加成功');
							window.location.href = "/commandRed/gotoCommandRed.do";
						} else {
							alert('添加失败');
							return false;
						}
					},
					error : function(data) {
						alert("服务器无响应");
						return false;
					}
					
		});
			
});

