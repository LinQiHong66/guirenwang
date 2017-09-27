app.controller("liftNow",["$scope","$http","$rootScope","postInfo","getInfo",'$interval',function (scope,http,rootScope,postInfo,getInfo,interval){
   http({
        url:'../../finance.json',
        method:'GET'
    }).success(function(data){
        scope.lfTitle=data.liftNow.lfTitle;
        scope.lfHeader=data.liftNow.lfHeader;
        scope.lfH5=data.liftNow.lfH5;
        scope.lfulHeader=data.liftNow.lfulHeader;

    }).error(function(){
        console.log(data);
    });
 
    //可用人民币 银行卡地址 提现记录
    postInfo.dataInfos(rootScope.ip+"/rmbWithdraw/getWithdrawInfo.do",{userNo:rootScope.userNo,'token':rootScope.tt})   
    .success(function(data){
        scope.rmb=data.data;//可用人民币
        scope.dizhi=data.bankList;//银行卡地址
        scope.lfUl=data.list;//提现记录

    }).error(function(error){
        console.log(error);
    });
    //手续费率
    postInfo.dataInfos(rootScope.ip+"/trade/getPiundatgeRate.do",{'param':'poundageRate','token':rootScope.tt})
     .success(function(data){
         scope.fei=data.StaticParams.value;
     }).error(function(error){
         console.log(error);
     });
    
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
            scope.lfHeader.yanzhenma=cont+'s后重新获取';
            var timer=interval(function () {
                cont--;
                scope.lfHeader.yanzhenma=cont+"秒后重新获取";
                if(cont==0){
                    interval.cancel(timer);
                    scope.lfHeader.yanzhenma='重新获取';
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
    //确认转出
    scope.tijiao=function(){
	   //虚拟币确认提现
	   if(scope.lfa,scope.codea,scope.qiana,scope.psword){
		//判断验证码
	  postInfo.dataInfos(rootScope.ip+"/reg/compare.do",{'mobile':rootScope.phone,'code':scope.codea,'token':rootScope.tt})   
        .success(function(data){
          if(data.desc=='fail'){
        	   scope.tsbox={'display':'block'};
		       scope.tishia='验证码错误！';
           }else{
        	   //确认转账
   	        	 postInfo.dataInfos(rootScope.ip+"/rmbWithdraw/goRmbWithdraw.do",{
	               'userNo':rootScope.userNo,
	              'price':scope.qiana,
	              'bank':scope.lfa,
	              'code':scope.codea,
	              'dealPwd':scope.psword,
	              'moble':rootScope.phone,
	              'token':rootScope.tt
	            }) 
		         .success(function(data){
		            scope.tsbox={'display':'block'};
		            if(data.desc=='success'){
		         	   scope.tishia='提现成功！';
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
	        })
	     }else{
	    	 scope.tsbox={'display':'block'};
	    	 scope.tishia='请填写数据！';
	     }
		   
    }
    console.log(rootScope.phone)
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