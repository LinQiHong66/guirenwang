
app.controller("dfCtrl",["$scope","$http","$rootScope","postInfo","getInfo",function (scope,http,rootScope,postInfo,getInfo) {

		// scope.a=$window.localstorage
	    http({
		        url:'../../safe.json',
		        method:'GET'
    		})
	    .success(function(data){
        	// 上面
	        scope.sTit=data.sdefault.tit;
	        scope.lev0=data.sdefault.level[0]+"：";
	        scope.lev1={"width":data.sdefault.level[1]+"%"};
	        scope.lev2=data.sdefault.level[2];
	        scope.lev3=data.sdefault.level[3];
	        scope.sDid=data.sdefault.id[0];
	        scope.sDName=data.sdefault.name[0];
	        scope.sDUser=data.sdefault.user[0];
	        scope.sDEmail=data.sdefault.email[0];	
	       /*scope.sId=data.sdefault.id[0]+"："+data.sdefault.id[1];
	        scope.sName=data.sdefault.name[0]+"："+data.sdefault.name[1];
	        scope.sUser=data.sdefault.user[0]+"："+data.sdefault.user[1];
	        scope.sEmail=data.sdefault.email[0]+"："+data.sdefault.email[1];*/
	        
	        //项目列表
	        // 实名认证
	        scope.smrz0=data.sdefault.data.name[0];
	        scope.smrz1=data.sdefault.data.name[1];
	        scope.smrz2=data.sdefault.data.name[2];
	        scope.smrz3=data.sdefault.data.name[3];
	        scope.smrz4=data.sdefault.data.name[4];
	        // 登录密码
	        scope.dlmm0=data.sdefault.data.password[0];
	        scope.dlmm1=data.sdefault.data.password[1];
	        scope.dlmm2=data.sdefault.data.password[2];
	        scope.dlmm3=data.sdefault.data.password[3];
	        //交易密码
	        scope.jymm0=data.sdefault.data.paypd[0];
	        scope.jymm1=data.sdefault.data.paypd[1];
	        scope.jymm2=data.sdefault.data.paypd[2];
	        scope.jymm3=data.sdefault.data.paypd[3];
	        //双重认证
	        scope.scrz0=data.sdefault.data.dbtest[0];
	        scope.scrz1=data.sdefault.data.dbtest[1];
	        scope.scrz2=data.sdefault.data.dbtest[2];
	        scope.scrz3=data.sdefault.data.dbtest[3];
	        scope.scrz4=data.sdefault.data.dbtest[4];
	        //手机绑定
	        scope.sjbd0=data.sdefault.data.phone[0];
	        scope.sjbd1=data.sdefault.data.phone[1];
	        scope.sjbd2=data.sdefault.data.phone[2];
	        scope.sjbd3=data.sdefault.data.phone[3];
	        //邮箱绑定
	        scope.yxbd0=data.sdefault.data.email[0];
	        scope.yxbd1=data.sdefault.data.email[1];
	        scope.yxbd2=data.sdefault.data.email[2];
	        scope.yxbd3=data.sdefault.data.email[3];
	        
	    }).error(function(data){

	    });
	    
	    if(rootScope.phone){
	    	scope.phone=rootScope.phone;
	        scope.tishi='已绑定的手机号:';
	        scope.caozuo='修改';
	    }else{
	    	scope.tishi='还没有绑定手机号';
	    	scope.phone='';
	    	scope.caozuo='去绑定'
	    }
		postInfo.dataInfos(rootScope.ip+"/inesvuser/getUserInfoByUserNo.do",{'userNo':rootScope.userNo,"token":rootScope.tt})  
		.success(function (data) {
			scope.userInfo=data.userInfo;
			scope.sId=scope.sDid+":"+scope.userInfo.id;
			scope.sName=scope.sDName+":"+scope.userInfo.real_name;
			scope.sUser=scope.sDUser+":"+scope.userInfo.username;
			if(rootScope.phone){
				scope.mailphone='手机号：';
				scope.sEmail=rootScope.phone;
			}else{
				scope.mailphone='还没有绑定手机号';
				scope.sEmail='';
			}
		})
		.error(function (data) {
			console.log("安全中心实名认证报错")
		});
	}]);