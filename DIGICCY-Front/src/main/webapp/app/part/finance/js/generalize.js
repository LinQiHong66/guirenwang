app.controller("generalize",["$scope","$http","$rootScope","postInfo","getInfo",function (scope,http,rootScope,postInfo,getInfo){

    http({
        url:'../../finance.json',
        method:'GET'
    }).success(function(data){
        
        window.localStorage['foo1']=data.generalize.grHeader.foo1.split('=')[1];
        scope.geTitle=data.generalize.geTitle;
        scope.grHeader=data.generalize.grHeader;
    }).error(function(){
        console.log(data);
    });

    //  用户邀请码接口
    postInfo.dataInfos(rootScope.ip+"/spread/invite.do",{'userNo':rootScope.userNo,'token':rootScope.tt})
    .success(function(data){
        scope.fo1=data.data[0].invite_num;
        //判断是否有邀请码
        if(scope.fo1==false){
        	 postInfo.dataInfos(rootScope.ip+"/recUser/getRecCode.do",{'userNo':rootScope.userNo,'token':rootScope.tt})
    	    .success(function(data){
    	        console.log(data)
    	        scope.fo1=data.inviteCode;
    	        scope.grHeader.foo11='http://'+location.href.split('/')[2]+'/?invit='+scope.fo1;
    	        scope.grHeader.fooo111='中国人自己的比特币 http://'+location.href.split('/')[2]+'/?invit='+scope.fo1;
    	        $("#code").qrcode({
    	            render: "table",
    	            width: 200,
    	            height:200,
    	            text: scope.grHeader.foo11
    	        });
    	    }).error(function(data){
    	        console.log(data);
    	    });
        }else{
	        scope.grHeader.foo11='http://'+location.href.split('/')[2]+'/?invit='+scope.fo1;
	        scope.grHeader.fooo111='中国人自己的比特币 http://'+location.href.split('/')[2]+'/?invit='+scope.fo1;
	        $("#code").qrcode({
	            render: "table",
	            width: 200,
	            height:200,
	            text: scope.grHeader.foo11
	        });
        };
    }).error(function(data){
        console.log(data);
    });
}]);