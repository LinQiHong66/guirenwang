"use strict";
var app=angular.module("myApp",["ngRoute", "autoActive"]);
app.constant("Routes",{
	    safe:"/sDefault",
	    auth:'/sAuthentication',
	    pd:'/sPassword',
	    pws:'/sPaypassword',
	    sdbt:'/sDbtest',
	    bp:'/sBindphone',
	    ali:'/sAlipay',
	    ppw:'/sPwset',
	    bank:'/sBank',
	    swl:'/sWallet',
	    add:'/sAddress',
	    log:'/sLog'
	   /* trade:"/hTrade",
	    problem:"/hProblem"*/
});
app.config(["$routeProvider","Routes",function($routeProvider,Routes){
		$routeProvider.when(Routes.safe,{
      		templateUrl:'view/default.html',
      		controller:"dfCtrl"
      	}).when(Routes.auth,{
      		templateUrl:'view/authentication.html',
      		controller:"authCtrl"
      	}).when(Routes.pd,{
      		templateUrl:'view/password.html',
      		controller:'pwCtrl'
      	}).when(Routes.pws,{
      		templateUrl:'view/pwset.html',
      		controller:'pdsCtrl'
      	}).when(Routes.sdbt,{
      		templateUrl:'view/dbtest.html',
      		controller:'dbtCtrl'
      	}).when(Routes.bp,{
      		templateUrl:'view/bindphone.html',
      		controller:'bpCtrl'
      	}).when(Routes.ali,{
      		templateUrl:'view/alipay.html',
      		controller:'aliCtrl'
      	}).when(Routes.ppw,{
      		templateUrl:'view/paypassword.html',
      		controller:'ppwCtrl'
      	}).when(Routes.bank,{
      		templateUrl:'view/bank.html',
      		controller:'bkCtrl'
      	}).when(Routes.swl,{
      		templateUrl:'view/wallet.html',
      		controller:'wlCtrl'
      	}).when(Routes.add,{
      		templateUrl:'view/address.html',
      		controller:'adCtrl'
      	}).when(Routes.log,{
      		templateUrl:'view/log.html',
      		controller:'logCtrl'
      	}).otherwise({
			redirectTo:Routes.safe
		})
	}]);
app.controller("myController",["$scope","$http","$rootScope","postInfo","getInfo",function (scope,http,rootScope,postInfo,getInfo) {
    rootScope.ip=getIp;
    rootScope.tt=window.sessionStorage.token;
    rootScope.userNo=window.sessionStorage.userNo;
    rootScope.id=window.sessionStorage.id;
    rootScope.phone=window.sessionStorage.phone;
    
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
            window.location="index.html";
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
	    http({
		        url:'../../safe.json',
		        method:'GET'
    		})
	    .success(function(data){
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
	        // 二级菜单数据
	        scope.safeNav1=data.safeNav.menu[0];
	        scope.safeNav2=data.safeNav.menu[1];
	        scope.safeNav3=data.safeNav.menu[2];
	        scope.safeNav4=data.safeNav.menu[3];
	        scope.safeNav5=data.safeNav.menu[4];
	        scope.safeNav6=data.safeNav.menu[5];
	        scope.safeNav7=data.safeNav.menu[6];
	        scope.safeNav8=data.safeNav.menu[7];
	        scope.safeNav9=data.safeNav.menu[8];
	        scope.safeNav10=data.safeNav.menu[9];
	        scope.safeNav11=data.safeNav.menu[10];
	        scope.safeNav12=data.safeNav.menu[11];
	    }).error(function(data){
	        	console.log(data);
	    });
}]);
