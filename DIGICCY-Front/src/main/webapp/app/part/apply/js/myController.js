	"use strict";
	var app=angular.module("myApp",["ngRoute", "autoActive"]);
	app.constant("Routes",{
	    subs:"/sDefault",
	    log:'/sLog',
	    bonus:'/sBonus',
	    bill:'/sBill',
	    recharge:'/sRecharge',
	    vote:'/sVote'

	   /* trade:"/hTrade",
	    problem:"/hProblem"*/
	});
	app.config(["$routeProvider","Routes",function($routeProvider,Routes){
		$routeProvider.when(Routes.subs,{
      		templateUrl:'view/default.html',
      		controller:"dfCtrl"
      	}).when(Routes.log,{
      		templateUrl:'view/log.html',
      		 controller:"logCtrl"
      	}).when(Routes.bonus,{
      		templateUrl:'view/bonus.html',
      		 controller:"bonusCtrl"
      	}).when(Routes.bill,{
      		templateUrl:'view/bill.html',
      		controller:"billCtrl"
      	}).when(Routes.recharge,{
      		templateUrl:'view/recharge.html',
      		 controller:"rechargeCtrl"
      	}).when(Routes.vote,{
      		templateUrl:'view/vote.html',
      		 controller:"voteCtrl"
      	}).otherwise({
			redirectTo:Routes.subs
		})

	}]);


	app.controller("myController",["$scope","$http","$rootScope","postInfo","getInfo",function (scope,http,rootScope,postInfo,getInfo){

		rootScope.ip=getIp;
		rootScope.tt=window.sessionStorage.token;
		rootScope.userNo=window.sessionStorage.userNo;

		
		if(!window.sessionStorage['username']){
		    if(!window.sessionStorage['loginflag']){
		    	 window.sessionStorage['loginflag']=true;
		    }
	        window.location="../../index.html";
        }
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
	            window.location="index.html";
	        }
	    };

	    http({
		        url:'../../apply.json',
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
