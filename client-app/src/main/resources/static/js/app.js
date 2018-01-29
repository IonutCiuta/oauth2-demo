'use strict';

var app = angular.module('client-app', ['ngRoute', 'ngStorage']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'template/authenticate.html',
            controller: 'AuthenticationController'
        })
        .when('/credentials', {
            templateUrl: 'template/credentials.html',
            controller: 'CredentialsController'
        });
});

app.controller('AuthenticationController', [
                '$scope', '$rootScope', '$localStorage', '$location', '$http', '$window',
                function($scope, $rootScope, $localStorage, $location, $http, $window) {
    console.log('Authentication area: ' + $window.localStorage.getItem("token"));

    $scope.authenticate = function() {
        $window.location.href = 'http://localhost:8081/#/authorization?' +
            "appId=95602&" +
            "appSecret=secret&" +
            "grantType=token&" +
            "scope=firstname,lastname";
    }
}]);

app.controller('CredentialsController', [
                '$scope', '$rootScope', '$localStorage', '$location', '$http',
                function($scope, $rootScope, $localStorage, $location, $http) {
    console.log('Credentials area');

    $scope.saveCredentials = function() {
        console.log('Credentials: ' + JSON.stringify($scope.app));

        $http.post('/api/v1/authorization/credentials', $scope.app, {})
            .then(function(response) {
               $location.path('/');
            }, function(error) {
                console.error(JSON.stringify(error));
            });
    }
}]);