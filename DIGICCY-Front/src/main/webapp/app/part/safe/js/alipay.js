app.controller('aliCtrl',['$scope','$http','$timeout','$interval',"$rootScope","postInfo","getInfo",function(scope,http,timeout,interval,rootScope,postInfo,getInfo){

	scope.bindAlipayFlg=true;
	scope.AtextBtnFlg=false;
	scope.alipayYell="";
	scope.honeAlipayA="";
	scope.honeAlipayB="";
	scope.Atext="免费获取验证码";
	http({
		url:'../../safe.json',
		method:'GET'
	})
	.success(function(data){
		scope.tit=data.alipay.tit;
		scope.info=data.alipay.info;
		scope.user=data.alipay.user+"：";
	});
	//判断是否已经绑定支付宝
	postInfo.dataInfos(rootScope.ip+"/inesvuser/phoneAndAlipayState.do",
	{desc:'alipay','userNo':rootScope.userNo,"token":rootScope.tt})  
	.success(function(data){
		if(data.code==223){
			scope.bindAlipayFlg=false;
			scope.bindAlipayA=data.userAlipay.substr(0, 3) + '********' + data.userAlipay.substr(7);
		}else if(data.code=224){
			scope.bindAlipayFlg = true;
		};
	})
	.error(function(data){
		console.log("安全中心的判断是否已经绑定支付宝报错!");
	});
	//获取短信验证码
	scope.AlipayHone = function () {
		if (!(/^1[34578]\d{9}$/.test(scope.honeAlipayA))) {
			scope.passwordflg = true;
			scope.passwordTip = "请输入正确的手机号码!";
			timeout(function () {
				scope.passwordflg = false;
			}, 1000);
			return false;
		} else {
			postInfo.dataInfos(rootScope.ip+"/reg/sendMobile.do",
			{'mobile': scope.honeAlipayA,'userNo':rootScope.userNo,"token":rootScope.tt})  
			.success(function (data) {
				scope.AlipayHoneCode=data.validataCode;
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
			.error(function (data) {
				console.log("绑定支付宝获取免费短信验证码报错")
			});
		};
	};
	//支付宝绑定
	scope.bAlipay = function () {
		if (!(/^1[34578]\d{9}$/.test(scope.honeAlipayA)) || scope.alipayYell==null || scope.honeAlipayB==null) {
			scope.passwordflg = true;
			scope.passwordTip = "请输入完善以上信息!";
			timeout(function () {
				scope.passwordflg = false;
			}, 1000);
			return false;
		}else{
			//验证码短信验证码是否正确
			postInfo.dataInfos(rootScope.ip+"/reg/compare.do",
			{"mobile":scope.honeAlipayA,"code":scope.honeAlipayB})  
			.success(function(data){
				if(data.code==100){
					postInfo.dataInfos(rootScope.ip+"/inesvuser/updateAlipay.do",
					{'alipay': scope.alipayYell,'userNo':rootScope.userNo,"token":rootScope.tt}) 
					.success(function (data) {
						if (data.code == 100) {
								window.location.reload();
							return false;
						}else{
							scope.passwordflg = true;
							scope.passwordTip = "绑定支付宝失败!";
							timeout(function () {
								scope.passwordflg = false;
							}, 1000);
							return false;
						};
					})
					.error(function (data) {
						console.log("安全中心的绑定支付宝报错");
						return false;
					});
				}else{
					scope.passwordflg = true;
					scope.passwordTip = "请输入正确的短信验证码!";
					timeout(function () {
						scope.passwordflg = false;
					}, 1000);
					return false;
				};
			})
			.error(function(data){
				console.log("安全中心的验证码短信验证码是否正确报错");
				scope.honeFlg=false;
			});
		};
	};
}]);