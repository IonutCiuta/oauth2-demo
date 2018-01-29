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
        })
        .when('/account', {
            templateUrl: 'template/account.html',
            controller: 'AccountController'
        })
        .when('/authorization', {
            templateUrl: 'template/authorization.html',
            controller: 'AuthorizationController'
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
        $http.post('/api/v1/account/create', $scope.user, {})
            .then(function(response) {
                console.log("Account created successfully!");
                $rootScope.username = $scope.user.username;
                $rootScope.password = $scope.user.password;
                $scope.authenticate();
            }, function(error) {
                console.error(JSON.stringify(error));
            });
    }
}]);

app.controller('AuthenticationController', [
                '$scope', '$rootScope', '$localStorage', '$location', '$http', '$window',
                function($scope, $rootScope, $localStorage, $location, $http, $window) {
    console.log('Authentication area');

    checkUserDetails();

    $scope.register = function() {
        $http.post('/api/v1/account/authenticate', $scope.user, {})
            .then(function(response) {
                console.log("Authentication was successful: " + response.data.token);

                $localStorage.token = response.data.token;
                $localStorage.user = $scope.user.username;

                $location.path('/account');
            }, function(error) {
                console.error(JSON.stringify(error));
            });
    }

    $scope.authenticateUser = function() {
        console.log('User: ' + JSON.stringify($scope.user));

        if($scope.user.username === 'admin' && $scope.user.password === 'admin') {
            $location.path('/credentials');
        } else {
            $scope.register();
        }
    }

    function checkUserDetails() {
        if($rootScope.username && $rootScope.password) {
            console.log("Found user details")
            $scope.user = {
                "username": $rootScope.username,
                "password": $rootScope.password
            }
        }
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
    console.log('Account area: ' + $localStorage.token);

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
                    'User-Token': $localStorage.token
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

app.controller('AuthorizationController', [
                '$scope', '$rootScope', '$localStorage', '$location', '$http', '$window',
                function($scope, $rootScope, $localStorage, $location, $http, $window) {
    var params = $location.search();
    console.log('Authorization area: ' + JSON.stringify(params));

    $scope.authUser = $localStorage.user
    $scope.scopes = params.scope;

    $scope.showDetails = false;
    $scope.showSpinner = true;

    window.setTimeout(function() {
        $http.get('/api/v1/resource/client/name?appId=' + params.appId)
        .then(function(response) {
            console.log(JSON.stringify(response.data));
            $scope.showDetails = true;
            $scope.showSpinner = false;
            $scope.appName = response.data.value;
        }, function(error) {
            console.error(error);
        });
    }, 3000)

    $scope.authorize = function() {
        console.log("Authorize");
        window.setTimeout(function() {
            $http.get('/api/v1/account/authenticate/external' +
                 "?appId=" + params.appId +
                 "&appSecret=" + params.appSecret +
                 "&scope=" + params.scope)
            .then(function(response) {
                console.log(JSON.stringify(response.data));

            }, function(error) {
                console.error(error);
            });
        }, 3000)
    }

    $scope.decline = function() {
        console.log("Decline");
    }
}]);