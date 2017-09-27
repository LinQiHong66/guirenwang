app.factory('postInfo',['$http',function(http){
	var factory=function(url,aa){
		return http({
			method:"POST",
			url:url,
			data:aa,
			headers:{'Content-Type':'application/x-www-form-urlencoded'},    
            transformRequest: function(obj) {    
                var str = [];    
                for (var p in obj) {    
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));    
                }    
                return str.join("&");    
            }
        })
	}
	return{
		dataInfos:function(url,aa){
			return factory(url,aa)
		}
	}
}])