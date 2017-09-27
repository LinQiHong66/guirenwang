app.controller("shiftOut",["$scope","$http","$rootScope","postInfo","getInfo",'$interval',function (scope,http,rootScope,postInfo,getInfo,interval){
    http({
        url:'../../finance.json',
        method:'GET'
    }).success(function(data){
        scope.soTitle=data.shiftOut.soTitle;
        scope.soHeader=data.shiftOut.soHeader;
        scope.soUlHeader=data.shiftOut.soUlHeader;

    }).error(function(){
        console.log(data);
    });
    
  //币种
   scope.bibi=[];
   postInfo.dataInfos(rootScope.ip+"/common/getBasicCoinTypeExceptRMB.do",{'token':rootScope.tt})
  .success(function(data){ 
      scope.bi=data.coinTypeList;
      scope.bibi=data.coinTypeList;
      scope.bia=20;
  }).error(function(error){
      console.log("select币种数据获取错误"+error);
  });
 //手续费率
   postInfo.dataInfos(rootScope.ip+"/trade/getPiundatgeRate.do",{'param':'poundageRate','token':rootScope.tt})
    .success(function(data){
        scope.fee=data.StaticParams.value;
    }).error(function(error){
        console.log(error);
    });
   
   /*美元的资产钱包地址*/
   postInfo.dataInfos(rootScope.ip+"/virtual/getUserBalanceAndAddress.do",{'userNo':rootScope.userNo,'coinNo':20,'token':rootScope.tt})
   .success(function (data) {
     scope.binum=data.data[0]?data.data[0].enable_coin:0;
     scope.dizhi=data.data;
       
   }).error(function(error){
       console.log(error);
   });
   //美元的转出记录
   postInfo.dataInfos(rootScope.ip+"/virtual/getUserBalanceWithdraw.do",{'userNo':rootScope.userNo,'coinNo':20,'token':rootScope.tt})
   .success(function (data) {
     scope.soUl=data.data;  
     for(var i=0;i<data.data.length;i++){
		 if( scope.bibi[i].coin_no == data.data[i]['coin_no']){
			 scope.binamea=scope.bibi[i].coin_name;
		  }
     }
   })
   .error(function(error){
       console.log(error);
   });
  scope.changebi=function(bia){
      /*币种资产钱包地址查询*/
      postInfo.dataInfos(rootScope.ip+"/virtual/getUserBalanceAndAddress.do",{'userNo':rootScope.userNo,'coinNo':scope.bia,'token':rootScope.tt})
      .success(function (data) {
    	if(data.data.length==0){
    		scope.binum=0;
   		    scope.dizhi='';
    	}else{
    		scope.binum=data.data[0].enable_coin;
            scope.dizhi=data.data;
    	}
      })
      .error(function(error){
          console.log(error);
      });
      //转出记录
      postInfo.dataInfos(rootScope.ip+"/virtual/getUserBalanceWithdraw.do",{'userNo':rootScope.userNo,'coinNo':scope.bia,'token':rootScope.tt})
      .success(function (data) {
        scope.soUl=data.data; 
        for(var i=0;i<data.data.length;i++){
   		 if( scope.bibi[i].coin_no == data.data[i]['coin_no']){
   			 scope.binamea=scope.bibi[i].coin_name;
   		  }
        }
      })
      .error(function(error){
          console.log(error);
      });
  }
  
  scope.tsbox={'display':'none'};
  scope.guan=function(){
 	 scope.tsbox={'display':'none'};
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
    //确认转出
    scope.tijiao=function(){
	   //虚拟币确认提现
	   if(scope.bia,scope.codea,scope.dizhia,scope.pawd,scope.numa){
		//判断验证码
	        postInfo.dataInfos(rootScope.ip+"/reg/compare.do",{'mobile':rootScope.phone,'code':scope.codea,'token':rootScope.tt})   
	        .success(function(data){
	          if(data.desc=='fail'){
	        	   scope.tsbox={'display':'block'};
			       scope.tishia='验证码错误！';
	           }else{
	        	   //确认转账
	   	        	postInfo.dataInfos(rootScope.ip+"/virtualWith/determineWithdraw.do",{
	                    'userNo':rootScope.userNo,
	                    'address':scope.dizhia,
	                    'coinType':scope.bia,
	                    'dealPwd':scope.pawd,
	                    'code':scope.codea,
	                    'trueNum':scope.numa,
	                    'phone':rootScope.phone,
	                    'token':rootScope.tt
	                 }) 
			         .success(function(data){
			            scope.tsbox={'display':'block'};
			            if(data.desc=='success'){
			         	   scope.tishia='转出成功！';
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