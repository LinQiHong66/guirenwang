reg.controller("indexCtrl",['$scope','$http','$rootScope','$interval',function(scope,http,rootscope,interval){
	
	
	// email判断
//	rootscope.pwd='dedddeedede';
	scope.text='获取手机验证码';
	scope.userval='';
	scope.showUl=true;
	scope.emailflag=0;
	scope.phoneflag=0;
	scope.agree=false;
	rootscope.info={};
	scope.selLi=true;
	scope.maskFlag=false;
	scope.pdval="";
	scope.pdyzval="";

	// 用户名判断
	scope.userName=function(){
		
		if(this.userval==''){
			this.usertype=false;
			this.usermessage='用户名不能为空';
		}else{
			this.usertype=true;
			this.usermessage='用户名输入正确';
			this.emailflag+=1;
			rootscope.username=this.userval;//用户名
		}
	}
	// 密码判断
	scope.password=function(){
		var pdzz=/^\w{6,18}$/;
		var val=pdzz.test(this.pdval);
		if(!val){
			this.pdtype=false;
			this.pdmessage='密码在6-18位,勿使用特殊字符！';
		}else{
			this.pdtype=true;
			this.pdmessage='密码格式正确';
			this.emailflag+=1;
		}
	}
	// 再次输入密码判断
	scope.again=function(){
		// console.log(this.agval);
		if(this.agval!=this.pdval){
			this.agtype=false;
			this.agmessage='两次密码输入不一致，请重写';
		}else{
			this.agtype=true;
			this.agmessage='两次密码一致';
			this.emailflag+=1;
			rootscope.password=this.pdval;//用户密码
		}
	}
	// 手机号码判断
	scope.phone=function(){
		var phonezz=/^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
		var val=phonezz.test(this.phoneval);
		if(!val){
			this.phonetype=false;
			this.phonemessage='手机号码格式不正确';
		}else{
			this.phonetype=true;
			this.phonemessage='手机号码格式正确';
			this.phoneflag+=1;
			rootscope.phone=this.phoneval;//手机号码
		}
	}
	scope.getphoneyz=function(){
		if(scope.phonemessage=='手机号码格式正确'){
			scope.n = 60;
	        scope.text = scope.n + "秒后再次获取";
	        scope.isDisable = true;
	        var time = interval(function () {
	            scope.n--;
	            scope.text = scope.n + "秒后再次获取";
	            if (scope.n == 0)
	            {
	                interval.cancel(time);
	                scope.isDisable = false;
	                scope.text = "重新获取验证码";
	            }
	        },1000);
			
			 http({//手机验证码
		        method:"POST",
		        url:"/forget/sendCode.do",
		        data:{'phone':scope.phoneval},
		        headers:{'Content-Type':'application/x-www-form-urlencoded'},  
		        transformRequest: function(obj) {    
		            var str = [];
		            for (var p in obj) {    
		                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		            }    
		            return str.join("&");
		        }
		    })
		    .success(function(data){
		        console.log(data);
		        scope.getCode=data.getCode;
		        console.log(scope.getCode);
		    })
		    .error(function(error){
		        console.log(error);
		    });
		}else{
			this.phoneyztype=false;
			this.phoneyzmessage='请输入手机号';
		}
	}
	// 手机验证码判断
	scope.phoneyz=function(){
		console.log(scope.getCode);
		if(this.phoneyzval!=scope.getCode){
			this.phoneyztype=false;
			this.phoneyzmessage='验证码错误';
		}else{
			this.phoneyztype=true;
			this.phoneyzmessage='验证码正确';
			this.phoneflag+=1;
		}
	}

	// 手机注册密码判断
	scope.pdyz=function(){
		var pdzz=/^\w{6,18}$/;
		var val=pdzz.test(this.pdyzval);
		// console.log(val);
		if(!val){
			this.pdyztype=false;
			this.pdyzmessage='密码在6-18位,勿使用特殊字符！';
		}else{
			this.pdyztype=true;
			this.pdyzmessage='密码格式正确';
			this.phoneflag+=1;
		}
	}
	// 手机注册再次密码判断
	scope.againpd=function(){
		// console.log(this.agval);
		if(this.againpdval!=this.pdyzval){
			this.againpdtype=false;
			this.againpdmessage='两次密码输入不一致，请重写';
		}else{
			this.againpdtype=true;
			this.againpdmessage='两次密码一致';
			this.phoneflag+=1;
			rootscope.password=this.pdyzval;//用户密码
		}
	}

	scope.ip = getIp;
	// 找回密码
	scope.fgpd=function(){
		console.log(scope.userval);
//		alert(1);

		 http({//忘记交易密码
	        method:"POST",
	        url:scope.ip+"/forget/goUpdatePsw.do",
	        data:{'username':scope.userval,'phone':scope.phoneval,'code':scope.phoneyzval,'updatePwd':scope.againpdval},
	        headers:{'Content-Type':'application/x-www-form-urlencoded'},    
	        transformRequest: function(obj) {    
	            var str = [];    
	            for (var p in obj) {    
	                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));    
	            }    
	            return str.join("&");    
	        }
	    })
	    .success(function(data){
	        console.log(data.desc);
	        console.log(data);
	    })
	    .error(function(error){
	        console.log(error);
	    });
	}
	
}])