/**
 * Created by Administrator on 2016/12/1 0001.
 */
$("#editHelpCenter").click(
		function() {
			var id = $("#id").val();
			var help_name = $("#help_name").val();
		    var help_grade = $("#help_grade").val();
		    var help_parent = $("#help_parent").val();
			var help_remark = UE.getEditor('editor').getContent();
			if(help_grade == '1'){
				var help_parent = '0';
				if(help_name == '' || help_grade ==''){
			    	alert("必填项目不能为空！")
			    	return false;
			    }
		    }else{
		    	if(help_name == '' || help_parent ==''
			    	|| help_parent == '' || help_remark == ''){
			    	alert("必填项目不能为空！")
			    	return false;
			    }
		    }
			$.ajax({
				url : "/helpCenter/editHelpCenter.do",
				type : "POST",
				dataType : "json",
				data : {
					id : id,
					help_name : help_name,
					help_grade : help_grade,
					help_parent : help_parent,
					help_remark : help_remark
				},
				success : function(data) {
					if (data.code == "100") {
						alert('修改成功');
						window.location.href = "/helpCenter/gotoHelpCenter.do";
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

