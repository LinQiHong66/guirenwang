app.factory('postInfo',['$http',function(http){
	var factory=function(url,aa){
		return http({
			method:"POST",
			url:url,
			data:aa,
			headers:{'Content-Type':'application/x-www-form-urlencoded'},
	        transformRequest: function (data) {
	        	return $.param(data)
	        }
        })
	}
	return{
		dataInfos:function(url,aa){
			return factory(url,aa)
		}
	}
}])