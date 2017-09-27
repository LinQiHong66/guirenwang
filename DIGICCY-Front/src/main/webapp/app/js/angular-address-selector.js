var mod = angular.module('ng.address.selector', []);

mod.directive('addressSelector', ['$http', '$filter', function($http, $filter) {
    return {
        scope: {},
        controller: function($scope, $element, $attrs, $transclude) {
            var addressArr = $scope.addressArr = [];

            this.getScope = function() {
                return $scope;
            }
            this.pushAddress = function(address) {
                addressArr.push(address);
            }

            $scope.$on('childChange', function(ev, model, scope){
            	angular.forEach(addressArr, function(value, key){
            		value.$emit('changeModel', model, scope);
            	});
            });

            $http.get($attrs.data).success(function(res) {
            	$scope.$emit('childChange', res);
            });
        },
        restrict: 'AE',
    };

}]);

mod.directive('province', ['$filter', function($filter) {

    return {
        scope: {
            code: '@'
        },
        require: '^?addressSelector',
        restrict: 'AE',
        controller: function($scope, $element, $attrs, $transclude) {
            $scope.name = 'province'

            $scope.$on('changeModel', function(ev, res, scope){
                if(scope === $scope)return;

            	var province = res && res.province;

            	if(province){
            		var code = $scope.code;

            		if(code && !$scope.model){
            			$scope.model = $filter('filter')(province, {code: code})[0];
            		}
            		else{
            			$scope.model = province[0]
            		}
            		$scope.province = province;
            	}
            });

            $scope.$watch('model', function(newValue, oldValue, scope) {
            	if(newValue){
                    $scope.change(newValue);
                }
            });
        },
        template: '<select ng-model="model" ng-options="p.name for p in province track by p.code"></select>',
        replace: true,
        link: function($scope, iElm, iAttrs, addressController) {
            if (!addressController) return;

            addressController.pushAddress($scope);

            $scope.change = function(model){
            	addressController.getScope().$emit('childChange', model, $scope);
            }
        }
    };

}]);
mod.directive('city', ['$filter', function($filter) {

    return {
        scope: {
            code: '@'
        },
        require: '^?addressSelector',
        restrict: 'AE',
        controller: function($scope, $element, $attrs, $transclude) {
            $scope.name = 'city'
            $scope.$on('changeModel', function(ev, res, scope){
                if(scope === $scope)return;

            	var city = res && res.city;

            	if(city && city.length>0){
            		var code = $scope.code;

            		if(code && !$scope.model){
            			$scope.model = $filter('filter')(city, {code: code})[0];
            		}
            		else{
            			$scope.model = city[0]
            		}
            	}
                $scope.city = city;
            });

            $scope.$watch('model', function(newValue, oldValue, scope) {
                if(newValue){
                    $scope.change(newValue);
                }
            });
        },
        template: '<select ng-model="model" ng-show="city.length" ng-options="c.name for c in city track by c.code"></select>',
        replace: true,
        link: function($scope, iElm, iAttrs, addressController) {
            if (!addressController) return;

            addressController.pushAddress($scope);

            $scope.change = function(model){
            	addressController.getScope().$emit('childChange', model, $scope);
            }
        }
    };

}]);

mod.directive('area', ['$filter', function($filter) {
    return {
        scope: {
            code: '@'
        },
        require: '^?addressSelector',
        controller: function($scope, $element, $attrs, $transclude) {
            $scope.$on('changeModel', function(ev, res, scope){
                if(scope === $scope)return;

            	var area = res && res.area;

            	if(area && area.length>0){
            		var code = $scope.code;

            		if(code && !$scope.model){
            			$scope.model = $filter('filter')(area, {code: code})[0];
            		}
            		else{
            			$scope.model = area[0]
            		}
            	}
                $scope.area = area;
            });
        },
        restrict: 'AE',
        template: '<select\
			            ng-model="model"\
                        ng-show="area.length"\
			            ng-options="a.name for a in area track by a.code">\
			       </select>',
        replace: true,
        link: function($scope, iElm, iAttrs, addressController) {
            if (!addressController) return;

            addressController.pushAddress($scope);

            $scope.change = function(model){
            	addressController.getScope().$emit('childChange', model);
            }
        }
    };
}]);
