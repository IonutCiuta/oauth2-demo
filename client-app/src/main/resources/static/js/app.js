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
        })
        .when('/account', {
            templateUrl: 'template/account.html',
            controller: 'AccountController'
        })
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

app.controller('AccountController', [
                '$scope', '$rootScope', '$localStorage', '$location', '$http',
                function($scope, $rootScope, $localStorage, $location, $http) {
    var params = $location.search();
    var token = params.token;
    console.log('Account area: ' + token);

    $localStorage.token = token;

    $scope.showDetails = false;
    $scope.showSpinner = true;

    $scope.hide = function() {
        $scope.showDetails = true;
        $scope.showSpinner = false;
    }

    window.setTimeout(function() {
        $http.get('/api/v1/account/details',
            {
                headers: {
                    'Content-Type': 'application/json',
                    'User-Token': token
                }
            }
        ).then(function(response) {
            console.log(JSON.stringify(response.data));
            $scope.showDetails = true;
            $scope.showSpinner = false;
            $scope.details = response.data;
        }, function(error) {
            console.error(JSON.stringify(error));
        });
    }, 5000)
}]);