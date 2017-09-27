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
	var no = $("#coin_no").val();
	var name = $("#coin_name").val();
	var code = $("#coin_code").val();
	var address = $("#coin_address").val();
	var host = $("#wallet_host").val();
	var post = $("#wallet_post").val();
	var wallet_name = $("#wallet_username").val();
	var wallet_pwd = $("#wallet_password").val();
	var wallet_lockpwd = $("#wallet_lock").val();
	var buy_poundatge = $("#buy_poundatge").val();
	var sell_poundatge = $("#sell_poundatge").val();
	var sell_withdraw_poundatge_one = $('#sell_withdraw_poundatge_one').val();
	var sell_withdraw_poundatge_twe = $('#sell_withdraw_poundatge_twe').val();
	var sell_withdraw_poundatge_three = $('#sell_withdraw_poundatge_three')
			.val();
	var block = $("#block").val();
	if (sell_withdraw_poundatge_one == null
			|| sell_withdraw_poundatge_twe == null
			|| sell_withdraw_poundatge_three == null
			|| sell_withdraw_poundatge_three == ""
			|| sell_withdraw_poundatge_twe == ""
			|| sell_withdraw_poundatge_one == "") {
		alert("提现手续费不能为空");
		return;
	} else {
		$.ajax({
			url : "/coin/addCoin.do",
			type : "post",
			dataType : "json",
			data : {
				no : no,
				name : name,
				code : code,
				address : address,
				host : host,
				post : post,
				wallet_name : wallet_name,
				wallet_pwd : wallet_pwd,
				wallet_lockpwd : wallet_lockpwd,
				buy_poundatge : buy_poundatge,
				sell_poundatge : sell_poundatge,
				block : block,
				sell_withdraw_poundatge_one : sell_withdraw_poundatge_one,
				sell_withdraw_poundatge_twe : sell_withdraw_poundatge_twe,
				sell_withdraw_poundatge_three : sell_withdraw_poundatge_three
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

}
function updateCoin() {
	// TODO
	var no = $("#update_no").val();
	var name = $("#update_name").val();
	var code = $("#update_code").val();
	var address = $("#update_address").val();
	var host = $("#update_host").val();
	var post = $("#update_post").val();
	var wallet_name = $("#update_wallet_name").val();
	var wallet_pwd = $("#update_wallet_pwd").val();
	var wallet_lockpwd = $("#update_wallet_lockpwd").val();
	var buy_poundatge = $("#update_buy_poundatge").val();
	var sell_poundatge = $("#update_sell_poundatge").val();
	var block = $("#update_block").val();
	var sell_withdraw_poundatge_one = $('#update_sell_withdraw_poundatge_one')
			.val();
	var sell_withdraw_poundatge_twe = $('#update_sell_withdraw_poundatge_twe')
			.val();
	var sell_withdraw_poundatge_three = $(
			'#update_sell_withdraw_poundatge_three').val();
	if (sell_withdraw_poundatge_one == null
			|| sell_withdraw_poundatge_twe == null
			|| sell_withdraw_poundatge_three == null
			|| sell_withdraw_poundatge_three == ""
			|| sell_withdraw_poundatge_twe == ""
			|| sell_withdraw_poundatge_one == "") {
		alert("提现手续费不能为空");
		return;
	} else {
		$.ajax({
			url : "/coin/updateCoin.do",
			type : "post",
			dataType : "json",
			data : {
				no : no,
				name : name,
				code : code,
				address : address,
				host : host,
				post : post,
				wallet_name : wallet_name,
				wallet_pwd : wallet_pwd,
				wallet_lockpwd : wallet_lockpwd,
				buy_poundatge : buy_poundatge,
				sell_poundatge : sell_poundatge,
				block : block,
				sell_withdraw_poundatge_one : sell_withdraw_poundatge_one,
				sell_withdraw_poundatge_twe : sell_withdraw_poundatge_twe,
				sell_withdraw_poundatge_three : sell_withdraw_poundatge_three
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
}
