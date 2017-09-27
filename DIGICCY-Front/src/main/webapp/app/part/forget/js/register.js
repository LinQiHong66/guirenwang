var reg=angular.module('regApp',['ngRoute']);


reg.config(["$routeProvider",function($routeProvider){
    $routeProvider
        .when('/index',{
            templateUrl:"./view/index.html",
            controller:"indexCtrl"
        }).when('/findcode',{
            templateUrl:"./view/findcode.html",
            controller:"fdCtrl"
        }).otherwise({
            redirectTo:'/index'
        })
}]);


// var mall=angular.module('mallApp',['ngRoute','autoActive']);
// mall.config(["$routeProvider",function($routeProvider){
//     $routeProvider
//         .when('/default',{
//             templateUrl:"./view/default.html",
//             controller:"defCtrl"
//         }).when('/log',{
//             templateUrl:"./view/log.html",
//             controller:"logCtrl"
//         }).when('/car',{
//             templateUrl:"./view/car.html",
//             controller:"carCtrl"
//         }).when('/address',{
//             templateUrl:"./view/address.html",
//             controller:"adsCtrl"
//         }).when('/discription',{
//             templateUrl:"./view/discription.html",
//             controller:"disCtrl"
//         }).otherwise({
//             redirectTo:'/default'
//         })
// }]);