app.controller("logCtrl",["$scope","$http","$rootScope","postInfo","getInfo",function (scope,http,rootScope,postInfo,getInfo){
        scope.xianshi={'display':'none'}; 
		postInfo.dataInfos(rootScope.ip+"/sub/getSubRecordInfoByUserNoAndCoinType.do",{'userNo':rootScope.userNo,"token":rootScope.tt})
		.success(function(data){
			scope.log=data.data;
			scope.jiedong=function(){
				 
			    postInfo.dataInfos(rootScope.ip+"/sub/thawCoinByUserNoAndCoinType.do",{'userNo':rootScope.userNo,'coinType':this.i.coin_no,'id':this.i.id,"token":rootScope.tt})   
				.success(function(data){
					scope.xianshi={'dispaly':'block'};
					if(data.desc=='success'){
						scope.tanchu='操作成功，请等待解冻';
					}else{
						scope.tanchu=data.desc;
					}
					//scope.tanchu=data.desc;
					scope.close=function(){
						 scope.xianshi={'display':'none'};
						 window.location.reload();
					}

				}).error(function(error){
			        console.log(error);
			        console.log("解冻报错了");
				});
			}

		}).error(function(error){
			console.log(error);
	        console.log("认购记录获取报错了");
		});

	}]);