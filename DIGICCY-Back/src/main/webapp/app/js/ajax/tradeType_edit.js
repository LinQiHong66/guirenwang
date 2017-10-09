/**
 * Created by Administrator on 2016/12/1 0001.
 */
$("#editTradeType").click(
		function() {
			var id = $("#id").val();
			var coin_no = $("#coin_no").val();
			var tran_coin_no = $("#tran_coin_no").val();
		    var state = $("#state").val();
		    if(coin_no == '' || tran_coin_no == '' || state == '' || id == ''){
		    	alert("必填项目不能为空！");
		    	return false;
		    }
			$.ajax({
					url : "/coinTradeType/update.do",
					type : "POST",
					dataType : "json",
					data : {
						id : id,
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

