app.controller('dbtCtrl',["$scope","$http","$rootScope","postInfo","getInfo",function (scope,http,rootScope,postInfo,getInfo){

	http({
		url:'../../safe.json',
		method:"GET"
	})
	.success(function(data){
		console.log(data);
		scope.tit=data.dbtest.tit;
		scope.fData0=data.dbtest.data1[0];
		scope.fData1=data.dbtest.data1[1];
		scope.fData2=data.dbtest.data1[2];
		scope.fData3=data.dbtest.data1[3];
		scope.fData4=data.dbtest.data1[4];
		scope.fData5=data.dbtest.data1[5];
		scope.fData6=data.dbtest.data1[6];

		scope.sData0=data.dbtest.data2[0];
		scope.sData1=data.dbtest.data2[1];
		scope.sData2=data.dbtest.data2[2];
	});
	
	postInfo.dataInfos(rootScope.ip+"/inesvuser/upValidatePwd.do",{'userNo':rootScope.userNo,"token":rootScope.tt,'validate_pwd':789456123})  
	.success(function(data){
	})
	.error(function(data){
		console.log(data)
	});
}]);