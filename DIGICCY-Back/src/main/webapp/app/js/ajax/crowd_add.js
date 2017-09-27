/**
 * Created by Administrator on 2016/12/1 0001.
 */
$(function () {
    $.ajax({
        url:"/coin/getAllCrowdCoin.do",
        type: "get",
        dataType: "json",
        success: function (msg) {
            var icoPriceType = $("#icoPriceType");
            var dataJson = JSON.stringify(msg.data);
            var data = JSON.parse(dataJson);
            for(var i=0;i<data.length;i++){
            	icoPriceType.append("<option value='"+data[i].coin_no+"'>"+data[i].coin_name+"</option>");
            }
        }
    });
})
	
$("#addCrowd").click(
		function() {
			var textExplain = editorExplain.$txt.html();
		    //将html源码放在隐藏域中
			$("#icoExplain").val(textExplain);
			var textRemark = editorRemark.$txt.html();
		    //将html源码放在隐藏域中
			$("#icoRemark").val(textRemark);
			var photoFile = $("#photoFile").val();
		    var photoRemarkFile = $("photoRemarkFile").val();
		    var icoName = $("#icoName").val();
		    var icoPriceType = $("#icoPriceType").val();
		    var icoTarget = $("#icoTarget").val();
		    var icoPrice = $("#icoPrice").val();
		    var minNumber = $("#minNumber").val();
		    var maxNumber = $("#maxNumber").val();
		    var numberInteger = $("#numberInteger").val();
		    var convert = $("#convert").val();
		    var convertFunction = $("#convertFunction").val();
		    var beginDate = $("#beginDate").val();
		    var endDate = $("#endDate").val();
		    if(photoFile == '' || photoRemarkFile ==''
		    	|| icoName == '' || icoPriceType == ''
		    	|| icoTarget == '' || icoPrice == ''
		    	|| endDate == '' || textRemark == ''
				|| textExplain == '' || beginDate == '' 
				|| minNumber == '' || maxNumber == ''
				|| numberInteger == '' || convert == ''
				|| convertFunction == ''){
		    	alert("必填项目不能为空！")
		    	return false;
		    }
			$.ajax({
					url : "/crowFunding/addCrowdFunding.do",
					type : "POST",
					dataType : "json",
					data : new FormData($('#myform')[0]),
					success : function(data) {
						console.log(data)
						if (data.code == "100") {
							alert('添加成功');
							window.location.href = "/crowFunding/gotoCrowd.do";
						} else {
							alert('添加失败');
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

