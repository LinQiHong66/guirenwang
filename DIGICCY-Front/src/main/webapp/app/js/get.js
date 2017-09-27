app.factory('getInfo',['$http',function(http){
	var factory=function(url,aa){
		return http({
			method:"GET",
			url:url,
			params:aa  })
	}
	return{
		dataInfos:function(url,aa){
			return factory(url,aa)
		}
	}
}])