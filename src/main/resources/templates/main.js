var app = angular.module('aitu-project', []);
let auth_state = window.localStorage.getItem("admin_auth_state");
let auth_token = window.localStorage.getItem("admin_auth_token");
let me = window.localStorage.getItem('me');
app.controller('adminController', function($scope, $http) {
    $auth = {
        login: '',
        password: ''
    }
    $scope.order_status = ''
    $scope.orders = []
    $scope.customer = me ? me : {}
    $scope.isAuthorized = auth_state === "true";
    $scope.token = auth_token ? auth_token : "";
    $scope.current_order = {}
    $scope.changed_status = '';
    $scope.onChangeStatus = function(index){
        let orderId = index
        $http({
                    url: 'http://127.0.0.1:8080/api/orders/'+orderId+'/status/'+$scope.changed_status,
                    method: "PUT",
                    headers: {
                        "Access-Control-Allow-Origin": "*",
                        "Content-Type": "application/json",
                        "Auth": $scope.token
                    },
                    data: 'status:'+$scope.changed_status
                })
                    .then(function (response) {
                            console.log(response.data)
                            $scope.orders.map(item => {
                                if(item.id === orderId){
                                    item.status = $scope.changed_status
                                }
                            })
                            $scope.changed_status = ''
                        }).catch((response)=>{
                            console.log(response)
                            alert('Some error occured')
                        });
    }
    $scope.onInput = function(){
        console.log($scope.order_status);
        $http({
            url: 'http://127.0.0.1:8080/api/orders/status/'+$scope.order_status,
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
        console.log(auth);
            $http({
                url: 'http://127.0.0.1:8080/api/admin/login',
                method: "POST",
                headers: {
                    "Access-Control-Allow-Origin": "*",
                    "Content-Type": "application/json"
                },
                data: auth
            })
                .then(function(response) {
                    if(response.status === 200){
                        $scope.auth = response.data;
                        console.log(response.data);
                        $scope.isAuthorized = true;
                        window.localStorage.setItem("admin_auth_state","true");
                        window.localStorage.setItem("admin_auth_token",response.data.token);
                        $scope.token = response.data.token;
                    } else {
                        reject(response.data)
                    }
                    }).then(function(){
                        $scope.getMe();
                    })
                    .catch((error)=>{
                        console.log(error);
                       if(error.status === 401){
                        alert("Wrong login and password");
                       }
                       $scope.isAuthorized = false;
                       window.localStorage.setItem("admin_auth_state","false");
                       window.localStorage.removeItem("admin_auth_token");
                       window.localStorage.removeItem("me");
                       $scope.customer = {}
                    });
                    }
        $scope.logOut = function(){
                window.localStorage.setItem("admin_auth_state","false");
                $scope.isAuthorized = false;
                window.localStorage.removeItem("admin_auth_token");
                window.localStorage.removeItem("me");
                $scope.customer = {}
            }
})