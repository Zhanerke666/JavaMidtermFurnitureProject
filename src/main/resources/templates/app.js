var app = angular.module('aitu-project', []);

app.controller('ProductCtrl', function($scope, $http) {
    $scope.productList = [];
    $scope.categoryList = [];

    $scope.getProducts = function() {
        $http({
            url: 'http://127.0.0.1:8080/api/product',
            method: "GET",
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json"
            }
        })
            .then(function (response) {
                    console.log(response);
                    $scope.productList = response.data;
                },
                function (response) { // optional
                    console.log(response);
                });
    };







    $scope.getCategories = function() {
        $http({
            url: 'http://127.0.0.1:8080/api/category',
            method: "GET",
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json"
            }
        })
            .then(function (response) {
                    console.log(response);
                    $scope.categoryList = response.data;
                },
                function (response) { // optional
                    console.log(response);
                });


    };

    $scope.getProducts();
    $scope.getCategories();






    $scope.auth = {
        login: '',
        password: ''
    };

    $scope.customer = {};

    $scope.getMe = function () {
        $http({
            url: 'http://127.0.0.1:8080/customers/me',
            method: "GET",
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json",
                "Auth": $scope.auth.token
            }

        })
            .then(function (response) {

                    $scope.customer = response.data;

                },
                function (response) { // optional
                    console.log(response);
                    $scope.customer = {};
                });

    }

    $scope.login = function (auth) {
        $http({
            url: 'http://127.0.0.1:8080/login',
            method: "POST",
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json"
            },
            data: auth
        })
            .then(function (response) {

                    $scope.auth = response.data;
                    $scope.getMe();

                },
                function (response) { // optional

                    $scope.auth = {};
                });

    };




});



