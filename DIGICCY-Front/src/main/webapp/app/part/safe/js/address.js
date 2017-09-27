app.controller('adCtrl',['$scope','$http',"postInfo","$rootScope","$timeout",function(scope,http,postInfo,rootScope,timeout){
	scope.hsHowFlg=false;
	http({
		url:'../../safe.json',
		method:'GET'
	})
	.success(function(data){
		scope.tit=data.address.tit;
		scope.tbtit=data.address.tbtit;
		scope.addaddress=data.address.addaddress;
	});
	scope.addAdds=function () {
		scope.hsHowFlg=true;
	};
	scope.close=function(){
		scope.hsHowFlg=false;
	};
	/*查看地址*/
	scope.chaxun=function () {
		postInfo.dataInfos(rootScope.ip+"/address/getUserAddress.do",{'userNo':rootScope.userNo,"token":rootScope.tt})
			.success(function (data) {
				scope.tbdata=data.data;
			})
			.error(function (data) {
				console.log("查询联系地址报错")
			});
	};
	// var watch = scope.$watch('tbdata',function(newValue,oldValue, scope){
	// 	console.log(newValue);
	// 	console.log(oldValue);
	// 	scope.chaxun();
    //
	// });
	scope.chaxun();
	/*删除地址*/
	scope.shangchu=function (num) {
			postInfo.dataInfos(rootScope.ip+"/address/getUserAddress.do",{'userNo':rootScope.userNo,"token":rootScope.tt})
				.success(function (data) {
					scope.idNum=data.data[num].id;
					postInfo.dataInfos(rootScope.ip+"/address/deleteAddress.do",{'userNo':rootScope.userNo,"token":rootScope.tt,"id":scope.idNum})
						.success(function (data1,status) {
							timeout(function(){
								scope.chaxun();
							},100);
						})
						.error(function (data) {
							console.log("删除地址报错")
						});
				})
				.error(function (data) {
					console.log("查询联系地址报错")
				});
	};
	scope.lijiTJ=function () {
		if(scope.xingming!=null){
			if(scope.xingming.length<0){
				scope.passwordflg = true;
				scope.passwordTip = "姓名不能为空!";
				timeout(function () {
					scope.passwordflg = false;
				}, 1000);
				return false;
			};
		}else{
			scope.passwordflg = true;
			scope.passwordTip = "姓名不能为空!";
			timeout(function () {
				scope.passwordflg = false;
			}, 1000);
			return false;
		};
		if(scope.shengfeng!=null){
			var sfz=/(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}$)/
			if(!sfz.test(scope.shengfeng)){
				scope.passwordflg = true;
				scope.passwordTip = "请输入正确的身份证号!";
				scope.shengfeng="";
				timeout(function () {
					scope.passwordflg = false;
				}, 1000);
				return false;
			};
		}else{
			scope.passwordflg = true;
			scope.passwordTip = "请输入身份证号!";
			scope.shengfeng="";
			timeout(function () {
				scope.passwordflg = false;
			}, 1000);
			return false;
		};
		if(scope.xinali!=null){
			if(!(/^1[34578]\d{9}$/.test(scope.xinali))){
				scope.passwordflg = true;
				scope.passwordTip = "请输入正确的手机号码!";
				scope.xinali="";
				timeout(function () {
					scope.passwordflg = false;
				}, 1000);
				return false;
			};
		}else {
			scope.passwordflg = true;
			scope.passwordTip = "请输入手机号码!";
			scope.xinali="";
			timeout(function () {
				scope.passwordflg = false;
			}, 1000);
			return false;
		};
		if(scope.songhuo!=null){
			if(scope.songhuo.length<0){
				scope.passwordflg = true;
				scope.passwordTip = "请输入详细地址!";
				scope.songhuo="";
				timeout(function () {
					scope.passwordflg = false;
				}, 1000);
				return false;
			};
		}else{
			scope.passwordflg = true;
			scope.passwordTip = "请输入详细地址!";
			scope.songhuo="";
			timeout(function () {
				scope.passwordflg = false;
			}, 1000);
			return false;
		};
		if(scope.jiaoyi!=null){
			if(scope.jiaoyi.length<0){
				scope.passwordflg = true;
				scope.passwordTip = "交易密码不能为空!";
				scope.jiaoyi="";
				timeout(function () {
					scope.passwordflg = false;
				}, 1000);
				return false;
			}else {
				postInfo.dataInfos(rootScope.ip+"/common/getTradePassword.do",
					{'userNo':rootScope.userNo,"token":rootScope.tt,'dealPwd':scope.jiaoyi})
				.success(function (data) {
					if(data.code==100){
						postInfo.dataInfos(rootScope.ip+"/address/insertAddress.do",
							{'userNo':rootScope.userNo,"token":rootScope.tt,"remarkAddress":scope.beizhu,"name":scope.xingming,"card":scope.shengfeng,"phone":scope.xinali,"address":scope.songhuo})
							.success(function (data) {
								scope.hsHowFlg=false;
								scope.beizhu="";
								scope.xingming="";
								scope.shengfeng="";
								scope.xinali="";
								scope.songhuo="";
								scope.jiaoyi="";
								timeout(function () {
									scope.chaxun();
								}, 100);
								return false;
							})
							.error(function (data) {
								console.log("查询联系地址报错")
							});
					}else {
						 scope.passwordflg = true;
						 scope.passwordTip = "请输入正确的交易密码!";
						 scope.jiaoyi="";
						 timeout(function () {
						 scope.passwordflg = false;
						 }, 1000);
						 return false;
					}
				})
				.error(function (data) {
					console.log("添加联系地址报错")
				})
			}
		}else {
			scope.passwordflg = true;
			scope.passwordTip = "请输入交易密码!";
			scope.jiaoyi="";
			timeout(function () {
				scope.passwordflg = false;
			}, 1000);
			return false;
		};
	}
}]);