
app.controller("dfCtrl",["$scope","$http","$rootScope","$timeout","postInfo","getInfo",function (scope,http,rootScope,timeout,postInfo,getInfo){
		
	//认购中心
    postInfo.dataInfos(rootScope.ip+"/sub/getSubRecord.do",{"token":rootScope.tt})   
	.success(function(data){
		scope.dataList=data.data;
	}).error(function(error){
       console.log("认购中心报错了")
	});

    /*查询资产*/
  	scope.chazichang=function () {
		postInfo.dataInfos(rootScope.ip+"/sub/getUserBalance.do",{userNo:rootScope.userNo,coinType:0,"token":rootScope.tt})
			.success(function(data){
				scope.money=data.data;
			})
			.error(function(data){
				console.log("认购中心的用户资产查询报错")
			});
	};
	scope.chazichang();
    /*立即抢购*/
   
    scope.reave=function(num){
		postInfo.dataInfos(rootScope.ip+"/sub/getSubRecord.do",{"token":rootScope.tt})   
		.success(function(data){
			scope.opposite=data.data[num];
			postInfo.dataInfos(rootScope.ip+"/sub/getSubRecord.do",{"token":rootScope.tt})
			.success(function(data){
				scope.tphoto=data.data[num].photo;
			}).error(function(error){
			console.log("认购中心报错了")
			});
			scope.coinType=scope.opposite.coin_type;
			postInfo.dataInfos(rootScope.ip+"/sub/getSubRecordInfoByCoinType.do",{coinType:scope.coinType,"token":rootScope.tt})   
			.success(function(data){
				scope.reaveData=data.data[0];
				scope.buyNum="";
				scope.buyPass="";
				scope.buyCoinType=scope.reaveData.coin_type;
				scope.powerIn=true;
				scope.close=function(){
					scope.powerIn=false;
				}
				scope.tsflag={'display':'none'};
				/*弹出框里的立即抢购*/
				scope.buyCoin=function(){
					scope.tsflag={'display':'block'};
					scope.closep=function(){
							scope.tsflag={'display':'none'};
					}
					postInfo.dataInfos(rootScope.ip+"/sub/buyCoinByUserNo.do",{userNo:scope.userNo,
							buyNum:scope.buyNum,
							dealPwd:scope.buyPass,
							coinType:scope.buyCoinType,"token":rootScope.tt}) 
					.success(function(data){
						 scope.tishi6=data.desc;
						timeout(function () {
							scope.chazichang();
						},1000);
						if(data.desc==='success'){
							timeout(function () {
							    scope.powerIn=false;
						    },1000);
						}
					})
					.error(function(error){
						 scope.tishi6='请输入数量和密码';
						console.log(error);
					});
				};
			})
			.error(function(data){
				console.log("认购中心的立即抢购报错2")
			});
		})
		.error(function(data){
			console.log("认购中心的立即抢购报错1")
		});
	}
	}]);