app.controller("core",["$scope","$http","$rootScope","postInfo","getInfo",function (scope,http,rootScope,postInfo,getInfo){
    http({
        url:'../../finance.json',
        method:'GET'
    }).success(function(data){
        scope.htitle=data.core.ctitle;
        scope.cc=data.core.cinfo;
        
        scope.titl= data.core.ctitl;

    }).error(function(){
        console.log(data);
    });
     scope.buybuy=[];
    //比特币  10  美元 20   
    //货币类型
    postInfo.dataInfos(rootScope.ip+"/common/getBasicCoinType.do ",{'token':rootScope.tt})
    .success(function(data){
        scope.buybuy=data.coinTypeList;
    })
    .error(function(error){
        console.log("select数据获取错误"+error);
    });

    /*人民币资产查询*/
    postInfo.dataInfos(rootScope.ip+"/trade/getUserBalanceInfo.do",{'userNo':rootScope.userNo,'coinType':'0','token':rootScope.tt})
   .success(function (data) {
      if(data.balanceInfo){
    	  scope.rmb=data.balanceInfo.enable_coin;
          scope.freezeRmb=data.balanceInfo.unable_coin;
          scope.zongzichan=data.balanceInfo.enable_coin+data.balanceInfo.unable_coin;
      }else{
    	  scope.rmb=0;
          scope.freezeRmb=0;
          scope.zongzichan=0;
      }
   }).error(function(error){
       console.log("select数据获取错误"+error);
   });
    
    postInfo.dataInfos(rootScope.ip+"/balance/getBanlance.do",{'userNo':rootScope.userNo,'token':rootScope.tt})
    .success(function(data){
        scope.info =data.data;
        scope.biarr={};
        //匹配币名
        for(var i=0;i<scope.buybuy.length;i++){
        	scope.biarr['a'+scope.buybuy[i].coin_no]=scope.buybuy[i].coin_name;
        }
//        请求币种信息--获取图片
        postInfo.dataInfos(rootScope.ip+"/common/getAllCoinInfo.do",{"token":rootScope.tt})
        .success(function(data){
        	scope.tphotoa={};
			for(var i=0;i<data.coinList.length;i++){
				scope.tphotoa['b'+data.coinList[i].coin_no]=data.coinList[i].icon;
			}
		}).error(function(data){
			console.log(data);
		})

    })
    .error(function(error){
        console.log(error);
    });

    scope.ellipsis=function(a){
    	if(a==null||a==undefined){
    		return null;
    	}else{
    		if(a.length>9){
        		return (a.substr(0, 5)+'*****'+a.substr(-4));
        	}else{
        		return a;
        	}
    	}
    }
}]);