/**
 * Created by Administrator on 2016/12/1 0001.
 */
$("#editCrowd").click(
		function() {
			var textExplain = editorExplain.$txt.html();
		    //将html源码放在隐藏域中
			$("#icoExplain").val(textExplain);
			var textRemark = editorRemark.$txt.html();
		    //将html源码放在隐藏域中
			$("#icoRemark").val(textRemark);
		    var icoName = $("#icoName").val();
		    var icoPriceType = $("#icoPriceType").val();
		    var icoTarget = $("#icoTarget").val();
		    var icoPrice = $("#icoPrice").val();
		    var minNumber = $("#minNumber").val();
		    var maxNumber = $("#maxNumber").val();
		    var numberInteger = $("#numberInteger").val();
		    var beginDate = $("#beginDate").val();
		    var endDate = $("#endDate").val();
		    if(photoFile == '' || photoRemarkFile ==''
		    	|| icoName == '' || icoPriceType == ''
		    	|| icoTarget == '' || icoPrice == ''
		    	|| endDate == '' || textRemark == ''
				|| textExplain == '' || beginDate == '' 
				|| minNumber == '' || maxNumber == ''
				|| numberInteger == ''){
		    	alert("必填项目不能为空！")
		    	return false;
		    }
			$.ajax({
					url : "/crowFunding/editCrowdFunding.do",
					type : "POST",
					dataType : "json",
					data : new FormData($('#myform')[0]),
					success : function(data) {
						console.log(data)
						if (data.code == "100") {
							alert('修改成功');
							window.location.href = "/crowFunding/gotoCrowd.do";
						} else {
							alert('修改失败');
							return false;
						}
					},
					error : function(data) {
						alert("服务器无响应");
						return false;
					},
					processData : false,
					contentType : false,
					async:false
					
		});
			
});

