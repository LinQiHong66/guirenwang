app.controller("reCard",["$scope","$http","$rootScope","postInfo","getInfo",function (scope,http,rootScope,postInfo,getInfo){
    http({
        url:'../../finance.json',
        method:'GET'
    }).success(function(data){
        scope.rtitle=data.reCard.rtitle;
        scope.re=data.reCard.rinfo;
        scope.card=data.reCard.card;

    }).error(function(){
        console.log(data);
    });

     //充值
    scope.tanchu={'display':'none'};
    scope.tijiao=function(){
       postInfo.dataInfos(rootScope.ip+"/rmbRecharge/goRmbRecharge.do",{'userNo':rootScope.userNo,'recType':scope.cz,'recharge_price':scope.money,'status':'1','token':rootScope.tt})
        .success(function(data){
            postInfo.dataInfos(rootScope.ip+"/rmbRecharge/getRmbRechargeInfo.do",{'userNo':rootScope.userNo,'coinType':'10','token':rootScope.tt})
            .success(function(data){
                scope.num1=data.data;
                scope.reinfo=data.list;
                scope.releng=data.list.length;
            })
            .error(function(error){
                console.log(error);
            });

            scope.tanchu={'display':'block'};
            if(data.desc==='success'){
                scope.tanchuinfo='提交成功，请去处理';
            }else{
                scope.tanchuinfo='系统繁忙，提交失败，请稍后再试';
            }
        })
        .error(function(error){
              console.log(error);
        }); 
    };
    scope.closetc=function(){
        scope.tanchu={'display':'none'};
    };
     
     scope.retype=["支付宝转账支付","微信转账支付","网银转账支付"];
     scope.state=['处理中','已完成','待付款'];
    //记录
    postInfo.dataInfos(rootScope.ip+"/rmbRecharge/getRmbRechargeInfo.do",{'userNo':rootScope.userNo,'coinType':'10','token':rootScope.tt})
    .success(function(data){
        scope.num1=data.data;
        scope.reinfo=data.list;
        scope.releng=data.list?data.list.length:'';
    })
    .error(function(error){
        console.log(error);
    });

/*    scope.setChangejl=function(){
        console.log(scope.aa);
        postInfo.dataInfos(rootScope.ip+"/rmbRecharge/updateStatus.do",{'userNo':rootScope.userNo,'status':scope.aa,'token':rootScope.tt})
        .success(function(data){
            scope.num1=data.data;
            scope.reinfo=data.list;
            scope.releng=data.list.length;
        })
        .error(function(error){
            console.log(error);
        });
    };*/



}]);