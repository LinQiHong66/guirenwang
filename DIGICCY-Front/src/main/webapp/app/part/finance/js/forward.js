 app.controller("forward",["$scope","$http","$rootScope","postInfo","getInfo",'$interval',function (scope,http,rootScope,postInfo,getInfo,interval){
     http({
        url:'../../finance.json',
        method:'GET'
    }).success(function(data){
        scope.fwTitle=data.forward.fwTitle;
        scope.fHeader=data.forward.fHeader;
        scope.fwUlHeader=data.forward.fwUlHeader;

    }).error(function(){
        console.log(data);
    });

    scope.bibi=[];
    scope.fw=10;
    //获取币种
    postInfo.dataInfos(rootScope.ip+"/common/getBasicCoinTypeExceptRMB.do",{'token':rootScope.tt})
    .success(function(data){
        scope.fwtype=data.coinTypeList;
        scope.bibi=data.coinTypeList;
        scope.fw=10;
    }).error(function(error){
        console.log("select数据获取错误"+error);
    });
  //手续费率
    postInfo.dataInfos(rootScope.ip+"/trade/getPiundatgeRate.do",{'param':'poundageRate','token':rootScope.tt})
     .success(function(data){
         scope.fei=data.StaticParams.value;
     }).error(function(error){
         console.log(error);
     });
    /*美元的资产*/
    postInfo.dataInfos(rootScope.ip+"/virtual/getUserBalanceAndAddress.do",{'userNo':rootScope.userNo,'coinNo':20,'token':rootScope.tt})
    .success(function (data) {
      scope.money=data.data[0]?data.data[0].enable_coin:0;
    })
    .error(function(error){
        console.log(error);
    });
    scope.biChange=function(fw){
        /*币种资产钱*/
        postInfo.dataInfos(rootScope.ip+"/virtual/getUserBalanceAndAddress.do",{'userNo':rootScope.userNo,'coinNo':scope.fw,'token':rootScope.tt})
        .success(function (data) {
	      	if(data.data.length==0){
	      		scope.money=0;
	      	}else{
	      		scope.money=data.data[0].enable_coin;
	      	}
        })
        .error(function(error){
            console.log(error);
        });
    }
    scope.yanzhenma='获取验证码';
    if(rootScope.phone){
    	scope.phone=rootScope.phone;
        scope.tishi='接收的手机号:';
        $('.btna').attr('disabled','false');
    }else{
    	 $('.btna').attr('disabled','true');
    	scope.tishi='去绑定手机号';
    	scope.phone='';
    	scope.goa=function(){
    		window.location='../safe/index.html#/sBindphone';
    	}
    }
     var cont=60;
     scope.hqyzm=function(){
         //充值发送验证码  mobile<String> : 手机号  
         postInfo.dataInfos(rootScope.ip+"/virtualWith/sendCodeCode.do",{'mobile':rootScope.phone,'token':rootScope.tt})   
         .success(function(data){
             $('.btna').attr('disabled','true');
             scope.yanzhenma=cont+'s后重新获取';
             var timer=interval(function () {
                 cont--;
                 scope.yanzhenma=cont+"秒后重新获取";
                 if(cont==0){
                     interval.cancel(timer);
                     scope.yanzhenma='重新获取';
                     $('.btna').attr('disabled','false');
                     cont=10;
                 }
             },1000);
         })
         .error(function(error){
             console.log(error);
         });
     }
     scope.tsbox={'display':'none'};
     scope.guan=function(){
    	 scope.tsbox={'display':'none'};
     }
   
    //确认转账
    scope.dianji=function(){
        /*  ·userNo<String> : 用户编号
           ·coinType<String> : 币种编号
       ·tranuser<String> : 转账用户编号
       ·coinNun<String> : 转账数目
       ·code<String> : 验证码
       ·dealPwd<String> : 币种编号
       ·mobile<String> : 手机号码*/
	  if(scope.uid,scope.unum,scope.codea,scope.pswd){
		//判断验证码
	        postInfo.dataInfos(rootScope.ip+"/reg/compare.do",{'mobile':rootScope.phone,'code':scope.codea,'token':rootScope.tt})   
	        .success(function(data){
	           console.log(data);
	          if(data.desc=='fail'){
	        	   scope.tsbox={'display':'block'};
			       scope.tishia='验证码错误！';
	           }else{
	        	   //确认转账
	        	   postInfo.dataInfos(rootScope.ip+"/transfer/confirmTransfer.do",
			         		{'userNo':rootScope.userNo,
			         	     'coinType':scope.fw,
			         	     'tranuser':scope.uid,
			         	     'coinNun':scope.unum,
			         	     'code':scope.codea,
			         	     'dealPwd':scope.pswd,
			         	     'mobile':rootScope.phone,
			         	     'token':rootScope.tt})
			         .success(function(data){
			            scope.tsbox={'display':'block'};
			            if(data.desc=='success'){
			         	   scope.tishia='转账成功！';
			            }else{
			         	   scope.tishia=data.desc;
			            }
			         })
			         .error(function(error){
			             console.log(error);
			         });
	           }
	        })
	        .error(function(error){
	            console.log(error);
	        });
	     }else{
	    	 scope.tsbox={'display':'block'};
	    	 scope.tishia='请填写数据！';
	     }
    }

    //转账记录
    postInfo.dataInfos(rootScope.ip+"/transfer/goTransfer.do",{'userNo':rootScope.userNo,'coinNo':'10','token':rootScope.tt})
    .success(function(data){
        scope.fwUl=data.list;
    })
    .error(function(error){
        console.log(error);
    });
    scope.ellipsis=function(a){
    	if(a==null||a==undefined){
    		return null;
    	}else{
    		if(a.length==0){
    			return null;
    		}else{
    			return (a.substr(0, 3)+'***'+a.substr(-4));	
    		}
    	}
    }
}]);