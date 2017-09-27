(function(angular) {
    'use sctrict';
    var autoActive = angular.module('autoActive', []);
    autoActive.directive('autoActive', ['$location', function($location) {
        return {
            link: function(scope, element, attributes) {
                var hash = $location.url();
                scope.location = $location;
                scope.$watch('location.url()', function(now, old) {
                    var aLink = element.children().attr('href').substr(1);
                    if (now.startsWith(aLink)) {
                        element.parent().children().removeClass(attributes.autoActive);
                        element.addClass(attributes.autoActive);
                    }
                });
            }
        }
    }]);
})(angular);

