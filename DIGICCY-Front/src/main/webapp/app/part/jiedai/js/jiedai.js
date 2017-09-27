var app = angular.module('app',[]);
app.controller("mainCtr",["$scope","$http","$rootScope","postInfo",function (scope,http,rootScope,postInfo){
    rootScope.ip="http://localhost";
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
        scope.jiedai = data.header.title[8];
    }).error(function(data){
        console.log(data);
    });

	
}]);
