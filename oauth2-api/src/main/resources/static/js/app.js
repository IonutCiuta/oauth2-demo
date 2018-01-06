'use strict';

var app = angular.module('oauth2-api', ['ngRoute', 'ngStorage']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'template/register.html',
            controller: 'RegisterController'
        })
        .when('/success', {
            templateUrl: 'template/success.html',
            controller: 'SuccessController'
        })
        .when('/failure', {
            templateUrl: 'template/failure.html',
            controller: 'FailureController'
        });
});

app.controller('RegisterController', [
                '$scope', '$rootScope', '$localStorage', '$location', '$http',
                function($scope, $rootScope, $localStorage, $location, $http) {
    console.log('Register area');

    $scope.register = function() {
        console.log('Register:' + $scope.appDetails);

        $http.get(
            '/api/v1/oauth/register?' + getParams(),
            {
                headers: {'Content-Type': 'application/json'}
            }
        ).then(function(response) {
            console.log(JSON.stringify(response.data));
            $rootScope.credentials = response.data;
            $location.path('/success');
        }, function(error) {
            console.error(JSON.stringify(error));
        });
    }

    function getParams() {
        return 'appName=' + $scope.appDetails.name + "&" +
               'appDomain=' + encodeURI($scope.appDetails.domain) + "&" +
               'redirectUrl=' + encodeURI($scope.appDetails.redirectUrl)
    }
}]);

app.controller('SuccessController', [
                '$scope', '$rootScope', '$localStorage', '$location', '$http',
                function($scope, $rootScope, $localStorage, $location, $http) {
    console.log('Success area');
}]);