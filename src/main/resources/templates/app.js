var app = angular.module('aitu-project', []);
let auth_state = window.localStorage.getItem("auth_state");
let auth_token = window.localStorage.getItem("auth_token");
app.controller('ProductCtrl', function($scope, $http) {
    $scope.order_status = ''
    $scope.orders = []
    $scope.current_order = {}
    $scope.changed_status = '';
    $scope.productList = [];
    $scope.categoryList = [];
    $scope.cart = [];
    $scope.order = {};
    $scope.isAuthorized = auth_state === "true";
    $scope.token = auth_token ? auth_token : "";
    $scope.orderList = [];



    $scope.onInput = function(){
        console.log($scope.order_status);
        $http({
            url: 'http://127.0.0.1:8080/api/orders/status/customer/'+$scope.order_status,
            method: "GET",
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json",
                "Auth": $scope.token
            },
            data: 'status:'+$scope.order_status
        })
            .then(function (response) {
                console.log(response.data)
                $scope.orders = response.data
            }).catch((response)=>{
            console.log(response)
            $scope.orders = []
        });
    }

    $scope.showMore = function(index){
        console.log(index)
        let id = $scope.orders[index].id
        $http({
            url: 'http://127.0.0.1:8080/api/orders/'+id,
            method: "GET",
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json",
                "Auth": $scope.token
            },
            data: 'id:'+id
        })
            .then(function (response) {
                console.log(response.data)
                $scope.current_order = response.data
            }).catch((response)=>{
            console.log(response)
            $scope.current_order = {}
        });
    }

    $scope.addToCart = function(product){
        console.log($scope.cart.length);
        if($scope.cart.length > 0){
            console.log("length true");
            let isExists = false;
           $scope.cart.map((item)=>{
                       if(item.id === product.id){
                            isExists = true;
                           item.quantity += 1;
                           item.totalSum = item.quantity * item.price;
                       }

                   });
            if(!isExists){
                product.quantity=1;
                product.totalSum = product.price;
                $scope.cart.push(product);
            }
        } else{
            product.quantity=1;
            product.totalSum = product.price;
            $scope.cart.push(product);
            }
    }
    $scope.totalSum = ()=>{
        let res=0;
        $scope.cart.map((item)=>{
            res+=item.totalSum;
        })
        return res;
    }
    $scope.collectOrder = ()=>{
        let today = new Date();
        let dd = String(today.getDate()).padStart(2, '0');
        let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        let yyyy = today.getFullYear();
        today = mm + '/' + dd + '/' + yyyy;
        let order = {};
        order.totalPrice = $scope.totalSum();
        order.date = today;
        order.orderDetailInfo = [];
        if($scope.cart.length){
            $scope.cart.map((item)=>{
                let orderItem = {};
                orderItem.price = item.price;
                orderItem.totalSum = item.totalSum;
                orderItem.productId = item.id;
                orderItem.quantity = item.quantity;
                order.orderDetailInfo.push(orderItem);
            })
        }else{
            alert("No items in the cart");
            return order;
        }
        console.log(order);
        console.log(JSON.stringify(order));
        return order;
    }

    $scope.makeOrder = function(){
        data = $scope.collectOrder();
        $http({
            url: 'http://127.0.0.1:8080/api/orders',
            method: "POST",
            data: data,
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json",
                "Auth": $scope.token
            }
        })
            .then(function (response) {
                    console.log(response);
                    if(response.status === 200){
                        $scope.cart = [];
                        $scope.order = {};
                        alert('Спасибо за заказ!!!')
                    }
                }).catch((response)=>{
                    alert(response);
                });
    }
    $scope.getOrders = function() {
                $http({
                    url: 'http://127.0.0.1:8080/api/orders',
                    method: "GET",
                    headers: {
                        "Access-Control-Allow-Origin": "*",
                        "Content-Type": "application/json",
                        "Auth": $scope.token
                    }
                })
                    .then(function (response) {
                            console.log(response.data);
                            $scope.orderList = response.data;

                        }).catch((error)=>{
                            console.log(error);
                        })
            };
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
                    console.log(response.data);
                    $scope.categoryList = response.data;
                },
                function (response) { // optional
                    console.log(response);
                });
    };
    $scope.getProductsByCategory = function(categoryID) {
        $http({
            url: 'http://127.0.0.1:8080/api/product/category/' + categoryID,
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
    $scope.getProducts();
    $scope.getCategories();
    $scope.getOrders();
    $scope.auth = {
        login: '',
        password: ''
    };
    $scope.customer = {};
    $scope.getMe = function(){
        $http({
            url: 'http://127.0.0.1:8080/customers/me',
            method: "GET",
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json",
                "Auth": $scope.token
            }
        })
            .then(function (response) {
                    $scope.customer = response.data;
                    localStorage.setItem('me',response.data);
                },
                function (response) { // optional
                    console.log(response);
                    $scope.customer = {};
                }).catch((response)=>{
                    alert(response);
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
                    console.log(response.data);
                    $scope.isAuthorized = true;
                    window.localStorage.setItem("auth_state","true");
                    window.localStorage.setItem("auth_token",response.data.token);
                    $scope.token = response.data.token;
                }).then(function(){
                    $scope.getMe();
                })
                .catch((error)=>{
                   alert(error);
                   $scope.isAuthorized = false;
                   window.localStorage.setItem("auth_state","false");
                   window.localStorage.removeItem("auth_token");
                });
                }
    $scope.logOut = function(){
            window.localStorage.setItem("auth_state","false");
            $scope.isAuthorized = false;
            window.localStorage.removeItem("auth_token");
        }

    });



