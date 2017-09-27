app.controller("voteCtrl",["$scope","$http","$rootScope","$timeout","postInfo","getInfo",function (scope,http,rootScope,timeout,postInfo,getInfo)  {
 scope.xianshi={'display':'none'};
 var tt=window.sessionStorage.token;
   /*渲染*/
	scope.tbxuanran=function () {
		postInfo.dataInfos(rootScope.ip+"/vote/voteInfo.do",{"token":rootScope.tt})
		   .success(function(data){
			   scope.bi=data.data;
			   postInfo.dataInfos(rootScope.ip+"/common/getBasicCoinTypeExceptRMB.do",{'userNo':rootScope.userNo,"token":rootScope.tt})
				   .success(function(data){
					   scope.tbconData=data.coinTypeList;
					   timeout(function () {
						   for(var i=0;i<scope.bi.length;i++){
							   for(var j=0;j<scope.tbconData.length;j++){
								   if(scope.bi[i].coin_type==scope.tbconData[j].coin_no){
									   scope.bi[i].coin_type=scope.tbconData[j].coin_name;
								   };
							   };
						   };
					   },100);
				   })
				   .error(function(data){
					   console.log("查询币种名称报错！")
				   });
		   }).error(function(error){
		   	  console.log("渲染列表报错");
		});
	};
	scope.tbxuanran();
		/*支持*/
		scope.zhichi=function(optionA){
			var coinTypeA="";
			for(var i=0;i<scope.tbconData.length;i++){
				if(optionA==scope.tbconData[i].coin_name){
					coinTypeA=scope.tbconData[i].coin_no;
					postInfo.dataInfos(rootScope.ip+"/vote/voteByCoinType.do",{'userNo':rootScope.userNo,'coinType':coinTypeA,'voteType':1,"token":rootScope.tt})
						.success(function(zhichi){
							scope.xianshi={'dispaly':'block'};
							scope.tanchu=zhichi.desc;
							scope.close=function(){
								scope.xianshi={'display':'none'};
							};
							timeout(function () {
								scope.tbxuanran();
							},100);
						}).error(function(error){
						console.log("新币投票报错");
					});
				};
			};
		};
		/*反对*/
		scope.fandui=function(optionB){
			var coinTypeB="";
			for(var j=0;j<scope.tbconData.length;j++){
				if(optionB==scope.tbconData[j].coin_name){
					coinTypeB=scope.tbconData[j].coin_no;
					postInfo.dataInfos(rootScope.ip+"/vote/voteByCoinType.do",{'userNo':rootScope.userNo,'coinType':coinTypeB,'voteType':2,"token":rootScope.tt})
						.success(function(fandui){
							scope.xianshi={'dispaly':'block'};
							scope.tanchu=fandui.desc;
							scope.close=function(){
								scope.xianshi={'display':'none'};
							};
							timeout(function () {
								scope.tbxuanran();
							},100);
						}).error(function(error){
						console.log("新币投票报错");
					});
				};
			};
		};
	}]);