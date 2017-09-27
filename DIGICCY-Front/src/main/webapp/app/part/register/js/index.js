reg.controller("indexCtrl",['$scope','$http','$rootScope','$interval',function(scope,http,rootscope,interval){
//	设置初始值
	scope.getCode='456789';
	scope.text='点击获取验证';
	scope.agree=false;
	scope.maskFlag=false;
	scope.ip=getIp;
	scope.yzsuccess=false;
	// email判断
	function aaa(){
		if(window.sessionStorage['inviteCode']){
			scope.invitval=window.sessionStorage['inviteCode'];
		}
	}
	aaa();

	// 手机号码判断
	scope.phone=function(){
		var phonezz=/^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
		var val=phonezz.test(this.phoneval);
		if(!val){
			this.phonetype=false;
			this.phonemessage='手机号码格式不正确';
		}else{
			http({
				// url:scope.ip+'/reg/phoneIsUnique.do',
				url:'http://localhost/reg/phoneIsUnique.do',
				method:'POST',
				data:{
					'phone':scope.phoneval
				},
				headers:{'Content-Type':'application/x-www-form-urlencoded'},
		        transformRequest: function (data) {
		        	return $.param(data)
		        }
			}).success(function(data){
				console.log(data.code)
				if(data.code=='12'){
					scope.phonetype=false;
					scope.phonemessage='该手机号已被注册';
				}else{
					scope.phonetype=true;
					scope.phonemessage='手机号码格式正确';
					rootscope.phone=scope.phoneval;
				}
			}).error(function(data){
				console.log(data);
			})
		}
	}
	//	获取手机验证码
	scope.getphoneyz=function(){
		if(scope.phoneval){
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
		        // url:scope.ip+"/forget/sendCode.do",
		        url:scope.ip+"/forget/sendCode.do",
		        // url:"http://localhost/forget/sendCode.do",
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
		        scope.getCode=data.getCode;
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
		// if(this.phoneyzval==null || this.phoneyzval!=scope.getCode){
		if(this.phoneyzval==null){
			this.phoneyztype=false;
			this.phoneyzmessage='验证码为空';
		}else{
			// this.phoneyztype=true;
			// this.phoneyzmessage='验证码正确';
			 http({//手机验证码
		        method:"POST",
		        url:scope.ip+"/reg/compare.do",
		        // url:"http://localhost/reg/compare.do",
		        data:{'mobile':scope.phoneval,'code':scope.phoneyzval},
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
		    	if(data.code=='100'){
		    		scope.phoneyztype=true;
					scope.phoneyzmessage='验证码正确';
					scope.yzsuccess=true;
		    	}else{
		    		scope.phoneyztype=false;
					scope.phoneyzmessage='验证码错误';
		    	}
		    	console.log(data);
		        // scope.getCode=data.getCode;
		    })
		    .error(function(error){
		        console.log(error);
		    });
			
		}
	}

	// 手机注册密码判断
	scope.pdyz=function(){
		var pdzz=/^\w{6,18}$/;
		var val=pdzz.test(this.pdyzval);
		if(!val){
			this.pdyztype=false;
			this.pdyzmessage='密码请在6-18位之间';
		}else{
			this.pdyztype=true;
			this.pdyzmessage='密码格式正确';
		}
	}
	// 手机注册再次密码判断
	scope.againpd=function(){
		if(this.againpdval!=this.pdyzval){
			this.againpdtype=false;
			this.againpdmessage='两次密码输入不一致，请重写';
		}else{
			this.againpdtype=true;
			this.againpdmessage='两次密码一致';
			rootscope.password=this.againpdval;
		}
	}
	//邀请码
	scope.yqma=function(){
		rootscope.invite_num=scope.invitval;
	}
	// 同意协议
	scope.select=function(){
		!scope.agree;
	}
	// 进入下一页
	scope.paypd=function(){
		
		if(scope.agree==false){
			this.xytype=false;
			this.xymessage="请同意该协议才能进行注册！"
		}else{
			if(scope.phoneval==null) {
				scope.phonetype=false;
				scope.phonemessage='请填写手机号码';
				return false;
			// }if(scope.phoneyzval!=scope.getCode) {
			}if(this.phoneyztype!=true) {
				scope.phoneyztype=false;
				scope.phoneyzmessage='验证码错误';
				return false;
			}if(scope.pdyzval==null) {
				scope.pdyztype=false;
				scope.pdyzmessage='密码请在6-18位之间';
				return false;
			}if(scope.againpdval!=scope.pdyzval) {
				scope.againpdtype=false;
				scope.againpdmessage='两次密码输入不一致，请重写';
				return false;
			}
			location.href='#/paypd';
		}	
	}

	// 关闭协议窗口
	scope.closeBox=function(){
		scope.maskFlag=false;
	}
	// 显示协议
	scope.showBox=function(){
		scope.maskFlag=true;
	}
}])