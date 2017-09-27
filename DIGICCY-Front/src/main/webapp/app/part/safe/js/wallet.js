app.controller('wlCtrl',['$scope','$http','$timeout',"$rootScope","postInfo","getInfo",function(scope,http,timeout,rootScope,postInfo,getInfo){

	scope.hsHowFlg=false;
	scope.bindphoneflg=false;
	http({
		url:'../../safe.json',
		method:'GET'
	})
	.success(function(data){
		scope.tit=data.wallet.tit;
		scope.tbtit=data.wallet.tbtit;
		scope.addaddress=data.wallet.addaddress;
	});
	/*关闭弹出框*/
	scope.close=function(){
		scope.hsHowFlg=false;
	};
	/*渲染地址*/
	scope.xuanran=function () {
		postInfo.dataInfos(rootScope.ip+"/Safety/getAddress.do",{'userNo':rootScope.userNo,"token":rootScope.tt})
			.success(function(data){
				scope.tbdata=data.data;
				scope.coin_noA=scope.tbdata?scope.tbdata.coin_no:null;
				postInfo.dataInfos(rootScope.ip+"/common/getCoin.do",{'userNo':rootScope.userNo,"token":rootScope.tt})
					.success(function(data){
						scope.conDataA=data.data;
						timeout(function () {
							try{
								for(var i=0;i<scope.tbdata.length;i++){
									for(var j=0;j<scope.conDataA.length;j++){
										if(scope.tbdata[i].coin_no==scope.conDataA[j].coin_no){
											scope.tbdata[i].coin_no=scope.conDataA[j].coin_name;
										};
									};
								};
							}catch(err){
								console.log(err);
							}
							
						},100);
					})
					.error(function(data){
						console.log("查询币种名称报错！")
					});
			})
			.error(function(data){
				console.log("渲染地址报错！")
			});
	};
	scope.xuanran();
	/*添加地址选择币种*/
	scope.notecase=function(){
		scope.hsHowFlg=true;
		postInfo.dataInfos(rootScope.ip+"/common/getCoin.do",{'userNo':rootScope.userNo,"token":rootScope.tt})
		.success(function(data){
			scope.conData=data.data;
		})
		.error(function(data){
			console.log("查询币种名称报错！")
		});
	};
	/*币种选择改变*/
	var temp=true;
	scope.changeA=function(num){
		scope.num=num;
		temp=false;
	};
	/*立即添加地址*/
	scope.superinduce=function(){
		if(temp==true){
			scope.bindphoneflg=true;
			scope.bindphoneTip="请选择币种名称!";
			timeout(function(){
				scope.bindphoneflg=false;
			},1000);
			return false;
		}else{
			if(scope.num==null){
				scope.bindphoneflg=true;
				scope.bindphoneTip="请选择币种名称!";
				timeout(function(){
					scope.bindphoneflg=false;
				},1000);
				return false;
			};
		};
		if(scope.charact!=null){
			if(scope.charact.length<0||scope.charact.length==0){
				scope.bindphoneflg=true;
				scope.bindphoneTip="请输入钱包标识!";
				scope.charact="";
				timeout(function(){
					scope.bindphoneflg=false;
				},1000);
				return false;
			};
		}else{
			scope.bindphoneflg=true;
			scope.bindphoneTip="请输入钱包标识!";
			scope.charact="";
			timeout(function(){
				scope.bindphoneflg=false;
			},1000);
			return false;
		};
		if(scope.site!=null){
			if(scope.site.length<0||scope.site.length==0){
				scope.bindphoneflg=true;
				scope.bindphoneTip="请输钱包地址!";
				scope.site="";
				timeout(function(){
					scope.bindphoneflg=false;
				},1000);
				return false;
			};
		}else{
			scope.bindphoneflg=true;
			scope.bindphoneTip="请输入钱包地址!";
			timeout(function(){
				scope.bindphoneflg=false;
			},1000);
			return false;
		};
		if(scope.passCoin!=null){
			if(scope.passCoin.length<0||scope.passCoin.length==0){
				scope.bindphoneflg=true;
				scope.bindphoneTip="请输入交易密码!";
				scope.passCoin="";
				timeout(function(){
					scope.bindphoneflg=false;
				},1000);
				return false;
			}else{
				postInfo.dataInfos(rootScope.ip+"/common/getTradePassword.do",{'userNo':rootScope.userNo,"token":rootScope.tt,'dealPwd':scope.passCoin})  
				.success(function(data){
					if(data.code==22){
						scope.bindphoneflg=true;
						scope.bindphoneTip="请输入正确的交易密码!";
						scope.passCoin="";
						timeout(function(){
							scope.bindphoneflg=false;
						},1000);
						return false;
					}else if(data.code==200){
						scope.bindphoneflg=true;
						scope.bindphoneTip="请输入正确的交易密码!";
						scope.passCoin="";
						timeout(function(){
							scope.bindphoneflg=false;
						},1000);
					}else if(data.code==100){
						postInfo.dataInfos(rootScope.ip+"/Safety/addAddress.do",
							{'userNo':rootScope.userNo,"token":rootScope.tt,"coinType":scope.coinn,"walletLogo":scope.charact,"address":scope.site,"dealPwd":scope.passCoin})
							.success(function(data){
								console.log(data.code)
								if(data.code==100){
									scope.hsHowFlg=false;
									timeout(function () {
										scope.xuanran();
									},100);
								}else if(data.code==200){
									scope.bindphoneflg=true;
									scope.bindphoneTip="请输入正确的钱包地址!";
									scope.site="";
									timeout(function(){
										scope.bindphoneflg=false;
									},1000);
									return false;
								};
							})
							.error(function(data){
								console.log("添加钱包地址报错！")
							});
					}
				})
				.error(function(data){
					console.log("安全中心的钱包管理添加钱包地址的交易密码报错")
				});
			};
		}else{
			scope.bindphoneflg=true;
			scope.bindphoneTip="请输入交易密码!";
			timeout(function(){
				scope.bindphoneflg=false;
			},1000);
			return false;
		};
	};
	/*删除地址*/
	scope.deleteDz=function (add) {
		postInfo.dataInfos(rootScope.ip+"/Safety/getAddress.do",{'userNo':rootScope.userNo,"token":rootScope.tt})
			.success(function(data){
				scope.addz=data.data[add].address;
					postInfo.dataInfos(rootScope.ip+"/Safety/deleteAddress.do",{'userNo':rootScope.userNo,"token":rootScope.tt,"address":scope.addz})
					.success(function(data){
						timeout(function () {
							scope.xuanran();
						},100);
					})
					.error(function(data){
						console.log("删除币种名称报错！")
					});
			})
			.error(function(data){
				console.log("查询币种名称报错！")
			});
		
	}
}]);