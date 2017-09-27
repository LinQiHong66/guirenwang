app.controller("query",["$scope","$http","$rootScope","postInfo","getInfo",function (scope,http,rootScope,postInfo,getInfo){
     http({
        url:'../../finance.json',
        method:'GET'
    }).success(function(data){
        scope.qTitle=data.query.qTitle;
        scope.qHeader=data.query.qHeader;
        scope.cx=1;

    }).error(function(error){
        console.log(error);
    });

    scope.buybuy=[];
    scope.cx=1;
    scope.bi=10;
    // common/getBasicCoinType.do  带人民币
    //比特币  10  美元 20   迪拉姆 30 嘿嘿嘿 40
    //货币类型
    postInfo.dataInfos(rootScope.ip+"/common/getBasicCoinTypeExceptRMB.do",{'token':rootScope.tt})
    .success(function(data){
        scope.bitype=data.coinTypeList;
        scope.bi=10;
        scope.buybuy=data.coinTypeList;
        scope.biarr={};
        //匹配币名
        for(var i=0;i<scope.buybuy.length;i++){
        	scope.biarr['a'+scope.buybuy[i].coin_no]=scope.buybuy[i].coin_name;
        }
//      请求币种信息--获取图片
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
        console.log("select数据获取错误"+error);
    });


    //dealType:  交易类型(0:买,1:卖)
    //coinNo:  : 货币类型
    scope.maimai=['买入','卖出','全部'];
    postInfo.dataInfos(rootScope.ip+"/entrustDeal/getEntrustDealList.do",{'userNo':rootScope.userNo,'dealType':scope.cx,'coinNo':scope.bi,'token':rootScope.tt})
    .success(function(data){
        scope.qUl=data.data;
    })
    .error(function(error){
        console.log("数据获取错误"+error);
    });

    scope.biChange=function(bi){
        postInfo.dataInfos(rootScope.ip+"/entrustDeal/getEntrustDealList.do",{'userNo':rootScope.userNo,'dealType':scope.cx,'coinNo':bi,'token':rootScope.tt})
        .success(function(data){
            scope.qUl=data.data;
        })
        .error(function(error){
            console.log(error);
        });
    };
    postInfo.dataInfos(rootScope.ip+"/sub/getSubRecord.do",{"token":rootScope.tt})
	    .success(function(data){
	        scope.tphoto=data.data;
//	        scope.tphotoa={};
//	//                获取图片地址
//	        for(var i=0;i<scope.tphoto.length;i++){
//	        	scope.tphotoa['b'+scope.tphoto[i].coin_type]=scope.tphoto[i].photo;
//	        }
	        
	    }).error(function(error){
	    console.log("财务中心的图片报错了")
	});
    scope.cxChange=function(cx){
		postInfo.dataInfos(rootScope.ip+"/entrustDeal/getEntrustDealList.do",{'userNo':rootScope.userNo,'dealType':cx,'coinNo':scope.bi,'token':rootScope.tt})
        .success(function(data){
            scope.qUl=data.data;
           
        })
        .error(function(error){
            console.log(error);
        });    
    };
}]);