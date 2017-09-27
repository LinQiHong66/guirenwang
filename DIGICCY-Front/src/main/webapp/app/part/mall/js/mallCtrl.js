mall.controller('mallCtrl',['$scope','$http',function(scope,http){
	http({
		url:'../../mall.json',
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

        scope.nav=data.nav;
	}).error(function(data){
        console.log(data);
    });
}])