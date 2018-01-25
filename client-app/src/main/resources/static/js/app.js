'use strict';

var app = angular.module('client-app', ['ngRoute', 'ngStorage']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'template/authenticate.html',
            controller: 'CreateAccountController'
        });
});

app.controller('AuthenticationController', [
                '$scope', '$rootScope', '$localStorage', '$location', '$http',
                function($scope, $rootScope, $localStorage, $location, $http) {
    console.log('Authentication area');

    $scope.authenticate = function() {
        $location.path('/authenticate');
    }
}]);