$(function() {
	$("#btn_add").click(function() {
		$("#box_add").css({
			"display" : "block"
		});
	});
	$("#close_add").click(function() {
		$("#box_add").css({
			"display" : "none"
		});
	})
	// $("#btn_update").click(function () {
	// $("#box_update").css({"display":"block"});
	// });
	$("#close_update").click(function() {
		$("#box_update").css({
			"display" : "none"
		});
	})
	$("#close_vote").click(function() {
		$("#box_vote").css({
			"display" : "none"
		});
	})
	$("#close_icon").click(function() {
		$("#box_icon").css({
			"display" : "none"
		});
	})
})

function addCoin() {
	// TODO
	var coin_no = $("#coin_no").val();
	var level_one = $("#level_one").val();
	var level_two = $("#level_two").val();
	//var block = $("#block").val();
		$.ajax({
			url : "/coinproportion/insert.do",
			type : "post",
			dataType : "json",
			data : {
				coin_no : coin_no,
				//coin_name : coin_name,
				level_one : level_one,
				level_two : level_two
			},
			success : function(msg) {
				// if(msg.code == "100"){
				$.alertable.alert(msg.desc);
				$("#box_add").css({
					"display" : "none"
				});
				window.location.reload();
				// }
			}
		});
}
function updateCoin() {
	// TODO
	var id = $("#update_id").val();
	var level_one = $("#update_level_one").val();
	var level_two = $("#update_level_two").val();
	var sell_withdraw_poundatge_three = $(
			'#update_sell_withdraw_poundatge_three').val();
		$.ajax({
			url : "/coinproportion/update.do",
			type : "post",
			dataType : "json",
			data : {
				id : id,
				level_one : level_one,
				level_two : level_two,				
			},
			success : function(msg) {
				// if(msg.code == "100"){
				$.alertable.alert(msg.desc);
				$("#box_update").css({
					"display" : "none"
				});
				window.location.reload();
				// }
			}
		});
}
