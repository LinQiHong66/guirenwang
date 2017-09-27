app.controller("logCtrl",["$scope","$http","$rootScope","postInfo","getInfo",function (scope,http,rootScope,postInfo,getInfo){
	http({
		url:'../../safe.json',
		method:'GET'
	}).success(function(data){
		scope.tit=data.log.tit;
		scope.tbtit=data.log.tbtit;
	});
	postInfo.dataInfos(rootScope.ip+"/user/getLoginLogInfo.do",{userNo: rootScope.id,'token':rootScope.tt})
	.success(function(data){
		scope.tbdata=data.data;
	})
	.error(function(error){
		console.log(error)
	})
	
}]);