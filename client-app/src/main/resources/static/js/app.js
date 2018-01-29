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
        $http.get('/api/v1/authorization/credentials')
        .then(function(response) {
            $window.location.href = 'http://localhost:8081/#/authorization' +
               "?appId=" + response.data.appId +
               "&appSecret=" + response.data.appSecret +
               "&grantType=token" +
               "&scope=firstname,lastname";
        }, function(error) {
            console.error(JSON.stringify(error));
        });
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