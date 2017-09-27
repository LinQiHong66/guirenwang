app.controller("billCtrl",["$scope","$http","$timeout","$rootScope","postInfo","getInfo",function (scope,http,timeout,rootScope,postInfo,getInfo) {
		
	 //分红记录
    postInfo.dataInfos(rootScope.ip+"/bonus/getBonusRecord.do",{'userNo':rootScope.userNo,'token':rootScope.tt})
	.success(function(data){
		scope.fenhonglog=data.data;
		scope.bonus_type=data.data?data.data.bonus_type:null;
		postInfo.dataInfos(rootScope.ip+"/common/getBasicCoinTypeExceptRMB.do",{'userNo':rootScope.userNo,"token":rootScope.tt})
			.success(function(data){
				scope.tbconData=data.coinTypeList;
				timeout(function () {
					try{
						for(var i=0;i<scope.fenhonglog.length;i++){
							for(var j=0;j<scope.tbconData.length;j++){
								if(scope.fenhonglog[i].bonus_type==scope.tbconData[j].coin_no){
									scope.fenhonglog[i].bonus_type=scope.tbconData[j].coin_name;
								};
								if(scope.fenhonglog[i].reward_type==scope.tbconData[j].coin_no){
									scope.fenhonglog[i].reward_type=scope.tbconData[j].coin_name;
								}
							};
						};
					}catch(err){
						console.log(err);
					}
				},100);
			})
			.error(function(data){
				console.log("查询币种名称报错！")
			});
	}).error(function(error){
        console.log("分红记录报错");
	});

}]);
