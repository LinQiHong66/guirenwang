/**
 * Created by Administrator on 2016/12/1 0001.
 */
$("#editTranAstrict").click(
		function() {
			var id = $("#id").val();
			var coin_no = $("#coin_no").val();
		    var buy_min_price = $("#buy_min_price").val();
		    var buy_max_price = $("#buy_max_price").val();
		    var sell_min_price = $("#sell_min_price").val();
		    var sell_max_price = $("#sell_max_price").val();
		    var single_min_price = $("#single_min_price").val();
		    var single_max_price = $("#single_max_price").val();
		    var rose_astrict = $("#rose_astrict").val();
		    var drop_astrict = $("#drop_astrict").val();
		    var beginDate = $("#beginDate").val();
		    var endDate = $("#endDate").val();
		    var state = $("#state").val();
		    if(coin_no == '' || buy_min_price == '' || buy_max_price =='' ||
		    		sell_min_price == '' || sell_max_price == '' || single_min_price == ''||
		    		single_max_price == '' || rose_astrict =='' || drop_astrict == '' ||
		    		beginDate == '' || endDate == '' || state == ''){
		    	alert("必填项目不能为空！");
		    	return false;
		    }
			$.ajax({
					url : "/coinTranAstrict/update.do",
					type : "POST",
					dataType : "json",
					data : {
						id : id,
						coin_no : coin_no,
						buy_min_price : buy_min_price,
						buy_max_price : buy_max_price,
						sell_min_price : sell_min_price,
						sell_max_price : sell_max_price,
						single_min_price : single_min_price,
						single_max_price : single_max_price,
						rose_astrict : rose_astrict,
						drop_astrict : drop_astrict,
						beginDate : beginDate,
						endDate : endDate,
						state : state
					},
					success : function(data) {
						if (data.code == "100") {
							alert('修改成功');
							window.location.href = "/coinTranAstrict/gotoCoinTranAstrict.do";
						}  else if(data.code == "201"){
							alert('该货币类型已有交易限制条件！');
							return false;
						}else {
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

