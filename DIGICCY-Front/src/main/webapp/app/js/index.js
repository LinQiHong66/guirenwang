var app=angular.module('myApp',[]);
app.controller("MainController",["$scope","$interval","$http","$rootScope","postInfo","getInfo",'$timeout',function (scope,interval,http,rootScope,postInfo,getInfo,timeout){
    function aaa(){
    	if(window.sessionStorage){
    		window.sessionStorage['inviteCode']='';
    		var url=location.href;
    		if(url.indexOf('invit')>-1){
    			window.sessionStorage['inviteCode']=url.substring(url.indexOf('invit')+6);
    		}
    	}
    }
    aaa();

     scope.tiaoF=function(){
        if(!window.sessionStorage['username']){
            if(!window.sessionStorage['loginflag']){
                 window.sessionStorage['loginflag']=true;
            }
            timeout(function(){
                scope.loginflag=window.sessionStorage.loginflag;
            },500);
        }else{
            window.location="part/finance/index.html";
        }
    };

     scope.tiaoS=function(){
        if(!window.sessionStorage['username']){
            if(!window.sessionStorage['loginflag']){
                 window.sessionStorage['loginflag']=true;
            }
            timeout(function(){
                scope.loginflag=window.sessionStorage.loginflag;
            },500);
        }else{
            window.location="part/safe/index.html";
        }
    };
  //首页每日行情去交易点击事件
    scope.gotojy=function(){
    	console.log(getIp);
    	  if(!window.sessionStorage['username']){
              if(!window.sessionStorage['loginflag']){
                   window.sessionStorage['loginflag']=true;
              }
              
              timeout(function(){
                  scope.loginflag=window.sessionStorage.loginflag;
              },500);
          }else{
              window.location="part/transaction/index.html#/jiaoyi";
          }
    }
    //rootScope.ip="http://192.168.10.242:9999";
    rootScope.ip=getIp;
    rootScope.tt=window.sessionStorage.token;
    rootScope.userNo=window.sessionStorage.userNo;
    var wh=$(window).height();
    $(window).scroll(function(){
        var ws=$(window).scrollTop();
        if(ws>240){
            $('.home_header').css('background','rgba(3, 59, 187, 0.8)');
        }else{
            $('.home_header').css('background','rgba(0,0,0,0.15)');
        }

    });

    scope.liIndex=0;
    scope.change=function(e){
        if(e.target.nodeName.toLowerCase()=="li"){
            var li= e.target;
            scope.liIndex=li.dataset.index;
        }
    };
    scope.zuo=function(e){
        scope.liIndex=scope.liIndex-1;
        if(scope.liIndex<0){
            scope.liIndex=3;
        }
    };
    scope.you=function(e){
        scope.liIndex=scope.liIndex+1;
        if(scope.liIndex>3){
            scope.liIndex=0;
        }
    };
    scope.timer=interval(tn,2000);
    function tn(){
        scope.liIndex=scope.liIndex+1;
        if(scope.liIndex>3){
            scope.liIndex=0;
        }
    }
    scope.mouse=function(){
        scope["xianshi"]={display:"block"};
        interval.cancel(scope.timer);
    };
    scope.out=function(){
        scope["xianshi"]={display:"none"};
        scope.timer=interval(tn,2000);
    };


   scope.loginflag=window.sessionStorage.loginflag;
   scope.closebox=function(){
      scope.loginflag=false;
   }
   scope.dlshow=function(){
	   if(!window.sessionStorage['loginflag']){
		   scope.loginflag=true;
      }
   }

   scope["loginh"]={display:"none"};
   scope["loginq"]={display:"block"};
   scope["namexianshi"]={display:"none"};
 /*  $("langa").hover(function(){
	    $("langbox").css("display","block");
	},function(){
	    $("langbox").css("display","none");
	});*/
   
   
  scope.upLoginIndex=function(){
       postInfo.dataInfos(rootScope.ip+"/user/login.do",{'username':scope.username,'password':scope.userpassword})
       .success(function(data){         
            if(data.code==100){
                window.sessionStorage['username']=data.loginUserInfo.username;
                window.sessionStorage['phone']=data.loginUserInfo.phone;
                window.sessionStorage['id']=data.loginUserInfo.id;
                window.sessionStorage['date']=data.loginUserInfo.date;
                window.sessionStorage['token']=data.token;
                window.sessionStorage['userNo']=data.loginUserInfo.user_no;
                scope["loginh"]={display:"block"};
                scope["loginq"]={display:"none"};
                scope.loginInfo=data.loginUserInfo;
                window.sessionStorage['loginflag']=false;
                scope.loginflag=window.sessionStorage.loginflag;

            }else if(data.code==200){
                scope["namexianshi"]={display:"block"};
            }else{
                console.log('错误');
            }
        }).error(function(error){
            console.log(error);
        });
   };
   scope.outlogin=function(){
        scope["loginh"]={display:"none"};
        scope["loginq"]={display:"block"};
        window.sessionStorage.clear();
   };
   
   if(window.sessionStorage['username']){  
       scope["loginh"]={display:"block"};
       scope["loginq"]={display:"none"};
        scope.loginInfo=window.sessionStorage;
    
    }else{  
        scope["loginh"]={display:"none"};
        scope["loginq"]={display:"block"};
        
    };
/*

    getInfo.dataInfos(rootScope.ip+"/trade/getAssessList.do",{token:'96E79218965EB72C92A549DD5A330112'})
    .success(function(data){
        console.log(data);//令牌正确的数据
    });
    
    getInfo.dataInfos(rootScope.ip+"/trade/getAssessList.do",{token:1111})
    .success(function(data){
        console.log(data);//令牌不对
    });
*/
    http({
        url:'shuju.json',
        method:'GET'
    }).success(function(shuju){
        var data=shuju.chinese;
        scope.lg='chinese';
        scope.changelg=function(){
            if(scope.lg=='chinese'){
                scope.lg='english';
                data=shuju.english;
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
                scope.jiedai=data.header.title[8];
                scope.list = data.home_banner;
                scope.qq=data.home_notice.qq;
                scope.wb=data.home_notice.wb;
                scope.qun=data.home_notice.qun;
                scope.market=data.home_market.today;
                scope.market_price=data.home_market.price;
                scope.titleP="knockdown";
                scope.desc=0;
                scope.infoL=data.home_info.infoL;
                scope.infoM=data.home_info.infoM;
                scope.infoR=data.home_info.infoR;
            }else{
                scope.lg='chinese';
                data=shuju.chinese;
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
                scope.jiedai=data.header.title[8];
                scope.list = data.home_banner;
                scope.qq=data.home_notice.qq;
                scope.wb=data.home_notice.wb;
                scope.qun=data.home_notice.qun;
                scope.market=data.home_market.today;
                scope.market_price=data.home_market.price;
                scope.titleP="knockdown";
                scope.desc=0;
                scope.infoL=data.home_info.infoL;
                scope.infoM=data.home_info.infoM;
                scope.infoR=data.home_info.infoR;
            }
            return data;
        };
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
        scope.jiedai=data.header.title[8];
        scope.list = data.home_banner;
        scope.qq=data.home_notice.qq;
        scope.wb=data.home_notice.wb;
        scope.qun=data.home_notice.qun;
        scope.market=data.home_market.today;
        scope.market_price=data.home_market.price;
        scope.titleP="knockdown";
        scope.desc=0;
        scope.infoL=data.home_info.infoL;
        scope.infoM=data.home_info.infoM;
        scope.infoR=data.home_info.infoR;
    }).error(function(data){
        console.log(data);
    });
    
//    公告
    http({
        url:rootScope.ip+'/notice/getNoticeByType.do',
        method:'POST',
        data:{
        	'type':1,
//        	'token':window.sessionStorage['token']
        },
        headers:{'Content-Type':'application/x-www-form-urlencoded'},    
        transformRequest: function(obj) {    
            var str = [];    
            for (var p in obj) {    
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));    
            }    
            return str.join("&");    
        }
    }).success(function(data){
        scope.ggtit=data.data;
    }).error(function(data){
        console.log(data);
    });
    
//  行业资讯
    http({
        url:rootScope.ip+'/notice/getNoticeByType.do',
        method:'POST',
        data:{
        	'type':2,
//        	'token':window.sessionStorage['token']
        },
        headers:{'Content-Type':'application/x-www-form-urlencoded'},    
        transformRequest: function(obj) {    
            var str = [];    
            for (var p in obj) {    
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));    
            }    
            return str.join("&");
        }
    }).success(function(data){
        scope.zxtit=data.data;
    }).error(function(data){
        console.log(data);
    });
    
