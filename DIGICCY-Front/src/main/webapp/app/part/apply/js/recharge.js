app.controller("rechargeCtrl",["$scope","$http","$rootScope","postInfo","getInfo",function (scope,http,rootScope,postInfo,getInfo){
   // ·userNo<Integer> : 用户编号     ·coinType<Integer> : 货币类型 0:人民币
    postInfo.dataInfos(rootScope.ip+"/sub/getUserBalance.do",{'userNo':rootScope.userNo,'coinType':0,"token":rootScope.tt})
		.success(function(data){
			if(data.data){
				scope.zichan=data.data;
			}else{
				scope.zichan=0;
			}
		}).error(function(error){
	        console.log("应用中心的话费充值报错！");
		});

	scope.pays=[{id:10,'name':'10元话费充值'},{id:20,'name':'20元话费充值'},{id:30,'name':'30元话费充值'},{id:40,'name':'40元话费充值'},{id:50,'name':'50元话费充值'}];
	scope.fangshi=[{'type':1,'name':'人民币'}];
    scope.zhuangtai=['已完成','充值中'];

	scope.chongzhi=function(){
		 if(!(/^1[34578]\d{9}$/.test(scope.myphone))){
	            scope.tis="*请输入正确的手机号码！"	
	            return false;
	        };
	     if(scope.mypay==undefined){
		    	 scope.tis="*请选择充值金额！"	
			     return false;
		 }
	     if(scope.myfangshi==undefined){
	    	 scope.tis="*请选择付款方式！"	
		     return false;
	     }
		scope.myphone1=scope.myphone-0;
		scope.mypwd1=scope.mypwd-0;
	 	
		postInfo.dataInfos(rootScope.ip+"/bill/billRecharge.do",
				{'userNo':rootScope.userNo,'phone':scope.myphone1,
			'price':scope.mypay,'payType':scope.myfangshi,'dealPwd':scope.mypwd1,"token":rootScope.tt})
		.success(function(data){
			if(data.code=="22"){
		    	 scope.tis="*请输入正确的交易密码！"	
			     return false;
			}else if(data.code=="100"){
				window.location.reload();
			}
		}).error(function(error){
	        console.log("应用中心的话费充值报错！");
		});
	};
	scope.changetype=function(a){
		if(a==1){
			return "人民币"
		}else{
			return " ";
		}
	}
	postInfo.dataInfos(rootScope.ip+"/bill/getBillInfo.do",{'userNo':rootScope.userNo,"token":rootScope.tt})
	.success(function(data){
		scope.jilu=data.data;
		
//		for(var i=0;i<scope.jilu.length;i++){
////			if(scope.jilu[i].pay_type==1){
////				scope.pay_type="人民币";
////			}else{
////				scope.pay_type=" ";
////			}
//		}
	}).error(function(error){
        console.log("应用中心的话费充值报错！");
	});

}]);