$.ajax({
	url:'/param/getBackInfo.do',
	type:'POST',
	dataType:'json',
	success:function(data){
		if(data.code == 100){
			$('#itemid').attr("value", data.result.id);
			$('#name').attr("value",data.result.bankUserName);
			$('#cardId').attr("value", data.result.bankCardId);
			$('#backName').attr("value", data.result.bankName);
			$('#remark').attr("value",data.result.remark);
		}else{
			alert("获取失败");
		}
	}
});

function savebackInfo(){
	var id = $('#itemid').val();
	var name = $('#name').val();
	var cardId = $('#cardId').val();
	var backName = $('#backName').val();
	var remark = $('#remark').val();
	$.ajax({
		url:'/param/modifyBankInfo.do',
		type:'POST',
		dataType:'json',
		data:{
			id:id,
			name:name,
			cardId:cardId,
			backName:backName,
			remark:remark
		},
		success:function(data){
			if(data.code == 100){
				alert('更新成功');
			}else{
				alert('更新失败');
			}
			window.location.href='/param/backInfo.do';
		}
	});
}