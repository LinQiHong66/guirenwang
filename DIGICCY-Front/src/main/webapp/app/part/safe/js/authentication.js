app.controller('authCtrl',["$scope","$http","$rootScope","postInfo","getInfo",function (scope,http,rootScope,postInfo,getInfo) {

	http({
		url:'../../safe.json',
		method:'GET'
	})
	.success(function(data){
		// 上面的数据
		scope.auTit=data.auth.tit;
		scope.auInfo=data.auth.info;
		scope.auName1=data.auth.data1.name[0]+" :";
		/*scope.auName2=data.auth.data1.name[1];*/
		scope.auType1=data.auth.data1.type[0]+"  :";
		/*scope.auType2=data.auth.data1.type[1];*/
		scope.auId1=data.auth.data1.IDCard[0]+" :";
		/*scope.auId2=data.auth.data1.IDCard[1];*/
		scope.auTime1=data.auth.data1.time[0]+" :";
		/*scope.auTime2=data.auth.data1.time[1];*/
		// 下面的数据
		scope.auInfo2=data.auth.info2;
		scope.auData0=data.auth.data2[0];
		scope.auData1=data.auth.data2[1];
		scope.auData2=data.auth.data2[2];
		scope.auData3=data.auth.data2[3];
		scope.auData4=data.auth.data2[4];
		scope.auData5=data.auth.data2[5];
	})

	postInfo.dataInfos(rootScope.ip+"/inesvuser/getUserInfoByUserNo.do",{'userNo':rootScope.userNo,"token":rootScope.tt})  
	.success(function (data) {
		scope.userInfo=data.userInfo;
		scope.auName2=scope.userInfo.real_name;
		scope.auType2=scope.userInfo.certificate_type;
		scope.auId2=scope.userInfo.certificate_num.substr(0, 5) + '*****' + scope.userInfo.certificate_num.substr(-4);
		scope.auTime2=scope.userInfo.date;
	})
	.error(function (data) {
		console.log("安全中心实名认证报错")
	});
	scope.phFlag=false;
	scope.shangchaung=function () {
		scope.phFlag=true;
	};
}]);



