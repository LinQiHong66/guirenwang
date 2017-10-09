/**
 * Created by Administrator on 2016/12/1 0001.
 */
$("#addTradeType").click(
		function() {
			var coin_no = $("#coin_no").val();
			var tran_coin_no = $("#tran_coin_no").val();
		    var state = $("#state").val();
		    if(coin_no == '' || tran_coin_no == '' || state ==''){
		    	alert("必填项目不能为空！");
		    	return false;
		    }
			$.ajax({
					url : "/coinTradeType/add.do",
					type : "POST",
					dataType : "json",
					data : {
						coin_no : coin_no,
						tran_coin_no : tran_coin_no,
						state : state
					},
					success: function (msg) {
		                // if(msg.code == "100"){
		                alert(msg.desc);
		                window.location.href = "/coinTradeType/gotoCoinTradeType.do";
		                // }
		            },
					error : function(data) {
						alert("服务器无响应");
						return false;
					}
					
		});
			
});

