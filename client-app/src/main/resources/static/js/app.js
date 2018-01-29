'use strict';

var app = angular.module('client-app', ['ngRoute', 'ngStorage']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'template/authenticate.html',
            controller: 'AuthenticationController'
        });
});

app.controller('AuthenticationController', [
                '$scope', '$rootScope', '$localStorage', '$location', '$http', '$window',
                function($scope, $rootScope, $localStorage, $location, $http, $window) {
    console.log('Authentication area: ' + $window.localStorage.getItem("token"));

    $scope.authenticate = function() {
        $window.location.href = 'http://localhost:8081/#/authorization?' +
            "appId=id&" +
            "appSecret=secret&" +
            "grantType=token&" +
            "scope=firstname,lastname";
    }
}]);