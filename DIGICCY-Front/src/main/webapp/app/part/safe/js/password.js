app.controller('pwCtrl',['$scope','$http','$timeout',"$rootScope","postInfo","getInfo",function(scope,http,timeout,rootScope,postInfo,getInfo){
	scope.passwordflg=false;
	scope.passwordTip="注意:原始密码与原密码一致；修改密码与确定密码要保持一致！";
	scope.saveFile=function(){
		if(scope.newPassword!=scope.ensurePassword){
			scope.passwordflg=true;
			scope.passwordTip="请确保修改新密码和确定密码一致!";
			scope.newPassword="";
			scope.ensurePassword="";
			timeout(function(){
				scope.passwordflg=false;
			},1500);
			return false;
		}else{
		
		postInfo.dataInfos(rootScope.ip+"/inesvuser/updateInesvUser.do",{password1:scope.password,password:scope.ensurePassword,'userNo':rootScope.userNo,"token":rootScope.tt})
		.success(function(data){
			if(data.code==200){
				scope.passwordflg=true;
				scope.passwordTip="修改密码失败！";
				scope.password="";
				scope.newPassword="";
				scope.ensurePassword="";
				timeout(function(){
					scope.passwordflg=false;
				},1500);
				return false;
			}else if(data.code==23){
				scope.passwordflg=true;
				scope.passwordTip="请输入正确的原始密码！";
				scope.password="";
				timeout(function(){
					scope.passwordflg=false;
				},1500);
				return false;
			}else if(data.code==100){
				scope.passwordflg=true;
				scope.passwordTip="修改登录密码成功！";
				window.sessionStorage.clear();
				timeout(function(){
					scope.passwordflg=false;
					if(!window.sessionStorage['username']){
					    if(!window.sessionStorage['loginflag']){
					    	 window.sessionStorage['loginflag']=true;
					    }
				        window.location="../../index.html";
			        }
				},1500);
			};
		})
		.error(function(data){
			console.log("修改登录密码报错");
		});
		};
	}; 
}]);