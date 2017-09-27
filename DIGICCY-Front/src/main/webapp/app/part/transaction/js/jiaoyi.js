app.controller("jiaoyi",["$scope","$http","$rootScope","postInfo","getInfo","$timeout",function (scope,http,rootScope,postInfo,getInfo,timeout){
         
 
    scope.buycoinType=10;
    scope.bindphoneflg=false;
    http({
        url:'../../transation.json',
        method:'GET'
    }).success(function(data){

        scope.b2Name=data.jiaoyi.box2.nav[0];
        scope.b2Price=data.jiaoyi.box2.nav[1];
        scope.b2Num=data.jiaoyi.box2.nav[2];
        scope.b2Rmb=data.jiaoyi.box2.nav[3];

        scope.wtTitle=data.jiaoyi.weituo.wtTitle;
        scope.wtTime=data.jiaoyi.weituo.wtTime;
        scope.wtMaimai=data.jiaoyi.weituo.wtMaimai;
        scope.wtPrice=data.jiaoyi.weituo.wtPrice;
        scope.wtNum=data.jiaoyi.weituo.wtNum;
        scope.wtOver=data.jiaoyi.weituo.wtOver;
        scope.wtAll=data.jiaoyi.weituo.wtAll;
        scope.wtCaozuo=data.jiaoyi.weituo.wtCaozuo;

        scope.jlTitle=data.jiaoyi.jilu.jlTitle;
        scope.jlTime=data.jiaoyi.jilu.jlTime;
        scope.jlMaimai=data.jiaoyi.jilu.jlMaimai;
        scope.jlPrice=data.jiaoyi.jilu.jlPrice;
        scope.jlNum=data.jiaoyi.jilu.jlNum;
        scope.jlAll=data.jiaoyi.jilu.jlAll;

    }).error(function(data){
        console.log(data);
    });

    scope.hqflag={'display':'none'};
    scope.hangqing=function(){
    scope.hqflag={'display':'block'};

    }
    scope.guana=function(){
    scope.hqflag={'display':'none'};
    }

    scope.jiaoyicg={'display':'none'};
    scope.$watch('coinType',function(){
    //////折线图
    postInfo.dataInfos(rootScope.ip+"/dayMarket/selectDayMarket.do",{'coinType':rootScope.coinType,'token':rootScope.tt})
    .success(function(data){

        var myChart = echarts.init(document.getElementById('kLine'));
        app.title = '';
    console.log(rootScope.coinType);
    var dataArrs=[];


    function getkdata(obj) {
        postInfo.dataInfos(rootScope.ip+"/dealDetail/getDealDetail.do",{'priceType':rootScope.coinType,'detailType':obj})
        .success(function(data){
            dataArrs=[];
            console.log("--------------1");
            console.log(data.dealDetailList);
            console.log("--------------1");
            console.log(rootScope.coinType+"***********");
            var riqi="";
            
            for(var i=0;i<data.dealDetailList.length;i++){
                riqi=data.dealDetailList[i].begin_date;
                // riqi=riqi[0].replace(" ","");
                console.log(riqi)
                dataArrs.push([riqi,data.dealDetailList[i].begin_price.toString(),data.dealDetailList[i].end_price.toString(),data.dealDetailList[i].difference_price,data.dealDetailList[i].difference_percentage,data.dealDetailList[i].min_price.toString(),data.dealDetailList[i].max_price.toString(),data.dealDetailList[i].begin_date_num,data.dealDetailList[i].end_date_num]);
                
            }
            console.log(dataArrs);
            // console.log(data.dealDetailList.match(/.+\s/g));
            // scope.zongzichan=(scope.enablecoin+scope.unableType)*data.dayMarketList[0].newes_deal+ scope.rmb+scope.freezeRmb;
            // scope.newpb=data.dayMarketList[0].sell_price;
            // scope.newps=data.dayMarketList[0].buy_price;

            var dates = dataArrs.map(function (item) {
                return item[0];
            });
            var data = dataArrs.map(function (item) {
                return [+item[1], +item[2], +item[5], +item[6]];
            });
            scope.optionDta(dates,data);

        }).error(function(error){
            console.log(error);
        });
    }
    getkdata(1);


    /*  var min15=min30=min60=min24=min1;*/
    function calculateMA(dayCount, data) {
        var result = [];
        for (var i = 0, len = data.length; i < len; i++) {
            if (i < dayCount) {
                result.push('-');
                continue;
            }
            var sum = 0;
            for (var j = 0; j < dayCount; j++) {
                sum += data[i - j][1];
            }
            result.push(sum / dayCount);
        }
        return result;
    }

            //默认为1分钟
    var dates = dataArrs.map(function (item) {
        return item[0];
    });
    var data = dataArrs.map(function (item) {
        return [+item[1], +item[2], +item[5], +item[6]];
    });
    scope.optionDta=function (dates,data) {
    var option = {
        animation: false,
        backgroundColor: '#21202D',
        legend: {
            data: ['日K', 'MA5', 'MA10', 'MA20', 'MA30'],
            inactiveColor: '#777',
            textStyle: {
                color: '#fff'
            }
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                animation: false,
                type: 'cross',
                crossStyle: {
                    color: '#376df4',
                    width: 1,
                    opacity: 1
                }
            }
        },
        xAxis: {
            type: 'category',
            data: dates,
            axisLine: { lineStyle: { color: '#8392A5' } }
        },
        yAxis: {
            scale: true,
            axisLine: { lineStyle: { color: '#8392A5' } },
            splitLine: { show: false }
        },
        dataZoom: [{
            textStyle: {
                color: '#8392A5'
            },
            handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
            handleSize: '80%',
            dataBackground: {
                areaStyle: {
                    color: '#8392A5'
                },
                lineStyle: {
                    opacity: 0.8,
                    color: '#8392A5'
                }
            },
            handleStyle: {
                color: '#fff',
                shadowBlur: 3,
                shadowColor: 'rgba(0, 0, 0, 0.6)',
                shadowOffsetX: 2,
                shadowOffsetY: 2
            }
        }, {
            type: 'inside'
        }],
        animation: false,
        series: [
            {
                type: 'candlestick',
                name: '日K',
                data: data,
                itemStyle: {
                    normal: {
                        color: '#FD1050',
                        color0: '#0CF49B',
                        borderColor: '#FD1050',
                        borderColor0: '#0CF49B'
                    }
                }
            },
            {
                name: 'MA5',
                type: 'line',
                data: calculateMA(5, data),
                smooth: true,
                showSymbol: false,
                lineStyle: {
                    normal: {
                        width: 1
                    }
                }
            },
            {
                name: 'MA10',
                type: 'line',
                data: calculateMA(10, data),
                smooth: true,
                showSymbol: false,
                lineStyle: {
                    normal: {
                        width: 1
                    }
                }
            },
            {
                name: 'MA20',
                type: 'line',
                data: calculateMA(20, data),
                smooth: true,
                showSymbol: false,
                lineStyle: {
                    normal: {
                        width: 1
                    }
                }
            },
            {
                name: 'MA30',
                type: 'line',
                data: calculateMA(30, data),
                smooth: true,
                showSymbol: false,
                lineStyle: {
                    normal: {
                        width: 1
                    }
                }
            }
        ]
    };
    myChart.setOption(option);
};
    scope.optionDta(dates,data);



    scope.blockedOut=function (a) {
        if(a==12){  //日线
            getkdata(7)
        }else if(a==24){//8小时
            getkdata(6)
        }else if(a==60){//1小时
            getkdata(5)
        }else if(a==30){//30分钟
            getkdata(4)
        }else if(a==15){//15分钟
            getkdata(3)
        }else if(a==5){//5分钟
            getkdata(2)
        }else {//1分钟
            getkdata(1)
        }
    };
})
.error(function(error){
    console.log(error);
});
        
        
        /////币的种类 单位
        postInfo.dataInfos(rootScope.ip+"/common/getBasicCoinTypeExceptRMB.do",{'token':rootScope.tt})
        .success(function(data){ 
            for(var i=0;i<data.coinTypeList.length;i++){
              if(data.coinTypeList[i]['coin_no'] == rootScope.coinType ){
                 scope.biname=data.coinTypeList[i].coin_name;
                 scope.att1=data.coinTypeList[i].attr1;
              }
            }
        })
        .error(function(error){
            console.log("select币种数据获取错误"+error);
        });

      //////币的资产和人民币资产 + 买卖
     /*人民币资产查询*/
     postInfo.dataInfos(rootScope.ip+"/trade/getUserBalanceInfo.do",{'userNo':rootScope.userNo,'coinType':'0','token':rootScope.tt})
    .success(function (data) {
         try{
        	  scope.rmb=data.balanceInfo.enable_coin?data.balanceInfo.enable_coin:0;
              scope.freezeRmb=data.balanceInfo.unable_coin?data.balanceInfo.unable_coin:0;

               /*币种资产查询*/
               postInfo.dataInfos(rootScope.ip+"/trade/getUserBalanceInfo.do",{'userNo':rootScope.userNo,'coinType':rootScope.coinType,'token':rootScope.tt})
               .success(function (data) {
                   scope.enablecoin=data.balanceInfo?data.balanceInfo.enable_coin:0;
                   scope.unableType=data.balanceInfo?data.balanceInfo.unable_coin:0;

                    //每个币的信息
                   postInfo.dataInfos(rootScope.ip+"/dayMarket/selectDayMarket.do",{'coinType':rootScope.coinType,'token':rootScope.tt})
                   .success(function(data){
                       if(data.dayMarketList){
                       	scope.zongzichan=(scope.enablecoin+scope.unableType)*data.dayMarketList[0].newes_deal+scope.rmb+scope.freezeRmb;
                           scope.newpb=data.dayMarketList[0].sell_price;
                           scope.newps=data.dayMarketList[0].buy_price;
                           console.log("////////")
                           console.log(data);
                       }else{
                       	scope.zongzichan=scope.rmb+scope.freezeRmb;
                           scope.newpb=0;
                           scope.newps=0;
                       }

                       //手续费率
                      postInfo.dataInfos(rootScope.ip+"/trade/getPiundatgeRate.do",{'param':'poundageRate','token':rootScope.tt})
                       .success(function(data){
                           scope.sellshouxufeilv=data.StaticParams.value;
                           scope.buyshouxufeilv=data.StaticParams.value;

                       
                           scope.maxbuynum=0;
                           scope.maxsellnum=0;
                           scope.buyEnum='';
                           scope.zongjiabuy=0;
                            scope.buyOnum='';
                           scope.zongjiasell=0;
                           //最大可买卖数量
                           scope.maiaa=function(){
                               if(scope.buyEprice>0){
                                   scope.maxbuynum=scope.rmb/((scope.buyshouxufeilv-0)+(scope.buyEprice-0));
                                   scope.zongjiabuy=((scope.buyshouxufeilv-0)+(scope.buyEprice-0))*(scope.buyEnum-0);
                                    //总数手续费
                                    scope.buyzongshouxufei=(scope.buyshouxufeilv-0)*(scope.buyEprice-0)*(scope.buyEnum-0);
                                   
                               }else{
                                    scope.maxbuynum=0;
                               }
                           }
                           
                           scope.sellaa=function(){
                               if(scope.buyOprice>0){
                                  scope.maxsellnum=scope.rmb/((scope.sellshouxufeilv-0)+(scope.buyOprice-0));
                                  scope.zongjiasell=((scope.sellshouxufeilv-0)+(scope.buyOprice-0))*(scope.buyOnum-0);
                                   //总数手续费
                                    scope.sellzongshouxufei=(scope.sellshouxufeilv-0)*(scope.buyOprice-0)*(scope.buyOnum-0);
                               }else{
                                    scope.maxsellnum=0;
                               }
                           }
                           //总jia
                           scope.buynumaa=function(){
                               scope.zongjiabuy=((scope.buyshouxufeilv-0)+(scope.buyEprice-0))*(scope.buyEnum-0);
                                //总数手续费
                                scope.buyzongshouxufei=(scope.buyshouxufeilv-0)*(scope.buyEprice-0)*(scope.buyEnum-0);
                           }

                          
                           scope.sellnumaa=function(){
                               scope.zongjiasell=((scope.sellshouxufeilv-0)+(scope.buyOprice-0))*(scope.buyOnum-0);
                               //总数手续费
                               scope.sellzongshouxufei=(scope.sellshouxufeilv-0)*(scope.buyOprice-0)*(scope.buyOnum-0);
                           }

                       })
                       .error(function(error){
                           console.log(error);
                       });
                      
                   })
                   .error(function(error){
                       console.log("数据获取错误"+error);
                   });

               })
               .error(function (data) {
                   console.log("查询币种资产报错");
               });
         }catch(err){
        	 console.log(err)
         }
    })
    .error(function (data) {
        console.log("查询资产报错");
    });

        /*买卖*/
        scope.buyEnter=function () {
            if(rootScope.tt){
            	if(scope.buyEprice!=null){
                    if(scope.buyEprice<0){
                        scope.bindphoneflg=true;
                        scope.bindphoneTip="买价不能小于零!";
                        scope.buyEprice="";
                        timeout(function(){
                            scope.bindphoneflg=false;
                        },1000);
                        return false;
                    };
                }else{
                    scope.bindphoneflg=true;
                    scope.bindphoneTip="买价不能为空!";
                    scope.buyEprice="";
                    timeout(function(){
                        scope.bindphoneflg=false;
                    },1000);
                    return false;
                };
                if(scope.buyEnum!=null){
                    if(scope.buyEnum<0){
                        scope.bindphoneflg=true;
                        scope.bindphoneTip="买入数量不能小于零!";
                        scope.buyEnum="";
                        timeout(function(){
                            scope.bindphoneflg=false;
                        },1000);
                        return false;
                    };
                }else{
                    scope.bindphoneflg=true;
                    scope.bindphoneTip="买入数量不能为空!";
                    timeout(function(){
                        scope.bindphoneflg=false;
                    },1000);
                    return false;
                };
                if(scope.buyEpass!=null){
                    if(scope.buyEpass.length<0||scope.buyEpass.length==0){
                        scope.bindphoneflg=true;
                        scope.bindphoneTip="请输入交易密码!";
                        scope.buyEpass="";
                        timeout(function(){
                            scope.bindphoneflg=false;
                        },1000);
                        return false;
                    }else{
                        postInfo.dataInfos(rootScope.ip+"/common/getTradePassword.do",{'userNo':rootScope.userNo,'dealPwd':scope.buyEpass,'token':rootScope.tt})
                            .success(function(data){
                                if(data.code==22){
                                    scope.buyEpass="";
                                    scope.bindphoneflg=true;
                                    scope.bindphoneTip="请输入正确的交易密码!";
                                    timeout(function(){
                                        scope.bindphoneflg=false;
                                    },1000);
                                    return false;
                                }else if(data.code==200){
                                    scope.buyEpass="";
                                    scope.bindphoneflg=true;
                                    scope.bindphoneTip="请输入正确的交易密码!";
                                    timeout(function(){
                                        scope.bindphoneflg=false;
                                    },1000);
                                    return false;
                                }else if(data.code==100){

                                   postInfo.dataInfos(rootScope.ip+"/trade/goTrade.do",{
                                                'userNo':rootScope.userNo,
                                                'buyPrice':scope.buyEprice,
                                                'buyNum':scope.buyEnum,
                                                'buyMum':scope.maxbuynum,
                                                'buyPayPassword':scope.buyEpass,
                                                'coinType':rootScope.coinType,
                                                'type':"buy",
                                                'poundatge':scope.buyzongshouxufei,
                                                'poundageRate': scope.buyshouxufeilv,
                                                'token':rootScope.tt})
                                        .success(function (data) {
                                        	console.log(data)
                                            scope.jiaoyicg={'display':'block'};
                                            if(data.desc=='success'){
                                                scope.jiaoyia='成功！';
                                                scope.buyF();
                                            }else{
                                                scope.jiaoyia='失败！';
                                            }
                                            timeout(function(){
                                                scope.jiaoyicg={'display':'none'};
                                            },1000);
                                            scope.buyEprice="";
                                            scope.buyEnum="";
                                            scope.buyEpass="";
                                            /*交易记录*/
                                             postInfo.dataInfos(rootScope.ip+"/trade/getDealDetailList.do",{'userNo':rootScope.userNo,'token':rootScope.tt})
                                               .success(function (data) {
                                                    scope.dealDetailList=data.dealDetailList;
                                                })
                                                .error(function (data) {
                                                    console.log("查询资产报错");
                                                });
                                             /*人民币资产查询*/
                                             postInfo.dataInfos(rootScope.ip+"/trade/getUserBalanceInfo.do",{'userNo':rootScope.userNo,'coinType':'0','token':rootScope.tt})
                                            .success(function (data) {
                                                   scope.rmb=data.balanceInfo.enable_coin;
                                                   scope.freezeRmb=data.balanceInfo.unable_coin;

                                                    /*币种资产查询*/
                                                    postInfo.dataInfos(rootScope.ip+"/trade/getUserBalanceInfo.do",{'userNo':rootScope.userNo,'coinType':rootScope.coinType,'token':rootScope.tt})
                                                    .success(function (data) {
                                                        scope.enablecoin=data.balanceInfo.enable_coin;
                                                        scope.unableType=data.balanceInfo.unable_coin;

                                                         //每个币的信息
                                                        postInfo.dataInfos(rootScope.ip+"/dayMarket/selectDayMarket.do",{'coinType':rootScope.coinType,'token':rootScope.tt})
                                                        .success(function(data){
                                                            scope.zongzichan=(scope.enablecoin+scope.unableType)*data.dayMarketList[0].newes_deal+ scope.rmb+scope.freezeRmb;
                                                            scope.newpb=data.dayMarketList[0].sell_price;
                                                            scope.newps=data.dayMarketList[0].buy_price;
                                                        }).error(function(error){
                                                            console.log(error);
                                                        });
                                                       
                                                    }) .error(function(error){
                                                        console.log(error);
                                                    });

                                                }).error(function (error) {
                                                    console.log(error);
                                                });
                                        })
                                        .error(function (data) {
                                            console.log("买入提交报错");
                                        });
                                }
                            })
                            .error(function(data){
                                console.log("交易密码报错")
                            });
                    };
                }else{
                    scope.bindphoneflg=true;
                    scope.bindphoneTip="请输入交易密码!";
                    timeout(function(){
                        scope.bindphoneflg=false;
                    },1000);
                    return false;
                };
            }else{
            	scope.jiaoyicg={'display':'block'};
                scope.jiaoyia='请登录！';
                timeout(function(){
                    scope.jiaoyicg={'display':'none'};
                },1000);
            }
        };

        scope.buyOnter=function () {
           if(rootScope.tt){
        	   if(scope.buyOprice!=null){
                   if(scope.buyOprice<0){
                       scope.bindphoneflg=true;
                       scope.bindphoneTip="卖价不能小于零!";
                       scope.buyOprice="";
                       timeout(function(){
                           scope.bindphoneflg=false;
                       },1000);
                       return false;
                   };
               }else{
                   scope.bindphoneflg=true;
                   scope.bindphoneTip="卖价不能为空!";
                   scope.buyOprice="";
                   timeout(function(){
                       scope.bindphoneflg=false;
                   },1000);
                   return false;
               };
               if(scope.buyOnum!=null){
                   if(scope.buyOnum<0){
                       scope.bindphoneflg=true;
                       scope.bindphoneTip="卖入数量不能小于零!";
                       scope.buyOnum="";
                       timeout(function(){
                           scope.bindphoneflg=false;
                       },1000);
                       return false;
                   };
               }else{
                   scope.bindphoneflg=true;
                   scope.bindphoneTip="卖入数量不能为空!";
                   timeout(function(){
                       scope.bindphoneflg=false;
                   },1000);
                   return false;
               };
               if(scope.buyOpass!=null){
                   if(scope.buyOpass.length<0||scope.buyOpass.length==0){
                       scope.bindphoneflg=true;
                        scope.bindphoneTip="请输入交易密码!";
                        scope.buyOpass="";
                        timeout(function(){
                           scope.bindphoneflg=false;
                        },1000);
                       return false;
                   }else{
                       
                       postInfo.dataInfos(rootScope.ip+"/common/getTradePassword.do",{'userNo':rootScope.userNo,'dealPwd':scope.buyOpass,'token':rootScope.tt})
                           .success(function(data){
                               if(data.code==22){
                                   scope.bindphoneflg=true;
                                    scope.bindphoneTip="请输入正确的交易密码!";
                                   scope.buyOpass="";
                                    timeout(function(){
                                    scope.bindphoneflg=false;
                                    },1000);
                                   return false;
                               }else if(data.code==200){
                                    scope.bindphoneflg=true;
                                    scope.bindphoneTip="请输入正确的交易密码!";
                                   scope.buyOpass="";
                                    timeout(function(){
                                    scope.bindphoneflg=false;
                                    },1000);
                                   return false;
                               }else if(data.code==100){
                                   postInfo.dataInfos(rootScope.ip+"/trade/goTrade.do",{
                                       'userNo':rootScope.userNo,
                                       'buyPrice':scope.buyOprice,
                                       'buyNum':scope.buyOnum,
                                       'buyMum': scope.maxsellnum,
                                       'buyPayPassword':scope.buyOpass,
                                       'coinType':rootScope.coinType,
                                       'type':"sell",
                                       'poundatge':scope.sellzongshouxufei,
                                       'poundageRate': scope.sellshouxufeilv,
                                       'token':rootScope.tt})
                                   .success(function (data) {
                                        scope.jiaoyicg={'display':'block'};
                                           if(data.desc=='success'){
                                               scope.jiaoyia='成功！';
                                               scope.sellF();
                                           }else{
                                               scope.jiaoyia='失败！';
                                           }
                                           timeout(function(){
                                               scope.jiaoyicg={'display':'none'};
                                           },1000);
                                           scope.buyOprice="";
                                           scope.buyOnum="";
                                           scope.buyOpass="";
                                           /*交易记录*/
                                            postInfo.dataInfos(rootScope.ip+"/trade/getDealDetailList.do",{'userNo':rootScope.userNo,'token':rootScope.tt})
                                              .success(function (data) {
                                                   scope.dealDetailList=data.dealDetailList;
                                               }) .error(function (data) {
                                                   console.log("查询资产报错");
                                               });
                                            /*人民币资产查询*/
                                            postInfo.dataInfos(rootScope.ip+"/trade/getUserBalanceInfo.do",{'userNo':rootScope.userNo,'coinType':'0','token':rootScope.tt})
                                           .success(function (data) {
                                                  scope.rmb=data.balanceInfo.enable_coin;
                                                  scope.freezeRmb=data.balanceInfo.unable_coin;

                                                   /*币种资产查询*/
                                                   postInfo.dataInfos(rootScope.ip+"/trade/getUserBalanceInfo.do",{'userNo':rootScope.userNo,'coinType':rootScope.coinType,'token':rootScope.tt})
                                                   .success(function (data) {
                                                       scope.enablecoin=data.balanceInfo.enable_coin;
                                                       scope.unableType=data.balanceInfo.unable_coin;

                                                        //每个币的信息
                                                       postInfo.dataInfos(rootScope.ip+"/dayMarket/selectDayMarket.do",{'coinType':rootScope.coinType,'token':rootScope.tt})
                                                       .success(function(data){
                                                           scope.zongzichan=(scope.enablecoin+scope.unableType)*data.dayMarketList[0].newes_deal+ scope.rmb+scope.freezeRmb;
                                                           scope.newpb=data.dayMarketList[0].sell_price;
                                                           scope.newps=data.dayMarketList[0].buy_price;
                                                       }).error(function(error){
                                                           console.log(error);
                                                       });
                                                      
                                                   }) .error(function(error){
                                                       console.log(error);
                                                   });

                                               }).error(function (error) {
                                                   console.log(error);
                                               });
                                   })
                                   .error(function (data) {
                                       console.log("卖出提交报错");
                                   });
                               }
                           })
                           .error(function(data){
                               console.log("交易密码报错")
                           });
                   };
               }else{
                   scope.bindphoneflg=true;
                   scope.bindphoneTip="请输入交易密码!";
                   scope.buyOpass="";
                   timeout(function(){
                       scope.bindphoneflg=false;
                   },1000);
                   return false;
               };
           }else{
        	   scope.jiaoyicg={'display':'block'};
               scope.jiaoyia='请登录！';
               timeout(function(){
                   scope.jiaoyicg={'display':'none'};
               },1000);
           }
        };
           //entrustCoin<Integer> : 货币类型
          //entrustType<Integer> : 交易类型(卖:'1'/买:'0')
          //number<Integer> : 查询的条数
          //买卖
        scope.sellF=function () {
            postInfo.dataInfos(rootScope.ip+"/trade/getEntrustOfTradeCenter.do",
            {'userNo':rootScope.userNo,'entrustType':'1','number':'25','entrustCoin':rootScope.coinType,'token':rootScope.tt})
            .success(function(data){
                console.log(data)
                scope.selllist=data.EntrustOfTradeCenterList;
            })
            .error(function(error){
                console.log(error);
            });
        };
        scope.sellF();
        scope.buyF=function () {
            postInfo.dataInfos(rootScope.ip+"/trade/getEntrustOfTradeCenter.do",
            {'userNo':rootScope.userNo,'entrustType':'0','number':'25','entrustCoin':rootScope.coinType,'token':rootScope.tt})
            .success(function(data){
                scope.buylist=data.EntrustOfTradeCenterList;
            })
            .error(function(error){
                console.log(error);
            });
        };
        scope.buyF();
      });
    scope.sellGain=function(a,b){
        scope.buyEprice=a;
        scope.buyEnum=b;
        scope.buynumaa();
        scope.maiaa();
    };
    scope.buyGain=function(a,b){
        console.log(a,b)
        scope.buyOprice=a;
        scope.buyOnum=b;
        scope.sellnumaa();
        scope.sellaa();
    };
     /*委托记录*/
        //userNo<Integer> : 用户编号
        //state<Integer> : 状态(0:委托中 1：已完成 2：已撤销)
        //number<Integer> : 查询的条数
        // 委托币种 entrust_coin
        // 委托类型 0：买 1：卖 entrust_type
        // 委托价格 entrust_price
        // 委托数量 entrust_num
        // 成交数量 deal_num
        // 手续费 piundatge
        // 状态 0:委托中 1：已完成 2：撤销 state
      scope.changewei=function(a){
		if(a==0){
			return "买"
		}else if(a==1){
			return "卖";
		}
	}
        scope.chexiaojilu=function(){
	    	postInfo.dataInfos(rootScope.ip+"/trade/getNotTradeEntrustOfTradeCenter.do",{'userNo':rootScope.userNo,'state':'0','number':'10','token':rootScope.tt})
	        .success(function (data) {
	            console.log(data)
	             scope.wtList=data.NotTradeEntrustOfTradeCenter;
	        })
	        .error(function (data) {
	            console.log("委托记录报错");
	        });
    	};
    	scope.chexiaojilu();
    	//撤销记录
	    scope.chexiao=function(id){
	    	postInfo.dataInfos(rootScope.ip+"/trade/delEntrust.do",
	    			{'userNo':rootScope.userNo,'id':id,'token':rootScope.tt})
	        .success(function (data) {
	        	scope.tisFalg=true;
	        	 scope.tis="撤销成功！"
	        	timeout(function(){
	        		scope.tisFalg=false;
	        		scope.chexiaojilu();
	        	},1500);
	        })
	        .error(function (data) {
	            console.log("委托记录报错");
	            scope.tis="撤销失败！"
	            scope.tisFalg=true;
	        	timeout(function(){
	        		scope.tisFalg=false;
	        		scope.chexiaojilu();
	        	},1500);
	        });
	    }

    /*交易记录*/
    scope.changetype=function(a){
		if(a==0){
			return "买"
		}else if(a==1){
			return "卖";
		}
	}
     postInfo.dataInfos(rootScope.ip+"/trade/getDealDetailList.do",
    		 {'userNo':rootScope.userNo,'token':rootScope.tt})
       .success(function (data) {
            scope.dealDetailList=data.dealDetailList;
        })
        .error(function (data) {
            console.log("查询资产报错");
        });

// 生成滑动条
    var dec = document.querySelector('.js-decimal');
    var initDec = new Powerange(dec, {step:1,callback: function(){
        // console.log(dec.value);
        scope.mrbl=dec.value;
        // console.log(scope.mrbl);
        $("#mrbl").html(dec.value*100);
    }, max: 10, start: 5,hideRange:true});

    var dec1 = document.querySelector('.js-decimal1');
    var initDec = new Powerange(dec1, {step:1, callback: function(){
        scope.mrbl=dec1.value;
        $("#mcbl").html(dec1.value*100);
    }, max: 10, start: 5,hideRange:true});
}]);