//  常见问题
    http({
        url:rootScope.ip+'/notice/getNoticeByType.do',
        method:'POST',
        data:{
        	'type':3,
        },
        headers:{'Content-Type':'application/x-www-form-urlencoded'},    
        transformRequest: function(obj) {    
            var str = [];    
            for (var p in obj) {    
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));    
            }    
            return str.join("&");
        }
    }).success(function(data){
        scope.wttit=data.data;
    }).error(function(data){
        console.log(data);
    });
    
    scope.xiangxi=function(a){
    	if(window.sessionStorage){
		      window.sessionStorage['articleId']=a;
		      window.open("./part/help/index.html#/xiangxi");
		  }else{
		      alert('您的浏览器版本太低，请升级新版chrome浏览器或使用更安全的360浏览器');
		  }
    }
    
//  每日行情
    http({
        url:rootScope.ip+'/dayMarket/getDayMarket.do',
        method:'POST',
        headers:{'Content-Type':'application/x-www-form-urlencoded'},    
        transformRequest: function(obj) {    
            var str = [];    
            for (var p in obj) {    
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));    
            }    
            return str.join("&");
        }
    }).success(function(data){
    	scope.hangqing=data.dayMarketList;
    }).error(function(data){
        console.log(data);
    });
    
//  获取币种名
    http({
        url:rootScope.ip+'/notice/getBasicCoinTypeExceptRMB.do',
        method:'POST',
        data:{
//        	'token':window.sessionStorage['token']
        },
        headers:{'Content-Type':'application/x-www-form-urlencoded'},    
        transformRequest: function(obj) {    
            var str = [];    
            for (var p in obj) {    
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));    
            }    
            return str.join("&");
        }
    }).success(function(data){
    	scope.gecoin=data.coinTypeList;
    	timeout(function(){
    		for(var i=0;i<scope.gecoin.length;i++){
	    		for(var j=0;j<scope.hangqing.length;j++){
	    			if(scope.hangqing[j].coin_type==scope.gecoin[i].coin_no){
	    				scope.hangqing[j].coin_type=scope.gecoin[i].coin_name;
	    			}
				}
    		}
    	},10);
    }).error(function(data){
        console.log(data);
    });
}]);