
app.controller("bonusCtrl",["$scope","$http","$timeout","$rootScope","postInfo","getInfo",function (scope,http,timeout,rootScope,postInfo,getInfo) {

	postInfo.dataInfos(rootScope.ip+"/bonus/getBonusInfo.do",{'userNo':rootScope.userNo,"token":rootScope.tt})   
	.success(function(data){
		scope.fenhong=data.data;
		postInfo.dataInfos(rootScope.ip+"/common/getBasicCoinTypeExceptRMB.do",{'userNo':rootScope.userNo,"token":rootScope.tt})
			.success(function(data){
				scope.tbconData=data.coinTypeList;
				timeout(function () {
					for(var i=0;i<scope.fenhong.length;i++){
						for(var j=0;j<scope.tbconData.length;j++){
							if(scope.fenhong[i].coin_type==scope.tbconData[j].coin_no){
								scope.fenhong[i].coin_type=scope.tbconData[j].coin_name;
							};
						};
					};
				},100);
			})
			.error(function(data){
				console.log("查询币种名称报错！")
			});
	})
	.error(function(error){
        console.log("1.1错误");
	});

}]);