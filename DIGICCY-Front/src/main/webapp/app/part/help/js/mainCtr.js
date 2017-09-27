app.controller("mainCtr",["$scope","$http","$rootScope","postInfo",function (scope,http,rootScope,postInfo){

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
    http({
        url:'../../help.json',
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
        scope.jiedai = data.header.title[8];
        scope.h_public=data.nav[0];
        scope.h_trade=data.nav[1];
        scope.h_problem=data.nav[2];

    }).error(function(data){
        console.log(data);
    });

}]);