reg.controller('rnameCtrl',['$scope','$http','$rootScope',function(scope,http,rootscope){
	http({
		url:'../../register.json',
		method:'GET'
	})
	scope.ip =getIp;
	scope.final=function(){
		if(scope.name==null){
			scope.namemessage="姓名不能为空！";
			scope.nametype=false;
			return false;
		}if(scope.idcardtype==true){
			this.idcardtype=false;
			this.idcardmessage='身份证号码格式不正确';
			return false;
		}
		http({
			// url:scope.ip+'/reg/addUser.do',
			url:'http://localhost/reg/addUser.do',
			method:'POST',
			data:{
				password:rootscope.password,
				region:'',
				realName:rootscope.realname,
				certificateNum:rootscope.certificateNum,
				dealPwd:rootscope.dealPwd,
				phone:rootscope.phone?rootscope.phone:0,
				invite_num:rootscope.invite_num || ''
			},
			headers:{'Content-Type':'application/x-www-form-urlencoded'},
	        transformRequest: function (data) {
	        	return $.param(data)
	        }
		}).success(function(data){
			console.log(data);
			location.href='#/final'
				
		}).error(function(data){
			console.log(data);
		})
		
	}
	scope.nametxt=function(){
		if(this.name==""){
			this.namemessage="姓名不能为空！";
			this.nametype=false;
		}else{
			this.namemessage="姓名输入正确";
			this.nametype=true;
			rootscope.realname=this.name;//真实姓名
			
		}
	}
	scope.idcard=function(){
		var idzz=/(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}$)/;
		var val=idzz.test(this.idc);
		if(!val){
			this.idcardtype=false;
			this.idcardmessage='身份证号码格式不正确';
		}else{
			this.idcardtype=true;
			this.idcardmessage='身份证号码格式正确';
			rootscope.certificateNum=this.idc;//身份证号码
		}
	}
}])