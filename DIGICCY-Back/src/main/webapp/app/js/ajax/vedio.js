$.ajax({
	url:'/other/getHomeVedio.do',
	type:'post',
	dataType:'json',
	success:function(data){
		if(data.code == 100){
			var url = data.result.url;
			$('#vedio').attr('src',url);
		}
	}
});