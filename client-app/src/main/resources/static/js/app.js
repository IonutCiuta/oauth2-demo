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
    console.log('Authentication area');

    $scope.authenticate = function() {
        $window.location.href = 'http://localhost:8080/#/authorization';
    }
}]);