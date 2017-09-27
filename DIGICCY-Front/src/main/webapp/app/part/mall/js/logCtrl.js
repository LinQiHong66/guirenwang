mall.controller('logCtrl',['$rootScope','$scope','$http',function(rootscope,scope,http){
	http({
		url:'../../mall.json',
		method:'GET'
	}).success(function(data){
		// 商品筛选
		scope.tit=data.tit;
		scope.fenlei=data.fenlei;
	})
}])