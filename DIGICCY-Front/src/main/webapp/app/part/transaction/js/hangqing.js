app.controller("hangqing",["$scope","$http",function($scope,$http){

    $http({
        url:'../../transation.json',
        method:'GET'
    }).success(function(data){

        $scope.con=data.hangqing.con;
        $scope.con1=data.hangqing.con1;
        $scope.con2=data.hangqing.con2;
        $scope.con3=data.hangqing.con3;

    }).error(function(data){
        console.log(data);
    });

}]);