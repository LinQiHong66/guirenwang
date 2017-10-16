/**
 * Created by Administrator on 2016/12/1 0001.
 */
$("#addLevel").click(
		function() {
			var coin_no = $("#coin_no").val();
		    var level_one = $("#level_one").val();
		    var level_two = $("#level_two").val();
		    var level_three = $("#level_three").val();
		    var level_four = $("#level_four").val();
		    var level_five = "0";
		    var level_type = "0";
		    var state = $("#state").val();
		    if(coin_no == '' || level_one == '' || level_two =='' ||
		    		level_three == '' || level_four == '' || level_five == ''||
		    		level_type == '' || state == ''){
		    	alert("必填项目不能为空！");
		    	return false;
		    }
			$.ajax({
					url : "/coinproportion/insert.do",
					type : "POST",
					dataType : "json",
					data : {
						coin_no : coin_no,
						level_one : level_one,
						level_two : level_two,
						level_three : level_three,
						level_four : level_four,
						level_five : level_five,
						level_type : level_type,
						state : state
					},
					success : function(data) {
						alert(data.desc);
						window.location.href = "/coinproportion/goto.do";
					},
					error : function(data) {
						alert("服务器无响应");
						return false;
					}
					
		});
});

$("#editLevel").click(
		function() {
			var id = $("#id").val();
			var coin_no = $("#coin_no").val();
		    var level_one = $("#level_one").val();
		    var level_two = $("#level_two").val();
		    var level_three = $("#level_three").val();
		    var level_four = $("#level_four").val();
		    var level_five = "0";
		    var level_type = "0";
		    var state = $("#state").val();
		    if(id == '' ||coin_no == '' || level_one == '' || level_two =='' ||
		    		level_three == '' || level_four == '' || level_five == ''||
		    		level_type == '' || state == ''){
		    	alert("必填项目不能为空！");
		    	return false;
		    }
			$.ajax({
					url : "/coinproportion/update.do",
					type : "POST",
					dataType : "json",
					data : {
						id : id,
						coin_no : coin_no,
						level_one : level_one,
						level_two : level_two,
						level_three : level_three,
						level_four : level_four,
						level_five : level_five,
						level_type : level_type,
						state : state
					},
					success : function(data) {
						alert(data.desc);
						window.location.href = "/coinproportion/goto.do";
					},
					error : function(data) {
						alert("服务器无响应");
						return false;
					}
					
		});
			
});


