app.controller("entrust",["$scope","$http","$rootScope","postInfo","getInfo",function (scope,http,rootScope,postInfo,getInfo){

   scope.buybuy=['人民币','比特币','美元','迪拉姆'];
    http({
        url:'../../finance.json',
        method:'GET'
    }).success(function(data){
        scope.enTitle=data.entrust.enTitle;
        scope.enHeader=data.entrust.enHeader;

    }).error(function(error){
        console.log(error);
    });

    scope.entrustCoin=10; 
    scope.entrustType=0;
    scope.state=0;  
    scope.buybuy=[];

    // common/getBasicCoinType.do  带人民币
    //比特币  10  美元 20   迪拉姆 30 嘿嘿嘿 40
    //货币类型
    postInfo.dataInfos(rootScope.ip+"/common/getBasicCoinTypeExceptRMB.do",{'token':rootScope.tt})
    .success(function(data){
        scope.bitype=data.coinTypeList;
        scope.entrustCoin=10;
        scope.buybuy=data.coinTypeList;
        scope.biarr={};
        for(var i=0;i<scope.buybuy.length;i++){
        	scope.biarr['a'+scope.buybuy[i].coin_no]=scope.buybuy[i].coin_name;
        }
    })
    .error(function(error){
        console.log("select数据获取错误"+error);
    });
    //entrustType: 委托类型  0：买 1：卖  2:全部
    //entrustCoin:  委托币种 10,20,30,40
    // state: 状态 0:委托中 1：已完成 2：撤销 3：全部
    scope.zhuangtai=['撤销','已完成','已撤销','全部'];
    scope.maimai=['买入','卖出','全部'];

    postInfo.dataInfos(rootScope.ip+"/entrustDeal/getEntrustManageList.do",{'userNo':rootScope.userNo,'entrustType':scope.entrustType,'entrustCoin':scope.entrustCoin,'state':scope.state,'token':rootScope.tt})
    .success(function(data){
        scope.enUl=data.EntrustList;
        /*添加图片*/
        postInfo.dataInfos(rootScope.ip+"/sub/getSubRecord.do",{"token":rootScope.tt})
            .success(function(data){
                scope.tphoto=data.data;
                scope.tphotoa={};
//                获取图片地址
                for(var i=0;i<scope.tphoto.length;i++){
                	scope.tphotoa['b'+scope.tphoto[i].coin_type]=scope.tphoto[i].photo;
                }
            }).error(function(error){
            console.log("财务中心的图片报错了")
        });
        
    })
    .error(function(error){
        console.log("展示数据获取错误"+error);
    });

    scope.changebtn=function(a){
    	console.log(a);
    	console.log(rootScope.userNo);
    	postInfo.dataInfos(rootScope.ip+"//trade/delEntrust.do",{
    		"token":rootScope.tt,
    		'userNo':rootScope.userNo,
    		'id':scope.enUl[a].id
    		})
        .success(function(data){
            console.log(data);
            postInfo.dataInfos(rootScope.ip+"/entrustDeal/getEntrustManageList.do",{'userNo':rootScope.userNo,'entrustType':scope.entrustType,'entrustCoin':scope.entrustCoin,'state':scope.state,'token':rootScope.tt})
            .success(function(data){
                scope.enUl=data.EntrustList;
                console.log(data);
                /*添加图片*/
//                postInfo.dataInfos(rootScope.ip+"/sub/getSubRecord.do",{"token":rootScope.tt})
//                    .success(function(data){
//                        scope.tphoto=data.data;
//                        for(var i=0;i<scope.tphoto.length;i++){
//                            for(var j=0;j<scope.enUl.length;j++){
//                                if(scope.tphoto[i].entrust_coin==scope.enUl[j].coin_type){
//                                    scope.tphotoa=scope.tphoto[i].photo;
//                                   return false;
//                                }
//                            }
//                        }
//                    }).error(function(error){
//                    console.log("财务中心的图片报错了")
//                });
                
            })
            .error(function(error){
                console.log("展示数据获取错误"+error);
            });
        }).error(function(error){
	        console.log(error)
	    });
    }
    scope.changeCoin=function(entrustCoin){
        console.log(entrustCoin)
        postInfo.dataInfos(rootScope.ip+"/entrustDeal/getEntrustManageList.do",{'userNo':rootScope.userNo,'entrustType':scope.entrustType,'entrustCoin':entrustCoin,'state':scope.state,'token':rootScope.tt})
        .success(function(data){
             scope.enUl=data.EntrustList;
//           请求币种信息--获取图片
             postInfo.dataInfos(rootScope.ip+"/common/getAllCoinInfo.do",{"token":rootScope.tt})
             .success(function(data){
             	scope.tphotoa={};
     			for(var i=0;i<data.coinList.length;i++){
     				scope.tphotoa['b'+data.coinList[i].coin_no]=data.coinList[i].icon;
     			}
     			console.log(scope.tphotoa);
     		}).error(function(data){
     			console.log(data);
     		})
        
        }).error(function(error){
            console.log(error);
        }); 
    };
    
    scope.changeType=function(entrustType){
        postInfo.dataInfos(rootScope.ip+"/entrustDeal/getEntrustManageList.do",{'userNo':rootScope.userNo,'entrustType':scope.entrustType,'entrustCoin':scope.entrustCoin,'state':scope.state,'token':rootScope.tt})
        .success(function(data){
                 scope.enUl=data.EntrustList;

        }).error(function(){
            console.log(data);
        });
        console.log(entrustType);
    };

    scope.changeState=function(state){
        postInfo.dataInfos(rootScope.ip+"/entrustDeal/getEntrustManageList.do",{'userNo':rootScope.userNo,'entrustType':scope.entrustType,'entrustCoin':scope.entrustCoin,'state':state,'token':rootScope.tt})
        .success(function(data){
            scope.enUl=data.EntrustList;

        }).error(function(){
            console.log(data);
        });
    };
}]);