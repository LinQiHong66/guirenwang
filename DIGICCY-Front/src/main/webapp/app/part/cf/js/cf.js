var app = angular.module('app',['ngRoute']);
app.config(["$routeProvider",function($routeProvider){
    $routeProvider
        .when('/list',{
            templateUrl:"./list.html",
            controller:"mainCtr"
        })
        .when('/xiangqing',{
            templateUrl:"./xiangqing.html",
            controller:"trade"
        })
        .otherwise({
            redirectTo:'/list'
        })
}]);
app.controller("mainCtr",["$scope","$http","$rootScope","postInfo",function (scope,http,rootScope,postInfo){
	rootScope.ip=getIp;
    rootScope.tt=window.sessionStorage.token;
    rootScope.userNo=window.sessionStorage.userNo;
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
    scope.changetb=function(a){
    	if(a!=0){
    		scope.tbshow=true;
    		scope.isSel=true;
    	}else{
    		scope.tbshow=false;
    		scope.isSel=false;
    	}
    }
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
    http({
        url:'../../spread.json',
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
    
//    邀请人数排行榜
    postInfo.dataInfos(rootScope.ip+"/spread/getRankInfo.do",{'token':rootScope.tt})
    .success(function(data){
    	if(data.code=='-1'){
    		scope.phb=false;
    	}else{
    		scope.phb=true;
    	}
        scope.yqrs=data.data;
    }).error(function(data){
        console.log(data);
    });
//  用户邀请码接口
    postInfo.dataInfos(rootScope.ip+"/spread/invite.do",{'userNo':rootScope.userNo,'token':rootScope.tt})
    .success(function(data){
    	if(data.code!='-1'){
    		scope.yqma=data.data[0];
    	}
    }).error(function(data){
        console.log(data);
    });
//  已推荐会员&&收入明细
     postInfo.dataInfos(rootScope.ip+"/spread/getRecUser.do",{'userNo':rootScope.userNo,'token':rootScope.tt})
    .success(function(data){
        scope.myRec=data.data;
        
    }).error(function(data){
        console.log(data);
    });
    
//  收益排行
     postInfo.dataInfos(rootScope.ip+"/spread/getDetailRankInfo.do",{'userNo':rootScope.userNo,'token':rootScope.tt})
    .success(function(data){
//        console.log(data);
        scope.syph=data.data;
        
    }).error(function(data){
        console.log(data);
    });
    
    
//  我的推广
    postInfo.dataInfos(rootScope.ip+"/spread/getMyRecUserInfo.do",{'userNo':rootScope.userNo,'token':rootScope.tt})
    .success(function(data){
    	if(data.code!='-1'){
    		scope.mysp=data.data[0];
    	}  
    }).error(function(data){
        console.log(data);
    });
	scope.webadd='http://'+location.href.split('/')[2]+'/?invit=';
}]);
