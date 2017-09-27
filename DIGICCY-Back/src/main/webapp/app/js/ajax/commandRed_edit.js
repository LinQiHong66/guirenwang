/**
 * Created by Administrator on 2016/12/1 0001.
 */
$("#editCommandRed").click(
		function() {
			var textRemark = commandRemark.$txt.html();
		    //将html源码放在隐藏域中
			$("#command_remark").val(textRemark);
			var command_name = $("#command_name").val();
		    var command_prize_type = $("#command_prize_type").val();
		    var command_name_price = $("#command_name_price").val();
		    var command_remark = $("#command_remark").val();
		    var command_no = $("#command_no").val();
		    if(command_name == '' || command_prize_type ==''
		    	|| command_name_price == '' || command_remark == ''
		    	|| command_no == ''){
		    	alert("必填项目不能为空！")
		    	return false;
		    }
			$.ajax({
				url : "/commandRed/editCommandRed.do",
				type : "POST",
				dataType : "json",
				data : {
					command_name : command_name,
					command_prize_type : command_prize_type,
					command_name_price : command_name_price,
					command_remark : command_remark,
					command_no : command_no
				},
				success : function(data) {
					if (data.code == "100") {
						alert('修改成功');
						window.location.href = "/commandRed/gotoCommandRed.do";
					} else {
						alert('修改失败');
						return false;
					}
				},
				error : function(data) {
					alert("服务器无响应");
					return false;
				}
					
		});
			
});

