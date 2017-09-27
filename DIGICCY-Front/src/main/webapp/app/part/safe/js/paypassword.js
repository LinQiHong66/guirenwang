app.controller('ppwCtrl',['$scope','$http','$timeout',"$rootScope","postInfo","getInfo",function(scope,http,timeout,rootScope,postInfo,getInfo){
	scope.payPasswordTip="注意:交易密码与原密码一致；新交易密码与确定交易密码要保持一致！";
	scope.payPasswordflg=false;
	scope.amendPassword=function(){
		if(scope.newPaypassword!=scope.ensurePaypassword){
			scope.payPasswordflg=true;
			scope.payPasswordTip="新交易密码与确定交易密码要保持一致！";
			scope.newPaypassword="";
			scope.ensurePaypassword="";
			timeout(function(){
				scope.payPasswordflg=false;
			},1500);
			return false;
		}else{
			
			postInfo.dataInfos(rootScope.ip+"/inesvuser/updateDealPwd.do",
				{pealpwd1:scope.payPassword,pealpwd:scope.ensurePaypassword,'userNo':rootScope.userNo,"token":rootScope.tt})
			.success(function(data){
				if(data.code==200){
					scope.payPasswordflg=true;
					scope.payPasswordTip="交易密码修改失败！";
					scope.payPassword="";
					scope.newPaypassword="";
					scope.ensurePaypassword="";
					timeout(function(){
						scope.payPasswordflg=false;
					},1500);
					return false;
				}else if(data.code==23){
					scope.payPasswordflg=true;
					scope.payPasswordTip="请输入正确的原始交易密码！";
					scope.payPassword="";
					timeout(function(){
						scope.payPasswordflg=false;
					},1500);
					return false;
				}else if(data.code==100){
					scope.payPasswordflg=true;
					scope.payPasswordTip="修改交易密码成功！";
					scope.payPassword="";
					scope.newPaypassword="";
					scope.ensurePaypassword="";
					timeout(function(){
						scope.payPasswordflg=false;
					},1500);
				};
			})
			.error(function(data){
				console.log("修改交易密码报错");
			});
		};
	};
}]);