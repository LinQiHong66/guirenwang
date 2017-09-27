app.controller("pingjia",["$scope","$http",function($scope,$http){
    $http({
        url:'../../transation.json',
        method:'GET'
    }).success(function(data){

        $scope.pjTitle=data.pingjia.pjTitle;
        $scope.pjHeader=data.pingjia.pjHeader;
        $scope.link=data.pingjia.link;
        $scope.dp=data.pingjia.detail;
        $scope.myWidth1={"width":(data.pingjia.pjHeader.nums/5)*100+"%"};
        $scope.myWidth2={"width":(data.pingjia.pjHeader.numa/5)*100+"%"};
        $scope.myWidth3={"width":(data.pingjia.pjHeader.numf/5)*100+"%"};
        var xnum=data.pingjia.pjHeader.xnum;
        var arr=[];
        for(var i=0;i<=xnum;i++){
           arr.push(i);
        }
        $scope.xarr=arr;

    }).error(function(data){
        console.log(data);
    });

    var msgaaa=$("#msgaaa").val();
    var xnb="{$xnb}";
    var s1=$(':radio[name="s1"]:checked').val();
    var s2=$(':radio[name="s2"]:checked').val();
    var s3=$(':radio[name="s3"]:checked').val();
    $scope.upcomment=function(){
        $http({
            url:'../../finance.json',
            method:'GET',
            data:{
                msgaaa:msgaaa,
                s1:s1,
                s2:s2,
                s3:s3,
                xnb:xnb
            }
        }).success(function(data){


        }).error(function(){
            console.log(data);
        });
    };
}]);
