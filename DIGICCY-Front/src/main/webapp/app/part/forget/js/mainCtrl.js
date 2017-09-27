// var regApp=angular.module('regApp',[]);
reg.controller("mainCtr",["$scope","$http",'$rootScope',function (scope,http,rootscope) {
    scope.tiaoF=function(){
        if(!window.sessionStorage['username']){
            if(!window.sessionStorage['loginflag']){
                 window.sessionStorage['loginflag']=true;
            }
            window.location="../../index.html";
        }else{
            window.location="index.html";
        }
    };
    scope.tiaoS=function(){
        if(!window.sessionStorage['username']){
            if(!window.sessionStorage['loginflag']){
                 window.sessionStorage['loginflag']=true;
            }
            window.location="../../index.html";
        }else{
            window.location="../safe/index.html";
        }
    };
    scope.tiaoA=function(){
        if(!window.sessionStorage['username']){
            if(!window.sessionStorage['loginflag']){
                 window.sessionStorage['loginflag']=true;
            }
            window.location="../../index.html";
        }else{
            window.location="../apply/index.html";
        }
    };
    
	rootscope.username='';//用户名
	rootscope.password='';//用户密码
	rootscope.region='';//所属地
	rootscope.realname='';//真实姓名
	rootscope.certificateNum='';//身份证号码
	rootscope.dealPwd='';//交易密码
	rootscope.mail='';//邮箱地址
	rootscope.phone='';//手机号码
	rootscope.invite_num='';//邀请码
    http({
        url:'../../register.json',
        method:'GET'
    }).success(function(data){
        scope.logo = data.header.logo;
        scope.title = data.header.biaoti;
        scope.home = data.header.title[0];
        scope.transaction = data.header.title[1];
        scope.finance = data.header.title[2];
        scope.safe = data.header.title[3];
        scope.apply = data.header.title[4];
        scope.shop = data.header.title[5];
        scope.help = data.header.title[6];
        scope.spread = data.header.title[7];
        scope.jiedai = data.header.title[8];
    }).error(function(data){
        console.log(data);
    });

}]);