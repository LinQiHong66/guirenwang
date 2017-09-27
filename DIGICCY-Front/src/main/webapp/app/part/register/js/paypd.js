reg.controller('paypdCtrl',['$scope','$http','$rootScope',function(scope,http,rootscope){
	http({
		url:'../../register.json',
		method:'GET'
	})
	console.log(rootscope.invite_num)
	// 验证密码是否在6到18位
	scope.jypd=function(){
		var pdzz=/^\w{6,18}$/;
		var val=pdzz.test(this.jypdval);
		if(!val){
			this.jypdtype=false;
			this.jypdmessage='密码请在6-18位之间';
		}else{
			this.jypdtype=true;
			this.jypdmessage='密码格式正确';
		}
	}
	// 验证两次密码输入是否一致
	scope.againjypd=function(){
		if(this.jypdval!=this.againjypdval){
			this.againjypdtype=false;
			this.againjypdmessage='两次密码输入不一致';
		}else{
			this.againjypdtype=true;
			this.againjypdmessage='两次密码一致';
			rootscope.dealPwd=this.againjypdval;//交易密码
		}
	}
	// 跳转到下一页
	scope.rname=function(){
		if(scope.jypdval!=null && scope.againjypdval!=null){
			location.href='#/rname';
		}else{
			scope.jypdtype=false;
			scope.jypdmessage='密码请在6-18位之间';
			scope.againjypdtype=false;
			scope.againjypdmessage='两次密码输入不一致';
		}
	}
}])