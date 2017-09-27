app.controller("xiangxi",["$scope","$http","$rootScope","postInfo",function (scope,http,rootScope,postInfo){
//  获取文章
    postInfo.dataInfos(rootScope.ip+"/notice/getNoticeById.do",{'id':window.sessionStorage['articleId']||1})    
    .success(function(data){
        scope.artdata=data.data;
    }).error(function(data){
        console.log(data);
    });
    
}]);