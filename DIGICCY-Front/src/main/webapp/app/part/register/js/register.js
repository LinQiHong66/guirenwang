var reg=angular.module('regApp',['ngRoute']);


reg.config(["$routeProvider",function($routeProvider){
    $routeProvider
        .when('/index',{
            templateUrl:"./view/index.html",
            controller:"indexCtrl"
        }).when('/paypd',{
            templateUrl:"./view/paypd.html",
            controller:"paypdCtrl"
        }).when('/rname',{
        	templateUrl:"./view/realname.html",
        	controller:"rnameCtrl"
        }).when('/final',{
        	templateUrl:"./view/final.html",
        	controller:"finalCtrl"
        }).otherwise({
            redirectTo:'/index'
        })
}]);

reg.controller("mainCtr",["$scope","$http",function (scope,http) {
	   scope.tiaoF=function(){
	        if(!window.sessionStorage['username']){
	            if(!window.sessionStorage['loginflag']){
	                 window.sessionStorage['loginflag']=true;
	            }
	            window.location="../../index.html";
	        }else{
	            window.location="index.html";
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
        url:'../../register.json',
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
