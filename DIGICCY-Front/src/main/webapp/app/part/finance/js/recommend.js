app.controller("recommend",["$scope","$http","$rootScope","postInfo","getInfo",function (scope,http,rootScope,postInfo,getInfo){
    http({
        url:'../../finance.json',
        method:'GET'
    }).success(function(data){

        scope.rcTitle=data.recommend.rcTitle;
        scope.rcHeader=data.recommend.rcHeader;

    }).error(function(){
        console.log(data);
    });

    
    postInfo.dataInfos(rootScope.ip+"/recommend/getMyrec.do",{'userNo':rootScope.userNo,'token':rootScope.tt})
    .success(function(data){
        scope.rcUl=data.data;
    })
    .error(function(error){
        console.log(error);
    });
}]);