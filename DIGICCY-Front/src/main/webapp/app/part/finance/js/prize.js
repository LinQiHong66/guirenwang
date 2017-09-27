app.controller("prize",["$scope","$http","$rootScope","postInfo","getInfo",function (scope,http,rootScope,postInfo,getInfo){
    http({
        url:'../../finance.json',
        method:'GET'
    }).success(function(data){
        scope.pTitle=data.prize.pTitle;
        scope.pHeader=data.prize.pHeader;
    }).error(function(){
        console.log(data);
    });

    scope.zhuangtai=['进行中','已完成','全部'];
    postInfo.dataInfos(rootScope.ip+"/prize/getPrize.do",{'userNo':rootScope.userNo,'token':rootScope.tt})
    .success(function(data){
        scope.pUl=data.data;
    })
    .error(function(error){
        console.log("数据获取错误"+error);
    });
}]);