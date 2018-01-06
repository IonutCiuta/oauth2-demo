'use strict';

var app = angular.module('trusted-app', ['ngRoute', 'ngStorage']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'template/createAccount.html',
            controller: 'CreateAccountController'
        })
        .when('/authenticate', {
            templateUrl: 'template/authenticate.html',
            controller: 'AuthenticationController'
        })
        .when('/credentials', {
            templateUrl: 'template/credentials.html',
            controller: 'CredentialsController'
        });
});

app.controller('CreateAccountController', [
                '$scope', '$rootScope', '$localStorage', '$location', '$http',
                function($scope, $rootScope, $localStorage, $location, $http) {
    console.log('Create account area');

    $scope.authenticate = function() {
        $location.path('/authenticate');
    }

    $scope.createAccount = function() {
        console.log('Account:' + JSON.stringify($scope.user));
    }
}]);

app.controller('AuthenticationController', [
                '$scope', '$rootScope', '$localStorage', '$location', '$http',
                function($scope, $rootScope, $localStorage, $location, $http) {
    console.log('Authentication area');

    $scope.register = function() {
        $location.path('/');
    }

    $scope.authenticateUser = function() {
        console.log('User: ' + JSON.stringify($scope.user));

        if($scope.user.username === 'admin' && $scope.user.password === 'admin') {
            $location.path('/credentials');
        } else {
            $scope.register();
        }
    }
}]);

app.controller('CredentialsController', [
                '$scope', '$rootScope', '$localStorage', '$location', '$http',
                function($scope, $rootScope, $localStorage, $location, $http) {
    console.log('Credentials area');

    $scope.saveCredentials = function() {
        console.log('Credentials: ' + JSON.stringify($scope.app));
        $location.path('/');
    }
}]);