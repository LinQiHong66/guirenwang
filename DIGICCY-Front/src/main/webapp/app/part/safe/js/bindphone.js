app.controller('bpCtrl',['$scope','$http','$timeout','$interval',"$rootScope","postInfo","getInfo",function(scope,http,timeout,interval,rootScope,postInfo,getInfo){

	scope.bindPhoned=false;
	scope.AtextBtnFlg=false;
	scope.honeFlg=false;
	scope.phone="";
	scope.identifyCode="";
	scope.phoneInuptA="";
	scope.Atext="免费获取验证码";
	http({
		url:'../../safe.json',
		method:'GET'
	})
	.success(function(data){
		scope.tit=data.bindphone.tit;
		scope.data0=data.bindphone.data[0];
		scope.data1=data.bindphone.data[1]+"：";
		scope.data2=data.bindphone.data[2]+"：";
		scope.data3=data.bindphone.data[3];
	});
	//判断是否已经绑定手机号码
	postInfo.dataInfos(rootScope.ip+"/inesvuser/phoneAndAlipayState.do",
				{desc:'phone','userNo':rootScope.userNo,"token":rootScope.tt})  
	.success(function(data){
		if(data.code==221){
			scope.Phoned=data.userPhone.substr(0, 3) + '****' + data.userPhone.substr(7);
			scope.bindPhoned=true;
		}else if(data.code==222){
			scope.Phoned="";
			scope.bindPhoned=false;
		};
	})
	.error(function(data){
		console.log("安全中心的判断是否已经绑定手机号码号报错");
	});	
	//获取手机验证码
	scope.identifying=function(){
		if(!(/^1[34578]\d{9}$/.test(scope.phone))){
			scope.bindphoneflg=true;
			scope.bindphoneTip="请输入正确的手机号码!";
			timeout(function(){
				scope.bindphoneflg=false;
			},1000);
			return false;
		}else{
			postInfo.dataInfos(rootScope.ip+"/reg/sendMobile.do",
			{'mobile':scope.phone,'userNo':rootScope.userNo,"token":rootScope.tt})  
			.success(function(data){
				scope.validataCode=data.validataCode;
				scope.AtextBtnFlg=true;
				scope.n = 60;
				scope.Atext = scope.n + "秒后重新发送";
				scope.isDisable = true;
				var time = interval(function () {
					scope.n--;
					scope.Atext = scope.n + "秒后重新发送";
					if (scope.n == 0)
					{
						interval.cancel(time);
						scope.isDisable = false;
						scope.Atext = "免费获取验证码";
						scope.AtextBtnFlg=false;
					}
				},1000);
			})
			.error(function(data){
				console.log("安全中心的绑定手机号获取短信验证码报错");
			});
		}
	};
	//绑定手机号码
	scope.bindPhone=function(){
		scope.honeFlg=true;
		if(!(/^1[34578]\d{9}$/.test(scope.phone))||scope.identifyCode==null||scope.phoneInuptA==null){
			scope.bindphoneflg=true;
			scope.bindphoneTip="请输入完善以上信息!";
			timeout(function(){
				scope.bindphoneflg=false;
			},1000);
			return false;
		}else{
			//验证码短信验证码是否正确
			postInfo.dataInfos(rootScope.ip+"/reg/compare.do",
			{"mobile":scope.phone,"code":scope.identifyCode})  
			.success(function(data){
				if(data.code==100){
					postInfo.dataInfos(rootScope.ip+"/inesvuser/updatePhone.do",
					{'phone':scope.phone,'userNo':rootScope.userNo,"token":rootScope.tt})  
					.success(function (data) {
						rootScope.phone=scope.phone;
							window.location.reload();
					})
					.error(function (data) {
						console.log("绑定手机报错");
					});
				}else{
					scope.honeFlg=false;
					scope.bindphoneflg=true;
					scope.bindphoneTip="请输入正确短信验证码!";
					timeout(function(){
						scope.bindphoneflg=false;
					},1000);
					return false;
				}
			})
			.error(function(data){
				console.log("安全中心的验证码短信验证码是否正确报错");
				scope.honeFlg=false;
			});
		};
	};
}]);