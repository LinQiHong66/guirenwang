app.controller("mainCtrl",["$scope","$http","$rootScope","postInfo","getInfo",function (scope,http,rootScope,postInfo,getInfo){

    scope.tiaoF=function(){
        if(!window.sessionStorage['username']){
            if(!window.sessionStorage['loginflag']){
                 window.sessionStorage['loginflag']=true;
            }
            window.location="../../index.html";
        }else{
            window.location="../finance/index.html";
        }
    };
    scope.tiaoS=function(){
        if(!window.sessionStorage['username']){
            if(!window.sessionStorage['loginflag']){
                 window.sessionStorage['loginflag']=true;
            }
            window.location="../../index.html";
        }else{
            window.location="../safe/index.html";
        }
    };
    scope.tiaoA=function(){
        if(!window.sessionStorage['username']){
            if(!window.sessionStorage['loginflag']){
                 window.sessionStorage['loginflag']=true;
            }
            window.location="../../index.html";
        }else{
            window.location="../apply/index.html";
        }
    };
    scope.dlshow=function(){
 	    window.location="../../index.html";
 	    scope.loginflag=true;
       
    }
    scope.outlogin=function(){
        scope["loginh"]={display:"none"};
        scope["loginq"]={display:"block"};
        window.sessionStorage.clear();
   };
    if(window.sessionStorage['username']){  
        scope["loginh"]={display:"block"};
        scope["loginq"]={display:"none"};
         scope.loginInfo=window.sessionStorage;
     
     }else{  
         scope["loginh"]={display:"none"};
         scope["loginq"]={display:"block"};
         
     };
    rootScope.ip=getIp;
    rootScope.tt=window.sessionStorage.token;
    rootScope.userNo=window.sessionStorage.userNo;
    rootScope.username=window.sessionStorage.username;
    rootScope.id=window.sessionStorage.id;
    http({
        url:'../../transation.json',
        method:'GET'
    }).success(function(data){
        scope.logo = data.header.logo;
        scope.title = data.header.biaoti;
        scope.home = data.header.title[0];
        scope.transaction = data.header.title[1];
        scope.finance = data.header.title[2];
        scope.safe = data.header.title[3];
        scope.apply = data.header.title[4];
        scope.shop = data.header.title[5];
        scope.help = data.header.title[6];
        scope.spread = data.header.title[7];
        scope.jiedai=data.header.title[8];


    }).error(function(data){
        console.log(data);
    });


     scope.bitype=[];
    // common/getBasicCoinType.do  带人民币
    //比特币  10  美元 20   
    //货币类型

        postInfo.dataInfos(rootScope.ip+"/common/getBasicCoinTypeExceptRMB.do",{'token':rootScope.tt})
        .success(function(data){
            scope.bitype=data.coinTypeList;
            scope.nav=data.coinTypeList;
            scope.fisrt=data.coinTypeList[0].coin_no?data.coinTypeList[0].coin_no:0;
            
            //添加币种的图片
//            postInfo.dataInfos(rootScope.ip+"/sub/getSubRecord.do",{"token":rootScope.tt})
//            .success(function(data){
//                scope.tphotoc=data.data;
//                console.log(data);
//                for(var i=0;i<scope.tphotoc.length;i++){
//            		if(scope.tphotoc[i].coin_type==scope.fisrt){
//                        scope.tphotoe=scope.tphotoc[i].photo;
//                        return false;
//                	}
//                }
//            }).error(function(error){
//            	console.log("财务中心的图片报错了")
//            });
            
        })
        .error(function(error){
            console.log("select币种数据获取错误"+error);
        });

    //coinType<Integer> : 货币类型
    //每个币的信息 
    rootScope.coinType='10';
    postInfo.dataInfos(rootScope.ip+"/dayMarket/selectDayMarket.do",{'coinType':rootScope.coinType,'token':rootScope.tt})
        .success(function(data){
            scope.day=data.dayMarketList[0];
           
            postInfo.dataInfos(rootScope.ip+"/common/getBasicCoinTypeExceptRMB.do",{'token':rootScope.tt})
            .success(function(data){
                scope.biname=data.coinTypeList[0].coin_name;
                scope.biimg=data.coinTypeList[0].icon;
            })
            .error(function(error){
                console.log("select币种数据获取错误"+error);
            });
        })
        .error(function(error){
            console.log("select数据获取错误"+error);
        });
    scope.cdIndex=0;
    
//    切换币种
    scope.changecd=function(conin){
        scope.cdIndex=this.$index;
        rootScope.coinType=scope.bitype[this.$index].coin_no;
        scope.biname=scope.bitype[this.$index].coin_name;
        postInfo.dataInfos(rootScope.ip+"/dayMarket/selectDayMarket.do",{'coinType':rootScope.coinType,'token':rootScope.tt})
        .success(function(data){
            scope.day=data.dayMarketList?data.dayMarketList[0]:'';
            
//            获取币种图片
            postInfo.dataInfos(rootScope.ip+"/common/getBasicCoinTypeExceptRMB.do",{'token':rootScope.tt})
            .success(function(data){
//                scope.biname=data.coinTypeList[0].coin_name;
                scope.biimg=data.coinTypeList[scope.cdIndex].icon;
            })
            .error(function(error){
                console.log("select币种数据获取错误"+error);
            });
            //添加币种图片
//            postInfo.dataInfos(rootScope.ip+"/sub/getSubRecord.do",{"token":rootScope.tt})
//            .success(function(data){
//                scope.tphotoc=data.data;
//                for(var i=0;i<scope.tphotoc.length;i++){
//                    if(scope.tphotoc[i].coin_type==conin){
//                        scope.tphotoe=scope.tphotoc[i].photo;
//                        return false;
//                    }
//                }
//            }).error(function(error){
//            	console.log("财务中心的图片报错了")
//            });
        })
        .error(function(error){
            console.log("数据获取错误"+error);
        });
    };
}]);