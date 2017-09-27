app.controller("shiftTo",["$scope","$http","$rootScope","postInfo","getInfo",function (scope,http,rootScope,postInfo,getInfo){
    http({
        url:'../../finance.json',
        method:'GET'
    }).success(function(data){
        scope.sfTitle=data.shiftTo.sfTitle;
        scope.sfHeader=data.shiftTo.sfHeader;
        scope.sfH5=data.shiftTo.sfH5;
        scope.sfUlHeader=data.shiftTo.sfUlHeader;
      
    }).error(function(){
        console.log(data);
    });

    scope.buybuy=[];
    scope.sft=10;
    //货币类型
    postInfo.dataInfos(rootScope.ip+"/common/getBasicCoinTypeExceptRMB.do",{'token':rootScope.tt})
    .success(function(data){
        scope.bitype=data.coinTypeList;
        scope.buybuy=data.coinTypeList;
    })
    .error(function(error){
        console.log("select数据获取错误"+error);
    });
    //比特币可用金额和转入地址和记录
    postInfo.dataInfos(rootScope.ip+"/virtual/goVirtualRecharge.do",{'username':rootScope.username,'userNo':rootScope.userNo,'coinType':'10','token':rootScope.tt})    
    .success(function(data){
        scope.qian=data.coin
        scope.address=data.address;
        scope.sfUl=data.data;
        $("#code").qrcode({
            render: "table",
            width: 200,
            height:200, 
            text:data.address
        });
     })
     .error(function(error){
         console.log(error);
     });
    
    scope.biChange=function(sft){
        //虚拟币可用金额和转入地址和记录
        postInfo.dataInfos(rootScope.ip+"/virtual/goVirtualRecharge.do",{'username':rootScope.username,'userNo':rootScope.userNo,'coinType':scope.sft,'token':rootScope.tt})    
        .success(function(data){
        	scope.qian=data.coin
            scope.address=data.address;
            scope.sfUl=data.data;
            $('#code').children().remove();
           if(data.address){
        	   $("#code").qrcode({
                   render: "table",
                   width: 200,
                   height:200,
                   text: data.address
               });
           }
        })
        .error(function(error){
            console.log(error);
        });
    }
}]);